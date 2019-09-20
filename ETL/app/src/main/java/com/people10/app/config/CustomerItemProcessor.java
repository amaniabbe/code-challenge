package com.people10.app.config;

import com.people10.app.domain.Customer;
import com.people10.app.dto.CustomerDTO;

import org.springframework.batch.item.ItemProcessor;


//Custom Processor will conver data from DTO to Customer Object special string to double parsing
public class CustomerItemProcessor implements ItemProcessor<CustomerDTO,Customer>{

    @Override
    public Customer process(CustomerDTO item) throws Exception {
        Customer customer = new Customer();
        if(item ==null){
            return null;
        }

        customer.setCreatedAt(item.getCreatedAt());
        customer.setEmail(item.getEmail());
        customer.setFirstName(item.getFirstName());
        customer.setIp(item.getIp());

        if(item.getLatitude() == null || item.getLatitude().isEmpty())
        customer.setLatitude(0);
        else customer.setLatitude(Double.parseDouble(item.getLatitude()));

        if(item.getLongitude() == null || item.getLongitude().isEmpty())
        customer.setLatitude(0);
        else customer.setLatitude(Double.parseDouble(item.getLongitude()));


        return customer;
    }

   

}