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

import com.mycompany.myapp.domain.DsColumnTypes;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.DsColumnTypesRepository;
import com.mycompany.myapp.service.dto.DsColumnTypesCriteria;
import com.mycompany.myapp.service.dto.DsColumnTypesDTO;
import com.mycompany.myapp.service.mapper.DsColumnTypesMapper;

/**
 * Service for executing complex queries for {@link DsColumnTypes} entities in the database.
 * The main input is a {@link DsColumnTypesCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DsColumnTypesDTO} or a {@link Page} of {@link DsColumnTypesDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DsColumnTypesQueryService extends QueryService<DsColumnTypes> {

    private final Logger log = LoggerFactory.getLogger(DsColumnTypesQueryService.class);

    private final DsColumnTypesRepository dsColumnTypesRepository;

    private final DsColumnTypesMapper dsColumnTypesMapper;

    public DsColumnTypesQueryService(DsColumnTypesRepository dsColumnTypesRepository, DsColumnTypesMapper dsColumnTypesMapper) {
        this.dsColumnTypesRepository = dsColumnTypesRepository;
        this.dsColumnTypesMapper = dsColumnTypesMapper;
    }

    /**
     * Return a {@link List} of {@link DsColumnTypesDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DsColumnTypesDTO> findByCriteria(DsColumnTypesCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DsColumnTypes> specification = createSpecification(criteria);
        return dsColumnTypesMapper.toDto(dsColumnTypesRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DsColumnTypesDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DsColumnTypesDTO> findByCriteria(DsColumnTypesCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DsColumnTypes> specification = createSpecification(criteria);
        return dsColumnTypesRepository.findAll(specification, page)
            .map(dsColumnTypesMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DsColumnTypesCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DsColumnTypes> specification = createSpecification(criteria);
        return dsColumnTypesRepository.count(specification);
    }

    /**
     * Function to convert {@link DsColumnTypesCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DsColumnTypes> createSpecification(DsColumnTypesCriteria criteria) {
        Specification<DsColumnTypes> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DsColumnTypes_.id));
            }
            if (criteria.getColTypeName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getColTypeName(), DsColumnTypes_.colTypeName));
            }
            if (criteria.getColTypeDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getColTypeDescription(), DsColumnTypes_.colTypeDescription));
            }
        }
        return specification;
    }
}
