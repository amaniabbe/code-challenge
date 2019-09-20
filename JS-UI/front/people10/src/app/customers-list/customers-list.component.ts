import { Component, OnInit } from '@angular/core';
import {CustomerService} from '../customer.service';
import { Customer} from '../domain/Customer';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-customers-list',
  templateUrl: './customers-list.component.html',
  styleUrls: ['./customers-list.component.css']
})
export class CustomersListComponent implements OnInit {

  customers: Customer[];

  constructor(private customerService:CustomerService) { }

  ngOnInit() {
    this.getAllCustomeres();
  }

  getAllCustomeres(){
    this.customerService.getAllCustomers().subscribe(custs => {
      this.customers = custs;

      console.log("Records retured from API: " + custs.length)});
  }

  deleteCutomer(id){
    this.customerService.deleteCustomer(id).subscribe(result => {
      this.customers =  result;
    });
    
  }

}
