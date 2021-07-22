package com.OrangeTalents.Proposta.bloqueio;

import java.time.LocalDateTime;

import com.OrangeTalents.Proposta.cartoes.Cartao;



public class BloqueioResponse {

	private String id;

	private String bloqueadoEm;

	private String sistemaResponsavel;

	private boolean ativo;

	private Cartao cartao;

	public Bloqueio toModel() {
		return new Bloqueio(id, LocalDateTime.parse(bloqueadoEm), sistemaResponsavel, ativo, cartao);
	}

	public String getId() {
		return id;
	}

	public String getBloqueadoEm() {
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
