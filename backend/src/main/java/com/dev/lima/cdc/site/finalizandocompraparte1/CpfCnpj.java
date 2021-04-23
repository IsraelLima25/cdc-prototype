package com.dev.lima.cdc.site.finalizandocompraparte1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = {CpfCnpjValidator.class})
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CpfCnpj {
	
	String message() default "{br.com.dev.lima.cdc.documento-invalido}";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };

}
