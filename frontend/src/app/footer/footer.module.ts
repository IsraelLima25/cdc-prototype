import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FooterPrimaryComponent } from './footer-primary/footer-primary.component';
import { FooterSecondaryComponent } from './footer-secondary/footer-secondary.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [FooterPrimaryComponent, FooterSecondaryComponent],
  exports:[
    FooterPrimaryComponent,
    FooterSecondaryComponent
  ]
})
export class FooterModule { }
