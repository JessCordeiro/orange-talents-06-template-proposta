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
	
	@NotBlank
	private String destino;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonFormat(pattern = "yyy-MM-dd@HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "America/Sao_Paulo")
	private LocalDateTime validoAte;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonFormat(pattern = "yyy-MM-dd@HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "America/Sao_Paulo")
	private LocalDateTime instanteAviso;
	
	private String ipCliente;
	
	private String userAgent;
	
	@ManyToOne
	@NotNull @Valid
	private Cartao cartao;

	@Deprecated
	public Aviso() {
	
	}

	

	

	public Aviso(Long id, @NotBlank String destino, LocalDateTime validoAte, LocalDateTime instanteAviso,
			String ipCliente, String userAgent, @NotNull @Valid Cartao cartao) {
		super();
		this.id = id;
		this.destino = destino;
		this.validoAte = validoAte;
		this.instanteAviso = instanteAviso;
		this.ipCliente = ipCliente;
		this.userAgent = userAgent;
		this.cartao = cartao;
	}



	public void setInformacoesDeRequest(String remoteAddr, String agent, Cartao cartao) {

		this.ipCliente = remoteAddr;
		this.userAgent = agent;
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

