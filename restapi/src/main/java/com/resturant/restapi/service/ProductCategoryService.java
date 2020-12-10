package com.resturant.restapi.service;

import com.resturant.restapi.Model.Media;
import com.resturant.restapi.Model.ProductCategory;
import com.resturant.restapi.converter.ProductsCategoryDtoConverter;
import com.resturant.restapi.dto.ProductCategoryDto;
import com.resturant.restapi.repository.MediaRepository;
import com.resturant.restapi.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productcategoryRepository;

    @Autowired
    private MediaRepository mediaRepository;

    public Set<ProductCategoryDto> getAll(){


        return ProductsCategoryDtoConverter.prodCategoryListToProdCategoryDtoSet(productcategoryRepository.findAll());
    }

    public ProductCategoryDto insertCatagory(ProductCategoryDto categoryDto){
        Media media=mediaRepository.findById(categoryDto.getCategorymedia().getId()).get();
        ProductCategory productCategory = ProductsCategoryDtoConverter.productCategoryDtoToProdCategory(categoryDto);
        productCategory.setCategorymedia(media);
        productcategoryRepository.save(productCategory);
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

        return ProductsCategoryDtoConverter.productCategoryToCategoryDto(productcategoryRepository.findById(id).get());
    }

}
