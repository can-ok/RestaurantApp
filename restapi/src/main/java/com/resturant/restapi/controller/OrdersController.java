package com.resturant.restapi.controller;

import com.resturant.restapi.Model.Orders;
import com.resturant.restapi.service.OrdersService;
import com.resturant.restapi.service.ProductsService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3006"})
@RestController
@RequestMapping(path = "/orders")
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @GetMapping("/getOrders")
    public List<Orders> getAllOrders(){

        return ordersService.getOrders();
    }

    @PostMapping("/saveOrder")
    public Orders saveOrder(@RequestBody Orders orders){

        return ordersService.saveOrder(orders);
    }

    @PostMapping("/saveOrders")
    public List<Orders> saveOrders(@RequestBody List<Orders> orders){

        System.out.println(orders);

        return ordersService.saveOrders(orders);

    }

}
