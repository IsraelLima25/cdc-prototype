import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Cupom } from "../compra/cupom";

const API = 'http://localhost:8080/api';

@Injectable()
export class CupomService{

    constructor(private http: HttpClient) { }

    aplicarCupom(idCupom){
        return this.http.put<Cupom>(`${API}/cupom/${idCupom}/aplicar`,null, {withCredentials: true})
    }

}