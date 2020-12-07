package com.resturant.restapi.service;

import com.resturant.restapi.Model.Users;
import com.resturant.restapi.converter.UserDtoConverter;
import com.resturant.restapi.dto.UsersDto;
import com.resturant.restapi.dto.WaiterDto;
import com.resturant.restapi.repository.AuthoritiesRepository;
import com.resturant.restapi.repository.UsersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    UsersRepository usersRepository;

    @Mock
    AuthoritiesRepository authoritiesRepository;

    @InjectMocks
    UserService userService;

    private List<UsersDto> usersDtoList=new ArrayList<>();

    private UsersDto usersDto=new UsersDto();

    @Before
    public void setUpDto(){
        usersDto.setUSERNAME("user");
        usersDto.setId(1);
        usersDto.setEnabled(true);
    }

    @Before
    public void setUpDtoList(){
        usersDtoList.add(usersDto);
    }


    @Test
    public void insertUser() {
    }

    @Test
    public void shouldGetAllUser() {
        when(usersRepository.findAll()).thenReturn(UserDtoConverter.userDtoListToUserList(usersDtoList));

        assertNotNull(userService.getAllUser());
        assertEquals(usersDtoList.size(),userService.getAllUser().size());
    }

    @Test
    public void shouldGetUser() {
        int id=1;
        when(usersRepository.findById(id)).thenReturn(Optional.of(UserDtoConverter.userDtoToUser(usersDto)));
        UsersDto usersResult=userService.getUser(id);
        assertEquals(usersResult.getUSERNAME(),usersDto.getUSERNAME());
    }

    @Test
    public void shouldNotGetUser() {
        int id=1;
        when(usersRepository.findById(id)).thenReturn(Optional.empty());
        UsersDto resultDto=userService.getUser(id);
        assertNull(resultDto);
    }


    @Test
    public void deleteUser() {
        Integer id=1;

        assertEquals(userService.deleteUser(id),"Success");

    }

    @Test
    public void updateUser() {
    }

    @Test
    public void shouldRegister() {
        when(usersRepository.getUserByNameANDPass(any(),any())).thenReturn(Optional.of(UserDtoConverter.userDtoToUser(usersDto)));

        assertNotNull(userService.register(usersDto));
    }


    @Test
    public void shouldNotRegister() {
        when(usersRepository.getUserByNameANDPass(any(),any())).thenReturn(Optional.empty());

        assertNull(userService.register(usersDto));
    }
}