package com.people10.app.config;

import com.people10.app.domain.Customer;
import com.people10.app.service.CustomerService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;

//use Hibernate/jpa repository/service to write data to table
@PersistenceContext
public class CustomerItemWriter implements  ItemWriter<Customer> {



    @Autowired
    private CustomerService customerService;

    @Override
    @Transactional
    public void write(List<? extends Customer> items) {

        for (Customer customer : items){
            customerService.saveCustomer(customer);
        }

    }


}