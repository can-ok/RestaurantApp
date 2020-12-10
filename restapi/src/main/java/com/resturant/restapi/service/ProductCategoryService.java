package com.resturant.restapi.service;

import com.resturant.restapi.Model.ProductCategory;
import com.resturant.restapi.converter.ProductsCategoryDtoConverter;
import com.resturant.restapi.dto.ProductCategoryDto;
import com.resturant.restapi.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ProductCategoryService {
    @Autowired
    ProductCategoryRepository productcategoryRepository;

    public Set<ProductCategoryDto> getAll(){


        return ProductsCategoryDtoConverter.prodCategoryListToProdCategoryDtoSet(productcategoryRepository.findAll());
    }

    public ProductCategoryDto insertCatagory(ProductCategoryDto categoryDto){
        productcategoryRepository.save(ProductsCategoryDtoConverter.productCategoryDtoToProdCategory(categoryDto));

        return categoryDto;
    }

    public String deleteUser(Integer id){

        productcategoryRepository.deleteById(id);

        return "success";
    }



    public ProductCategoryDto updateCategory(Integer id,ProductCategoryDto category){

        Optional<ProductCategory> entity=productcategoryRepository.findById(id);

        if(!entity.isPresent()){

            System.out.println("Sonuç bulnamadı");
            return null;
        }
        else{

            entity.get().setId(id);
            entity.get().setName(category.getName());

            productcategoryRepository.save(entity.get());
            return category;
        }
    }

    //update
}
