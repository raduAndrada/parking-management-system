import { NgModule } from "@angular/core";
import { GenericListComponent } from './generic-list/generic-list.component';
import { HeaderComponent } from './header/header.component';
import { AddressComponent } from './address/address.component';
import { ConfirmationModalComponent } from './confirmation-modal/confirmation-modal.component';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from '../app-routing.module';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatTabsModule } from '@angular/material/tabs';
import { MatButtonModule } from '@angular/material/button';
import { AuthService } from './auth.service';



@NgModule({
  declarations: [
    HeaderComponent,
    GenericListComponent,
    AddressComponent,
    ConfirmationModalComponent,

  ],
  imports: [
    MDBBootstrapModule.forRoot(),
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    FontAwesomeModule,
    BrowserModule,
    BrowserAnimationsModule, 
    MatTabsModule,
    MatButtonModule,

  ],
  providers: [AuthService],
  exports: [
    FormsModule,
    HttpClientModule,
    HeaderComponent,
    GenericListComponent,
    AddressComponent,
    ConfirmationModalComponent,
    AppRoutingModule,
    FontAwesomeModule,
    BrowserModule,
    BrowserAnimationsModule, 
    MatTabsModule,
    MatButtonModule,
  ],
})
export class CoreModule  {
 }
