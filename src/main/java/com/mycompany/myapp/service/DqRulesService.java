package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DqRulesDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.DqRules}.
 */
public interface DqRulesService {

    /**
     * Save a dqRules.
     *
     * @param dqRulesDTO the entity to save.
     * @return the persisted entity.
     */
    DqRulesDTO save(DqRulesDTO dqRulesDTO);

    /**
     * Get all the dqRules.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DqRulesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" dqRules.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DqRulesDTO> findOne(Long id);

    /**
     * Delete the "id" dqRules.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
