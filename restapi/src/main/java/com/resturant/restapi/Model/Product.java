package com.resturant.restapi.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String  title;

    private String description;

    private Integer price;



    //@JsonManagedReference
    @ManyToMany()
    @JoinTable(name="TBL_CATEGORY_DRINK",joinColumns = @JoinColumn(name="product_id"),inverseJoinColumns = @JoinColumn(name="category_id"))
    private Set<ProductCategory> productcategory=new HashSet<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price=price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }


    public Set<ProductCategory> getProductcategory() {
        return productcategory;
    }

    public void setProductcategory(Set<ProductCategory> productcategory) {
        this.productcategory = productcategory;
    }
}
