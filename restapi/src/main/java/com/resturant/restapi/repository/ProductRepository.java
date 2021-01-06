package com.resturant.restapi.repository;

import com.resturant.restapi.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {


//    List<Drink> findDrinkByProductCategory(String ProductCategor);
//
//
//    @Query("Select DISTINCT PRODUCTCATEGORY from Drink ")
//    List<String> getCategories();

    //List<Product> findProductByProductcategoryId(Integer id);


    Slice<Product> findProductByProductcategoryId(Integer id, Pageable pagable);

    @Query("Select p from Product p")
    Page<Product> findAllProducts(Pageable pagable);

    @Query("Select p from Product p")
    Slice<Product> findAllProd(Pageable pageable);

}
