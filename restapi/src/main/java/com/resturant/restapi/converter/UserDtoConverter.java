package com.resturant.restapi.converter;

import com.resturant.restapi.Model.Users;
import com.resturant.restapi.dto.UsersDto;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

public class UserDtoConverter {


    public static Users userDtoToUser(UsersDto usersDto){

        Users users=new Users();
        users.setUSERNAME(usersDto.getUSERNAME());
        users.setPassword("{noop}"+usersDto.getPassword());
        users.setEnabled(usersDto.isEnabled());
        users.setId(usersDto.getId());
        return users;
    }

    public static List<UsersDto> convertUsesrListoUserDtoList(List<Users> entityList){

        List<UsersDto> usersDtoList=new ArrayList<>();


        entityList.forEach(entity->{

            UsersDto usersDto=userToUserDto(entity);
            usersDtoList.add(usersDto);
        });
        

        return usersDtoList;
    }

    public static List<Users> userDtoListToUserList(List<UsersDto> dtoList){

        List<Users> usersList=new ArrayList<>();

        dtoList.forEach(item->{

            Users user=userDtoToUser(item);

            usersList.add(user);
        });
        return usersList;
    }


    public static UsersDto userToUserDto(Users user){

        UsersDto userDto=new UsersDto();

        userDto.setId(user.getId());
        userDto.setUSERNAME(user.getUSERNAME());
        userDto.setPassword(user.getPassword());
        userDto.setEnabled(user.isEnabled());
        userDto.setAUTHORITY(user.getAUTHORITY());
        return userDto;
    }

}
