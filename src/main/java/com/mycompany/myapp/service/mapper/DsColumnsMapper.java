package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.DsColumnsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DsColumns} and its DTO {@link DsColumnsDTO}.
 */
@Mapper(componentModel = "spring", uses = {DsTablesMapper.class, DqRulesMapper.class})
public interface DsColumnsMapper extends EntityMapper<DsColumnsDTO, DsColumns> {

    @Mapping(source = "colTbl.id", target = "colTblId")
    DsColumnsDTO toDto(DsColumns dsColumns);

    @Mapping(source = "colTblId", target = "colTbl")
    @Mapping(target = "removeRule", ignore = true)
    DsColumns toEntity(DsColumnsDTO dsColumnsDTO);

    default DsColumns fromId(Long id) {
        if (id == null) {
            return null;
        }
        DsColumns dsColumns = new DsColumns();
        dsColumns.setId(id);
        return dsColumns;
    }
}
