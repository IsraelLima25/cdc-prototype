package com.dev.lima.cdc.cadastroautor;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dev.lima.cdc.model.Autor;

@RestController
@RequestMapping("/api/autores")
public class CrudAutoresController {

	@Autowired
	private AutorRepository repositoryAutor;
	
	@Transactional
	@PostMapping
	public ResponseEntity<AutorForm> cadastrarAutor(@RequestBody @Valid AutorForm request) {
		
		Autor autorModel = request.toAutor();
		repositoryAutor.save(autorModel);
		AutorForm autorFormResponse = autorModel.toAutorForm();
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(autorModel.getId()).toUri();
		return ResponseEntity.created(uri).body(autorFormResponse);
	}
}
