package com.OrangeTalents.Proposta.parcela;

import javax.validation.constraints.NotNull;

import com.OrangeTalents.Proposta.cartoes.Cartao;

public class ParcelaRequest {

	

	@NotNull
	private Integer quantidade;
	
	@NotNull
	private Number valor;

	public ParcelaRequest(@NotNull Integer quantidade, @NotNull Number valor) {
		super();
		this.quantidade = quantidade;
		this.valor = valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Number getValor() {
		return valor;
	}

	public void setValor(Number valor) {
		this.valor = valor;
	}

	public Parcela toModel(Cartao cartao) {
		return new Parcela(this.quantidade, this.valor, cartao);
	}
	
	
}
