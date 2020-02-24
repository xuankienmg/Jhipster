package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.EventLogsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.EventLogs}.
 */
public interface EventLogsService {

    /**
     * Save a eventLogs.
     *
     * @param eventLogsDTO the entity to save.
     * @return the persisted entity.
     */
    EventLogsDTO save(EventLogsDTO eventLogsDTO);

    /**
     * Get all the eventLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EventLogsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" eventLogs.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EventLogsDTO> findOne(Long id);

    /**
     * Delete the "id" eventLogs.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
