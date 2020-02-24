package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.DqNotificationsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DqNotifications} and its DTO {@link DqNotificationsDTO}.
 */
@Mapper(componentModel = "spring", uses = {DqRulesMapper.class})
public interface DqNotificationsMapper extends EntityMapper<DqNotificationsDTO, DqNotifications> {

    @Mapping(source = "rule.id", target = "ruleId")
    DqNotificationsDTO toDto(DqNotifications dqNotifications);

    @Mapping(source = "ruleId", target = "rule")
    DqNotifications toEntity(DqNotificationsDTO dqNotificationsDTO);

    default DqNotifications fromId(Long id) {
        if (id == null) {
            return null;
        }
        DqNotifications dqNotifications = new DqNotifications();
        dqNotifications.setId(id);
        return dqNotifications;
    }
}
