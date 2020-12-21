package com.resturant.restapi.converter;

import com.resturant.restapi.Model.Tables;
import com.resturant.restapi.dto.TablesDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import javax.persistence.Table;
import java.util.Map;

@Mapper
public interface TableMapper {

    TableMapper INSTANCE= Mappers.getMapper(TableMapper.class);

    TablesDto toDto(Tables tables);

    Tables toEntity(TablesDto tablesDto);
}
