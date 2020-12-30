package com.resturant.restapi.controller;

import com.resturant.restapi.dto.TablesDto;
import com.resturant.restapi.repository.TableRepository;
import com.resturant.restapi.service.TableService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TableControllerTest {


    @Mock
    TableService tableService;

    @InjectMocks
    TableController tableController;


    List<TablesDto> tablesList =new ArrayList<>();
    TablesDto tablesDto =new TablesDto();

    @Before
    public void setUp(){

        tablesDto.setId(1);
        tablesDto.setEnabled(true);
        tablesDto.setTitle("Salon");
        tablesDto.setTableCount(10);

        tablesList.add(tablesDto);
    }

    @Test
    public void getAll() {
        when(tableService.getAllTables()).thenReturn(tablesList);

        assertEquals(tableController.getAll(),tablesList);
    }

    @Test
    public void addTable() {

        when(tableService.insertTable(tablesDto)).thenReturn(tablesDto);

        assertEquals(tableController.addTable(tablesDto),tablesDto);
    }

    @Test
    public void updateTable() {
        int id=1;
        when(tableService.updateTable(tablesDto,id)).thenReturn(tablesDto);

        assertEquals(tableService.updateTable(tablesDto,id),tablesDto);
    }

    @Test
    public void getTablesCategory() {
        int id=1;
        when(tableService.getTablebyId(id)).thenReturn(tablesDto);

        assertEquals(tableController.getTablesCategory(id),tablesDto);
    }
}