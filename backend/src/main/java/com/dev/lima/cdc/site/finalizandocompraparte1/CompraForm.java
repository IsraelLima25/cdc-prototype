package com.dev.lima.cdc.site.finalizandocompraparte1;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.dev.lima.cdc.model.Cupom;
import com.dev.lima.cdc.model.Endereco;
import com.dev.lima.cdc.model.ItemCompra;

public class CompraForm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Email
	@NotBlank
	private String email;
	@NotBlank
	private String nome;
	@NotBlank
	private String sobreNome;
	@NotBlank
	private String documento;
	@NotBlank
	private String telefone;
	@Valid
	@NotNull
	private Endereco endereco;
	@ManyToOne
	private Cupom cupom;
	@NotNull
	private Set<ItemCompra> itens = new HashSet<>();
	private BigDecimal valorTotal;
	
	public CompraForm(Long id, @Email @NotBlank String email, @NotBlank String nome, @NotBlank String sobreNome,
			@NotBlank String documento, @NotBlank String telefone, @Valid @NotNull Endereco endereco, Cupom cupom,
			@NotNull Set<ItemCompra> itens, BigDecimal valorTotal) {
		this.id = id;
		this.email = email;
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.documento = documento;
		this.telefone = telefone;
		this.endereco = endereco;
		this.cupom = cupom;
		this.itens = itens;
		this.valorTotal = valorTotal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Cupom getCupom() {
		return cupom;
	}

	public void setCupom(Cupom cupom) {
		this.cupom = cupom;
	}

	public Set<ItemCompra> getItens() {
		return itens;
	}

	public void setItens(Set<ItemCompra> itens) {
		this.itens = itens;
	}
	
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
}
