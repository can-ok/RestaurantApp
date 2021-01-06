package com.resturant.restapi.converter;

import com.resturant.restapi.Model.Tables;
import com.resturant.restapi.dto.TablesDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TableMapper {

    TablesDto toDto(Tables tables);

    Tables toEntity(TablesDto tablesDto);


    List<TablesDto> toDtoList(List<Tables> tablesList);
}
