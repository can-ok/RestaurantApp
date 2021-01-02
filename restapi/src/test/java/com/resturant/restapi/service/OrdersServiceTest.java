//package com.resturant.restapi.service;
//
//import com.resturant.restapi.Model.Orders;
//import com.resturant.restapi.builder.OrdersBuilder;
//import com.resturant.restapi.builder.OrdersDtoBuilder;
//import com.resturant.restapi.converter.OrdersDtoConverter;
//import com.resturant.restapi.converter.OrdersMapper;
//import com.resturant.restapi.dto.OrdersDto;
//import com.resturant.restapi.repository.OrdersRepository;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.mockito.Matchers.any;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//public class OrdersServiceTest {
//
//    @Mock
//    OrdersRepository ordersRepository;
//
//    @Mock
//    OrdersMapper ordersMapper;
//
//    @InjectMocks
//    private OrdersService ordersService;
//
//    private OrdersDto ordersDto;
//
//    private Orders orders;
//
//    private List<OrdersDto> ordersDtoList=new ArrayList<>();
//    private List<Orders> orderList=new ArrayList<>();
//
//    @Before
//    public void setUp() throws Exception{
//        ordersDto=new OrdersDtoBuilder().id(1).paymentType("cash").orderTable("Masa 1").productCount(5)
//                .totalPrice(100).productId(1).waiterId("1").build();
//
//        orders=new OrdersBuilder().id(1).paymentType("cash").orderTable("Masa 1").productCount(5)
//                .totalPrice(100).productId(1).waiterId("1").build();
//
//        ordersDtoList.add(ordersDto);
//        orderList.add(orders);
//
//        when(ordersMapper.toDtoList(orderList)).thenReturn(ordersDtoList);
//        //when(ordersMapper.toDtoList(any())).
//    }
//
//    @Test
//    public void saveOrder() {
//        when(ordersRepository.save(any())).thenReturn(OrdersDtoConverter.ordersDtoToOrders(ordersDto));
//        OrdersDto dtoResult=ordersService.saveOrder(ordersDto);
//
//        assertEquals(dtoResult,ordersDto);
//
//    }
//
//    @Test
//    public void saveOrders() {
//
//        when(ordersRepository.saveAll(any())).thenReturn(OrdersDtoConverter.ordersDtoListToOrderList(ordersDtoList));
//
//        List<OrdersDto> dtoResultList=ordersService.saveOrders(ordersDtoList);
//
//        assertNotNull(dtoResultList);
//
//        assertEquals(dtoResultList,ordersDtoList);
//    }
//
//    @Test
//    public void getOrders() {
//
//        when(ordersRepository.findAll()).thenReturn(orderList);
//
//        List<OrdersDto> dtoResultList=ordersService.getOrders();
//
//        assertNotNull(dtoResultList);
//        assertEquals(dtoResultList.size(),ordersDtoList.size());
//    }
//
//    @Test
//    public void deleteOrder(){
//        int id=1;
//        when(ordersRepository.findById(id)).thenReturn(Optional.of(orders));
//
//        String result=ordersService.deleteOrder(id);
//
//        assertEquals(result,"Success");
//
//    }
//
//}