package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.DqRuleTypesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DqRuleTypes} and its DTO {@link DqRuleTypesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DqRuleTypesMapper extends EntityMapper<DqRuleTypesDTO, DqRuleTypes> {



    default DqRuleTypes fromId(Long id) {
        if (id == null) {
            return null;
        }
        DqRuleTypes dqRuleTypes = new DqRuleTypes();
        dqRuleTypes.setId(id);
        return dqRuleTypes;
    }
}
