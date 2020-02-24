package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.DsStoresDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DsStores} and its DTO {@link DsStoresDTO}.
 */
@Mapper(componentModel = "spring", uses = {DsDbmsTypesMapper.class, DsCollationsMapper.class})
public interface DsStoresMapper extends EntityMapper<DsStoresDTO, DsStores> {

    @Mapping(source = "storeDbmsType.id", target = "storeDbmsTypeId")
    @Mapping(source = "storeCollation.id", target = "storeCollationId")
    DsStoresDTO toDto(DsStores dsStores);

    @Mapping(source = "storeDbmsTypeId", target = "storeDbmsType")
    @Mapping(source = "storeCollationId", target = "storeCollation")
    DsStores toEntity(DsStoresDTO dsStoresDTO);

    default DsStores fromId(Long id) {
        if (id == null) {
            return null;
        }
        DsStores dsStores = new DsStores();
        dsStores.setId(id);
        return dsStores;
    }
}
