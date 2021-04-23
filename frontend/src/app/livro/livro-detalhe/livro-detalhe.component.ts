import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { CookieService } from 'ngx-cookie-service';

import { LivroService } from 'src/app/services/livro.service';
import { LivroDetalhado } from '../livro-detalhado';

@Component({
  selector: 'cdc-livro-detalhe',
  templateUrl: './livro-detalhe.component.html',
  styleUrls: ['./livro-detalhe.component.css']
})
export class LivroDetalheComponent implements OnInit {

  livroBusca: LivroDetalhado

  constructor(private activatedRoute: ActivatedRoute, private livroService: LivroService,
    private router: Router, private cookieService: CookieService) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      let isbnBusca = params.get("isbn")
      this.livroService.buscarLivroPorIsbn(isbnBusca).subscribe(
        result => this.livroBusca = result,
        /* Melhorar futuramente o tratamento desta excessão causada por alguma instabilidade do servidor */
        /* Não gostou da forma como está? Implemente a melhor solução você! */
        err => (console.log(err))
      )
    })
  }

  enviarItemCarrinho(tipoLivroSelecionado: any) {
    this.router.navigate(['adicionar-livro-carrinho', this.livroBusca.isbn, tipoLivroSelecionado])    
  }

}
