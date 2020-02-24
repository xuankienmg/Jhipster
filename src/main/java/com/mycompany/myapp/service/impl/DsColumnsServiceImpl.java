package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DsColumnsService;
import com.mycompany.myapp.domain.DsColumns;
import com.mycompany.myapp.repository.DsColumnsRepository;
import com.mycompany.myapp.service.dto.DsColumnsDTO;
import com.mycompany.myapp.service.mapper.DsColumnsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DsColumns}.
 */
@Service
@Transactional
public class DsColumnsServiceImpl implements DsColumnsService {

    private final Logger log = LoggerFactory.getLogger(DsColumnsServiceImpl.class);

    private final DsColumnsRepository dsColumnsRepository;

    private final DsColumnsMapper dsColumnsMapper;

    public DsColumnsServiceImpl(DsColumnsRepository dsColumnsRepository, DsColumnsMapper dsColumnsMapper) {
        this.dsColumnsRepository = dsColumnsRepository;
        this.dsColumnsMapper = dsColumnsMapper;
    }

    /**
     * Save a dsColumns.
     *
     * @param dsColumnsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DsColumnsDTO save(DsColumnsDTO dsColumnsDTO) {
        log.debug("Request to save DsColumns : {}", dsColumnsDTO);
        DsColumns dsColumns = dsColumnsMapper.toEntity(dsColumnsDTO);
        dsColumns = dsColumnsRepository.save(dsColumns);
        return dsColumnsMapper.toDto(dsColumns);
    }

    /**
     * Get all the dsColumns.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DsColumnsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DsColumns");
        return dsColumnsRepository.findAll(pageable)
            .map(dsColumnsMapper::toDto);
    }

    /**
     * Get all the dsColumns with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<DsColumnsDTO> findAllWithEagerRelationships(Pageable pageable) {
        return dsColumnsRepository.findAllWithEagerRelationships(pageable).map(dsColumnsMapper::toDto);
    }

    /**
     * Get one dsColumns by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DsColumnsDTO> findOne(Long id) {
        log.debug("Request to get DsColumns : {}", id);
        return dsColumnsRepository.findOneWithEagerRelationships(id)
            .map(dsColumnsMapper::toDto);
    }

    /**
     * Delete the dsColumns by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DsColumns : {}", id);
        dsColumnsRepository.deleteById(id);
    }
}
