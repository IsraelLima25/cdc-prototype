import { Component, Input, OnInit } from '@angular/core';

import { Livro } from '../livro';

@Component({
  selector: 'cdc-list-livro',
  templateUrl: './list-livro.component.html',
  styleUrls: ['./list-livro.component.css']
})
export class ListLivroComponent implements OnInit {

  @Input() livros: Livro[]

  constructor() { }

  ngOnInit() {

  }

}
