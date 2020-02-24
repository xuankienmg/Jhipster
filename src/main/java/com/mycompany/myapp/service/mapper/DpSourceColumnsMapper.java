package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.DpSourceColumnsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DpSourceColumns} and its DTO {@link DpSourceColumnsDTO}.
 */
@Mapper(componentModel = "spring", uses = {DsTablesMapper.class, DsColumnsMapper.class})
public interface DpSourceColumnsMapper extends EntityMapper<DpSourceColumnsDTO, DpSourceColumns> {

    @Mapping(source = "tbl.id", target = "tblId")
    @Mapping(source = "col.id", target = "colId")
    DpSourceColumnsDTO toDto(DpSourceColumns dpSourceColumns);

    @Mapping(source = "tblId", target = "tbl")
    @Mapping(source = "colId", target = "col")
    DpSourceColumns toEntity(DpSourceColumnsDTO dpSourceColumnsDTO);

    default DpSourceColumns fromId(Long id) {
        if (id == null) {
            return null;
        }
        DpSourceColumns dpSourceColumns = new DpSourceColumns();
        dpSourceColumns.setId(id);
        return dpSourceColumns;
    }
}
