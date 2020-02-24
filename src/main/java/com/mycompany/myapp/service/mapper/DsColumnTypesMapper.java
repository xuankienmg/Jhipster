package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.DsColumnTypesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DsColumnTypes} and its DTO {@link DsColumnTypesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DsColumnTypesMapper extends EntityMapper<DsColumnTypesDTO, DsColumnTypes> {



    default DsColumnTypes fromId(Long id) {
        if (id == null) {
            return null;
        }
        DsColumnTypes dsColumnTypes = new DsColumnTypes();
        dsColumnTypes.setId(id);
        return dsColumnTypes;
    }
}
