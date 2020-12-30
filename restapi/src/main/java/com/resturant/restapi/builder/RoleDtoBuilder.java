package com.resturant.restapi.builder;

import com.resturant.restapi.Model.Role;
import com.resturant.restapi.dto.RoleDto;

public class RoleDtoBuilder extends Builder{

    private Integer id;

    private String name;

    @Override
    public RoleDto build() {
        RoleDto role=new RoleDto();
        role.setId(this.id);
        role.setName(name);
        return role;
    }

    @Override
    public RoleDtoBuilder id(int id) {
        this.id=id;
        return this;
    }
    public RoleDtoBuilder name(String name) {
        this.name=name;
        return this;
    }

}
