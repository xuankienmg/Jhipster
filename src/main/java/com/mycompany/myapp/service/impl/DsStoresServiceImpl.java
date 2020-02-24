package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DsStoresService;
import com.mycompany.myapp.domain.DsStores;
import com.mycompany.myapp.repository.DsStoresRepository;
import com.mycompany.myapp.service.dto.DsStoresDTO;
import com.mycompany.myapp.service.mapper.DsStoresMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DsStores}.
 */
@Service
@Transactional
public class DsStoresServiceImpl implements DsStoresService {

    private final Logger log = LoggerFactory.getLogger(DsStoresServiceImpl.class);

    private final DsStoresRepository dsStoresRepository;

    private final DsStoresMapper dsStoresMapper;

    public DsStoresServiceImpl(DsStoresRepository dsStoresRepository, DsStoresMapper dsStoresMapper) {
        this.dsStoresRepository = dsStoresRepository;
        this.dsStoresMapper = dsStoresMapper;
    }

    /**
     * Save a dsStores.
     *
     * @param dsStoresDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DsStoresDTO save(DsStoresDTO dsStoresDTO) {
        log.debug("Request to save DsStores : {}", dsStoresDTO);
        DsStores dsStores = dsStoresMapper.toEntity(dsStoresDTO);
        dsStores = dsStoresRepository.save(dsStores);
        return dsStoresMapper.toDto(dsStores);
    }

    /**
     * Get all the dsStores.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DsStoresDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DsStores");
        return dsStoresRepository.findAll(pageable)
            .map(dsStoresMapper::toDto);
    }

    /**
     * Get one dsStores by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DsStoresDTO> findOne(Long id) {
        log.debug("Request to get DsStores : {}", id);
        return dsStoresRepository.findById(id)
            .map(dsStoresMapper::toDto);
    }

    /**
     * Delete the dsStores by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DsStores : {}", id);
        dsStoresRepository.deleteById(id);
    }
}
