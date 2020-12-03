package com.resturant.restapi.converter;

import com.resturant.restapi.Model.Users;
import com.resturant.restapi.dto.UsersDto;

import java.util.ArrayList;
import java.util.List;

public class UserDtoConverter {


    public static Users  userDtoToUser(UsersDto usersDto){

        Users users=new Users();
        users.setUSERNAME(usersDto.getUSERNAME());
        users.setPassword("{noop}"+usersDto.getPassword());
        users.setEnabled(usersDto.isEnabled());

        return users;
    }

    public static List<UsersDto> convertUsesrListoUserDtoList(List<Users> entityList){

        List<UsersDto> usersDtoList=new ArrayList<>();

        for(Users entity:entityList){

            UsersDto usersDto=new UsersDto();
            usersDto.setId(entity.getId());
            usersDto.setUSERNAME(entity.getUSERNAME());
            usersDto.setEnabled(entity.isEnabled());
            usersDto.setAUTHORITY(entity.getAUTHORITY());
            usersDtoList.add(usersDto);
        }

        return usersDtoList;
    }

}
