package com.resturant.restapi.dto;

import com.resturant.restapi.Model.Media;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class WaiterDto {

    @Min(value = 1,message = "Id must be bigger than 1")
    private Integer id;

    @NotNull(message = "firstname Can Not BE Null")
    private String firstname;

    @NotNull(message = "lastname Can Not BE Null")
    private String lastname;

    @NotNull(message = "email Can Not BE Null")
    private String email;

    @NotNull(message = "birtdate Can Not BE Null")
    private Date birtdate;

    @NotNull(message = "media Can Not BE Null")
    private Media media;

}
