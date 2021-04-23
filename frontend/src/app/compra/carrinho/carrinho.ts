import { ItemCarrinho } from "./item-carrinho";

export interface Carrinho {
    itens: ItemCarrinho[]
    totalCarrinho: number
    quantidadeTotalItens: number
}