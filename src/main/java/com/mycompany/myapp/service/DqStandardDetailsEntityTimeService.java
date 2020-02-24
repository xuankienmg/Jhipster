package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DqStandardDetailsEntityTimeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.DqStandardDetailsEntityTime}.
 */
public interface DqStandardDetailsEntityTimeService {

    /**
     * Save a dqStandardDetailsEntityTime.
     *
     * @param dqStandardDetailsEntityTimeDTO the entity to save.
     * @return the persisted entity.
     */
    DqStandardDetailsEntityTimeDTO save(DqStandardDetailsEntityTimeDTO dqStandardDetailsEntityTimeDTO);

    /**
     * Get all the dqStandardDetailsEntityTimes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DqStandardDetailsEntityTimeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" dqStandardDetailsEntityTime.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DqStandardDetailsEntityTimeDTO> findOne(Long id);

    /**
     * Delete the "id" dqStandardDetailsEntityTime.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
