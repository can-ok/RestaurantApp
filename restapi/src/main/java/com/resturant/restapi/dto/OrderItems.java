package com.resturant.restapi.dto;

import javax.jnlp.IntegrationService;
import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderItems {


    private Integer Id;

    @NotNull(message = "productId Can Not BE Null")
    private Integer productId;

    @NotNull(message = "productCount Can Not BE Null")
    private Integer productCount;

    @NotNull(message = "totalPrice Can Not BE Null")
    @Min(value = 0,message = "totalPrice Can Not BE Lower Than 0")
    private Integer productPrice;

}
