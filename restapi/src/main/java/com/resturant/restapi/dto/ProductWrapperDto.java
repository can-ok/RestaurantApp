package com.resturant.restapi.dto;

import java.util.List;

public class ProductWrapperDto {
    List<ProductDto> listproductDto;
    Boolean hasNext;

    public List<ProductDto> getListproductDto() {
        return listproductDto;
    }

    public void setListproductDto(List<ProductDto> listproductDto) {
        this.listproductDto = listproductDto;
    }

    public Boolean getHasNext() {
        return hasNext;
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }
}
