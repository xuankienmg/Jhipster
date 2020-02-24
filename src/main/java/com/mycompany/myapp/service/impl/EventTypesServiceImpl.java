package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.EventTypesService;
import com.mycompany.myapp.domain.EventTypes;
import com.mycompany.myapp.repository.EventTypesRepository;
import com.mycompany.myapp.service.dto.EventTypesDTO;
import com.mycompany.myapp.service.mapper.EventTypesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EventTypes}.
 */
@Service
@Transactional
public class EventTypesServiceImpl implements EventTypesService {

    private final Logger log = LoggerFactory.getLogger(EventTypesServiceImpl.class);

    private final EventTypesRepository eventTypesRepository;

    private final EventTypesMapper eventTypesMapper;

    public EventTypesServiceImpl(EventTypesRepository eventTypesRepository, EventTypesMapper eventTypesMapper) {
        this.eventTypesRepository = eventTypesRepository;
        this.eventTypesMapper = eventTypesMapper;
    }

    /**
     * Save a eventTypes.
     *
     * @param eventTypesDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EventTypesDTO save(EventTypesDTO eventTypesDTO) {
        log.debug("Request to save EventTypes : {}", eventTypesDTO);
        EventTypes eventTypes = eventTypesMapper.toEntity(eventTypesDTO);
        eventTypes = eventTypesRepository.save(eventTypes);
        return eventTypesMapper.toDto(eventTypes);
    }

    /**
     * Get all the eventTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EventTypesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EventTypes");
        return eventTypesRepository.findAll(pageable)
            .map(eventTypesMapper::toDto);
    }

    /**
     * Get one eventTypes by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EventTypesDTO> findOne(Long id) {
        log.debug("Request to get EventTypes : {}", id);
        return eventTypesRepository.findById(id)
            .map(eventTypesMapper::toDto);
    }

    /**
     * Delete the eventTypes by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EventTypes : {}", id);
        eventTypesRepository.deleteById(id);
    }
}
