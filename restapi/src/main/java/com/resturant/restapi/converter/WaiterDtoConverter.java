package com.resturant.restapi.converter;

import com.resturant.restapi.Model.Waiter;
import com.resturant.restapi.dto.WaiterDto;

import java.util.ArrayList;
import java.util.List;

public class WaiterDtoConverter {


    public static WaiterDto waiterToWaiterDto(Waiter waiter){
        WaiterDto waiterDto=new WaiterDto();
        waiterDto.setId(waiter.getId());
        waiterDto.setName(waiter.getName());

        return waiterDto;
    }

    public static List<WaiterDto> waiterListToWaiterDtoList(List<Waiter> listWaiter){

        List<WaiterDto> waiterDtoList=new ArrayList<>();
        listWaiter.forEach(waiter ->{

            WaiterDto waiterDto=waiterToWaiterDto(waiter);

            waiterDtoList.add(waiterDto);
        } );

        return waiterDtoList;
    }

    public static Waiter waiterDtoToWaiter(WaiterDto waiterDto){
        Waiter waiter=new Waiter();

        waiter.setId(waiterDto.getId());
        waiter.setName(waiterDto.getName());

        return waiter;
    }




}
