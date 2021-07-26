package com.OrangeTalents.Proposta.cartoes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

import com.OrangeTalents.Proposta.aviso.Aviso;
import com.OrangeTalents.Proposta.bloqueio.Bloqueio;
import com.OrangeTalents.Proposta.bloqueio.BloqueioResponse;
import com.OrangeTalents.Proposta.carteira.Carteira;
import com.OrangeTalents.Proposta.carteira.CarteiraRequest;
import com.OrangeTalents.Proposta.carteira.Gateway;
import com.OrangeTalents.Proposta.parcela.Parcela;

import com.OrangeTalents.Proposta.proposta.Proposta;
import com.OrangeTalents.Proposta.renegociacao.Renegociacao;
import com.OrangeTalents.Proposta.renegociacao.RenegociacaoRequest;
import com.OrangeTalents.Proposta.vencimento.Vencimento;
import com.OrangeTalents.Proposta.vencimento.VencimentoRequest;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "cartoes")
public class Cartao {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonFormat(pattern = "yyy-MM-dd@HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "America/Sao_Paulo")
	private LocalDateTime emitidoEm;
	
	@NotBlank
    private String titular;
	
	
   
	@OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
	private List<Bloqueio> bloqueios;
	
	@OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
	private List<Aviso> avisos;

	
	@OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
	private List<Carteira> carteiras;

	
	@OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
	private List<Parcela> parcelas;

	@NotNull
	private BigDecimal limite;
	
	@OneToOne(cascade = CascadeType.MERGE)
	private Renegociacao renegociacao;

	
    @NotNull
    @Valid
    @OneToOne(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private Vencimento vencimento;
    
    @NotNull
    @Valid
    @OneToOne
    private Proposta proposta;

    @Deprecated
	public Cartao() {
		
	}

   


	public Cartao(@NotBlank Long id, LocalDateTime emitidoEm, @NotBlank String titular, List<Bloqueio> bloqueios,
			List<Aviso> avisos, List<Carteira> carteiras, List<Parcela> parcelas, @NotNull BigDecimal limite,
			Renegociacao renegociacao, @NotNull @Valid Vencimento vencimento, @NotNull @Valid Proposta proposta) {
		super();
		this.id = id;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.bloqueios = bloqueios;
		this.avisos = avisos;
		this.carteiras = carteiras;
		this.parcelas = parcelas;
		this.limite = limite;
		this.renegociacao = renegociacao;
		this.vencimento = vencimento;
		this.proposta = proposta;
	}
	

	public void addBloqueio(Bloqueio bloqueio) {
		this.bloqueios.add(bloqueio);
	}

	
	public void addAviso(Aviso aviso) {
		this.avisos.add(aviso);
	}
	
	//public void addCarteira(Carteira carteira) {
		//this.carteiras.add(carteira);
	//}
	
	
	public Object addCarteira(Gateway gateway, Carteira carteira) {
		
		if (carteira.getCarteira().equals(gateway)) {
			return this.carteiras.add(carteira);
		}
		return HttpStatus.BAD_REQUEST;
	}
		
	
	

	public Long getId() {
		return id;
	}



	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}



	public String getTitular() {
		return titular;
	}



	public List<Bloqueio> getBloqueios() {
		return bloqueios;
	}


	public List<Aviso> getAvisos() {
		return avisos;
	}



	public List<Carteira> getCarteiras() {
		return carteiras;
	}



	public List<Parcela> getParcelas() {
		return parcelas;
	}



	public BigDecimal getLimite() {
		return limite;
	}



	public Renegociacao getRenegociacao() {
		return renegociacao;
	}


	public Vencimento getVencimento() {
		return vencimento;
	}



	public Proposta getProposta() {
		return proposta;
	}
    
	
	
 
    
    
}
