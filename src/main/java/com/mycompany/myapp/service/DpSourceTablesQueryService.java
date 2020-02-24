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

import com.mycompany.myapp.domain.DpSourceTables;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.DpSourceTablesRepository;
import com.mycompany.myapp.service.dto.DpSourceTablesCriteria;
import com.mycompany.myapp.service.dto.DpSourceTablesDTO;
import com.mycompany.myapp.service.mapper.DpSourceTablesMapper;

/**
 * Service for executing complex queries for {@link DpSourceTables} entities in the database.
 * The main input is a {@link DpSourceTablesCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DpSourceTablesDTO} or a {@link Page} of {@link DpSourceTablesDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DpSourceTablesQueryService extends QueryService<DpSourceTables> {

    private final Logger log = LoggerFactory.getLogger(DpSourceTablesQueryService.class);

    private final DpSourceTablesRepository dpSourceTablesRepository;

    private final DpSourceTablesMapper dpSourceTablesMapper;

    public DpSourceTablesQueryService(DpSourceTablesRepository dpSourceTablesRepository, DpSourceTablesMapper dpSourceTablesMapper) {
        this.dpSourceTablesRepository = dpSourceTablesRepository;
        this.dpSourceTablesMapper = dpSourceTablesMapper;
    }

    /**
     * Return a {@link List} of {@link DpSourceTablesDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DpSourceTablesDTO> findByCriteria(DpSourceTablesCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DpSourceTables> specification = createSpecification(criteria);
        return dpSourceTablesMapper.toDto(dpSourceTablesRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DpSourceTablesDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DpSourceTablesDTO> findByCriteria(DpSourceTablesCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DpSourceTables> specification = createSpecification(criteria);
        return dpSourceTablesRepository.findAll(specification, page)
            .map(dpSourceTablesMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DpSourceTablesCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DpSourceTables> specification = createSpecification(criteria);
        return dpSourceTablesRepository.count(specification);
    }

    /**
     * Function to convert {@link DpSourceTablesCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DpSourceTables> createSpecification(DpSourceTablesCriteria criteria) {
        Specification<DpSourceTables> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DpSourceTables_.id));
            }
            if (criteria.getRows() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRows(), DpSourceTables_.rows));
            }
            if (criteria.getRowSize() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRowSize(), DpSourceTables_.rowSize));
            }
            if (criteria.getColumns() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getColumns(), DpSourceTables_.columns));
            }
            if (criteria.getHasTimestamp() != null) {
                specification = specification.and(buildSpecification(criteria.getHasTimestamp(), DpSourceTables_.hasTimestamp));
            }
            if (criteria.getTblId() != null) {
                specification = specification.and(buildSpecification(criteria.getTblId(),
                    root -> root.join(DpSourceTables_.tbl, JoinType.LEFT).get(DsTables_.id)));
            }
        }
        return specification;
    }
}
