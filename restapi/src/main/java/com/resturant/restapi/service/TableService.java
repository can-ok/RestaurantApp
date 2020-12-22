package com.resturant.restapi.service;

import com.resturant.restapi.Model.Orders;
import com.resturant.restapi.Model.Tables;
import com.resturant.restapi.converter.TableDtoConverter;
import com.resturant.restapi.converter.TableMapper;
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

    @Autowired
    TableMapper tableMapper;

    public List<TablesDto> getAllTables(){

        List<TablesDto> tablesDtoList=new ArrayList<>();

        tableRepository.findAll().forEach(entity->{

            TablesDto tablesDto=tableMapper.toDto(entity);
            tablesDtoList.add(tablesDto);
        });

        return tablesDtoList;
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

        Tables tables;
        tables=tableMapper.toEntity(tablesDto);

        tableRepository.save(tables);

        return tablesDto;
    }

    public TablesDto getTablebyId(int id){

        Optional<Tables> tableEntity=tableRepository.findById(id);

        if(tableEntity.isPresent()){

           TablesDto tablesDto=tableMapper.toDto(tableEntity.get());
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

            tableEntity.get().setEnabled(tablesDto.getEnabled());
            tableEntity.get().setTableCount(tablesDto.getTableCount());
            tableEntity.get().setTitle(tablesDto.getTitle());
            tableEntity.get().setId(tablesDto.getId());

            tableRepository.save(tableEntity.get());

            return tablesDto;
        }
    }

}
