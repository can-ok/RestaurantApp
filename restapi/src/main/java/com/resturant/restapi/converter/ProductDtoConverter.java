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

    public static Drink convertDrinkDtoToDrink(DrinkDto dto, Optional<ProductCategory> productcategory){

        Drink entity=new Drink();
        entity.setProductcategory(productcategory.get());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setTitle(dto.getTitle());

        return entity;
    }


    public static Food convertFoodDtoToFood(FoodDto dto, Optional<ProductCategory> productcategory){

        Food entity=new Food();
        entity.setProductcategory(productcategory.get());
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

            ProductCategoryDto category=new ProductCategoryDto();
            category.setId(entity.getProductcategory().getId());
            category.setName(entity.getProductcategory().getName());

            dto.setProductcategory(category);

            dtoList.add(dto);
        });

        return dtoList;
    }

    public static List<DrinkDto> convertDrinkListToDrinDtoList(List<DrinkDto> dtoList,List<Drink> entityList){

        for(Drink entity:entityList){

            DrinkDto dto=new DrinkDto();
            dto.setId(entity.getId());
            dto.setDescription(entity.getDescription());
            dto.setPrice(entity.getPrice());
            dto.setTitle(entity.getTitle());

            ProductCategoryDto category=new ProductCategoryDto();
            category.setId(entity.getProductcategory().getId());
            category.setName(entity.getProductcategory().getName());

            dto.setProductcategory(category);

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

        ProductCategoryDto category=new ProductCategoryDto();
        category.setId(entity.getProductcategory().getId());
        category.setName(entity.getProductcategory().getName());

        dto.setProductcategory(category);

        return dto;
    }


    public static DrinkDto convertDrinktoDrinkDto(Drink entity){

        DrinkDto dto=new DrinkDto();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setTitle(entity.getTitle());

        ProductCategoryDto category=new ProductCategoryDto();
        category.setId(entity.getProductcategory().getId());
        category.setName(entity.getProductcategory().getName());

        dto.setProductcategory(category);

        return dto;
    }
}
