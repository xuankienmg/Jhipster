package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DqStandardDetailsEntityVarcharDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.DqStandardDetailsEntityVarchar}.
 */
public interface DqStandardDetailsEntityVarcharService {

    /**
     * Save a dqStandardDetailsEntityVarchar.
     *
     * @param dqStandardDetailsEntityVarcharDTO the entity to save.
     * @return the persisted entity.
     */
    DqStandardDetailsEntityVarcharDTO save(DqStandardDetailsEntityVarcharDTO dqStandardDetailsEntityVarcharDTO);

    /**
     * Get all the dqStandardDetailsEntityVarchars.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DqStandardDetailsEntityVarcharDTO> findAll(Pageable pageable);

    /**
     * Get the "id" dqStandardDetailsEntityVarchar.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DqStandardDetailsEntityVarcharDTO> findOne(Long id);

    /**
     * Delete the "id" dqStandardDetailsEntityVarchar.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
