package com.resturant.restapi.Model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import org.codehaus.jackson.annotate.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT_CATEGORY")
public class ProductCategory {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String name;


    @JsonIgnore
    @ManyToMany(mappedBy = "productcategory")
    private Set<Drink> Drinkproducts;

    @JsonIgnore
    @ManyToMany(mappedBy = "foodcategory")
    private Set<Food> Foodproducts;

    public ProductCategory(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Drink> getDrinkproducts() {
        return Drinkproducts;
    }

    public void setDrinkproducts(Set<Drink> drinkproducts) {
        this.Drinkproducts = drinkproducts;
    }

    public Set<Food> getFoodproducts() {
        return Foodproducts;
    }

    public void setFoodproducts(Set<Food> foodproducts) {
        this.Foodproducts = foodproducts;
    }

//for hold recursion

//
//    public void setDrinkproducts(Set<Drink> drinkproducts) {
//        Drinkproducts = drinkproducts;
//    }
//
//    public Set<Drink> getDrinkproducts() {
//        return Drinkproducts;
//    }
//
//    public Set<Food> getFoodproducts() {
//        return Foodproducts;
//    }
//
//    public void setFoodproducts(Set<Food> foodproducts) {
//        Foodproducts = foodproducts;
//    }


}
