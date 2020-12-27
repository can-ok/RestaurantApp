package com.resturant.restapi.converter;

import com.resturant.restapi.Model.Users;
import com.resturant.restapi.dto.UsersDto;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.catalina.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UsersDto toDto(Users users);
    Users toEntity(UsersDto usersDto);
    @Mapping(
            ignore = true,
            source = "roles",target = "roles"
    )
    Users toEntityWOROle(UsersDto dto);

    List<UsersDto> toDtoList(List<Users> users);


}
