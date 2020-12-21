package com.resturant.restapi.service;

import com.resturant.restapi.Model.Role;
import com.resturant.restapi.Model.Users;
import com.resturant.restapi.builder.RoleBuilder;
import com.resturant.restapi.builder.UserBuilder;
import com.resturant.restapi.converter.UserDtoConverter;
import com.resturant.restapi.dto.UsersDto;
import com.resturant.restapi.dto.WaiterDto;
import com.resturant.restapi.repository.RolesRepository;
import com.resturant.restapi.repository.UsersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

import java.util.*;

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
    RolesRepository rolesRepository;

    @InjectMocks
    UserService userService;

    private List<UsersDto> usersDtoList=new ArrayList<>();

    private UsersDto usersDto=new UsersDto();
    private Users user;

    private Role role;

    private Set<Role> roles=new HashSet<>();

    @Before
    public void setUpDto(){

        role=new RoleBuilder().id(1).name("user").build();
        roles.add(role);
        user=new UserBuilder().id(1).username("user").password("pass").roles(roles).build();
        usersDto=UserDtoConverter.userToUserDto(user);
        usersDtoList.add(usersDto);

    }


    @Test
    public void insertUser() {

        when(rolesRepository.findById(any())).thenReturn(Optional.of(role));
        UsersDto result=userService.insertUser(usersDto);
        assertEquals(usersDto.getRoles(),result.getRoles());
        assertEquals(usersDto.getId(),result.getId());
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
        assertEquals(usersResult.getUsername(),usersDto.getUsername());
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
        when(usersRepository.findById(any())).thenReturn(Optional.of(user));

        assertEquals(userService.updateUser(1,usersDto).getId(),user.getId());

    }

    @Test
    public void shouldNotUpdateUser() {
        when(usersRepository.findById(any())).thenReturn(Optional.empty());

        assertNull(userService.updateUser(1,usersDto));

    }

    @Test
    public void shouldRegister() {
        when(usersRepository.getUsersByUSERNAME(any())).thenReturn(Optional.of(UserDtoConverter.userDtoToUser(usersDto)));

        assertNotNull(userService.register(usersDto));
        assertEquals(userService.register(usersDto).get("name"),"user");
    }


    @Test
    public void shouldNotRegister() {
        when(usersRepository.getUserByNameANDPass(any(),any())).thenReturn(Optional.empty());

        assertNull(userService.register(usersDto));
    }
}