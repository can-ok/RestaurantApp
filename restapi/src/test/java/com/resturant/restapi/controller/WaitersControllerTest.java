package com.resturant.restapi.controller;

import com.resturant.restapi.dto.WaiterDto;
import com.resturant.restapi.service.WaiterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class WaitersControllerTest {

    @Mock
    WaiterService waiterService;

    @InjectMocks
    WaitersController waitersController;


    List<WaiterDto> waiterDtoList=new ArrayList<>();
    WaiterDto waiterDto=new WaiterDto();

    @Before
    public void setUpWaitersList(){
        waiterDto.setFirstname("waiter_deneöme");
        waiterDto.setId(1);
        waiterDtoList.add(waiterDto);
    }

    @Test
    public void getAll() {
        when(waiterService.getAllWaiters()).thenReturn(waiterDtoList);
        List<WaiterDto>result=waitersController.getAll();
        assertEquals(waiterDtoList,result);

    }

    @Test
    public void saveWaiter() {

        when(waiterService.insert(any())).thenReturn(waiterDto);
        WaiterDto result=waitersController.saveWaiter(waiterDto);

        assertEquals(waiterDto,result);
    }

    @Test
    public void deleteWaiter() {

        when(waiterService.delete(any())).thenReturn("Success");

        assertEquals(waitersController.deleteWaiter(1),"Success");
    }

    @Test
    public void updateWaiter() {
        int id=1;
        when(waiterService.updateWaiter(1,waiterDto)).thenReturn(waiterDto);

        WaiterDto resultDto=waitersController.updateWaiter(id,waiterDto);

        assertEquals(waiterDto,resultDto);

    }
}