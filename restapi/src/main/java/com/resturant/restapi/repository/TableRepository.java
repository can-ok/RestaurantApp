package com.resturant.restapi.repository;

import com.resturant.restapi.Model.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<Tables,Integer> {
}
