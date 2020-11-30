import { Component, OnInit, Input } from '@angular/core';
import { RestService } from 'src/app/core/RestService.service';
import { Parking, ParkingLevel, Membership, MembershipType, User } from 'src/app/core/models';
import { ActivatedRoute, Router } from '@angular/router';
import { faSave } from '@fortawesome/free-solid-svg-icons';
import { FaIconLibrary } from '@fortawesome/angular-fontawesome';

@Component({
  selector: 'membership-create',
  templateUrl: './membership-create.component.html',
  styleUrls: ['./membership-create.component.css'],
  providers: [RestService]
})
export class MembershipCreateComponent implements OnInit {

  @Input() public user: User; 

  public types = ['PERMANENT','YEAR','HALF_YEAR','SEASON','MONTH'];

  selectedParking: number;
  selectedParkingLevel: number;
  selectedType: string;
  parkings: Parking[];
  parkingLevels: ParkingLevel[];



  toAdd: Membership = {membershipType: MembershipType.SEASON, parkingSpot: null, user: null};

  constructor(  
      private parkingService: RestService<Parking>,
      private parkingLevelService: RestService<ParkingLevel>,
      private membershipService: RestService<Membership>,
      private route: ActivatedRoute,
      private router: Router,
      private library: FaIconLibrary
      ) {
      library.addIcons(faSave); 
      const parkingUrl = '/v1/parkings';
      this.parkingService.getList(parkingUrl).subscribe((parkings: Parking[]) => {
        this.parkings = parkings;
      });
     }

  ngOnInit(): void {
    
  }

  addMembership() {
    this.toAdd.user = this.user;
    const added = this.membershipService
      .create(this.toAdd, '/v1/memberships')
      .subscribe((added) => {
        console.log(added);
        this.router.navigate(['users']);
      });
  }

  onParkingChange() {
    this.selectedParkingLevel = 0;
    const parkingUrl = '/v1/parkingLevels/parking/' + this.selectedParking;
    this.parkingLevelService.getList(parkingUrl).subscribe((parkingLevels: ParkingLevel[]) => {
      this.parkingLevels = parkingLevels;
    });
  }

}
