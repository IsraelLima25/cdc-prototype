package com.dev.lima.cdc.site.carrinho;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Carrinho {

	private Set<ItemCarrinho> itens = new HashSet<>();
	
	@SuppressWarnings("unused")
	private BigDecimal totalCarrinho;
	
	@SuppressWarnings("unused")
	private Integer quantidadeTotalItens;
	
	public Set<ItemCarrinho> getItens() {
		return itens;
	}
	
	public BigDecimal getTotalCarrinho() {
		
		return itens.stream().map(item -> item.getPrecoTotal()).reduce(
			BigDecimal.ZERO, (atual,proximo) -> atual.add(proximo));
	}
	
	public Integer getQuantidadeTotalItens() {
		return itens.stream().map(item -> item.getQuantidade()).reduce
				(0, (atual, proximo) -> atual + proximo);
	}
	
	@Override
	public String toString() {
		return "Carrinho [itens=" + itens + "]";
	}

	public void esvaziarCarrinho() {
		this.itens.clear();
	}
}
