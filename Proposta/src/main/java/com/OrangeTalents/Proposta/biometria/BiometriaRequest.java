package com.OrangeTalents.Proposta.biometria;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.OrangeTalents.Proposta.cartoes.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;

public class BiometriaRequest {

	@NotBlank
	private String biometria;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonFormat(pattern = "yyy-MM-dd@HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "America/Sao_Paulo")
	private LocalDateTime data;

	public BiometriaRequest(String biometria) {
		super();
		this.biometria = biometria;
		
	}

	public String getBiometria() {
		return biometria;
	}

	public LocalDateTime getData() {
		return data;
	}
	
	public Biometria toModel(Long id, EntityManager em) {
		
		Cartao cartao = Optional.ofNullable(em.find(Cartao.class, id)).orElseThrow(() -> new ResponseStatusException(
				HttpStatus.NOT_FOUND, "O Id deste Cartão não foi encontrado!"));
		
		return new Biometria(this.biometria, cartao );
	}
	
	
	
}
