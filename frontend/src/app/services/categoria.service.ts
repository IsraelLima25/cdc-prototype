import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Categoria } from '../header/categoria';

const API = 'http://localhost:8080/api';

@Injectable()
export class CategoriaService {

    constructor(private http: HttpClient) { }

    listarTodasCategorias() {
        return this.http
            .get<Categoria[]>(`${API}/categorias/todasCategorias`);
    }
}