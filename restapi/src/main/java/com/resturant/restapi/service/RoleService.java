package com.resturant.restapi.service;

import com.resturant.restapi.Model.Role;
import com.resturant.restapi.converter.RoleDtoConverter;
import com.resturant.restapi.converter.RoleMapper;
import com.resturant.restapi.dto.RoleDto;
import com.resturant.restapi.exception.ContentNotAllowed;
import com.resturant.restapi.exception.EntityNotFound;
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
        List<RoleDto> roleDtoList=roleMapper.toDtoList(rolesRepository.findAll());
        return roleDtoList;
    }

    public RoleDto getRole(int id){

        if(id<0){
            throw new ContentNotAllowed("Role id is not allowed");
        }
        return roleMapper.toDto(rolesRepository.findById(id).get());
    }

    public RoleDto insert(RoleDto roleDto){
        if(roleDto==null){
            throw new ContentNotAllowed("Role Content Not Allowed");
        }
        Role role=roleMapper.toEntity(roleDto);
        rolesRepository.save(role);
        return roleDto;
    }


    public String delete(int id){

        Optional<Role> roleOptional=rolesRepository.findById(id);
        if(!roleOptional.isPresent()){
            throw new EntityNotFound("Role Not Found");
        }

        rolesRepository.deleteById(id);
        return "Success";

    }

    public String update(RoleDto roleDto,int id){

        if(roleDto==null && id<0){
            throw new ContentNotAllowed("Not Allowed content");
        }

        Optional<Role> roleOptinal=rolesRepository.findById(id);
        if(!roleOptinal.isPresent()){
            throw new EntityNotFound("Entity Not Found");
        }

        Role role=roleOptinal.get();

        if(role.getName()!=roleDto.getName()){
            role.setName(roleDto.getName());
        }

        rolesRepository.save(role);
        return "Success";
    }
}
