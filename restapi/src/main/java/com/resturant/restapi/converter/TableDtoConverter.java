package com.resturant.restapi.converter;

import com.resturant.restapi.Model.Tables;
import com.resturant.restapi.dto.TablesDto;

import java.util.ArrayList;
import java.util.List;

public class TableDtoConverter {


    public static List<TablesDto> tableListToTableDto(List<Tables> tablesList){

        List<TablesDto> tablesDtoList=new ArrayList<>();

        for(Tables tables:tablesList){

            TablesDto tablesDto=new TablesDto();

            tablesDto.setEnabled(tables.getEnabled());
            tablesDto.setId(tables.getId());
            tablesDto.setTableCount(tables.getTableCount());
            tablesDto.setTitle(tables.getTitle());

            tablesDtoList.add(tablesDto);
        }

        return tablesDtoList;
    }


    public static Tables tablesDtoToTables(Tables tables,TablesDto tablesDto){

//        tables.setEnabled(tablesDto.getEnabled());
//        tables.setTableCount(tablesDto.getTableCount());
//        tables.setTitle(tablesDto.getTitle());
//        tables.setId(tablesDto.getId());

        tables=TableMapper.INSTANCE.toEntity(tablesDto);

        return tables;
    }

    public static TablesDto tablesToTablesDto(Tables tables){

        TablesDto tablesDto=new TablesDto();
//        tablesDto.setTitle(tables.getTitle());
//        tablesDto.setTableCount(tables.getTableCount());
//        tablesDto.setId(tables.getId());
//        tablesDto.setEnabled(tables.getEnabled());

        tablesDto=TableMapper.INSTANCE.toDto(tables);

        return tablesDto;
    }


}
