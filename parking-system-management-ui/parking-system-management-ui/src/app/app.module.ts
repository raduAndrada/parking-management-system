import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';


import { CustomerCreateComponent } from './customer/customer-create/customer-create.component';
import { VehicleCreateComponent } from './customer/customer-create/vehicle-create/vehicle-create.component';

import { AdminModule } from './admin/dashboard/admin.module';
import { CoreModule } from './core/core.module';
import { LoginComponent } from './login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    CustomerCreateComponent,
    VehicleCreateComponent,
    LoginComponent,

  ],
  imports: [
    AdminModule,
    CoreModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
