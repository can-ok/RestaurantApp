package com.resturant.restapi.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "USERS")
public class Users  implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "USERNAME")
    private String USERNAME;
    @Column(name = "PASSWORD")
    private String Password;
    @Column(name = "ENABLED")
    private boolean Enabled;




    private String 	AUTHORITY;

    public String getAUTHORITY() {
        return AUTHORITY;
    }

    public void setAUTHORITY(String AUTHORITY) {
        this.AUTHORITY = AUTHORITY;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public boolean isEnabled() {
        return Enabled;
    }

    public void setEnabled(boolean enabled) {
        this.Enabled = enabled;
    }
}
