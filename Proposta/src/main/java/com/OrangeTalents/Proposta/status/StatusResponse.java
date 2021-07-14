package com.OrangeTalents.Proposta.status;


import com.OrangeTalents.Proposta.proposta.PropostaStatus;

public class StatusResponse {
	
	private String documento;
	
	private String nome;
	
	private StatusProposta resultadoSolicitacao;
	
	private String status;
	
	private Long idProposta;
	
	

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public StatusProposta getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}

	public String getStatus() {
		return status;
	}

	public Long getIdProposta() {
		return idProposta;
	}
	
	
	public PropostaStatus toModel() {
		if (StatusProposta.COM_RESTRICAO.equals(status)) {
			return PropostaStatus.NAO_ELEGIVEL;
		}return PropostaStatus.ELEGIVEL;
	}
}
