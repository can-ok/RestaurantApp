package com.resturant.restapi.service;

import com.resturant.restapi.Model.Media;
import com.resturant.restapi.Model.ProductCategory;
import com.resturant.restapi.converter.ProductMapper;
import com.resturant.restapi.converter.ProductsCategoryDtoConverter;
import com.resturant.restapi.converter.ProductsCategoryMapper;
import com.resturant.restapi.dto.ProductCategoryDto;
import com.resturant.restapi.repository.MediaRepository;
import com.resturant.restapi.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productcategoryRepository;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    ProductsCategoryMapper productsCategoryMapper;

    public Set<ProductCategoryDto> getAll(){
        Set<ProductCategoryDto> productCategoryDtoList=new HashSet<>();
        productcategoryRepository.findAll().forEach(productCategory -> {

            ProductCategoryDto productCategoryDto=productsCategoryMapper.toDto(productCategory);

            productCategoryDtoList.add(productCategoryDto);
        });
        return productCategoryDtoList;
    }

    public ProductCategoryDto insertCatagory(ProductCategoryDto categoryDto){
        Media media=mediaRepository.findById(categoryDto.getCategorymedia().getId()).get();

        ProductCategory productCategory = productsCategoryMapper.toEntity(categoryDto);

        productCategory.setCategorymedia(media);
        productcategoryRepository.save(productCategory);
        return categoryDto;
    }

    public String deleteUser(Integer id){
        Optional<ProductCategory> byId = productcategoryRepository.findById(id);
        if (byId.isPresent())
        {
            byId.get().setCategorymedia(null);
            productcategoryRepository.deleteById(id);
            return "success";

        }
        return "Fail";
    }



    public ProductCategoryDto updateCategory(Integer id,ProductCategoryDto category){

        Optional<ProductCategory> entity=productcategoryRepository.findById(id);

        if(!entity.isPresent()){

            System.out.println("Sonuç bulnamadı");
            return null;
        }
        else{

            Media updateMedia=mediaRepository.findById(entity.get().getCategorymedia().getId()).get();
            entity.get().setId(id);
            entity.get().setName(category.getName());
            entity.get().setDescription(category.getDescription());
            entity.get().setCategorymedia(updateMedia);
            productcategoryRepository.save(entity.get());
            return category;
        }
    }

    public ProductCategoryDto getDrinkById(int id){


        return productsCategoryMapper.toDto(productcategoryRepository.findById(id).get());
    }

}
