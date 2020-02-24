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

import com.mycompany.myapp.domain.DqStandards;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.DqStandardsRepository;
import com.mycompany.myapp.service.dto.DqStandardsCriteria;
import com.mycompany.myapp.service.dto.DqStandardsDTO;
import com.mycompany.myapp.service.mapper.DqStandardsMapper;

/**
 * Service for executing complex queries for {@link DqStandards} entities in the database.
 * The main input is a {@link DqStandardsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DqStandardsDTO} or a {@link Page} of {@link DqStandardsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DqStandardsQueryService extends QueryService<DqStandards> {

    private final Logger log = LoggerFactory.getLogger(DqStandardsQueryService.class);

    private final DqStandardsRepository dqStandardsRepository;

    private final DqStandardsMapper dqStandardsMapper;

    public DqStandardsQueryService(DqStandardsRepository dqStandardsRepository, DqStandardsMapper dqStandardsMapper) {
        this.dqStandardsRepository = dqStandardsRepository;
        this.dqStandardsMapper = dqStandardsMapper;
    }

    /**
     * Return a {@link List} of {@link DqStandardsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DqStandardsDTO> findByCriteria(DqStandardsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DqStandards> specification = createSpecification(criteria);
        return dqStandardsMapper.toDto(dqStandardsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DqStandardsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DqStandardsDTO> findByCriteria(DqStandardsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DqStandards> specification = createSpecification(criteria);
        return dqStandardsRepository.findAll(specification, page)
            .map(dqStandardsMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DqStandardsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DqStandards> specification = createSpecification(criteria);
        return dqStandardsRepository.count(specification);
    }

    /**
     * Function to convert {@link DqStandardsCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DqStandards> createSpecification(DqStandardsCriteria criteria) {
        Specification<DqStandards> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DqStandards_.id));
            }
            if (criteria.getStdName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStdName(), DqStandards_.stdName));
            }
            if (criteria.getStdDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStdDescription(), DqStandards_.stdDescription));
            }
            if (criteria.getStdTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getStdTypeId(),
                    root -> root.join(DqStandards_.stdType, JoinType.LEFT).get(DqStandardTypes_.id)));
            }
            if (criteria.getRuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getRuleId(),
                    root -> root.join(DqStandards_.rules, JoinType.LEFT).get(DqRules_.id)));
            }
        }
        return specification;
    }
}
