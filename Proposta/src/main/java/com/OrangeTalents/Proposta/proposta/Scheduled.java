package com.OrangeTalents.Proposta.proposta;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.OrangeTalents.Proposta.cartoes.CartaoResponse;
import com.OrangeTalents.Proposta.cartoes.CartoesClient;

@Component
public class Scheduled {

	
	@Autowired
	PropostaRepository repository;
	
	@Autowired
	CartoesClient cartaoClient;
	
	@PersistenceContext
	private EntityManager em;
	

	
	
	@Transactional
	@Scheduled(fixedDelayString = "${periodicidade.executa-operacao}")
	public void associaCartao() {
		
		List<Proposta> propostasDisponiveis =
                repository.findByStatus(PropostaStatus.ELEGIVEL);
		
		propostasDisponiveis.forEach(proposta -> {
			CartaoResponse  cartaoResponse = cartaoClient.associaCartao(proposta.toCartaoRequest());
		
		proposta.toCartaoResponse(cartaoResponse.toModel(proposta));
		
		em.merge(proposta);
		});
}
}
