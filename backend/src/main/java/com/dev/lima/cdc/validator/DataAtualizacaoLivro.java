package com.dev.lima.cdc.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = DataAtualizacaoLivroValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface DataAtualizacaoLivro {
	
	String message() default "{com.dev.lima.data-atualizacao-livro-invalida}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
}
