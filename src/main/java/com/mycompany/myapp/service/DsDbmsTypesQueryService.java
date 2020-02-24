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

import com.mycompany.myapp.domain.DsDbmsTypes;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.DsDbmsTypesRepository;
import com.mycompany.myapp.service.dto.DsDbmsTypesCriteria;
import com.mycompany.myapp.service.dto.DsDbmsTypesDTO;
import com.mycompany.myapp.service.mapper.DsDbmsTypesMapper;

/**
 * Service for executing complex queries for {@link DsDbmsTypes} entities in the database.
 * The main input is a {@link DsDbmsTypesCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DsDbmsTypesDTO} or a {@link Page} of {@link DsDbmsTypesDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DsDbmsTypesQueryService extends QueryService<DsDbmsTypes> {

    private final Logger log = LoggerFactory.getLogger(DsDbmsTypesQueryService.class);

    private final DsDbmsTypesRepository dsDbmsTypesRepository;

    private final DsDbmsTypesMapper dsDbmsTypesMapper;

    public DsDbmsTypesQueryService(DsDbmsTypesRepository dsDbmsTypesRepository, DsDbmsTypesMapper dsDbmsTypesMapper) {
        this.dsDbmsTypesRepository = dsDbmsTypesRepository;
        this.dsDbmsTypesMapper = dsDbmsTypesMapper;
    }

    /**
     * Return a {@link List} of {@link DsDbmsTypesDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DsDbmsTypesDTO> findByCriteria(DsDbmsTypesCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DsDbmsTypes> specification = createSpecification(criteria);
        return dsDbmsTypesMapper.toDto(dsDbmsTypesRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DsDbmsTypesDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DsDbmsTypesDTO> findByCriteria(DsDbmsTypesCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DsDbmsTypes> specification = createSpecification(criteria);
        return dsDbmsTypesRepository.findAll(specification, page)
            .map(dsDbmsTypesMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DsDbmsTypesCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DsDbmsTypes> specification = createSpecification(criteria);
        return dsDbmsTypesRepository.count(specification);
    }

    /**
     * Function to convert {@link DsDbmsTypesCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DsDbmsTypes> createSpecification(DsDbmsTypesCriteria criteria) {
        Specification<DsDbmsTypes> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DsDbmsTypes_.id));
            }
            if (criteria.getDbmsTypeName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDbmsTypeName(), DsDbmsTypes_.dbmsTypeName));
            }
            if (criteria.getDbsmTypeDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDbsmTypeDescription(), DsDbmsTypes_.dbsmTypeDescription));
            }
        }
        return specification;
    }
}
