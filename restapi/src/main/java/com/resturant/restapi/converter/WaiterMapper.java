package com.resturant.restapi.converter;

import com.resturant.restapi.Model.Waiter;
import com.resturant.restapi.dto.WaiterDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WaiterMapper {

//    WaiterMapper INSTANCE= Mappers.getMapper(WaiterMapper.class);

    WaiterDto toDto(Waiter waiter);

    Waiter toWaiter(WaiterDto waiterDto);

}
