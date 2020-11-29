import { Component, OnInit, Input } from '@angular/core';
import { Vehicle, Size, User } from 'src/app/core/models';
import { ActivatedRoute, Router } from '@angular/router';
import { RestService } from 'src/app/core/RestService.service';

@Component({
  selector: 'vehicle-create',
  templateUrl: './vehicle-create.component.html',
  styleUrls: ['./vehicle-create.component.css'],
  providers: [RestService]
})
export class VehicleCreateComponent implements OnInit {

  @Input() public user?: User;

  vehicleSizes: Size[];
  selectedSize: Size;
  toAdd: Vehicle = {
    name: '',
    licencePlate: '',
    user: null,
    size: Size.MEDIUM,
  };

  constructor(
    private route: ActivatedRoute,
    private vehickeService: RestService<Vehicle>,
    private router: Router
  ) {
  }

  ngOnInit(): void {}

  create() {
    this.toAdd.user = this.user;
    const added = this.vehickeService
      .create(this.toAdd, 'v1/api/vehicles')
      .subscribe((added) => {
        console.log(added);
        this.router.navigate(['customers']);
      });
  }

}
