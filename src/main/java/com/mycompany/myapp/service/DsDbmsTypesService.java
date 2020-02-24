package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DsDbmsTypesDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.DsDbmsTypes}.
 */
public interface DsDbmsTypesService {

    /**
     * Save a dsDbmsTypes.
     *
     * @param dsDbmsTypesDTO the entity to save.
     * @return the persisted entity.
     */
    DsDbmsTypesDTO save(DsDbmsTypesDTO dsDbmsTypesDTO);

    /**
     * Get all the dsDbmsTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DsDbmsTypesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" dsDbmsTypes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DsDbmsTypesDTO> findOne(Long id);

    /**
     * Delete the "id" dsDbmsTypes.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
