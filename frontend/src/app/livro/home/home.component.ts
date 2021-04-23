import { Component, OnInit } from '@angular/core';

import { LivroService } from 'src/app/services/livro.service';
import { PageLivro } from '../list-livro/page-livro';

@Component({
  selector: 'cdc-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  pageLivrosultimosLancados: PageLivro
  pageLivrosultimosAtualizados: PageLivro

  constructor(private livroService: LivroService) { }

  ngOnInit() {
    this.livroService.listarUltimosLivrosLancados()
      .subscribe(page => this.pageLivrosultimosLancados = page, err => console.log(err))

    this.livroService.listarUltimosLivrosAtualizados()
      .subscribe(page => this.pageLivrosultimosAtualizados = page, err => console.log(err))
  }

}
