package com.resturant.restapi.repository;

import com.resturant.restapi.Model.Food;
import com.resturant.restapi.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {


//    Users findByUserName(String UserName);

    @Query("Select f from Users f where f.UserName=:name AND f.Password=:password")
    Optional<Users> getUserName(String name,String password);

}
