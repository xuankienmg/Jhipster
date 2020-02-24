package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DqRuleStatusDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.DqRuleStatus}.
 */
public interface DqRuleStatusService {

    /**
     * Save a dqRuleStatus.
     *
     * @param dqRuleStatusDTO the entity to save.
     * @return the persisted entity.
     */
    DqRuleStatusDTO save(DqRuleStatusDTO dqRuleStatusDTO);

    /**
     * Get all the dqRuleStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DqRuleStatusDTO> findAll(Pageable pageable);

    /**
     * Get the "id" dqRuleStatus.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DqRuleStatusDTO> findOne(Long id);

    /**
     * Delete the "id" dqRuleStatus.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
