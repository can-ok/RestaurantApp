package com.resturant.restapi.builder;


import com.resturant.restapi.Model.Orders;
import org.hibernate.criterion.Order;

import java.sql.Timestamp;
import java.util.Date;

public class OrdersBuilder extends Builder {

    private Integer id;

    private Integer productId;

    private Integer productCount;

    private Integer totalPrice;

    private Date orderDate = new Timestamp(System.currentTimeMillis());

    private String paymentType;

    private String orderTable;

    private String waiterId;

    @Override
    public Orders build() {
        Orders orders=new Orders();
        orders.setWaiterId(this.waiterId);
        orders.setTotalPrice(this.totalPrice);
        orders.setOrderTable(this.orderTable);
        orders.setProductId(this.productId);
        orders.setProductCount(this.productCount);
        orders.setPaymentType(this.paymentType);
        return orders;
    }

    @Override
    public OrdersBuilder id(int id) {
        this.id=id;
        return this;
    }


    public OrdersBuilder waiterId(String waiterId) {
        this.waiterId=waiterId;
        return this;
    }

    public OrdersBuilder orderTable(String orderTable) {
        this.orderTable=orderTable;
        return this;
    }

    public OrdersBuilder paymentType(String paymentType) {
        this.paymentType=paymentType;
        return this;
    }

    public OrdersBuilder productId(Integer productId) {
        this.productId=productId;
        return this;
    }

    public OrdersBuilder productCount(Integer productCount) {
        this.productCount=productCount;
        return this;
    }

    public OrdersBuilder totalPrice(Integer totalPrice) {
        this.totalPrice=totalPrice;
        return this;
    }



}
