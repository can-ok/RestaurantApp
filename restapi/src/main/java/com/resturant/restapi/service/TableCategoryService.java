package com.resturant.restapi.service;


import com.resturant.restapi.Model.TableCategory;
import com.resturant.restapi.Model.Tables;
import com.resturant.restapi.repository.TableCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableCategoryService {

    @Autowired
    TableCategoryRepository tableCategoryRepository;

    public List<TableCategory> getAll(){

        return tableCategoryRepository.findAll();
    }
}
