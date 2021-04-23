package com.dev.lima.cdc.cadastrocategoria;

public class CategoriaFormResponse {

	private Long id;
	private String descricao;
	private Long idCategoriaMae;

	public CategoriaFormResponse(Long id, String descricao, Long idCategoriaMae) {
		this.id = id;
		this.descricao = descricao;
		this.idCategoriaMae = idCategoriaMae;
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

	public Long getIdCategoriaMae() {
		return idCategoriaMae;
	}

	public void setIdCategoriaMae(Long idCategoriaMae) {
		this.idCategoriaMae = idCategoriaMae;
	}

}
