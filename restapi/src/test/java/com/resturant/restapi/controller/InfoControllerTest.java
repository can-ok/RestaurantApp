package com.resturant.restapi.controller;

import com.resturant.restapi.service.InfoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;


@RunWith(MockitoJUnitRunner.class)
public class InfoControllerTest {
    @Mock
    InfoService infoService;

    @InjectMocks
    InfoController infoController;

    Map<String,String> configList=new HashMap<>();

    @Before
    public void setConfigList(){
        configList.put("Deneme","Deneme Value");
    }

    @Test
    public void getInfolist() {

        when(infoService.getConfig()).thenReturn(configList);
        assertEquals(infoController.getInfolist(),configList);
    }
}