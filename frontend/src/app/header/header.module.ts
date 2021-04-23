import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { MenuBarComponent } from './menu-bar/menu-bar.component';
import { ItemMenuBarComponent } from './item-menu-bar/item-menu-bar.component';
import { CategoriaService } from '../services/categoria.service';

@NgModule({
  imports: [
    CommonModule,
    RouterModule
  ],
  declarations: [
    MenuBarComponent,
    ItemMenuBarComponent,
    ItemMenuBarComponent,
    MenuBarComponent
  ],
  exports: [
    MenuBarComponent
  ],
  providers: [
    CategoriaService
  ]
})
export class HeaderModule { }
