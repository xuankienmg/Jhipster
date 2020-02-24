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

import com.mycompany.myapp.domain.DqStandardTypes;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.DqStandardTypesRepository;
import com.mycompany.myapp.service.dto.DqStandardTypesCriteria;
import com.mycompany.myapp.service.dto.DqStandardTypesDTO;
import com.mycompany.myapp.service.mapper.DqStandardTypesMapper;

/**
 * Service for executing complex queries for {@link DqStandardTypes} entities in the database.
 * The main input is a {@link DqStandardTypesCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DqStandardTypesDTO} or a {@link Page} of {@link DqStandardTypesDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DqStandardTypesQueryService extends QueryService<DqStandardTypes> {

    private final Logger log = LoggerFactory.getLogger(DqStandardTypesQueryService.class);

    private final DqStandardTypesRepository dqStandardTypesRepository;

    private final DqStandardTypesMapper dqStandardTypesMapper;

    public DqStandardTypesQueryService(DqStandardTypesRepository dqStandardTypesRepository, DqStandardTypesMapper dqStandardTypesMapper) {
        this.dqStandardTypesRepository = dqStandardTypesRepository;
        this.dqStandardTypesMapper = dqStandardTypesMapper;
    }

    /**
     * Return a {@link List} of {@link DqStandardTypesDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DqStandardTypesDTO> findByCriteria(DqStandardTypesCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DqStandardTypes> specification = createSpecification(criteria);
        return dqStandardTypesMapper.toDto(dqStandardTypesRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DqStandardTypesDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DqStandardTypesDTO> findByCriteria(DqStandardTypesCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DqStandardTypes> specification = createSpecification(criteria);
        return dqStandardTypesRepository.findAll(specification, page)
            .map(dqStandardTypesMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DqStandardTypesCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DqStandardTypes> specification = createSpecification(criteria);
        return dqStandardTypesRepository.count(specification);
    }

    /**
     * Function to convert {@link DqStandardTypesCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DqStandardTypes> createSpecification(DqStandardTypesCriteria criteria) {
        Specification<DqStandardTypes> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DqStandardTypes_.id));
            }
            if (criteria.getStdTypeName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStdTypeName(), DqStandardTypes_.stdTypeName));
            }
            if (criteria.getStdTypeDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStdTypeDescription(), DqStandardTypes_.stdTypeDescription));
            }
        }
        return specification;
    }
}
