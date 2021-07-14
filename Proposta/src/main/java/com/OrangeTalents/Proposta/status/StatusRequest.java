package com.OrangeTalents.Proposta.status;

import com.OrangeTalents.Proposta.proposta.Proposta;

public class StatusRequest {
	
	private String documento;
	
	private String nome;
	
	private Long idProposta;
	

	public StatusRequest(Proposta proposta) {
		super();
		this.documento = proposta.getDocumento();
		this.nome = proposta.getNome();
		this.idProposta = proposta.getId();
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public Long getIdProposta() {
		return idProposta;
	}
	
	
	
}
