//package com.resturant.restapi.service;
//
//import com.resturant.restapi.dto.AuthoritiesDto;
//import com.resturant.restapi.dto.UsersDto;
//import com.resturant.restapi.repository.UsersRepository;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.Assert.*;
//import static org.mockito.ArgumentMatchers.nullable;
//import static org.mockito.Mockito.doThrow;
//import static org.mockito.Mockito.when;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Matchers.any;
//
//@RunWith(MockitoJUnitRunner.class)
//public class UserAuthTest {
//
//    @Mock
//    UsersRepository usersRepository;
//
//    @Mock
//    AuthoritiesRepository authoritiesRepository;
//
//    @InjectMocks
//    UserService userService;
//
//    private List<AuthoritiesDto> authoritiesDtoList=new ArrayList<>();
//
//    private AuthoritiesDto authoritiesDto=new AuthoritiesDto();
//
//    HashMap<String, String> map = new HashMap<>();
//    @Before
//    public void setUpDto(){
//        authoritiesDto.setUsername("admin");
//        authoritiesDto.setId(1);
//    }
//
//    @Before
//    public void setUpDtoList(){
//        authoritiesDtoList.add(authoritiesDto);
//    }
//
//    @Before
//    public void setMap(){
//
//    }
//
//    @Test
//    public void getAllAuth() {
//
//        when(authoritiesRepository.findAll()).thenReturn(AuthDtoConverter.authDtoListToAuthList(authoritiesDtoList));
//
//        assertEquals(userService.getAllAuth().size(),authoritiesDtoList.size());
//    }
//
//
//
//}