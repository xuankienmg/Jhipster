package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DsStoresDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.DsStores}.
 */
public interface DsStoresService {

    /**
     * Save a dsStores.
     *
     * @param dsStoresDTO the entity to save.
     * @return the persisted entity.
     */
    DsStoresDTO save(DsStoresDTO dsStoresDTO);

    /**
     * Get all the dsStores.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DsStoresDTO> findAll(Pageable pageable);

    /**
     * Get the "id" dsStores.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DsStoresDTO> findOne(Long id);

    /**
     * Delete the "id" dsStores.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
