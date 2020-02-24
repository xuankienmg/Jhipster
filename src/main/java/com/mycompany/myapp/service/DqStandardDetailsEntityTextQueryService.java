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

import com.mycompany.myapp.domain.DqStandardDetailsEntityText;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.DqStandardDetailsEntityTextRepository;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityTextCriteria;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityTextDTO;
import com.mycompany.myapp.service.mapper.DqStandardDetailsEntityTextMapper;

/**
 * Service for executing complex queries for {@link DqStandardDetailsEntityText} entities in the database.
 * The main input is a {@link DqStandardDetailsEntityTextCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DqStandardDetailsEntityTextDTO} or a {@link Page} of {@link DqStandardDetailsEntityTextDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DqStandardDetailsEntityTextQueryService extends QueryService<DqStandardDetailsEntityText> {

    private final Logger log = LoggerFactory.getLogger(DqStandardDetailsEntityTextQueryService.class);

    private final DqStandardDetailsEntityTextRepository dqStandardDetailsEntityTextRepository;

    private final DqStandardDetailsEntityTextMapper dqStandardDetailsEntityTextMapper;

    public DqStandardDetailsEntityTextQueryService(DqStandardDetailsEntityTextRepository dqStandardDetailsEntityTextRepository, DqStandardDetailsEntityTextMapper dqStandardDetailsEntityTextMapper) {
        this.dqStandardDetailsEntityTextRepository = dqStandardDetailsEntityTextRepository;
        this.dqStandardDetailsEntityTextMapper = dqStandardDetailsEntityTextMapper;
    }

    /**
     * Return a {@link List} of {@link DqStandardDetailsEntityTextDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DqStandardDetailsEntityTextDTO> findByCriteria(DqStandardDetailsEntityTextCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DqStandardDetailsEntityText> specification = createSpecification(criteria);
        return dqStandardDetailsEntityTextMapper.toDto(dqStandardDetailsEntityTextRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DqStandardDetailsEntityTextDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DqStandardDetailsEntityTextDTO> findByCriteria(DqStandardDetailsEntityTextCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DqStandardDetailsEntityText> specification = createSpecification(criteria);
        return dqStandardDetailsEntityTextRepository.findAll(specification, page)
            .map(dqStandardDetailsEntityTextMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DqStandardDetailsEntityTextCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DqStandardDetailsEntityText> specification = createSpecification(criteria);
        return dqStandardDetailsEntityTextRepository.count(specification);
    }

    /**
     * Function to convert {@link DqStandardDetailsEntityTextCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DqStandardDetailsEntityText> createSpecification(DqStandardDetailsEntityTextCriteria criteria) {
        Specification<DqStandardDetailsEntityText> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DqStandardDetailsEntityText_.id));
            }
            if (criteria.getStdAttributeName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStdAttributeName(), DqStandardDetailsEntityText_.stdAttributeName));
            }
            if (criteria.getStdAttributeValue() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStdAttributeValue(), DqStandardDetailsEntityText_.stdAttributeValue));
            }
            if (criteria.getStdId() != null) {
                specification = specification.and(buildSpecification(criteria.getStdId(),
                    root -> root.join(DqStandardDetailsEntityText_.std, JoinType.LEFT).get(DqStandards_.id)));
            }
        }
        return specification;
    }
}
