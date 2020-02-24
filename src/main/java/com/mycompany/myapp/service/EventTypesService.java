package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.EventTypesDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.EventTypes}.
 */
public interface EventTypesService {

    /**
     * Save a eventTypes.
     *
     * @param eventTypesDTO the entity to save.
     * @return the persisted entity.
     */
    EventTypesDTO save(EventTypesDTO eventTypesDTO);

    /**
     * Get all the eventTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EventTypesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" eventTypes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EventTypesDTO> findOne(Long id);

    /**
     * Delete the "id" eventTypes.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
