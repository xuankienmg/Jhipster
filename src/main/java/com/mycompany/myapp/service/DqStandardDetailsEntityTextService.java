package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DqStandardDetailsEntityTextDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.DqStandardDetailsEntityText}.
 */
public interface DqStandardDetailsEntityTextService {

    /**
     * Save a dqStandardDetailsEntityText.
     *
     * @param dqStandardDetailsEntityTextDTO the entity to save.
     * @return the persisted entity.
     */
    DqStandardDetailsEntityTextDTO save(DqStandardDetailsEntityTextDTO dqStandardDetailsEntityTextDTO);

    /**
     * Get all the dqStandardDetailsEntityTexts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DqStandardDetailsEntityTextDTO> findAll(Pageable pageable);

    /**
     * Get the "id" dqStandardDetailsEntityText.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DqStandardDetailsEntityTextDTO> findOne(Long id);

    /**
     * Delete the "id" dqStandardDetailsEntityText.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
