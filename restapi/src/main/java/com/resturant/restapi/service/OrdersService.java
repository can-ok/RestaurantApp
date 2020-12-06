package com.resturant.restapi.service;

import com.resturant.restapi.Model.Orders;
import com.resturant.restapi.converter.OrdersDtoConverter;
import com.resturant.restapi.dto.OrdersDto;
import com.resturant.restapi.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    @Autowired
    OrdersRepository ordersRepository;


    public OrdersDto saveOrder(OrdersDto ordersDto){

        Orders orders= OrdersDtoConverter.ordersDtoToOrders(ordersDto);
        ordersRepository.save(orders);
        return ordersDto;
    }

    public List<OrdersDto> saveOrders(List<OrdersDto> ordersDto){

        List<Orders> ordersList= OrdersDtoConverter.ordersDtoListToOrderList(ordersDto);

        ordersRepository.saveAll(ordersList);

        return ordersDto;
    }

    public List<OrdersDto> getOrders(){

        List<OrdersDto> ordersDtoList= OrdersDtoConverter.ordersListToOrdersDtoList(ordersRepository.findAll());

        return ordersDtoList;
    }

}


