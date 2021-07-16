package com.OrangeTalents.Proposta.status;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "status", url = "http://localhost:9999/api/solicitacao")
public interface StatusGateway {

    @PostMapping("/api/solicitacao")
    public StatusResponse status(@RequestBody StatusRequest request); 
}






	
    

