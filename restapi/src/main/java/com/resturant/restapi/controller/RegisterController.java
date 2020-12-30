package com.resturant.restapi.controller;


import com.resturant.restapi.Model.Users;
import com.resturant.restapi.dto.UsersDto;
import com.resturant.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3006"})
@RestController
public class RegisterController {
    @Autowired
    UserService userService;


    @PostMapping("/register")
    public Map<String ,String> register(@RequestBody UsersDto user){

        return userService.register(user);
    }
}
