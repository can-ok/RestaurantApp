package com.resturant.restapi.controller;

import com.resturant.restapi.Model.Orders;
import com.resturant.restapi.dto.OrdersDto;
import com.resturant.restapi.service.OrdersService;
import com.resturant.restapi.service.ProductsService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3006"})
@RestController
@RequestMapping(path = "/orders")
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @GetMapping("/getOrders")
    public List<OrdersDto> getAllOrders(){

        return ordersService.getOrders();
    }

    @PostMapping("/saveOrder")
    public OrdersDto saveOrder(@Valid @RequestBody OrdersDto ordersDto){

        return ordersService.saveOrder(ordersDto);
    }

    @PostMapping("/saveOrders")
    public List<OrdersDto> saveOrders(@Valid @RequestBody List<OrdersDto> ordersDto){

        System.out.println(ordersDto);

        return ordersService.saveOrders(ordersDto);

    }

}
