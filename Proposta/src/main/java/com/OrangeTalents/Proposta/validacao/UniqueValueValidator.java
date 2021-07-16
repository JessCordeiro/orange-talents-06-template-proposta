package com.OrangeTalents.Proposta.validacao;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;





public class UniqueValueValidator implements ConstraintValidator<Documento, Object>{

	@PersistenceContext
	EntityManager manager;
	private String domainAttribute;
	private Class<?> klass;
	

	
	 @Override
	    public void initialize(Documento uniqueValue) {
	    domainAttribute = uniqueValue.fieldName().toLowerCase();
	    klass = uniqueValue.domainClass();
	    }
	    
	
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Query query = manager.createQuery("select 1 from "+klass.getName()+" where "+domainAttribute+"=:value");
		
		
		query.setParameter("value", Hashing.sha256().hashString((CharSequence) value, StandardCharsets.UTF_8).toString());
		
		
		
		try {
			query.getSingleResult();
			throw new UniqueValueException(Collections.singletonMap("documento", "já está cadastrado"));
		}catch (NoResultException e ) {
			return true;
		}
		
	
	}
	
}



	