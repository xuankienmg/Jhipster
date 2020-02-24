package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DataMappingDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.DataMapping}.
 */
public interface DataMappingService {

    /**
     * Save a dataMapping.
     *
     * @param dataMappingDTO the entity to save.
     * @return the persisted entity.
     */
    DataMappingDTO save(DataMappingDTO dataMappingDTO);

    /**
     * Get all the dataMappings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DataMappingDTO> findAll(Pageable pageable);

    /**
     * Get the "id" dataMapping.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DataMappingDTO> findOne(Long id);

    /**
     * Delete the "id" dataMapping.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
