package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DsDbmsTypesService;
import com.mycompany.myapp.domain.DsDbmsTypes;
import com.mycompany.myapp.repository.DsDbmsTypesRepository;
import com.mycompany.myapp.service.dto.DsDbmsTypesDTO;
import com.mycompany.myapp.service.mapper.DsDbmsTypesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DsDbmsTypes}.
 */
@Service
@Transactional
public class DsDbmsTypesServiceImpl implements DsDbmsTypesService {

    private final Logger log = LoggerFactory.getLogger(DsDbmsTypesServiceImpl.class);

    private final DsDbmsTypesRepository dsDbmsTypesRepository;

    private final DsDbmsTypesMapper dsDbmsTypesMapper;

    public DsDbmsTypesServiceImpl(DsDbmsTypesRepository dsDbmsTypesRepository, DsDbmsTypesMapper dsDbmsTypesMapper) {
        this.dsDbmsTypesRepository = dsDbmsTypesRepository;
        this.dsDbmsTypesMapper = dsDbmsTypesMapper;
    }

    /**
     * Save a dsDbmsTypes.
     *
     * @param dsDbmsTypesDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DsDbmsTypesDTO save(DsDbmsTypesDTO dsDbmsTypesDTO) {
        log.debug("Request to save DsDbmsTypes : {}", dsDbmsTypesDTO);
        DsDbmsTypes dsDbmsTypes = dsDbmsTypesMapper.toEntity(dsDbmsTypesDTO);
        dsDbmsTypes = dsDbmsTypesRepository.save(dsDbmsTypes);
        return dsDbmsTypesMapper.toDto(dsDbmsTypes);
    }

    /**
     * Get all the dsDbmsTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DsDbmsTypesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DsDbmsTypes");
        return dsDbmsTypesRepository.findAll(pageable)
            .map(dsDbmsTypesMapper::toDto);
    }

    /**
     * Get one dsDbmsTypes by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DsDbmsTypesDTO> findOne(Long id) {
        log.debug("Request to get DsDbmsTypes : {}", id);
        return dsDbmsTypesRepository.findById(id)
            .map(dsDbmsTypesMapper::toDto);
    }

    /**
     * Delete the dsDbmsTypes by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DsDbmsTypes : {}", id);
        dsDbmsTypesRepository.deleteById(id);
    }
}
