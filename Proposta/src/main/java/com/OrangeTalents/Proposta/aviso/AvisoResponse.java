package com.OrangeTalents.Proposta.aviso;

import java.time.LocalDateTime;

import com.OrangeTalents.Proposta.cartoes.Cartao;


public class AvisoResponse {
	
	private Long id;
	
	private String destino;

	private String validoAte;
	
	private String instanteAviso;
	
	private String ipCliente;
	
	private String userAgent;

	private Cartao cartao;
	
	
	public Aviso toModel() {
		return new Aviso(id, destino, LocalDateTime.parse(validoAte), LocalDateTime.parse(instanteAviso),ipCliente,userAgent,cartao);
	}

	public Long getId() {
		return id;
	}

	public String getDestino() {
		return destino;
	}


	public String getIpCliente() {
		return ipCliente;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public String getValidoAte() {
		return validoAte;
	}

	public String getInstanteAviso() {
		return instanteAviso;
	}
	
	
	

}
