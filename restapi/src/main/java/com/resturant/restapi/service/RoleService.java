package com.resturant.restapi.service;

import com.resturant.restapi.Model.Role;
import com.resturant.restapi.converter.RoleDtoConverter;
import com.resturant.restapi.dto.RoleDto;
import com.resturant.restapi.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    RolesRepository rolesRepository;

    public List<RoleDto> getAll(){

        return RoleDtoConverter.roleListToRoleDtoList(rolesRepository.findAll());
    }

    public RoleDto getRole(int id){

        return RoleDtoConverter.roleToRoleDto(rolesRepository.findById(id).get());
    }

    public RoleDto  insert(RoleDto role){

        rolesRepository.save(RoleDtoConverter.roleDtoToRole(role));
        return role;
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
