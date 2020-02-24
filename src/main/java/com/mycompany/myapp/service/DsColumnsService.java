package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DsColumnsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.DsColumns}.
 */
public interface DsColumnsService {

    /**
     * Save a dsColumns.
     *
     * @param dsColumnsDTO the entity to save.
     * @return the persisted entity.
     */
    DsColumnsDTO save(DsColumnsDTO dsColumnsDTO);

    /**
     * Get all the dsColumns.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DsColumnsDTO> findAll(Pageable pageable);

    /**
     * Get all the dsColumns with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<DsColumnsDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" dsColumns.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DsColumnsDTO> findOne(Long id);

    /**
     * Delete the "id" dsColumns.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
