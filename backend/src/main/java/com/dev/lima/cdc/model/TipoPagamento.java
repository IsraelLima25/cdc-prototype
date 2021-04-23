package com.dev.lima.cdc.model;

import com.dev.lima.cdc.site.finalizandocompraparte2.PagamentoPagSeguro;
import com.dev.lima.cdc.site.finalizandocompraparte2.PagamentoPayPal;
import com.dev.lima.cdc.site.finalizandocompraparte2.PagamentoProcess;

public enum TipoPagamento {

	PAYPAL {
		@Override
		public PagamentoProcess processarPagamento() {
			return new PagamentoPayPal();
		}
	}, PAGSEGURO {
		@Override
		public PagamentoProcess processarPagamento() {
			
			return new PagamentoPagSeguro();
		}
	};
	
	public abstract PagamentoProcess processarPagamento();
}
