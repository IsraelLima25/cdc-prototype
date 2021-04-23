package com.dev.lima.cdc.cadastrolivro;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dev.lima.cdc.cadastroautor.AutorRepository;
import com.dev.lima.cdc.cadastrocategoria.CategoriaRepository;
import com.dev.lima.cdc.model.Livro;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/api/livros")
@Validated
public class CrudLivrosController {

	@Autowired
	private LivroRepository repositoryLivro;
	@Autowired
	private AutorRepository repositoryAutor;
	@Autowired
	private CategoriaRepository repositoryCategoria;
	@Autowired
	private TipoLivroRepository repositoryTipoLivro;
	@Autowired
	private Uploader uploader;

	@InitBinder
	public void init(WebDataBinder dataBinder) {
		dataBinder.addValidators(new CampoTituloValidator(repositoryLivro), new CampoIsbnValidator(repositoryLivro));
	}

	@Transactional
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LivroFormResponse> cadastrarLivro(@Valid LivroForm request)
			throws JsonMappingException, JsonProcessingException {

		Livro livroModel = request.toLivro(repositoryAutor, uploader, repositoryCategoria, repositoryTipoLivro);
		repositoryLivro.save(livroModel);

		LivroFormResponse livroFormResponse = livroModel.toLivroFormResponse();

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(livroFormResponse.getIsbn()).toUri();

		return ResponseEntity.created(uri).body(livroFormResponse);
	}
	
	@GetMapping(value = "/{isbn}")
	public ResponseEntity<LivroFormResponse> buscarLivroPorIsbn(@Valid @NotBlank @PathVariable("isbn") String isbn) {
		
		Optional<Livro> possivelLivro = repositoryLivro.findById(isbn);		
		
		if(possivelLivro.isEmpty()) {
			throw new IllegalArgumentException("Nenhum livro encontrado com este isbn");
		}
		
		LivroFormResponse livroEncontrado = possivelLivro.get().toLivroFormResponse();
		
		return ResponseEntity.ok(livroEncontrado);
	}
	
}
