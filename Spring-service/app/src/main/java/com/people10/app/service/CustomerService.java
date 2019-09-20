package com.people10.app.service;

import javax.transaction.Transactional;

import com.people10.app.domain.Customer;
import com.people10.app.repository.CustomerRepository;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public ResponseEntity<String> saveCustomer(Customer customer) {
         customerRepository.save(customer);
        return new ResponseEntity<>("Customer Saved", HttpStatus.CREATED);
    }


    public List<Customer> findAllCustomer() {
        List<Customer> customerList = new ArrayList<>();
        customerRepository.findAll().forEach(customerList::add);
        return customerList;
    }

    public Customer findCustomer(Long id) {

        Customer customer = customerRepository.findById(id).get();
        return customer;
    }

    public ResponseEntity<String> deleteCustomer(Long id) {
        System.out.println("Delete Customer with Id  " + id);
        customerRepository.deleteById(id);
        return new ResponseEntity<>("Customer has been deleted", HttpStatus.OK);

    }

    public ResponseEntity<String> updateCustomer(Customer customer) {

        customerRepository.save(customer);
        return new ResponseEntity<>("Customer has been updated", HttpStatus.OK);

    }


}