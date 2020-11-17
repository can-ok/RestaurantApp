package com.resturant.restapi.repository;

import com.resturant.restapi.Model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinksRepository extends JpaRepository<Drink,Integer> {
}
