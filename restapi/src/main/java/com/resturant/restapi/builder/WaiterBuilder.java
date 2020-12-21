package com.resturant.restapi.builder;

import com.resturant.restapi.Model.Media;
import com.resturant.restapi.Model.Waiter;
import liquibase.pro.packaged.W;

import java.util.Date;

public class WaiterBuilder extends Builder{


    private Integer id;

    private String firstname;

    private String lastname;

    private String email;

    private Date birtdate;

    private Media media;

    @Override
    public Waiter build() {
        Waiter waiter=new Waiter();
        waiter.setId(this.id);
        waiter.setFirstname(this.firstname);
        waiter.setLastname(this.lastname);
        waiter.setBirtdate(this.birtdate);
        waiter.setEmail(this.email);
        waiter.setMedia(this.media);
        return waiter;
    }

    @Override
    public WaiterBuilder id(int id) {

        this.id=id;
        return this;
    }


    public WaiterBuilder firstname(String firstname) {

        this.firstname=firstname;
        return this;
    }

    public WaiterBuilder lastname(String lastname) {

        this.lastname=lastname;
        return this;
    }

    public WaiterBuilder email(String email) {

        this.email=email;
        return this;
    }

    public WaiterBuilder birtdate(Date birtdate) {

        this.birtdate=birtdate;
        return this;
    }

    public WaiterBuilder media(Media media){
        this.media=media;
        return this;
    }

}
