import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CustomerProfileComponent} from './customer-profile/customer-profile.component';
import {CustomersListComponent} from './customers-list/customers-list.component';
import {CreateCustomerComponent} from './component/create-customer/create-customer.component';
import {DashboardComponent}from './dashboard/dashboard.component'

const routes: Routes = [
  {path: '', redirectTo:'/home',pathMatch:'full'},
  {path:'home' , component:DashboardComponent},
  {path : 'customer/:id' , component:CustomerProfileComponent},
  {path : 'customers' , component:CustomersListComponent},
  {path : 'create' , component:CreateCustomerComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
