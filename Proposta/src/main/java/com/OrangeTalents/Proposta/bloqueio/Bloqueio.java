package com.OrangeTalents.Proposta.bloqueio;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.OrangeTalents.Proposta.cartoes.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Bloqueio {

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonFormat(pattern = "yyy-MM-dd@HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "America/Sao_Paulo")
	private LocalDateTime bloqueadoEm;
	
	@NotBlank
	private String sistemaResponsavel;
	
	@NotNull
	private boolean ativo;
	
	@NotNull
    @Valid
    @ManyToOne
    private Cartao cartao;

	@Deprecated
	public Bloqueio() {
		
	}

	public Bloqueio(LocalDateTime bloqueadoEm, @NotBlank String sistemaResponsavel, @NotNull boolean ativo,
			@NotNull @Valid Cartao cartao) {
		super();
		this.bloqueadoEm = bloqueadoEm;
		this.sistemaResponsavel = sistemaResponsavel;
		this.ativo = ativo;
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getBloqueadoEm() {
		return bloqueadoEm;
	}

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public Cartao getCartao() {
		return cartao;
	}
	
	
	
	
}
