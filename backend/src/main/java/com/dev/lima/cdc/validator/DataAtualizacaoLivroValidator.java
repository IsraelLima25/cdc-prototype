package com.dev.lima.cdc.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.dev.lima.cdc.cadastrolivro.LivroForm;

public class DataAtualizacaoLivroValidator implements ConstraintValidator<DataAtualizacaoLivro, LivroForm> {

	@Override
	public boolean isValid(LivroForm livro, ConstraintValidatorContext context) {
		
		if(livro.getDataAtualizacao() == null || livro.getDataPublicacao() == null) {
			return false;
		}

		if (livro.getDataAtualizacao().isBefore(livro.getDataPublicacao())) {
			return false;
		}

		return true;

	}

}
