package com.dev.lima.cdc.site.home;

import javax.validation.constraints.NotBlank;

public class LivroResponse {
	
	@NotBlank
	private String linkCapa;
	@NotBlank
	private String titulo;
	@NotBlank
	private String conteudo;

	public void setLinkCapa(String linkCapa) {
		this.linkCapa = linkCapa;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

}
