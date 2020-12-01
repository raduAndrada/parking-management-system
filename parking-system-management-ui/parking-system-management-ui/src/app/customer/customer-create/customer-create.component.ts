import { Component, OnInit, Input } from '@angular/core';
import { User, UserType } from 'src/app/core/models';
import { ActivatedRoute, Router } from '@angular/router';
import { FaIconLibrary } from '@fortawesome/angular-fontawesome';
import {  faArrowRight } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-customer-create',
  templateUrl: './customer-create.component.html',
})
export class CustomerCreateComponent implements OnInit {
  toAdd: User = {
    name: '',
    birthday: '',
    username: '' ,
    address: '',
    email: '',
    password: '',
    phoneNumber: '',
    userType: UserType.CUSTOMER
  };

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private library: FaIconLibrary
    ) {
      library.addIcons(faArrowRight);
  }

  ngOnInit(): void {}

  back() {
    this.router.navigate(['users']);
  }


}
