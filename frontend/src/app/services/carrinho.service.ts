import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Carrinho } from "../compra/carrinho/carrinho";

const API = 'http://localhost:8080/api';

@Injectable()
export class CarrinhoService {

    constructor(private http: HttpClient) { }

    adicionarItemCarrinho(isbn, tipoLivro) {

        return this.http
            .post<Carrinho>(`${API}/detalhe/carrinho/adicionar/${isbn}?formato=${tipoLivro}`, null,
                { withCredentials: true })
    }

    alterarQuantidadeItemCarrinho(isbn, quantidade, formato) {
        return this.http
            .put<Carrinho>(`${API}/carrinho/${isbn}/atualizar?novaQuantidade=${quantidade}&formato=${formato}`, null,
                { withCredentials: true })
    }

    removerItem(isbn, formato) {
        return this.http.delete<Carrinho>(`${API}/carrinho/${isbn}/remover?formato=${formato}`, { withCredentials: true })
    }

    carregarCarrinho() {
        return this.http.get<Carrinho>(`${API}/carrinho`, { withCredentials: true })
    }
}