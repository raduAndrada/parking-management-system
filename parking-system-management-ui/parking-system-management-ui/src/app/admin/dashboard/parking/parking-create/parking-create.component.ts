import { Component, OnInit } from '@angular/core';
import { ParkingCreate } from 'src/app/core/models';
import { ActivatedRoute, Router } from '@angular/router';
import { RestService } from 'src/app/core/RestService.service';
import { faSave } from '@fortawesome/free-solid-svg-icons';
import { FaIconLibrary } from '@fortawesome/angular-fontawesome';

@Component({
  selector: 'app-parking-create',
  templateUrl: './parking-create.component.html',
  styleUrls: ['./parking-create.component.css'],
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
      library.addIcons(faSave);
  }

  ngOnInit(): void {}

  create() {
    console.log(this.toAdd);
    const added = this.parkingService
      .create(this.toAdd, '/v1/parkings/configure')
      .subscribe((added) => {
        console.log(added);
        this.router.navigate(['parkings']);
      });
  }

}
