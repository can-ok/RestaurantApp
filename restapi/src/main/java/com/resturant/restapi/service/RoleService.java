package com.resturant.restapi.service;

import com.resturant.restapi.Model.Role;
import com.resturant.restapi.converter.RoleDtoConverter;
import com.resturant.restapi.converter.RoleMapper;
import com.resturant.restapi.dto.RoleDto;
import com.resturant.restapi.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    RoleMapper roleMapper;

    public List<RoleDto> getAll(){

        List<RoleDto> rolesDtoList=new ArrayList<>();
        rolesRepository.findAll().forEach(role -> {
            RoleDto roleDto=roleMapper.toDto(role);
            rolesDtoList.add(roleDto);
        });

        return rolesDtoList;
    }

    public RoleDto getRole(int id){

        return roleMapper.toDto(rolesRepository.findById(id).get());
    }

    public RoleDto  insert(RoleDto roleDto){

        Role role=roleMapper.toEntity(roleDto);
        rolesRepository.save(role);
        return roleDto;
    }


    public String delete(int id){
        if(rolesRepository.findById(id).isPresent()){

            rolesRepository.deleteById(id);
            return "Success";
        }

        return "Fail";
    }

    public String update(RoleDto roleDto,int id){

        Optional<Role> roleOptinal=rolesRepository.findById(id);
        if(!roleOptinal.isPresent()){

            return "Fail";

        }


        Role role=roleOptinal.get();
        role.setName(roleDto.getName());
        role.setId(id);
        rolesRepository.save(role);
        return "Success";
    }
}
