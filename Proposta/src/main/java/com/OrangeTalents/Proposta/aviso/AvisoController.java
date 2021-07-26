package com.OrangeTalents.Proposta.aviso;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.OrangeTalents.Proposta.cartoes.Cartao;
import com.OrangeTalents.Proposta.cartoes.CartaoResponse;
import com.OrangeTalents.Proposta.cartoes.CartoesClient;

import feign.FeignException;

@RestController
@RequestMapping("/avisos")
public class AvisoController {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	CartoesClient cartoesClient;
	
	@PostMapping("{id}")
	@Transactional
	public ResponseEntity<?> criarAviso(@PathVariable @Valid @RequestBody Long id,  AvisoRequest request, HttpServletRequest requestInfos,
			@RequestHeader("user-agent") String agent ){
		
		Cartao cartao = Optional.ofNullable(em.find(Cartao.class, id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id não encontrado"));
		
		try {
			
			cartoesClient.aviso(cartao.getId(), request);
			
			CartaoResponse respostaCartao = cartoesClient.buscaCartaoPorId(cartao.getId());		
			
			Aviso aviso = (Aviso) respostaCartao.getAvisos();
			aviso.setInformacoesDeRequest(requestInfos.getRemoteAddr(), agent, cartao);
			cartao.addAviso(aviso);
			
			em.merge(cartao);
			
		}catch(FeignException f) {
			return ResponseEntity.unprocessableEntity().build();
		
	}
		return ResponseEntity.ok().build();
}
	}