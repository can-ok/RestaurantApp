package com.resturant.restapi.controller;

import com.resturant.restapi.Model.Users;
import com.resturant.restapi.converter.UserDtoConverter;
import com.resturant.restapi.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class RegisterControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private RegisterController registerController;

    private Users user=new Users();

    HashMap<String, String> map = new HashMap<>();

    @Before
    public void setUser(){
        user.setUSERNAME("user1");
        user.setId(1);
        user.setPassword("132");
        user.setEnabled(true);

        map.put("name",user.getUSERNAME());
    }

    @Test
    public void register() {
        when(userService.register(any())).thenReturn(map);

        Map<String,String> resutMap=registerController.register(UserDtoConverter.userToUserDto(user));

        assertEquals(map.get("name"),resutMap.get("name"));
    }
}