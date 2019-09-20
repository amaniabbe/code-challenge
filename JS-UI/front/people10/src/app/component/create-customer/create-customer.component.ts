import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/domain/Customer';

@Component({
  selector: 'app-create-customer',
  templateUrl: './create-customer.component.html',
  styleUrls: ['./create-customer.component.css']
})
export class CreateCustomerComponent implements OnInit {
  customer:Customer;
  
  constructor() { }

  ngOnInit() {
  }

}
