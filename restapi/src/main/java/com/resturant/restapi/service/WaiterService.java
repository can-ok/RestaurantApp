package com.resturant.restapi.service;

import com.resturant.restapi.Model.Media;
import com.resturant.restapi.Model.Waiter;
import com.resturant.restapi.converter.WaiterDtoConverter;
import com.resturant.restapi.dto.WaiterDto;
import com.resturant.restapi.repository.MediaRepository;
import com.resturant.restapi.repository.WaiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WaiterService {

    @Autowired
    private WaiterRepository waiterRepository;

    @Autowired
    private MediaRepository mediaRepository;

    public List<WaiterDto> getAllWaiters(){

        return WaiterDtoConverter.waiterListToWaiterDtoList( waiterRepository.findAll());
    }


    public WaiterDto insert(WaiterDto waiterDto){
        if(waiterDto!=null){

            Media media=mediaRepository.findById(waiterDto.getMedia().getId()).get();
            Waiter waiter=WaiterDtoConverter.waiterDtoToWaiter(waiterDto);
            waiter.setMedia(media);
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
            Media media=mediaRepository.findById(waiterDto.getMedia().getId()).get();

            waiterOptional.get().setFirstname(waiterDto.getFirstname());
            waiterOptional.get().setId(id);
            waiterOptional.get().setEmail(waiterDto.getEmail());
            waiterOptional.get().setLastname(waiterDto.getLastname());
            waiterOptional.get().setBirtdate(waiterDto.getBirtdate());
            waiterOptional.get().setMedia(media);
            waiterRepository.save(waiterOptional.get());

            return waiterDto;
        }

    }



}
