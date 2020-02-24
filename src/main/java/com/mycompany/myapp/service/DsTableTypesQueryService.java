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

import com.mycompany.myapp.domain.DsTableTypes;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.DsTableTypesRepository;
import com.mycompany.myapp.service.dto.DsTableTypesCriteria;
import com.mycompany.myapp.service.dto.DsTableTypesDTO;
import com.mycompany.myapp.service.mapper.DsTableTypesMapper;

/**
 * Service for executing complex queries for {@link DsTableTypes} entities in the database.
 * The main input is a {@link DsTableTypesCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DsTableTypesDTO} or a {@link Page} of {@link DsTableTypesDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DsTableTypesQueryService extends QueryService<DsTableTypes> {

    private final Logger log = LoggerFactory.getLogger(DsTableTypesQueryService.class);

    private final DsTableTypesRepository dsTableTypesRepository;

    private final DsTableTypesMapper dsTableTypesMapper;

    public DsTableTypesQueryService(DsTableTypesRepository dsTableTypesRepository, DsTableTypesMapper dsTableTypesMapper) {
        this.dsTableTypesRepository = dsTableTypesRepository;
        this.dsTableTypesMapper = dsTableTypesMapper;
    }

    /**
     * Return a {@link List} of {@link DsTableTypesDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DsTableTypesDTO> findByCriteria(DsTableTypesCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DsTableTypes> specification = createSpecification(criteria);
        return dsTableTypesMapper.toDto(dsTableTypesRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DsTableTypesDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DsTableTypesDTO> findByCriteria(DsTableTypesCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DsTableTypes> specification = createSpecification(criteria);
        return dsTableTypesRepository.findAll(specification, page)
            .map(dsTableTypesMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DsTableTypesCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DsTableTypes> specification = createSpecification(criteria);
        return dsTableTypesRepository.count(specification);
    }

    /**
     * Function to convert {@link DsTableTypesCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DsTableTypes> createSpecification(DsTableTypesCriteria criteria) {
        Specification<DsTableTypes> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DsTableTypes_.id));
            }
            if (criteria.getTblTypeName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTblTypeName(), DsTableTypes_.tblTypeName));
            }
            if (criteria.getTblTypeDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTblTypeDescription(), DsTableTypes_.tblTypeDescription));
            }
        }
        return specification;
    }
}
