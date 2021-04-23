package com.dev.lima.cdc.site;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.lima.cdc.cadastrolivro.LivroFormResponse;
import com.dev.lima.cdc.cadastrolivro.LivroHomeResponse;
import com.dev.lima.cdc.cadastrolivro.LivroRepository;
import com.dev.lima.cdc.model.Livro;

@RestController
@RequestMapping("/api/home")
public class HomeController {

	@Autowired
	private LivroRepository repositoryLivro;

	/**
	 * @param campoFiltro - define por qual campos os livros serão buscados na ordem decrescente.
	 * Os valor possíveis são por {dataPublicacao} ou {dataAtualizacao}
	 * @param pagina
	 * @param linhasPorPagina
	 * @return
	 */
	
	@GetMapping(value = "/ultimosLivros")
	public ResponseEntity<Page<LivroHomeResponse>> ultimosLancamentos(
			@Positive @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
			@Positive @RequestParam(value = "linhasPorPagina", defaultValue = "4") Integer linhasPorPagina,
			@NotBlank @RequestParam(value = "campoFiltro") String campoFiltro) {

		PageRequest pageSortedBydataPublicacaoDesc = PageRequest.of(pagina, linhasPorPagina,
				Sort.by(campoFiltro).descending());
		
		List<LivroHomeResponse> livroResponse = repositoryLivro.findAll(pageSortedBydataPublicacaoDesc)
				.getContent().stream().map(Livro::toLivroHomeResponse)
				.collect(Collectors.toList());
		
		Page<LivroHomeResponse> pageImpl = LivroHomeResponse.
				pageImpl(livroResponse,pageSortedBydataPublicacaoDesc,livroResponse.size());

		return ResponseEntity.ok(pageImpl);
	}

	@GetMapping(value = "/pesquisarLivro")
	public ResponseEntity<Page<LivroFormResponse>> pesquisarPorAutorTituloEConteudo(
			@NotBlank @RequestParam String valor,
			@Positive @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
			@Positive @RequestParam(value = "linhasPorPagina", defaultValue = "10") Integer linhasPorPagina) {

		PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina);

		Page<LivroFormResponse> paginaLivros = repositoryLivro.filtrar(valor, pageRequest);
		return ResponseEntity.ok(paginaLivros);
	}
}
