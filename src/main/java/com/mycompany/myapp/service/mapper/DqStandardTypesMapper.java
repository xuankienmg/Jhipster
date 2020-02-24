package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.DqStandardTypesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DqStandardTypes} and its DTO {@link DqStandardTypesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DqStandardTypesMapper extends EntityMapper<DqStandardTypesDTO, DqStandardTypes> {



    default DqStandardTypes fromId(Long id) {
        if (id == null) {
            return null;
        }
        DqStandardTypes dqStandardTypes = new DqStandardTypes();
        dqStandardTypes.setId(id);
        return dqStandardTypes;
    }
}
