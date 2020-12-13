package com.resturant.restapi.repository;

import com.resturant.restapi.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Role,Integer> {




}
