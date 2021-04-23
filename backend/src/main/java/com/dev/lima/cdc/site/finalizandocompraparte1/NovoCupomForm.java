package com.dev.lima.cdc.site.finalizandocompraparte1;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.dev.lima.cdc.model.Cupom;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class NovoCupomForm {

	@NotBlank
	private String codigo;
	@Future
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy kk:mm")
	@NotNull
	private LocalDateTime expiracao;
	@Positive
	@DecimalMax("0.25")
	private BigDecimal desconto;
	
	@Deprecated
	public NovoCupomForm() {
		
	}

	public NovoCupomForm(@NotBlank String codigo, @Future @NotNull LocalDateTime expiracao,
			@Positive @DecimalMax("0.25") BigDecimal desconto) {
		super();
		this.codigo = codigo;
		this.expiracao = expiracao;
		this.desconto = desconto;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public LocalDateTime getExpiracao() {
		return expiracao;
	}

	public void setExpiracao(LocalDateTime expiracao) {
		this.expiracao = expiracao;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public Cupom novoCupom() {
		return new Cupom(codigo, expiracao, desconto);
	}

}
