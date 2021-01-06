package com.resturant.restapi.service;

import com.resturant.restapi.Model.Media;
import com.resturant.restapi.Model.Waiter;
import com.resturant.restapi.converter.WaiterMapper;
import com.resturant.restapi.dto.WaiterDto;
import com.resturant.restapi.exception.EntityNotFound;
import com.resturant.restapi.repository.MediaRepository;
import com.resturant.restapi.repository.WaiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class WaiterService {

    @Autowired
    private WaiterRepository waiterRepository;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private  WaiterMapper waiterMapper;


    public List<WaiterDto> getAllWaiters(){

//        List<WaiterDto> waiterDtoList=new ArrayList<>();
//        waiterRepository.findAll().forEach(waiter ->{
//
//            WaiterDto waiterDto=waiterMapper.toDto(waiter);
//
//            waiterDtoList.add(waiterDto);
//        } );

        List<WaiterDto> waiterDtoList=waiterMapper.waiterDtoList(waiterRepository.findAll());

        return waiterDtoList;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public WaiterDto insert(WaiterDto waiterDto){

        Waiter waiter= waiterMapper.toWaiter(waiterDto);
        Optional<Media> byId = mediaRepository.findById(waiterDto.getMedia().getId());
        if(!byId.isPresent()){
            throw new EntityNotFound("Media Not Found");
        }
        waiter.setMedia(byId.get());
        waiterRepository.save(waiter);
        return waiterDto;


    }

    @Transactional(propagation = Propagation.REQUIRED)
    public String delete(Integer id){

        Optional<Waiter> waiterOptional=waiterRepository.findById(id);

        if(waiterOptional.isPresent()){
            waiterRepository.delete(waiterOptional.get());
            return "Success";
        }
        return "Fail";
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public WaiterDto updateWaiter(Integer id,WaiterDto waiterDto){
        Optional<Waiter> waiterOptional=waiterRepository.findById(id);

        if(!waiterOptional.isPresent()){
            throw new EntityNotFound("Waiter Not Found");

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
