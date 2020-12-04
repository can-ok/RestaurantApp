package com.resturant.restapi.converter;

import com.resturant.restapi.Model.Orders;
import com.resturant.restapi.dto.OrdersDto;
import org.aspectj.weaver.ast.Or;
import org.hibernate.criterion.Order;

import java.util.ArrayList;
import java.util.List;

public class OrdersDtoConvert {


    public static Orders ordersDtoToOrders(OrdersDto ordersDto){

        Orders orders=new Orders();

        orders.setId(ordersDto.getId()); //It is AutoIncrement
        orders.setOrderTable(ordersDto.getOrderTable());
        orders.setOrderDate(ordersDto.getOrderDate());
        orders.setPaymentType(ordersDto.getPaymentType());
        orders.setProductCount(ordersDto.getProductCount());
        orders.setTotalPrice(ordersDto.getTotalPrice());
        orders.setProductId(ordersDto.getProductId());
        return orders;
    }



    public static List<Orders> ordersDtoListToOrderList(List<OrdersDto> ordersListDtos){

        List<Orders> ordersList=new ArrayList<>();
        for(OrdersDto ordersDto:ordersListDtos){

            Orders orders=new Orders();
            orders.setId(ordersDto.getId()); //It is AutoIncrement
            orders.setOrderTable(ordersDto.getOrderTable());
            orders.setOrderDate(ordersDto.getOrderDate());
            orders.setPaymentType(ordersDto.getPaymentType());
            orders.setProductCount(ordersDto.getProductCount());
            orders.setTotalPrice(ordersDto.getTotalPrice());
            orders.setProductId(ordersDto.getProductId());

            ordersList.add(orders);
        }

        return ordersList;
    }


    public static List<OrdersDto> ordersListToOrdersDtoList(List<Orders> ordersList){

        List<OrdersDto> odersDtoList=new ArrayList<>();

        for(Orders entity:ordersList){

            OrdersDto ordersDto=new OrdersDto();

            ordersDto.setId(entity.getId());
            ordersDto.setOrderDate(entity.getOrderDate());
            ordersDto.setOrderTable(entity.getOrderTable());
            ordersDto.setPaymentType(entity.getPaymentType());
            ordersDto.setProductCount(entity.getProductCount());
            ordersDto.setProductId(entity.getProductId());
            ordersDto.setTotalPrice(entity.getTotalPrice());

            odersDtoList.add(ordersDto);
        }

        return odersDtoList;
    }

}
