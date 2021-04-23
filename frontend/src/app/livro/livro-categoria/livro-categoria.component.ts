import { Component, OnChanges, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { LivroService } from 'src/app/services/livro.service';
import { Livro } from '../livro';

@Component({
  selector: 'cdc-livro-categoria',
  templateUrl: './livro-categoria.component.html',
  styleUrls: ['./livro-categoria.component.css']
})
export class LivroCategoriaComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private livroService: LivroService) { }

  livrosCategoria: { [key: string]: Livro[] }
  descricaoCategoriaMae: string
  descricaoCategoriaFilho: string

  ngOnInit() {
    this.inicializarEstados()
  }

  buscarLivrosAgrupadosPorCategoria(idCategoriaClick) {
    this.livroService.listarLivrosPorCategoria(idCategoriaClick)
      .subscribe(mapLivro => {
        console.log(mapLivro)
        this.livrosCategoria = mapLivro
      },
        err => console.log(err))
  }

  inicializarEstados() {
    this.activatedRoute.paramMap
      .subscribe(params => {
        this.descricaoCategoriaMae = params.get("descricaoCategoriaMae")
        let idCategoriaClick = this.activatedRoute.snapshot.params['idCategoria']
        this.buscarLivrosAgrupadosPorCategoria(idCategoriaClick)
      });
  }
}
