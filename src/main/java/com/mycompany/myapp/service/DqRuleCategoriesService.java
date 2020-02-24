package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DqRuleCategoriesDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.DqRuleCategories}.
 */
public interface DqRuleCategoriesService {

    /**
     * Save a dqRuleCategories.
     *
     * @param dqRuleCategoriesDTO the entity to save.
     * @return the persisted entity.
     */
    DqRuleCategoriesDTO save(DqRuleCategoriesDTO dqRuleCategoriesDTO);

    /**
     * Get all the dqRuleCategories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DqRuleCategoriesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" dqRuleCategories.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DqRuleCategoriesDTO> findOne(Long id);

    /**
     * Delete the "id" dqRuleCategories.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
