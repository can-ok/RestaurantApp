package com.resturant.restapi.service;

import com.resturant.restapi.builder.OrdersDtoBuilder;
import com.resturant.restapi.converter.OrdersDtoConverter;
import com.resturant.restapi.dto.OrdersDto;
import com.resturant.restapi.repository.OrdersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrdersServiceTest {

    @Mock
    OrdersRepository ordersRepository;

    @InjectMocks
    private OrdersService ordersService;

    private OrdersDto ordersDto;

    private List<OrdersDto> ordersDtoList=new ArrayList<>();

    @Before
    public void setUp() throws Exception{
        ordersDto=new OrdersDtoBuilder().id(1).paymentType("cash").orderTable("Masa 1").productCount(5)
                .totalPrice(100).productId(1).waiterId("1").build();

        ordersDtoList.add(ordersDto);
    }

    @Test
    public void saveOrder() {
        when(ordersRepository.save(any())).thenReturn(OrdersDtoConverter.ordersDtoToOrders(ordersDto));
        OrdersDto dtoResult=ordersService.saveOrder(ordersDto);

        assertEquals(dtoResult,ordersDto);

    }

    @Test
    public void saveOrders() {
    
        when(ordersRepository.saveAll(any())).thenReturn(OrdersDtoConverter.ordersDtoListToOrderList(ordersDtoList));

        List<OrdersDto> dtoResultList=ordersService.saveOrders(ordersDtoList);

        assertNotNull(dtoResultList);

        assertEquals(dtoResultList,ordersDtoList);
    }

    @Test
    public void getOrders() {

        when(ordersRepository.findAll()).thenReturn(OrdersDtoConverter.ordersDtoListToOrderList(ordersDtoList));

        List<OrdersDto> dtoResultList=ordersService.getOrders();

        //assertTrue(dtoResultList.equals(ordersDtoList)); ?????
       assertEquals(dtoResultList.size(),ordersDtoList.size());


    }
}