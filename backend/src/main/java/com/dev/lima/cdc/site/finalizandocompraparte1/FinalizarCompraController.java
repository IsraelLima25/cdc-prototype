package com.dev.lima.cdc.site.finalizandocompraparte1;

import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.lima.cdc.cadastrolivro.LivroRepository;
import com.dev.lima.cdc.config.Cookies;
import com.dev.lima.cdc.model.Compra;
import com.dev.lima.cdc.model.ItemCompra;
import com.dev.lima.cdc.site.carrinho.Carrinho;
import com.dev.lima.cdc.site.carrinho.CarrinhoService;

@RestController
@RequestMapping("/api/compra")
public class FinalizarCompraController {
	
	@Autowired
	private CarrinhoService carrinhoService;
	@Autowired
	private LivroRepository repositoryLivro;
	@Autowired
	private CupomRepository repositoryCupum;
	@Autowired
	private CompraRepository repositoryCompra;
	@Autowired
	private Cookies cookies;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new CupomExistenteENaoExpiradoValidator(repositoryCupum));
	}
	
	@Transactional
	@PostMapping(value = "/finalizar")
	public ResponseEntity<CompraForm> processarCompra(@RequestBody @Valid DadosCompradorForm dadosCompradorForm,
			@CookieValue("carrinho") String jsonCarrinho, HttpServletResponse response) {
		
		Carrinho carrinho = carrinhoService.cria(Optional.of(jsonCarrinho));
		
		Set<ItemCompra> itens = carrinhoService.geraItensCompra(repositoryLivro);
		Compra novaCompra = dadosCompradorForm.novaCompra(itens, repositoryCupum);
		Compra compraSalva = repositoryCompra.save(novaCompra);
		cookies.writeAsJson("carrinho", carrinho, response);
		
		return ResponseEntity.ok(compraSalva.toCompraForm());
	}
}
