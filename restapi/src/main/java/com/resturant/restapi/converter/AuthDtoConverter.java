package com.resturant.restapi.converter;

import com.resturant.restapi.Model.AUTHORITIES;
import com.resturant.restapi.dto.AuthoritiesDto;

import java.util.ArrayList;
import java.util.List;

public class AuthDtoConverter {


    public static AUTHORITIES authDtoToAuth(AuthoritiesDto authoritiesDto){
        AUTHORITIES authorities=new AUTHORITIES();
        authorities.setUsername(authoritiesDto.getUsername());
        authorities.setId(authoritiesDto.getId());
        return authorities;
    }

    public static List<AUTHORITIES> authDtoListToAuthList(List<AuthoritiesDto> authoritiesDtosList){

        List<AUTHORITIES> authoritiesList=new ArrayList<>();

        authoritiesDtosList.forEach(item->{

            AUTHORITIES authorities=authDtoToAuth(item);
            authoritiesList.add(authorities);

        });

        return authoritiesList;
    }
}
