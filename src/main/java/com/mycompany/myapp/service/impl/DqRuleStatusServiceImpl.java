package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DqRuleStatusService;
import com.mycompany.myapp.domain.DqRuleStatus;
import com.mycompany.myapp.repository.DqRuleStatusRepository;
import com.mycompany.myapp.service.dto.DqRuleStatusDTO;
import com.mycompany.myapp.service.mapper.DqRuleStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DqRuleStatus}.
 */
@Service
@Transactional
public class DqRuleStatusServiceImpl implements DqRuleStatusService {

    private final Logger log = LoggerFactory.getLogger(DqRuleStatusServiceImpl.class);

    private final DqRuleStatusRepository dqRuleStatusRepository;

    private final DqRuleStatusMapper dqRuleStatusMapper;

    public DqRuleStatusServiceImpl(DqRuleStatusRepository dqRuleStatusRepository, DqRuleStatusMapper dqRuleStatusMapper) {
        this.dqRuleStatusRepository = dqRuleStatusRepository;
        this.dqRuleStatusMapper = dqRuleStatusMapper;
    }

    /**
     * Save a dqRuleStatus.
     *
     * @param dqRuleStatusDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DqRuleStatusDTO save(DqRuleStatusDTO dqRuleStatusDTO) {
        log.debug("Request to save DqRuleStatus : {}", dqRuleStatusDTO);
        DqRuleStatus dqRuleStatus = dqRuleStatusMapper.toEntity(dqRuleStatusDTO);
        dqRuleStatus = dqRuleStatusRepository.save(dqRuleStatus);
        return dqRuleStatusMapper.toDto(dqRuleStatus);
    }

    /**
     * Get all the dqRuleStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DqRuleStatusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DqRuleStatuses");
        return dqRuleStatusRepository.findAll(pageable)
            .map(dqRuleStatusMapper::toDto);
    }

    /**
     * Get one dqRuleStatus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DqRuleStatusDTO> findOne(Long id) {
        log.debug("Request to get DqRuleStatus : {}", id);
        return dqRuleStatusRepository.findById(id)
            .map(dqRuleStatusMapper::toDto);
    }

    /**
     * Delete the dqRuleStatus by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DqRuleStatus : {}", id);
        dqRuleStatusRepository.deleteById(id);
    }
}
