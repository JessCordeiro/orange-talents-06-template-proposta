package com.OrangeTalents.Proposta.renegociacao;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.OrangeTalents.Proposta.cartoes.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class Renegociacao {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer quantidade;
	
	private Number valor;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonFormat(pattern = "yyy-MM-dd@HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "America/Sao_Paulo")
	private LocalDateTime dataDeCriacao;
	
	@NotNull
    @Valid
    @ManyToOne
    private Cartao cartao;

	@Deprecated
	public Renegociacao() {
		
	}

	
	
	
	public Renegociacao(Integer quantidade, Number valor, LocalDateTime dataDeCriacao, @NotNull @Valid Cartao cartao) {
		super();
		this.quantidade = quantidade;
		this.valor = valor;
		this.dataDeCriacao = dataDeCriacao;
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

	public LocalDateTime getDataDeCriacao() {
		return dataDeCriacao;
	}
	
	
	
	
}
