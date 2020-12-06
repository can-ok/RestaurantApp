package com.resturant.restapi.repository;

import com.resturant.restapi.Model.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaiterRepository extends JpaRepository<Waiter,Integer> {
}
