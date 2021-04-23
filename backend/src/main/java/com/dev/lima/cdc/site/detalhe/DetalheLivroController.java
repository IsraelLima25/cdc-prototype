package com.dev.lima.cdc.site.detalhe;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.lima.cdc.cadastrocategoria.CategoriaForm;
import com.dev.lima.cdc.cadastrolivro.LivroHomeResponse;
import com.dev.lima.cdc.cadastrolivro.LivroRepository;
import com.dev.lima.cdc.config.Cookies;
import com.dev.lima.cdc.model.Categoria;
import com.dev.lima.cdc.model.Formato;
import com.dev.lima.cdc.model.Livro;
import com.dev.lima.cdc.site.carrinho.Carrinho;
import com.dev.lima.cdc.site.carrinho.CarrinhoService;

@RestController
@RequestMapping("/api/detalhe")
@Validated
public class DetalheLivroController {

	@Autowired
	private LivroRepository repositoryLivro;
	@Autowired
	private LivroRepository livroRepository;
	@Autowired
	private CarrinhoService carrinhoService;
	@Autowired
	private Cookies cookies;

	@GetMapping(value = "/categoria/{id}")
	public ResponseEntity<Map<CategoriaForm, List<LivroHomeResponse>>> filtrarLivroPorCategoria(@Positive @PathVariable("id") Long id) {

		Optional<List<Livro>> possivelLivro = repositoryLivro.findByCategoria(id);

		Assert.isTrue(possivelLivro.isPresent(), "Nenhum livro encontrado com o id passado");

		if (possivelLivro.isPresent()) {

			Map<Categoria, List<Livro>> livrosAgrupadosPorCategoria = possivelLivro.get().stream()
					.collect(Collectors.groupingBy(Livro::getCategoria));
			
			Map<CategoriaForm, List<LivroHomeResponse>> livrosAgrupadosPorCategoriaConvert = Livro
					.toMapLivroPorCategoriaResponse(livrosAgrupadosPorCategoria);

			return ResponseEntity.ok(livrosAgrupadosPorCategoriaConvert);
		}
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping(value = "/carrinho/adicionar/{isbnLivro}")
	public ResponseEntity<Carrinho> adicionarLivroCarrinho(@PathVariable @NotBlank String isbnLivro,
			@CookieValue("carrinho") Optional<String> jsonCarrinho,			
			@RequestParam("formato") @NotNull Formato formato,
			HttpServletResponse response) {

		Carrinho carrinho = carrinhoService.cria(jsonCarrinho);
		Optional<Livro> possivelLivro = livroRepository.findById(isbnLivro);

		Assert.isTrue(possivelLivro.isPresent(), "Nenhum livro encontrado com o isbn passado");

		carrinho = carrinhoService.adiciona(possivelLivro.get(), formato);

		cookies.writeAsJson("carrinho", carrinho, response);		

		return ResponseEntity.ok(carrinho);
	}

}
