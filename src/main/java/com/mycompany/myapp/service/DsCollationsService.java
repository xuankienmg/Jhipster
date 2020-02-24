package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DsCollationsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.DsCollations}.
 */
public interface DsCollationsService {

    /**
     * Save a dsCollations.
     *
     * @param dsCollationsDTO the entity to save.
     * @return the persisted entity.
     */
    DsCollationsDTO save(DsCollationsDTO dsCollationsDTO);

    /**
     * Get all the dsCollations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DsCollationsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" dsCollations.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DsCollationsDTO> findOne(Long id);

    /**
     * Delete the "id" dsCollations.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
