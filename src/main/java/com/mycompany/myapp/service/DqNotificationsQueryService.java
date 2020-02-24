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

import com.mycompany.myapp.domain.DqNotifications;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.DqNotificationsRepository;
import com.mycompany.myapp.service.dto.DqNotificationsCriteria;
import com.mycompany.myapp.service.dto.DqNotificationsDTO;
import com.mycompany.myapp.service.mapper.DqNotificationsMapper;

/**
 * Service for executing complex queries for {@link DqNotifications} entities in the database.
 * The main input is a {@link DqNotificationsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DqNotificationsDTO} or a {@link Page} of {@link DqNotificationsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DqNotificationsQueryService extends QueryService<DqNotifications> {

    private final Logger log = LoggerFactory.getLogger(DqNotificationsQueryService.class);

    private final DqNotificationsRepository dqNotificationsRepository;

    private final DqNotificationsMapper dqNotificationsMapper;

    public DqNotificationsQueryService(DqNotificationsRepository dqNotificationsRepository, DqNotificationsMapper dqNotificationsMapper) {
        this.dqNotificationsRepository = dqNotificationsRepository;
        this.dqNotificationsMapper = dqNotificationsMapper;
    }

    /**
     * Return a {@link List} of {@link DqNotificationsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DqNotificationsDTO> findByCriteria(DqNotificationsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DqNotifications> specification = createSpecification(criteria);
        return dqNotificationsMapper.toDto(dqNotificationsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DqNotificationsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DqNotificationsDTO> findByCriteria(DqNotificationsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DqNotifications> specification = createSpecification(criteria);
        return dqNotificationsRepository.findAll(specification, page)
            .map(dqNotificationsMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DqNotificationsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DqNotifications> specification = createSpecification(criteria);
        return dqNotificationsRepository.count(specification);
    }

    /**
     * Function to convert {@link DqNotificationsCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DqNotifications> createSpecification(DqNotificationsCriteria criteria) {
        Specification<DqNotifications> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DqNotifications_.id));
            }
            if (criteria.getRepicientId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRepicientId(), DqNotifications_.repicientId));
            }
            if (criteria.getRepicientTypeId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRepicientTypeId(), DqNotifications_.repicientTypeId));
            }
            if (criteria.getRuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getRuleId(),
                    root -> root.join(DqNotifications_.rule, JoinType.LEFT).get(DqRules_.id)));
            }
        }
        return specification;
    }
}
