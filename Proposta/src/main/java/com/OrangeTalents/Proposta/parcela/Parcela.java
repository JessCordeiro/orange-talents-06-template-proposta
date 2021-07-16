package com.OrangeTalents.Proposta.parcela;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.OrangeTalents.Proposta.cartoes.Cartao;

@Entity
public class Parcela {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private Integer quantidade;
	
	@NotNull
	private Number valor;
	
	@NotNull
    @Valid
    @ManyToOne
    private Cartao cartao;
	
	@Deprecated
	public Parcela() {
		
	}

	public Parcela(@NotNull Integer quantidade, @NotNull Number valor, @NotNull @Valid Cartao cartao) {
		super();
		this.quantidade = quantidade;
		this.valor = valor;
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Number getValor() {
		return valor;
	}

	public Cartao getCartao() {
		return cartao;
	}

	
	
	
	
}
