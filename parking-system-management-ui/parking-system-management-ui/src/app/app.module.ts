import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { MatTabsModule } from '@angular/material/tabs'
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

import { MDBBootstrapModule } from "angular-bootstrap-md";
import { AppRoutingModule } from './app-routing.module';
import { GenericListComponent } from './core/generic-list/generic-list.component';
import { ParkingListComponent } from './admin/dashboard/parking/parking-list/parking-list.component';
import { ParkingCreateComponent } from './admin/dashboard/parking/parking-create/parking-create.component';
import { DashboardComponent } from './admin/dashboard/dashboard.component';
import { UserListComponent } from './admin/dashboard/user/user-list/user-list.component';
import { CustomerCreateComponent } from './customer/customer-create/customer-create.component';
import { VehicleCreateComponent } from './customer/customer-create/vehicle-create/vehicle-create.component';
import { HttpClientModule } from '@angular/common/http';
import { HeaderComponent } from './core/header/header.component';
import { AddressComponent } from './core/address/address.component';
import { ParkingViewComponent } from './admin/dashboard/parking/parking-view/parking-view.component';
import { ParkingZoneComponent } from './admin/dashboard/parking/parking-view/parking-zone/parking-zone.component';
import { ParkingSpotComponent } from './admin/dashboard/parking/parking-view/parking-zone/parking-spot/parking-spot.component';
import { ParkingLevelComponent } from './admin/dashboard/parking/parking-view/parking-level/parking-level.component';
import { UserViewComponent } from './admin/dashboard/user/user-view/user-view.component';
import { MembershipListComponent } from './admin/dashboard/user/user-view/membership-list/membership-list.component';
import { MembershipCreateComponent } from './admin/dashboard/user/user-view/membership-create/membership-create.component';
import { ConfirmationModalComponent } from './core/confirmation-modal/confirmation-modal.component';

@NgModule({
  declarations: [
    AppComponent,
    GenericListComponent,
    ParkingListComponent,
    ParkingCreateComponent,
    DashboardComponent,
    UserListComponent,
    CustomerCreateComponent,
    VehicleCreateComponent,
    HeaderComponent,
    AddressComponent,
    ParkingViewComponent,
    ParkingZoneComponent,
    ParkingSpotComponent,
    ParkingLevelComponent,
    UserViewComponent,
    MembershipListComponent,
    MembershipCreateComponent,
    ConfirmationModalComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule, 
    FormsModule,
    HttpClientModule,
    MDBBootstrapModule.forRoot(),
    AppRoutingModule,
    FontAwesomeModule,
    MatTabsModule,
    MatButtonModule,
    MatSelectModule,
    MatFormFieldModule,
    MatInputModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
