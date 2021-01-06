package com.resturant.restapi.dto;

import com.resturant.restapi.Model.OrderItems;
import com.resturant.restapi.Model.Waiter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrdersDto {

    private Integer id;

//    @NotNull(message = "totalPrice Can Not BE Null")
//    @Min(value = 0,message = "totalPrice Can Not BE Lower Than 0")
    private Integer totalPrice;


    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date orderDate = new Timestamp(System.currentTimeMillis());
//
//    @NotNull(message = "paymentType Can Not BE Null")
    private String paymentType;

//    @NotNull(message = "orderTable Can Not BE Null")
    private String orderTable;

    //@NotNull(message = "waiterId Can Not BE Null")
    private Waiter waiterId;

    //@NotNull(message = "customerId Can Not BE Null")
    private Integer customerId;


//    @NotNull(message = "orderItems Can Not BE Null")
    private Set<OrderItems> orderItems;



}
