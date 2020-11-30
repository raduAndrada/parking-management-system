import { Component, OnInit, Input } from '@angular/core';
import { ParkingSpot } from 'src/app/core/models';

@Component({
  selector: 'parking-spot',
  templateUrl: './parking-spot.component.html',
  styleUrls: ['./parking-spot.component.css']
})
export class ParkingSpotComponent implements OnInit {

  @Input() parkingSpot: ParkingSpot;

  constructor() { }

  ngOnInit(): void {
  }

}
