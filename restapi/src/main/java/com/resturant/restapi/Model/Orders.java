package com.resturant.restapi.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import liquibase.pro.packaged.C;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer Id;

    @Column(name="PRODUCTID")
    private Integer productId;

    @Column(name ="PRODUCTCOUNT")
    private Integer productCount;

    @Column(name="TOTALPRICE")
    private Integer totalPrice;


    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @Column(name="ORDERDATE")
    private Date orderDate = new Timestamp(System.currentTimeMillis());

    @Column(name = "PAYMENTTYPE")
    private String paymentType;

    @Column(name="ORDERTABLE")
    private String orderTable;


    @Column(name="WAITERID")
    private String waiterId;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getOrderTable() {
        return orderTable;
    }

    public void setOrderTable(String orderTable) {
        this.orderTable = orderTable;
    }

    public String getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(String waiterId) {
        this.waiterId = waiterId;
    }
}
