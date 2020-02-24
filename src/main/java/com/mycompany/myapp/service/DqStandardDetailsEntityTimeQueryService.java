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

import com.mycompany.myapp.domain.DqStandardDetailsEntityTime;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.DqStandardDetailsEntityTimeRepository;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityTimeCriteria;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityTimeDTO;
import com.mycompany.myapp.service.mapper.DqStandardDetailsEntityTimeMapper;

/**
 * Service for executing complex queries for {@link DqStandardDetailsEntityTime} entities in the database.
 * The main input is a {@link DqStandardDetailsEntityTimeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DqStandardDetailsEntityTimeDTO} or a {@link Page} of {@link DqStandardDetailsEntityTimeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DqStandardDetailsEntityTimeQueryService extends QueryService<DqStandardDetailsEntityTime> {

    private final Logger log = LoggerFactory.getLogger(DqStandardDetailsEntityTimeQueryService.class);

    private final DqStandardDetailsEntityTimeRepository dqStandardDetailsEntityTimeRepository;

    private final DqStandardDetailsEntityTimeMapper dqStandardDetailsEntityTimeMapper;

    public DqStandardDetailsEntityTimeQueryService(DqStandardDetailsEntityTimeRepository dqStandardDetailsEntityTimeRepository, DqStandardDetailsEntityTimeMapper dqStandardDetailsEntityTimeMapper) {
        this.dqStandardDetailsEntityTimeRepository = dqStandardDetailsEntityTimeRepository;
        this.dqStandardDetailsEntityTimeMapper = dqStandardDetailsEntityTimeMapper;
    }

    /**
     * Return a {@link List} of {@link DqStandardDetailsEntityTimeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DqStandardDetailsEntityTimeDTO> findByCriteria(DqStandardDetailsEntityTimeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DqStandardDetailsEntityTime> specification = createSpecification(criteria);
        return dqStandardDetailsEntityTimeMapper.toDto(dqStandardDetailsEntityTimeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DqStandardDetailsEntityTimeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DqStandardDetailsEntityTimeDTO> findByCriteria(DqStandardDetailsEntityTimeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DqStandardDetailsEntityTime> specification = createSpecification(criteria);
        return dqStandardDetailsEntityTimeRepository.findAll(specification, page)
            .map(dqStandardDetailsEntityTimeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DqStandardDetailsEntityTimeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DqStandardDetailsEntityTime> specification = createSpecification(criteria);
        return dqStandardDetailsEntityTimeRepository.count(specification);
    }

    /**
     * Function to convert {@link DqStandardDetailsEntityTimeCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DqStandardDetailsEntityTime> createSpecification(DqStandardDetailsEntityTimeCriteria criteria) {
        Specification<DqStandardDetailsEntityTime> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DqStandardDetailsEntityTime_.id));
            }
            if (criteria.getStdAttributeName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStdAttributeName(), DqStandardDetailsEntityTime_.stdAttributeName));
            }
            if (criteria.getStdAttributeValue() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStdAttributeValue(), DqStandardDetailsEntityTime_.stdAttributeValue));
            }
            if (criteria.getStdId() != null) {
                specification = specification.and(buildSpecification(criteria.getStdId(),
                    root -> root.join(DqStandardDetailsEntityTime_.std, JoinType.LEFT).get(DqStandards_.id)));
            }
        }
        return specification;
    }
}
