import { Output } from '@angular/core';
import { EventEmitter } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { CarrinhoService } from 'src/app/services/carrinho.service';
import { MessageService } from 'src/app/shared/message.service';
import { Carrinho } from './carrinho';

@Component({
  selector: 'cdc-carrinho',
  templateUrl: './carrinho.component.html',
  styleUrls: ['./carrinho.component.css']
})
export class CarrinhoComponent implements OnInit {

  carrinho: Carrinho

  constructor(private activatedRoute: ActivatedRoute, private carrinhoService: CarrinhoService,
    private router: Router, private messageService: MessageService) { }

  ngOnInit() {
      this.activatedRoute.paramMap.subscribe(params => {
      let isbn = params.get("isbn")
      let tipoLivro = params.get("tipoLivro")
      if (isbn !== null && tipoLivro !== null) {
        this.adicionarItemCarrinho(isbn, tipoLivro)
      } else if(this.carrinho === undefined) {
        this.carregarItensCarrinho()
      }
    })
  }

  adicionarItemCarrinho(isbn, tipoLivro) {
    this.carrinhoService.adicionarItemCarrinho(isbn, tipoLivro)
      .subscribe(result => {
        this.carrinho = result
        this.router.navigate(['carrinho'])
        this.atualizarQuantidades(this.carrinho.quantidadeTotalItens)
      })
    }
    
    incrementarItem(isbnLivro, quantidade, formato) {
      let quantidadeAtualizada = quantidade + 1
      this.carrinhoService.alterarQuantidadeItemCarrinho(isbnLivro, quantidadeAtualizada, formato)
      .subscribe(result => {
        this.carrinho = result
        this.atualizarQuantidades(this.carrinho.quantidadeTotalItens)
      })
    }

  decrementarItem(isbnLivro, quantidade, formato) {
    if (quantidade === 1)
      this.removerItem(isbnLivro, formato)
    else{
      let quantidadeAtualizada = quantidade - 1
      this.carrinhoService.alterarQuantidadeItemCarrinho(isbnLivro, quantidadeAtualizada, formato)
        .subscribe(result => {
          this.carrinho = result
          this.atualizarQuantidades(this.carrinho.quantidadeTotalItens)
        })
    }  
  }

  removerItem(isbnLivro, formato) {
    this.carrinhoService.removerItem(isbnLivro, formato)
      .subscribe(result => {
        this.carrinho = result
        this.atualizarQuantidades(this.carrinho.quantidadeTotalItens)
      })
  }

  carregarItensCarrinho() {
    this.carrinhoService.carregarCarrinho()
      .subscribe(result => {
        this.carrinho = result
        this.atualizarQuantidades(this.carrinho.quantidadeTotalItens)
      })
  }

  atualizarQuantidades(quantidadeTotalItensCarrinho) {
    this.messageService.sendMessage(quantidadeTotalItensCarrinho)
  }

  finalizarCompra(){
    let idCompraExistente = JSON.parse(window.localStorage.getItem("idCompraGerada"))
    if(idCompraExistente === null){      
      this.router.navigate(['finalizar-compra-informacao'])
    }else {
      this.router.navigate(['finalizar-compra-pagamento', idCompraExistente])
    }    
  }
}
