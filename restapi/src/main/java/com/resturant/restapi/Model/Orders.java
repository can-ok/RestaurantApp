package com.resturant.restapi.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import liquibase.pro.packaged.C;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@SQLDelete(
        sql="UPDATE ORDERS SET deleted=true where id=?")
@Where(clause = "deleted=false")
public class Orders extends BaseEntity {


//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id
//    private Integer Id;

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

    @Column(name="CUSTOMERID")
    private String customerId;

}
