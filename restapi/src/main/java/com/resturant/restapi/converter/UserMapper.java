package com.resturant.restapi.converter;

import com.resturant.restapi.Model.Users;
import com.resturant.restapi.dto.UsersDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UsersDto toDto(Users users);
    Users toEntity(UsersDto usersDto);


    List<UsersDto> toDtoList(List<Users> users);

    //List<Users> toEntityList(List<UsersDto> usersDto);

    @Mapping(
            ignore = true,
            source = "roles",target = "roles"
    )
    Users toEntityWOROle(UsersDto dto);



}
