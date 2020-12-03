package com.resturant.restapi.service;

import com.resturant.restapi.Model.Tables;
import com.resturant.restapi.converter.TableDtoConverter;
import com.resturant.restapi.dto.TablesDto;
import com.resturant.restapi.repository.TableRepository;
import javafx.scene.control.Tab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableService {

    @Autowired
    TableRepository tableRepository;

    public List<TablesDto> getAllTables(){

        List<TablesDto>  tablesListDto= TableDtoConverter.tableListToTableDto(tableRepository.findAll());
        return tablesListDto;
    }


    public TablesDto insertTable(TablesDto tablesDto){

        Tables tables=new Tables();
        tables=TableDtoConverter.tablesDtoToTables(tables,tablesDto);
        tableRepository.save(tables);

        return tablesDto;
    }

    public TablesDto getTablebyId(int id){

        Optional<Tables> tableEntity=tableRepository.findById(id);

        if(tableEntity.isPresent()){

           TablesDto tablesDto=TableDtoConverter.tablesToTablesDto(tableEntity.get());

            return tablesDto;
        }

        return null;
    }

    public TablesDto updateTable(TablesDto tablesDto,int id){

        Optional<Tables> tableEntity=tableRepository.findById(id);

        if(!tableEntity.isPresent()){

            return  null;
        }
        else{

            Tables tables=TableDtoConverter.tablesDtoToTables(tableEntity.get(),tablesDto);



            tableRepository.save(tables);

            return tablesDto;
        }
    }

}
