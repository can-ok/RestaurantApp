package com.resturant.restapi.builder;

import com.resturant.restapi.Model.Media;
import com.resturant.restapi.dto.WaiterDto;

import java.util.Date;

public class WaiterDtoBuilder extends Builder {

    private Integer id;

    private String firstname;

    private String lastname;

    private String email;

    private Date birtdate;

    private Media media;

    @Override
    public WaiterDto build() {
        WaiterDto waiter=new WaiterDto();
        waiter.setId(this.id);
        waiter.setFirstname(this.firstname);
        waiter.setLastname(this.lastname);
        waiter.setBirtdate(this.birtdate);
        waiter.setEmail(this.email);
        waiter.setMedia(this.media);
        return waiter;
    }

    @Override
    public WaiterDtoBuilder id(int id) {
        this.id=id;
        return this;
    }

    public WaiterDtoBuilder firstname(String firstname) {

        this.firstname=firstname;
        return this;
    }

    public WaiterDtoBuilder lastname(String lastname) {

        this.lastname=lastname;
        return this;
    }

    public WaiterDtoBuilder email(String email) {

        this.email=email;
        return this;
    }

    public WaiterDtoBuilder birtdate(Date birtdate) {

        this.birtdate=birtdate;
        return this;
    }

    public WaiterDtoBuilder media(Media media){
        this.media=media;
        return this;
    }
}
