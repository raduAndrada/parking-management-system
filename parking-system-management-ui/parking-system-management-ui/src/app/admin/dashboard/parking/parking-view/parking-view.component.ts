import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RestService } from 'src/app/core/RestService.service';
import { Parking, ParkingLevel } from 'src/app/core/models';
import { FaIconLibrary } from '@fortawesome/angular-fontawesome';
import { faPlus } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'parking-view',
  templateUrl: './parking-view.component.html',
  styleUrls: ['./parking-view.component.css'],
  providers: [RestService]
})
export class ParkingViewComponent implements OnInit {

  parkingLevels: ParkingLevel[];
  href: string;
  parkingName: string;

  constructor(
    private route: ActivatedRoute,
    private parkingLevelService: RestService<ParkingLevel>,
    private router: Router,
    private library: FaIconLibrary
    ) {
    this.href = this.router.url;
    const parkingId = this.href.replace( /^\D+/g, '');
    library.addIcons(faPlus);
    const parkingUrl = '/v1/parkingLevels/parking/' + parkingId;
    this.parkingLevelService.getList(parkingUrl).subscribe((parkingLevels: ParkingLevel[]) => {
      this.parkingLevels = parkingLevels;
      if (parkingLevels !== undefined && parkingLevels !== null && parkingLevels.length > 0) {
        this.parkingName = parkingLevels[0].parking.name;
      }
    });
  }


  ngOnInit(): void {
  }

}
