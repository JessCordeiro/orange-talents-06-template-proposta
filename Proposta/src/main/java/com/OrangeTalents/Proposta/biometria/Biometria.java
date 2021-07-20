package com.OrangeTalents.Proposta.biometria;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.OrangeTalents.Proposta.cartoes.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
public class Biometria {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String biometria;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonFormat(pattern = "yyy-MM-dd@HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "America/Sao_Paulo")
	private LocalDateTime data;
	
	@ManyToOne
	private Cartao cartao;

	@Deprecated
	public Biometria() {
		
	}

	public Biometria(String biometria, Cartao cartao) {
		super();
		this.biometria = biometria;
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}

	public String getBiometria() {
		return biometria;
	}

	public LocalDateTime getData() {
		return data;
	}

	public Cartao getCartao() {
		return cartao;
	}
	
	
	
	
	
}
