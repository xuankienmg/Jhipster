package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DsCollationsService;
import com.mycompany.myapp.domain.DsCollations;
import com.mycompany.myapp.repository.DsCollationsRepository;
import com.mycompany.myapp.service.dto.DsCollationsDTO;
import com.mycompany.myapp.service.mapper.DsCollationsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DsCollations}.
 */
@Service
@Transactional
public class DsCollationsServiceImpl implements DsCollationsService {

    private final Logger log = LoggerFactory.getLogger(DsCollationsServiceImpl.class);

    private final DsCollationsRepository dsCollationsRepository;

    private final DsCollationsMapper dsCollationsMapper;

    public DsCollationsServiceImpl(DsCollationsRepository dsCollationsRepository, DsCollationsMapper dsCollationsMapper) {
        this.dsCollationsRepository = dsCollationsRepository;
        this.dsCollationsMapper = dsCollationsMapper;
    }

    /**
     * Save a dsCollations.
     *
     * @param dsCollationsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DsCollationsDTO save(DsCollationsDTO dsCollationsDTO) {
        log.debug("Request to save DsCollations : {}", dsCollationsDTO);
        DsCollations dsCollations = dsCollationsMapper.toEntity(dsCollationsDTO);
        dsCollations = dsCollationsRepository.save(dsCollations);
        return dsCollationsMapper.toDto(dsCollations);
    }

    /**
     * Get all the dsCollations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DsCollationsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DsCollations");
        return dsCollationsRepository.findAll(pageable)
            .map(dsCollationsMapper::toDto);
    }

    /**
     * Get one dsCollations by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DsCollationsDTO> findOne(Long id) {
        log.debug("Request to get DsCollations : {}", id);
        return dsCollationsRepository.findById(id)
            .map(dsCollationsMapper::toDto);
    }

    /**
     * Delete the dsCollations by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DsCollations : {}", id);
        dsCollationsRepository.deleteById(id);
    }
}
