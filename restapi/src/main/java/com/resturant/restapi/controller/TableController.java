package com.resturant.restapi.controller;


import com.resturant.restapi.Model.Food;
import com.resturant.restapi.Model.Tables;
import com.resturant.restapi.dto.TablesDto;
import com.resturant.restapi.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3006"})
@RestController
@RequestMapping("/table")
public class TableController {

    @Autowired
    TableService tableService;


    @GetMapping("/getAll")
    public List<TablesDto> getAll(){

        return tableService.getAllTables();
    }

    @PostMapping(path ="/add")
    public TablesDto addTable(@RequestBody TablesDto tableDto){

        return tableService.insertTable(tableDto);
    }
//
//    @DeleteMapping(path="/delete/{id}")
//    public String deleteTable(@PathVariable Integer id){
//        return tableService.deleteTable(id);
//    }
//
    @PutMapping(path = "/update/{id}")
    public TablesDto updateTable(@RequestBody TablesDto tablesDto,@PathVariable int id){

        return tableService.updateTable(tablesDto,id);
    }


    @GetMapping(path = "/getTable/{id}")
    public TablesDto getTablesCategory(@PathVariable int id){

        return tableService.getTablebyId(id);
    }

    @GetMapping(path = "/getResvervedTable")
    public List<Map<String,String>> getReservedTable(){

        return tableService.getResvervedTable();
    }

}
