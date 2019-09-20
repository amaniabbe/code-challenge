import { Injectable } from '@angular/core';
import {customers} from './domain/customers'
import {Observable, of} from 'rxjs';
import {Customer} from './domain/Customer';


@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  customers:Customer[]
  constructor() { this.customers = customers }
  

  

  public getCustomer(id):Observable<Customer>{
    return  of(customers.find(customer=> customer.id = id))
  }

  public getAllCustomers():Observable<Customer[]>{
    return of(this.customers);
  }

  public deleteCustomer(id):Observable<Customer[]>{
    return of(this.customers.splice(id,1))
  }

  

}
