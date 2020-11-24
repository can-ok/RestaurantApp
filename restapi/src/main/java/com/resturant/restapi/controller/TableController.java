package com.resturant.restapi.controller;


import com.resturant.restapi.Model.Food;
import com.resturant.restapi.Model.Tables;
import com.resturant.restapi.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3006"})
@RestController
@RequestMapping("/table")
public class TableController {

    @Autowired
    TableService tableService;


    @GetMapping("/getAll")
    public List<Tables> getAll(){

        return tableService.getAllTables();
    }

    @PostMapping(path ="/add/{id}")
    public String addFood(@RequestBody Tables table, @PathVariable int id){

        return tableService.insertTable(table,id);
    }

}
