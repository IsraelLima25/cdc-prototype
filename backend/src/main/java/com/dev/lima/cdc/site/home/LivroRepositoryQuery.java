package com.dev.lima.cdc.site.home;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dev.lima.cdc.cadastrolivro.LivroFormResponse;

public interface LivroRepositoryQuery {

	Page<LivroFormResponse> filtrar(String value, Pageable pageable);
}
