package com.dev.lima.cdc.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import com.dev.lima.cdc.cadastrocategoria.CategoriaForm;
import com.dev.lima.cdc.cadastrolivro.LivroFormResponse;
import com.dev.lima.cdc.cadastrolivro.LivroHomeResponse;
import com.dev.lima.cdc.cadastrolivro.TipoLivroForm;

@Entity
@Table(name = "tbl_livro")
public class Livro {

	@Id
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
	@NotBlank
	@URL
	private String linkCapaLivro;
	@ManyToOne
	@NotNull
	private Autor autor;
	@ManyToOne
	@NotNull
	private Categoria categoria;
	@NotNull
	@PastOrPresent
	private LocalDate dataPublicacao;
	@NotNull
	@PastOrPresent
	private LocalDate dataAtualizacao;
	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "livro_isbn")
	private List<TipoLivro> tipos = new ArrayList<>();

	@Deprecated
	public Livro() {

	}

	public Livro(@NotBlank @Size(max = 13) String isbn, @NotBlank @Size(max = 30) String titulo,
			@NotBlank @Size(max = 50) String subTitulo, @NotBlank @Size(max = 500) String conteudo,
			@NotBlank @Size(max = 500) String sumarioMarkDown, @NotNull @Positive Integer numeroPaginas,
			@NotBlank @URL String linkCapaLivro, @NotNull Autor autor, @NotNull Categoria categoria,
			@PastOrPresent @NotNull LocalDate dataPublicacao, @NotNull @PastOrPresent LocalDate dataAtualizacao,
			@NotNull List<TipoLivro> tipos) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.subTitulo = subTitulo;
		this.conteudo = conteudo;
		this.sumarioMarkDown = sumarioMarkDown;
		this.numeroPaginas = numeroPaginas;
		this.linkCapaLivro = linkCapaLivro;
		this.autor = autor;
		this.categoria = categoria;
		this.dataPublicacao = dataPublicacao;
		this.dataAtualizacao = dataAtualizacao;
		this.tipos = tipos;
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

	public String getLinkCapaLivro() {
		return linkCapaLivro;
	}

	public void setLinkCapaLivro(String linkCapaLivro) {
		this.linkCapaLivro = linkCapaLivro;
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

	public List<TipoLivro> getTipos() {
		return tipos;
	}

	public void setTipos(List<TipoLivro> tipos) {
		this.tipos = tipos;
	}

	public LivroFormResponse toLivroFormResponse() {

		List<TipoLivroForm> tiposLivroForm = tipos.stream().map(TipoLivro::toTipoLivroForm)
				.collect(Collectors.toList());

		return new LivroFormResponse(isbn, titulo, subTitulo, conteudo, sumarioMarkDown, numeroPaginas, autor,
				categoria, dataPublicacao, dataAtualizacao, tiposLivroForm, linkCapaLivro);
	}

	public LivroHomeResponse toLivroHomeResponse() {
		
		return new LivroHomeResponse(isbn, linkCapaLivro, titulo, categoria.toCategoriaForm());
	}

	public BigDecimal getPrecoPorTipo(Formato formato) {
		return tipos.stream().filter(tipo -> tipo.getFormato()
				.equals(formato)).findFirst().get().getPreco();
	}

	public static Map<CategoriaForm, List<LivroHomeResponse>> toMapLivroPorCategoriaResponse(
			Map<Categoria, List<Livro>> livrosAgrupadosPorCategoria) {
		
		Map<CategoriaForm, List<LivroHomeResponse>> mapConverter = new HashMap<>();
		
		livrosAgrupadosPorCategoria.forEach((key,value) -> {
			
			CategoriaForm keyCategoriaForm = key.toCategoriaForm();
			List<LivroHomeResponse> keyLivrosForm = value.stream().map(Livro::toLivroHomeResponse)
					.collect(Collectors.toList());
			
			mapConverter.put(keyCategoriaForm, keyLivrosForm);
			
		});
		
		return mapConverter;
		
	}
}
