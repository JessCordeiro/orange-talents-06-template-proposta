package com.OrangeTalents.Proposta.biometria;

import java.net.URI;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.OrangeTalents.Proposta.cartoes.Cartao;
import com.OrangeTalents.Proposta.validacao.ResourceNotFoundException;





@RestController
@RequestMapping("/biometrias")
public class BiometriaController {
	
	@PersistenceContext
	private EntityManager em;

	@PostMapping("/{idCartao}")
	public ResponseEntity<?> criar(@RequestBody @Valid BiometriaRequest request,
			@PathVariable("idCartao") Long id, UriComponentsBuilder uriBuilder)throws ResourceNotFoundException  {
		
		Cartao cartao = Optional.ofNullable(em.find(Cartao.class, id))
				.orElseThrow(() -> new ResourceNotFoundException(id));
		
		Biometria biometria = request.toModel(id, em);
		
		em.merge(biometria);
		 
		
		URI uri = uriBuilder.path("/biometrias/{id}").buildAndExpand(biometria.getId()).toUri();

		return ResponseEntity.created(uri).body(new BiometriaResponse(biometria));		
	}
	
}
