package com.resturant.restapi.service;

import com.resturant.restapi.Model.Tables;
import com.resturant.restapi.converter.TableDtoConverter;
import com.resturant.restapi.dto.TablesDto;
import com.resturant.restapi.repository.TableRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;


@RunWith(MockitoJUnitRunner.class)
public class TableServiceTest {

    @Mock
    private TableRepository tableRepository;

    @InjectMocks
    private TableService tableService;

    List<Tables> tablesList =new ArrayList<>();
    Tables tables =new Tables();
    @Before
    public void setUp(){

        tables.setId(1);
        tables.setEnabled(true);
        tables.setTitle("Salon");
        tables.setTableCount(10);

        tablesList.add(tables);
    }

    @Test
    public void shouldgetAllTables() {

        when(tableRepository.findAll()).thenReturn(tablesList);
        List<TablesDto> resultTableList=tableService.getAllTables();

        assertEquals(resultTableList.equals(tablesList),tablesList);
    }

    @Test
    public void insertTable() {

       when(tableRepository.save(any())).thenReturn(tables);

       TablesDto resultTableDto=tableService.insertTable(TableDtoConverter.tablesToTablesDto(tables));

       //assertEquals(tables,resultTableDto);

        assertNotNull(resultTableDto);
    }

    @Test
    public void shoulGetTablebyId() {
        int id=1;
        when(tableRepository.findById(1)).thenReturn(Optional.of(tables));

        TablesDto resultDto=tableService.getTablebyId(id);

        assertEquals(tables.getId(),resultDto.getId());

    }


    @Test
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


        TablesDto resultDto=tableService.updateTable(TableDtoConverter.tablesToTablesDto(tables),id);

        assertNull(resultDto);
        assertEquals(resultDto,tables);
    }
}