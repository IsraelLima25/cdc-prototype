import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListLivroComponent } from './list-livro/list-livro.component';
import { CardLivroComponent } from './card-livro/card-livro.component';
import { HomeComponent } from './home/home.component';
import { LivroService } from '../services/livro.service';
import { LivroCategoriaComponent } from './livro-categoria/livro-categoria.component';
import { ResultadoBuscaComponent } from './resultado-busca/resultado-busca.component';
import { LivroDetalheComponent } from './livro-detalhe/livro-detalhe.component';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    CommonModule,
    RouterModule
  ],
  declarations: [
    ListLivroComponent,
    CardLivroComponent,
    HomeComponent, LivroCategoriaComponent, ResultadoBuscaComponent, LivroDetalheComponent
  ],
  exports: [
    HomeComponent, ListLivroComponent, CardLivroComponent
  ],
  providers: [
    LivroService
  ]
})
export class LivroModule { }
