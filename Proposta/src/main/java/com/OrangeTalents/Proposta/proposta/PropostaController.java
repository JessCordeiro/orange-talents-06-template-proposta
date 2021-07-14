package com.OrangeTalents.Proposta.proposta;



import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/proposta")
public class PropostaController {
	
	
	@Autowired
	PropostaRepository repository;
	
	@PostMapping
	@Transactional
	ResponseEntity<?> cadastrar(@Valid @RequestBody PropostaRequest request, UriComponentsBuilder uriComponentsBuilder){
		if(repository.existsByDocumento(request.getDocumento())) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Já existe documento cadastrado");
		
	}else {

		Proposta proposta = request.toModel();
		repository.save(proposta);
		return ResponseEntity.created(uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri()).build();

	}
		}
} 
