package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EtlPackagesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EtlPackages} and its DTO {@link EtlPackagesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EtlPackagesMapper extends EntityMapper<EtlPackagesDTO, EtlPackages> {



    default EtlPackages fromId(Long id) {
        if (id == null) {
            return null;
        }
        EtlPackages etlPackages = new EtlPackages();
        etlPackages.setId(id);
        return etlPackages;
    }
}
