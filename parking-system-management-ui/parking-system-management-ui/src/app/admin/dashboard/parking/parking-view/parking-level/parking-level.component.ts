import { Component, OnInit, Input } from '@angular/core';
import { ParkingLevel } from 'src/app/core/models';

@Component({
  selector: 'parking-level',
  templateUrl: './parking-level.component.html',
  styleUrls: ['./parking-level.component.css']
})
export class ParkingLevelComponent implements OnInit {

  @Input() parkingLevel: ParkingLevel;

  constructor() { }

  ngOnInit(): void {
  }

}
