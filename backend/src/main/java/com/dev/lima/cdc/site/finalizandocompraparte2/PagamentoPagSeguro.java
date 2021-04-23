package com.dev.lima.cdc.site.finalizandocompraparte2;

public class PagamentoPagSeguro implements PagamentoProcess {

	@Override
	public String processar() {
		System.out.println("Comunicando com servidor PagSeguro");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		return "Pagamento pagseguro confirmado!";
	}

}
