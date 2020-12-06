package com.resturant.restapi.converter;

import com.resturant.restapi.Model.ProductCategory;
import com.resturant.restapi.dto.ProductCategoryDto;

import java.util.ArrayList;
import java.util.List;

public class ProductsCategoryDtoConverter {


    public static ProductCategoryDto productCategoryToCategoryDto(ProductCategory productCategory){
        ProductCategoryDto productCategoryDto=new ProductCategoryDto();

        productCategoryDto.setName(productCategory.getName());
        productCategoryDto.setId(productCategory.getId());

        return productCategoryDto;

    }

    public static List<ProductCategoryDto> prodCategoryListToProdCategoryDtoList(List<ProductCategory> productCategories){

        List<ProductCategoryDto> productCategoryDtoList=new ArrayList<>();

        productCategories.forEach((entity)->{

            ProductCategoryDto productCategoryDto=productCategoryToCategoryDto(entity);

            productCategoryDtoList.add(productCategoryDto);

        });
        return productCategoryDtoList;
    }


    public static List<ProductCategory> prodCategoryDtoListToProductCategoryList(List<ProductCategoryDto> productCategoriesDto){

        List<ProductCategory> productCategory=new ArrayList<>();

        productCategoriesDto.forEach((entity)->{

            ProductCategory productCategoryDto=productCategoryDtoToProdCategory(entity);

            productCategory.add(productCategoryDto);

        });
        return productCategory;
    }


    public static ProductCategory productCategoryDtoToProdCategory(ProductCategoryDto productCategoryDto){
        ProductCategory productCategory=new ProductCategory();

        productCategory.setName(productCategoryDto.getName());
        productCategory.setId(productCategoryDto.getId());

        return productCategory;

    }
}
