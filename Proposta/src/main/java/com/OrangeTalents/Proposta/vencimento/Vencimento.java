package com.OrangeTalents.Proposta.vencimento;

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
public class Vencimento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer dia;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonFormat(pattern = "yyy-MM-dd@HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "America/Sao_Paulo")
	private LocalDateTime dataDeCriacao;
	
	@NotNull
    @Valid
    @ManyToOne
    private Cartao cartao;

	@Deprecated
	public Vencimento() {
		
	}

	
	
	public Vencimento(Integer dia, LocalDateTime dataDeCriacao, @NotNull @Valid Cartao cartao) {
		super();
		this.dia = dia;
		this.dataDeCriacao = dataDeCriacao;
		this.cartao = cartao;
	}



	public Long getId() {
		return id;
	}

	public Integer getDia() {
		return dia;
	}

	public LocalDateTime getDataDeCriacao() {
		return dataDeCriacao;
	}

	
	
	
	
	
	
	
	
}
