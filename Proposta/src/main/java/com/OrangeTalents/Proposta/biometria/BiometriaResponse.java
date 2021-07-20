package com.OrangeTalents.Proposta.biometria;



public class BiometriaResponse {
	
	private Long id;

	private String digital;

	public BiometriaResponse(Long id, String digital) {
		super();
		this.id = id;
		this.digital = digital;
	}

	public BiometriaResponse(Biometria biometria) {
		this.id = biometria.getId();
		this.digital = biometria.getBiometria();
	}

	public Long getId() {
		return id;
	}

	public String getDigital() {
		return digital;
	}
	
	
}
