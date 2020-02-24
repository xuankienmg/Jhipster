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

import com.mycompany.myapp.domain.DqStandardDetailsEntityInt;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.DqStandardDetailsEntityIntRepository;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityIntCriteria;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityIntDTO;
import com.mycompany.myapp.service.mapper.DqStandardDetailsEntityIntMapper;

/**
 * Service for executing complex queries for {@link DqStandardDetailsEntityInt} entities in the database.
 * The main input is a {@link DqStandardDetailsEntityIntCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DqStandardDetailsEntityIntDTO} or a {@link Page} of {@link DqStandardDetailsEntityIntDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DqStandardDetailsEntityIntQueryService extends QueryService<DqStandardDetailsEntityInt> {

    private final Logger log = LoggerFactory.getLogger(DqStandardDetailsEntityIntQueryService.class);

    private final DqStandardDetailsEntityIntRepository dqStandardDetailsEntityIntRepository;

    private final DqStandardDetailsEntityIntMapper dqStandardDetailsEntityIntMapper;

    public DqStandardDetailsEntityIntQueryService(DqStandardDetailsEntityIntRepository dqStandardDetailsEntityIntRepository, DqStandardDetailsEntityIntMapper dqStandardDetailsEntityIntMapper) {
        this.dqStandardDetailsEntityIntRepository = dqStandardDetailsEntityIntRepository;
        this.dqStandardDetailsEntityIntMapper = dqStandardDetailsEntityIntMapper;
    }

    /**
     * Return a {@link List} of {@link DqStandardDetailsEntityIntDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DqStandardDetailsEntityIntDTO> findByCriteria(DqStandardDetailsEntityIntCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DqStandardDetailsEntityInt> specification = createSpecification(criteria);
        return dqStandardDetailsEntityIntMapper.toDto(dqStandardDetailsEntityIntRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DqStandardDetailsEntityIntDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DqStandardDetailsEntityIntDTO> findByCriteria(DqStandardDetailsEntityIntCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DqStandardDetailsEntityInt> specification = createSpecification(criteria);
        return dqStandardDetailsEntityIntRepository.findAll(specification, page)
            .map(dqStandardDetailsEntityIntMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DqStandardDetailsEntityIntCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DqStandardDetailsEntityInt> specification = createSpecification(criteria);
        return dqStandardDetailsEntityIntRepository.count(specification);
    }

    /**
     * Function to convert {@link DqStandardDetailsEntityIntCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DqStandardDetailsEntityInt> createSpecification(DqStandardDetailsEntityIntCriteria criteria) {
        Specification<DqStandardDetailsEntityInt> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DqStandardDetailsEntityInt_.id));
            }
            if (criteria.getStdAttributeName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStdAttributeName(), DqStandardDetailsEntityInt_.stdAttributeName));
            }
            if (criteria.getStdAttributeValue() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStdAttributeValue(), DqStandardDetailsEntityInt_.stdAttributeValue));
            }
            if (criteria.getStdId() != null) {
                specification = specification.and(buildSpecification(criteria.getStdId(),
                    root -> root.join(DqStandardDetailsEntityInt_.std, JoinType.LEFT).get(DqStandards_.id)));
            }
        }
        return specification;
    }
}
