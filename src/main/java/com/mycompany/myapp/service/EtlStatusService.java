package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.EtlStatusDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.EtlStatus}.
 */
public interface EtlStatusService {

    /**
     * Save a etlStatus.
     *
     * @param etlStatusDTO the entity to save.
     * @return the persisted entity.
     */
    EtlStatusDTO save(EtlStatusDTO etlStatusDTO);

    /**
     * Get all the etlStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EtlStatusDTO> findAll(Pageable pageable);

    /**
     * Get the "id" etlStatus.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EtlStatusDTO> findOne(Long id);

    /**
     * Delete the "id" etlStatus.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
