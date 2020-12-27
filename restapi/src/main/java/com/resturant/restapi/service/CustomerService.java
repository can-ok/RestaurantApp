package com.resturant.restapi.service;

import com.resturant.restapi.Model.Customer;
import com.resturant.restapi.converter.CustomerMapper;
import com.resturant.restapi.dto.CustomerDto;
import com.resturant.restapi.exception.ContentNotAllowed;
import com.resturant.restapi.exception.EntityNotFound;
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

    public Page<CustomerDto> getCustomerByPhoneNumber(int pageCount,int pageSize,CustomerDto customerDto){

        Pageable pages= PageRequest.of(pageCount,pageSize);
        Page<CustomerDto> customers=customerRepository.findAllByPhoneNumber(pages,customerDto.getPhoneNumber()).map(customerMapper::toDto);

        return customers;
    }

    public String insertCustomer(CustomerDto customerDto){
        if(customerDto.equals(null) || customerDto.getId()<1) {
           throw new ContentNotAllowed("Content not Allowed");
        }

        Customer customer=customerMapper.toEntity(customerDto);

        customerRepository.save(customer);
        return "Success";
    }

    public String deleteCustomer(Integer id){
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if(!customerOptional.isPresent()){
            throw new EntityNotFound("Entity not Found");
        }
        customerRepository.delete(customerOptional.get());
        return "Success";
    }

    public CustomerDto updateCustomer(CustomerDto customerDto){

        if(customerDto.equals(null) || customerDto.getId()==null){
            throw new ContentNotAllowed("Content Not Allowed");
        }

        Optional<Customer> optionalCustomer = customerRepository.findById(customerDto.getId());
        if(!optionalCustomer.isPresent()){
            throw new EntityNotFound("Entity not Found");
        }


        optionalCustomer.get().setAddress(customerDto.getAddress());
        optionalCustomer.get().setCity(customerDto.getCity());
        optionalCustomer.get().setId(customerDto.getId());
        optionalCustomer.get().setFirstName(customerDto.getFirstName());
        optionalCustomer.get().setLastName(customerDto.getLastName());

        customerRepository.save(optionalCustomer.get());

        return customerMapper.toDto(optionalCustomer.get());
    }

}
