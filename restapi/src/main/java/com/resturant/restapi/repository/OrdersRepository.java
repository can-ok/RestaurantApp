package com.resturant.restapi.repository;

import com.resturant.restapi.Model.Orders;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrdersRepository extends JpaRepository<Orders,Integer> {
}
