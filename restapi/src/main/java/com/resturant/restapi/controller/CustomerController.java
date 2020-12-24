package com.resturant.restapi.controller;


import com.resturant.restapi.Model.Customer;
import com.resturant.restapi.dto.CustomerDto;
import com.resturant.restapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3006"})
@RestController
@RequestMapping(path = "/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/")
    public Page<CustomerDto> getCustomers(@RequestParam(value = "page") int page,
                                          @RequestParam(value = "size") int size){

        return customerService.getAllCustomers(page,size);
    }

    @PostMapping
    public String saveCustomer(@RequestBody CustomerDto customerDto){

        return customerService.insertCustomer(customerDto);
    }

    @PutMapping
    public CustomerDto updateCustomer(@RequestBody CustomerDto customerDto){

        return customerService.updateCustomer(customerDto);
    }

    @DeleteMapping
    public String deleteCustomer(Integer id){
        return customerService.deleteCustomer(id);
    }

}
