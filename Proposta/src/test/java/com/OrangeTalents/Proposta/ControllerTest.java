package com.OrangeTalents.Proposta;



import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;



public class ControllerTest {
	
	
	
	@Test
	public void fazerProposta() throws Exception{
		
		RestAssured.given()
		.param("documento", "27199567081")
		.param("email", "jessica@jessica.com")
		.param("nome", "Maria")
		.param("endereco", "Assunção")
		.param("salario", 5000)
	
		
		
		//.body("{ \"documento\": \"27199567081\", \"email\": \"jessica@jessica.com\", \"nome\": \"Maria\", \"endereco\": \"Assunção\", \"salario\": 5000	}")
		.contentType(ContentType.JSON)
		.when()
		.post("http://localhost:8080/propostas")
		.then()
		.statusCode(201)
		.log();                      
		
		
	}
	
}
