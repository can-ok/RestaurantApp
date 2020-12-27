package com.resturant.restapi.service;

import com.resturant.restapi.Model.Orders;
import com.resturant.restapi.converter.OrdersDtoConverter;
import com.resturant.restapi.converter.OrdersMapper;
import com.resturant.restapi.dto.OrdersDto;
import com.resturant.restapi.exception.ContentNotAllowed;
import com.resturant.restapi.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    OrdersMapper ordersMapper;

    public OrdersDto saveOrder(OrdersDto ordersDto){
        if(ordersDto.equals(null)&&ordersDto.getId()<-1){
            throw new ContentNotAllowed("Content Not allowed");
        }

        Orders orders= ordersMapper.toEntity(ordersDto);
        ordersRepository.save(orders);
        return ordersDto;
    }

    public List<OrdersDto> saveOrders(List<OrdersDto> ordersDto){

        if(ordersDto.size()<0){
            throw new ContentNotAllowed("Content Not allowed");
        }

        List<Orders> ordersList= ordersMapper.toEntityList(ordersDto);

        ordersRepository.saveAll(ordersList);

        return ordersDto;
    }

    public List<OrdersDto> getOrders(){

        List<OrdersDto> ordersDtoList= ordersMapper.toDtoList(ordersRepository.findAll());

        return ordersDtoList;
    }

}


