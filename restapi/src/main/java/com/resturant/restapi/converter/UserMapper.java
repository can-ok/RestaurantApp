package com.resturant.restapi.converter;

import com.resturant.restapi.Model.Users;
import com.resturant.restapi.dto.UsersDto;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);

    UsersDto toDto(Users users);
    Users toEntity(UsersDto usersDto);
    @Mapping(
            ignore = true,
            source = "roles",target = "roles"
    )
    Users toEntityWOROle(UsersDto dto);
}
