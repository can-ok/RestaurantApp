package com.resturant.restapi.service;

import com.resturant.restapi.Model.Media;
import com.resturant.restapi.Model.Waiter;
import com.resturant.restapi.builder.MediaDtoBuilder;
import com.resturant.restapi.builder.WaiterBuilder;
import com.resturant.restapi.builder.WaiterDtoBuilder;
import com.resturant.restapi.converter.MediaDtoConverter;
import com.resturant.restapi.converter.WaiterMapper;
import com.resturant.restapi.dto.WaiterDto;
import com.resturant.restapi.exception.EntityNotFound;
import com.resturant.restapi.repository.MediaRepository;
import com.resturant.restapi.repository.WaiterRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;


@RunWith(MockitoJUnitRunner.class)
public class WaiterServiceTest {

    @Mock
    private WaiterRepository waiterRepository;

    @Mock
    WaiterMapper waiterMapper;

    @Mock
    private MediaRepository mediaRepository;

    @InjectMocks
    private WaiterService waiterService;



    private List<WaiterDto> waiterDtoList=new ArrayList<>();
    private List<Waiter> waiterList=new ArrayList<>();

    private WaiterDto waiterDto;
    private Waiter waiter;
    private Media media;


    @Before
    public void setUpDto(){
        byte[] fileBytes = "deneme".getBytes();
        media= MediaDtoConverter.mediaDtoToMedia(new MediaDtoBuilder().fileContent(fileBytes).id(1).name("deneme").build());

        waiter=new WaiterBuilder().firstname("deneme").id(1).lastname("deneme").media(media).build();
        waiterDto=new WaiterDtoBuilder().firstname("deneme").id(1).lastname("deneme").media(media).build();

        when(waiterMapper.toDto(any())).thenReturn(waiterDto);
        when(waiterMapper.toWaiter(any())).thenReturn(waiter);
        when(waiterMapper.waiterDtoList(any())).thenReturn(waiterDtoList);
        when(waiterMapper.waiterList(any())).thenReturn(waiterList);

    }

    @Before
    public void setUpDtoList(){
        waiterDtoList.add(waiterDto);
        waiterList.add(waiter);
    }



    @Test
    public void shouldGetAllWaiters() {
        when(waiterRepository.findAll()).thenReturn(waiterList);
        assertEquals(waiterService.getAllWaiters().size(),waiterDtoList.size());
    }



    @Test
    public void shouldInsert() {
        when(waiterRepository.save(any())).thenReturn(waiter);
        when(mediaRepository.findById(any())).thenReturn(Optional.of(media));


        WaiterDto result=waiterService.insert(waiterDto);
        assertNotNull(result);
        assertEquals(result,waiterDto);

    }


    @Test
    public void delete() {
        int id=1;
        when(waiterRepository.findById(id)).thenReturn(Optional.of(waiter));

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
        when(waiterRepository.findById(id)).thenReturn(Optional.of(waiter));
        when(mediaRepository.findById(any())).thenReturn(Optional.of(media));
        assertEquals(waiterDto,waiterService.updateWaiter(id,waiterDto));
    }

    @Test(expected = EntityNotFound.class)
    public void shouldNotUpdateWaiter() {
        int id=1;
        when(waiterRepository.findById(id)).thenReturn(Optional.empty());
        assertNull(waiterService.updateWaiter(id,waiterDto));
    }
}