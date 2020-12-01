import { Component, OnInit, Input } from '@angular/core';
import { Vehicle, Size, User } from 'src/app/core/models';
import { ActivatedRoute, Router } from '@angular/router';
import { RestService } from 'src/app/core/RestService.service';
import { FaIconLibrary } from '@fortawesome/angular-fontawesome';
import {faSave} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'vehicle-create',
  templateUrl: './vehicle-create.component.html',
  styleUrls: ['./vehicle-create.component.css'],
  providers: [RestService]
})
export class VehicleCreateComponent implements OnInit {

  @Input() public user?: User;

  vehicleSizes = ['LARGE', 'MEDIUM', 'SMALL'];
  selectedSize: Size;
  toAdd: Vehicle = {
    name: '',
    licencePlate: '',
    user: null,
    size: Size.MEDIUM,
  };

  constructor(
    private route: ActivatedRoute,
    private vehicleService: RestService<Vehicle>,
    private router: Router,
    private library: FaIconLibrary
  ) {
    library.addIcons(faSave);
  }

  ngOnInit(): void {}

  create() {
    this.toAdd.user = this.user;
    const added = this.vehicleService
      .create(this.toAdd, '/v1/vehicles')
      .subscribe((added) => {
        this.router.navigate(['customers']);
      });
  }

}
