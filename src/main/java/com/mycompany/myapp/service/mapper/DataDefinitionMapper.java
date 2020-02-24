package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.DataDefinitionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DataDefinition} and its DTO {@link DataDefinitionDTO}.
 */
@Mapper(componentModel = "spring", uses = {DsColumnsMapper.class, DsColumnTypesMapper.class, DsTablesMapper.class})
public interface DataDefinitionMapper extends EntityMapper<DataDefinitionDTO, DataDefinition> {

    @Mapping(source = "col.id", target = "colId")
    @Mapping(source = "type.id", target = "typeId")
    @Mapping(source = "tbl.id", target = "tblId")
    DataDefinitionDTO toDto(DataDefinition dataDefinition);

    @Mapping(source = "colId", target = "col")
    @Mapping(source = "typeId", target = "type")
    @Mapping(source = "tblId", target = "tbl")
    DataDefinition toEntity(DataDefinitionDTO dataDefinitionDTO);

    default DataDefinition fromId(Long id) {
        if (id == null) {
            return null;
        }
        DataDefinition dataDefinition = new DataDefinition();
        dataDefinition.setId(id);
        return dataDefinition;
    }
}
