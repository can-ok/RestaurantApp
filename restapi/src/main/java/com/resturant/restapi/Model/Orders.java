package com.resturant.restapi.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

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
//
//    @Column(name="PRODUCTID")
//    private Integer productId;
//
//    @Column(name ="PRODUCTCOUNT")
//    private Integer productCount;

    @Column(name="TOTALPRICE")
    private Integer totalPrice;


    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @Column(name="ORDERDATE")
    private Date orderDate = new Timestamp(System.currentTimeMillis());

    @Column(name = "PAYMENTTYPE")
    private String paymentType;

    @Column(name="ORDERTABLE")
    private String orderTable;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "WAITERID")
    private Waiter waiterId;

//    @Column(name="CUSTOMERNAME")
//    private String customerName;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "CUSTOMERID")
//    private Customer customerId;

    @Column(name="CUSTOMERID")
    private Integer customerId;


    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "orderId")
    private Set<OrderItems> orderItems;




}
