package com.resturant.restapi.dto;


import com.resturant.restapi.Model.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MediaDto {

    private Integer id;

    private String name;

    private byte[] fileContent;



}
