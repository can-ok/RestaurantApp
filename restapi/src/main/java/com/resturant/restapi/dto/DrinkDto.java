package com.resturant.restapi.dto;

import com.resturant.restapi.Model.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
public class DrinkDto extends ProductDto{


    private Integer id;

    private String  title;

    private String description;

    private Integer price;

    private Set<ProductCategoryDto> productcategory=new HashSet<>();




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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Set<ProductCategoryDto> getProductcategory() {
        return productcategory;
    }

    public void setProductcategory(Set<ProductCategoryDto> productcategory) {
        this.productcategory = productcategory;
    }
}
