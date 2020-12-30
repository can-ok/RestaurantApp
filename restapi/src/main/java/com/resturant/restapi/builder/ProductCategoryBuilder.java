package com.resturant.restapi.builder;

import com.resturant.restapi.Model.ProductCategory;
import com.resturant.restapi.converter.MediaDtoConverter;

public class ProductCategoryBuilder extends Builder {

    private Integer id;

    private String name;

    private String description;

    @Override
    public ProductCategory build() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(this.id);
        productCategory.setName(this.name);
        byte[] fileBytes = "deneme".getBytes();
        productCategory.setCategorymedia(MediaDtoConverter.mediaDtoToMedia(new MediaDtoBuilder().fileContent(fileBytes).id(1).name("deneme").build()));
        productCategory.setDescription(this.description);
        return productCategory;
    }

    @Override
    public ProductCategoryBuilder id(int id) {
        this.id = id;
        return this;
    }


    public ProductCategoryBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ProductCategoryBuilder description(String description) {
        this.description = description;
        return this;
    }



}
