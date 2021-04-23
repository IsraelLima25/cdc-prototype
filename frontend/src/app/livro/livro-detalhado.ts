import { Autor } from "./autor";
import { Categoria } from "./categoria";
import { TipoLivro } from "./tipo-livro";

export interface LivroDetalhado {
    isbn: string
    titulo: string
    subtitulo: string
    conteudo: string
    sumarioMarkDown: string
    numeroPaginas: number
    autor: Autor
    categoria: Categoria
    dataPublicacao: Date
    dataAtualizacao: Date
    tipos: TipoLivro[]
    linkCapa: string

}