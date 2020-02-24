package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EventTypesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EventTypes} and its DTO {@link EventTypesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EventTypesMapper extends EntityMapper<EventTypesDTO, EventTypes> {



    default EventTypes fromId(Long id) {
        if (id == null) {
            return null;
        }
        EventTypes eventTypes = new EventTypes();
        eventTypes.setId(id);
        return eventTypes;
    }
}
