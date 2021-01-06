package com.resturant.restapi.converter;

import com.resturant.restapi.Model.Media;
import com.resturant.restapi.Model.Product;
import com.resturant.restapi.Model.ProductCategory;
import com.resturant.restapi.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public class ProductDtoConverter {

    public static Product convertDrinkDtoToDrink(Product entity , ProductDto dto, Optional<ProductCategory> productcategory, Media media){

        entity.getProductcategory().add(productcategory.get());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setMedia(media);
        entity.setTitle(dto.getTitle());

        return entity;
    }



    public static List<ProductDto> convertDrinkListToDrinDtoList(List<ProductDto> dtoList, List<Product> entityList){

        for(Product entity:entityList){

            ProductDto dto=new ProductDto();
            dto.setId(entity.getId());
            dto.setDescription(entity.getDescription());
            dto.setPrice(entity.getPrice());
            dto.setTitle(entity.getTitle());

            dto.setMedia(entity.getMedia());
            dto.setProductcategory(ProductsCategoryDtoConverter.prodCategoryListToProdCategoryDtoList(entity.getProductcategory()));

            dtoList.add(dto);


        }
        return dtoList;
    }





    public static ProductDto convertDrinktoDrinkDto(Product entity){

        ProductDto dto=new ProductDto();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setTitle(entity.getTitle());

        dto.setProductcategory(ProductsCategoryDtoConverter.prodCategoryListToProdCategoryDtoList(entity.getProductcategory()));
        dto.setMedia(entity.getMedia());
        //dto.setProductcategory(category);

        return dto;
    }
}
