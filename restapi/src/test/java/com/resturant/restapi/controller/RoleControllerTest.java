package com.resturant.restapi.controller;

import com.resturant.restapi.dto.RoleDto;
import com.resturant.restapi.service.RoleService;
import com.resturant.restapi.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import javax.persistence.ManyToOne;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RoleControllerTest {


    @Mock
    UserService userService;

    @Mock
    RoleService roleService;

    @InjectMocks
    RoleController roleController;


    List<RoleDto> roleDtos=new ArrayList<>();
    RoleDto roleDto=new RoleDto();

    @Before
    public void setUp() throws Exception {
        roleDto.setName("deneme");
        roleDto.setId(1);

        roleDtos.add(roleDto);

    }

    @Test
    public void getAll() {
        when(roleService.getAll()).thenReturn(roleDtos);

        assertNotNull(roleController.getAll());
    }

    @Test
    public void save() {
        when(roleService.insert(any())).thenReturn(roleDto);
        assertEquals(roleController.save(roleDto).getId(),roleDto.getId());
    }

    @Test
    public void delete() {
        int id=1;
        when(roleService.delete(id)).thenReturn("Succes");
        assertEquals(roleController.delete(id),"Succes");
    }

    @Test
    public void update() {
        int id=1;
        when(roleService.update(roleDto,id)).thenReturn("Succes");
        assertEquals(roleController.update(roleDto,id),"Succes");

    }

    @Test
    public void getById() {
        int id=1;
        when(roleService.getRole(id)).thenReturn(roleDto);
        assertEquals(roleController.getById(id),roleDto);

    }
}