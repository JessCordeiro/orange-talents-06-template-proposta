package com.OrangeTalents.Proposta.carteira;

import java.time.LocalDateTime;


import com.OrangeTalents.Proposta.cartoes.Cartao;


public class CarteiraResponse {
	
	
	private Long id;
	
	private String email;
	
	private String carteira;

	private String associadaEm;

	private String emissor;

    private Cartao cartao;

    

	public Carteira toModel() {
		return new Carteira(id, email, carteira, LocalDateTime.parse(associadaEm),emissor,cartao);
	}
    
	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getCarteira() {
		return carteira;
	}

	

	public String getAssociadaEm() {
		return associadaEm;
	}

	public String getEmissor() {
		return emissor;
	}

	public Cartao getCartao() {
		return cartao;
	}
    
    
	
}
