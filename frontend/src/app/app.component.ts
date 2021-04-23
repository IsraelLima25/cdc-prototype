import { AfterViewChecked, ChangeDetectorRef, OnDestroy } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { MasterPageService } from './shared/master-page.service';

@Component({
  selector: 'cdc-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, AfterViewChecked, OnDestroy { 

  title = 'app'
  
  exibirMenu: any = true
  constructor(private masterPageService: MasterPageService, private readonly changeDetectorRef: ChangeDetectorRef){}
  
  ngAfterViewChecked(): void {
    this.changeDetectorRef.detectChanges();
  }
  
  ngOnInit() {
    this.masterPageService.getMessage().subscribe((message:string) => {
      this.exibirMenu = message
    })  
  } 

  ngOnDestroy(){
    window.localStorage.removeItem("compra")
    window.localStorage.removeItem("idCompraGerada")
  }
}
