package com.resturant.restapi.service;

import com.resturant.restapi.Model.AUTHORITIES;
import com.resturant.restapi.Model.Users;
import com.resturant.restapi.repository.AuthorityRepository;
import com.resturant.restapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authRepository;

    public Users insertUser(Users users){

        AUTHORITIES newAuth=new AUTHORITIES(null,users.getUserName(),users.getAuthority());
        //we also have to insert authorzation
        authRepository.save(newAuth);
        //update userpass
        users.setPassword("{noop}"+users.getPassword());
        return userRepository.save(users);
    }

    public List<Users> getAllUser(){

        return userRepository.findAll();
    }

    public Users getUser(Integer id){
        Optional<Users> userEntity=userRepository.findById(id);

        if(!userEntity.isPresent()){
            return null;
        }

        return userEntity.get();
    }

    public String deleteUser(Integer id){

        userRepository.deleteById(id);

        return "success";
    }


    public Users updateUser(Integer id, Users users){
        Optional<Users> entity = userRepository.findById(id);

        if (!entity.isPresent()) {
            System.out.println("Sonuç bulunamadı");
            return null;}
        else{

            entity.get().setId(id);
            entity.get().setUserName(users.getUserName());
            entity.get().setPassword(users.getPassword());


            userRepository.save(entity.get());

            return entity.get();
        }
    }

    public List<AUTHORITIES> getAllAuth(){

        return authRepository.findAll();
    }


    //handle register


    public Map<String,String > register(Users user){

        HashMap<String, String> map = new HashMap<>();
        String pass=user.getPassword();

        //update user name
        user.setPassword("{noop}"+user.getPassword());
        Optional<Users> entity= userRepository.getUserName(user.getUserName(),user.getPassword());

        if(entity.isPresent()){


            map.put("name",user.getUserName());
            //map.put("password",user.getPassword());

            String originalInput = user.getUserName()+":"+pass;
            String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
            map.put("auth",encodedString);

            return map;
        }
        else {

            return map;
        }

    }




}
