package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.DsTableTypesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DsTableTypes} and its DTO {@link DsTableTypesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DsTableTypesMapper extends EntityMapper<DsTableTypesDTO, DsTableTypes> {



    default DsTableTypes fromId(Long id) {
        if (id == null) {
            return null;
        }
        DsTableTypes dsTableTypes = new DsTableTypes();
        dsTableTypes.setId(id);
        return dsTableTypes;
    }
}
