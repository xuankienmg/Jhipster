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

import com.mycompany.myapp.domain.DqStandardDetailsEntityDatetime;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.DqStandardDetailsEntityDatetimeRepository;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityDatetimeCriteria;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityDatetimeDTO;
import com.mycompany.myapp.service.mapper.DqStandardDetailsEntityDatetimeMapper;

/**
 * Service for executing complex queries for {@link DqStandardDetailsEntityDatetime} entities in the database.
 * The main input is a {@link DqStandardDetailsEntityDatetimeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DqStandardDetailsEntityDatetimeDTO} or a {@link Page} of {@link DqStandardDetailsEntityDatetimeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DqStandardDetailsEntityDatetimeQueryService extends QueryService<DqStandardDetailsEntityDatetime> {

    private final Logger log = LoggerFactory.getLogger(DqStandardDetailsEntityDatetimeQueryService.class);

    private final DqStandardDetailsEntityDatetimeRepository dqStandardDetailsEntityDatetimeRepository;

    private final DqStandardDetailsEntityDatetimeMapper dqStandardDetailsEntityDatetimeMapper;

    public DqStandardDetailsEntityDatetimeQueryService(DqStandardDetailsEntityDatetimeRepository dqStandardDetailsEntityDatetimeRepository, DqStandardDetailsEntityDatetimeMapper dqStandardDetailsEntityDatetimeMapper) {
        this.dqStandardDetailsEntityDatetimeRepository = dqStandardDetailsEntityDatetimeRepository;
        this.dqStandardDetailsEntityDatetimeMapper = dqStandardDetailsEntityDatetimeMapper;
    }

    /**
     * Return a {@link List} of {@link DqStandardDetailsEntityDatetimeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DqStandardDetailsEntityDatetimeDTO> findByCriteria(DqStandardDetailsEntityDatetimeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DqStandardDetailsEntityDatetime> specification = createSpecification(criteria);
        return dqStandardDetailsEntityDatetimeMapper.toDto(dqStandardDetailsEntityDatetimeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DqStandardDetailsEntityDatetimeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DqStandardDetailsEntityDatetimeDTO> findByCriteria(DqStandardDetailsEntityDatetimeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DqStandardDetailsEntityDatetime> specification = createSpecification(criteria);
        return dqStandardDetailsEntityDatetimeRepository.findAll(specification, page)
            .map(dqStandardDetailsEntityDatetimeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DqStandardDetailsEntityDatetimeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DqStandardDetailsEntityDatetime> specification = createSpecification(criteria);
        return dqStandardDetailsEntityDatetimeRepository.count(specification);
    }

    /**
     * Function to convert {@link DqStandardDetailsEntityDatetimeCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DqStandardDetailsEntityDatetime> createSpecification(DqStandardDetailsEntityDatetimeCriteria criteria) {
        Specification<DqStandardDetailsEntityDatetime> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DqStandardDetailsEntityDatetime_.id));
            }
            if (criteria.getStdAttributeName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStdAttributeName(), DqStandardDetailsEntityDatetime_.stdAttributeName));
            }
            if (criteria.getStdAttributeValue() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStdAttributeValue(), DqStandardDetailsEntityDatetime_.stdAttributeValue));
            }
            if (criteria.getStdId() != null) {
                specification = specification.and(buildSpecification(criteria.getStdId(),
                    root -> root.join(DqStandardDetailsEntityDatetime_.std, JoinType.LEFT).get(DqStandards_.id)));
            }
        }
        return specification;
    }
}
