package com.resturant.restapi.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Food extends Product{


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column
    private String productCategory;
    @Column
    private String  title;
    @Column
    private int price;
    @Column
    private String description;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getProductCategory() {
        return productCategory;
    }

    @Override
    public void setProductCategory(String productCategory) {
        this.productCategory=productCategory;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title=title;

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
    public void setDescription(String Description) {

        this.description=Description;
    }
}