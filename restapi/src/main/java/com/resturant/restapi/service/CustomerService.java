package com.resturant.restapi.service;

import com.resturant.restapi.Model.Customer;
import com.resturant.restapi.converter.CustomerMapper;
import com.resturant.restapi.dto.CustomerDto;
import com.resturant.restapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerMapper customerMapper;

    public Page<CustomerDto> getAllCustomers(int pageCount,int pageSize){

        Pageable pages= PageRequest.of(pageCount,pageSize);
        Page<CustomerDto> customers=customerRepository.findAll(pages).map(customerMapper::toDto);

        return customers;
    }

    public String insertCustomer(CustomerDto customerDto){
        if(customerDto.equals(null)){
            return null;
        }

        Customer customer=customerMapper.toEntity(customerDto);

        customerRepository.save(customer);
        return "Success";
    }

    public String deleteCustomer(Integer id){
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if(!customerOptional.isPresent()){
            return "Fail";
        }
        customerRepository.delete(customerOptional.get());
        return "Success";
    }

    public CustomerDto updateCustomer(CustomerDto customerDto){

        if(customerDto.equals(null) || customerDto.getCustomerId()==null){
            return null;
        }

        Optional<Customer> optionalCustomer = customerRepository.findById(customerDto.getCustomerId());
        if(!optionalCustomer.isPresent()){
            return null;
        }

       //optionalCustomer.get().getAddress()!=customerDto.getAddress()?optionalCustomer.get().setAddress(customerDto.getAddress()): continue;

        optionalCustomer.get().setAddress(customerDto.getAddress());
        optionalCustomer.get().setCity(customerDto.getCity());
        optionalCustomer.get().setCustomerId(customerDto.getCustomerId());
        optionalCustomer.get().setFirstName(customerDto.getFirstName());
        optionalCustomer.get().setLastName(customerDto.getLastName());

        customerRepository.save(optionalCustomer.get());

        return customerMapper.toDto(optionalCustomer.get());
    }

}
