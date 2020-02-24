package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.DsDbmsTypesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DsDbmsTypes} and its DTO {@link DsDbmsTypesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DsDbmsTypesMapper extends EntityMapper<DsDbmsTypesDTO, DsDbmsTypes> {



    default DsDbmsTypes fromId(Long id) {
        if (id == null) {
            return null;
        }
        DsDbmsTypes dsDbmsTypes = new DsDbmsTypes();
        dsDbmsTypes.setId(id);
        return dsDbmsTypes;
    }
}
