import { Component, OnInit } from '@angular/core';
import { Parking } from 'src/app/core/models';
import { ActivatedRoute, Router } from '@angular/router';
import { RestService } from 'src/app/core/RestService.service';
import { FaIconLibrary } from '@fortawesome/angular-fontawesome';
import { faPlus } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'parking-list',
  templateUrl: './parking-list.component.html',
  styleUrls: ['./parking-list.component.css'],
  providers: [RestService]
})
export class ParkingListComponent implements OnInit {

  parkingList: Parking[];
  headElements = ['ID', 'Code', 'Name', 'Location'];

  constructor(
    private route: ActivatedRoute,
    private parkingService: RestService<Parking>,
    private router: Router,
    private library: FaIconLibrary
    ) {
    library.addIcons(faPlus);
    const parkingUrl = '/v1/parkings';
    this.parkingService.getList(parkingUrl).subscribe((parkingList: Parking[]) => {
      this.parkingList = parkingList;
    });
  }

  ngOnInit(): void {}

  addParking() {
    this.router.navigate(['create'], { relativeTo: this.route });
  }

  viewParking(parkingId) {
    this.router.navigate([parkingId], { relativeTo: this.route });
  }


}
