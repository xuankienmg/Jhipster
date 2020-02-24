package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DsTablesService;
import com.mycompany.myapp.domain.DsTables;
import com.mycompany.myapp.repository.DsTablesRepository;
import com.mycompany.myapp.service.dto.DsTablesDTO;
import com.mycompany.myapp.service.mapper.DsTablesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DsTables}.
 */
@Service
@Transactional
public class DsTablesServiceImpl implements DsTablesService {

    private final Logger log = LoggerFactory.getLogger(DsTablesServiceImpl.class);

    private final DsTablesRepository dsTablesRepository;

    private final DsTablesMapper dsTablesMapper;

    public DsTablesServiceImpl(DsTablesRepository dsTablesRepository, DsTablesMapper dsTablesMapper) {
        this.dsTablesRepository = dsTablesRepository;
        this.dsTablesMapper = dsTablesMapper;
    }

    /**
     * Save a dsTables.
     *
     * @param dsTablesDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DsTablesDTO save(DsTablesDTO dsTablesDTO) {
        log.debug("Request to save DsTables : {}", dsTablesDTO);
        DsTables dsTables = dsTablesMapper.toEntity(dsTablesDTO);
        dsTables = dsTablesRepository.save(dsTables);
        return dsTablesMapper.toDto(dsTables);
    }

    /**
     * Get all the dsTables.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DsTablesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DsTables");
        return dsTablesRepository.findAll(pageable)
            .map(dsTablesMapper::toDto);
    }

    /**
     * Get one dsTables by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DsTablesDTO> findOne(Long id) {
        log.debug("Request to get DsTables : {}", id);
        return dsTablesRepository.findById(id)
            .map(dsTablesMapper::toDto);
    }

    /**
     * Delete the dsTables by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DsTables : {}", id);
        dsTablesRepository.deleteById(id);
    }
}
