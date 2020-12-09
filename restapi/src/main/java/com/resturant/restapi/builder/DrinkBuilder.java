package com.resturant.restapi.builder;

import com.resturant.restapi.Model.Drink;
import com.resturant.restapi.Model.ProductCategory;
import com.resturant.restapi.dto.DrinkDto;
import com.resturant.restapi.dto.ProductCategoryDto;

public class DrinkBuilder extends Builder{

    private Integer id;

    private String  title;

    private String description;

    private Integer price;

    private ProductCategory productCategoryategory;

    @Override
    public Drink build() {
        Drink drink =new Drink();
        drink.setId(this.id);
        drink.setTitle(this.title);
        drink.setDescription(this.description);
        //drink.setProductcategory(this.productCategoryategory);
        drink.setId(this.id);
        return drink;
    }

    @Override
    public DrinkBuilder id(int id) {
        this.id=id;
        return this;
    }


    public DrinkBuilder title(String title) {
        this.title=title;
        return this;
    }

    public DrinkBuilder description(String description) {
        this.description=description;
        return this;
    }


    public DrinkBuilder price(Integer price) {
        this.price=price;
        return this;
    }

    public DrinkBuilder productcategory(ProductCategory productCategory){
        this.productCategoryategory=productCategory;
        return this;
    }


}
