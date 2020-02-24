package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DqStandardDetailsEntityDecimalDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.DqStandardDetailsEntityDecimal}.
 */
public interface DqStandardDetailsEntityDecimalService {

    /**
     * Save a dqStandardDetailsEntityDecimal.
     *
     * @param dqStandardDetailsEntityDecimalDTO the entity to save.
     * @return the persisted entity.
     */
    DqStandardDetailsEntityDecimalDTO save(DqStandardDetailsEntityDecimalDTO dqStandardDetailsEntityDecimalDTO);

    /**
     * Get all the dqStandardDetailsEntityDecimals.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DqStandardDetailsEntityDecimalDTO> findAll(Pageable pageable);

    /**
     * Get the "id" dqStandardDetailsEntityDecimal.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DqStandardDetailsEntityDecimalDTO> findOne(Long id);

    /**
     * Delete the "id" dqStandardDetailsEntityDecimal.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
