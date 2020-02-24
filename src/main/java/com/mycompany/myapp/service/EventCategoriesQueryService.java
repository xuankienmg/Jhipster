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

import com.mycompany.myapp.domain.EventCategories;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.EventCategoriesRepository;
import com.mycompany.myapp.service.dto.EventCategoriesCriteria;
import com.mycompany.myapp.service.dto.EventCategoriesDTO;
import com.mycompany.myapp.service.mapper.EventCategoriesMapper;

/**
 * Service for executing complex queries for {@link EventCategories} entities in the database.
 * The main input is a {@link EventCategoriesCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EventCategoriesDTO} or a {@link Page} of {@link EventCategoriesDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EventCategoriesQueryService extends QueryService<EventCategories> {

    private final Logger log = LoggerFactory.getLogger(EventCategoriesQueryService.class);

    private final EventCategoriesRepository eventCategoriesRepository;

    private final EventCategoriesMapper eventCategoriesMapper;

    public EventCategoriesQueryService(EventCategoriesRepository eventCategoriesRepository, EventCategoriesMapper eventCategoriesMapper) {
        this.eventCategoriesRepository = eventCategoriesRepository;
        this.eventCategoriesMapper = eventCategoriesMapper;
    }

    /**
     * Return a {@link List} of {@link EventCategoriesDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EventCategoriesDTO> findByCriteria(EventCategoriesCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EventCategories> specification = createSpecification(criteria);
        return eventCategoriesMapper.toDto(eventCategoriesRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EventCategoriesDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EventCategoriesDTO> findByCriteria(EventCategoriesCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EventCategories> specification = createSpecification(criteria);
        return eventCategoriesRepository.findAll(specification, page)
            .map(eventCategoriesMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EventCategoriesCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EventCategories> specification = createSpecification(criteria);
        return eventCategoriesRepository.count(specification);
    }

    /**
     * Function to convert {@link EventCategoriesCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<EventCategories> createSpecification(EventCategoriesCriteria criteria) {
        Specification<EventCategories> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), EventCategories_.id));
            }
            if (criteria.getEventCatName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEventCatName(), EventCategories_.eventCatName));
            }
        }
        return specification;
    }
}
