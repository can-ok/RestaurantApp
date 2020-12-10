package com.resturant.restapi.Model;


import org.codehaus.jackson.annotate.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private Set<Product> drinkproducts;



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

    public Set<Product> getDrinkproducts() {
        return drinkproducts;
    }

    public void setDrinkproducts(Set<Product> drinkproducts) {
        this.drinkproducts = drinkproducts;
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
