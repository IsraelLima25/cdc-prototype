import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Compra } from '../compra/compra';
import { DadosPagamento } from '../compra/dados-pagamento';

const API = 'http://localhost:8080/api';

@Injectable()
export class CompraService {

    constructor(private http: HttpClient) { }

    gerarItensCompra(compra: Compra) {
        return this.http.post(`${API}/compra/finalizar`, compra, { withCredentials: true })
    }

    fazerPagamentoCompra(dadosPagamento: DadosPagamento) {
        return this.http.post(`${API}/pagamentos/pagar`, dadosPagamento, { withCredentials: true })
    }
    
}