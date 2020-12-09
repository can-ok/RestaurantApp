package com.resturant.restapi.dto;

import com.resturant.restapi.Model.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FoodDto  extends ProductDto {

    private Integer id;

    private String  title;

    private String description;

    private Integer price;

    private Set<ProductCategoryDto> foodcategory=new HashSet<>();

}
