import { Component, OnInit } from '@angular/core';
import {CustomerService} from '../customer.service';
import { Customer} from '../domain/Customer';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-customer-profile',
  templateUrl: './customer-profile.component.html',
  styleUrls: ['./customer-profile.component.css']
})
export class CustomerProfileComponent implements OnInit {

  customer:Customer;

  constructor(private customerService:CustomerService , private route:ActivatedRoute) {}

  getCustomer(){
    const id  = this.route.snapshot.paramMap.get('id');

    this.customerService.getCustomer(id).subscribe( cust =>
      {console.log(cust);
        this.customer = cust;
       }
    )
  }

  ngOnInit() {
    this.getCustomer();
  }

}
