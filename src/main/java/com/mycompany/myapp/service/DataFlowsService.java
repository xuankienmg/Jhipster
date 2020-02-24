package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DataFlowsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.DataFlows}.
 */
public interface DataFlowsService {

    /**
     * Save a dataFlows.
     *
     * @param dataFlowsDTO the entity to save.
     * @return the persisted entity.
     */
    DataFlowsDTO save(DataFlowsDTO dataFlowsDTO);

    /**
     * Get all the dataFlows.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DataFlowsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" dataFlows.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DataFlowsDTO> findOne(Long id);

    /**
     * Delete the "id" dataFlows.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
