package com.OrangeTalents.Proposta.carteira;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;



public class CarteiraRequest {

	
	@NotBlank
	@Email
	private String email;
	
	
	@NotBlank
	private Gateway carteira;



	public String getEmail() {
		return email;
	}



	public Gateway getCarteira() {
		return carteira;
	}

	
	
	



	
	
}
