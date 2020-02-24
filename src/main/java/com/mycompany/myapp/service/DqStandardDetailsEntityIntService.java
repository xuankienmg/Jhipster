package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DqStandardDetailsEntityIntDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.DqStandardDetailsEntityInt}.
 */
public interface DqStandardDetailsEntityIntService {

    /**
     * Save a dqStandardDetailsEntityInt.
     *
     * @param dqStandardDetailsEntityIntDTO the entity to save.
     * @return the persisted entity.
     */
    DqStandardDetailsEntityIntDTO save(DqStandardDetailsEntityIntDTO dqStandardDetailsEntityIntDTO);

    /**
     * Get all the dqStandardDetailsEntityInts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DqStandardDetailsEntityIntDTO> findAll(Pageable pageable);

    /**
     * Get the "id" dqStandardDetailsEntityInt.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DqStandardDetailsEntityIntDTO> findOne(Long id);

    /**
     * Delete the "id" dqStandardDetailsEntityInt.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
