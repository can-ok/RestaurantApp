package com.resturant.restapi.service;

import com.resturant.restapi.Model.Food;
import com.resturant.restapi.Model.ProductCategory;
import com.resturant.restapi.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {
    @Autowired
    ProductCategoryRepository productcategoryRepository;

    public List<ProductCategory> getAll(){

        return productcategoryRepository.findAll();
    }

    public ProductCategory insertCatagory(ProductCategory category){

        return productcategoryRepository.save(category);
    }

    public String deleteUser(Integer id){

        productcategoryRepository.deleteById(id);

        return "success";
    }



    public ProductCategory updateCategory(Integer id,ProductCategory category){

        Optional<ProductCategory> entity=productcategoryRepository.findById(id);

        if(!entity.isPresent()){

            System.out.println("Sonuç bulnamadı");
            return null;
        }
        else{

            entity.get().setId(id);
            entity.get().setName(category.getName());

            productcategoryRepository.save(entity.get());
            return entity.get();
        }
    }

    //update
}
