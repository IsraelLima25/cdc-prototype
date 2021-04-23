package com.dev.lima.cdc.cadastrolivro;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.dev.lima.cdc.model.Formato;
import com.dev.lima.cdc.model.TipoLivro;

public class TipoForm {

	@NotNull
	private Formato formato;
	@NotNull
	@PositiveOrZero
	private BigDecimal preco;

	public TipoLivro toTipo() {
		return new TipoLivro(formato, preco);
	}

	public void setFormato(Formato formato) {
		this.formato = formato;
	}

	public Formato getFormato() {
		return formato;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public BigDecimal getPreco() {
		return preco;
	}

}
