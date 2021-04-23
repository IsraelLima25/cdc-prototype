package com.dev.lima.cdc.cadastrolivro;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.dev.lima.cdc.cadastroautor.AutorRepository;
import com.dev.lima.cdc.cadastrocategoria.CategoriaRepository;
import com.dev.lima.cdc.exeception.TipoLivroExeception;
import com.dev.lima.cdc.model.Autor;
import com.dev.lima.cdc.model.Categoria;
import com.dev.lima.cdc.model.Livro;
import com.dev.lima.cdc.model.TipoLivro;
import com.dev.lima.cdc.validator.DataAtualizacaoLivro;
import com.dev.lima.cdc.validator.ExistsId;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@DataAtualizacaoLivro
public class LivroForm {

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
	private MultipartFile capa;
	@Positive
	@ExistsId(domainClass = Autor.class, fieldName = "id")
	private Long idAutor;
	@Positive
	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	private Long idCategoria;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataPublicacao;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataAtualizacao;
	@NotBlank
	private String tiposLivroJSON;
	
	public LivroForm(@NotBlank @Size(max = 13) String isbn, @NotBlank @Size(max = 30) String titulo,
			@NotBlank @Size(max = 50) String subTitulo, @NotBlank @Size(max = 500) String conteudo,
			@NotBlank @Size(max = 500) String sumarioMarkDown, @NotNull @Positive Integer numeroPaginas,
			@NotNull MultipartFile capa, @Positive Long idAutor, @Positive Long idCategoria,
			@DateTimeFormat(pattern = "yyyy-MM-dd") @NotNull LocalDate dataPublicacao, 
			@DateTimeFormat(pattern = "yyyy-MM-dd") @NotNull LocalDate dataAtualizacao,
			@NotBlank String tiposLivroJSON) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.subTitulo = subTitulo;
		this.conteudo = conteudo;
		this.sumarioMarkDown = sumarioMarkDown;
		this.numeroPaginas = numeroPaginas;
		this.capa = capa;
		this.idAutor = idAutor;
		this.idCategoria = idCategoria;
		this.dataPublicacao = dataPublicacao;
		this.dataAtualizacao = dataAtualizacao;
		this.tiposLivroJSON = tiposLivroJSON;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
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

	public Integer getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(Integer numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public void setSumarioMarkDown(String sumarioMarkDown) {
		this.sumarioMarkDown = sumarioMarkDown;
	}

	public MultipartFile getCapa() {
		return capa;
	}

	public void setCapa(MultipartFile capa) {
		this.capa = capa;
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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Long getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(Long idAutor) {
		this.idAutor = idAutor;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	public String getTiposLivroJSON() {
		return tiposLivroJSON;
	}
	
	public void setTiposLivroJSON(String tiposLivroJSON) {
		this.tiposLivroJSON = tiposLivroJSON;
	}

	public Livro toLivro(AutorRepository repositoryAutor, Uploader upload, CategoriaRepository repositoryCategoria,
			TipoLivroRepository repositoryTipoLivro) throws JsonMappingException, JsonProcessingException {

		List<TipoForm> tipos = new ObjectMapper().readValue(tiposLivroJSON, new TypeReference<List<TipoForm>>(){});
		
		if(tipos.size() > 3) {
			throw new TipoLivroExeception("Erro grave! Só são permitidos 3 tipos de livros.");
		}
		
		Autor autorLivro = repositoryAutor.findById(idAutor).get();
		Categoria categoriaLivro = repositoryCategoria.findById(idCategoria).get();
		
		String linkCapa = upload.fazerUpload(capa);
		List<TipoLivro> tiposModel = tipos.stream().map(TipoForm::toTipo).collect(Collectors.toList());
		
		return new Livro(isbn, titulo, subTitulo, conteudo, sumarioMarkDown, numeroPaginas, linkCapa, autorLivro,
				categoriaLivro, dataPublicacao, dataAtualizacao, tiposModel);
	}
}
