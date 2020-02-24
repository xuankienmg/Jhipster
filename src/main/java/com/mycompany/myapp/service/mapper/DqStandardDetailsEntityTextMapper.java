package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityTextDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DqStandardDetailsEntityText} and its DTO {@link DqStandardDetailsEntityTextDTO}.
 */
@Mapper(componentModel = "spring", uses = {DqStandardsMapper.class})
public interface DqStandardDetailsEntityTextMapper extends EntityMapper<DqStandardDetailsEntityTextDTO, DqStandardDetailsEntityText> {

    @Mapping(source = "std.id", target = "stdId")
    DqStandardDetailsEntityTextDTO toDto(DqStandardDetailsEntityText dqStandardDetailsEntityText);

    @Mapping(source = "stdId", target = "std")
    DqStandardDetailsEntityText toEntity(DqStandardDetailsEntityTextDTO dqStandardDetailsEntityTextDTO);

    default DqStandardDetailsEntityText fromId(Long id) {
        if (id == null) {
            return null;
        }
        DqStandardDetailsEntityText dqStandardDetailsEntityText = new DqStandardDetailsEntityText();
        dqStandardDetailsEntityText.setId(id);
        return dqStandardDetailsEntityText;
    }
}
