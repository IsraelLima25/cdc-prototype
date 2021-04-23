package com.dev.lima.cdc.site.finalizandocompraparte2;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.lima.cdc.config.Cookies;
import com.dev.lima.cdc.model.Pagamento;
import com.dev.lima.cdc.site.carrinho.Carrinho;
import com.dev.lima.cdc.site.carrinho.CarrinhoService;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {
	
	@Autowired
	private CarrinhoService carrinhoService;
	@Autowired
	private Cookies cookies;
	@Autowired
	private PagamentoService servicePagamento;
	@Autowired 
	private EmailService serviceEmail;
	
	@Transactional
	@PostMapping(value = "/pagar")
	public ResponseEntity<DadosPagamentoForm> processarPagamento(@RequestBody @Valid DadosPagamentoForm request,
			@CookieValue("carrinho") String jsonCarrinho, HttpServletResponse response) {
		
		Pagamento pagamentoModel = request.toPagamento();
		servicePagamento.processarPagamento(pagamentoModel, request.getIdCompra());
		
		Carrinho carrinho = carrinhoService.cria(Optional.of(jsonCarrinho));
		carrinho.esvaziarCarrinho();
		cookies.writeAsJson("carrinho", carrinho, response);
		
		serviceEmail.enviarEmail();
		
		return ResponseEntity.ok(request);
	}


}
