package com.resturant.restapi.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Drink extends Product implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name="PRODUCTCATEGORY_id")
//    private PRODUCTCATEGORY productCategory;




    private String  title;



    private String description;

    private Integer price;



    public void setProductcategory(ProductCategory productcategory) {
        this.productcategory = productcategory;
    }

    @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name="category_id")
   private ProductCategory productcategory;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void setPrice(int price) {
        this.price=price;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }



    public void setPrice(Integer price) {
        this.price = price;
    }

    public ProductCategory getProductcategory() {
        return productcategory;
    }

}
