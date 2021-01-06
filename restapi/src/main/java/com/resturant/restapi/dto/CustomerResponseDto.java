package com.resturant.restapi.dto;


import com.resturant.restapi.Model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@XmlRootElement
public class CustomerResponseDto {

    @XmlElement(name = "Customer")
    List<Customer> customerList;
}
