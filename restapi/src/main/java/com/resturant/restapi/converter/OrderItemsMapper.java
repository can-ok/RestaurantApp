package com.resturant.restapi.converter;

import com.resturant.restapi.Model.OrderItems;
import com.resturant.restapi.Model.Orders;
import com.resturant.restapi.dto.OrderItemsDto;
import com.resturant.restapi.dto.OrdersDto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface OrderItemsMapper {

    //@Mapping(ignore = true,source = "orderId",target = "orderId")
    OrderItemsDto toDto(OrderItems orderItems);

    OrderItems toEntity(OrderItemsDto orderItemsDto);

    List<OrderItems> toEntityList(List<OrderItemsDto> ordersDtoList);

    Set<OrderItems> toEntitySet(Set<OrderItemsDto> ordersDtoList);

    List<OrdersDto> toDtoList(List<Orders> ordersList);
}
