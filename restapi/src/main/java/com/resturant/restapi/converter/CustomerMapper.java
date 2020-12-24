package com.resturant.restapi.converter;

import com.resturant.restapi.Model.Customer;
import com.resturant.restapi.Model.Media;
import com.resturant.restapi.dto.CustomerDto;
import com.resturant.restapi.dto.MediaDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface CustomerMapper {


    CustomerDto toDto(Customer customer);

    Customer toEntity(CustomerDto customerDto);
}
