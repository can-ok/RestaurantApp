package com.resturant.restapi.dto;


import com.resturant.restapi.Model.Media;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    @NotNull(message = "Media Can Not BE Null")
    private Media media;

}
