package com.OrangeTalents.Proposta.carteira;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.OrangeTalents.Proposta.cartoes.Cartao;
import com.OrangeTalents.Proposta.cartoes.CartaoResponse;
import com.OrangeTalents.Proposta.cartoes.CartoesClient;

import feign.FeignException;

@RestController
@RequestMapping("/carteiras")
public class CarteiraController {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	CartoesClient cartoesClient;
	
	
	Gateway gateway;
	
	@PostMapping("{id}")
	public ResponseEntity<?> criarCarteira(@Valid @PathVariable Long id, CarteiraRequest request, HttpServletRequest requestInfos,
			@RequestHeader("user-agent") String agent, UriComponentsBuilder uriComponentsBuilder){
		
		Cartao cartao = Optional.ofNullable(em.find(Cartao.class, id)).orElseThrow(() -> 
		new ResponseStatusException(HttpStatus.NOT_FOUND, "Id não encontrado"));
		
		try {
			
			cartoesClient.carteira(cartao.getId(), request);
			
			CartaoResponse respostaCartao = cartoesClient.buscaCartaoPorId(cartao.getId());
			
			Carteira carteira = (Carteira) respostaCartao.getCarteiras();
			
			if(!carteira.getEmissor().isEmpty()) {
				
				return ResponseEntity.unprocessableEntity().build();
				
			}
			
			carteira.setInformacoesDeRequest(requestInfos.getRemoteAddr(), agent, cartao);
			
			cartao.addCarteira(gateway, carteira);
			
			em.merge(cartao);
			return ResponseEntity.created(uriComponentsBuilder.path("/carteiras/{id}").buildAndExpand(carteira.getId()).toUri()).build();
			
			
		}catch(FeignException f) {
			return ResponseEntity.badRequest().build();
			
		}
		
		//return ResponseEntity.ok().build();
	}
		}
