import { Component, OnInit, Input } from '@angular/core';
import { User, UserType } from 'src/app/core/models';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-customer-create',
  templateUrl: './customer-create.component.html',
  styleUrls: ['./customer-create.component.css']
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
    private router: Router
  ) {
  }

  ngOnInit(): void {}



}
