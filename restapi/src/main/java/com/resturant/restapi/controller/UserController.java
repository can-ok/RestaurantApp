package com.resturant.restapi.controller;

import com.resturant.restapi.Model.Users;
import com.resturant.restapi.dto.UsersDto;
import com.resturant.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3006"})
@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/getAll")
    public List<UsersDto> getAllUsers(){

        return userService.getAllUser();
    }

    @PostMapping("/save")
    public UsersDto saveUser(@RequestBody UsersDto usersDto){

        return userService.insertUser(usersDto);
    }

    @GetMapping("/get/{id}")
    public UsersDto getUser(@PathVariable Integer id){

        return userService.getUser(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id){

        return userService.deleteUser(id);
    }
    @PutMapping("/update/{id}")
    public UsersDto updateUser(@PathVariable Integer id, @RequestBody UsersDto usersDto){

        return userService.updateUser(id, usersDto);
    }



//    @GetMapping("/getAllAuth")
//    public List<AUTHORITIES> getAllAuth(){
//
//        return userService.getAllAuth();
//    }


}
