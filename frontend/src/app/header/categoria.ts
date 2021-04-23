import { CategoriaFilho } from "./categoria-filho";
import { CategoriaMae } from "./categoria-mae";

export interface Categoria {
    categoriaMae: CategoriaMae;
    categoriasFilho: CategoriaFilho[];
}