package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DqRuleTypesDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.DqRuleTypes}.
 */
public interface DqRuleTypesService {

    /**
     * Save a dqRuleTypes.
     *
     * @param dqRuleTypesDTO the entity to save.
     * @return the persisted entity.
     */
    DqRuleTypesDTO save(DqRuleTypesDTO dqRuleTypesDTO);

    /**
     * Get all the dqRuleTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DqRuleTypesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" dqRuleTypes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DqRuleTypesDTO> findOne(Long id);

    /**
     * Delete the "id" dqRuleTypes.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
