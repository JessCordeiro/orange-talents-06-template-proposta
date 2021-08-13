package com.OrangeTalents.Proposta.status;

import com.OrangeTalents.Proposta.proposta.PropostaStatus;

public enum StatusProposta {
	COM_RESTRICAO(PropostaStatus.NAO_ELEGIVEL),
	SEM_RESTRICAO(PropostaStatus.ELEGIVEL);
	private PropostaStatus propostaStatus;

	private StatusProposta(PropostaStatus propostaStatus) {
		this.propostaStatus = propostaStatus;
	}

	public PropostaStatus getPropostaStatus() {
		return propostaStatus;
	}
	
	
	
	
}
