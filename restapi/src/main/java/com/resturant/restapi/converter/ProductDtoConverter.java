package com.resturant.restapi.converter;

import com.resturant.restapi.Model.Product;
import com.resturant.restapi.Model.ProductCategory;
import com.resturant.restapi.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public class ProductDtoConverter {

    public static Product convertDrinkDtoToDrink(Product entity , ProductDto dto, Optional<ProductCategory> productcategory){

        entity.getProductcategory().add(productcategory.get());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setTitle(dto.getTitle());

        return entity;
    }


   /* public static Food convertFoodDtoToFood(FoodDto dto, Optional<ProductCategory> productcategory){

        Food entity=new Food();
        entity.getFoodcategory().add(productcategory.get());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setTitle(dto.getTitle());

        return entity;
    }*/


//    public static List<FoodDto> convertFoodListtoDtoList(List<FoodDto> dtoList,List<Food> entityList){
//
//
//        entityList.stream().forEach( entity->{
//            FoodDto dto=new FoodDto();
//            dto.setId(entity.getId());
//            dto.setDescription(entity.getDescription());
//            dto.setPrice(entity.getPrice());
//            dto.setTitle(entity.getTitle());
//
//
//
//            dto.setFoodcategory(ProductsCategoryDtoConverter.prodCategoryListToProdCategoryDtoList(entity.getFoodcategory()));
////           ProductCategoryDto category=new ProductCategoryDto();
////            category.setId(entity.getProductcategory().getId());
////            category.setName(entity.getProductcategory().getName());
//
//            //dto.getFoodcategory(entity.getFoodcategory());
//
//            dtoList.add(dto);
//        });
//
//        return dtoList;
//    }

    public static List<ProductDto> convertDrinkListToDrinDtoList(List<ProductDto> dtoList, List<Product> entityList){

        for(Product entity:entityList){

            ProductDto dto=new ProductDto();
            dto.setId(entity.getId());
            dto.setDescription(entity.getDescription());
            dto.setPrice(entity.getPrice());
            dto.setTitle(entity.getTitle());

//            ProductCategoryDto category=new ProductCategoryDto();
//            category.setId(entity.getProductcategory().());
//            category.setName(entity.getProductcategory().getName());

            //dto.setProductcategory(category);
            dto.setProductcategory(ProductsCategoryDtoConverter.prodCategoryListToProdCategoryDtoList(entity.getProductcategory()));

            dtoList.add(dto);


        }
        return dtoList;
    }

//    public static FoodDto convertFoodtoFoodDto(Food entity){
//
//        FoodDto dto=new FoodDto();
//        dto.setId(entity.getId());
//        dto.setDescription(entity.getDescription());
//        dto.setPrice(entity.getPrice());
//        dto.setTitle(entity.getTitle());
///*
//        ProductCategoryDto category=new ProductCategoryDto();
//        category.setId(entity.getProductcategory().getId());
//        category.setName(entity.getProductcategory().getName());*/
//
//        //dto.setFoodcategory(entity.getFoodcategory());
//
//        return dto;
//    }


    public static ProductDto convertDrinktoDrinkDto(Product entity){

        ProductDto dto=new ProductDto();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setTitle(entity.getTitle());

//        ProductCategoryDto category=new ProductCategoryDto();
//        category.setId(entity.getProductcategory().getId());
//        category.setName(entity.getProductcategory().getName());

        //dto.setProductcategory(category);

        return dto;
    }
}
