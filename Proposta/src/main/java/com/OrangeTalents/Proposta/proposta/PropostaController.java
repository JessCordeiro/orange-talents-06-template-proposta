package com.OrangeTalents.Proposta.proposta;




import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


import com.OrangeTalents.Proposta.cartoes.CartaoResponse;
import com.OrangeTalents.Proposta.cartoes.CartoesClient;

import com.OrangeTalents.Proposta.validacao.Logs;
import com.OrangeTalents.Proposta.validacao.ResourceNotFoundException;



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
	public ResponseEntity<?> cadastrar(@Valid @RequestBody PropostaRequest request, 
			UriComponentsBuilder uriComponentsBuilder){
		
		
		if(repository.existsByDocumento(request.getDocumento())) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		
	}

		Proposta proposta = request.toModel();
		repository.save(proposta);
		
		
		return ResponseEntity.created(uriComponentsBuilder.path("/propostas/{id}")
				.buildAndExpand(proposta.getId()).toUri()).build();
		
		
	}

		
	@Transactional
	@Scheduled(fixedDelayString = "${periodicidade.executa-operacao}")
	public void associaCartao(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page,limit, Sort.by(sortDirection,"PropostaStatus.ELEGIVEL"));
		
		Page<Proposta> propostasDisponiveis = repository.findAll(pageable);
	

		propostasDisponiveis.forEach(proposta -> {
		CartaoResponse  cartaoResponse = cartaoClient.associaCartao(proposta.toCartaoRequest());
		proposta.toCartaoResponse(cartaoResponse.toModel(proposta));
		em.merge(proposta);
		});
}
	
	

	
	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<?> acompanhar(@PathVariable("id") Long id) throws ResourceNotFoundException {
		Optional<Proposta> proposta = Optional.ofNullable(repository.findById(id)
				.orElseThrow(() ->new ResourceNotFoundException(id)));
		
		return ResponseEntity.ok(proposta);
	}
	
	
	
		
} 
