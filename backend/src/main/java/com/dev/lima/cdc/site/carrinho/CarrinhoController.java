package com.dev.lima.cdc.site.carrinho;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.lima.cdc.cadastrolivro.LivroRepository;
import com.dev.lima.cdc.config.Cookies;
import com.dev.lima.cdc.model.Formato;
import com.dev.lima.cdc.model.Livro;

@RestController
@RequestMapping("/api/carrinho")
@Validated
public class CarrinhoController {

	@Autowired
	private CarrinhoService carrinhoService;
	@Autowired
	private LivroRepository livroRepository;
	@Autowired
	private Cookies cookies;

	@PutMapping(value = "/{isbnLivro}/atualizar")
	public ResponseEntity<Carrinho> atualizarItemCarrinho(@PathVariable("isbnLivro") @NotBlank String isbnLivro,
			@RequestParam @Positive Integer novaQuantidade, @CookieValue("carrinho") String jsonCarrinho,
			@RequestParam @NotNull Formato formato, HttpServletResponse response) {
		
		Carrinho carrinho = carrinhoService.cria(Optional.of(jsonCarrinho));
		Optional<Livro> possivelLivro = livroRepository.findById(isbnLivro);
		
		Assert.isTrue(possivelLivro.isPresent(), "Nenhum livro encontrado com o isbn passado");
		
		carrinhoService.atualiza(possivelLivro.get(),novaQuantidade,formato);
		cookies.writeAsJson("carrinho",carrinho,response);
		return ResponseEntity.ok(carrinho);
	}

	@DeleteMapping(value = "/{isbnLivro}/remover")
	public ResponseEntity<Carrinho> removerItemCarrinho(@PathVariable("isbnLivro") @NotBlank String isbn,
			@RequestParam @NotNull Formato formato, HttpServletResponse response,
			@CookieValue("carrinho") String jsonCarrinho) {

		Carrinho carrinho = carrinhoService.cria(Optional.of(jsonCarrinho));

		Optional<Livro> possivelLivro = livroRepository.findById(isbn);

		Assert.isTrue(possivelLivro.isPresent(), "Nenhum livro encontrado com o isbn passado");

		carrinhoService.remover(possivelLivro.get(), formato);
		cookies.writeAsJson("carrinho", carrinho, response);

		return ResponseEntity.ok(carrinho);
	}
	
	@GetMapping
	public ResponseEntity<Carrinho> carregarCarrinho(@CookieValue("carrinho") Optional<String> jsonCarrinho) {
		
		Carrinho carrinho = carrinhoService
				.cria(jsonCarrinho);
		
		return ResponseEntity.ok(carrinho);		
	}	
}
