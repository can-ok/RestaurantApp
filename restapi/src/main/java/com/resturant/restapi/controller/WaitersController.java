package com.resturant.restapi.controller;


import com.resturant.restapi.dto.WaiterDto;
import com.resturant.restapi.service.WaiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3006"})
@RestController
@RequestMapping(path = "/waiters")
public class WaitersController {

    @Autowired
    WaiterService waiterService;

    @GetMapping(path = "/getAll")
    public List<WaiterDto> getAll(){

        return waiterService.getAllWaiters();
    }

    @PostMapping(path ="/save")
    public WaiterDto saveWaiter(@RequestBody WaiterDto waiterDto){

        return waiterService.insert(waiterDto);
    }

    @DeleteMapping(path = "/delete/{id}")
    public String deleteWaiter(@PathVariable Integer id){

        return waiterService.delete(id);
    }

    @PutMapping(path = "/update/{id}")
    public WaiterDto updateWaiter(@PathVariable int id,@RequestBody WaiterDto waiterDto){

        return waiterService.updateWaiter(id,waiterDto);
    }




}
