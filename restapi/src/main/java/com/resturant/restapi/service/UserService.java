package com.resturant.restapi.service;

import com.resturant.restapi.Model.User;
import com.resturant.restapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User insertUser(User user){

        return userRepository.save(user);
    }

    public List<User> getAllUser(){

        return userRepository.findAll();
    }

    public User getUser(Integer id){
        Optional<User> userEntity=userRepository.findById(id);

        if(!userEntity.isPresent()){
            return null;
        }

        return userEntity.get();
    }

    public String deleteUser(Integer id){

        userRepository.deleteById(id);

        return "success";
    }


    public User updateUser(Integer id,User user){
        Optional<User> entity = userRepository.findById(id);

        if (!entity.isPresent()) {
            System.out.println("Sonuç bulunamadı");
            return null;}
        else{

            entity.get().setId(id);
            entity.get().setName(user.getName());
            entity.get().setPassword(user.getPassword());
            entity.get().setRole(user.getRole());


            userRepository.save(entity.get());

            return entity.get();
        }
    }




}
