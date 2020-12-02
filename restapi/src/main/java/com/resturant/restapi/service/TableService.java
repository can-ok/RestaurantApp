package com.resturant.restapi.service;

import com.resturant.restapi.Model.TableCategory;
import com.resturant.restapi.Model.Tables;
import com.resturant.restapi.repository.TableCategoryRepository;
import com.resturant.restapi.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableService {
    @Autowired
    TableRepository tableRepository;

    @Autowired
    TableCategoryRepository tableCategoryRepository;

    public List<Tables> getAllTables(){

        return tableRepository.findAll();
    }

    public String insertTable(Tables tables,int id){

        Optional<TableCategory> categoryEntity=tableCategoryRepository.findById(id);
        if(categoryEntity.isPresent()){

            tables.setTableCategory(categoryEntity.get());
            tableRepository.save(tables);
            return "success";
        }


        return "fail";
    }

    public String deleteTable(Integer id){
        tableRepository.deleteById(id);

        return "success";
    }

    public Tables updateTable(Integer id,Tables tables){

        Optional<Tables> tableEntity=tableRepository.findById(id);

        Optional<TableCategory> tableCategoryEntity=tableCategoryRepository.findById(tables.getTableCategory().getId());

        if(!tableEntity.isPresent()){
            return null;
        }
        else{

            tableEntity.get().setId(id);
            tableEntity.get().setEnabled(tables.getEnabled());
            tableEntity.get().setTableCategory(tables.getTableCategory());
            tableEntity.get().setTitle(tables.getTitle());
            tableEntity.get().setTableCategory(tableCategoryEntity.get());
            tableRepository.save(tableEntity.get());
            return tableEntity.get();
        }
    }

}
