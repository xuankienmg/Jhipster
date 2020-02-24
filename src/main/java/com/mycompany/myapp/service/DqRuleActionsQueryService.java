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

import com.mycompany.myapp.domain.DqRuleActions;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.DqRuleActionsRepository;
import com.mycompany.myapp.service.dto.DqRuleActionsCriteria;
import com.mycompany.myapp.service.dto.DqRuleActionsDTO;
import com.mycompany.myapp.service.mapper.DqRuleActionsMapper;

/**
 * Service for executing complex queries for {@link DqRuleActions} entities in the database.
 * The main input is a {@link DqRuleActionsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DqRuleActionsDTO} or a {@link Page} of {@link DqRuleActionsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DqRuleActionsQueryService extends QueryService<DqRuleActions> {

    private final Logger log = LoggerFactory.getLogger(DqRuleActionsQueryService.class);

    private final DqRuleActionsRepository dqRuleActionsRepository;

    private final DqRuleActionsMapper dqRuleActionsMapper;

    public DqRuleActionsQueryService(DqRuleActionsRepository dqRuleActionsRepository, DqRuleActionsMapper dqRuleActionsMapper) {
        this.dqRuleActionsRepository = dqRuleActionsRepository;
        this.dqRuleActionsMapper = dqRuleActionsMapper;
    }

    /**
     * Return a {@link List} of {@link DqRuleActionsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DqRuleActionsDTO> findByCriteria(DqRuleActionsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DqRuleActions> specification = createSpecification(criteria);
        return dqRuleActionsMapper.toDto(dqRuleActionsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DqRuleActionsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DqRuleActionsDTO> findByCriteria(DqRuleActionsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DqRuleActions> specification = createSpecification(criteria);
        return dqRuleActionsRepository.findAll(specification, page)
            .map(dqRuleActionsMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DqRuleActionsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DqRuleActions> specification = createSpecification(criteria);
        return dqRuleActionsRepository.count(specification);
    }

    /**
     * Function to convert {@link DqRuleActionsCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DqRuleActions> createSpecification(DqRuleActionsCriteria criteria) {
        Specification<DqRuleActions> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DqRuleActions_.id));
            }
            if (criteria.getActionName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getActionName(), DqRuleActions_.actionName));
            }
            if (criteria.getActionDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getActionDescription(), DqRuleActions_.actionDescription));
            }
        }
        return specification;
    }
}
