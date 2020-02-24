package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DqRuleActionsService;
import com.mycompany.myapp.domain.DqRuleActions;
import com.mycompany.myapp.repository.DqRuleActionsRepository;
import com.mycompany.myapp.service.dto.DqRuleActionsDTO;
import com.mycompany.myapp.service.mapper.DqRuleActionsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DqRuleActions}.
 */
@Service
@Transactional
public class DqRuleActionsServiceImpl implements DqRuleActionsService {

    private final Logger log = LoggerFactory.getLogger(DqRuleActionsServiceImpl.class);

    private final DqRuleActionsRepository dqRuleActionsRepository;

    private final DqRuleActionsMapper dqRuleActionsMapper;

    public DqRuleActionsServiceImpl(DqRuleActionsRepository dqRuleActionsRepository, DqRuleActionsMapper dqRuleActionsMapper) {
        this.dqRuleActionsRepository = dqRuleActionsRepository;
        this.dqRuleActionsMapper = dqRuleActionsMapper;
    }

    /**
     * Save a dqRuleActions.
     *
     * @param dqRuleActionsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DqRuleActionsDTO save(DqRuleActionsDTO dqRuleActionsDTO) {
        log.debug("Request to save DqRuleActions : {}", dqRuleActionsDTO);
        DqRuleActions dqRuleActions = dqRuleActionsMapper.toEntity(dqRuleActionsDTO);
        dqRuleActions = dqRuleActionsRepository.save(dqRuleActions);
        return dqRuleActionsMapper.toDto(dqRuleActions);
    }

    /**
     * Get all the dqRuleActions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DqRuleActionsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DqRuleActions");
        return dqRuleActionsRepository.findAll(pageable)
            .map(dqRuleActionsMapper::toDto);
    }

    /**
     * Get one dqRuleActions by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DqRuleActionsDTO> findOne(Long id) {
        log.debug("Request to get DqRuleActions : {}", id);
        return dqRuleActionsRepository.findById(id)
            .map(dqRuleActionsMapper::toDto);
    }

    /**
     * Delete the dqRuleActions by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DqRuleActions : {}", id);
        dqRuleActionsRepository.deleteById(id);
    }
}
