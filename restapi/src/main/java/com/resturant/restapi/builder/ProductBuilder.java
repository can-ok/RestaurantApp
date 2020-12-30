package com.resturant.restapi.builder;

import com.resturant.restapi.Model.Media;
import com.resturant.restapi.Model.Product;
import com.resturant.restapi.Model.ProductCategory;

import java.util.Set;

public class ProductBuilder extends Builder{

    private Integer id;

    private String  title;

    private String description;

    private Integer price;

    private Set<ProductCategory> productCategoryategory;

    private Media media;

    @Override
    public Product build() {
        Product product =new Product();
        product.setId(this.id);
        product.setTitle(this.title);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setProductcategory(this.productCategoryategory);
        product.setId(this.id);
        product.setMedia(media);
        return product;
    }

    @Override
    public ProductBuilder id(int id) {
        this.id=id;
        return this;
    }


    public ProductBuilder title(String title) {
        this.title=title;
        return this;
    }

    public ProductBuilder description(String description) {
        this.description=description;
        return this;
    }


    public ProductBuilder price(Integer price) {
        this.price=price;
        return this;
    }

    public ProductBuilder productcategory(Set<ProductCategory> productCategory){
        this.productCategoryategory=productCategory;
        return this;
    }

    public ProductBuilder media(Media media){
        this.media=media;
        return this;
    }


}
