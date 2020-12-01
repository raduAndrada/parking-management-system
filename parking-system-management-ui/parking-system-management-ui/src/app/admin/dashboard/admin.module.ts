import { NgModule } from "@angular/core";
import { UserListComponent } from './user/user-list/user-list.component';
import { UserViewComponent } from './user/user-view/user-view.component';
import { MembershipCreateComponent } from './user/user-view/membership-create/membership-create.component';
import { MembershipListComponent } from './user/user-view/membership-list/membership-list.component';
import { ParkingListComponent } from './parking/parking-list/parking-list.component';
import { ParkingZoneComponent } from './parking/parking-view/parking-zone/parking-zone.component';
import { ParkingCreateComponent } from './parking/parking-create/parking-create.component';
import { ParkingSpotComponent } from './parking/parking-view/parking-zone/parking-spot/parking-spot.component';
import { ParkingLevelComponent } from './parking/parking-view/parking-level/parking-level.component';
import { ParkingViewComponent } from './parking/parking-view/parking-view.component';
import { DashboardComponent } from './dashboard.component';
import { CoreModule } from 'src/app/core/core.module';
import { MDBBootstrapModule } from 'angular-bootstrap-md';


@NgModule({
  declarations: [
    UserListComponent,
    UserViewComponent,
    MembershipCreateComponent,
    MembershipListComponent,
    ParkingListComponent,
    ParkingZoneComponent,
    ParkingCreateComponent,
    ParkingSpotComponent,
    ParkingLevelComponent,
    ParkingViewComponent,
    DashboardComponent,
  ],
  imports: [
    CoreModule,
    MDBBootstrapModule.forRoot(),
  ],
  providers: [
  ],
  exports: [

  ],
})
export class AdminModule { }
