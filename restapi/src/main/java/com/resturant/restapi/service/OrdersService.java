package com.resturant.restapi.service;

import com.resturant.restapi.Model.Orders;
import com.resturant.restapi.converter.DtoConverter;
import com.resturant.restapi.converter.OrdersDtoConvert;
import com.resturant.restapi.dto.OrdersDto;
import com.resturant.restapi.repository.OrdersRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    @Autowired
    OrdersRepository ordersRepository;


    public OrdersDto saveOrder(OrdersDto ordersDto){

        Orders orders= OrdersDtoConvert.ordersDtoToOrders(ordersDto);
        ordersRepository.save(orders);
        return ordersDto;
    }

    public List<OrdersDto> saveOrders(List<OrdersDto> ordersDto){

        List<Orders> ordersList= OrdersDtoConvert.ordersDtoListToOrderList(ordersDto);

        ordersRepository.saveAll(ordersList);

        return ordersDto;
    }

    public List<OrdersDto> getOrders(){

        List<OrdersDto> ordersDtoList= OrdersDtoConvert.ordersListToOrdersDtoList(ordersRepository.findAll());

        return ordersDtoList;
    }

}


