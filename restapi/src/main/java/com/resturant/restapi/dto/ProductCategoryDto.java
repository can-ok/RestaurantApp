package com.resturant.restapi.dto;

import com.resturant.restapi.Model.Media;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryDto implements Serializable {

    @Min(value = 0,message = "Id Can Not Be less than 1")
    private Integer id;

    @NotNull(message = "name Can Not BE Null")
    private String name;

    @NotNull(message = "description Can Not BE Null")
    private String description;

//    private Set<ProductDto> products;

    @NotNull(message = "categorymedia Can Not BE Null")
    private Media categorymedia;


}
