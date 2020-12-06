package com.resturant.restapi.controller;

import com.resturant.restapi.dto.AuthoritiesDto;
import com.resturant.restapi.dto.UsersDto;
import com.resturant.restapi.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;


    @InjectMocks
    private UserController userController;


    List<UsersDto> userList=new ArrayList<>();

    UsersDto usersDto =new UsersDto();

    @Before
    public void setUserList(){

        usersDto.setUSERNAME("user1");
        usersDto.setId(1);
        usersDto.setPassword("132");
        usersDto.setEnabled(true);

        userList.add(usersDto);

    }

    List<AuthoritiesDto> authList=new ArrayList<>();


    @Test
    public void shoudGetAllUsers() {

        when(userService.getAllUser()).thenReturn(userList);

        List<UsersDto> resultList=userController.getAllUsers();

        assertEquals(userList,resultList);
    }

    @Test
    public void shoudSaveUser() {
        when(userService.insertUser(usersDto)).thenReturn(usersDto);

        assertEquals(userController.saveUser(usersDto),usersDto);
    }

    @Test
    public void  shoudGetUser() {
        int id=1;
        when(userService.getUser(id)).thenReturn(usersDto);

        assertEquals(userController.getUser(id),usersDto);
    }

    @Test
    public void  shoudNotGetUser() {
        int id=1;
        when(userService.getUser(id)).thenReturn(null);

        assertNull(userController.getUser(id));
    }

    @Test
    public void deleteUser() {
        int id=1;
        when(userService.deleteUser(id)).thenReturn("Success");

        assertEquals(userController.deleteUser(id),"Success");
    }

    @Test
    public void updateUser() {
        int id=1;
        when(userService.updateUser(id,usersDto)).thenReturn(usersDto);

        assertEquals(userController.updateUser(id,usersDto),usersDto);
    }

    @Test
    public void getAllAuth() {
        int id=1;
        //when(userService.getAllAuth()).thenReturn();
    }
}