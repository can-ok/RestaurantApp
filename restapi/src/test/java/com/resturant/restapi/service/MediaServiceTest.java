package com.resturant.restapi.service;

import com.resturant.restapi.builder.MediaDtoBuilder;
import com.resturant.restapi.converter.MediaDtoConverter;
import com.resturant.restapi.dto.MediaDto;
import com.resturant.restapi.repository.MediaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class MediaServiceTest {
    @Mock
    MediaRepository mediaRepository;

    @InjectMocks
    MediaService mediaService;


    List<MediaDto> mediaDtos=new ArrayList<>();
    MediaDto dto;
    @Before
    public void SetUp(){

        byte[] fileBytes="deneme".getBytes();
        dto= new MediaDtoBuilder().fileContent(fileBytes).id(1).name("deneme").build();

        mediaDtos.add(dto);
    }

    MockMultipartFile file;

    @Before
    public void SetUpMultipart() throws IOException {


                file= new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes());
    }

    @Test
    public void getAllMedia() {

        when(mediaRepository.findAll()).thenReturn(MediaDtoConverter.mediaDtoListToMediaList(mediaDtos));

        assertNotNull(mediaService.getAllMedia());
        assertEquals(mediaService.getAllMedia().size(),mediaDtos.size());
    }

    @Test
    public void saveFile() {
        when(mediaRepository.save(any())).thenReturn(MediaDtoConverter.mediaDtoToMedia(dto));

        String result=mediaService.saveFile(any(),any());

        assertEquals(result,"Success");


    }

    @Test
    public void generateUUID() {
    }

    @Test
    public void generateFullFilePath() {
    }
}