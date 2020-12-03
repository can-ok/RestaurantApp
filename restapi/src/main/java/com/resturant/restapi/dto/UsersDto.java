package com.resturant.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UsersDto {

    private Integer id;
    private String USERNAME;
    private String Password;
    private boolean Enabled;
    private String 	AUTHORITY;


}
