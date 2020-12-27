package com.resturant.restapi.builder;

import com.resturant.restapi.dto.OrdersDto;

import java.sql.Timestamp;
import java.util.Date;

public class OrdersDtoBuilder extends Builder {

    private Integer id;

    private Integer productId;

    private Integer productCount;

    private Integer totalPrice;

    private Date orderDate = new Timestamp(System.currentTimeMillis());

    private String paymentType;

    private String orderTable;

    private String waiterId;


    @Override
    public OrdersDto build() {

        OrdersDto dto=new OrdersDto();
        dto.setWaiterId(this.waiterId);
        dto.setTotalPrice(this.totalPrice);
        dto.setOrderTable(this.orderTable);
        dto.setProductId(this.productId);
        dto.setProductCount(this.productCount);
        dto.setPaymentType(this.paymentType);
        return dto;
    }

    public OrdersDtoBuilder waiterId(String waiterId) {
        this.waiterId=waiterId;
        return this;
    }

    public OrdersDtoBuilder orderTable(String orderTable) {
        this.orderTable=orderTable;
        return this;
    }

    public OrdersDtoBuilder paymentType(String paymentType) {
        this.paymentType=paymentType;
        return this;
    }

    public OrdersDtoBuilder productId(Integer productId) {
        this.productId=productId;
        return this;
    }

    public OrdersDtoBuilder productCount(Integer productCount) {
        this.productCount=productCount;
        return this;
    }

    public OrdersDtoBuilder totalPrice(Integer totalPrice) {
        this.totalPrice=totalPrice;
        return this;
    }


    @Override
    public OrdersDtoBuilder id(int Id) {
        this.id=Id;
        return this;
    }


}
