package com.dev.lima.cdc.cadastrolivro;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.lima.cdc.model.TipoLivro;

public interface TipoLivroRepository extends JpaRepository<TipoLivro, Long> {

}
