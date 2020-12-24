package com.resturant.restapi.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerDto {

    private Integer customerId;
    private String firstName;
    private String lastName;
    private String city;
    private String address;
    private String phoneNumber;

}
