package com.resturant.restapi.service;

import com.resturant.restapi.Model.Role;
import com.resturant.restapi.Model.Users;
import com.resturant.restapi.builder.RoleBuilder;
import com.resturant.restapi.builder.RoleDtoBuilder;
import com.resturant.restapi.builder.UserBuilder;
import com.resturant.restapi.config.MessageSourceExternalizer;
import com.resturant.restapi.converter.RoleMapper;
import com.resturant.restapi.converter.UserDtoConverter;
import com.resturant.restapi.converter.UserMapper;
import com.resturant.restapi.dto.RoleDto;
import com.resturant.restapi.dto.UsersDto;
import com.resturant.restapi.exception.AuthenticationNotAllowed;
import com.resturant.restapi.exception.EntityNotFound;
import com.resturant.restapi.repository.RolesRepository;
import com.resturant.restapi.repository.UsersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    UsersRepository usersRepository;

    @Mock
    UserMapper userMapper;

    @Mock
    RoleMapper roleMapper;

    @Mock
    RolesRepository rolesRepository;

    @Mock
    private MessageSourceExternalizer messageSourceExternalizer;

    @InjectMocks
    UserService userService;

    private List<UsersDto> usersDtoList=new ArrayList<>();
    private List<Users> usersList=new ArrayList<>();

    private UsersDto usersDto=new UsersDto();
    private Users user;

    private Role role;
    private RoleDto roleDto;
    private Set<Role> roles=new HashSet<>();

    private Set<RoleDto> rolesDto=new HashSet<>();
    @Before
    public void setUpDto(){

        role=new RoleBuilder().id(1).name("user").build();
        roles.add(role);

        roleDto=new RoleDtoBuilder().id(1).name("user").build();
        rolesDto.add(roleDto);

        user=new UserBuilder().id(1).username("user").password("pass").roles(roles).build();
        usersList.add(user);


        usersDto=new UsersDto(1,"user","pass",true,null);
        usersDtoList.add(usersDto);
        usersDto.setRoles(rolesDto);

        when(userMapper.toEntityWOROle(any())).thenReturn(user);
        when(userMapper.toDto(any())).thenReturn(usersDto);
        when(userMapper.toEntity(any())).thenReturn(user);
        when(userMapper.toDtoList(any())).thenReturn(usersDtoList);
        when(roleMapper.toRoleList(any())).thenReturn(roles);

    }


    @Test
    public void insertUser() {

        when(rolesRepository.findById(any())).thenReturn(Optional.of(role));
        when(userMapper.toEntityWOROle(any())).thenReturn(user);

        UsersDto result=userService.insertUser(usersDto);
        assertEquals(usersDto.getRoles(),result.getRoles());
        assertEquals(usersDto.getId(),result.getId());
    }

    @Test
    public void shouldGetAllUser() {
        when(usersRepository.findAll()).thenReturn(usersList);
        when(userMapper.toDto(any())).thenReturn(usersDto);

        assertNotNull(userService.getAllUser());
        assertEquals(usersDtoList.size(),userService.getAllUser().size());
    }

    @Test
    public void shouldGetUser() {
        int id=1;
        when(usersRepository.findById(id)).thenReturn(Optional.of(user));
        when(userMapper.toDto(any())).thenReturn(usersDto);

        UsersDto usersResult=userService.getUser(id);

        assertEquals(usersResult.getUsername(),usersDto.getUsername());
    }

    @Test(expected = EntityNotFound.class)
    public void shouldNotGetUser() {
        int id=1;
        when(usersRepository.findById(id)).thenReturn(Optional.empty());
        UsersDto resultDto=userService.getUser(id);
        assertNull(resultDto);
    }


    @Test(expected = EntityNotFound.class)
    public void shoudNotdeleteUser() {
        Integer id=1;
        assertEquals(userService.deleteUser(id),"Success");

    }

    @Test()
    public void shoudDeleteUser() {
        Integer id=1;
        when(usersRepository.findById(id)).thenReturn(Optional.of(user));
        assertEquals(userService.deleteUser(id),"Success");

    }

    @Test
    public void updateUser() {
        when(usersRepository.findById(any())).thenReturn(Optional.of(user));
        when(userMapper.toDto(any())).thenReturn(usersDto);
        assertEquals(userService.updateUser(1,usersDto).getId(),user.getId());

    }

    @Test(expected = EntityNotFound.class)
    public void shouldNotUpdateUser() {
        when(usersRepository.findById(any())).thenReturn(Optional.empty());

        assertNull(userService.updateUser(1,usersDto));

    }

    @Test()
    public void shouldRegister() {
        when(usersRepository.getUsersByUSERNAME(any())).thenReturn(Optional.of(UserDtoConverter.userDtoToUser(usersDto)));

        assertNotNull(userService.register(usersDto));
        assertEquals(userService.register(usersDto).get("name"),"user");
    }


    @Test(expected = AuthenticationNotAllowed.class)
    public void shouldNotRegister() {
        when(usersRepository.getUserByNameANDPass(any(),any())).thenReturn(Optional.empty());

        assertNull(userService.register(usersDto));
    }
}