package com.resturant.restapi.service;

import com.resturant.restapi.converter.WaiterDtoConverter;
import com.resturant.restapi.dto.WaiterDto;
import com.resturant.restapi.repository.WaiterRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;


@RunWith(MockitoJUnitRunner.class)
public class WaiterServiceTest {

    @Mock
    private WaiterRepository waiterRepository;


    @InjectMocks
    private WaiterService waiterService;

    private List<WaiterDto> waiterDtoList=new ArrayList<>();

    private WaiterDto waiterDto=new WaiterDto();

    @Before
    public void setUpDto(){
        waiterDto.setName("user");
        waiterDto.setId(1);
    }

    @Before
    public void setUpDtoList(){
        waiterDtoList.add(waiterDto);
    }



    @Test
    public void shouldGetAllWaiters() {
        when(waiterRepository.findAll()).thenReturn(WaiterDtoConverter.waiterDtoListToWaiterList(waiterDtoList));
        assertEquals(waiterService.getAllWaiters().size(),waiterDtoList.size());
    }

    @Test
    public void shoudlNotInsert() {
        when(waiterRepository.save(any())).thenReturn(Optional.empty());

        WaiterDto waiterDto=waiterService.insert(null);
        assertNull(waiterDto);

    }

    @Test
    public void shouldInsert() {
        when(waiterRepository.save(any())).thenReturn(WaiterDtoConverter.waiterDtoToWaiter(waiterDto));

        WaiterDto result=waiterService.insert(waiterDto);
        assertNotNull(result);
        assertEquals(result,waiterDto);

    }


    @Test
    public void delete() {
        int id=1;
        when(waiterRepository.findById(id)).thenReturn(Optional.of(WaiterDtoConverter.waiterDtoToWaiter(waiterDto)));

        assertEquals(waiterService.delete(id),"Success");

    }

    @Test
    public void shouldNotdelete() {
        int id=1;
        when(waiterRepository.findById(id)).thenReturn(Optional.empty());

        assertEquals(waiterService.delete(id),"Fail");

    }

    @Test
    public void shouldUpdateWaiter() {
        int id=1;
        when(waiterRepository.findById(id)).thenReturn(Optional.of(WaiterDtoConverter.waiterDtoToWaiter(waiterDto)));
        assertEquals(waiterDto,waiterService.updateWaiter(id,waiterDto));
    }

    @Test
    public void shouldNotUpdateWaiter() {
        int id=1;
        when(waiterRepository.findById(id)).thenReturn(Optional.empty());
        assertNull(waiterService.updateWaiter(id,waiterDto));
    }
}