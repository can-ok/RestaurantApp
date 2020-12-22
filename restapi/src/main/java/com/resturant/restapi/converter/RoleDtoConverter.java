package com.resturant.restapi.converter;

import com.resturant.restapi.Model.Role;
import com.resturant.restapi.dto.RoleDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoleDtoConverter {


    public static Role roleDtoToRole(RoleDto roleDto){
        Role role=new Role();
        role.setName(roleDto.getName());
        role.setId(roleDto.getId());
        return role;
    }

    public static List<Role> roleDtoListToRoleList(List<RoleDto> roleDtoList){

        List<Role> roleList=new ArrayList<>();

        roleDtoList.forEach(item->{
            Role role=roleDtoToRole(item);
            roleList.add(role);
        });

        return roleList;
    }

    public static RoleDto roleToRoleDto(Role role){
        RoleDto dto=new RoleDto();
        dto.setId(role.getId());
        dto.setName(role.getName());
        return dto;
    }

    public static List<RoleDto> roleListToRoleDtoList(List<Role> roles){

        List<RoleDto> rolesDtoList=new ArrayList<>();

        roles.forEach(item ->{
            RoleDto roleDto=roleToRoleDto(item);

            rolesDtoList.add(roleDto);
        });

        return rolesDtoList;
    }


    public static Set<RoleDto> roleSetToRoleDtoSet(Set<Role> roles){

        Set<RoleDto> rolesDtoList=new HashSet<>();

        roles.forEach(item ->{
            RoleDto roleDto=roleToRoleDto(item);

            rolesDtoList.add(roleDto);
        });

        return rolesDtoList;
    }


    public static Set<Role> roleDtoSetToRoleSet(Set<RoleDto> roleDtos){
        Set<Role> roles=new HashSet<>();

        roleDtos.forEach(item ->{
            Role role=roleDtoToRole(item);
            roles.add(role);
        });

        return roles;
    }
}
