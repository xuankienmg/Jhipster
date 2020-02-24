package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DsTableTypesService;
import com.mycompany.myapp.domain.DsTableTypes;
import com.mycompany.myapp.repository.DsTableTypesRepository;
import com.mycompany.myapp.service.dto.DsTableTypesDTO;
import com.mycompany.myapp.service.mapper.DsTableTypesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DsTableTypes}.
 */
@Service
@Transactional
public class DsTableTypesServiceImpl implements DsTableTypesService {

    private final Logger log = LoggerFactory.getLogger(DsTableTypesServiceImpl.class);

    private final DsTableTypesRepository dsTableTypesRepository;

    private final DsTableTypesMapper dsTableTypesMapper;

    public DsTableTypesServiceImpl(DsTableTypesRepository dsTableTypesRepository, DsTableTypesMapper dsTableTypesMapper) {
        this.dsTableTypesRepository = dsTableTypesRepository;
        this.dsTableTypesMapper = dsTableTypesMapper;
    }

    /**
     * Save a dsTableTypes.
     *
     * @param dsTableTypesDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DsTableTypesDTO save(DsTableTypesDTO dsTableTypesDTO) {
        log.debug("Request to save DsTableTypes : {}", dsTableTypesDTO);
        DsTableTypes dsTableTypes = dsTableTypesMapper.toEntity(dsTableTypesDTO);
        dsTableTypes = dsTableTypesRepository.save(dsTableTypes);
        return dsTableTypesMapper.toDto(dsTableTypes);
    }

    /**
     * Get all the dsTableTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DsTableTypesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DsTableTypes");
        return dsTableTypesRepository.findAll(pageable)
            .map(dsTableTypesMapper::toDto);
    }

    /**
     * Get one dsTableTypes by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DsTableTypesDTO> findOne(Long id) {
        log.debug("Request to get DsTableTypes : {}", id);
        return dsTableTypesRepository.findById(id)
            .map(dsTableTypesMapper::toDto);
    }

    /**
     * Delete the dsTableTypes by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DsTableTypes : {}", id);
        dsTableTypesRepository.deleteById(id);
    }
}
