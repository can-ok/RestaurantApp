package com.resturant.restapi.dto;

import com.resturant.restapi.Model.Media;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TablesDto {

    @Min(value = 1)
    private Integer id;

    @NotNull(message = "title Can Not BE Null")
    private String title;

    @NotNull(message = "enabled Can Not BE Null")
    private Boolean enabled;

    @Min(value = 0)
    @NotNull(message = "tableCount Can Not BE Null")
    private Integer tableCount;

    @NotNull(message = "name Can Not BE Null")
    private Media media;

}
