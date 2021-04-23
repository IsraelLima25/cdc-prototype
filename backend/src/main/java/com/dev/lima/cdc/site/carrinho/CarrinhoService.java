package com.dev.lima.cdc.site.carrinho;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.dev.lima.cdc.cadastrolivro.LivroRepository;
import com.dev.lima.cdc.model.Formato;
import com.dev.lima.cdc.model.ItemCompra;
import com.dev.lima.cdc.model.Livro;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CarrinhoService {
	
	private Carrinho carrinho;

	public Carrinho cria(Optional<String> jsonCarrinho) {

		carrinho = jsonCarrinho.map(json -> {
			try {
				return new ObjectMapper().readValue(json, Carrinho.class);
			} catch (JsonProcessingException e) {
				throw new RuntimeException(e);
			}
		}).orElse(new Carrinho());
		
		return carrinho;
	}

	public Carrinho adiciona(Livro livro, Formato formato) {
		ItemCarrinho novoItem = new ItemCarrinho(livro,formato);
		boolean result = carrinho.getItens().add(novoItem);
		
		if (!result) {
			ItemCarrinho itemExistente = carrinho.getItens().stream().filter(novoItem::equals).findFirst().get();
			itemExistente.incrementa();
		}
		
		return carrinho;
	}

	public void atualiza(Livro livro, Integer novaQuantidade, Formato formato) {
		
		Assert.isTrue(novaQuantidade > 0, "A nova quantidade do item do carrinho deve ser maior que zero!");
		
		Optional<ItemCarrinho> possivelItem = carrinho.getItens().stream().filter(item -> item.getIsbnLivro()
				.equals(livro.getIsbn())).collect(Collectors.toList())
				.stream().filter(item -> item.getFormato().equals(formato)).findFirst();
		
		Assert.isTrue(possivelItem.isPresent(), "Você não deve atualizar um item que não esteja adicionado ao carrinho!!");
		ItemCarrinho itemExistente = possivelItem.get();
		itemExistente.atualizarItem(novaQuantidade);
	}

	public void remover(Livro livro, Formato formato) {
		
		ItemCarrinho itemCarrinho = new ItemCarrinho(livro, formato);
		Optional<ItemCarrinho> posssivelItem = carrinho.getItens().stream()
				.filter(item -> item.equals(itemCarrinho)).findFirst();
		
		Assert.isTrue(posssivelItem.isPresent(), "Não é possível remover um item que não foi adicionado ao carrinho!!");
		
		carrinho.getItens().removeIf(item -> item.equals(itemCarrinho));
	}
	
	public Set<ItemCompra> geraItensCompra(LivroRepository livroRepository) {
		return this.carrinho.getItens().stream().map(itemCarrinho -> {
			return itemCarrinho.novoItemCompra(livroRepository);
		}).collect(Collectors.toSet());
	}
}
