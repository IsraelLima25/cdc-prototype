package com.dev.lima.cdc.cadastroautor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.dev.lima.cdc.model.Autor;

public class AutorForm {
	
	private Long id;
	@NotBlank
	@Size(max = 30)
	private String nome;
	@Size(max = 500)
	@NotBlank
	private String biografia;

	public AutorForm(Long id, @NotBlank @Size(max = 30) String nome, @Size(max = 500) @NotBlank String biografia) {
		this.id = id;
		this.nome = nome;
		this.biografia = biografia;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Long getId() {
		return id;
	}

	public Autor toAutor() {
		return new Autor(nome, biografia);
	}

}
