package com.OrangeTalents.Proposta.aviso;

import java.time.LocalDateTime;



import javax.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import com.OrangeTalents.Proposta.cartoes.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;



public class AvisoRequest {

	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonFormat(pattern = "yyy-MM-dd@HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "America/Sao_Paulo")
	private LocalDateTime validoAte;
	
	@NotBlank
	private String destino;
	
	
	@Deprecated
	public AvisoRequest() {
	
	}



	public AvisoRequest(LocalDateTime validoAte, @NotBlank String destino) {
		super();
		this.validoAte = validoAte;
		this.destino = destino;
	}



	public LocalDateTime getValidoAte() {
		return validoAte;
	}



	public String getDestino() {
		return destino;
	}



	public Aviso toModel(Cartao cartao) {
        return new Aviso(validoAte, destino, cartao);
    }

	
}
