package com.OrangeTalents.Proposta.proposta;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {UniqueValueValidator.class })
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface Documento {

	String message() default "Precisa ser um CPF ou CNPJ válido.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
    
    String fieldName();
    
    Class<?> domainClass();
	
}
