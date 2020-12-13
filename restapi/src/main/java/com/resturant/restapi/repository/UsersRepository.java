package com.resturant.restapi.repository;

import com.resturant.restapi.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {


//    Users findByUserName(String UserName);

    @Query("Select f from Users f where f.USERNAME=:name AND f.Password=:password")
    Optional<Users> getUserByNameANDPass(String name,String password);

    @Query("Select f from Users  f where f.USERNAME=:name")
    Optional<Users> getUsersByUSERNAME(String name);

}
