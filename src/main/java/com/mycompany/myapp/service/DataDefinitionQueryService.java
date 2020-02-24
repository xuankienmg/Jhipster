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

import com.mycompany.myapp.domain.DataDefinition;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.DataDefinitionRepository;
import com.mycompany.myapp.service.dto.DataDefinitionCriteria;
import com.mycompany.myapp.service.dto.DataDefinitionDTO;
import com.mycompany.myapp.service.mapper.DataDefinitionMapper;

/**
 * Service for executing complex queries for {@link DataDefinition} entities in the database.
 * The main input is a {@link DataDefinitionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DataDefinitionDTO} or a {@link Page} of {@link DataDefinitionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DataDefinitionQueryService extends QueryService<DataDefinition> {

    private final Logger log = LoggerFactory.getLogger(DataDefinitionQueryService.class);

    private final DataDefinitionRepository dataDefinitionRepository;

    private final DataDefinitionMapper dataDefinitionMapper;

    public DataDefinitionQueryService(DataDefinitionRepository dataDefinitionRepository, DataDefinitionMapper dataDefinitionMapper) {
        this.dataDefinitionRepository = dataDefinitionRepository;
        this.dataDefinitionMapper = dataDefinitionMapper;
    }

    /**
     * Return a {@link List} of {@link DataDefinitionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DataDefinitionDTO> findByCriteria(DataDefinitionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DataDefinition> specification = createSpecification(criteria);
        return dataDefinitionMapper.toDto(dataDefinitionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DataDefinitionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DataDefinitionDTO> findByCriteria(DataDefinitionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DataDefinition> specification = createSpecification(criteria);
        return dataDefinitionRepository.findAll(specification, page)
            .map(dataDefinitionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DataDefinitionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DataDefinition> specification = createSpecification(criteria);
        return dataDefinitionRepository.count(specification);
    }

    /**
     * Function to convert {@link DataDefinitionCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DataDefinition> createSpecification(DataDefinitionCriteria criteria) {
        Specification<DataDefinition> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DataDefinition_.id));
            }
            if (criteria.getSrcColId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSrcColId(), DataDefinition_.srcColId));
            }
            if (criteria.getDefDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDefDescription(), DataDefinition_.defDescription));
            }
            if (criteria.getDefSampleData() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDefSampleData(), DataDefinition_.defSampleData));
            }
            if (criteria.getColId() != null) {
                specification = specification.and(buildSpecification(criteria.getColId(),
                    root -> root.join(DataDefinition_.col, JoinType.LEFT).get(DsColumns_.id)));
            }
            if (criteria.getTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getTypeId(),
                    root -> root.join(DataDefinition_.type, JoinType.LEFT).get(DsColumnTypes_.id)));
            }
            if (criteria.getTblId() != null) {
                specification = specification.and(buildSpecification(criteria.getTblId(),
                    root -> root.join(DataDefinition_.tbl, JoinType.LEFT).get(DsTables_.id)));
            }
        }
        return specification;
    }
}
