package com.resturant.restapi.converter;

import com.resturant.restapi.Model.Waiter;
import com.resturant.restapi.dto.WaiterDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class WaiterDtoConverter {




    public  static WaiterDto waiterToWaiterDto(Waiter waiter){
        WaiterDto waiterDto=new WaiterDto();
        waiterDto.setId(waiter.getId());
        waiterDto.setFirstname(waiter.getFirstname());
        waiterDto.setLastname(waiter.getLastname());
        waiterDto.setBirtdate(waiter.getBirtdate());
        waiterDto.setEmail(waiter.getEmail());
        waiterDto.setMedia(waiter.getMedia());

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
        waiter.setFirstname(waiterDto.getFirstname());
        waiter.setLastname(waiterDto.getLastname());
        waiter.setBirtdate(waiterDto.getBirtdate());
        waiter.setEmail(waiterDto.getEmail());
        //waiter.setMedia(waiterDto.getMedia());

        return waiter;
    }

    public static List<Waiter> waiterDtoListToWaiterList(List<WaiterDto> waiterDtoList){

        List<Waiter> waiterList=new ArrayList<>();

        waiterDtoList.forEach(element->{

            Waiter waiter=waiterDtoToWaiter(element);

            waiterList.add(waiter);
        });
        return waiterList;
    }




}
