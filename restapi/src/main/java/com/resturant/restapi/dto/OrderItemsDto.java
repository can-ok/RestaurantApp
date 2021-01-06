package com.resturant.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemsDto {


    private Integer Id;

    @NotNull(message = "productId Can Not BE Null")
    private Integer productId;

    @NotNull(message = "productCount Can Not BE Null")
    private Integer productCount;

    @NotNull(message = "totalPrice Can Not BE Null")
    @Min(value = 0,message = "totalPrice Can Not BE Lower Than 0")
    private Integer productPrice;


    private Integer orderId;


}
