package com.resturant.restapi.service;

import com.resturant.restapi.Model.Media;
import com.resturant.restapi.Model.Orders;
import com.resturant.restapi.Model.Tables;
import com.resturant.restapi.converter.TableDtoConverter;
import com.resturant.restapi.converter.TableMapper;
import com.resturant.restapi.dto.TablesDto;
import com.resturant.restapi.exception.ContentNotAllowed;
import com.resturant.restapi.exception.EntityNotFound;
import com.resturant.restapi.repository.MediaRepository;
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
    MediaRepository mediaRepository;

    @Autowired
    TableMapper tableMapper;

    public List<TablesDto> getAllTables(){

        List<TablesDto> tablesDtoList=tableMapper.toDtoList(tableRepository.findAll());

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

        if(tablesDto==null){
            throw new ContentNotAllowed("Table Content Not Allowed");
        }

        Media media=mediaRepository.findById(tablesDto.getMedia().getId()).get();
        Tables tables=tableMapper.toEntity(tablesDto);

        tables.setMedia(media);
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
            throw new EntityNotFound("Table Entity Not Found");
        }
        else{

            if(tableEntity.get().getEnabled()!=tablesDto.getEnabled()){
                tableEntity.get().setEnabled(tablesDto.getEnabled());
            }
            if(tableEntity.get().getTableCount()!=tablesDto.getTableCount()){
                tableEntity.get().setTableCount(tablesDto.getTableCount());
            }
            if(tableEntity.get().getTitle()!=tablesDto.getTitle()){
                tableEntity.get().setTitle(tablesDto.getTitle());
            }
            if(tableEntity.get().getMedia().getId()!=tablesDto.getMedia().getId()){
                Media media=mediaRepository.findById(tablesDto.getMedia().getId()).get();
                tableEntity.get().setMedia(media);
            }

            tableRepository.save(tableEntity.get());

            return tablesDto;
        }
    }

    public String deleteTable(Integer id){
        Optional<Tables> byId = tableRepository.findById(id);
        if(!byId.isPresent()){
            throw new EntityNotFound("Entity not found");
        }
        tableRepository.deleteById(id);
        return "Success";
    }

}
