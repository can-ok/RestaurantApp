package com.resturant.restapi.dto;

import com.resturant.restapi.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UsersDto {

    @Min(0)
    private Integer id;

    @NotNull(message = "username Can Not BE Null")
    private String username;
    @NotNull(message = "password Can Not BE Null")
    private String password;
    @NotNull(message = "enabled Can Not BE Null")
    private boolean enabled;

    @NotEmpty(message = "roles Can Not BE Null")
    private Set<RoleDto> roles=new HashSet<>();


}
