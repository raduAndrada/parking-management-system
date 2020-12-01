import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { RestService } from 'src/app/core/RestService.service';
import { Parking, ParkingLevel, MembershipCreate } from 'src/app/core/models';
import { ActivatedRoute, Router } from '@angular/router';
import { faSave, faArrowRight } from '@fortawesome/free-solid-svg-icons';
import { FaIconLibrary } from '@fortawesome/angular-fontawesome';
import { ConfirmationModalComponent } from 'src/app/core/confirmation-modal/confirmation-modal.component';

@Component({
  selector: 'membership-create',
  templateUrl: './membership-create.component.html',
  providers: [RestService]
})
export class MembershipCreateComponent implements OnInit {

  @ViewChild("modalConfirmation") modalConfirmation:  ConfirmationModalComponent;

  public types = ['PERMANENT','YEAR','HALF_YEAR','SEASON','MONTH'];

  selectedParking: number;
  selectedParkingLevel: number;
  selectedType: string;
  parkings: Parking[];
  parkingLevels: ParkingLevel[];
  userId: string;



  toAdd: MembershipCreate = {
    membershipType : '',
    parkingId: null,
    parkingLevelId: null,
    userId: null
  };

  constructor(  
      private parkingService: RestService<Parking>,
      private parkingLevelService: RestService<ParkingLevel>,
      private membershipService: RestService<MembershipCreate>,
      private route: ActivatedRoute,
      private router: Router,
      private library: FaIconLibrary
      ) {
      library.addIcons(faSave, faArrowRight); 
      const href = this.router.url;
      this.userId = href.replace( /^\D+/g, '');
      const parkingUrl = '/v1/parkings';
      this.parkingService.getList(parkingUrl).subscribe((parkings: Parking[]) => {
        this.parkings = parkings;
      });
     }

  ngOnInit(): void {
    
  }

  back() {
    this.router.navigate(['users']);
  }


  addMembership() {
    const modalRef = this.modalConfirmation.open();
    modalRef.result.then((result) => {
     if (result === "Ok") {
      this.toAdd.userId = parseInt(this.userId);
      this.toAdd.membershipType = this.selectedType;
      this.toAdd.parkingId = this.selectedParking;
      this.toAdd.parkingLevelId = this.selectedParkingLevel;
      const added = this.membershipService
        .create(this.toAdd, '/v1/memberships/create')
        .subscribe((added) => {
          this.router.navigate(['users']);
      });
     }
   });

  }

  onParkingChange() {
    const parkingUrl = '/v1/parkingLevels/parking/' + this.selectedParking;
    this.parkingLevelService.getList(parkingUrl).subscribe((parkingLevels: ParkingLevel[]) => {
      this.parkingLevels = parkingLevels;
      if (parkingLevels !== null && parkingLevels.length > 0) {
        this.selectedParkingLevel = parkingLevels[0].id ;
      }
    });
  }

}
