package com.OrangeTalents.Proposta.bloqueio;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.OrangeTalents.Proposta.cartoes.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;

public class BloqueioRequest {

	

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonFormat(pattern = "yyy-MM-dd@HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "America/Sao_Paulo")
	private LocalDateTime bloqueadoEm;
	
	@NotBlank
	private String sistemaResponsavel;
	
	@NotNull
	private boolean ativo;

	public BloqueioRequest(LocalDateTime bloqueadoEm, @NotBlank String sistemaResponsavel, @NotNull boolean ativo) {
		super();
		this.bloqueadoEm = bloqueadoEm;
		this.sistemaResponsavel = sistemaResponsavel;
		this.ativo = ativo;
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

	public Bloqueio toModel(Cartao cartao) {
		return new Bloqueio(this.bloqueadoEm, this.sistemaResponsavel, this.ativo, cartao);
	}
	
	
}
