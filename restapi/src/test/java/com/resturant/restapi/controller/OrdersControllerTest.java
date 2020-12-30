package com.resturant.restapi.controller;

import com.resturant.restapi.Model.Orders;
import com.resturant.restapi.dto.OrdersDto;
import com.resturant.restapi.service.OrdersService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class OrdersControllerTest {

    @Mock
    private OrdersService ordersService;

    @InjectMocks
    private OrdersController ordersController;

    List<OrdersDto> ordersList=new ArrayList<>();
    OrdersDto ordersDto=new OrdersDto();

    @Before
    public void setUpOrdersList(){
        ordersDto.setOrderTable("Masa1");
        ordersDto.setProductId(1);
        ordersDto.setId(1);

        ordersList.add(ordersDto);

    }

    @Test
    public void getAllOrders() {

        when(ordersService.getOrders()).thenReturn(ordersList);
        List<OrdersDto> resultList=ordersController.getAllOrders();
        assertEquals(resultList,ordersList);
    }

    @Test
    public void saveOrder() {

        when(ordersService.saveOrder(any())).thenReturn(ordersDto);

        OrdersDto resultDto=ordersController.saveOrder(ordersDto);

        assertEquals(resultDto,ordersDto);
    }

    @Test
    public void saveOrders() {

        when(ordersService.saveOrders(any())).thenReturn(ordersList);
        List<OrdersDto> resultList=ordersController.saveOrders(ordersList);
        assertEquals(resultList,ordersList);
    }
}