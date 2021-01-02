package com.resturant.restapi.service;


import com.resturant.restapi.Model.Customer;
import com.resturant.restapi.Model.Media;
import com.resturant.restapi.Model.Orders;
import com.resturant.restapi.Model.Waiter;
import com.resturant.restapi.converter.OrdersMapper;
import com.resturant.restapi.dto.OrderDetailsDto;
import com.resturant.restapi.dto.OrdersDto;
import com.resturant.restapi.exception.EntityNotFound;
import com.resturant.restapi.repository.CustomerRepository;
import com.resturant.restapi.repository.OrdersRepository;
import com.resturant.restapi.repository.WaiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Order;
import java.util.Optional;

@Service
public class OrderDetailService {

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    OrdersService ordersService;

    @Autowired
    OrdersMapper ordersMapper;

    @Autowired
    WaiterRepository waiterRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    public OrderDetailsDto saveOrders(OrderDetailsDto ordersDetailsDto){

        OrdersDto ordersDto=ordersDetailsDto.getOrdersDto();

        Orders order=ordersMapper.toEntity(ordersDto);

        Optional<Waiter> waiterByID = waiterRepository.findById(ordersDetailsDto.getWaiterId());
        if(waiterByID.isPresent()){
            order.setWaiterId(waiterByID.get());
        }

        Optional<Customer> customerById= customerRepository.findById(ordersDetailsDto.getCustomerId());
        if(customerById.isPresent()){
            order.setCustomerId(customerById.get());
        }

        ordersRepository.save(order);

        ordersDetailsDto.getOrderItemsDtoList().forEach(orderItemsDto -> {
            orderItemsDto.setOrderId(order.getId());
        });

        orderItemService.saveOrderItems(ordersDetailsDto.getOrderItemsDtoList());

        return ordersDetailsDto;
    }
}
