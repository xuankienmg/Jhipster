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

import com.mycompany.myapp.domain.DqRuleStatus;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.DqRuleStatusRepository;
import com.mycompany.myapp.service.dto.DqRuleStatusCriteria;
import com.mycompany.myapp.service.dto.DqRuleStatusDTO;
import com.mycompany.myapp.service.mapper.DqRuleStatusMapper;

/**
 * Service for executing complex queries for {@link DqRuleStatus} entities in the database.
 * The main input is a {@link DqRuleStatusCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DqRuleStatusDTO} or a {@link Page} of {@link DqRuleStatusDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DqRuleStatusQueryService extends QueryService<DqRuleStatus> {

    private final Logger log = LoggerFactory.getLogger(DqRuleStatusQueryService.class);

    private final DqRuleStatusRepository dqRuleStatusRepository;

    private final DqRuleStatusMapper dqRuleStatusMapper;

    public DqRuleStatusQueryService(DqRuleStatusRepository dqRuleStatusRepository, DqRuleStatusMapper dqRuleStatusMapper) {
        this.dqRuleStatusRepository = dqRuleStatusRepository;
        this.dqRuleStatusMapper = dqRuleStatusMapper;
    }

    /**
     * Return a {@link List} of {@link DqRuleStatusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DqRuleStatusDTO> findByCriteria(DqRuleStatusCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DqRuleStatus> specification = createSpecification(criteria);
        return dqRuleStatusMapper.toDto(dqRuleStatusRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DqRuleStatusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DqRuleStatusDTO> findByCriteria(DqRuleStatusCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DqRuleStatus> specification = createSpecification(criteria);
        return dqRuleStatusRepository.findAll(specification, page)
            .map(dqRuleStatusMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DqRuleStatusCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DqRuleStatus> specification = createSpecification(criteria);
        return dqRuleStatusRepository.count(specification);
    }

    /**
     * Function to convert {@link DqRuleStatusCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DqRuleStatus> createSpecification(DqRuleStatusCriteria criteria) {
        Specification<DqRuleStatus> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DqRuleStatus_.id));
            }
            if (criteria.getStatusName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatusName(), DqRuleStatus_.statusName));
            }
            if (criteria.getStatusDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatusDescription(), DqRuleStatus_.statusDescription));
            }
        }
        return specification;
    }
}
