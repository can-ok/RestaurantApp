package com.resturant.restapi.dto;


import com.resturant.restapi.Model.Customer;
import com.resturant.restapi.Model.OrderItems;
import com.resturant.restapi.Model.Waiter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsDto {

    private Integer Id;

    Set<OrderItemsDto> orderItemsDtoList;

    OrdersDto ordersDto;

    private Integer waiterId;

    private Integer customerId;




//
//    private Integer totalPrice;
//
//
//    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
//    private Date orderDate = new Timestamp(System.currentTimeMillis());
//
//    @NotNull(message = "paymentType Can Not BE Null")
//    private String paymentType;
//
//    private String orderTable;
//
//    //@NotNull(message = "waiterId Can Not BE Null")
//    private String waiterId;
//
//    //@NotNull(message = "customerId Can Not BE Null")
//    private Customer customerId;



}
