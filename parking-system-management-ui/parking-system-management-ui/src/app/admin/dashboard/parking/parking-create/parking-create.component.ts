import { Component, OnInit } from '@angular/core';
import { ParkingCreate } from 'src/app/core/models';
import { ActivatedRoute, Router } from '@angular/router';
import { RestService } from 'src/app/core/RestService.service';
import { faSave, faArrowRight } from '@fortawesome/free-solid-svg-icons';
import { FaIconLibrary } from '@fortawesome/angular-fontawesome';

@Component({
  selector: 'app-parking-create',
  templateUrl: './parking-create.component.html',
  providers: [RestService]
})
export class ParkingCreateComponent implements OnInit {

  toAdd: ParkingCreate = {
    parking: {closesAt: '', location: '', name: '', opensAt: ''},
    numberOfLevels: '',
    parkingZoneEndingLetter: '',
    parkingZoneStartingLetter: '',
    parkingZoneSpotNumber: 1,
  };

  constructor(
    private route: ActivatedRoute,
    private parkingService: RestService<ParkingCreate>,
    private router: Router,    
    private library: FaIconLibrary
    ) {
      library.addIcons(faSave, faArrowRight);
  }

  ngOnInit(): void {}

  create() {
    const added = this.parkingService
      .create(this.toAdd, '/v1/parkings/configure')
      .subscribe((added) => {
        this.router.navigate(['parkings']);
      });
  }

  back() {
    this.router.navigate(['parkings']);
  }


}
