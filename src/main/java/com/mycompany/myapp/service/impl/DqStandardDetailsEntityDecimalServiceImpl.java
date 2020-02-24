package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DqStandardDetailsEntityDecimalService;
import com.mycompany.myapp.domain.DqStandardDetailsEntityDecimal;
import com.mycompany.myapp.repository.DqStandardDetailsEntityDecimalRepository;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityDecimalDTO;
import com.mycompany.myapp.service.mapper.DqStandardDetailsEntityDecimalMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DqStandardDetailsEntityDecimal}.
 */
@Service
@Transactional
public class DqStandardDetailsEntityDecimalServiceImpl implements DqStandardDetailsEntityDecimalService {

    private final Logger log = LoggerFactory.getLogger(DqStandardDetailsEntityDecimalServiceImpl.class);

    private final DqStandardDetailsEntityDecimalRepository dqStandardDetailsEntityDecimalRepository;

    private final DqStandardDetailsEntityDecimalMapper dqStandardDetailsEntityDecimalMapper;

    public DqStandardDetailsEntityDecimalServiceImpl(DqStandardDetailsEntityDecimalRepository dqStandardDetailsEntityDecimalRepository, DqStandardDetailsEntityDecimalMapper dqStandardDetailsEntityDecimalMapper) {
        this.dqStandardDetailsEntityDecimalRepository = dqStandardDetailsEntityDecimalRepository;
        this.dqStandardDetailsEntityDecimalMapper = dqStandardDetailsEntityDecimalMapper;
    }

    /**
     * Save a dqStandardDetailsEntityDecimal.
     *
     * @param dqStandardDetailsEntityDecimalDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DqStandardDetailsEntityDecimalDTO save(DqStandardDetailsEntityDecimalDTO dqStandardDetailsEntityDecimalDTO) {
        log.debug("Request to save DqStandardDetailsEntityDecimal : {}", dqStandardDetailsEntityDecimalDTO);
        DqStandardDetailsEntityDecimal dqStandardDetailsEntityDecimal = dqStandardDetailsEntityDecimalMapper.toEntity(dqStandardDetailsEntityDecimalDTO);
        dqStandardDetailsEntityDecimal = dqStandardDetailsEntityDecimalRepository.save(dqStandardDetailsEntityDecimal);
        return dqStandardDetailsEntityDecimalMapper.toDto(dqStandardDetailsEntityDecimal);
    }

    /**
     * Get all the dqStandardDetailsEntityDecimals.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DqStandardDetailsEntityDecimalDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DqStandardDetailsEntityDecimals");
        return dqStandardDetailsEntityDecimalRepository.findAll(pageable)
            .map(dqStandardDetailsEntityDecimalMapper::toDto);
    }

    /**
     * Get one dqStandardDetailsEntityDecimal by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DqStandardDetailsEntityDecimalDTO> findOne(Long id) {
        log.debug("Request to get DqStandardDetailsEntityDecimal : {}", id);
        return dqStandardDetailsEntityDecimalRepository.findById(id)
            .map(dqStandardDetailsEntityDecimalMapper::toDto);
    }

    /**
     * Delete the dqStandardDetailsEntityDecimal by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DqStandardDetailsEntityDecimal : {}", id);
        dqStandardDetailsEntityDecimalRepository.deleteById(id);
    }
}
