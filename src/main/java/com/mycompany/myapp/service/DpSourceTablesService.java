package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DpSourceTablesDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.DpSourceTables}.
 */
public interface DpSourceTablesService {

    /**
     * Save a dpSourceTables.
     *
     * @param dpSourceTablesDTO the entity to save.
     * @return the persisted entity.
     */
    DpSourceTablesDTO save(DpSourceTablesDTO dpSourceTablesDTO);

    /**
     * Get all the dpSourceTables.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DpSourceTablesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" dpSourceTables.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DpSourceTablesDTO> findOne(Long id);

    /**
     * Delete the "id" dpSourceTables.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
