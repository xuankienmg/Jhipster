package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.DqRulesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DqRules} and its DTO {@link DqRulesDTO}.
 */
@Mapper(componentModel = "spring", uses = {DqRuleTypesMapper.class, DqRuleRiskLevelsMapper.class, DqRuleStatusMapper.class, DqRuleCategoriesMapper.class, DqRuleActionsMapper.class})
public interface DqRulesMapper extends EntityMapper<DqRulesDTO, DqRules> {

    @Mapping(source = "type.id", target = "typeId")
    @Mapping(source = "risk.id", target = "riskId")
    @Mapping(source = "status.id", target = "statusId")
    @Mapping(source = "cat.id", target = "catId")
    @Mapping(source = "action.id", target = "actionId")
    DqRulesDTO toDto(DqRules dqRules);

    @Mapping(source = "typeId", target = "type")
    @Mapping(source = "riskId", target = "risk")
    @Mapping(source = "statusId", target = "status")
    @Mapping(source = "catId", target = "cat")
    @Mapping(source = "actionId", target = "action")
    @Mapping(target = "stds", ignore = true)
    @Mapping(target = "removeStd", ignore = true)
    @Mapping(target = "cols", ignore = true)
    @Mapping(target = "removeCol", ignore = true)
    DqRules toEntity(DqRulesDTO dqRulesDTO);

    default DqRules fromId(Long id) {
        if (id == null) {
            return null;
        }
        DqRules dqRules = new DqRules();
        dqRules.setId(id);
        return dqRules;
    }
}
