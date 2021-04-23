package com.dev.lima.cdc.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.dev.lima.cdc.cadastrolivro.TipoLivroForm;

@Entity
@Table(name = "tbl_tipo_livro")
public class TipoLivro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Formato formato;
	@NotNull
	@PositiveOrZero
	private BigDecimal preco;

	@Deprecated
	public TipoLivro() {
	}

	public TipoLivro(@NotNull Formato formato, @NotNull @PositiveOrZero BigDecimal preco) {
		this.formato = formato;
		this.preco = preco;
	}

	public Long getId() {
		return id;
	}

	public Formato getFormato() {
		return formato;
	}

	public void setFormato(Formato formato) {
		this.formato = formato;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public TipoLivroForm toTipoLivroForm() {
		return new TipoLivroForm(id, formato, preco);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((formato == null) ? 0 : formato.hashCode());
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
		TipoLivro other = (TipoLivro) obj;
		if (formato != other.formato)
			return false;
		return true;
	}

}
