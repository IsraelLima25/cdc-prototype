package com.dev.lima.cdc.cadastrocategoria;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dev.lima.cdc.model.Categoria;

@RestController
@RequestMapping("/api/categorias")
public class CrudCategoriasController {

	@Autowired
	private CategoriaRepository repositoryCategoria;

	@Transactional
	@PostMapping
	public ResponseEntity<CategoriaForm> cadastrarCategoria(@RequestBody @Valid CategoriaForm request) {

		Categoria categoriaModel = request.toCategoria(repositoryCategoria);
		repositoryCategoria.save(categoriaModel);
		CategoriaForm categoriaFormResponse = categoriaModel.toCategoriaForm();
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(categoriaModel.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(categoriaFormResponse);
	}
	
	@GetMapping(value = "/todasCategorias")
	public List<CategoriaAnsiosaResponse> listarCategoriasMaeAtrelandoFilhos() {
		
		List<Categoria> categoriasMae = repositoryCategoria.findByCategoriasMae();
		List<CategoriaAnsiosaResponse> categoriasAnsiosas = categoriasMae.stream().map(categoria -> categoria
				.toCategoriaAnsiosaResponse(repositoryCategoria)).collect(Collectors.toList());
				
		return categoriasAnsiosas;
	}

	
	
}
