package com.dev.lima.cdc.site.finalizandocompraparte2;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.dev.lima.cdc.model.Compra;
import com.dev.lima.cdc.model.Pagamento;
import com.dev.lima.cdc.model.TipoPagamento;
import com.dev.lima.cdc.validator.ExistsId;

public class DadosPagamentoForm {
	
	@ExistsId(domainClass = Compra.class, fieldName = "id")
	@Positive
	private Long idCompra;
	@NotNull
	private TipoPagamento tipo;
	private Long idCupom;
	@NotBlank
	private String observacao;
	
	public TipoPagamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoPagamento tipo) {
		this.tipo = tipo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public Long getIdCompra() {
		return idCompra;
	}
	
	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
	}

	public Pagamento toPagamento() {
	
		return new Pagamento(tipo,observacao,idCupom);
	}
	
	public Long getIdCupom() {
		return idCupom;
	}
	
	public void setIdCupom(Long idCupom) {
		this.idCupom = idCupom;
	}
}
