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

import com.mycompany.myapp.domain.DsTables;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.DsTablesRepository;
import com.mycompany.myapp.service.dto.DsTablesCriteria;
import com.mycompany.myapp.service.dto.DsTablesDTO;
import com.mycompany.myapp.service.mapper.DsTablesMapper;

/**
 * Service for executing complex queries for {@link DsTables} entities in the database.
 * The main input is a {@link DsTablesCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DsTablesDTO} or a {@link Page} of {@link DsTablesDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DsTablesQueryService extends QueryService<DsTables> {

    private final Logger log = LoggerFactory.getLogger(DsTablesQueryService.class);

    private final DsTablesRepository dsTablesRepository;

    private final DsTablesMapper dsTablesMapper;

    public DsTablesQueryService(DsTablesRepository dsTablesRepository, DsTablesMapper dsTablesMapper) {
        this.dsTablesRepository = dsTablesRepository;
        this.dsTablesMapper = dsTablesMapper;
    }

    /**
     * Return a {@link List} of {@link DsTablesDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DsTablesDTO> findByCriteria(DsTablesCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DsTables> specification = createSpecification(criteria);
        return dsTablesMapper.toDto(dsTablesRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DsTablesDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DsTablesDTO> findByCriteria(DsTablesCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DsTables> specification = createSpecification(criteria);
        return dsTablesRepository.findAll(specification, page)
            .map(dsTablesMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DsTablesCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DsTables> specification = createSpecification(criteria);
        return dsTablesRepository.count(specification);
    }

    /**
     * Function to convert {@link DsTablesCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DsTables> createSpecification(DsTablesCriteria criteria) {
        Specification<DsTables> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DsTables_.id));
            }
            if (criteria.getTblName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTblName(), DsTables_.tblName));
            }
            if (criteria.getTblDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTblDescription(), DsTables_.tblDescription));
            }
            if (criteria.getTblTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getTblTypeId(),
                    root -> root.join(DsTables_.tblType, JoinType.LEFT).get(DsTableTypes_.id)));
            }
            if (criteria.getStoreId() != null) {
                specification = specification.and(buildSpecification(criteria.getStoreId(),
                    root -> root.join(DsTables_.store, JoinType.LEFT).get(DsStores_.id)));
            }
        }
        return specification;
    }
}
