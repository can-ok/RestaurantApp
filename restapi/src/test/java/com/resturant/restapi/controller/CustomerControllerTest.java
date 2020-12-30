package com.resturant.restapi.controller;

import com.resturant.restapi.Model.Customer;
import com.resturant.restapi.Model.Media;
import com.resturant.restapi.builder.CustomerBuilder;
import com.resturant.restapi.builder.MediaBuilder;
import com.resturant.restapi.dto.CustomerDto;
import com.resturant.restapi.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import javax.persistence.ManyToOne;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)

public class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    List<CustomerDto> customerDtos=new ArrayList<>();

    private Media media;

    private Customer customer;

    private CustomerDto customerDto;

    @Before
    public void setUpWaitersList(){
        byte[] fileBytes = "deneme".getBytes();

        media= new MediaBuilder().fileContent(fileBytes).id(1).name("deneme").build();
        customer=new CustomerBuilder().address("x").city("x").id(1).firstName("deneme").media(media).build();

        customerDto=new CustomerDto(1,"x","x","x","x","531",media);

        customerDtos.add(customerDto);

    }

    @Test
    public void getCustomers() {
        Page<CustomerDto> customerDtoPage=new PageImpl<>(customerDtos);

        when(customerService.getAllCustomers(0,5)).thenReturn(customerDtoPage);

        assertNotNull(customerController.getCustomers(0,5));
    }

    @Test
    public void saveCustomer() {

        when(customerService.insertCustomer(any())).thenReturn("Success");
        assertEquals(customerController.saveCustomer(customerDto),"Success");
    }

    @Test
    public void updateCustomer() {
        when(customerService.updateCustomer(any())).thenReturn(customerDto);
        assertEquals(customerController.updateCustomer(customerDto).getId(),customerDto.getId());
    }

    @Test
    public void deleteCustomer() {
        when(customerService.deleteCustomer(any())).thenReturn("Success");
        assertEquals(customerController.deleteCustomer(1),"Success");
    }

    @Test
    public void getCustomersbyNumber() {
        Page<CustomerDto> customerDtoPage=new PageImpl<>(customerDtos);
        when(customerService.getCustomerByPhoneNumber(0,5,customerDto)).thenReturn(customerDtoPage);

        assertEquals(customerDtoPage.getTotalElements(),customerDtos.size());
    }
}