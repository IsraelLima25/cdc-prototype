import { Component, Input, OnInit } from '@angular/core';

import { Categoria } from '../categoria';

@Component({
  selector: 'cdc-item-menu-bar',
  templateUrl: './item-menu-bar.component.html',
  styleUrls: ['./item-menu-bar.component.css']
})
export class ItemMenuBarComponent implements OnInit {

  @Input() categoria: Categoria

  constructor() { }

  ngOnInit() {
  }

}
