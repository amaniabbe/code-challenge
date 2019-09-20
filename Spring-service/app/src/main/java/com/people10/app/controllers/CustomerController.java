package com.people10.app.controllers;

import com.people10.app.domain.Customer;
import com.people10.app.service.CustomerService;


import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
   
    @GetMapping("/customers")
    public List<Customer> findAllCustomers(){
        //System.out.println(customerService.findAllCustomer().size());
       return customerService.findAllCustomer();
    }

    @PostMapping("/customers")
    public ResponseEntity<String> saveCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }



    @GetMapping("/customers/{id}")
    public Customer findCustomer(@PathVariable Long id){
        return customerService.findCustomer(id);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id){
       return customerService.deleteCustomer(id);
    }


    @PutMapping("/customers")
    public ResponseEntity<String> updateCustomer(@RequestBody Customer customer){
        return customerService.updateCustomer(customer);
    }


}