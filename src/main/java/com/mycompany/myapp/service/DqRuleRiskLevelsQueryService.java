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

import com.mycompany.myapp.domain.DqRuleRiskLevels;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.DqRuleRiskLevelsRepository;
import com.mycompany.myapp.service.dto.DqRuleRiskLevelsCriteria;
import com.mycompany.myapp.service.dto.DqRuleRiskLevelsDTO;
import com.mycompany.myapp.service.mapper.DqRuleRiskLevelsMapper;

/**
 * Service for executing complex queries for {@link DqRuleRiskLevels} entities in the database.
 * The main input is a {@link DqRuleRiskLevelsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DqRuleRiskLevelsDTO} or a {@link Page} of {@link DqRuleRiskLevelsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DqRuleRiskLevelsQueryService extends QueryService<DqRuleRiskLevels> {

    private final Logger log = LoggerFactory.getLogger(DqRuleRiskLevelsQueryService.class);

    private final DqRuleRiskLevelsRepository dqRuleRiskLevelsRepository;

    private final DqRuleRiskLevelsMapper dqRuleRiskLevelsMapper;

    public DqRuleRiskLevelsQueryService(DqRuleRiskLevelsRepository dqRuleRiskLevelsRepository, DqRuleRiskLevelsMapper dqRuleRiskLevelsMapper) {
        this.dqRuleRiskLevelsRepository = dqRuleRiskLevelsRepository;
        this.dqRuleRiskLevelsMapper = dqRuleRiskLevelsMapper;
    }

    /**
     * Return a {@link List} of {@link DqRuleRiskLevelsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DqRuleRiskLevelsDTO> findByCriteria(DqRuleRiskLevelsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DqRuleRiskLevels> specification = createSpecification(criteria);
        return dqRuleRiskLevelsMapper.toDto(dqRuleRiskLevelsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DqRuleRiskLevelsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DqRuleRiskLevelsDTO> findByCriteria(DqRuleRiskLevelsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DqRuleRiskLevels> specification = createSpecification(criteria);
        return dqRuleRiskLevelsRepository.findAll(specification, page)
            .map(dqRuleRiskLevelsMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DqRuleRiskLevelsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DqRuleRiskLevels> specification = createSpecification(criteria);
        return dqRuleRiskLevelsRepository.count(specification);
    }

    /**
     * Function to convert {@link DqRuleRiskLevelsCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DqRuleRiskLevels> createSpecification(DqRuleRiskLevelsCriteria criteria) {
        Specification<DqRuleRiskLevels> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DqRuleRiskLevels_.id));
            }
            if (criteria.getRiskName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRiskName(), DqRuleRiskLevels_.riskName));
            }
            if (criteria.getRiskDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRiskDescription(), DqRuleRiskLevels_.riskDescription));
            }
        }
        return specification;
    }
}
