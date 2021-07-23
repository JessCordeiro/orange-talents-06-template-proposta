package com.OrangeTalents.Proposta.aviso;

import java.time.LocalDateTime;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import com.OrangeTalents.Proposta.cartoes.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;



public class AvisoRequest {

	@NotBlank
	private String destino;
	
	@NotNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonFormat(pattern = "yyy-MM-dd@HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "America/Sao_Paulo")
	private LocalDateTime validoAte;
	
	
	
	
	
	@Deprecated
	public AvisoRequest() {
	
	}



	public AvisoRequest(@NotBlank String destino, @NotNull LocalDateTime validoAte) {
		super();
		this.destino = destino;
		this.validoAte = validoAte;
	}





	public LocalDateTime getValidoAte() {
		return validoAte;
	}



	public String getDestino() {
		return destino;
	}

	


	
	
}
