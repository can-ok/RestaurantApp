package com.resturant.restapi.service;

import com.resturant.restapi.Model.Role;
import com.resturant.restapi.config.MessageSourceExternalizer;
import com.resturant.restapi.converter.RoleMapper;
import com.resturant.restapi.dto.RoleDto;
import com.resturant.restapi.exception.ContentNotAllowed;
import com.resturant.restapi.exception.EntityNotFound;
import com.resturant.restapi.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    RoleMapper roleMapper;
    @Autowired
    private MessageSourceExternalizer messageSourceExternalizer;

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

    @Transactional(propagation = Propagation.REQUIRED)
    public RoleDto insert(RoleDto roleDto){

        Role role=roleMapper.toEntity(roleDto);
        rolesRepository.save(role);
        return roleDto;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public String delete(int id){

        Optional<Role> roleOptional=rolesRepository.findById(id);
        if(!roleOptional.isPresent()){
            throw new EntityNotFound("Role "+messageSourceExternalizer.getMessage("content.error"));
        }

        rolesRepository.deleteById(id);
        return "Success";

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public String update(RoleDto roleDto,int id){


        Optional<Role> roleOptinal=rolesRepository.findById(id);
        if(!roleOptinal.isPresent()){
            throw new EntityNotFound(messageSourceExternalizer.getMessage("entity.error"));
        }

        Role role=roleOptinal.get();

        if(role.getName()!=roleDto.getName()){
            role.setName(roleDto.getName());
        }

        rolesRepository.save(role);
        return "Success";
    }
}
