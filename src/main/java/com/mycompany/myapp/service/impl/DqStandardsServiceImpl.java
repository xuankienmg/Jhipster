package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DqStandardsService;
import com.mycompany.myapp.domain.DqStandards;
import com.mycompany.myapp.repository.DqStandardsRepository;
import com.mycompany.myapp.service.dto.DqStandardsDTO;
import com.mycompany.myapp.service.mapper.DqStandardsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DqStandards}.
 */
@Service
@Transactional
public class DqStandardsServiceImpl implements DqStandardsService {

    private final Logger log = LoggerFactory.getLogger(DqStandardsServiceImpl.class);

    private final DqStandardsRepository dqStandardsRepository;

    private final DqStandardsMapper dqStandardsMapper;

    public DqStandardsServiceImpl(DqStandardsRepository dqStandardsRepository, DqStandardsMapper dqStandardsMapper) {
        this.dqStandardsRepository = dqStandardsRepository;
        this.dqStandardsMapper = dqStandardsMapper;
    }

    /**
     * Save a dqStandards.
     *
     * @param dqStandardsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DqStandardsDTO save(DqStandardsDTO dqStandardsDTO) {
        log.debug("Request to save DqStandards : {}", dqStandardsDTO);
        DqStandards dqStandards = dqStandardsMapper.toEntity(dqStandardsDTO);
        dqStandards = dqStandardsRepository.save(dqStandards);
        return dqStandardsMapper.toDto(dqStandards);
    }

    /**
     * Get all the dqStandards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DqStandardsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DqStandards");
        return dqStandardsRepository.findAll(pageable)
            .map(dqStandardsMapper::toDto);
    }

    /**
     * Get all the dqStandards with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<DqStandardsDTO> findAllWithEagerRelationships(Pageable pageable) {
        return dqStandardsRepository.findAllWithEagerRelationships(pageable).map(dqStandardsMapper::toDto);
    }

    /**
     * Get one dqStandards by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DqStandardsDTO> findOne(Long id) {
        log.debug("Request to get DqStandards : {}", id);
        return dqStandardsRepository.findOneWithEagerRelationships(id)
            .map(dqStandardsMapper::toDto);
    }

    /**
     * Delete the dqStandards by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DqStandards : {}", id);
        dqStandardsRepository.deleteById(id);
    }
}
