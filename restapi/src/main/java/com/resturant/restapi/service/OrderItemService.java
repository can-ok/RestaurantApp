package com.resturant.restapi.service;

import com.resturant.restapi.Model.OrderItems;
import com.resturant.restapi.Model.Orders;
import com.resturant.restapi.converter.OrderItemsMapper;
import com.resturant.restapi.converter.OrdersMapper;
import com.resturant.restapi.dto.OrderItemsDto;
import com.resturant.restapi.dto.OrdersDto;
import com.resturant.restapi.repository.OrderItemRepository;
import com.resturant.restapi.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class OrderItemService  {


    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderItemsMapper orderItemsMapper;


    @Transactional
    public String saveOrderItems(Set<OrderItemsDto> orderItemsDtoList){

        Set<OrderItems> orderItems=orderItemsMapper.toEntitySet(orderItemsDtoList);

        orderItemRepository.saveAll(orderItems);

        return "Success";
    }

}
