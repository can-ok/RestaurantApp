package com.resturant.restapi.converter;

import com.resturant.restapi.Model.Role;
import com.resturant.restapi.dto.RoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface RoleMapper {


    RoleDto toDto(Role role);

    Role toEntity(RoleDto roleDto);

    List<RoleDto>  toDtoList(List<Role> roles);

    Set<Role> toRoleList(Set<RoleDto> roleDtoList);
}
