package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.DsTablesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DsTables} and its DTO {@link DsTablesDTO}.
 */
@Mapper(componentModel = "spring", uses = {DsTableTypesMapper.class, DsStoresMapper.class})
public interface DsTablesMapper extends EntityMapper<DsTablesDTO, DsTables> {

    @Mapping(source = "tblType.id", target = "tblTypeId")
    @Mapping(source = "store.id", target = "storeId")
    DsTablesDTO toDto(DsTables dsTables);

    @Mapping(source = "tblTypeId", target = "tblType")
    @Mapping(source = "storeId", target = "store")
    DsTables toEntity(DsTablesDTO dsTablesDTO);

    default DsTables fromId(Long id) {
        if (id == null) {
            return null;
        }
        DsTables dsTables = new DsTables();
        dsTables.setId(id);
        return dsTables;
    }
}
