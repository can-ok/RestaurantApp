package com.resturant.restapi.service;

import com.resturant.restapi.Model.Tables;
import com.resturant.restapi.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService {

    @Autowired
    TableRepository tableRepository;

    public List<Tables> getAllTables(){

        return tableRepository.findAll();
    }


    public Tables insertTable(Tables tables){


        return  tableRepository.save(tables);
    }

}
