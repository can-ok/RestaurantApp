package com.resturant.restapi.service;

import com.resturant.restapi.Model.Users;
import com.resturant.restapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Users insertUser(Users users){

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


//    public Users updateUser(Integer id, Users users){
//        Optional<Users> entity = userRepository.findById(id);
//
//        if (!entity.isPresent()) {
//            System.out.println("Sonuç bulunamadı");
//            return null;}
//        else{
//
//            entity.get().setId(id);
//            entity.get().setName(users.getName());
//            entity.get().setPassword(users.getPassword());
//            entity.get().setRole(users.getRole());
//
//
//            userRepository.save(entity.get());
//
//            return entity.get();
//        }
    //}




}
