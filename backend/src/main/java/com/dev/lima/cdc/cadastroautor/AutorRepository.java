package com.dev.lima.cdc.cadastroautor;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.lima.cdc.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {

}
