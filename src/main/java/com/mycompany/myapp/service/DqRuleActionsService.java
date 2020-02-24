package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DqRuleActionsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.DqRuleActions}.
 */
public interface DqRuleActionsService {

    /**
     * Save a dqRuleActions.
     *
     * @param dqRuleActionsDTO the entity to save.
     * @return the persisted entity.
     */
    DqRuleActionsDTO save(DqRuleActionsDTO dqRuleActionsDTO);

    /**
     * Get all the dqRuleActions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DqRuleActionsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" dqRuleActions.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DqRuleActionsDTO> findOne(Long id);

    /**
     * Delete the "id" dqRuleActions.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
