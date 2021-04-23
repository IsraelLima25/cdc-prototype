package com.dev.lima.cdc.cadastrolivro;

import java.util.Optional;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dev.lima.cdc.model.Livro;

public abstract class LivroCampoUnicoValidator implements Validator {

	protected LivroRepository repository;

	@Override
	public boolean supports(Class<?> clazz) {
		return LivroForm.class.isAssignableFrom(clazz);
	}
	
	public LivroCampoUnicoValidator(LivroRepository repository) {
		this.repository = repository;
	}

	public abstract Optional<Livro> buscarLivroPorCampo(LivroForm livroForm);

	@Override
	public void validate(Object target, Errors errors) {

		LivroForm livroForm = (LivroForm) target;
		Optional<Livro> optionalLivro = buscarLivroPorCampo(livroForm);
		if (optionalLivro.isPresent()) {
			String campoInvalido = getNomeCampoInvalido();
			errors.rejectValue(campoInvalido, null, 
					"Erro grave!! Já existe um livro com o(a) "+campoInvalido+" igual a este.");
		}
	}
	
	protected abstract String getNomeCampoInvalido();
	
}
