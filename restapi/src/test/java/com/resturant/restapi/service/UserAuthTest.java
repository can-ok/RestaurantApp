package com.resturant.restapi.service;

import com.resturant.restapi.Model.Role;
import com.resturant.restapi.builder.RoleBuilder;
import com.resturant.restapi.converter.RoleDtoConverter;
import com.resturant.restapi.converter.RoleMapper;
import com.resturant.restapi.dto.RoleDto;
import com.resturant.restapi.repository.RolesRepository;
import com.resturant.restapi.repository.UsersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;
@RunWith(MockitoJUnitRunner.class)
public class UserAuthTest {

    @Mock
    RoleMapper roleMapper;

    @Mock
    RolesRepository rolesRepository;

    @InjectMocks
    RoleService roleService;

    private List<RoleDto> roleDtos =new ArrayList<>();

    private RoleDto roleDto =new RoleDto();

    private Role role;

    HashMap<String, String> map = new HashMap<>();
    @Before
    public void setUpDto(){

        role=new RoleBuilder().name("admin").id(1).build();

        roleDto= RoleDtoConverter.roleToRoleDto(role);

        roleDtos.add(roleDto);

    }


    @Test
    public void getAllRole() {

       when(rolesRepository.findAll()).thenReturn(RoleDtoConverter.roleDtoListToRoleList(roleDtos));
       when(roleMapper.toDto(any())).thenReturn(roleDto);
       assertEquals(roleService.getAll().size(),roleDtos.size());
    }


    @Test
    public void shouldGetRole() {

        when(rolesRepository.findById(any())).thenReturn(Optional.of(role));
        when(roleMapper.toDto(any())).thenReturn(roleDto);
        assertEquals(roleService.getRole(1).getId(),roleDto.getId());
    }

    @Test
    public void shouldInsertRole() {

        assertEquals(roleService.insert(roleDto).getId(),roleDto.getId());
    }

    @Test
    public void shouldDeleteRole() {
        when(rolesRepository.findById(any())).thenReturn(Optional.of(role));

        assertEquals(roleService.delete(1),"Success");
    }
    @Test
    public void shouldNotDeleteRole() {
        when(rolesRepository.findById(any())).thenReturn(Optional.empty());

        assertEquals(roleService.delete(1),"Fail");
    }

    @Test
    public void shouldUpdateRole() {
        when(rolesRepository.findById(any())).thenReturn(Optional.of(role));

        assertEquals(roleService.update(roleDto,1),"Success");
    }

    @Test
    public void shouldNotUpdateRole() {
        when(rolesRepository.findById(any())).thenReturn(Optional.empty());

        assertEquals(roleService.update(roleDto,1),"Fail");
    }




}