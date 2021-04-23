package com.dev.lima.cdc.cadastrocategoria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dev.lima.cdc.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
	@Query("SELECT c FROM Categoria c WHERE c.categoriaMae = null")
	List<Categoria> findByCategoriasMae();
	
	@Query("SELECT categoria FROM Categoria categoria JOIN categoria.categoriaMae maeCategoria WHERE maeCategoria.id=:idCategoria ORDER BY categoria.descricao ASC")
	List<Categoria> findCategoriasByMae(@Param("idCategoria") Long idCategoria);

}
