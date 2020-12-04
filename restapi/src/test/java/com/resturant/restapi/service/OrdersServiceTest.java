package com.resturant.restapi.service;

import com.resturant.restapi.Model.Orders;
import com.resturant.restapi.converter.DtoConverter;
import com.resturant.restapi.converter.OrdersDtoConvert;
import com.resturant.restapi.dto.OrdersDto;
import com.resturant.restapi.repository.OrdersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class OrdersServiceTest {

    @Mock
    OrdersRepository ordersRepository;

    @InjectMocks
    private OrdersService ordersService;

    private OrdersDto ordersDto=new OrdersDto();

    private List<OrdersDto> ordersDtoList=new ArrayList<>();

    @Before
    public void setUp() throws Exception{
        ordersDto.setOrderTable("Masa1");
        ordersDto.setProductId(1);
        ordersDto.setId(1);

        ordersDtoList.add(ordersDto);
    }

    @Test
    public void saveOrder() {
        when(ordersRepository.save(any())).thenReturn(OrdersDtoConvert.ordersDtoToOrders(ordersDto));
        OrdersDto dtoResult=ordersService.saveOrder(ordersDto);

        assertEquals(dtoResult,ordersDto);

    }

    @Test
    public void saveOrders() {
    
        when(ordersRepository.saveAll(any())).thenReturn(OrdersDtoConvert.ordersDtoListToOrderList(ordersDtoList));

        List<OrdersDto> dtoResultList=ordersService.saveOrders(ordersDtoList);

        assertNotNull(dtoResultList);

        assertEquals(dtoResultList,ordersDtoList);
    }

    @Test
    public void getOrders() {

        when(ordersRepository.findAll()).thenReturn(OrdersDtoConvert.ordersDtoListToOrderList(ordersDtoList));

        List<OrdersDto> dtoResultList=ordersService.getOrders();

        //assertTrue(dtoResultList.equals(ordersDtoList)); ?????
       assertEquals(dtoResultList.size(),ordersDtoList.size());


    }
}