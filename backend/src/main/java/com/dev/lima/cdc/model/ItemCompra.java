package com.dev.lima.cdc.model;

import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Embeddable
public class ItemCompra {

	@ManyToOne
	@NotNull
	@Valid
	private Livro livro;
	@Positive
	private Integer quantidade;
	@Positive
	@NotNull
	private BigDecimal precoUnitario;
	@Positive
	@NotNull
	private BigDecimal precoTotal;
	@NotBlank
	private String titulo;
	@NotNull
	private Formato formato;
	
	@Deprecated
	public ItemCompra() {
		
	}
	
	public ItemCompra(@NotNull Livro livro, @Positive Integer quantidade, @NotNull BigDecimal precoUnitario, 
			@NotNull BigDecimal precoTotal, @NotBlank String titulo, @NotNull Formato formato) {
		
		this.livro = livro;
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
		this.precoTotal = precoTotal;
		this.titulo = titulo;
		this.formato = formato;
	}
	
	@Override
	public String toString() {
		return "ItemCompra [livro=" + livro + ", quantidade=" + quantidade + ", precoUnitario=" + precoUnitario
				+ ", precoTotal=" + precoTotal + ", titulo=" + titulo + "]";
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public BigDecimal getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(BigDecimal precoTotal) {
		this.precoTotal = precoTotal;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public Formato getFormato() {
		return formato;
	}
	
	public void setFormato(Formato formato) {
		this.formato = formato;
	}
	
}
