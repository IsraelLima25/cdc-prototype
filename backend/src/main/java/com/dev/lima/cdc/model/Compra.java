package com.dev.lima.cdc.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.dev.lima.cdc.site.finalizandocompraparte1.CompraForm;

@Entity
@Table(name = "tbl_compra")
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Email
	@NotBlank
	private String email;
	private String nome;
	@NotBlank
	private String sobreNome;
	@NotBlank
	private String documento;
	@NotBlank
	private String telefone;
	@Valid
	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	private Endereco endereco;
	@ManyToOne
	private Cupom cupom;
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<ItemCompra> itens = new HashSet<>();
	private BigDecimal valorTotal;
	@Enumerated(EnumType.STRING)
	private EstadoPagamento estado = EstadoPagamento.PENDENTE; 
	
	@Deprecated
	public Compra() {
		
	}

	public Compra(@Email @NotBlank String email, String nome, @NotBlank String sobreNome,
			@NotBlank String documento, @NotBlank String telefone, @Valid @NotNull Endereco endereco,
			@NotNull Set<ItemCompra> itens) {
		this.email = email;
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.documento = documento;
		this.telefone = telefone;
		this.endereco = endereco;
		this.itens = itens;
		this.valorTotal = itens.stream().map(item -> item.getPrecoTotal())
					.reduce(BigDecimal.ZERO, (atual,proximo) -> atual.add(proximo));
	}
	
	public void setCupom(Cupom cupom) {
		this.cupom = cupom;
	}
	
	public Cupom getCupom() {
		return cupom;
	}
	
	public BigDecimal getValorTotal() {		
		return valorTotal;
	}
	
	public EstadoPagamento getEstado() {
		return estado;
	}
	
	public void confimarPagamentoCompra() {
		this.estado = EstadoPagamento.PAGO;
	}
	
	public CompraForm toCompraForm() {
		return new CompraForm(id, email, nome, sobreNome, documento, telefone, endereco, cupom, itens, valorTotal);
	}
	
	@Override
	public String toString() {
		return "Compra [id=" + id + ", email=" + email + ", nome=" + nome + ", sobreNome=" + sobreNome + ", documento="
				+ documento + ", telefone=" + telefone + ", endereco=" + endereco + ", cupom=" + cupom + ", itens="
				+ itens + "]";
	}
	
	public void aplicarCupom() {
		if(cupom != null && cupom.taValido()) {
			valorTotal = valorTotal.subtract(cupom.getDesconto());		
		}
	}
}
