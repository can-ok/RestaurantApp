package com.resturant.restapi.controller;


import com.resturant.restapi.dto.TablesDto;
import com.resturant.restapi.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
    public TablesDto addTable(@Valid @RequestBody TablesDto tableDto){

        return tableService.insertTable(tableDto);
    }

    @DeleteMapping(path="/delete/{id}")
    public String deleteTable(@NotNull @PathVariable Integer id){
        return tableService.deleteTable(id);
    }

    @PutMapping(path = "/update/{id}")
    public TablesDto updateTable(@Valid @RequestBody TablesDto tablesDto,@NotNull @PathVariable int id){

        return tableService.updateTable(tablesDto,id);
    }


    @GetMapping(path = "/getTable/{id}")
    public TablesDto getTablesCategory(@NotNull @PathVariable int id){

        return tableService.getTablebyId(id);
    }

    @GetMapping(path = "/getResvervedTable")
    public List<Map<String,String>> getReservedTable(){

        return tableService.getResvervedTable();
    }

}
