package com.resturant.restapi.builder;

import com.resturant.restapi.dto.MediaDto;

public class MediaDtoBuilder extends Builder{

    private Integer id;

    private String name;

    private byte[] fileContent;

    @Override
    public MediaDto build() {
        MediaDto mediaDto=new MediaDto();
        mediaDto.setId(this.id);
        mediaDto.setName(this.name);
        mediaDto.setFileContent(this.fileContent);
        return mediaDto;
    }

    @Override
    public MediaDtoBuilder id(int id) {
        this.id=id;
        return this;
    }


    public MediaDtoBuilder fileContent(byte[] fileContent){
        this.fileContent=fileContent;
        return this;
    }


    public MediaDtoBuilder name(String name){
        this.name=name;
        return this;
    }


}
