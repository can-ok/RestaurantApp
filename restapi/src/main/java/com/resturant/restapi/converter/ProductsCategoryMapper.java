package com.resturant.restapi.converter;

import com.resturant.restapi.Model.ProductCategory;
import com.resturant.restapi.dto.ProductCategoryDto;
import com.resturant.restapi.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ProductsCategoryMapper {


    @Mapping(
            ignore = true,
            source = "products",target = "products")
    ProductCategoryDto toDto(ProductCategory productCategory);


    ProductCategory toEntity(ProductCategoryDto productCategoryDto);


    Set<ProductCategoryDto> toProductCategoryDtoSet(Set<ProductCategory> productCategories);

    List<ProductCategory> toProductCategorySet(Set<ProductCategoryDto> productCategories);
}
