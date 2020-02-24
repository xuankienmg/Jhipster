package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.DsCollationsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DsCollations} and its DTO {@link DsCollationsDTO}.
 */
@Mapper(componentModel = "spring", uses = {DsDbmsTypesMapper.class})
public interface DsCollationsMapper extends EntityMapper<DsCollationsDTO, DsCollations> {

    @Mapping(source = "dbmsType.id", target = "dbmsTypeId")
    DsCollationsDTO toDto(DsCollations dsCollations);

    @Mapping(source = "dbmsTypeId", target = "dbmsType")
    DsCollations toEntity(DsCollationsDTO dsCollationsDTO);

    default DsCollations fromId(Long id) {
        if (id == null) {
            return null;
        }
        DsCollations dsCollations = new DsCollations();
        dsCollations.setId(id);
        return dsCollations;
    }
}
