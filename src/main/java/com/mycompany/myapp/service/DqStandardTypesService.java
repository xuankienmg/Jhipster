package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DqStandardTypesDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.DqStandardTypes}.
 */
public interface DqStandardTypesService {

    /**
     * Save a dqStandardTypes.
     *
     * @param dqStandardTypesDTO the entity to save.
     * @return the persisted entity.
     */
    DqStandardTypesDTO save(DqStandardTypesDTO dqStandardTypesDTO);

    /**
     * Get all the dqStandardTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DqStandardTypesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" dqStandardTypes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DqStandardTypesDTO> findOne(Long id);

    /**
     * Delete the "id" dqStandardTypes.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
