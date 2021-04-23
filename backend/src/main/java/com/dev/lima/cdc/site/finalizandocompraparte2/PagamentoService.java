package com.dev.lima.cdc.site.finalizandocompraparte2;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.dev.lima.cdc.model.Compra;
import com.dev.lima.cdc.model.Pagamento;
import com.dev.lima.cdc.site.finalizandocompraparte1.CompraRepository;
import com.dev.lima.cdc.site.finalizandocompraparte1.CupomRepository;

@Service
public class PagamentoService {

	@Autowired
	private CompraRepository repositoryCompra;
	@Autowired
	private PagamentoRepository repositoryPagamento;
	@Autowired
	private CupomRepository cupomRepository;

	public void processarPagamento(Pagamento pagamento, Long idCompra) {
		
		Optional<Compra> possivelCompra = repositoryCompra.findById(idCompra);
		
		Assert.isTrue(possivelCompra.isPresent(), "Nenhuma compra encontrada com este id");
		
		if(possivelCompra.get().getCupom() != null) {
			possivelCompra.get().setCupom(cupomRepository
					.findByCodigo(pagamento.getIdCupom().toString()).get());			
			possivelCompra.get().aplicarCupom();
		}
		
		PagamentoProcess processarPagamento = pagamento.getTipo().processarPagamento();
		processarPagamento.processar();
		
		pagamento.setCompra(possivelCompra.get());
		repositoryPagamento.save(pagamento);
		possivelCompra.get().confimarPagamentoCompra();
		repositoryCompra.save(possivelCompra.get());
		
	}
}
