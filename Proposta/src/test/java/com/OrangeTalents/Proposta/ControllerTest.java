package com.OrangeTalents.Proposta;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;


import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.OrangeTalents.Proposta.proposta.Proposta;
import com.OrangeTalents.Proposta.proposta.PropostaRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@AutoConfigureTestEntityManager
@Transactional
public class ControllerTest {
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private EntityManager manager;
	
	@Autowired
	ObjectMapper objectMapper;
	
	private Proposta proposta;
	
	private BigDecimal salario = new BigDecimal(5000);
	
	//@BeforeEach
	//public void persistir() {
		
	//	proposta = new Proposta("27199567081","jessica@jessica.com", "Jessica","jisijisjd", salario);
	//	manager.persist(proposta);
	//}
	
	public static MvcResult performPost(MockMvc mockMvc, String url, int status, ObjectMapper objectMapper, Object request) throws Exception{
		return mockMvc.perform(MockMvcRequestBuilders
				.post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
				.andExpect(MockMvcResultMatchers.status().is(status)).andReturn();
	}
	
	
	@Test
	public void fazerProposta() throws Exception{
		
		PropostaRequest request = new PropostaRequest("27199567081","jessica@jessica.com", "Jessica","jisijisjd", salario);
		
		performPost(mock, "/propostas", 201, objectMapper, proposta);
		
		List<Proposta> proposta = manager.createQuery("select * from Produto",  Proposta.class).getResultList();
		
		assertTrue(proposta.size()!=0);
		
		
		
	}
	
}
