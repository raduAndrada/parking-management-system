import { Component, OnInit, ViewChild } from '@angular/core';
import { RestService } from 'src/app/core/RestService.service';
import { User, Membership } from 'src/app/core/models';
import { ActivatedRoute, Router } from '@angular/router';
import { FaIconLibrary } from '@fortawesome/angular-fontawesome';
import { faPlus, faTrash, faArrowRight } from '@fortawesome/free-solid-svg-icons';
import { ConfirmationModalComponent } from 'src/app/core/confirmation-modal/confirmation-modal.component';

@Component({
  selector: 'membership-list',
  templateUrl: './membership-list.component.html',
  providers: [RestService]
})
export class MembershipListComponent implements OnInit {

  @ViewChild("modalConfirmation") modalConfirmation:  ConfirmationModalComponent;

  membershipList: Membership[];
  headElements = ['ID', 'Code', 'Spot Number', 'Membership Type', 'Cancel Membership'];

  constructor(
    private membershipService: RestService<Membership>,
    private route: ActivatedRoute,
    private router: Router,
    private library: FaIconLibrary
    ) {
    library.addIcons(faPlus, faTrash, faArrowRight);
    const href = this.router.url;
    const userId = href.replace( /^\D+/g, '');
    const membershipUrl = '/v1/memberships/user/' + userId;
    this.membershipService.getList(membershipUrl).subscribe((membershipList: Membership[]) => {
      this.membershipList = membershipList;
    });
  }

  ngOnInit(): void {}

  addMembership() {
    this.router.navigate(['membership-create'], { relativeTo: this.route });
  }

  back() {
    this.router.navigate(['users']);
  }

  cancel(membershipId) {
    const modalRef = this.modalConfirmation.open();
    modalRef.result.then((result) => {
     if (result === "Ok") {
      const membershipUrl = '/v1/memberships/id';
     this.membershipService.delete(membershipId, membershipUrl).subscribe(temp =>  this.router.navigate(['users']));
     }
   });
 
    
  }

}
