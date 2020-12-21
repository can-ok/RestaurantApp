package com.resturant.restapi.builder;

import com.resturant.restapi.Model.Role;
import com.resturant.restapi.Model.Users;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class UserBuilder extends Builder{

    private Integer id;

    private String USERNAME;
    private String Password;
    private boolean Enabled;

    private Set<Role> roles=new HashSet<>();


    @Override
    public Users build() {
        Users user=new Users();
        user.setId(this.id);
        user.setUsername(this.USERNAME);
        user.setPassword(this.Password);
        user.setRoles(this.roles);
        user.setEnabled(this.Enabled);
        return user;
    }

    @Override
    public UserBuilder id(int id) {
        this.id=id;
        return this;
    }

    public UserBuilder username(String USERNAME) {
        this.USERNAME=USERNAME;
        return this;
    }

    public UserBuilder enabled(Boolean Enabled) {
        this.Enabled=Enabled;
        return this;
    }

    public UserBuilder roles(Set<Role> roles) {
        this.roles=roles;
        return this;
    }

    public UserBuilder password(String Password) {
        this.Password=Password;
        return this;
    }
}
