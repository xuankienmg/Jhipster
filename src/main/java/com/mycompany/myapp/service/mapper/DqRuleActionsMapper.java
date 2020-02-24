package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.DqRuleActionsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DqRuleActions} and its DTO {@link DqRuleActionsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DqRuleActionsMapper extends EntityMapper<DqRuleActionsDTO, DqRuleActions> {



    default DqRuleActions fromId(Long id) {
        if (id == null) {
            return null;
        }
        DqRuleActions dqRuleActions = new DqRuleActions();
        dqRuleActions.setId(id);
        return dqRuleActions;
    }
}
