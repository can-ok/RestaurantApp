package com.resturant.restapi.controller;

import com.resturant.restapi.Model.Users;
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
    public List<Users> getAllUsers(){

        return userService.getAllUser();
    }

    @PostMapping("/save")
    public Users saveUser(@RequestBody Users users){

        return userService.insertUser(users);
    }

    @GetMapping("/get/{id}")
    public Users getUser(@PathVariable Integer id){

        return userService.getUser(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id){

        return userService.deleteUser(id);
    }
//    @PutMapping("/update/{id}")
//    public Users updateUser(@PathVariable Integer id, @RequestBody Users users){
//
//        return userService.updateUser(id, users);
//    }


}
