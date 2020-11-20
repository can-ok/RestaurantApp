package com.resturant.restapi.controller;

import com.resturant.restapi.Model.User;
import com.resturant.restapi.service.UserService;
import org.omg.CORBA.INTERNAL;
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
    public List<User> getAllUsers(){

        return userService.getAllUser();
    }

    @PostMapping("/save")
    public User saveUser(@RequestBody User user){

        return userService.insertUser(user);
    }

    @GetMapping("/get/{id}")
    public User getUser(@PathVariable Integer id){

        return userService.getUser(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id){

        return userService.deleteUser(id);
    }
    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Integer id,@RequestBody User user){

        return userService.updateUser(id,user);
    }


}
