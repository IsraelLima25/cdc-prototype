package com.dev.lima.cdc.cadastrolivro;

import java.math.BigDecimal;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import com.dev.lima.cdc.model.Formato;

public class TipoLivroForm {

	@Positive
	private Long id;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Formato formato;
	@NotNull
	@PositiveOrZero
	private BigDecimal preco;

	public TipoLivroForm(@Positive Long id, @NotNull Formato formato, @NotNull @PositiveOrZero BigDecimal preco) {
		this.id = id;
		this.formato = formato;
		this.preco = preco;
	}

	public Long getId() {
		return id;
	}

	public Formato getFormato() {
		return formato;
	}

	public BigDecimal getPreco() {
		return preco;
	}

}
