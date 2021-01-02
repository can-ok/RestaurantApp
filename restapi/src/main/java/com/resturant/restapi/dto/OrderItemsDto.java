package com.resturant.restapi.dto;

import com.resturant.restapi.Model.OrderItems;
import com.resturant.restapi.Model.Orders;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.jnlp.IntegrationService;
import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


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
