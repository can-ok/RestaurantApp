package com.resturant.restapi.controller;

import com.resturant.restapi.service.InfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(tags = "Show Application Properties")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3006"})
@RestController
@RequestMapping("/info")
public class InfoController {

    @Autowired
    InfoService infoService;




    @ApiResponses(value={
            @ApiResponse(code = 201,message = "Status Ok"),
            @ApiResponse(code = 401,message = "login ol")
    })
    @ApiOperation(value = "list properties",notes = "Get application properties")
    @GetMapping("/list")
    public Map<String,String> getInfolist(){

        return infoService.getConfig();
    }


    @GetMapping("/beans")
    public List<String> getBeanlist(){
        return infoService.getBeans();
    }

}
