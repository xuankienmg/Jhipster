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

import com.mycompany.myapp.domain.DsCollations;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.DsCollationsRepository;
import com.mycompany.myapp.service.dto.DsCollationsCriteria;
import com.mycompany.myapp.service.dto.DsCollationsDTO;
import com.mycompany.myapp.service.mapper.DsCollationsMapper;

/**
 * Service for executing complex queries for {@link DsCollations} entities in the database.
 * The main input is a {@link DsCollationsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DsCollationsDTO} or a {@link Page} of {@link DsCollationsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DsCollationsQueryService extends QueryService<DsCollations> {

    private final Logger log = LoggerFactory.getLogger(DsCollationsQueryService.class);

    private final DsCollationsRepository dsCollationsRepository;

    private final DsCollationsMapper dsCollationsMapper;

    public DsCollationsQueryService(DsCollationsRepository dsCollationsRepository, DsCollationsMapper dsCollationsMapper) {
        this.dsCollationsRepository = dsCollationsRepository;
        this.dsCollationsMapper = dsCollationsMapper;
    }

    /**
     * Return a {@link List} of {@link DsCollationsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DsCollationsDTO> findByCriteria(DsCollationsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DsCollations> specification = createSpecification(criteria);
        return dsCollationsMapper.toDto(dsCollationsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DsCollationsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DsCollationsDTO> findByCriteria(DsCollationsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DsCollations> specification = createSpecification(criteria);
        return dsCollationsRepository.findAll(specification, page)
            .map(dsCollationsMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DsCollationsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DsCollations> specification = createSpecification(criteria);
        return dsCollationsRepository.count(specification);
    }

    /**
     * Function to convert {@link DsCollationsCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DsCollations> createSpecification(DsCollationsCriteria criteria) {
        Specification<DsCollations> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DsCollations_.id));
            }
            if (criteria.getCollationName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCollationName(), DsCollations_.collationName));
            }
            if (criteria.getCollationDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCollationDescription(), DsCollations_.collationDescription));
            }
            if (criteria.getDbmsTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getDbmsTypeId(),
                    root -> root.join(DsCollations_.dbmsType, JoinType.LEFT).get(DsDbmsTypes_.id)));
            }
        }
        return specification;
    }
}
