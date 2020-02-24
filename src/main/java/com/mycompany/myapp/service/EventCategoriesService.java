package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.EventCategoriesDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.EventCategories}.
 */
public interface EventCategoriesService {

    /**
     * Save a eventCategories.
     *
     * @param eventCategoriesDTO the entity to save.
     * @return the persisted entity.
     */
    EventCategoriesDTO save(EventCategoriesDTO eventCategoriesDTO);

    /**
     * Get all the eventCategories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EventCategoriesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" eventCategories.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EventCategoriesDTO> findOne(Long id);

    /**
     * Delete the "id" eventCategories.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
