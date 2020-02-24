package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DpSourceTablesService;
import com.mycompany.myapp.domain.DpSourceTables;
import com.mycompany.myapp.repository.DpSourceTablesRepository;
import com.mycompany.myapp.service.dto.DpSourceTablesDTO;
import com.mycompany.myapp.service.mapper.DpSourceTablesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DpSourceTables}.
 */
@Service
@Transactional
public class DpSourceTablesServiceImpl implements DpSourceTablesService {

    private final Logger log = LoggerFactory.getLogger(DpSourceTablesServiceImpl.class);

    private final DpSourceTablesRepository dpSourceTablesRepository;

    private final DpSourceTablesMapper dpSourceTablesMapper;

    public DpSourceTablesServiceImpl(DpSourceTablesRepository dpSourceTablesRepository, DpSourceTablesMapper dpSourceTablesMapper) {
        this.dpSourceTablesRepository = dpSourceTablesRepository;
        this.dpSourceTablesMapper = dpSourceTablesMapper;
    }

    /**
     * Save a dpSourceTables.
     *
     * @param dpSourceTablesDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DpSourceTablesDTO save(DpSourceTablesDTO dpSourceTablesDTO) {
        log.debug("Request to save DpSourceTables : {}", dpSourceTablesDTO);
        DpSourceTables dpSourceTables = dpSourceTablesMapper.toEntity(dpSourceTablesDTO);
        dpSourceTables = dpSourceTablesRepository.save(dpSourceTables);
        return dpSourceTablesMapper.toDto(dpSourceTables);
    }

    /**
     * Get all the dpSourceTables.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DpSourceTablesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DpSourceTables");
        return dpSourceTablesRepository.findAll(pageable)
            .map(dpSourceTablesMapper::toDto);
    }

    /**
     * Get one dpSourceTables by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DpSourceTablesDTO> findOne(Long id) {
        log.debug("Request to get DpSourceTables : {}", id);
        return dpSourceTablesRepository.findById(id)
            .map(dpSourceTablesMapper::toDto);
    }

    /**
     * Delete the dpSourceTables by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DpSourceTables : {}", id);
        dpSourceTablesRepository.deleteById(id);
    }
}
