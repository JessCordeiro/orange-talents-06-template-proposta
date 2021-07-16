package com.OrangeTalents.Proposta.cartoes;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.OrangeTalents.Proposta.proposta.Proposta;

@FeignClient(name ="cartoes", url = "http://localhost:8888")
public interface CartoesClient {
	
	@RequestMapping(method = RequestMethod.POST, value = "/api/cartoes")
    CartaoResponse associaCartao(@RequestBody CartaoRequest request);

	
}
