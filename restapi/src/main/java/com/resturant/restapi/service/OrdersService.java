package com.resturant.restapi.service;

import com.resturant.restapi.Model.Orders;
import com.resturant.restapi.converter.OrdersDtoConverter;
import com.resturant.restapi.converter.OrdersMapper;
import com.resturant.restapi.dto.OrdersDto;
import com.resturant.restapi.exception.ContentNotAllowed;
import com.resturant.restapi.exception.EntityNotFound;
import com.resturant.restapi.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    OrdersMapper ordersMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public OrdersDto saveOrder(OrdersDto ordersDto){

        Orders orders= ordersMapper.toEntity(ordersDto);
        ordersRepository.save(orders);
        return ordersDto;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<OrdersDto> saveOrders(List<OrdersDto> ordersDto){

        List<Orders> ordersList= ordersMapper.toEntityList(ordersDto);
        ordersRepository.saveAll(ordersList);
        return ordersDto;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public String deleteOrder(Integer id){

        Optional<Orders> byId = ordersRepository.findById(id);
        if (!byId.isPresent()){
            throw new EntityNotFound("Entity Not Found");
        }
        ordersRepository.delete(byId.get());
        return "Success";
    }


    public List<OrdersDto> getOrders(){

        List<OrdersDto> ordersDtoList= ordersMapper.toDtoList(ordersRepository.findAll());

        return ordersDtoList;
    }

}


