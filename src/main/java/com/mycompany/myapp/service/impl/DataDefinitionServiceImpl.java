package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DataDefinitionService;
import com.mycompany.myapp.domain.DataDefinition;
import com.mycompany.myapp.repository.DataDefinitionRepository;
import com.mycompany.myapp.service.dto.DataDefinitionDTO;
import com.mycompany.myapp.service.mapper.DataDefinitionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DataDefinition}.
 */
@Service
@Transactional
public class DataDefinitionServiceImpl implements DataDefinitionService {

    private final Logger log = LoggerFactory.getLogger(DataDefinitionServiceImpl.class);

    private final DataDefinitionRepository dataDefinitionRepository;

    private final DataDefinitionMapper dataDefinitionMapper;

    public DataDefinitionServiceImpl(DataDefinitionRepository dataDefinitionRepository, DataDefinitionMapper dataDefinitionMapper) {
        this.dataDefinitionRepository = dataDefinitionRepository;
        this.dataDefinitionMapper = dataDefinitionMapper;
    }

    /**
     * Save a dataDefinition.
     *
     * @param dataDefinitionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DataDefinitionDTO save(DataDefinitionDTO dataDefinitionDTO) {
        log.debug("Request to save DataDefinition : {}", dataDefinitionDTO);
        DataDefinition dataDefinition = dataDefinitionMapper.toEntity(dataDefinitionDTO);
        dataDefinition = dataDefinitionRepository.save(dataDefinition);
        return dataDefinitionMapper.toDto(dataDefinition);
    }

    /**
     * Get all the dataDefinitions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DataDefinitionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DataDefinitions");
        return dataDefinitionRepository.findAll(pageable)
            .map(dataDefinitionMapper::toDto);
    }

    /**
     * Get one dataDefinition by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DataDefinitionDTO> findOne(Long id) {
        log.debug("Request to get DataDefinition : {}", id);
        return dataDefinitionRepository.findById(id)
            .map(dataDefinitionMapper::toDto);
    }

    /**
     * Delete the dataDefinition by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DataDefinition : {}", id);
        dataDefinitionRepository.deleteById(id);
    }
}
