package com.dev.lima.cdc.cadastrolivro;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.dev.lima.cdc.cadastrocategoria.CategoriaForm;

public class LivroHomeResponse {
	
	@NotBlank
	private String isbn;
	@NotBlank
	private String linkCapa;
	@NotBlank
	private String titulo;	

	public LivroHomeResponse(@NotBlank String isbn, @NotBlank String linkCapa, @NotBlank String titulo,
			@NotNull CategoriaForm categoriaForm) {
		
		this.isbn = isbn;
		this.linkCapa = linkCapa;
		this.titulo = titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getLinkCapa() {
		return linkCapa;
	}

	public void setLinkCapa(String linkCapa) {
		this.linkCapa = linkCapa;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public static Page<LivroHomeResponse> pageImpl(List<LivroHomeResponse> livroResponse,
			PageRequest pageSortedBydataPublicacaoDesc, int size) {

		return new PageImpl<>(livroResponse, pageSortedBydataPublicacaoDesc, livroResponse.size());
	}

}
