package com.resturant.restapi.controller;


import com.resturant.restapi.converter.CustomerDtoConverter;
import com.resturant.restapi.dto.CustomerDto;
import com.resturant.restapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3006"})
@RestController
@RequestMapping(path = "/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/")
    public List<CustomerDto> getCustomers(@RequestParam(value = "page") int page,
                                       @RequestParam(value = "size") int size)
    {
        return CustomerDtoConverter.customerToCustomerDtoList(customerService.getAllCustomers(page,size));
    }


    @GetMapping("/{id}")
    public CustomerDto getCustomer(@PathVariable int id)
    {
        return CustomerDtoConverter.customerToCustomerDto(customerService.getCustomer(id));
    }

    @PostMapping()
    public String saveCustomer(@Valid @RequestBody CustomerDto customerDto){

        return customerService.addCustomer(customerDto);
    }
//
//    @PutMapping
//    public CustomerDto updateCustomer(@Valid @RequestBody CustomerDto customerDto){
//
//        return customerService.updateCustomer(customerDto);
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteCustomer(@NotNull @PathVariable  Integer id){
//        return customerService.deleteCustomer(id);
//    }
//
//    @PostMapping( path = "/byNumber")
//    public Page<CustomerDto> getCustomersbyNumber(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size,
//                                                  @Valid @RequestBody CustomerDto customerDto)
//    {
//        return customerService.getCustomerByPhoneNumber(page,size,customerDto);
//    }
//

//    @GetMapping("/export")
//    public ResponseEntity<Resource> exportToExcel(){
//
//        String filename="customer.xlsx";
//        InputStreamResource file=new InputStreamResource(customerService());
//
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=" + filename)
//                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
//                .body(file);
//
//    }


}
