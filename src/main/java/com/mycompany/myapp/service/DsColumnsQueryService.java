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

import com.mycompany.myapp.domain.DsColumns;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.DsColumnsRepository;
import com.mycompany.myapp.service.dto.DsColumnsCriteria;
import com.mycompany.myapp.service.dto.DsColumnsDTO;
import com.mycompany.myapp.service.mapper.DsColumnsMapper;

/**
 * Service for executing complex queries for {@link DsColumns} entities in the database.
 * The main input is a {@link DsColumnsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DsColumnsDTO} or a {@link Page} of {@link DsColumnsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DsColumnsQueryService extends QueryService<DsColumns> {

    private final Logger log = LoggerFactory.getLogger(DsColumnsQueryService.class);

    private final DsColumnsRepository dsColumnsRepository;

    private final DsColumnsMapper dsColumnsMapper;

    public DsColumnsQueryService(DsColumnsRepository dsColumnsRepository, DsColumnsMapper dsColumnsMapper) {
        this.dsColumnsRepository = dsColumnsRepository;
        this.dsColumnsMapper = dsColumnsMapper;
    }

    /**
     * Return a {@link List} of {@link DsColumnsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DsColumnsDTO> findByCriteria(DsColumnsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DsColumns> specification = createSpecification(criteria);
        return dsColumnsMapper.toDto(dsColumnsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DsColumnsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DsColumnsDTO> findByCriteria(DsColumnsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DsColumns> specification = createSpecification(criteria);
        return dsColumnsRepository.findAll(specification, page)
            .map(dsColumnsMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DsColumnsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DsColumns> specification = createSpecification(criteria);
        return dsColumnsRepository.count(specification);
    }

    /**
     * Function to convert {@link DsColumnsCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DsColumns> createSpecification(DsColumnsCriteria criteria) {
        Specification<DsColumns> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DsColumns_.id));
            }
            if (criteria.getColName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getColName(), DsColumns_.colName));
            }
            if (criteria.getColDataType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getColDataType(), DsColumns_.colDataType));
            }
            if (criteria.getIsPrimaryKey() != null) {
                specification = specification.and(buildSpecification(criteria.getIsPrimaryKey(), DsColumns_.isPrimaryKey));
            }
            if (criteria.getIsForeignKey() != null) {
                specification = specification.and(buildSpecification(criteria.getIsForeignKey(), DsColumns_.isForeignKey));
            }
            if (criteria.getIsIdentity() != null) {
                specification = specification.and(buildSpecification(criteria.getIsIdentity(), DsColumns_.isIdentity));
            }
            if (criteria.getIsNull() != null) {
                specification = specification.and(buildSpecification(criteria.getIsNull(), DsColumns_.isNull));
            }
            if (criteria.getColTblId() != null) {
                specification = specification.and(buildSpecification(criteria.getColTblId(),
                    root -> root.join(DsColumns_.colTbl, JoinType.LEFT).get(DsTables_.id)));
            }
            if (criteria.getRuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getRuleId(),
                    root -> root.join(DsColumns_.rules, JoinType.LEFT).get(DqRules_.id)));
            }
        }
        return specification;
    }
}
