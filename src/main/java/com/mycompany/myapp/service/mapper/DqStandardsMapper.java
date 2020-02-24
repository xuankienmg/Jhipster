package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.DqStandardsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DqStandards} and its DTO {@link DqStandardsDTO}.
 */
@Mapper(componentModel = "spring", uses = {DqStandardTypesMapper.class, DqRulesMapper.class})
public interface DqStandardsMapper extends EntityMapper<DqStandardsDTO, DqStandards> {

    @Mapping(source = "stdType.id", target = "stdTypeId")
    DqStandardsDTO toDto(DqStandards dqStandards);

    @Mapping(source = "stdTypeId", target = "stdType")
    @Mapping(target = "removeRule", ignore = true)
    DqStandards toEntity(DqStandardsDTO dqStandardsDTO);

    default DqStandards fromId(Long id) {
        if (id == null) {
            return null;
        }
        DqStandards dqStandards = new DqStandards();
        dqStandards.setId(id);
        return dqStandards;
    }
}
