package com.resturant.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthoritiesDto {

    private Integer id;

    private String username;

    private String 	AUTHORITY;
}
