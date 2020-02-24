package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DsTablesDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.DsTables}.
 */
public interface DsTablesService {

    /**
     * Save a dsTables.
     *
     * @param dsTablesDTO the entity to save.
     * @return the persisted entity.
     */
    DsTablesDTO save(DsTablesDTO dsTablesDTO);

    /**
     * Get all the dsTables.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DsTablesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" dsTables.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DsTablesDTO> findOne(Long id);

    /**
     * Delete the "id" dsTables.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
