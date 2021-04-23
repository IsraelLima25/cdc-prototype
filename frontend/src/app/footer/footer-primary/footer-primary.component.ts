import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'cdc-footer-primary',
  templateUrl: './footer-primary.component.html',
  styleUrls: ['./footer-primary.component.css']
})
export class FooterPrimaryComponent implements OnInit {

  valorPesquisa: string = ''

  constructor(private toastr: ToastrService, private router: Router) { }

  ngOnInit() {
  }

  pesquisarLivros() {
    if (this.valorPesquisa.length === 0) {
      this.toastr.warning('Pesquisa inválida')
    } else {
      this.router.navigate(['pesquisar', this.valorPesquisa])
    }
  }

}
