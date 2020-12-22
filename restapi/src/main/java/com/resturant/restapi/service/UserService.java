package com.resturant.restapi.service;

import com.resturant.restapi.Model.Role;
import com.resturant.restapi.Model.Users;
import com.resturant.restapi.converter.RoleDtoConverter;
import com.resturant.restapi.converter.UserDtoConverter;
import com.resturant.restapi.converter.UserMapper;
import com.resturant.restapi.dto.UsersDto;

import com.resturant.restapi.repository.RolesRepository;
import com.resturant.restapi.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    UsersRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private UserMapper userMapper;

    public UsersDto insertUser(UsersDto usersDto){


        Users users=userMapper.toEntityWOROle(usersDto);

        users.setPassword(encoder.encode(usersDto.getPassword()));

        usersDto.getRoles().stream().forEach(dtoRole->{
            Role role = rolesRepository.findById(dtoRole.getId()).get();
            users.getRoles().add(role);
        });
        userRepository.save(users);
        return usersDto;
    }

    public List<UsersDto> getAllUser(){

        List<UsersDto> usersDtoList=new ArrayList<>();

        userRepository.findAll().forEach(entity->{
            UsersDto usersDto=userMapper.toDto(entity);
            usersDtoList.add(usersDto);
        });

        return usersDtoList;
    }

    public UsersDto getUser(Integer id){
        Optional<Users> userEntity=userRepository.findById(id);

        if(!userEntity.isPresent()){
            return null;
        }
        else{

            UsersDto usersDto=userMapper.toDto(userEntity.get());
            return usersDto;
        }


    }

    public String deleteUser(Integer id){
        userRepository.deleteById(id);
        return "Success";
    }


    public UsersDto updateUser(Integer id, UsersDto usersDto){
        Optional<Users> entity = userRepository.findById(id);

        if (!entity.isPresent()) {
            System.out.println("Sonuç bulunamadı");
            return null;}
        else{

            entity.get().setId(id);
            entity.get().setUsername(usersDto.getUsername());
            entity.get().setPassword(usersDto.getPassword());
            entity.get().setRoles(RoleDtoConverter.roleDtoSetToRoleSet(usersDto.getRoles()));
            userRepository.save(entity.get());

            UsersDto response=userMapper.toDto(entity.get());

            return response;
        }
    }

    private final static BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

    public Map<String,String> register(UsersDto user){

        //System.out.println(user.getUSERNAME());
        HashMap<String, String> map = new HashMap<>();
        String pass=user.getPassword();

        //update user name
        //user.setPassword("{noop}"+user.getPassword());

        Optional<Users> entity= userRepository.getUsersByUSERNAME(user.getUsername());

        if(entity.isPresent()){

            //if(encoder.matches(entity.get().getPassword(),user.getPassword())){
                map.put("name",user.getUsername());
                //map.put("password",user.getPassword());

                String originalInput = user.getUsername()+":"+pass;
                String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
                map.put("auth",encodedString);

                return map;

        }
        else {

            return null;
        }

    }




}
