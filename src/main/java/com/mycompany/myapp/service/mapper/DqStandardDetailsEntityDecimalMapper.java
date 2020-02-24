package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityDecimalDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DqStandardDetailsEntityDecimal} and its DTO {@link DqStandardDetailsEntityDecimalDTO}.
 */
@Mapper(componentModel = "spring", uses = {DqStandardsMapper.class})
public interface DqStandardDetailsEntityDecimalMapper extends EntityMapper<DqStandardDetailsEntityDecimalDTO, DqStandardDetailsEntityDecimal> {

    @Mapping(source = "std.id", target = "stdId")
    DqStandardDetailsEntityDecimalDTO toDto(DqStandardDetailsEntityDecimal dqStandardDetailsEntityDecimal);

    @Mapping(source = "stdId", target = "std")
    DqStandardDetailsEntityDecimal toEntity(DqStandardDetailsEntityDecimalDTO dqStandardDetailsEntityDecimalDTO);

    default DqStandardDetailsEntityDecimal fromId(Long id) {
        if (id == null) {
            return null;
        }
        DqStandardDetailsEntityDecimal dqStandardDetailsEntityDecimal = new DqStandardDetailsEntityDecimal();
        dqStandardDetailsEntityDecimal.setId(id);
        return dqStandardDetailsEntityDecimal;
    }
}
