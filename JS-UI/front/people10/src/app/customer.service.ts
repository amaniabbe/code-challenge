import { Injectable } from '@angular/core';
import {customers} from './domain/customers'
import {Observable, of} from 'rxjs';
import {Customer} from './domain/Customer';


@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  customers:Customer[]
  constructor() { this.customers = customers
    this.customers.sort((a , b) => a.id - b.id)
  }
  

  

  public getCustomer(id):Observable<Customer>{
    return  of(customers.find(customer=> customer.id = id))
  }

  public getAllCustomers():Observable<Customer[]>{
    return of(this.customers);
  }

  public deleteCustomer(id):Observable<Customer[]>{
    this.customers = customers.filter(obj => obj.id == id)
    return of(this.customers)
  }

  public editCustomer(from:Customer , to:Customer){
    var  index = this.customers.indexOf(from)
    if(index > -1){
      this.customers[index].email = to.email;
      this.customers[index].ip = to.ip;
      this.customers[index].latitude = to.latitude;
      this.customers[index].longitude = to.longitude;
    }

  }

  public addCustomer(customer:Customer){
    var id = this.customers[customers.length -1].id;
    customer.id = id + 1;
    customers.push(customer); 
  }

}
