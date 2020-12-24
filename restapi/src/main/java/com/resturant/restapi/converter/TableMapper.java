package com.resturant.restapi.converter;

import com.resturant.restapi.Model.Tables;
import com.resturant.restapi.dto.TablesDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import javax.persistence.Table;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface TableMapper {

    TablesDto toDto(Tables tables);

    Tables toEntity(TablesDto tablesDto);


    List<TablesDto> toDtoList(List<Tables> tablesList);
}
