package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.EventLogsService;
import com.mycompany.myapp.domain.EventLogs;
import com.mycompany.myapp.repository.EventLogsRepository;
import com.mycompany.myapp.service.dto.EventLogsDTO;
import com.mycompany.myapp.service.mapper.EventLogsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EventLogs}.
 */
@Service
@Transactional
public class EventLogsServiceImpl implements EventLogsService {

    private final Logger log = LoggerFactory.getLogger(EventLogsServiceImpl.class);

    private final EventLogsRepository eventLogsRepository;

    private final EventLogsMapper eventLogsMapper;

    public EventLogsServiceImpl(EventLogsRepository eventLogsRepository, EventLogsMapper eventLogsMapper) {
        this.eventLogsRepository = eventLogsRepository;
        this.eventLogsMapper = eventLogsMapper;
    }

    /**
     * Save a eventLogs.
     *
     * @param eventLogsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EventLogsDTO save(EventLogsDTO eventLogsDTO) {
        log.debug("Request to save EventLogs : {}", eventLogsDTO);
        EventLogs eventLogs = eventLogsMapper.toEntity(eventLogsDTO);
        eventLogs = eventLogsRepository.save(eventLogs);
        return eventLogsMapper.toDto(eventLogs);
    }

    /**
     * Get all the eventLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EventLogsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EventLogs");
        return eventLogsRepository.findAll(pageable)
            .map(eventLogsMapper::toDto);
    }

    /**
     * Get one eventLogs by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EventLogsDTO> findOne(Long id) {
        log.debug("Request to get EventLogs : {}", id);
        return eventLogsRepository.findById(id)
            .map(eventLogsMapper::toDto);
    }

    /**
     * Delete the eventLogs by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EventLogs : {}", id);
        eventLogsRepository.deleteById(id);
    }
}
