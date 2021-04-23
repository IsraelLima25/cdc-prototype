package com.dev.lima.cdc.cadastrolivro;

import java.util.Optional;

import com.dev.lima.cdc.model.Livro;

public class CampoIsbnValidator extends LivroCampoUnicoValidator {

	public CampoIsbnValidator(LivroRepository repository) {
		super(repository);
	}

	@Override
	public Optional<Livro> buscarLivroPorCampo(LivroForm livroForm) {
		
		Optional<Livro> livroFind = repository.findById(livroForm.getIsbn());
		return livroFind;
	}

	@Override
	protected String getNomeCampoInvalido() {
		return "isbn";
	}
}
