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

import com.mycompany.myapp.domain.DataFlows;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.DataFlowsRepository;
import com.mycompany.myapp.service.dto.DataFlowsCriteria;
import com.mycompany.myapp.service.dto.DataFlowsDTO;
import com.mycompany.myapp.service.mapper.DataFlowsMapper;

/**
 * Service for executing complex queries for {@link DataFlows} entities in the database.
 * The main input is a {@link DataFlowsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DataFlowsDTO} or a {@link Page} of {@link DataFlowsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DataFlowsQueryService extends QueryService<DataFlows> {

    private final Logger log = LoggerFactory.getLogger(DataFlowsQueryService.class);

    private final DataFlowsRepository dataFlowsRepository;

    private final DataFlowsMapper dataFlowsMapper;

    public DataFlowsQueryService(DataFlowsRepository dataFlowsRepository, DataFlowsMapper dataFlowsMapper) {
        this.dataFlowsRepository = dataFlowsRepository;
        this.dataFlowsMapper = dataFlowsMapper;
    }

    /**
     * Return a {@link List} of {@link DataFlowsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DataFlowsDTO> findByCriteria(DataFlowsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DataFlows> specification = createSpecification(criteria);
        return dataFlowsMapper.toDto(dataFlowsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DataFlowsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DataFlowsDTO> findByCriteria(DataFlowsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DataFlows> specification = createSpecification(criteria);
        return dataFlowsRepository.findAll(specification, page)
            .map(dataFlowsMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DataFlowsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DataFlows> specification = createSpecification(criteria);
        return dataFlowsRepository.count(specification);
    }

    /**
     * Function to convert {@link DataFlowsCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DataFlows> createSpecification(DataFlowsCriteria criteria) {
        Specification<DataFlows> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DataFlows_.id));
            }
            if (criteria.getFlowName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFlowName(), DataFlows_.flowName));
            }
            if (criteria.getFlowDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFlowDescription(), DataFlows_.flowDescription));
            }
            if (criteria.getSource() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSource(), DataFlows_.source));
            }
            if (criteria.getDestination() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDestination(), DataFlows_.destination));
            }
            if (criteria.getTransformation() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTransformation(), DataFlows_.transformation));
            }
            if (criteria.getlSET() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getlSET(), DataFlows_.lSET));
            }
            if (criteria.getcET() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getcET(), DataFlows_.cET));
            }
            if (criteria.getEtlStatusId() != null) {
                specification = specification.and(buildSpecification(criteria.getEtlStatusId(),
                    root -> root.join(DataFlows_.etlStatus, JoinType.LEFT).get(EtlStatus_.id)));
            }
            if (criteria.getEtlPkgId() != null) {
                specification = specification.and(buildSpecification(criteria.getEtlPkgId(),
                    root -> root.join(DataFlows_.etlPkg, JoinType.LEFT).get(EtlPackages_.id)));
            }
        }
        return specification;
    }
}
