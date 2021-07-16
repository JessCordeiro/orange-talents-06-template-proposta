package com.OrangeTalents.Proposta.carteira;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.OrangeTalents.Proposta.cartoes.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;

public class CarteiraRequest {

	
	@NotBlank
	@Email
	private String email;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonFormat(pattern = "yyy-MM-dd@HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "America/Sao_Paulo")
	private LocalDateTime associadaEm;
	
	@NotBlank
	private String emissor;

	public CarteiraRequest(@NotBlank @Email String email, LocalDateTime associadaEm, @NotBlank String emissor) {
		super();
		this.email = email;
		this.associadaEm = associadaEm;
		this.emissor = emissor;
	}

	public String getEmail() {
		return email;
	}

	public LocalDateTime getAssociadaEm() {
		return associadaEm;
	}

	public String getEmissor() {
		return emissor;
	}

	public Carteira toModel(Cartao cartao) {
		return new Carteira(this.email, this.associadaEm, this.emissor, cartao);
	}
	
	
}
