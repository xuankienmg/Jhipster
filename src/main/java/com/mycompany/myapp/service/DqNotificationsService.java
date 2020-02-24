package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DqNotificationsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.DqNotifications}.
 */
public interface DqNotificationsService {

    /**
     * Save a dqNotifications.
     *
     * @param dqNotificationsDTO the entity to save.
     * @return the persisted entity.
     */
    DqNotificationsDTO save(DqNotificationsDTO dqNotificationsDTO);

    /**
     * Get all the dqNotifications.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DqNotificationsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" dqNotifications.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DqNotificationsDTO> findOne(Long id);

    /**
     * Delete the "id" dqNotifications.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
