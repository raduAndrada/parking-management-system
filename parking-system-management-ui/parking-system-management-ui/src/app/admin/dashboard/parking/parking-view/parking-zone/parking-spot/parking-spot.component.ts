import { Component, OnInit, Input } from '@angular/core';
import { ParkingSpot } from 'src/app/core/models';
import { RestService } from 'src/app/core/RestService.service';
import { FaIconLibrary } from '@fortawesome/angular-fontawesome';
import { faMoneyBillWave } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'parking-spot',
  templateUrl: './parking-spot.component.html',
  styleUrls: ['./parking-spot.component.css'],
  providers: [RestService]
})
export class ParkingSpotComponent implements OnInit {

  @Input() parkingSpot: ParkingSpot;

  isRentable: boolean;

  constructor(
    private readonly parkingSpotService: RestService<ParkingSpot>,
    private library: FaIconLibrary
    ) { 
      library.addIcons(faMoneyBillWave)

    }

  ngOnInit(): void {
  }

  rentParkingSpot() {
    this.parkingSpot.rentable = !this.parkingSpot.rentable;
    this.parkingSpotService
                        .update(this.parkingSpot, '/v1/parkingSpots')
                        .subscribe(updated => this.parkingSpot = updated);
  }

}
