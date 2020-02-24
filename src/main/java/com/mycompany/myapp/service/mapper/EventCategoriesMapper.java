package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EventCategoriesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EventCategories} and its DTO {@link EventCategoriesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EventCategoriesMapper extends EntityMapper<EventCategoriesDTO, EventCategories> {



    default EventCategories fromId(Long id) {
        if (id == null) {
            return null;
        }
        EventCategories eventCategories = new EventCategories();
        eventCategories.setId(id);
        return eventCategories;
    }
}
