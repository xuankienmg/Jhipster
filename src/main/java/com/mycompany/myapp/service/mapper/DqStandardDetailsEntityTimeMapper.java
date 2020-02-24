package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityTimeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DqStandardDetailsEntityTime} and its DTO {@link DqStandardDetailsEntityTimeDTO}.
 */
@Mapper(componentModel = "spring", uses = {DqStandardsMapper.class})
public interface DqStandardDetailsEntityTimeMapper extends EntityMapper<DqStandardDetailsEntityTimeDTO, DqStandardDetailsEntityTime> {

    @Mapping(source = "std.id", target = "stdId")
    DqStandardDetailsEntityTimeDTO toDto(DqStandardDetailsEntityTime dqStandardDetailsEntityTime);

    @Mapping(source = "stdId", target = "std")
    DqStandardDetailsEntityTime toEntity(DqStandardDetailsEntityTimeDTO dqStandardDetailsEntityTimeDTO);

    default DqStandardDetailsEntityTime fromId(Long id) {
        if (id == null) {
            return null;
        }
        DqStandardDetailsEntityTime dqStandardDetailsEntityTime = new DqStandardDetailsEntityTime();
        dqStandardDetailsEntityTime.setId(id);
        return dqStandardDetailsEntityTime;
    }
}
