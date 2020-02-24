package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.EventCategoriesService;
import com.mycompany.myapp.domain.EventCategories;
import com.mycompany.myapp.repository.EventCategoriesRepository;
import com.mycompany.myapp.service.dto.EventCategoriesDTO;
import com.mycompany.myapp.service.mapper.EventCategoriesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EventCategories}.
 */
@Service
@Transactional
public class EventCategoriesServiceImpl implements EventCategoriesService {

    private final Logger log = LoggerFactory.getLogger(EventCategoriesServiceImpl.class);

    private final EventCategoriesRepository eventCategoriesRepository;

    private final EventCategoriesMapper eventCategoriesMapper;

    public EventCategoriesServiceImpl(EventCategoriesRepository eventCategoriesRepository, EventCategoriesMapper eventCategoriesMapper) {
        this.eventCategoriesRepository = eventCategoriesRepository;
        this.eventCategoriesMapper = eventCategoriesMapper;
    }

    /**
     * Save a eventCategories.
     *
     * @param eventCategoriesDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EventCategoriesDTO save(EventCategoriesDTO eventCategoriesDTO) {
        log.debug("Request to save EventCategories : {}", eventCategoriesDTO);
        EventCategories eventCategories = eventCategoriesMapper.toEntity(eventCategoriesDTO);
        eventCategories = eventCategoriesRepository.save(eventCategories);
        return eventCategoriesMapper.toDto(eventCategories);
    }

    /**
     * Get all the eventCategories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EventCategoriesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EventCategories");
        return eventCategoriesRepository.findAll(pageable)
            .map(eventCategoriesMapper::toDto);
    }

    /**
     * Get one eventCategories by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EventCategoriesDTO> findOne(Long id) {
        log.debug("Request to get EventCategories : {}", id);
        return eventCategoriesRepository.findById(id)
            .map(eventCategoriesMapper::toDto);
    }

    /**
     * Delete the eventCategories by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EventCategories : {}", id);
        eventCategoriesRepository.deleteById(id);
    }
}
