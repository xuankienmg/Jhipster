package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DpSourceColumnsService;
import com.mycompany.myapp.domain.DpSourceColumns;
import com.mycompany.myapp.repository.DpSourceColumnsRepository;
import com.mycompany.myapp.service.dto.DpSourceColumnsDTO;
import com.mycompany.myapp.service.mapper.DpSourceColumnsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DpSourceColumns}.
 */
@Service
@Transactional
public class DpSourceColumnsServiceImpl implements DpSourceColumnsService {

    private final Logger log = LoggerFactory.getLogger(DpSourceColumnsServiceImpl.class);

    private final DpSourceColumnsRepository dpSourceColumnsRepository;

    private final DpSourceColumnsMapper dpSourceColumnsMapper;

    public DpSourceColumnsServiceImpl(DpSourceColumnsRepository dpSourceColumnsRepository, DpSourceColumnsMapper dpSourceColumnsMapper) {
        this.dpSourceColumnsRepository = dpSourceColumnsRepository;
        this.dpSourceColumnsMapper = dpSourceColumnsMapper;
    }

    /**
     * Save a dpSourceColumns.
     *
     * @param dpSourceColumnsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DpSourceColumnsDTO save(DpSourceColumnsDTO dpSourceColumnsDTO) {
        log.debug("Request to save DpSourceColumns : {}", dpSourceColumnsDTO);
        DpSourceColumns dpSourceColumns = dpSourceColumnsMapper.toEntity(dpSourceColumnsDTO);
        dpSourceColumns = dpSourceColumnsRepository.save(dpSourceColumns);
        return dpSourceColumnsMapper.toDto(dpSourceColumns);
    }

    /**
     * Get all the dpSourceColumns.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DpSourceColumnsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DpSourceColumns");
        return dpSourceColumnsRepository.findAll(pageable)
            .map(dpSourceColumnsMapper::toDto);
    }

    /**
     * Get one dpSourceColumns by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DpSourceColumnsDTO> findOne(Long id) {
        log.debug("Request to get DpSourceColumns : {}", id);
        return dpSourceColumnsRepository.findById(id)
            .map(dpSourceColumnsMapper::toDto);
    }

    /**
     * Delete the dpSourceColumns by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DpSourceColumns : {}", id);
        dpSourceColumnsRepository.deleteById(id);
    }
}
