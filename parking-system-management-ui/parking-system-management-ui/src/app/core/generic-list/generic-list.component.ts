import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'generic-list',
  templateUrl: './generic-list.component.html',
  styleUrls: ['./generic-list.component.css']
})
export class GenericListComponent implements OnInit {

  @Input() public elements: [];
  @Input() public headElements: [];
  @Input() public elementKeys: [];

  constructor() { }

  ngOnInit(): void {
  }

}
