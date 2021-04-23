package com.dev.lima.cdc.model;

public enum Formato {

	EBOOK("E-book"), IMPRESSO("Impresso"), EBOOKEIMPRESSO("E-book + Impresso");

	private String descricao;

	private Formato(String descricao) {
		this.descricao = descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
