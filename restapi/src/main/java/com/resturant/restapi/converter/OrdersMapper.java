package com.resturant.restapi.converter;

import com.resturant.restapi.Model.Customer;
import com.resturant.restapi.Model.Orders;
import com.resturant.restapi.dto.CustomerDto;
import com.resturant.restapi.dto.OrdersDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrdersMapper {

    OrdersDto toDto(Orders orders);

    Orders toEntity(OrdersDto ordersDto);

    List<Orders> toEntityList(List<OrdersDto> ordersDtoList);
}
