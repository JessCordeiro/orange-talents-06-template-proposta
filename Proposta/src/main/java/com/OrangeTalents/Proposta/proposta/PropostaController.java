package com.OrangeTalents.Proposta.proposta;



import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


import com.OrangeTalents.Proposta.cartoes.CartaoResponse;
import com.OrangeTalents.Proposta.cartoes.CartoesClient;

import com.OrangeTalents.Proposta.validacao.Logs;







@RestController
@RequestMapping("/propostas")
public class PropostaController {
	
	
	@Autowired
	PropostaRepository repository;
	
	@Autowired
	CartoesClient cartaoClient;
	
	@PersistenceContext
	private EntityManager em;
	
	private final Logger logger = LoggerFactory.getLogger(Logs.class);
	
	@PostMapping
	@Transactional
	ResponseEntity<?> cadastrar(@Valid @RequestBody PropostaRequest request, UriComponentsBuilder uriComponentsBuilder){
		if(repository.existsByDocumento(request.getDocumento())) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Já existe documento cadastrado");
		
	}

		Proposta proposta = request.toModel();
		 logger.info("Proposta Criada com Sucesso!", proposta.getDocumento());
		repository.save(proposta);
		return ResponseEntity.created(uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri()).build();
		
		
	}

		
	
	
	@Transactional
	@Scheduled(fixedDelayString = "${periodicidade.executa-operacao}")
	public void associaCartao() {
		
		List<Proposta> propostasDisponiveis =
                repository.findByStatus(PropostaStatus.ELEGIVEL);
		
		
	while(propostasDisponiveis.size()>0) {
		Proposta proposta = propostasDisponiveis.get(0);
		CartaoResponse  cartaoResponse = cartaoClient.associaCartao(proposta.toCartaoRequest());
		proposta.toCartaoResponse(cartaoResponse.toModel(proposta));
		
		em.merge(proposta);
		propostasDisponiveis.remove(0);
		
		
		
	}
	System.out.println("Fim do Scheduled");
}
	@GetMapping("/{id}")
	public ResponseEntity<?> acompanhar(@PathVariable("id") Long id) {
		Optional<Proposta> proposta = repository.findById(id);

		if (proposta.isPresent()) {
			
			
			return ResponseEntity.ok(proposta);
		}

		logger.warn("Proposta não encontrada");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
		
} 
