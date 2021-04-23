import { Cupom } from "./cupom";
import { Endereco } from "./endereco";

export class DadosCompra {
    email: string
    nome: string
    sobreNome: string
    documento: string
    telefone: string    
    cupom: Cupom = new Cupom()
    endereco: Endereco = new Endereco()
    totalCompra: number

}