package com.resturant.restapi.service;

import com.resturant.restapi.Model.Orders;
import com.resturant.restapi.repository.OrdersRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    @Autowired
    OrdersRepository ordersRepository;


    public Orders saveOrder(Orders order){

        return ordersRepository.save(order);
    }

    public List<Orders> saveOrders(List<Orders> orders){

        return  ordersRepository.saveAll(orders);
    }

    public List<Orders> getOrders(){

        return ordersRepository.findAll();
    }

}


