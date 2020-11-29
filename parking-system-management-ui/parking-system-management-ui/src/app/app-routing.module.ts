
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AppComponent } from './app.component';
import { CustomerCreateComponent } from './customer/customer-create/customer-create.component';

const appRoutes: Routes = [
    { path: "customers", component: CustomerCreateComponent, pathMatch: "full"},
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
