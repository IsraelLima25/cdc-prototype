import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CarrinhoService } from 'src/app/services/carrinho.service';
import { MasterPageService } from 'src/app/shared/master-page.service';
import { Carrinho } from '../carrinho/carrinho';
import { Cupom } from '../cupom';
import { DadosCompra } from '../dados-compra';

@Component({
  selector: 'cdc-finalizar-compra-informacoes',
  templateUrl: './finalizar-compra-informacoes.component.html',
  styleUrls: ['./finalizar-compra-informacoes.component.css']
})
export class FinalizarCompraInformacoesComponent implements OnInit, OnDestroy {
  
  dadosCompra: DadosCompra = new DadosCompra()
  carrinho: Carrinho

  constructor(private carrinhoService: CarrinhoService, private masterPageService: MasterPageService,
    private router: Router){}

  ngOnInit() {

    this.carrinhoService.carregarCarrinho().subscribe(result=>{
      this.carrinho = result
      if(result.quantidadeTotalItens <= 0) {
        this.router.navigate(['carrinho'])
      }
    })

    this.masterPageService.userMenu(false)

    this.carrinhoService.carregarCarrinho().subscribe(result => {
      if(this.dadosCompra.cupom.desconto === undefined){
        this.dadosCompra.totalCompra = result.totalCarrinho
      }else{
        this.dadosCompra.totalCompra = result.totalCarrinho - this.dadosCompra.cupom.desconto
      }
    })

    let compra = window.localStorage.getItem("compra") 
    if(compra === null){
      window.localStorage.setItem("compra",JSON.stringify(this.dadosCompra))
    }else{
      this.dadosCompra = JSON.parse(window.localStorage.getItem("compra"))
    }
  }

  descontoAplicadoEmitido(cupom: Cupom) {    
    this.dadosCompra = JSON.parse(window.localStorage.getItem("compra"))
    this.dadosCompra.cupom.desconto = cupom.desconto
    this.dadosCompra.cupom.codigo = cupom.codigo
    this.dadosCompra.cupom.expiracao = cupom.expiracao    
    this.dadosCompra.totalCompra = this.carrinho.totalCarrinho - cupom.desconto
    window.localStorage.setItem("compra", JSON.stringify(this.dadosCompra))    
  }

  dadosCompraFormEmitido(dadosCompraForm){
    this.dadosCompra = dadosCompraForm
    window.localStorage.setItem("compra", JSON.stringify(this.dadosCompra))    
  }

  ngOnDestroy() {
    this.masterPageService.userMenu(true)
  }

}
