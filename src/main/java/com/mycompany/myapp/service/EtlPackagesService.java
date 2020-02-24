package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.EtlPackagesDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.EtlPackages}.
 */
public interface EtlPackagesService {

    /**
     * Save a etlPackages.
     *
     * @param etlPackagesDTO the entity to save.
     * @return the persisted entity.
     */
    EtlPackagesDTO save(EtlPackagesDTO etlPackagesDTO);

    /**
     * Get all the etlPackages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EtlPackagesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" etlPackages.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EtlPackagesDTO> findOne(Long id);

    /**
     * Delete the "id" etlPackages.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
