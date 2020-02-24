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

import com.mycompany.myapp.domain.EventLogs;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.EventLogsRepository;
import com.mycompany.myapp.service.dto.EventLogsCriteria;
import com.mycompany.myapp.service.dto.EventLogsDTO;
import com.mycompany.myapp.service.mapper.EventLogsMapper;

/**
 * Service for executing complex queries for {@link EventLogs} entities in the database.
 * The main input is a {@link EventLogsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EventLogsDTO} or a {@link Page} of {@link EventLogsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EventLogsQueryService extends QueryService<EventLogs> {

    private final Logger log = LoggerFactory.getLogger(EventLogsQueryService.class);

    private final EventLogsRepository eventLogsRepository;

    private final EventLogsMapper eventLogsMapper;

    public EventLogsQueryService(EventLogsRepository eventLogsRepository, EventLogsMapper eventLogsMapper) {
        this.eventLogsRepository = eventLogsRepository;
        this.eventLogsMapper = eventLogsMapper;
    }

    /**
     * Return a {@link List} of {@link EventLogsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EventLogsDTO> findByCriteria(EventLogsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EventLogs> specification = createSpecification(criteria);
        return eventLogsMapper.toDto(eventLogsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EventLogsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EventLogsDTO> findByCriteria(EventLogsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EventLogs> specification = createSpecification(criteria);
        return eventLogsRepository.findAll(specification, page)
            .map(eventLogsMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EventLogsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EventLogs> specification = createSpecification(criteria);
        return eventLogsRepository.count(specification);
    }

    /**
     * Function to convert {@link EventLogsCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<EventLogs> createSpecification(EventLogsCriteria criteria) {
        Specification<EventLogs> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), EventLogs_.id));
            }
            if (criteria.getRows() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRows(), EventLogs_.rows));
            }
            if (criteria.getEventNote() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEventNote(), EventLogs_.eventNote));
            }
            if (criteria.getEventTimestamp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEventTimestamp(), EventLogs_.eventTimestamp));
            }
            if (criteria.getEventTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getEventTypeId(),
                    root -> root.join(EventLogs_.eventType, JoinType.LEFT).get(EventTypes_.id)));
            }
            if (criteria.getEventCatId() != null) {
                specification = specification.and(buildSpecification(criteria.getEventCatId(),
                    root -> root.join(EventLogs_.eventCat, JoinType.LEFT).get(EventCategories_.id)));
            }
            if (criteria.getFlowId() != null) {
                specification = specification.and(buildSpecification(criteria.getFlowId(),
                    root -> root.join(EventLogs_.flow, JoinType.LEFT).get(DataFlows_.id)));
            }
            if (criteria.getTblId() != null) {
                specification = specification.and(buildSpecification(criteria.getTblId(),
                    root -> root.join(EventLogs_.tbl, JoinType.LEFT).get(DsTables_.id)));
            }
        }
        return specification;
    }
}
