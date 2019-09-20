import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CustomerProfileComponent } from './customer-profile/customer-profile.component';
import { CustomersListComponent } from './customers-list/customers-list.component';
import {CustomerService} from './customer.service'
import { HttpClientModule } from '@angular/common/http';
import { CreateCustomerComponent } from './component/create-customer/create-customer.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    CustomerProfileComponent,
    CustomersListComponent,
    CreateCustomerComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [CustomerService],
  bootstrap: [AppComponent]
})
export class AppModule { }
