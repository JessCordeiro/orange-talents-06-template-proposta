package com.OrangeTalents.Proposta.renegociacao;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;

import com.OrangeTalents.Proposta.cartoes.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;

public class RenegociacaoRequest {

	private Integer quantidade;
	
	private Number valor;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonFormat(pattern = "yyy-MM-dd@HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "America/Sao_Paulo")
	private LocalDateTime dataDeCriacao;

	public RenegociacaoRequest(Integer quantidade, Number valor, LocalDateTime dataDeCriacao) {
		super();
		this.quantidade = quantidade;
		this.valor = valor;
		this.dataDeCriacao = dataDeCriacao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Number getValor() {
		return valor;
	}

	public LocalDateTime getDataDeCriacao() {
		return dataDeCriacao;
	}

	public @Valid Renegociacao toModel(Cartao cartao) {
		return new Renegociacao(this.quantidade, this.valor,this.dataDeCriacao, cartao);
	}
	
	
}
