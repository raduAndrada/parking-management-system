import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
})
export class AddressComponent implements OnInit {

  @Input() public street: string;
  @Input() public state: string;
  @Input() public streetNo: string;
  @Input() public country: string;
  @Input() public city: string;
  
  constructor() { }

  ngOnInit(): void {
  }

}
