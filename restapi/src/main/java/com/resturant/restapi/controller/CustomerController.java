package com.resturant.restapi.controller;


import com.resturant.restapi.Model.Customer;
import com.resturant.restapi.dto.CustomerDto;
import com.resturant.restapi.service.CustomerService;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.InputStream;


@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3006"})
@RestController
@RequestMapping(path = "/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/")
    public Page<CustomerDto> getCustomers(@RequestParam(value = "page") int page,
                                          @RequestParam(value = "size") int size)
    {

        return customerService.getAllCustomers(page,size);
    }

    @PostMapping
    public String saveCustomer(@Valid @RequestBody CustomerDto customerDto){

        return customerService.insertCustomer(customerDto);
    }

    @PutMapping
    public CustomerDto updateCustomer(@Valid @RequestBody CustomerDto customerDto){

        return customerService.updateCustomer(customerDto);
    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@NotNull @PathVariable  Integer id){
        return customerService.deleteCustomer(id);
    }

    @PostMapping( path = "/byNumber")
    public Page<CustomerDto> getCustomersbyNumber(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size,
                                                  @Valid @RequestBody CustomerDto customerDto)
    {
        return customerService.getCustomerByPhoneNumber(page,size,customerDto);
    }


    @GetMapping("/export")
    public ResponseEntity<Resource> exportToExcel(){

        String filename="customer.xlsx";
        InputStreamResource file=new InputStreamResource(customerService.loadXML());

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);

    }


}
