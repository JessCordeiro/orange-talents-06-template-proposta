package com.OrangeTalents.Proposta.aviso;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.OrangeTalents.Proposta.cartoes.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Aviso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonFormat(pattern = "yyy-MM-dd@HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "America/Sao_Paulo")
	private LocalDateTime validoAte;
	
	@NotBlank
	private String destino;
	
	@ManyToOne
	@NotNull @Valid
	private Cartao cartao;

	@Deprecated
	public Aviso() {
	
	}

	public Aviso(LocalDateTime validoAte, @NotBlank String destino, @NotNull @Valid Cartao cartao) {
		super();
		this.validoAte = validoAte;
		this.destino = destino;
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getValidoAte() {
		return validoAte;
	}

	public String getDestino() {
		return destino;
	}

	public Cartao getCartao() {
		return cartao;
	}
	
	
	
	
	
}

