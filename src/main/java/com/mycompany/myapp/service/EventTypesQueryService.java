package com.mycompany.myapp.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.mycompany.myapp.domain.EventTypes;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.EventTypesRepository;
import com.mycompany.myapp.service.dto.EventTypesCriteria;
import com.mycompany.myapp.service.dto.EventTypesDTO;
import com.mycompany.myapp.service.mapper.EventTypesMapper;

/**
 * Service for executing complex queries for {@link EventTypes} entities in the database.
 * The main input is a {@link EventTypesCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EventTypesDTO} or a {@link Page} of {@link EventTypesDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EventTypesQueryService extends QueryService<EventTypes> {

    private final Logger log = LoggerFactory.getLogger(EventTypesQueryService.class);

    private final EventTypesRepository eventTypesRepository;

    private final EventTypesMapper eventTypesMapper;

    public EventTypesQueryService(EventTypesRepository eventTypesRepository, EventTypesMapper eventTypesMapper) {
        this.eventTypesRepository = eventTypesRepository;
        this.eventTypesMapper = eventTypesMapper;
    }

    /**
     * Return a {@link List} of {@link EventTypesDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EventTypesDTO> findByCriteria(EventTypesCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EventTypes> specification = createSpecification(criteria);
        return eventTypesMapper.toDto(eventTypesRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EventTypesDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EventTypesDTO> findByCriteria(EventTypesCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EventTypes> specification = createSpecification(criteria);
        return eventTypesRepository.findAll(specification, page)
            .map(eventTypesMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EventTypesCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EventTypes> specification = createSpecification(criteria);
        return eventTypesRepository.count(specification);
    }

    /**
     * Function to convert {@link EventTypesCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<EventTypes> createSpecification(EventTypesCriteria criteria) {
        Specification<EventTypes> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), EventTypes_.id));
            }
            if (criteria.getEventTypeName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEventTypeName(), EventTypes_.eventTypeName));
            }
        }
        return specification;
    }
}
