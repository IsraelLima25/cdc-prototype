package com.dev.lima.cdc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_pagamento")
public class Pagamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private TipoPagamento tipo;
	@NotBlank
	private String observacao;
	private Long idCupom;
	@OneToOne
	private Compra compra;

	public Pagamento(@NotNull TipoPagamento tipo, @NotBlank String observacao, Long idCupom) {
		this.tipo = tipo;
		this.observacao = observacao;
		this.idCupom = idCupom;
	}

	public Long getId() {
		return id;
	}

	public TipoPagamento getTipo() {
		return tipo;
	}

	public String getObservacao() {
		return observacao;
	}
	
	public Compra getCompra() {
		return compra;
	}
	
	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	
	public Long getIdCupom() {
		return idCupom;
	}
	
	public void setIdCupom(Long idCupom) {
		this.idCupom = idCupom;
	}

}
