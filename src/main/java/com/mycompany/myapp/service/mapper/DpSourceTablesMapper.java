package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.DpSourceTablesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DpSourceTables} and its DTO {@link DpSourceTablesDTO}.
 */
@Mapper(componentModel = "spring", uses = {DsTablesMapper.class})
public interface DpSourceTablesMapper extends EntityMapper<DpSourceTablesDTO, DpSourceTables> {

    @Mapping(source = "tbl.id", target = "tblId")
    DpSourceTablesDTO toDto(DpSourceTables dpSourceTables);

    @Mapping(source = "tblId", target = "tbl")
    DpSourceTables toEntity(DpSourceTablesDTO dpSourceTablesDTO);

    default DpSourceTables fromId(Long id) {
        if (id == null) {
            return null;
        }
        DpSourceTables dpSourceTables = new DpSourceTables();
        dpSourceTables.setId(id);
        return dpSourceTables;
    }
}
