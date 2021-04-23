import { Component, Input, OnInit } from '@angular/core';
import { CompraService } from 'src/app/services/compra-service';
import { DadosCompra } from '../../dados-compra';
import { DadosPagamento } from '../../dados-pagamento';

import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'cdc-dados-compra',
  templateUrl: './dados-compra.component.html',
  styleUrls: ['./dados-compra.component.css']
})
export class DadosCompraComponent implements OnInit {

  @Input() dadosCompra: DadosCompra
  formaPagamento: string = "PAGSEGURO"

  constructor(private compraService: CompraService, private toastr: ToastrService,
    private router: Router) { }

  ngOnInit() {
    let dadosCompra = JSON.parse(window.localStorage.getItem("compra"))
    if (dadosCompra !== null) {
      this.dadosCompra = dadosCompra
    }
  }

  adicionarPagamento(formaPagamento) {
    this.formaPagamento = formaPagamento
  }

  fazerPagamento() {

    let pagamento = new DadosPagamento();
    pagamento.idCompra = JSON.parse(window.localStorage.getItem("idCompraGerada"))
    pagamento.tipo = this.formaPagamento
    pagamento.idCupom = JSON.parse(window.localStorage.getItem("compra")).cupom.codigo

    this.compraService.fazerPagamentoCompra(pagamento).subscribe(result => {
      this.limparDadosClient()
      this.router.navigate([''])
      this.toastr.success("Pagamento realizado com sucesso. Parabéns pela compra!!")
      this.toastr.info("Foi enviado um email com os dados da compra e da entrega. Fique Atento!!")
      /* Enquanto a transação está acontecendo no servidor, travar o botão de finalizar pagamento!
       Do usuário podemos esperar de tudo(inclusive vários clicks), ahh sim.. Por gentileza, 
       favor adicionar um aviso para o usuario enquanto esse acesso ao servidor estiver acontecendo,
       tipo um "loading" ;) */
    }, err =>
      /* Precisa dar um tratamento mais digno.*/
      console.log(err)
    )
  }

  limparDadosClient() {
    window.localStorage.removeItem("compra")
    window.localStorage.removeItem("idCompraGerada")
  }
}
