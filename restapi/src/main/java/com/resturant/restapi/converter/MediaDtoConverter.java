package com.resturant.restapi.converter;

import com.resturant.restapi.Model.Media;
import com.resturant.restapi.dto.MediaDto;

import java.util.ArrayList;
import java.util.List;

public class MediaDtoConverter {

    public static List<MediaDto> mediaListToMediaDtoList(List<Media> mediaList){
        List<MediaDto> mediaDtos=new ArrayList<>();

        mediaList.forEach(media -> {
            mediaDtos.add(mediaToMediaDto(media));
        });

        return mediaDtos;
    }

    public static MediaDto mediaToMediaDto(Media media){
        MediaDto dto=new MediaDto();
        dto.setFileContent(media.getFileContent());
        dto.setId(media.getId());
        dto.setName(media.getName());
        return dto;
    }
    public static Media mediaDtoToMedia(MediaDto mediaDto){
        Media media=new Media();
        media.setFileContent(mediaDto.getFileContent());
        media.setId(mediaDto.getId());
        media.setName(mediaDto.getName());
        return media;
    }

    public static List<Media> mediaDtoListToMediaList(List<MediaDto> mediaDtoList){
        List<Media> medias=new ArrayList<>();

        mediaDtoList.forEach(mediaDto -> {
            Media entity=mediaDtoToMedia(mediaDto);
            medias.add(entity);
        });

        return medias;
    }

}
