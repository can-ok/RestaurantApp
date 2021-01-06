package com.resturant.restapi.converter;

import com.resturant.restapi.Model.Orders;
import com.resturant.restapi.dto.OrdersDto;

import java.util.ArrayList;
import java.util.List;

public class OrdersDtoConverter {


    public static Orders ordersDtoToOrders(OrdersDto ordersDto){
        Orders orders=new Orders();

        orders.setId(ordersDto.getId()); //It is AutoIncrement
        orders.setOrderTable(ordersDto.getOrderTable());
        orders.setOrderDate(ordersDto.getOrderDate());
        orders.setPaymentType(ordersDto.getPaymentType());
        orders.setTotalPrice(ordersDto.getTotalPrice());
        orders.setWaiterId(ordersDto.getWaiterId());
        return orders;
    }



    public static List<Orders> ordersDtoListToOrderList(List<OrdersDto> ordersListDtos){

        List<Orders> ordersList=new ArrayList<>();

        ordersListDtos.stream().forEach(ordersDto -> {
            Orders orders=ordersDtoToOrders(ordersDto);
            ordersList.add(orders);
        });

        return ordersList;
    }

    public static OrdersDto orderToOrdersDto(Orders orders){
        OrdersDto ordersDto=new OrdersDto();
        ordersDto.setId(orders.getId());
        ordersDto.setOrderDate(orders.getOrderDate());
        ordersDto.setOrderTable(orders.getOrderTable());
        ordersDto.setPaymentType(orders.getPaymentType());

        ordersDto.setTotalPrice(orders.getTotalPrice());
        ordersDto.setWaiterId(orders.getWaiterId());

        return ordersDto;
    }


    public static List<OrdersDto> ordersListToOrdersDtoList(List<Orders> ordersList){

        List<OrdersDto> odersDtoList=new ArrayList<>();

        ordersList.stream().forEach(entity->{
            OrdersDto ordersDto=orderToOrdersDto(entity);
            odersDtoList.add(ordersDto);

        });

        return odersDtoList;
    }

}
