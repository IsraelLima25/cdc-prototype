package com.dev.lima.cdc.cadastrolivro;

import java.util.Optional;

import com.dev.lima.cdc.model.Livro;

public class CampoTituloValidator extends LivroCampoUnicoValidator {

	public CampoTituloValidator(LivroRepository repository) {
		super(repository);
	}

	@Override
	public Optional<Livro> buscarLivroPorCampo(LivroForm livroForm) {
		Optional<Livro> livroFind = repository.findByTitulo(livroForm.getTitulo());
		return livroFind;
	}

	@Override
	protected String getNomeCampoInvalido() {
		return "titulo";
	}

}
