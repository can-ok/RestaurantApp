package com.resturant.restapi.repository;

import com.resturant.restapi.Model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrinksRepository extends JpaRepository<Drink,Integer> {


//    List<Drink> findDrinkByProductCategory(String ProductCategor);
//
//
//    @Query("Select DISTINCT PRODUCTCATEGORY from Drink ")
//    List<String> getCategories();

    List<Drink> findDrinkByProductcategoryId(Integer id);

}
