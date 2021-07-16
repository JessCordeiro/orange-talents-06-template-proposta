package com.OrangeTalents.Proposta.carteira;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.OrangeTalents.Proposta.cartoes.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Carteira {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Email
	private String email;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonFormat(pattern = "yyy-MM-dd@HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "America/Sao_Paulo")
	private LocalDateTime associadaEm;
	
	@NotBlank
	private String emissor;
	
	@NotNull
    @Valid
    @ManyToOne
    private Cartao cartao;
	
	@Deprecated
	public Carteira() {
		
	}

	public Carteira(@NotBlank @Email String email, LocalDateTime associadaEm, @NotBlank String emissor,
			@NotNull @Valid Cartao cartao) {
		super();
		this.email = email;
		this.associadaEm = associadaEm;
		this.emissor = emissor;
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
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

	public Cartao getCartao() {
		return cartao;
	}
	
	
	
	

}
