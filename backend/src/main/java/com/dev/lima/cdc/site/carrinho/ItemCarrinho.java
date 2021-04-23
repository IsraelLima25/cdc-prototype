package com.dev.lima.cdc.site.carrinho;

import java.math.BigDecimal;

import com.dev.lima.cdc.cadastrolivro.LivroRepository;
import com.dev.lima.cdc.model.Formato;
import com.dev.lima.cdc.model.ItemCompra;
import com.dev.lima.cdc.model.Livro;

public class ItemCarrinho {

	private String isbnLivro;
	private String linkCapa;
	private String titulo;
	private String subTitulo;
	private BigDecimal precoUnitario;
	private Integer quantidade = 1;
	private Formato formato;
	private BigDecimal precoTotal = BigDecimal.ZERO;

	@Deprecated
	public ItemCarrinho() {
	}

	public ItemCarrinho(Livro livro, Formato formato) {
		this.isbnLivro = livro.getIsbn();
		this.linkCapa = livro.getLinkCapaLivro();
		this.titulo = livro.getTitulo();
		this.subTitulo = livro.getSubTitulo();
		this.formato = formato;
		this.precoUnitario = livro.getPrecoPorTipo(formato);
	}

	public String getIdLivro() {
		return isbnLivro;
	}

	public void setIdLivro(String idLivro) {
		this.isbnLivro = idLivro;
	}

	public String getLinkCapa() {
		return linkCapa;
	}

	public void setLinkCapa(String linkCapa) {
		this.linkCapa = linkCapa;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Formato getFormato() {
		return formato;
	}

	public void setFormato(Formato formato) {
		this.formato = formato;
	}

	public void incrementa() {
		this.quantidade++;
	}

	public String getIsbnLivro() {
		return isbnLivro;
	}

	public void setIsbnLivro(String isbnLivro) {
		this.isbnLivro = isbnLivro;
	}

	public BigDecimal getPrecoTotal() {
		return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
	}

	public String getSubTitulo() {
		return subTitulo;
	}

	public void setSubTitulo(String subTitulo) {
		this.subTitulo = subTitulo;
	}

	public void atualizarItem(Integer novaQuantidade) {
		this.precoTotal = precoUnitario.multiply(BigDecimal.valueOf(novaQuantidade));
		this.quantidade = novaQuantidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((formato == null) ? 0 : formato.hashCode());
		result = prime * result + ((isbnLivro == null) ? 0 : isbnLivro.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemCarrinho other = (ItemCarrinho) obj;
		if (formato != other.formato)
			return false;
		if (isbnLivro == null) {
			if (other.isbnLivro != null)
				return false;
		} else if (!isbnLivro.equals(other.isbnLivro))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemCarrinho [isbnLivro=" + isbnLivro + ", linkCapa=" + linkCapa + ", titulo=" + titulo
				+ ", precoUnitario=" + precoUnitario + ", quantidade=" + quantidade + ", formato=" + formato
				+ ", precoTotal=" + precoTotal + "]";
	}

	public ItemCompra novoItemCompra(LivroRepository livroRepository) {

		return new ItemCompra(livroRepository.findById(isbnLivro).get(), this.quantidade, this.precoUnitario,
				this.precoTotal, this.titulo, formato);
	}

}
