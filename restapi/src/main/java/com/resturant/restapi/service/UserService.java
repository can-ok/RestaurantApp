package com.resturant.restapi.service;

import com.resturant.restapi.Model.Role;
import com.resturant.restapi.Model.Users;
import com.resturant.restapi.config.MessageSourceExternalizer;
import com.resturant.restapi.converter.RoleDtoConverter;
import com.resturant.restapi.converter.RoleMapper;
import com.resturant.restapi.converter.UserDtoConverter;
import com.resturant.restapi.converter.UserMapper;
import com.resturant.restapi.dto.UsersDto;

import com.resturant.restapi.exception.AuthenticationNotAllowed;
import com.resturant.restapi.exception.ContentNotAllowed;
import com.resturant.restapi.exception.EntityNotFound;
import com.resturant.restapi.repository.RolesRepository;
import com.resturant.restapi.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    UsersRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MessageSourceExternalizer messageSourceExternalizer;

    public UsersDto insertUser(UsersDto usersDto){
        if(usersDto==null && usersDto.getId()<1){
            throw new ContentNotAllowed("User Content Not Allowed");
        }


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

//        List<UsersDto> usersDtoList=new ArrayList<>();
//
//        userRepository.findAll().forEach(entity->{
//            UsersDto usersDto=userMapper.toDto(entity);
//            usersDtoList.add(usersDto);
//        });

        return userMapper.toDtoList(userRepository.findAll());
    }

    public UsersDto getUser(Integer id){
        Optional<Users> userEntity=userRepository.findById(id);

        if(!userEntity.isPresent()){
            throw new  EntityNotFound("User entity not found");
        }
        else{

            UsersDto usersDto=userMapper.toDto(userEntity.get());
            return usersDto;
        }


    }

    public String deleteUser(Integer id){
        Optional<Users> byId = userRepository.findById(id);
        if(!byId.isPresent()){
            throw new EntityNotFound("User Entity Not Found");
        }
        userRepository.delete(byId.get());
        return "Success";
    }


    public UsersDto updateUser(Integer id, UsersDto usersDto){
        Optional<Users> entity = userRepository.findById(id);

        if (!entity.isPresent()) {

            throw new ContentNotAllowed("User Content Not Allowed");
        }
        else{

            if(entity.get().getUsername()!=usersDto.getUsername()){
                entity.get().setUsername(usersDto.getUsername());
            }
            if(entity.get().getUsername()!=usersDto.getPassword()){
                entity.get().setPassword(usersDto.getPassword());
            }
            if(usersDto.getRoles().size()>0){
                entity.get().setRoles(roleMapper.toRoleList(usersDto.getRoles()));
            }
            userRepository.save(entity.get());

            UsersDto response=userMapper.toDto(entity.get());

            return response;
        }
    }

    private final static BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

    public Map<String,String> register(UsersDto user){

        HashMap<String, String> map = new HashMap<>();
        String pass=user.getPassword();

        if( user.getUsername()==null  || user.getUsername().equals("")){
            throw new AuthenticationNotAllowed(messageSourceExternalizer.getMessage("username.error"));
        }

        if(user.getPassword()==null ||  user.getPassword().equals("")){
            throw new AuthenticationNotAllowed(messageSourceExternalizer.getMessage("password.error"));
        }

        Optional<Users> entity= userRepository.getUsersByUSERNAME(user.getUsername());


        if(!entity.isPresent() || !encoder.matches(pass,entity.get().getPassword())){
            throw new AuthenticationNotAllowed(messageSourceExternalizer.getMessage("authentication.error"));
        }


        map.put("name",user.getUsername());

        String originalInput = user.getUsername()+":"+pass;
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
        map.put("auth",encodedString);

        return map;

    }




}
