package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DqRulesService;
import com.mycompany.myapp.domain.DqRules;
import com.mycompany.myapp.repository.DqRulesRepository;
import com.mycompany.myapp.service.dto.DqRulesDTO;
import com.mycompany.myapp.service.mapper.DqRulesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DqRules}.
 */
@Service
@Transactional
public class DqRulesServiceImpl implements DqRulesService {

    private final Logger log = LoggerFactory.getLogger(DqRulesServiceImpl.class);

    private final DqRulesRepository dqRulesRepository;

    private final DqRulesMapper dqRulesMapper;

    public DqRulesServiceImpl(DqRulesRepository dqRulesRepository, DqRulesMapper dqRulesMapper) {
        this.dqRulesRepository = dqRulesRepository;
        this.dqRulesMapper = dqRulesMapper;
    }

    /**
     * Save a dqRules.
     *
     * @param dqRulesDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DqRulesDTO save(DqRulesDTO dqRulesDTO) {
        log.debug("Request to save DqRules : {}", dqRulesDTO);
        DqRules dqRules = dqRulesMapper.toEntity(dqRulesDTO);
        dqRules = dqRulesRepository.save(dqRules);
        return dqRulesMapper.toDto(dqRules);
    }

    /**
     * Get all the dqRules.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DqRulesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DqRules");
        return dqRulesRepository.findAll(pageable)
            .map(dqRulesMapper::toDto);
    }

    /**
     * Get one dqRules by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DqRulesDTO> findOne(Long id) {
        log.debug("Request to get DqRules : {}", id);
        return dqRulesRepository.findById(id)
            .map(dqRulesMapper::toDto);
    }

    /**
     * Delete the dqRules by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DqRules : {}", id);
        dqRulesRepository.deleteById(id);
    }
}
