package com.resturant.restapi.dto;

import com.resturant.restapi.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UsersDto {

    private Integer id;
    private String username;
    private String password;
    private boolean enabled;
    private Set<RoleDto> roles=new HashSet<>();


}
