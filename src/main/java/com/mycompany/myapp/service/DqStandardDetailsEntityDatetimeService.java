package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DqStandardDetailsEntityDatetimeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.DqStandardDetailsEntityDatetime}.
 */
public interface DqStandardDetailsEntityDatetimeService {

    /**
     * Save a dqStandardDetailsEntityDatetime.
     *
     * @param dqStandardDetailsEntityDatetimeDTO the entity to save.
     * @return the persisted entity.
     */
    DqStandardDetailsEntityDatetimeDTO save(DqStandardDetailsEntityDatetimeDTO dqStandardDetailsEntityDatetimeDTO);

    /**
     * Get all the dqStandardDetailsEntityDatetimes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DqStandardDetailsEntityDatetimeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" dqStandardDetailsEntityDatetime.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DqStandardDetailsEntityDatetimeDTO> findOne(Long id);

    /**
     * Delete the "id" dqStandardDetailsEntityDatetime.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
