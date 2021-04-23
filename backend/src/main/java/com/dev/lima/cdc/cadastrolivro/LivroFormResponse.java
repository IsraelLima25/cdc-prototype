package com.dev.lima.cdc.cadastrolivro;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import com.dev.lima.cdc.model.Autor;
import com.dev.lima.cdc.model.Categoria;

public class LivroFormResponse {

	@NotBlank
	@Size(max = 13)
	private String isbn;
	@NotBlank
	@Size(max = 30)
	private String titulo;
	@NotBlank
	@Size(max = 50)
	private String subTitulo;
	@NotBlank
	@Size(max = 500)
	private String conteudo;
	@NotBlank
	@Size(max = 500)
	private String sumarioMarkDown;
	@NotNull
	@Positive
	private Integer numeroPaginas;
	@NotNull
	private Autor autor;
	@NotNull
	private Categoria categoria;
	@PastOrPresent
	private LocalDate dataPublicacao;
	@PastOrPresent
	private LocalDate dataAtualizacao;
	@NotNull
	private List<TipoLivroForm> tipos = new ArrayList<>();
	@NotBlank
	@URL
	private String linkCapa;

	public LivroFormResponse(@NotBlank @Size(max = 13) String isbn, @NotBlank @Size(max = 30) String titulo,
			@NotBlank @Size(max = 50) String subTitulo, @NotBlank @Size(max = 500) String conteudo,
			@NotBlank @Size(max = 500) String sumarioMarkDown, @NotNull @Positive Integer numeroPaginas,
			@NotNull Autor autor, @NotNull Categoria categoria, @PastOrPresent LocalDate dataPublicacao,
			@PastOrPresent LocalDate dataAtualizacao, @NotNull List<TipoLivroForm> tipos,
			@NotBlank @URL String linkCapa) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.subTitulo = subTitulo;
		this.conteudo = conteudo;
		this.sumarioMarkDown = sumarioMarkDown;
		this.numeroPaginas = numeroPaginas;
		this.autor = autor;
		this.categoria = categoria;
		this.dataPublicacao = dataPublicacao;
		this.dataAtualizacao = dataAtualizacao;
		this.tipos = tipos;
		this.linkCapa = linkCapa;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSubTitulo() {
		return subTitulo;
	}

	public void setSubTitulo(String subTitulo) {
		this.subTitulo = subTitulo;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public String getSumarioMarkDown() {
		return sumarioMarkDown;
	}

	public void setSumarioMarkDown(String sumarioMarkDown) {
		this.sumarioMarkDown = sumarioMarkDown;
	}

	public Integer getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(Integer numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(LocalDate dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public LocalDate getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(LocalDate dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public List<TipoLivroForm> getTipos() {
		return tipos;
	}

	public void setTipos(List<TipoLivroForm> tipos) {
		this.tipos = tipos;
	}
	
	public String getLinkCapa() {
		return linkCapa;
	}
	
	public void setLinkCapa(String linkCapa) {
		this.linkCapa = linkCapa;
	}

}
