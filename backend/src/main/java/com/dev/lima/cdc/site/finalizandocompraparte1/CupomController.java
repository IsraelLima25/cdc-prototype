package com.dev.lima.cdc.site.finalizandocompraparte1;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.lima.cdc.model.Cupom;

@RestController
@RequestMapping("/api/cupom")
public class CupomController {
	
	@Autowired
	private CupomRepository repositoryCupom;

	@PostMapping
	@Transactional
	public ResponseEntity<NovoCupomForm> cria(@RequestBody @Valid NovoCupomForm request) {
		
		Cupom cupomModel = request.novoCupom();
		repositoryCupom.save(cupomModel);
		
		return ResponseEntity.ok(cupomModel.toFormCupom());
	}
	
	@PutMapping(value = "/{idCupom}/aplicar")
	public ResponseEntity<NovoCupomForm> aplicarCupomDesconto(@PathVariable("idCupom") String idCupom,
			@CookieValue("carrinho") String jsonCarrinho) {
		
		Optional<Cupom> possivelCupom = repositoryCupom.findByCodigo(idCupom);
		Assert.isTrue(possivelCupom.isPresent(), "Insira um código de desconto ou cartão-presente válido");
		Assert.isTrue(possivelCupom.get().taValido(), "Este cupom está vencido");
	
		return ResponseEntity.ok(possivelCupom.get().toFormCupom()); 
	}
}
