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

import com.mycompany.myapp.domain.DataMapping;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.DataMappingRepository;
import com.mycompany.myapp.service.dto.DataMappingCriteria;
import com.mycompany.myapp.service.dto.DataMappingDTO;
import com.mycompany.myapp.service.mapper.DataMappingMapper;

/**
 * Service for executing complex queries for {@link DataMapping} entities in the database.
 * The main input is a {@link DataMappingCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DataMappingDTO} or a {@link Page} of {@link DataMappingDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DataMappingQueryService extends QueryService<DataMapping> {

    private final Logger log = LoggerFactory.getLogger(DataMappingQueryService.class);

    private final DataMappingRepository dataMappingRepository;

    private final DataMappingMapper dataMappingMapper;

    public DataMappingQueryService(DataMappingRepository dataMappingRepository, DataMappingMapper dataMappingMapper) {
        this.dataMappingRepository = dataMappingRepository;
        this.dataMappingMapper = dataMappingMapper;
    }

    /**
     * Return a {@link List} of {@link DataMappingDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DataMappingDTO> findByCriteria(DataMappingCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DataMapping> specification = createSpecification(criteria);
        return dataMappingMapper.toDto(dataMappingRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DataMappingDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DataMappingDTO> findByCriteria(DataMappingCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DataMapping> specification = createSpecification(criteria);
        return dataMappingRepository.findAll(specification, page)
            .map(dataMappingMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DataMappingCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DataMapping> specification = createSpecification(criteria);
        return dataMappingRepository.count(specification);
    }

    /**
     * Function to convert {@link DataMappingCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DataMapping> createSpecification(DataMappingCriteria criteria) {
        Specification<DataMapping> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DataMapping_.id));
            }
            if (criteria.getSrcColId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSrcColId(), DataMapping_.srcColId));
            }
            if (criteria.getColId() != null) {
                specification = specification.and(buildSpecification(criteria.getColId(),
                    root -> root.join(DataMapping_.col, JoinType.LEFT).get(DsColumns_.id)));
            }
        }
        return specification;
    }
}
