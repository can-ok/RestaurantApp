package com.resturant.restapi.builder;

import com.resturant.restapi.Model.Media;
import com.resturant.restapi.dto.MediaDto;
import liquibase.pro.packaged.B;

public class MediaBuilder extends Builder {


    private Integer id;

    private String name;

    private byte[] fileContent;

    @Override
    public Media build() {
        Media mediaDto=new Media();
        mediaDto.setId(this.id);
        mediaDto.setName(this.name);
        mediaDto.setFileContent(this.fileContent);
        return mediaDto;
    }

    @Override
    public MediaBuilder id(int id) {
        this.id=id;
        return this;
    }


    public MediaBuilder fileContent(byte[] fileContent){
        this.fileContent=fileContent;
        return this;
    }


    public MediaBuilder name(String name){
        this.name=name;
        return this;
    }
}
