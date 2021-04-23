package com.dev.lima.cdc.site.home;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.dev.lima.cdc.cadastrolivro.LivroFormResponse;
import com.dev.lima.cdc.model.Categoria;
import com.dev.lima.cdc.model.Livro;

public class LivroRepositoryQueryImpl implements LivroRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	/**
	 * Query páginavél para filtrar livros, primeiramente pela categoria caso exista.
	 * Caso contrário fazer o filtro por like do titulo, subTitulo, sumario ou autor
	 * @param value
	 */
	
	/* TODO Resolver problema das N+1 consultas */
	
	@Override
	public Page<LivroFormResponse> filtrar(String value, Pageable pageable) {

		TypedQuery<Categoria> typedQueryPossivelCategoria = manager
				.createQuery("SELECT c FROM Categoria c WHERE c.descricao = :possivelCategoria", Categoria.class);
		typedQueryPossivelCategoria.setParameter("possivelCategoria", value);

		List<Categoria> listCategorias = typedQueryPossivelCategoria.getResultList();

		if (!listCategorias.isEmpty()) {

			TypedQuery<Livro> typedQueryLivrosCategorias = manager.createQuery(
					"SELECT DISTINCT l FROM Livro l JOIN l.categoria c LEFT JOIN l.tipos t WHERE l.categoria.descricao = :categoria OR "
					+ "c.categoriaMae.descricao = :categoria", Livro.class);
			typedQueryLivrosCategorias.setParameter("categoria", value);
			
			criarRestricoesDePaginacao(typedQueryLivrosCategorias, pageable);
			
			List<LivroFormResponse> livrosForm = toLivroFormFilter(typedQueryLivrosCategorias);
			
			return new PageImpl<>(livrosForm, pageable,total(value)); 
		}
		
		TypedQuery<Livro> typedQueryLivrosFilterGeral = manager
				.createQuery("SELECT DISTINCT l FROM Livro l JOIN l.autor LEFT JOIN l.tipos t WHERE l.titulo LIKE :value OR "
				+ "l.subTitulo LIKE :value OR l.sumarioMarkDown LIKE :value OR "
				+ "l.autor.nome LIKE :value", Livro.class);
		typedQueryLivrosFilterGeral.setParameter("value", "%"+value+"%");
		
		criarRestricoesDePaginacao(typedQueryLivrosFilterGeral, pageable);
		
		List<LivroFormResponse> livrosForm = toLivroFormFilter(typedQueryLivrosFilterGeral);
		
		return new PageImpl<>(livrosForm, pageable,total(value)); 
	}

	private List<LivroFormResponse> toLivroFormFilter(TypedQuery<Livro> typedQueryLivrosFilterGeral) {
		List<LivroFormResponse> livrosForm = typedQueryLivrosFilterGeral
				.getResultList().stream().map(Livro::toLivroFormResponse)
				.collect(Collectors.toList());
		return livrosForm;
	}
	
	private void criarRestricoesDePaginacao(TypedQuery<Livro> query, Pageable pageable) {
		
		int paginaAtual = pageable.getPageNumber();
		int totalResgistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalResgistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalResgistrosPorPagina);
	}
	
	private Long total(String value) {
		
		TypedQuery<Livro> typedQuery = manager.createQuery("SELECT l FROM Livro l", Livro.class);
		long quantidadeLivros = (long) typedQuery.getResultList().size();
		
		return quantidadeLivros;
	}
}
