package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DqStandardTypesService;
import com.mycompany.myapp.domain.DqStandardTypes;
import com.mycompany.myapp.repository.DqStandardTypesRepository;
import com.mycompany.myapp.service.dto.DqStandardTypesDTO;
import com.mycompany.myapp.service.mapper.DqStandardTypesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DqStandardTypes}.
 */
@Service
@Transactional
public class DqStandardTypesServiceImpl implements DqStandardTypesService {

    private final Logger log = LoggerFactory.getLogger(DqStandardTypesServiceImpl.class);

    private final DqStandardTypesRepository dqStandardTypesRepository;

    private final DqStandardTypesMapper dqStandardTypesMapper;

    public DqStandardTypesServiceImpl(DqStandardTypesRepository dqStandardTypesRepository, DqStandardTypesMapper dqStandardTypesMapper) {
        this.dqStandardTypesRepository = dqStandardTypesRepository;
        this.dqStandardTypesMapper = dqStandardTypesMapper;
    }

    /**
     * Save a dqStandardTypes.
     *
     * @param dqStandardTypesDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DqStandardTypesDTO save(DqStandardTypesDTO dqStandardTypesDTO) {
        log.debug("Request to save DqStandardTypes : {}", dqStandardTypesDTO);
        DqStandardTypes dqStandardTypes = dqStandardTypesMapper.toEntity(dqStandardTypesDTO);
        dqStandardTypes = dqStandardTypesRepository.save(dqStandardTypes);
        return dqStandardTypesMapper.toDto(dqStandardTypes);
    }

    /**
     * Get all the dqStandardTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DqStandardTypesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DqStandardTypes");
        return dqStandardTypesRepository.findAll(pageable)
            .map(dqStandardTypesMapper::toDto);
    }

    /**
     * Get one dqStandardTypes by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DqStandardTypesDTO> findOne(Long id) {
        log.debug("Request to get DqStandardTypes : {}", id);
        return dqStandardTypesRepository.findById(id)
            .map(dqStandardTypesMapper::toDto);
    }

    /**
     * Delete the dqStandardTypes by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DqStandardTypes : {}", id);
        dqStandardTypesRepository.deleteById(id);
    }
}
