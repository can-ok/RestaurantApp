package com.resturant.restapi.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class Users {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;




    @Column(name="USERNAME")
    private String UserName;

    @Column(name ="PASSWORD")
    private String Password;

    @Column(name = "ENABLED")
    private boolean Enabled;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public boolean isEnabled() {
        return Enabled;
    }

    public void setEnabled(boolean enabled) {
        Enabled = enabled;
    }
}
