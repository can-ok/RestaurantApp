package com.resturant.restapi.converter;

import com.resturant.restapi.Model.Product;
import com.resturant.restapi.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(ignore = true,source = "productcategory",target = "productcategory")
    ProductDto toDto(Product product);

    @Mapping(ignore = true,source = "productcategory",target = "productcategory")
    @Mapping(ignore = true,source = "media",target = "media")
    Product toEntity(ProductDto productDto);

    List<ProductDto> toDtoList(List<Product> products);
}
