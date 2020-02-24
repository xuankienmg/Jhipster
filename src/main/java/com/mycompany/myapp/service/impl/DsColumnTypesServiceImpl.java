package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DsColumnTypesService;
import com.mycompany.myapp.domain.DsColumnTypes;
import com.mycompany.myapp.repository.DsColumnTypesRepository;
import com.mycompany.myapp.service.dto.DsColumnTypesDTO;
import com.mycompany.myapp.service.mapper.DsColumnTypesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DsColumnTypes}.
 */
@Service
@Transactional
public class DsColumnTypesServiceImpl implements DsColumnTypesService {

    private final Logger log = LoggerFactory.getLogger(DsColumnTypesServiceImpl.class);

    private final DsColumnTypesRepository dsColumnTypesRepository;

    private final DsColumnTypesMapper dsColumnTypesMapper;

    public DsColumnTypesServiceImpl(DsColumnTypesRepository dsColumnTypesRepository, DsColumnTypesMapper dsColumnTypesMapper) {
        this.dsColumnTypesRepository = dsColumnTypesRepository;
        this.dsColumnTypesMapper = dsColumnTypesMapper;
    }

    /**
     * Save a dsColumnTypes.
     *
     * @param dsColumnTypesDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DsColumnTypesDTO save(DsColumnTypesDTO dsColumnTypesDTO) {
        log.debug("Request to save DsColumnTypes : {}", dsColumnTypesDTO);
        DsColumnTypes dsColumnTypes = dsColumnTypesMapper.toEntity(dsColumnTypesDTO);
        dsColumnTypes = dsColumnTypesRepository.save(dsColumnTypes);
        return dsColumnTypesMapper.toDto(dsColumnTypes);
    }

    /**
     * Get all the dsColumnTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DsColumnTypesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DsColumnTypes");
        return dsColumnTypesRepository.findAll(pageable)
            .map(dsColumnTypesMapper::toDto);
    }

    /**
     * Get one dsColumnTypes by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DsColumnTypesDTO> findOne(Long id) {
        log.debug("Request to get DsColumnTypes : {}", id);
        return dsColumnTypesRepository.findById(id)
            .map(dsColumnTypesMapper::toDto);
    }

    /**
     * Delete the dsColumnTypes by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DsColumnTypes : {}", id);
        dsColumnTypesRepository.deleteById(id);
    }
}
