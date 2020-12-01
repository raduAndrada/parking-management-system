import { Component, OnInit, Input } from '@angular/core';
import { ParkingLevel } from 'src/app/core/models';

@Component({
  selector: 'parking-level',
  templateUrl: './parking-level.component.html'
})
export class ParkingLevelComponent implements OnInit {

  @Input() parkingLevel: ParkingLevel;

  constructor() { }

  ngOnInit(): void {
  }

}
