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

import com.mycompany.myapp.domain.DqRules;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.DqRulesRepository;
import com.mycompany.myapp.service.dto.DqRulesCriteria;
import com.mycompany.myapp.service.dto.DqRulesDTO;
import com.mycompany.myapp.service.mapper.DqRulesMapper;

/**
 * Service for executing complex queries for {@link DqRules} entities in the database.
 * The main input is a {@link DqRulesCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DqRulesDTO} or a {@link Page} of {@link DqRulesDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DqRulesQueryService extends QueryService<DqRules> {

    private final Logger log = LoggerFactory.getLogger(DqRulesQueryService.class);

    private final DqRulesRepository dqRulesRepository;

    private final DqRulesMapper dqRulesMapper;

    public DqRulesQueryService(DqRulesRepository dqRulesRepository, DqRulesMapper dqRulesMapper) {
        this.dqRulesRepository = dqRulesRepository;
        this.dqRulesMapper = dqRulesMapper;
    }

    /**
     * Return a {@link List} of {@link DqRulesDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DqRulesDTO> findByCriteria(DqRulesCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DqRules> specification = createSpecification(criteria);
        return dqRulesMapper.toDto(dqRulesRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DqRulesDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DqRulesDTO> findByCriteria(DqRulesCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DqRules> specification = createSpecification(criteria);
        return dqRulesRepository.findAll(specification, page)
            .map(dqRulesMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DqRulesCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DqRules> specification = createSpecification(criteria);
        return dqRulesRepository.count(specification);
    }

    /**
     * Function to convert {@link DqRulesCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DqRules> createSpecification(DqRulesCriteria criteria) {
        Specification<DqRules> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DqRules_.id));
            }
            if (criteria.getRuleName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRuleName(), DqRules_.ruleName));
            }
            if (criteria.getRuleDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRuleDescription(), DqRules_.ruleDescription));
            }
            if (criteria.getTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getTypeId(),
                    root -> root.join(DqRules_.type, JoinType.LEFT).get(DqRuleTypes_.id)));
            }
            if (criteria.getRiskId() != null) {
                specification = specification.and(buildSpecification(criteria.getRiskId(),
                    root -> root.join(DqRules_.risk, JoinType.LEFT).get(DqRuleRiskLevels_.id)));
            }
            if (criteria.getStatusId() != null) {
                specification = specification.and(buildSpecification(criteria.getStatusId(),
                    root -> root.join(DqRules_.status, JoinType.LEFT).get(DqRuleStatus_.id)));
            }
            if (criteria.getCatId() != null) {
                specification = specification.and(buildSpecification(criteria.getCatId(),
                    root -> root.join(DqRules_.cat, JoinType.LEFT).get(DqRuleCategories_.id)));
            }
            if (criteria.getActionId() != null) {
                specification = specification.and(buildSpecification(criteria.getActionId(),
                    root -> root.join(DqRules_.action, JoinType.LEFT).get(DqRuleActions_.id)));
            }
            if (criteria.getStdId() != null) {
                specification = specification.and(buildSpecification(criteria.getStdId(),
                    root -> root.join(DqRules_.stds, JoinType.LEFT).get(DqStandards_.id)));
            }
            if (criteria.getColId() != null) {
                specification = specification.and(buildSpecification(criteria.getColId(),
                    root -> root.join(DqRules_.cols, JoinType.LEFT).get(DsColumns_.id)));
            }
        }
        return specification;
    }
}
