package com.resturant.restapi.builder;

import com.resturant.restapi.Model.Role;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

public class RoleBuilder  extends Builder{

    private Integer id;

    private String name;


    @Override
    public Role build() {
        Role role=new Role();
        return role;
    }

    @Override
    public RoleBuilder id(int id) {
        this.id=id;
        return this;
    }

    public RoleBuilder name(String name) {
        this.name=name;
        return this;
    }


}
