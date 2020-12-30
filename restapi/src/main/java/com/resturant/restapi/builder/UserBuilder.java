package com.resturant.restapi.builder;

import com.resturant.restapi.Model.Role;
import com.resturant.restapi.Model.Users;

import java.util.HashSet;
import java.util.Set;

public class UserBuilder extends Builder{

    private Integer id;

    private String username;
    private String password;
    private boolean enabled;

    private Set<Role> roles=new HashSet<>();


    @Override
    public Users build() {
        Users user=new Users();
        user.setId(this.id);
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setRoles(this.roles);
        user.setEnabled(this.enabled);
        return user;
    }

    @Override
    public UserBuilder id(int id) {
        this.id=id;
        return this;
    }

    public UserBuilder username(String USERNAME) {
        this.username =USERNAME;
        return this;
    }

    public UserBuilder enabled(Boolean Enabled) {
        this.enabled =Enabled;
        return this;
    }

    public UserBuilder roles(Set<Role> roles) {
        this.roles=roles;
        return this;
    }

    public UserBuilder password(String Password) {
        this.password =Password;
        return this;
    }
}
