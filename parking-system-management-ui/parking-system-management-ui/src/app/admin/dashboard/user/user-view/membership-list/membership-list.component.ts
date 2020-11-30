import { Component, OnInit } from '@angular/core';
import { RestService } from 'src/app/core/RestService.service';
import { User, Membership } from 'src/app/core/models';
import { ActivatedRoute, Router } from '@angular/router';
import { FaIconLibrary } from '@fortawesome/angular-fontawesome';
import { faPlus } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'membership-list',
  templateUrl: './membership-list.component.html',
  styleUrls: ['./membership-list.component.css'],
  providers: [RestService]
})
export class MembershipListComponent implements OnInit {

  membershipList: Membership[];
  headElements = ['ID', 'Code', 'Spot Number', 'Membership Type'];

  constructor(
    private membershipService: RestService<Membership>,
    private route: ActivatedRoute,
    private router: Router,
    private library: FaIconLibrary
    ) {
    library.addIcons(faPlus);
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

  viewUser(membershipId) {
    this.router.navigate([membershipId], { relativeTo: this.route });
  }

}
