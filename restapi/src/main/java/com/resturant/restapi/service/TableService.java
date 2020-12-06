package com.resturant.restapi.service;

import com.resturant.restapi.Model.Orders;
import com.resturant.restapi.Model.Tables;
import com.resturant.restapi.converter.TableDtoConverter;
import com.resturant.restapi.dto.TablesDto;
import com.resturant.restapi.repository.OrdersRepository;
import com.resturant.restapi.repository.TableRepository;
import javafx.scene.control.Tab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TableService {

    @Autowired
    TableRepository tableRepository;

    @Autowired
    OrdersRepository ordersRepository;


    public List<TablesDto> getAllTables(){

        List<TablesDto>  tablesListDto= TableDtoConverter.tableListToTableDto(tableRepository.findAll());
        return tablesListDto;
    }



    public List<Map<String,String>> getResvervedTable(){


        List<Map<String,String>> reservedListd=new ArrayList<>();


        List<Orders> orders=ordersRepository.findAll();


        orders.stream().forEach(order->{
            Map<String,String> tablesOrder=new HashMap<>();
            tablesOrder.put("Table",order.getOrderTable());
            tablesOrder.put("Count",order.getProductCount().toString());

            reservedListd.add(tablesOrder);

        });


        return reservedListd;
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
