package com.resturant.restapi.builder;

import com.resturant.restapi.Model.Customer;
import com.resturant.restapi.Model.Media;

public class CustomerBuilder extends Builder {


    private Integer id;
    private String firstName;
    private String lastName;
    private String city;
    private String address;
    private String phoneNumber;
    private Media media;


    @Override
    public Customer build() {
        Customer customer=new Customer();
        customer.setId(this.id);
        customer.setAddress(this.address);
        customer.setLastName(this.lastName);
        customer.setFirstName(this.firstName);
        customer.setCity(this.city);
        customer.setPhoneNumber(this.phoneNumber);
        customer.setMedia(this.media);
        return customer;
    }

    @Override
    public CustomerBuilder id(int id) {
        this.id=id;
        return this;
    }

    public CustomerBuilder address(String address){
        this.address=address;
        return this;
    }

    public CustomerBuilder lastname(String lastName){
        this.lastName=lastName;
        return this;
    }
    public CustomerBuilder firstName(String firstName){
        this.firstName=firstName;
        return this;
    }

    public CustomerBuilder city(String city){
        this.city=city;
        return this;
    }

    public CustomerBuilder phoneNumber(String phoneNumber){
        this.phoneNumber=phoneNumber;
        return this;
    }

    public CustomerBuilder media(Media media){
        this.media=media;
        return this;
    }
}
