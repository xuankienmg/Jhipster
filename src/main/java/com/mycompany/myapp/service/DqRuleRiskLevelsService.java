package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DqRuleRiskLevelsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.DqRuleRiskLevels}.
 */
public interface DqRuleRiskLevelsService {

    /**
     * Save a dqRuleRiskLevels.
     *
     * @param dqRuleRiskLevelsDTO the entity to save.
     * @return the persisted entity.
     */
    DqRuleRiskLevelsDTO save(DqRuleRiskLevelsDTO dqRuleRiskLevelsDTO);

    /**
     * Get all the dqRuleRiskLevels.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DqRuleRiskLevelsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" dqRuleRiskLevels.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DqRuleRiskLevelsDTO> findOne(Long id);

    /**
     * Delete the "id" dqRuleRiskLevels.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
