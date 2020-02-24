package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DqStandardsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.DqStandards}.
 */
public interface DqStandardsService {

    /**
     * Save a dqStandards.
     *
     * @param dqStandardsDTO the entity to save.
     * @return the persisted entity.
     */
    DqStandardsDTO save(DqStandardsDTO dqStandardsDTO);

    /**
     * Get all the dqStandards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DqStandardsDTO> findAll(Pageable pageable);

    /**
     * Get all the dqStandards with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<DqStandardsDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" dqStandards.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DqStandardsDTO> findOne(Long id);

    /**
     * Delete the "id" dqStandards.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
