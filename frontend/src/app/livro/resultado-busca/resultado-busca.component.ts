import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { LivroService } from 'src/app/services/livro.service';
import { PageLivroDetalhado } from '../list-livro/page-livro-detalhado';

@Component({
  selector: 'cdc-resultado-busca',
  templateUrl: './resultado-busca.component.html',
  styleUrls: ['./resultado-busca.component.css']
})
export class ResultadoBuscaComponent implements OnInit {

  pageLivroDetalhado: PageLivroDetalhado
  valorBusca: string

  constructor(private activatedRoute: ActivatedRoute, private livroService: LivroService) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      this.valorBusca = params.get('valorPesquisa')
      this.livroService.filtrarLivroGenerico(this.valorBusca).subscribe(result => {
        this.pageLivroDetalhado = result
      })
    })
  }
}
