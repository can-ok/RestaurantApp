package com.resturant.restapi.converter;

import com.resturant.restapi.dto.CustomerDto;
import com.resturant.restapi.stub.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerDtoConverter {



    public static CustomerDto customerToCustomerDto(Customer customer){
        CustomerDto customerDto=new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setFirstName(customer.getFirstName().getValue());
        customerDto.setLastName(customer.getLastName().getValue());
        customerDto.setPhoneNumber(customer.getPhoneNumber().getValue());
        customerDto.setAddress(customer.getAddress().getValue());
        customerDto.setCity(customer.getCity().getValue());
        return customerDto;
    }


    public static List<CustomerDto> customerToCustomerDtoList(List<Customer> customerList){

        List<CustomerDto> customerDtoList=new ArrayList<>();
        customerList.forEach(customer -> {

            CustomerDto customerDto= customerToCustomerDto(customer);
            customerDtoList.add(customerDto);
        });

        return customerDtoList;
    }





}
