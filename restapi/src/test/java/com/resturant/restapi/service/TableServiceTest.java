package com.resturant.restapi.service;

import com.resturant.restapi.Model.Media;
import com.resturant.restapi.Model.Orders;
import com.resturant.restapi.Model.Tables;
import com.resturant.restapi.builder.MediaDtoBuilder;
import com.resturant.restapi.builder.OrdersBuilder;
import com.resturant.restapi.config.MessageSourceExternalizer;
import com.resturant.restapi.converter.MediaDtoConverter;
import com.resturant.restapi.converter.TableDtoConverter;
import com.resturant.restapi.converter.TableMapper;
import com.resturant.restapi.dto.TablesDto;
import com.resturant.restapi.exception.EntityNotFound;
import com.resturant.restapi.repository.MediaRepository;
import com.resturant.restapi.repository.OrdersRepository;
import com.resturant.restapi.repository.TableRepository;
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
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;


@RunWith(MockitoJUnitRunner.class)
public class TableServiceTest {

    @Mock
    private TableRepository tableRepository;
    @Mock
    private OrdersRepository ordersRepository;

    @Mock
    private TableMapper tableMapper;

    @InjectMocks
    private TableService tableService;

    @Mock
    private MediaRepository mediaRepository;

    @Mock
    private MessageSourceExternalizer messageSourceExternalizer;

    private List<Tables> tablesList =new ArrayList<>();
    private Tables tables =new Tables();
    private TablesDto tablesDto=new TablesDto();
    private List<TablesDto> tablesDtoList =new ArrayList<>();

    private Media media;


    @Before
    public void setUp(){

        when(tableMapper.toDto(any())).thenReturn(tablesDto);

        tablesDtoList.add(tablesDto);

        when(tableMapper.toDtoList(any())).thenReturn(tablesDtoList);

        tablesList.add(tables);

        byte[] fileBytes = "deneme".getBytes();
        media= MediaDtoConverter.mediaDtoToMedia(new MediaDtoBuilder().fileContent(fileBytes).id(1).name("deneme").build());


        tables.setId(1);
        tablesDto.setId(1);
        tables.setEnabled(true);
        tablesDto.setTitle("Salon");
        tables.setTitle("Salon");
        tables.setTableCount(10);
        tablesDto.setTableCount(10);
        tables.setMedia(media);
        tablesDto.setMedia(media);

    }


    private List<Orders> orderList=new ArrayList<>();
    @Before
    public void setUpOrder(){
        Orders orders=new OrdersBuilder().id(1).paymentType("cash").orderTable("Masa 1").productCount(5)
                .totalPrice(100).productId(1).waiterId("1").build();

        orderList.add(orders);

    }

    @Test
    public void shouldgetAllTables() {

        when(tableRepository.findAll()).thenReturn(tablesList);
        List<TablesDto> resultTableList=tableService.getAllTables();

        assertEquals(resultTableList.size(),tablesList.size());
    }

    @Test
    public void insertTable() {
        when(tableRepository.save(any())).thenReturn(tables);
        when(mediaRepository.findById(any())).thenReturn(Optional.of(media));

        TablesDto resultTableDto=tableService.insertTable(tablesDto);
       //assertEquals(tables,resultTableDto);

        assertNotNull(resultTableDto);
    }

    @Test
    public void shouldGetTablebyId() {
        int id=1;
        when(tableRepository.findById(1)).thenReturn(Optional.of(tables));
        when(tableMapper.toDto(any())).thenReturn(tablesDto);
        TablesDto resultDto=tableService.getTablebyId(id);

        assertEquals(tables.getId(),resultDto.getId());

    }


    @Test(expected = EntityNotFound.class)
    public void shoudNotGetTablebyId() {
        int id=1;
        when(tableRepository.findById(1)).thenReturn(Optional.empty());

        TablesDto resultDto=tableService.getTablebyId(id);

        assertNull(resultDto);
    }


    @Test
    public void updateTable() {
        int id=1;
        when(tableRepository.findById(id)).thenReturn(Optional.of(tables));

        when(tableRepository.save(any())).thenReturn(tables);//gereksiz

        when(mediaRepository.findById(any())).thenReturn(Optional.of(media));

        TablesDto resultDto=tableService.updateTable(tablesDto,id);
        assertNotNull(resultDto);
        assertEquals(resultDto.getId(),tables.getId());
    }

    @Test
    public void shouldDeleteTable(){
        int id=1;
        when(tableRepository.findById(id)).thenReturn(Optional.of(tables));
        assertEquals(tableService.deleteTable(id),"Success");

    }

    @Test(expected = EntityNotFound.class)
    public void shouldNotDeleteTable(){
        int id=1;
        when(tableRepository.findById(id)).thenReturn(Optional.ofNullable(null));
        assertEquals(tableService.deleteTable(id),"Success");

    }

    @Test
    public void shouldGetReservedTable(){
        when(ordersRepository.findAll()).thenReturn(orderList);
        assertEquals(tableService.getResvervedTable().size(),1);
    }

}