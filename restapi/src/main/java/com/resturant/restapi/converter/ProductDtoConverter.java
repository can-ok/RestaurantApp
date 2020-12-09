package com.resturant.restapi.converter;

import com.resturant.restapi.Model.Drink;
import com.resturant.restapi.Model.Food;
import com.resturant.restapi.Model.ProductCategory;
import com.resturant.restapi.dto.DrinkDto;
import com.resturant.restapi.dto.FoodDto;
import com.resturant.restapi.dto.ProductCategoryDto;

import java.util.List;
import java.util.Optional;

public class ProductDtoConverter {

    public static Drink convertDrinkDtoToDrink(Drink entity ,DrinkDto dto, Optional<ProductCategory> productcategory){

        entity.getProductcategory().add(productcategory.get());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setTitle(dto.getTitle());

        return entity;
    }


    public static Food convertFoodDtoToFood(FoodDto dto, Optional<ProductCategory> productcategory){

        Food entity=new Food();
        entity.getFoodcategory().add(productcategory.get());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setTitle(dto.getTitle());

        return entity;
    }


    public static List<FoodDto> convertFoodListtoDtoList(List<FoodDto> dtoList,List<Food> entityList){


        entityList.stream().forEach( entity->{
            FoodDto dto=new FoodDto();
            dto.setId(entity.getId());
            dto.setDescription(entity.getDescription());
            dto.setPrice(entity.getPrice());
            dto.setTitle(entity.getTitle());



            dto.setFoodcategory(ProductsCategoryDtoConverter.prodCategoryListToProdCategoryDtoList(entity.getFoodcategory()));
//           ProductCategoryDto category=new ProductCategoryDto();
//            category.setId(entity.getProductcategory().getId());
//            category.setName(entity.getProductcategory().getName());

            //dto.getFoodcategory(entity.getFoodcategory());

            dtoList.add(dto);
        });

        return dtoList;
    }

    public static List<DrinkDto> convertDrinkListToDrinDtoList(List<DrinkDto> dtoList, List<com.resturant.restapi.Model.Drink> entityList){

        for(com.resturant.restapi.Model.Drink entity:entityList){

            DrinkDto dto=new DrinkDto();
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

    public static FoodDto convertFoodtoFoodDto(Food entity){

        FoodDto dto=new FoodDto();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setTitle(entity.getTitle());
/*
        ProductCategoryDto category=new ProductCategoryDto();
        category.setId(entity.getProductcategory().getId());
        category.setName(entity.getProductcategory().getName());*/

        //dto.setFoodcategory(entity.getFoodcategory());

        return dto;
    }


    public static DrinkDto convertDrinktoDrinkDto(com.resturant.restapi.Model.Drink entity){

        DrinkDto dto=new DrinkDto();
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
