package com.resturant.restapi.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@XmlRootElement(name = "customer")
public class CustomerDto {

    //@Min()
    private Integer id;

    @NotNull(message = "firstName Can Not BE Null")
    private String firstName;
    @NotNull(message = "lastName Can Not BE Null")
    private String lastName;
    @NotNull(message = "City Can Not BE Null")
    private String city;
    @NotNull(message = "Address Can Not BE Null")
    private String address;
    @NotNull(message = "PhoneNumber Can Not BE Null")
    private String phoneNumber;


}
