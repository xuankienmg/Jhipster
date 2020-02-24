package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.DqRuleStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DqRuleStatus} and its DTO {@link DqRuleStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DqRuleStatusMapper extends EntityMapper<DqRuleStatusDTO, DqRuleStatus> {



    default DqRuleStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        DqRuleStatus dqRuleStatus = new DqRuleStatus();
        dqRuleStatus.setId(id);
        return dqRuleStatus;
    }
}
