import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/core/models';
import { ActivatedRoute, Router } from '@angular/router';
import { RestService } from 'src/app/core/RestService.service';
import { FaIconLibrary } from '@fortawesome/angular-fontawesome';

@Component({
  selector: 'user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css'],
  providers: [RestService]
})
export class UserListComponent implements OnInit {

  userList: User[];
  headElements = ['ID', 'Code', 'Name', 'Username', 'Email', 'Phone', 'Membership'];

  constructor(
    private userService: RestService<User>,
    private route: ActivatedRoute,
    private router: Router,
    ) {
    const userUrl = '/v1/users';
    this.userService.getList(userUrl).subscribe((userList: User[]) => {
      this.userList = userList;
    });
  }

  ngOnInit(): void {}

  addUser() {
    this.router.navigate(['create'], { relativeTo: this.route });
  }

  viewUser(userId) {
    this.router.navigate([userId], { relativeTo: this.route });
  }

}
