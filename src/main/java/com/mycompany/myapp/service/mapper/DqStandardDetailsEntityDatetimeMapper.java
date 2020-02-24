package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityDatetimeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DqStandardDetailsEntityDatetime} and its DTO {@link DqStandardDetailsEntityDatetimeDTO}.
 */
@Mapper(componentModel = "spring", uses = {DqStandardsMapper.class})
public interface DqStandardDetailsEntityDatetimeMapper extends EntityMapper<DqStandardDetailsEntityDatetimeDTO, DqStandardDetailsEntityDatetime> {

    @Mapping(source = "std.id", target = "stdId")
    DqStandardDetailsEntityDatetimeDTO toDto(DqStandardDetailsEntityDatetime dqStandardDetailsEntityDatetime);

    @Mapping(source = "stdId", target = "std")
    DqStandardDetailsEntityDatetime toEntity(DqStandardDetailsEntityDatetimeDTO dqStandardDetailsEntityDatetimeDTO);

    default DqStandardDetailsEntityDatetime fromId(Long id) {
        if (id == null) {
            return null;
        }
        DqStandardDetailsEntityDatetime dqStandardDetailsEntityDatetime = new DqStandardDetailsEntityDatetime();
        dqStandardDetailsEntityDatetime.setId(id);
        return dqStandardDetailsEntityDatetime;
    }
}
