package com.resturant.restapi.dto;

import com.resturant.restapi.Model.Media;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDto {

    @Min(value = 1)
    private Integer id;

    @NotNull(message = "title Can Not BE Null")
    private String  title;
    @NotNull(message = "description Can Not BE Null")
    private String description;
    @NotNull(message = "price Can Not BE Null")
    private Integer price;

    @NotEmpty(message = "productcategory movie list cannot be empty.")
    @NotNull(message = "productcategory Can Not BE Null")
    private Set<ProductCategoryDto> productcategory=new HashSet<>();

    @NotNull(message = "Media Can Not BE Null")
    private Media media;



}
