import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule, Routes } from '@angular/router';

import { CookieService } from 'ngx-cookie-service';
import { ToastrModule } from 'ngx-toastr';

import { AppComponent } from './app.component';
import { HeaderModule } from './header/header.module';
import { CoreModule } from './core/core.module';
import { FooterModule } from './footer/footer.module';
import { LivroModule } from './livro/livro.module';
import { HomeComponent } from './livro/home/home.component';
import { LivroCategoriaComponent } from './livro/livro-categoria/livro-categoria.component';
import { ResultadoBuscaComponent } from './livro/resultado-busca/resultado-busca.component';
import { LivroDetalheComponent } from './livro/livro-detalhe/livro-detalhe.component';
import { CarrinhoComponent } from './compra/carrinho/carrinho.component';
import { CompraModule } from './compra/compra.module';
import { FinalizarCompraInformacoesComponent } from './compra/finalizar-compra-informacoes/finalizar-compra-informacoes.component';
import { FinalizarCompraPagamentoComponent } from './compra/finalizar-compra-pagamento/finalizar-compra-pagamento.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'livro-por-categoria/:idCategoria/:descricaoCategoriaMae', component: LivroCategoriaComponent },
  { path: 'pesquisar/:valorPesquisa', component: ResultadoBuscaComponent },
  { path: 'detalhe-livro/:isbn', component: LivroDetalheComponent },
  { path: 'adicionar-livro-carrinho/:isbn/:tipoLivro', component: CarrinhoComponent },
  { path: 'carrinho', component: CarrinhoComponent},
  { path: 'finalizar-compra-informacao', component: FinalizarCompraInformacoesComponent },
  { path: 'finalizar-compra-pagamento/:idCompra', component: FinalizarCompraPagamentoComponent }
]

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    CoreModule,
    HeaderModule,
    FooterModule,
    LivroModule,
    CompraModule,
    RouterModule.forRoot(routes),
    ToastrModule.forRoot()
  ],
  providers: [CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
