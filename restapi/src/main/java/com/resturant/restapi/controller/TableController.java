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

    @PostMapping(path ="/add")
    public Tables addTable(@RequestBody Tables table){

        return tableService.insertTable(table);
    }
//
//    @DeleteMapping(path="/delete/{id}")
//    public String deleteTable(@PathVariable Integer id){
//        return tableService.deleteTable(id);
//    }
//
//    @PutMapping(path = "/update/{id}")
//    public Tables updateTable(@RequestBody Tables tables,@PathVariable int id){
//
//        return tableService.updateTable(id,tables);
//    }

}
