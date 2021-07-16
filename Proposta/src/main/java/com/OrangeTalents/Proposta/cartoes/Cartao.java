package com.OrangeTalents.Proposta.cartoes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

import com.OrangeTalents.Proposta.aviso.Aviso;
import com.OrangeTalents.Proposta.aviso.AvisoRequest;
import com.OrangeTalents.Proposta.bloqueio.Bloqueio;
import com.OrangeTalents.Proposta.bloqueio.BloqueioRequest;
import com.OrangeTalents.Proposta.carteira.Carteira;
import com.OrangeTalents.Proposta.carteira.CarteiraRequest;
import com.OrangeTalents.Proposta.parcela.Parcela;
import com.OrangeTalents.Proposta.parcela.ParcelaRequest;
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
	
	@NotNull
    @Valid
    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private Set<Bloqueio> bloqueios = new HashSet<>();
	
	@NotNull
    @Valid
    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private Set<Aviso> avisos = new HashSet<>();
	
    @NotNull
    @Valid
    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private Set<Carteira> carteiras = new HashSet<>();
    
    @NotNull
    @Valid
    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private Set<Parcela> parcelas = new HashSet<>();
    
    @NotNull
    private BigDecimal limite;
    @Valid
    @OneToOne(mappedBy = "cartao", cascade = CascadeType.MERGE)
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

    
    
    public Cartao( @NotNull LocalDateTime emitidoEm,
            @NotBlank String titular,
            @NotNull @Valid Collection<BloqueioRequest> bloqueios,
            @NotNull @Valid Collection<AvisoRequest> avisos,
            @NotNull @Valid Collection<CarteiraRequest> carteiras,
            @NotNull @Valid Collection<ParcelaRequest> parcelas,
            @NotNull BigDecimal limite,
            @Valid RenegociacaoRequest renegociacao,
            @NotNull @Valid VencimentoRequest vencimento,
            @NotNull @Valid Proposta proposta) {
    	
    	this.emitidoEm = emitidoEm;
    	this.titular = titular;
    	Set<Bloqueio> novosBloqueios = bloqueios.stream().map(bloqueio -> bloqueio.toModel(this))
          .collect(Collectors.toSet());
    	this.bloqueios.addAll(novosBloqueios);
    	Set<Aviso> novosAvisos = avisos.stream().map(aviso -> aviso.toModel(this))
          .collect(Collectors.toSet());
    	this.avisos.addAll(novosAvisos);
    	Set<Carteira> novasCarteiras = carteiras.stream().map(carteira -> carteira.toModel(this))
          .collect(Collectors.toSet());
    	this.carteiras.addAll(novasCarteiras);
    	Set<Parcela> novasParcelas = parcelas.stream().map(parcela -> parcela.toModel(this))
          .collect(Collectors.toSet());
    	this.parcelas.addAll(novasParcelas);
    	this.limite = limite;
    	if (renegociacao == null) {
      this.renegociacao = null;
    	} else {
      this.renegociacao = renegociacao.toModel(this);
    	}
    	this.vencimento = vencimento.toModel(this);
    	this.proposta = proposta;
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

	public Set<Bloqueio> getBloqueios() {
		return bloqueios;
	}

	public Set<Aviso> getAvisos() {
		return avisos;
	}

	public Set<Carteira> getCarteiras() {
		return carteiras;
	}

	public Set<Parcela> getParcelas() {
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
