package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DqRuleRiskLevelsService;
import com.mycompany.myapp.domain.DqRuleRiskLevels;
import com.mycompany.myapp.repository.DqRuleRiskLevelsRepository;
import com.mycompany.myapp.service.dto.DqRuleRiskLevelsDTO;
import com.mycompany.myapp.service.mapper.DqRuleRiskLevelsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DqRuleRiskLevels}.
 */
@Service
@Transactional
public class DqRuleRiskLevelsServiceImpl implements DqRuleRiskLevelsService {

    private final Logger log = LoggerFactory.getLogger(DqRuleRiskLevelsServiceImpl.class);

    private final DqRuleRiskLevelsRepository dqRuleRiskLevelsRepository;

    private final DqRuleRiskLevelsMapper dqRuleRiskLevelsMapper;

    public DqRuleRiskLevelsServiceImpl(DqRuleRiskLevelsRepository dqRuleRiskLevelsRepository, DqRuleRiskLevelsMapper dqRuleRiskLevelsMapper) {
        this.dqRuleRiskLevelsRepository = dqRuleRiskLevelsRepository;
        this.dqRuleRiskLevelsMapper = dqRuleRiskLevelsMapper;
    }

    /**
     * Save a dqRuleRiskLevels.
     *
     * @param dqRuleRiskLevelsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DqRuleRiskLevelsDTO save(DqRuleRiskLevelsDTO dqRuleRiskLevelsDTO) {
        log.debug("Request to save DqRuleRiskLevels : {}", dqRuleRiskLevelsDTO);
        DqRuleRiskLevels dqRuleRiskLevels = dqRuleRiskLevelsMapper.toEntity(dqRuleRiskLevelsDTO);
        dqRuleRiskLevels = dqRuleRiskLevelsRepository.save(dqRuleRiskLevels);
        return dqRuleRiskLevelsMapper.toDto(dqRuleRiskLevels);
    }

    /**
     * Get all the dqRuleRiskLevels.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DqRuleRiskLevelsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DqRuleRiskLevels");
        return dqRuleRiskLevelsRepository.findAll(pageable)
            .map(dqRuleRiskLevelsMapper::toDto);
    }

    /**
     * Get one dqRuleRiskLevels by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DqRuleRiskLevelsDTO> findOne(Long id) {
        log.debug("Request to get DqRuleRiskLevels : {}", id);
        return dqRuleRiskLevelsRepository.findById(id)
            .map(dqRuleRiskLevelsMapper::toDto);
    }

    /**
     * Delete the dqRuleRiskLevels by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DqRuleRiskLevels : {}", id);
        dqRuleRiskLevelsRepository.deleteById(id);
    }
}
