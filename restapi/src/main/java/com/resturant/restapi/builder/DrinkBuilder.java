package com.resturant.restapi.builder;

import com.resturant.restapi.Model.Product;
import com.resturant.restapi.Model.ProductCategory;

public class DrinkBuilder extends Builder{

    private Integer id;

    private String  title;

    private String description;

    private Integer price;

    private ProductCategory productCategoryategory;

    @Override
    public Product build() {
        Product product =new Product();
        product.setId(this.id);
        product.setTitle(this.title);
        product.setDescription(this.description);
        //drink.setProductcategory(this.productCategoryategory);
        product.setId(this.id);
        return product;
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
