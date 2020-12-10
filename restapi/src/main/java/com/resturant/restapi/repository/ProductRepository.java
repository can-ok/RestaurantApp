package com.resturant.restapi.repository;

import com.resturant.restapi.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {


//    List<Drink> findDrinkByProductCategory(String ProductCategor);
//
//
//    @Query("Select DISTINCT PRODUCTCATEGORY from Drink ")
//    List<String> getCategories();

    List<Product> findProductByProductcategoryId(Integer id);

}
