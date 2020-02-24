package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DsTableTypesDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.DsTableTypes}.
 */
public interface DsTableTypesService {

    /**
     * Save a dsTableTypes.
     *
     * @param dsTableTypesDTO the entity to save.
     * @return the persisted entity.
     */
    DsTableTypesDTO save(DsTableTypesDTO dsTableTypesDTO);

    /**
     * Get all the dsTableTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DsTableTypesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" dsTableTypes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DsTableTypesDTO> findOne(Long id);

    /**
     * Delete the "id" dsTableTypes.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
