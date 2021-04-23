import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CarrinhoComponent } from './carrinho/carrinho.component';
import { CarrinhoService } from '../services/carrinho.service';
import { RouterModule } from '@angular/router';
import { FinalizarCompraInformacoesComponent } from './finalizar-compra-informacoes/finalizar-compra-informacoes.component';
import { ReactiveFormsModule } from '@angular/forms';
import { CupomService } from '../services/cupom.service';
import { ItensCompradosComponent } from './finalizar-compra-informacoes/itens-comprados/itens-comprados.component';
import { FormCompradorComponent } from './finalizar-compra-informacoes/form-comprador/form-comprador.component';
import { CompraService } from '../services/compra-service';
import { FinalizarCompraPagamentoComponent } from './finalizar-compra-pagamento/finalizar-compra-pagamento.component';
import { DadosCompraComponent } from './finalizar-compra-pagamento/dados-compra/dados-compra.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    ReactiveFormsModule
  ],
  declarations: [
    CarrinhoComponent, 
    FinalizarCompraInformacoesComponent, 
    ItensCompradosComponent, 
    FormCompradorComponent, 
    FinalizarCompraPagamentoComponent, 
    DadosCompraComponent
  ],
  providers: [
    CarrinhoService,
    CupomService,
    CompraService
  ]
})
export class CompraModule { }
