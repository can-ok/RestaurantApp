package com.resturant.restapi.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ORDERITEMS")
@Getter
@Setter
@SQLDelete(
        sql="UPDATE ORDERS SET deleted=true where id=?")
@Where(clause = "deleted=false")
public class OrderItems extends BaseEntity{


    @Column(name="PRODUCTID")
    private Integer productId;

    @Column(name ="PRODUCTCOUNT")
    private Integer productCount;

    @Column(name="PRODUCTPRICE")
    private Integer productPrice;

//
//    @JsonBackReference
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="ORDERID")
//    private Orders orderId;


    @Column(name = "ORDERID")
    private Integer orderId;




}
