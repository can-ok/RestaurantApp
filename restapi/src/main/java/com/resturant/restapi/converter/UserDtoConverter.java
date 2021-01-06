package com.resturant.restapi.converter;

import com.resturant.restapi.Model.Users;
import com.resturant.restapi.dto.UsersDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class UserDtoConverter {

    private final static BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

    public static Users userDtoToUser(UsersDto usersDto){

        Users users=new Users();

        users.setUsername(usersDto.getUsername());
        users.setPassword(encoder.encode(usersDto.getPassword()));
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
        userDto.setUsername(user.getUsername());
        userDto.setPassword(encoder.encode(user.getPassword()));
        //create converter and add AUthDot

        userDto.setRoles( RoleDtoConverter.roleSetToRoleDtoSet(user.getRoles()));
        userDto.setEnabled(user.isEnabled());

        return userDto;
    }

}
