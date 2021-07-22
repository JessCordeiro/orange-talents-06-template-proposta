package com.OrangeTalents.Proposta.bloqueio;



import javax.validation.constraints.NotBlank;



public class BloqueioRequest {

	
	@NotBlank
	private String sistemaResponsavel;


	@Deprecated
	public BloqueioRequest() {
	}
	
	public BloqueioRequest(String sistemaResponsavel) {
		this.sistemaResponsavel = sistemaResponsavel;
	}


	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}


	
	
}
