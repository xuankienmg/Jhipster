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

import com.mycompany.myapp.domain.DqStandardDetailsEntityDecimal;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.DqStandardDetailsEntityDecimalRepository;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityDecimalCriteria;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityDecimalDTO;
import com.mycompany.myapp.service.mapper.DqStandardDetailsEntityDecimalMapper;

/**
 * Service for executing complex queries for {@link DqStandardDetailsEntityDecimal} entities in the database.
 * The main input is a {@link DqStandardDetailsEntityDecimalCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DqStandardDetailsEntityDecimalDTO} or a {@link Page} of {@link DqStandardDetailsEntityDecimalDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DqStandardDetailsEntityDecimalQueryService extends QueryService<DqStandardDetailsEntityDecimal> {

    private final Logger log = LoggerFactory.getLogger(DqStandardDetailsEntityDecimalQueryService.class);

    private final DqStandardDetailsEntityDecimalRepository dqStandardDetailsEntityDecimalRepository;

    private final DqStandardDetailsEntityDecimalMapper dqStandardDetailsEntityDecimalMapper;

    public DqStandardDetailsEntityDecimalQueryService(DqStandardDetailsEntityDecimalRepository dqStandardDetailsEntityDecimalRepository, DqStandardDetailsEntityDecimalMapper dqStandardDetailsEntityDecimalMapper) {
        this.dqStandardDetailsEntityDecimalRepository = dqStandardDetailsEntityDecimalRepository;
        this.dqStandardDetailsEntityDecimalMapper = dqStandardDetailsEntityDecimalMapper;
    }

    /**
     * Return a {@link List} of {@link DqStandardDetailsEntityDecimalDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DqStandardDetailsEntityDecimalDTO> findByCriteria(DqStandardDetailsEntityDecimalCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DqStandardDetailsEntityDecimal> specification = createSpecification(criteria);
        return dqStandardDetailsEntityDecimalMapper.toDto(dqStandardDetailsEntityDecimalRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DqStandardDetailsEntityDecimalDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DqStandardDetailsEntityDecimalDTO> findByCriteria(DqStandardDetailsEntityDecimalCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DqStandardDetailsEntityDecimal> specification = createSpecification(criteria);
        return dqStandardDetailsEntityDecimalRepository.findAll(specification, page)
            .map(dqStandardDetailsEntityDecimalMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DqStandardDetailsEntityDecimalCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DqStandardDetailsEntityDecimal> specification = createSpecification(criteria);
        return dqStandardDetailsEntityDecimalRepository.count(specification);
    }

    /**
     * Function to convert {@link DqStandardDetailsEntityDecimalCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DqStandardDetailsEntityDecimal> createSpecification(DqStandardDetailsEntityDecimalCriteria criteria) {
        Specification<DqStandardDetailsEntityDecimal> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DqStandardDetailsEntityDecimal_.id));
            }
            if (criteria.getStdAttributeName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStdAttributeName(), DqStandardDetailsEntityDecimal_.stdAttributeName));
            }
            if (criteria.getStdAttributeValue() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStdAttributeValue(), DqStandardDetailsEntityDecimal_.stdAttributeValue));
            }
            if (criteria.getStdId() != null) {
                specification = specification.and(buildSpecification(criteria.getStdId(),
                    root -> root.join(DqStandardDetailsEntityDecimal_.std, JoinType.LEFT).get(DqStandards_.id)));
            }
        }
        return specification;
    }
}
