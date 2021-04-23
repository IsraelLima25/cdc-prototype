import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { PageLivro } from "../livro/list-livro/page-livro";
import { PageLivroDetalhado } from "../livro/list-livro/page-livro-detalhado";
import { Livro } from "../livro/livro";
import { LivroDetalhado } from "../livro/livro-detalhado";

const API = 'http://localhost:8080/api';

@Injectable()
export class LivroService {

    constructor(private http: HttpClient) { }

    listarUltimosLivrosLancados() {

        return this.http
            .get<PageLivro>(`${API}/home/ultimosLivros?campoFiltro=dataPublicacao`)
    }

    listarUltimosLivrosAtualizados() {
        return this.http
            .get<PageLivro>(`${API}/home/ultimosLivros?campoFiltro=dataAtualizacao`)
    }

    listarLivrosPorCategoria(idCategoria: number) {
        return this.http.get<{ [key: string]: Livro[] }>(`${API}/detalhe/categoria/${idCategoria}`)
    }

    filtrarLivroGenerico(valor: string) {
        return this.http.get<PageLivroDetalhado>(`${API}/home/pesquisarLivro?valor=${valor}`)
    }

    buscarLivroPorIsbn(isbn: string) {
        return this.http.get<LivroDetalhado>(`${API}/livros/${isbn}`)
    }
}