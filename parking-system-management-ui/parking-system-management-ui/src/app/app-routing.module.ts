
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AppComponent } from './app.component';
import { CustomerCreateComponent } from './customer/customer-create/customer-create.component';
import { ParkingCreateComponent } from './admin/dashboard/parking/parking-create/parking-create.component';
import { ParkingListComponent } from './admin/dashboard/parking/parking-list/parking-list.component';
import { ParkingViewComponent } from './admin/dashboard/parking/parking-view/parking-view.component';

const appRoutes: Routes = [
    { path: "customers", component: CustomerCreateComponent, pathMatch: "full"},
    { path: "parkings/create", component: ParkingCreateComponent, pathMatch: "full"},
    { path: "parkings/:id", component: ParkingViewComponent, pathMatch: "full"},
    { path: "parkings", component: ParkingListComponent, pathMatch: "full"},
    { path: "" , redirectTo: "/customers", pathMatch: "full"},
  ];

@NgModule({
    imports: [
        RouterModule.forRoot(appRoutes/*, {useHash: true}*/)
    ],
    providers: [
    ],
    exports: [ RouterModule]
})
export class AppRoutingModule {

}
