package com.resturant.restapi.dto;

import com.resturant.restapi.Model.Media;
import com.resturant.restapi.Model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryDto implements Serializable {

    private Integer id;

    private String name;

    private String description;

//    private Set<ProductDto> products;

    private Media categorymedia;


}
