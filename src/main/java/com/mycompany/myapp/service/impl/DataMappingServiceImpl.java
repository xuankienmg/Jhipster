package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DataMappingService;
import com.mycompany.myapp.domain.DataMapping;
import com.mycompany.myapp.repository.DataMappingRepository;
import com.mycompany.myapp.service.dto.DataMappingDTO;
import com.mycompany.myapp.service.mapper.DataMappingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DataMapping}.
 */
@Service
@Transactional
public class DataMappingServiceImpl implements DataMappingService {

    private final Logger log = LoggerFactory.getLogger(DataMappingServiceImpl.class);

    private final DataMappingRepository dataMappingRepository;

    private final DataMappingMapper dataMappingMapper;

    public DataMappingServiceImpl(DataMappingRepository dataMappingRepository, DataMappingMapper dataMappingMapper) {
        this.dataMappingRepository = dataMappingRepository;
        this.dataMappingMapper = dataMappingMapper;
    }

    /**
     * Save a dataMapping.
     *
     * @param dataMappingDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DataMappingDTO save(DataMappingDTO dataMappingDTO) {
        log.debug("Request to save DataMapping : {}", dataMappingDTO);
        DataMapping dataMapping = dataMappingMapper.toEntity(dataMappingDTO);
        dataMapping = dataMappingRepository.save(dataMapping);
        return dataMappingMapper.toDto(dataMapping);
    }

    /**
     * Get all the dataMappings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DataMappingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DataMappings");
        return dataMappingRepository.findAll(pageable)
            .map(dataMappingMapper::toDto);
    }

    /**
     * Get one dataMapping by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DataMappingDTO> findOne(Long id) {
        log.debug("Request to get DataMapping : {}", id);
        return dataMappingRepository.findById(id)
            .map(dataMappingMapper::toDto);
    }

    /**
     * Delete the dataMapping by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DataMapping : {}", id);
        dataMappingRepository.deleteById(id);
    }
}
