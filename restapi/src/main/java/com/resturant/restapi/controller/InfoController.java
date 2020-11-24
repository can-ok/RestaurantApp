package com.resturant.restapi.controller;

import com.resturant.restapi.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3006"})
@RestController
@RequestMapping("/info")
public class InfoController {

    @Autowired
    InfoService infoService;

    @GetMapping("/list")
    public Map<String,String> getInfolist(){

        return infoService.getConfig();
    }

}
