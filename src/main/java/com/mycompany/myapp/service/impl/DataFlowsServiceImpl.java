package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DataFlowsService;
import com.mycompany.myapp.domain.DataFlows;
import com.mycompany.myapp.repository.DataFlowsRepository;
import com.mycompany.myapp.service.dto.DataFlowsDTO;
import com.mycompany.myapp.service.mapper.DataFlowsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DataFlows}.
 */
@Service
@Transactional
public class DataFlowsServiceImpl implements DataFlowsService {

    private final Logger log = LoggerFactory.getLogger(DataFlowsServiceImpl.class);

    private final DataFlowsRepository dataFlowsRepository;

    private final DataFlowsMapper dataFlowsMapper;

    public DataFlowsServiceImpl(DataFlowsRepository dataFlowsRepository, DataFlowsMapper dataFlowsMapper) {
        this.dataFlowsRepository = dataFlowsRepository;
        this.dataFlowsMapper = dataFlowsMapper;
    }

    /**
     * Save a dataFlows.
     *
     * @param dataFlowsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DataFlowsDTO save(DataFlowsDTO dataFlowsDTO) {
        log.debug("Request to save DataFlows : {}", dataFlowsDTO);
        DataFlows dataFlows = dataFlowsMapper.toEntity(dataFlowsDTO);
        dataFlows = dataFlowsRepository.save(dataFlows);
        return dataFlowsMapper.toDto(dataFlows);
    }

    /**
     * Get all the dataFlows.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DataFlowsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DataFlows");
        return dataFlowsRepository.findAll(pageable)
            .map(dataFlowsMapper::toDto);
    }

    /**
     * Get one dataFlows by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DataFlowsDTO> findOne(Long id) {
        log.debug("Request to get DataFlows : {}", id);
        return dataFlowsRepository.findById(id)
            .map(dataFlowsMapper::toDto);
    }

    /**
     * Delete the dataFlows by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DataFlows : {}", id);
        dataFlowsRepository.deleteById(id);
    }
}
