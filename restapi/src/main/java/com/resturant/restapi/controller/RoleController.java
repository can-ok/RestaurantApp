package com.resturant.restapi.controller;


import com.resturant.restapi.Model.Role;
import com.resturant.restapi.dto.RoleDto;
import com.resturant.restapi.service.RoleService;
import com.resturant.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3006"})
@RestController
@RequestMapping(path = "/roles")
public class RoleController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @GetMapping()
    public List<RoleDto> getAll(){
        return roleService.getAll();
    }

    @PostMapping()
    public RoleDto save(@RequestBody RoleDto role){

        return roleService.insert(role);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){

        return roleService.delete(id);
    }

    @PutMapping("/{id}")
    public String update(@RequestBody RoleDto roleDto,@PathVariable int id){
        return roleService.update(roleDto,id);
    }


    @GetMapping("/{id}")
    public RoleDto getById(@PathVariable int id){
        return roleService.getRole(id);
    }
}
