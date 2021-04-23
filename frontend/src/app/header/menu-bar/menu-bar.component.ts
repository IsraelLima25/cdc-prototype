import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';
import { CarrinhoService } from 'src/app/services/carrinho.service';

import { CategoriaService } from 'src/app/services/categoria.service';
import { MessageService } from 'src/app/shared/message.service';
import { Categoria } from '../categoria';

@Component({
  selector: 'cdc-menu-bar',
  templateUrl: './menu-bar.component.html',
  styleUrls: ['./menu-bar.component.css']
})
export class MenuBarComponent implements OnInit {

  categorias: Categoria[] = []
  valorPesquisa: string = ''
  quantidadeTotalItensCarrinho: string

  constructor(private categoriaService: CategoriaService, private toastr: ToastrService,
    private router: Router, private messageService: MessageService, private carrinhoService: CarrinhoService) { }

  ngOnInit() {
    
    this.carrinhoService.carregarCarrinho().subscribe(result => {
      if(result.quantidadeTotalItens > 0) {
        this.quantidadeTotalItensCarrinho = result.quantidadeTotalItens.toString()
      }
    })

    this.categoriaService.listarTodasCategorias()
      .subscribe(categoria => {
        this.categorias = this.categorias.concat(categoria)
      },
        err => console.log(err))

    this.messageService.getMessage().subscribe((message:string) => {
      this.quantidadeTotalItensCarrinho = message
    })    
        
  }

  pesquisarLivros() {
    if (this.valorPesquisa.length === 0) {
      this.toastr.warning('Pesquisa inválida')
    } else {
      this.router.navigate(['pesquisar', this.valorPesquisa])
    }
  }
}
