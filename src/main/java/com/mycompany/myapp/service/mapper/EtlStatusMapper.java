package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EtlStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EtlStatus} and its DTO {@link EtlStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EtlStatusMapper extends EntityMapper<EtlStatusDTO, EtlStatus> {



    default EtlStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        EtlStatus etlStatus = new EtlStatus();
        etlStatus.setId(id);
        return etlStatus;
    }
}
