package com.dev.lima.cdc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.dev.lima.cdc.cadastroautor.AutorForm;

@Entity
@Table(name = "tbl_autor")
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Size(max = 30)
	private String nome;
	@Size(max = 500)
	@NotBlank
	private String biografia;
	
	@Deprecated
	public Autor() {
		
	}

	public Autor(@Size(max = 30) @NotBlank String nome, @Size(max = 500) @NotBlank String biografia) {
		this.nome = nome;
		this.biografia = biografia;
	}

	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
	
	public AutorForm toAutorForm() {
		return new AutorForm(id,nome, biografia);
	}
}
