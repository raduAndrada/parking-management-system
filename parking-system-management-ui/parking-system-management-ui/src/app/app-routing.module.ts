
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AppComponent } from './app.component';
import { CustomerCreateComponent } from './customer/customer-create/customer-create.component';
import { ParkingCreateComponent } from './admin/dashboard/parking/parking-create/parking-create.component';
import { ParkingListComponent } from './admin/dashboard/parking/parking-list/parking-list.component';
import { ParkingViewComponent } from './admin/dashboard/parking/parking-view/parking-view.component';
import { UserListComponent } from './admin/dashboard/user/user-list/user-list.component';
import { UserViewComponent } from './admin/dashboard/user/user-view/user-view.component';
import { MembershipCreateComponent } from './admin/dashboard/user/user-view/membership-create/membership-create.component';

const appRoutes: Routes = [
    { path: "customers/create", component: CustomerCreateComponent, pathMatch: "full"},
    { path: "parkings/create", component: ParkingCreateComponent, pathMatch: "full"},
    { path: "parkings/:id", component: ParkingViewComponent, pathMatch: "full"},
    { path: "parkings", component: ParkingListComponent, pathMatch: "full"},
    { path: "users", component: UserListComponent, pathMatch: "full"},
     { path: "users/:id/membership-create", component: MembershipCreateComponent, pathMatch: "full"},
    { path: "users/:id", component: UserViewComponent, pathMatch: "full"},
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
