package com.OrangeTalents.Proposta.cartoes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.OrangeTalents.Proposta.aviso.Aviso;
import com.OrangeTalents.Proposta.bloqueio.Bloqueio;
import com.OrangeTalents.Proposta.bloqueio.BloqueioRequest;
import com.OrangeTalents.Proposta.bloqueio.BloqueioResponse;
import com.OrangeTalents.Proposta.carteira.Carteira;
import com.OrangeTalents.Proposta.carteira.CarteiraRequest;
import com.OrangeTalents.Proposta.parcela.Parcela;
import com.OrangeTalents.Proposta.proposta.Proposta;
import com.OrangeTalents.Proposta.renegociacao.Renegociacao;
import com.OrangeTalents.Proposta.renegociacao.RenegociacaoRequest;
import com.OrangeTalents.Proposta.vencimento.Vencimento;
import com.OrangeTalents.Proposta.vencimento.VencimentoRequest;




public class CartaoResponse {

	
	@NotBlank
    private Long id;
    @NotNull
    private LocalDateTime emitidoEm;
    @NotBlank
    private String titular;
    @NotNull
    @Valid
    private List<Bloqueio> bloqueios = new ArrayList<>();
    @NotNull
    @Valid
    private List<Aviso> avisos = new ArrayList<>();
    @NotNull
    @Valid
    private List<Carteira> carteiras = new ArrayList<>();
    @NotNull
    @Valid
    private List<Parcela> parcelas = new ArrayList<>();
    @NotNull
    private BigDecimal limite;
    @Valid
    private Renegociacao renegociacao;
    @NotNull
    @Valid
    private Vencimento vencimento;
    
    
    
    
	
	public Long getId() {
		return id;
	}
	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}
	public String getTitular() {
		return titular;
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
	public List<Bloqueio> getBloqueios() {
		return bloqueios;
	}
	
	public Bloqueio getUltimoBloqueio() {
		return bloqueios.get(bloqueios.size() - 1);
	}
	
	public Cartao toModel(Proposta proposta) {

        return new Cartao(this.id,this.emitidoEm, this.titular,
        		this.bloqueios,this.avisos, this.carteiras, this.parcelas,
                this.limite, this.renegociacao, this.vencimento, proposta);
    }
	
	

    
}
