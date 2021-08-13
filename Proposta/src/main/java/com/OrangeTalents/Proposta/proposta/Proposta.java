package com.OrangeTalents.Proposta.proposta;

import java.math.BigDecimal;
import java.util.Base64;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.OrangeTalents.Proposta.cartoes.Cartao;
import com.OrangeTalents.Proposta.cartoes.CartaoRequest;
import com.OrangeTalents.Proposta.status.StatusProposta;
import com.OrangeTalents.Proposta.status.StatusRequest;



@Entity
public class Proposta {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true, nullable=false)
	private String documento;
	
	@Column(unique=true, nullable=false)
	private String email;
	
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=false)
	private String endereco;
	
	@Column(nullable=false)
	private BigDecimal salario;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PropostaStatus status = PropostaStatus.NAO_ELEGIVEL;

	@OneToOne(cascade = CascadeType.MERGE)
	private Cartao cartao;

	

	@Deprecated
	public Proposta() {
		
	}

	

	public Proposta(String documento, String email, String nome, String endereco, BigDecimal salario  
			) {
		super();
		this.documento = documento;
		documento = Base64.getEncoder().encodeToString(documento.getBytes());
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
		this.status = status;
		this.cartao = cartao;
		
	}



	public void setStatus(StatusProposta status) {
			this.status = status.getPropostaStatus();
		
			
	}
	


	public Long getId() {
		return id;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}
	
	
	public CartaoRequest toCartaoRequest() {

		return new CartaoRequest(documento, nome, id);

	}

	public void toCartaoResponse(Cartao cartao) {

		this.cartao = cartao;

	}
	

	public PropostaStatus getStatus() {
		return status;
	}

	public Cartao getCartao() {
		return cartao;
	}

	

	
	
}
