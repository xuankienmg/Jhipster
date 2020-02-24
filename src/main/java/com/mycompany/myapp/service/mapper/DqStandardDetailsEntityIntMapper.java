package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityIntDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DqStandardDetailsEntityInt} and its DTO {@link DqStandardDetailsEntityIntDTO}.
 */
@Mapper(componentModel = "spring", uses = {DqStandardsMapper.class})
public interface DqStandardDetailsEntityIntMapper extends EntityMapper<DqStandardDetailsEntityIntDTO, DqStandardDetailsEntityInt> {

    @Mapping(source = "std.id", target = "stdId")
    DqStandardDetailsEntityIntDTO toDto(DqStandardDetailsEntityInt dqStandardDetailsEntityInt);

    @Mapping(source = "stdId", target = "std")
    DqStandardDetailsEntityInt toEntity(DqStandardDetailsEntityIntDTO dqStandardDetailsEntityIntDTO);

    default DqStandardDetailsEntityInt fromId(Long id) {
        if (id == null) {
            return null;
        }
        DqStandardDetailsEntityInt dqStandardDetailsEntityInt = new DqStandardDetailsEntityInt();
        dqStandardDetailsEntityInt.setId(id);
        return dqStandardDetailsEntityInt;
    }
}
