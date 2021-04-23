package com.dev.lima.cdc.site.finalizandocompraparte1;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.lima.cdc.model.Cupom;

public interface CupomRepository extends JpaRepository<Cupom, Long> {

	Optional<Cupom> findByCodigo(String codigoCupom);

}
