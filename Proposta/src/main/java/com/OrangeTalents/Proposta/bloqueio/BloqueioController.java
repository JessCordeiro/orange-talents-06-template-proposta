package com.OrangeTalents.Proposta.bloqueio;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.OrangeTalents.Proposta.cartoes.Cartao;
import com.OrangeTalents.Proposta.cartoes.CartaoResponse;
import com.OrangeTalents.Proposta.cartoes.CartoesClient;
import com.OrangeTalents.Proposta.validacao.ResourceNotFoundException;

import feign.FeignException;

@RestController
@RequestMapping("/bloqueios")
public class BloqueioController {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	CartoesClient cartoesClient;

	@PostMapping("{id}")
	@Transactional
	public ResponseEntity<?> criarBloqueio(@PathVariable @Valid Long id, HttpServletRequest requestInfos,
			@RequestHeader("user-agent") String agent)throws ResourceNotFoundException {
		
//		Cartao cartao = Optional.ofNullable(em.find(Cartao.class, id)).orElseThrow(() -> 
//		new ResponseStatusException(HttpStatus.NOT_FOUND, "Id não encontrado"));
		
		Cartao cartao = Optional.ofNullable(em.find(Cartao.class, id))
				.orElseThrow(() -> new ResourceNotFoundException(id));
		
		try {
			
			cartoesClient.bloqueio(cartao.getId(), new BloqueioRequest("Proposta"));
			
			CartaoResponse respostaCartao = cartoesClient.buscaCartaoPorId(cartao.getId());
			
			Bloqueio bloqueio = respostaCartao.getUltimoBloqueio();
			
			bloqueio.setInformacoesDeRequest(requestInfos.getRemoteAddr(), agent, cartao);
			
			cartao.addBloqueio(bloqueio);
			
			em.merge(cartao);
			
			
			
			
		}catch(FeignException f) {
			return ResponseEntity.unprocessableEntity().build();
		
	}
		return ResponseEntity.ok().build();
}
}
