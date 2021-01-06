package com.resturant.restapi.converter;

import com.resturant.restapi.Model.Media;
import com.resturant.restapi.dto.MediaDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MediaMapper {

    //MediaMapper INSTANCE= Mappers.getMapper(MediaMapper.class);

    MediaDto toDto(Media media);

    Media toEntity(MediaDto mediaDto);
}
