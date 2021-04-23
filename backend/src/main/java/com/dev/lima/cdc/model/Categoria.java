package com.dev.lima.cdc.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.dev.lima.cdc.cadastrocategoria.CategoriaAnsiosaResponse;
import com.dev.lima.cdc.cadastrocategoria.CategoriaForm;
import com.dev.lima.cdc.cadastrocategoria.CategoriaMaeForm;
import com.dev.lima.cdc.cadastrocategoria.CategoriaRepository;

@Entity
@Table(name = "tbl_categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Size(max = 30)
	@NotBlank
	private String descricao;

	@ManyToOne
	private Categoria categoriaMae;

	@Deprecated
	public Categoria() {

	}

	public Categoria(@Size(max = 30) @NotBlank String descricao, Categoria categoriaMae) {
		super();
		this.descricao = descricao;
		this.categoriaMae = categoriaMae;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Categoria getCategoriaMae() {
		return categoriaMae;
	}
	
	public CategoriaForm toCategoriaForm() {
		return new CategoriaForm(descricao, categoriaMae.getId());
	}
	
	public CategoriaMaeForm toCategoriaMaeForm() {
		return new CategoriaMaeForm(id, descricao);
	}
	
	public CategoriaAnsiosaResponse toCategoriaAnsiosaResponse(CategoriaRepository repositoryCategoria) {
		
		List<Categoria> categoriasFilho = repositoryCategoria
				.findCategoriasByMae(this.getId());
		return new CategoriaAnsiosaResponse(this,categoriasFilho);
		
	}

	@Override
	public String toString() {
		return descricao;
	}
}
