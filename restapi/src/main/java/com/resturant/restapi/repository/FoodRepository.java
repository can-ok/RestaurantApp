package com.resturant.restapi.repository;

import com.resturant.restapi.Model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food,Integer> {

    //Select * from Food where PRODUCT_CATEGORY ='Yemek'
//    @Query("select Food.id,Food .description,Food .price from Food where PRODUCT_CATEGORY=:category")
//    String findFoodByProductCategory(String category);

//    @Query("Select f from Food f where f.productCategory=:name")
//    List<Food> getCategoryByFoods(String name);
//
//
//    @Query("Select DISTINCT PRODUCTCATEGORY from Food")
//    List<String> getCategories();

    List<Food> findFoodByProductcategoryId(Integer id);

}
