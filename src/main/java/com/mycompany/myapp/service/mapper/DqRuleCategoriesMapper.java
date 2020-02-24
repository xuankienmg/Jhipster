package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.DqRuleCategoriesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DqRuleCategories} and its DTO {@link DqRuleCategoriesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DqRuleCategoriesMapper extends EntityMapper<DqRuleCategoriesDTO, DqRuleCategories> {



    default DqRuleCategories fromId(Long id) {
        if (id == null) {
            return null;
        }
        DqRuleCategories dqRuleCategories = new DqRuleCategories();
        dqRuleCategories.setId(id);
        return dqRuleCategories;
    }
}
