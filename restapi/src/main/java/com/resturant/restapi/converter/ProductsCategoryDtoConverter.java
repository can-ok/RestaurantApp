package com.resturant.restapi.converter;

import com.resturant.restapi.Model.Media;
import com.resturant.restapi.Model.ProductCategory;
import com.resturant.restapi.dto.ProductCategoryDto;
import liquibase.pro.packaged.M;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductsCategoryDtoConverter {


    public static ProductCategoryDto productCategoryToCategoryDto(ProductCategory productCategory){

        ProductCategoryDto productCategoryDto=new ProductCategoryDto();

        productCategoryDto.setName(productCategory.getName());
        productCategoryDto.setId(productCategory.getId());
        productCategoryDto.setDescription(productCategory.getDescription());
        //productCategoryDto.setDrinkproducts(productCategory.getDrinkproducts());
        productCategoryDto.setCategorymedia(productCategory.getCategorymedia());

        return productCategoryDto;

    }

    public static Set<ProductCategoryDto> prodCategoryListToProdCategoryDtoList(Set<ProductCategory> productCategories){

        Set<ProductCategoryDto> productCategoryDtoList=new HashSet<>();

        productCategories.forEach((entity)->{

            ProductCategoryDto productCategoryDto=productCategoryToCategoryDto(entity);

            productCategoryDtoList.add(productCategoryDto);

        });
        return productCategoryDtoList;
    }



    public static Set<ProductCategoryDto> prodCategoryListToProdCategoryDtoSet(List<ProductCategory> productCategories){

        Set<ProductCategoryDto> productCategoryDtoList=new HashSet<>();

        productCategories.forEach((entity)->{

            ProductCategoryDto productCategoryDto=productCategoryToCategoryDto(entity);

            productCategoryDtoList.add(productCategoryDto);

        });
        return productCategoryDtoList;
    }


    public static List<ProductCategory> prodCategoryDtoSetToProdCategoryList(Set<ProductCategoryDto> productCategories){

        List<ProductCategory> productCategoryList=new ArrayList<>();

        productCategories.forEach((entity)->{

            ProductCategory productCategory=productCategoryDtoToProdCategory(entity);

            productCategoryList.add(productCategory);

        });
        return productCategoryList;
    }


    public static Set<ProductCategory> prodCategoryDtoListToProductCategoryList(Set<ProductCategoryDto> productCategoriesDto){

        Set<ProductCategory> productCategory=new HashSet<>();

        productCategoriesDto.forEach((entity)->{

            ProductCategory productCategoryDto=productCategoryDtoToProdCategory(entity);

            productCategory.add(productCategoryDto);

        });
        return productCategory;
    }


    public static ProductCategory productCategoryDtoToProdCategory(ProductCategoryDto productCategoryDto){
        ProductCategory productCategory=new ProductCategory();

        productCategory.setDescription(productCategoryDto.getDescription());
        productCategory.setName(productCategoryDto.getName());
        productCategory.setId(productCategoryDto.getId());


        return productCategory;

    }
}
