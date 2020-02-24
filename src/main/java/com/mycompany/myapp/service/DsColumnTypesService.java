package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DsColumnTypesDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.DsColumnTypes}.
 */
public interface DsColumnTypesService {

    /**
     * Save a dsColumnTypes.
     *
     * @param dsColumnTypesDTO the entity to save.
     * @return the persisted entity.
     */
    DsColumnTypesDTO save(DsColumnTypesDTO dsColumnTypesDTO);

    /**
     * Get all the dsColumnTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DsColumnTypesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" dsColumnTypes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DsColumnTypesDTO> findOne(Long id);

    /**
     * Delete the "id" dsColumnTypes.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
