package com.OrangeTalents.Proposta.cartoes;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.OrangeTalents.Proposta.aviso.AvisoRequest;
import com.OrangeTalents.Proposta.aviso.AvisoResponse;
import com.OrangeTalents.Proposta.bloqueio.BloqueioRequest;
import com.OrangeTalents.Proposta.bloqueio.BloqueioResponse;
import com.OrangeTalents.Proposta.carteira.CarteiraRequest;
import com.OrangeTalents.Proposta.carteira.CarteiraResponse;






@FeignClient(name ="cartoes", url = "http://localhost:8888")
public interface CartoesClient {
	
	@RequestMapping(method = RequestMethod.POST, value = "/api/cartoes")
    CartaoResponse associaCartao(@RequestBody CartaoRequest request);
	
	@PostMapping("/{id}/bloqueios")
	public BloqueioResponse bloqueio(@PathVariable Long id, BloqueioRequest request);
	
	@GetMapping("/{id}")
	public CartaoResponse buscaCartaoPorId(@PathVariable Long id);
	
	@PostMapping("/{id}/avisos")
	public AvisoResponse aviso(@PathVariable Long id, AvisoRequest request);
	
	@PostMapping("/{id}/carteiras")
	public CarteiraResponse carteira(@PathVariable Long id, CarteiraRequest request);

	
}
