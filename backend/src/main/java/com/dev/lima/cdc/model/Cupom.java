package com.dev.lima.cdc.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.dev.lima.cdc.site.finalizandocompraparte1.NovoCupomForm;

@Entity
@Table(name = "tbl_cupom")
public class Cupom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String codigo;
	@NotNull
	@Future
	@NotNull
	private LocalDateTime expiracao;
	@Positive
	@DecimalMax("0.50")
	private BigDecimal desconto;

	@Deprecated
	public Cupom() {

	}

	public Cupom(@NotBlank String codigo, @NotNull @Future @NotNull LocalDateTime expiracao,
			@Positive @DecimalMax("0.50") BigDecimal desconto) {
		this.codigo = codigo;
		this.expiracao = expiracao;
		this.desconto = desconto;
	}
	
	public BigDecimal getDesconto() {
		return desconto;
	}

	public boolean taValido() {
		return expiracao.compareTo(LocalDateTime.now()) >= 0;
	}
	
	public NovoCupomForm toFormCupom() {
		return new NovoCupomForm(codigo, expiracao, desconto);
	}
	
}
