package com.resturant.restapi.dto;

import com.resturant.restapi.Model.Drink;
import com.resturant.restapi.Model.Food;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryDto {

    private Integer id;

    private String name;


    private Set<Drink> Drinkproducts;

    private Set<Food> Foodproducts;


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
        Drinkproducts = drinkproducts;
    }

    public Set<Food> getFoodproducts() {
        return Foodproducts;
    }

    public void setFoodproducts(Set<Food> foodproducts) {
        Foodproducts = foodproducts;
    }
}
