import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Livro } from '../livro';

@Component({
  selector: 'cdc-card-livro',
  templateUrl: './card-livro.component.html',
  styleUrls: ['./card-livro.component.css']
})
export class CardLivroComponent implements OnInit {

  @Input() livro: Livro

  constructor(private router: Router) { }

  ngOnInit() {

  }

  detalharLivro() {
    this.router.navigate(['detalhe-livro', this.livro.isbn])
  }

}
