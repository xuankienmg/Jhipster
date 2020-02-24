package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.DataMappingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DataMapping} and its DTO {@link DataMappingDTO}.
 */
@Mapper(componentModel = "spring", uses = {DsColumnsMapper.class})
public interface DataMappingMapper extends EntityMapper<DataMappingDTO, DataMapping> {

    @Mapping(source = "col.id", target = "colId")
    DataMappingDTO toDto(DataMapping dataMapping);

    @Mapping(source = "colId", target = "col")
    DataMapping toEntity(DataMappingDTO dataMappingDTO);

    default DataMapping fromId(Long id) {
        if (id == null) {
            return null;
        }
        DataMapping dataMapping = new DataMapping();
        dataMapping.setId(id);
        return dataMapping;
    }
}
