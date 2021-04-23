import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { FormGroup } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { CarrinhoService } from 'src/app/services/carrinho.service';
import { CupomService } from 'src/app/services/cupom.service';
import { Carrinho } from '../../carrinho/carrinho';
import { Cupom } from '../../cupom';
import { DadosCompra } from '../../dados-compra';


@Component({
  selector: 'cdc-itens-comprados',
  templateUrl: './itens-comprados.component.html',
  styleUrls: ['./itens-comprados.component.css']
})
export class ItensCompradosComponent implements OnInit {

  botaoCupomDisabled = true
  carrinho: Carrinho
  itensCompraForm: FormGroup
  @Input() dadosCompra: DadosCompra
  @Output() descontoAplicado = new EventEmitter()

  constructor(private carrinhoService: CarrinhoService, private cupomService: CupomService,
    private formBuilder: FormBuilder, private toastr: ToastrService) { }

  ngOnInit() {

    this.itensCompraForm = this.formBuilder.group({
      cupom: ['', Validators.required]
    })
    this.carrinhoService.carregarCarrinho().subscribe(result => {
      this.carrinho = result
    })
  }

  onKey(value) {
    if (value === '') {
      this.botaoCupomDisabled = true
    } else {
      this.botaoCupomDisabled = false
    }
  }

  aplicarDesconto() {
    let idCupom = this.itensCompraForm.get("cupom").value
    this.cupomService.aplicarCupom(idCupom).subscribe((result:Cupom) => {
      this.dadosCompra.cupom = result
      this.emitirDescontoAplicado(result)
    },
      erro => this.tratarErro(erro)
    )
  }

  tratarErro(erro){
    if(erro.status === 404) {
      /* Cupom não existe */
      this.toastr.error(erro.error.msg)     
    }
  }

  decontoAplicado(){
    if(this.dadosCompra.cupom.desconto > 0) {
      return true
    }else{
      return false
    }
  }

  emitirDescontoAplicado(cupom){
    this.descontoAplicado.emit(cupom)
  }
}
