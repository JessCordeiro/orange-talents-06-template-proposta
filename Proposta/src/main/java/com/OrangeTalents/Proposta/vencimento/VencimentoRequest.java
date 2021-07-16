package com.OrangeTalents.Proposta.vencimento;

import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.OrangeTalents.Proposta.cartoes.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;

public class VencimentoRequest {
	
	
	private Integer dia;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonFormat(pattern = "yyy-MM-dd@HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "America/Sao_Paulo")
	private LocalDateTime dataDeCriacao;

	public VencimentoRequest(Integer dia, LocalDateTime dataDeCriacao) {
		super();
		this.dia = dia;
		this.dataDeCriacao = dataDeCriacao;
	}

	public Integer getDia() {
		return dia;
	}

	public LocalDateTime getDataDeCriacao() {
		return dataDeCriacao;
	}
	
	public @NotNull @Valid Vencimento toModel(Cartao cartao) {
		return new Vencimento(this.dia, this.dataDeCriacao, cartao);
	}
	
}
