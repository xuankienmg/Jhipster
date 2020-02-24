package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.DqRuleRiskLevelsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DqRuleRiskLevels} and its DTO {@link DqRuleRiskLevelsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DqRuleRiskLevelsMapper extends EntityMapper<DqRuleRiskLevelsDTO, DqRuleRiskLevels> {



    default DqRuleRiskLevels fromId(Long id) {
        if (id == null) {
            return null;
        }
        DqRuleRiskLevels dqRuleRiskLevels = new DqRuleRiskLevels();
        dqRuleRiskLevels.setId(id);
        return dqRuleRiskLevels;
    }
}
