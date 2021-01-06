package com.resturant.restapi.service;


import com.resturant.restapi.Model.Orders;
import com.resturant.restapi.Model.Waiter;
import com.resturant.restapi.converter.OrdersMapper;
import com.resturant.restapi.dto.OrderDetailsDto;
import com.resturant.restapi.dto.OrdersDto;
import com.resturant.restapi.repository.CustomerRepository;
import com.resturant.restapi.repository.OrdersRepository;
import com.resturant.restapi.repository.WaiterRepository;
import com.resturant.restapi.stub.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    CustomerService customerService;

    @Transactional
    public OrderDetailsDto saveOrders(OrderDetailsDto ordersDetailsDto){

        OrdersDto ordersDto=ordersDetailsDto.getOrdersDto();

        Orders order=ordersMapper.toEntity(ordersDto);

        if(ordersDetailsDto.getWaiterId()!=null){
            Optional<Waiter> waiterByID = waiterRepository.findById(ordersDetailsDto.getWaiterId());
            if(waiterByID.isPresent()){
                order.setWaiterId(waiterByID.get());
            }
        }


        if(ordersDetailsDto.getCustomerId()!=null){

            Customer customer =customerService.getCustomer(ordersDetailsDto.getCustomerId());
            if(customer!=null){
                order.setCustomerId(customer.getId());

            }

        }


        ordersRepository.save(order);

        ordersDetailsDto.getOrderItemsDtoList().forEach(orderItemsDto -> {
            orderItemsDto.setOrderId(order.getId());
        });

        orderItemService.saveOrderItems(ordersDetailsDto.getOrderItemsDtoList());

        return ordersDetailsDto;
    }
}
