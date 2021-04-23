package com.dev.lima.cdc.cadastrocategoria;

import java.util.List;
import java.util.stream.Collectors;

import com.dev.lima.cdc.model.Categoria;

public class CategoriaAnsiosaResponse {

	private CategoriaMaeForm categoriaMae;
	private List<CategoriaFormResponse> categoriasFilho;

	public CategoriaAnsiosaResponse(Categoria categoriaMae, List<Categoria> categoriasFilhos) {

		this.categoriaMae = categoriaMae.toCategoriaMaeForm();
		this.categoriasFilho = categoriasFilhos.stream().map(categoria -> toCategoriaFormResponse(categoria.getId(),
				categoria.getDescricao(), categoria.getCategoriaMae().getId())).collect(Collectors.toList());
	}

	public CategoriaFormResponse toCategoriaFormResponse(Long id, String descricao, Long idCategoriaMae) {
		return new CategoriaFormResponse(id, descricao, idCategoriaMae);
	}

	public CategoriaMaeForm getCategoriaMae() {
		return categoriaMae;
	}

	public void setCategoriaMae(CategoriaMaeForm categoriaMae) {
		this.categoriaMae = categoriaMae;
	}

	public List<CategoriaFormResponse> getCategoriasFilho() {
		return categoriasFilho;
	}

	public void setCategoriasFilho(List<CategoriaFormResponse> categoriasFilho) {
		this.categoriasFilho = categoriasFilho;
	}

}
