package com.resturant.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class WaiterDto {

    private Integer id;

    private String firstname;

    private String lastname;

    private String email;

    private Date birthDate;
}
