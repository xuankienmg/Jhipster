package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityVarcharDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DqStandardDetailsEntityVarchar} and its DTO {@link DqStandardDetailsEntityVarcharDTO}.
 */
@Mapper(componentModel = "spring", uses = {DqStandardsMapper.class})
public interface DqStandardDetailsEntityVarcharMapper extends EntityMapper<DqStandardDetailsEntityVarcharDTO, DqStandardDetailsEntityVarchar> {

    @Mapping(source = "std.id", target = "stdId")
    DqStandardDetailsEntityVarcharDTO toDto(DqStandardDetailsEntityVarchar dqStandardDetailsEntityVarchar);

    @Mapping(source = "stdId", target = "std")
    DqStandardDetailsEntityVarchar toEntity(DqStandardDetailsEntityVarcharDTO dqStandardDetailsEntityVarcharDTO);

    default DqStandardDetailsEntityVarchar fromId(Long id) {
        if (id == null) {
            return null;
        }
        DqStandardDetailsEntityVarchar dqStandardDetailsEntityVarchar = new DqStandardDetailsEntityVarchar();
        dqStandardDetailsEntityVarchar.setId(id);
        return dqStandardDetailsEntityVarchar;
    }
}
