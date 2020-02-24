package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EventLogsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EventLogs} and its DTO {@link EventLogsDTO}.
 */
@Mapper(componentModel = "spring", uses = {EventTypesMapper.class, EventCategoriesMapper.class, DataFlowsMapper.class, DsTablesMapper.class})
public interface EventLogsMapper extends EntityMapper<EventLogsDTO, EventLogs> {

    @Mapping(source = "eventType.id", target = "eventTypeId")
    @Mapping(source = "eventCat.id", target = "eventCatId")
    @Mapping(source = "flow.id", target = "flowId")
    @Mapping(source = "tbl.id", target = "tblId")
    EventLogsDTO toDto(EventLogs eventLogs);

    @Mapping(source = "eventTypeId", target = "eventType")
    @Mapping(source = "eventCatId", target = "eventCat")
    @Mapping(source = "flowId", target = "flow")
    @Mapping(source = "tblId", target = "tbl")
    EventLogs toEntity(EventLogsDTO eventLogsDTO);

    default EventLogs fromId(Long id) {
        if (id == null) {
            return null;
        }
        EventLogs eventLogs = new EventLogs();
        eventLogs.setId(id);
        return eventLogs;
    }
}
