package com.resturant.restapi.service;

import com.resturant.restapi.Model.Waiter;
import com.resturant.restapi.converter.WaiterDtoConverter;
import com.resturant.restapi.dto.WaiterDto;
import com.resturant.restapi.repository.WaiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WaiterService {

    @Autowired
    private WaiterRepository waiterRepository;


    public List<WaiterDto> getAllWaiters(){

        return WaiterDtoConverter.waiterListToWaiterDtoList( waiterRepository.findAll());
    }


    public WaiterDto insert(WaiterDto waiterDto){

        if(waiterDto!=null){
            Waiter waiter=WaiterDtoConverter.waiterDtoToWaiter(waiterDto);
            waiterRepository.save(waiter);
            return waiterDto;
        }
        else{
            return null;
        }
    }

    public String delete(Integer id){

        Optional<Waiter> waiterOptional=waiterRepository.findById(id);

        if(waiterOptional.isPresent()){
            waiterRepository.delete(waiterOptional.get());
            return "Success";
        }
        return "Fail";
    }


    public WaiterDto updateWaiter(Integer id,WaiterDto waiterDto){
        Optional<Waiter> waiterOptional=waiterRepository.findById(id);

        if(!waiterOptional.isPresent()){
            System.out.println("Sonuç bulnamadı");
            return null;
        }
        else {

            waiterOptional.get().setName(waiterDto.getName());
            waiterOptional.get().setId(id);
            waiterRepository.save(waiterOptional.get());

            return waiterDto;
        }

    }



}
