import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CarrinhoService } from 'src/app/services/carrinho.service';
import { MasterPageService } from 'src/app/shared/master-page.service';
import { Carrinho } from '../carrinho/carrinho';
import { Cupom } from '../cupom';
import { DadosCompra } from '../dados-compra';

@Component({
  selector: 'cdc-finalizar-compra-pagamento',
  templateUrl: './finalizar-compra-pagamento.component.html',
  styleUrls: ['./finalizar-compra-pagamento.component.css']
})
export class FinalizarCompraPagamentoComponent implements OnInit, OnDestroy {

  dadosCompra: DadosCompra;
  idCompraGeradaServidor: string
  carrinho: Carrinho

  constructor(private masterPageService: MasterPageService, private activatedRoute: ActivatedRoute,
    private router: Router, private carrinhoService: CarrinhoService) { }
  
  ngOnInit() {    

    this.masterPageService.userMenu(false)   
    this.activatedRoute.paramMap.subscribe(params => {      
      this.idCompraGeradaServidor = params.get("idCompra")      
      window.localStorage.setItem("idCompraGerada", this.idCompraGeradaServidor)     

    })    
    this.carrinhoService.carregarCarrinho().subscribe(result => {
      this.carrinho = result
      if(result.quantidadeTotalItens <= 0) {
        this.router.navigate(['carrinho'])
      }
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
    console.log("Desconto emitido"+cupom.codigo)
    this.dadosCompra.cupom.desconto = cupom.desconto
    this.dadosCompra.cupom.codigo = cupom.codigo
    this.dadosCompra.cupom.expiracao = cupom.expiracao
    this.dadosCompra.totalCompra -= cupom.desconto
    console.log(this.dadosCompra)
    window.localStorage.setItem("compra", JSON.stringify(this.dadosCompra)) 
       
  }
  
  ngOnDestroy() {
    this.masterPageService.userMenu(true)
  }
}
