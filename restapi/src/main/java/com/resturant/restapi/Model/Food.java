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
public class Food extends Product implements Serializable {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;


    private String  title;

    private String description;


    private Integer price;


    //@JsonManagedReference
    @ManyToMany()
    @JoinTable(name="TBL_CATEGORY_FOOD",joinColumns = @JoinColumn(name="product_id"),inverseJoinColumns = @JoinColumn(name="category_id"))
    private Set<ProductCategory> foodcategory=new HashSet<>();


    public Set<ProductCategory> getFoodcategory() {
        return foodcategory;
    }

    public void setFoodcategory(Set<ProductCategory> foodcategory) {
        this.foodcategory = foodcategory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPrice(Integer price) {
        this.price = price;
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
