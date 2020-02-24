package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.DataFlowsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DataFlows} and its DTO {@link DataFlowsDTO}.
 */
@Mapper(componentModel = "spring", uses = {EtlStatusMapper.class, EtlPackagesMapper.class})
public interface DataFlowsMapper extends EntityMapper<DataFlowsDTO, DataFlows> {

    @Mapping(source = "etlStatus.id", target = "etlStatusId")
    @Mapping(source = "etlPkg.id", target = "etlPkgId")
    DataFlowsDTO toDto(DataFlows dataFlows);

    @Mapping(source = "etlStatusId", target = "etlStatus")
    @Mapping(source = "etlPkgId", target = "etlPkg")
    DataFlows toEntity(DataFlowsDTO dataFlowsDTO);

    default DataFlows fromId(Long id) {
        if (id == null) {
            return null;
        }
        DataFlows dataFlows = new DataFlows();
        dataFlows.setId(id);
        return dataFlows;
    }
}
