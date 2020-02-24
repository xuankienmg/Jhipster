package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DataDefinitionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.DataDefinition}.
 */
public interface DataDefinitionService {

    /**
     * Save a dataDefinition.
     *
     * @param dataDefinitionDTO the entity to save.
     * @return the persisted entity.
     */
    DataDefinitionDTO save(DataDefinitionDTO dataDefinitionDTO);

    /**
     * Get all the dataDefinitions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DataDefinitionDTO> findAll(Pageable pageable);

    /**
     * Get the "id" dataDefinition.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DataDefinitionDTO> findOne(Long id);

    /**
     * Delete the "id" dataDefinition.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
