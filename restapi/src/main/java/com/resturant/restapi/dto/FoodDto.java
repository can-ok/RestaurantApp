package com.resturant.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FoodDto  extends ProductDto {

    private Integer id;

    private String  title;

    private String description;

    private Integer price;

    private ProductCategoryDto productcategory;

}
