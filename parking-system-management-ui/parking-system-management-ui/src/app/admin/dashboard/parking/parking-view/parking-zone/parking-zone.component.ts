import { Component, OnInit, Input } from '@angular/core';
import { ParkingZone } from 'src/app/core/models';

@Component({
  selector: 'parking-zone',
  templateUrl: './parking-zone.component.html'
})
export class ParkingZoneComponent implements OnInit {

  @Input() parkingZone: ParkingZone;

  constructor() { }

  ngOnInit(): void {
  }

}
