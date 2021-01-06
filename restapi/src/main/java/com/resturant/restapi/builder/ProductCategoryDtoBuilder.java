package com.resturant.restapi.builder;

import com.resturant.restapi.converter.MediaDtoConverter;
import com.resturant.restapi.dto.ProductCategoryDto;

public class ProductCategoryDtoBuilder extends Builder{


    private Integer id;

    private String name;

    private String description;

    @Override
    public ProductCategoryDto build() {

        ProductCategoryDto productCategory = new ProductCategoryDto();
        productCategory.setId(this.id);
        productCategory.setName(this.name);
        byte[] fileBytes = "deneme".getBytes();
        productCategory.setCategorymedia(MediaDtoConverter.mediaDtoToMedia(new MediaDtoBuilder().fileContent(fileBytes).id(1).name("deneme").build()));
        productCategory.setDescription(this.description);
        return productCategory;
    }

    @Override
    public ProductCategoryDtoBuilder id(int id) {
        this.id=id;
        return this;
    }
    public ProductCategoryDtoBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ProductCategoryDtoBuilder description(String description) {
        this.description = description;
        return this;
    }

}
