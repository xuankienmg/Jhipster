package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DpSourceColumnsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.DpSourceColumns}.
 */
public interface DpSourceColumnsService {

    /**
     * Save a dpSourceColumns.
     *
     * @param dpSourceColumnsDTO the entity to save.
     * @return the persisted entity.
     */
    DpSourceColumnsDTO save(DpSourceColumnsDTO dpSourceColumnsDTO);

    /**
     * Get all the dpSourceColumns.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DpSourceColumnsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" dpSourceColumns.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DpSourceColumnsDTO> findOne(Long id);

    /**
     * Delete the "id" dpSourceColumns.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
