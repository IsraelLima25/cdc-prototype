package com.dev.lima.cdc.cadastrocategoria;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import com.dev.lima.cdc.model.Categoria;
import com.dev.lima.cdc.validator.ExistsId;
import com.dev.lima.cdc.validator.UniqueValue;

public class CategoriaForm {
	
	@UniqueValue(domainClass = Categoria.class, fieldName = "descricao")
	@NotBlank
	@Size(max = 35)
	private String descricao;
	
	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	@Positive
	@NotNull
	private Long idCategoriaMae;
	
	public CategoriaForm(@NotBlank @Size(max = 35) String descricao, @Positive @NotNull Long idCategoriaMae) {
		this.descricao = descricao;
		this.idCategoriaMae = idCategoriaMae;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getIdCategoriaMae() {
		return idCategoriaMae;
	}

	public void setIdCategoriaMae(Long idCategoriaMae) {
		this.idCategoriaMae = idCategoriaMae;
	}

	public Categoria toCategoria(CategoriaRepository repositoryCategoria) {
		
		Optional<Categoria> possivelCategoriaMae = repositoryCategoria.findById(idCategoriaMae);

		Assert.isTrue(possivelCategoriaMae.isPresent(), "Não existe categoria para este id");
		
		return new Categoria(descricao,possivelCategoriaMae.get());
	}

	@Override
	public String toString() {
		return descricao;
	}

}
