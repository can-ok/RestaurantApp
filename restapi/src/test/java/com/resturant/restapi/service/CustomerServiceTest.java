package com.resturant.restapi.service;

import com.resturant.restapi.Model.Customer;
import com.resturant.restapi.Model.Media;
import com.resturant.restapi.builder.CustomerBuilder;
import com.resturant.restapi.builder.MediaBuilder;
import com.resturant.restapi.builder.MediaDtoBuilder;
import com.resturant.restapi.config.MessageSourceExternalizer;
import com.resturant.restapi.converter.CustomerMapper;
import com.resturant.restapi.converter.MediaDtoConverter;
import com.resturant.restapi.dto.CustomerDto;
import com.resturant.restapi.exception.ContentNotAllowed;
import com.resturant.restapi.exception.EntityNotFound;
import com.resturant.restapi.repository.CustomerRepository;
import com.resturant.restapi.repository.MediaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.swing.text.html.parser.Entity;

import static org.junit.Assert.*;


import java.util.*;

import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;
    @Mock
    CustomerMapper customerMapper;
    @Mock
    MediaRepository mediaRepository;
    @Mock
    private MessageSourceExternalizer messageSourceExternalizer;

    @InjectMocks
    CustomerService customerService;

    private Pageable pages= PageRequest.of(0,5);

    private Media media;

    private Customer customer;
    private List<Customer> customerList=new ArrayList<>();
    private CustomerDto customerDto;
    private List<CustomerDto> customerDtos=new ArrayList<>();

    @Before
    public void setUp(){
        byte[] fileBytes = "deneme".getBytes();

        media= new MediaBuilder().fileContent(fileBytes).id(1).name("deneme").build();
        customer=new CustomerBuilder().address("x").city("x").id(1).firstName("deneme").media(media).build();

        customerDto=new CustomerDto(1,"x","x","x","x","531",media);

        customerList.add(customer);
        customerDtos.add(customerDto);

        when(customerMapper.toEntity(customerDto)).thenReturn(customer);
        when(customerMapper.toDto(customer)).thenReturn(customerDto);
        when(customerMapper.toDtoList(any())).thenReturn(customerDtos);
    }

    @Test
    public void getAllCustomers() {
        Page<Customer> customerPage= new PageImpl<>(customerList);
        when(customerRepository.findAll()).thenReturn(customerList);
        when(customerRepository.findAll(pages)).thenReturn(customerPage);
        assertNotNull(customerService.getAllCustomers(0,5));
    }

    @Test
    public void getCustomerByPhoneNumber() {
        Page<Customer> customerPage= new PageImpl<>(customerList);
        when(customerRepository.findAllByPhoneNumber(pages,customerDto.getPhoneNumber())).thenReturn(customerPage);
        Page<CustomerDto> customerByPhoneNumber = customerService.getCustomerByPhoneNumber(0, 5, customerDto);
        assertNotNull(customerByPhoneNumber);
    }

    @Test
    public void insertCustomer() {
        when(mediaRepository.findById(any())).thenReturn(Optional.of(media));
        String result=customerService.insertCustomer(customerDto);

        assertEquals(result,"Success");
    }

//    @Test(expected = ContentNotAllowed.class)
//    public void shoudNotInsertCustomer() {
//        when(mediaRepository.findById(any())).thenReturn(Optional.of(media));
//        String result=customerService.insertCustomer(null);
//
//        assertEquals(result,"Success");
//    }

    @Test
    public void deleteCustomer() {
        when(customerRepository.findById(any())).thenReturn(Optional.of(customer));
        assertEquals(customerService.deleteCustomer(any()),"Success");
    }

    @Test
    public void updateCustomer() {
        when(customerRepository.findById(any())).thenReturn(Optional.of(customer));
        when(mediaRepository.findById(any())).thenReturn(Optional.of(media));

        assertNotNull(customerService.updateCustomer(customerDto));
    }

    @Test(expected = EntityNotFound.class)
    public void shouldNotUpdateCustomer() {
        when(customerRepository.findById(any())).thenReturn(Optional.empty());

        assertNotNull(customerService.updateCustomer(customerDto));
    }


}