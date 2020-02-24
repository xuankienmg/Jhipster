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

import com.mycompany.myapp.domain.DqStandardDetailsEntityVarchar;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.DqStandardDetailsEntityVarcharRepository;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityVarcharCriteria;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityVarcharDTO;
import com.mycompany.myapp.service.mapper.DqStandardDetailsEntityVarcharMapper;

/**
 * Service for executing complex queries for {@link DqStandardDetailsEntityVarchar} entities in the database.
 * The main input is a {@link DqStandardDetailsEntityVarcharCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DqStandardDetailsEntityVarcharDTO} or a {@link Page} of {@link DqStandardDetailsEntityVarcharDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DqStandardDetailsEntityVarcharQueryService extends QueryService<DqStandardDetailsEntityVarchar> {

    private final Logger log = LoggerFactory.getLogger(DqStandardDetailsEntityVarcharQueryService.class);

    private final DqStandardDetailsEntityVarcharRepository dqStandardDetailsEntityVarcharRepository;

    private final DqStandardDetailsEntityVarcharMapper dqStandardDetailsEntityVarcharMapper;

    public DqStandardDetailsEntityVarcharQueryService(DqStandardDetailsEntityVarcharRepository dqStandardDetailsEntityVarcharRepository, DqStandardDetailsEntityVarcharMapper dqStandardDetailsEntityVarcharMapper) {
        this.dqStandardDetailsEntityVarcharRepository = dqStandardDetailsEntityVarcharRepository;
        this.dqStandardDetailsEntityVarcharMapper = dqStandardDetailsEntityVarcharMapper;
    }

    /**
     * Return a {@link List} of {@link DqStandardDetailsEntityVarcharDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DqStandardDetailsEntityVarcharDTO> findByCriteria(DqStandardDetailsEntityVarcharCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DqStandardDetailsEntityVarchar> specification = createSpecification(criteria);
        return dqStandardDetailsEntityVarcharMapper.toDto(dqStandardDetailsEntityVarcharRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DqStandardDetailsEntityVarcharDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DqStandardDetailsEntityVarcharDTO> findByCriteria(DqStandardDetailsEntityVarcharCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DqStandardDetailsEntityVarchar> specification = createSpecification(criteria);
        return dqStandardDetailsEntityVarcharRepository.findAll(specification, page)
            .map(dqStandardDetailsEntityVarcharMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DqStandardDetailsEntityVarcharCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DqStandardDetailsEntityVarchar> specification = createSpecification(criteria);
        return dqStandardDetailsEntityVarcharRepository.count(specification);
    }

    /**
     * Function to convert {@link DqStandardDetailsEntityVarcharCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DqStandardDetailsEntityVarchar> createSpecification(DqStandardDetailsEntityVarcharCriteria criteria) {
        Specification<DqStandardDetailsEntityVarchar> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DqStandardDetailsEntityVarchar_.id));
            }
            if (criteria.getStdAtttributeName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStdAtttributeName(), DqStandardDetailsEntityVarchar_.stdAtttributeName));
            }
            if (criteria.getStdAttributeValue() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStdAttributeValue(), DqStandardDetailsEntityVarchar_.stdAttributeValue));
            }
            if (criteria.getStdId() != null) {
                specification = specification.and(buildSpecification(criteria.getStdId(),
                    root -> root.join(DqStandardDetailsEntityVarchar_.std, JoinType.LEFT).get(DqStandards_.id)));
            }
        }
        return specification;
    }
}
