import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CompraService } from 'src/app/services/compra-service';
import { Compra } from '../../compra';
import { DadosCompra } from '../../dados-compra';

@Component({
  selector: 'cdc-form-comprador',
  templateUrl: './form-comprador.component.html',
  styleUrls: ['./form-comprador.component.css']
})
export class FormCompradorComponent implements OnInit {

  compraForm: FormGroup;
  @Input() dadosCompra: DadosCompra
  @Output() emitirDadosCompraForm = new EventEmitter()
  errosForm = []

  constructor(private formBuilder: FormBuilder,
    private router: Router, private compraService: CompraService) { }

  ngOnInit() {

    this.compraForm = this.formBuilder.group({
      email: ['', Validators.required],
      nome: [],
      sobreNome: ['', Validators.required],
      documento: ['', Validators.required],
      endereco: ['', Validators.required],
      complemento: ['', Validators.required],
      cidade: ['', Validators.required],
      pais: ['', Validators.required],
      estado: ['', Validators.required],
      cep: ['', Validators.required],
      telefone: ['', Validators.required]

    })
    
    if(this.existeDadosCompra()){
      this.carregarDados()
    }

    this.router.navigateByUrl("/finalizar-compra-informacao")
  }

  enviarDadosCompra() {
    this.dadosCompra.email = this.compraForm.get("email").value
    this.dadosCompra.nome = this.compraForm.get("nome").value
    this.dadosCompra.sobreNome = this.compraForm.get("sobreNome").value
    this.dadosCompra.documento = this.compraForm.get("documento").value
    this.dadosCompra.endereco.enderecoLogradouro = this.compraForm.get("endereco").value
    this.dadosCompra.endereco.complemento = this.compraForm.get("complemento").value
    this.dadosCompra.endereco.pais = this.compraForm.get("pais").value
    this.dadosCompra.endereco.cidade = this.compraForm.get("cidade").value
    this.dadosCompra.endereco.estado = this.compraForm.get("estado").value
    this.dadosCompra.endereco.cep = this.compraForm.get("cep").value
    this.dadosCompra.telefone = this.compraForm.get("telefone").value
    
    this.emitirDadosCompra()
  }

  emitirDadosCompra(){    
    this.emitirDadosCompraForm.emit(this.dadosCompra)
    this.gerarItensCompra();
  }

  gerarItensCompra(){
    let compra = new Compra();
    compra.codigoCupom = this.dadosCompra.cupom.codigo
    compra.documento = this.dadosCompra.documento
    compra.email = this.dadosCompra.email
    compra.endereco = this.dadosCompra.endereco
    compra.nome = this.dadosCompra.nome
    compra.sobreNome = this.dadosCompra.sobreNome
    compra.telefone = this.dadosCompra.telefone

    console.log(this.dadosCompra)

    this.compraService.gerarItensCompra(compra).subscribe((result:any) => {           
      this.router.navigate(['finalizar-compra-pagamento', result.id])      
    },
    erro => {
      this.tratarErro(erro)
    }
    )
  }

  tratarErro(errosList) {
    this.errosForm =  errosList.error.listError
    console.log(this.errosForm)
  }

  existeDadosCompra(){
    if(this.dadosCompra === null) {
      return false
    }

    return true;
  }

  carregarDados(){
    this.compraForm.controls['email'].setValue(this.dadosCompra.email)
    this.compraForm.controls['nome'].setValue(this.dadosCompra.nome)
    this.compraForm.controls['sobreNome'].setValue(this.dadosCompra.sobreNome)
    this.compraForm.controls['documento'].setValue(this.dadosCompra.documento)
    this.compraForm.controls['endereco'].setValue(this.dadosCompra.endereco.enderecoLogradouro)
    this.compraForm.controls['complemento'].setValue(this.dadosCompra.endereco.complemento)
    this.compraForm.controls['pais'].setValue(this.dadosCompra.endereco.pais)
    this.compraForm.controls['cidade'].setValue(this.dadosCompra.endereco.cidade)
    this.compraForm.controls['estado'].setValue(this.dadosCompra.endereco.estado)
    this.compraForm.controls['cep'].setValue(this.dadosCompra.endereco.cep)
    this.compraForm.controls['telefone'].setValue(this.dadosCompra.telefone) 
  }

  irParaPagamento(){
    let idCompraGerada: string = window.localStorage.getItem("idCompraGerada")

    if(idCompraGerada !== 'null'){
      this.router.navigate(['finalizar-compra-pagamento', idCompraGerada])
    }
  }
}
