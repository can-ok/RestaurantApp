package com.resturant.restapi.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductCategory {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "productcategory")
    @JsonIgnore
    private Set<Drink> Drinkproducts=new HashSet<>();

    @OneToMany(mappedBy = "productcategory")
    @JsonIgnore
    private Set<Food> Foodproducts=new HashSet<>();

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
