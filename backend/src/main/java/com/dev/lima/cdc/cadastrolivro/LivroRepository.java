package com.dev.lima.cdc.cadastrolivro;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dev.lima.cdc.model.Livro;
import com.dev.lima.cdc.site.home.LivroRepositoryQuery;

public interface LivroRepository extends JpaRepository<Livro, String>, LivroRepositoryQuery {

	Optional<Livro> findByTitulo(String titulo);
	
	@Query("SELECT l FROM Livro l JOIN l.categoria cf JOIN cf.categoriaMae cm WHERE cf.id=:idCategoria OR cm.id=:idCategoria")
	Optional<List<Livro>> findByCategoria (@Param("idCategoria") Long idCategoria);	

}
