package com.resturant.restapi.dto;

import com.resturant.restapi.Model.Media;
import com.resturant.restapi.Model.Product;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryDto {

    private Integer id;

    private String name;

    private String description;

    private Set<ProductDto> drinkproducts;

    private Media categorymedia;


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


    public Set<ProductDto> getDrinkproducts() {
        return drinkproducts;
    }

    public void setDrinkproducts(Set<ProductDto> drinkproducts) {
        this.drinkproducts = drinkproducts;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Media getCategorymedia() {
        return categorymedia;
    }

    public void setCategorymedia(Media categorymedia) {
        this.categorymedia = categorymedia;
    }
}
