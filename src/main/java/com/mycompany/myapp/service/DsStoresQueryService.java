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

import com.mycompany.myapp.domain.DsStores;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.DsStoresRepository;
import com.mycompany.myapp.service.dto.DsStoresCriteria;
import com.mycompany.myapp.service.dto.DsStoresDTO;
import com.mycompany.myapp.service.mapper.DsStoresMapper;

/**
 * Service for executing complex queries for {@link DsStores} entities in the database.
 * The main input is a {@link DsStoresCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DsStoresDTO} or a {@link Page} of {@link DsStoresDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DsStoresQueryService extends QueryService<DsStores> {

    private final Logger log = LoggerFactory.getLogger(DsStoresQueryService.class);

    private final DsStoresRepository dsStoresRepository;

    private final DsStoresMapper dsStoresMapper;

    public DsStoresQueryService(DsStoresRepository dsStoresRepository, DsStoresMapper dsStoresMapper) {
        this.dsStoresRepository = dsStoresRepository;
        this.dsStoresMapper = dsStoresMapper;
    }

    /**
     * Return a {@link List} of {@link DsStoresDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DsStoresDTO> findByCriteria(DsStoresCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DsStores> specification = createSpecification(criteria);
        return dsStoresMapper.toDto(dsStoresRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DsStoresDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DsStoresDTO> findByCriteria(DsStoresCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DsStores> specification = createSpecification(criteria);
        return dsStoresRepository.findAll(specification, page)
            .map(dsStoresMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DsStoresCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DsStores> specification = createSpecification(criteria);
        return dsStoresRepository.count(specification);
    }

    /**
     * Function to convert {@link DsStoresCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DsStores> createSpecification(DsStoresCriteria criteria) {
        Specification<DsStores> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DsStores_.id));
            }
            if (criteria.getStoreName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStoreName(), DsStores_.storeName));
            }
            if (criteria.getStoreDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStoreDescription(), DsStores_.storeDescription));
            }
            if (criteria.getStoreSize() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStoreSize(), DsStores_.storeSize));
            }
            if (criteria.getGrowthSize() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGrowthSize(), DsStores_.growthSize));
            }
            if (criteria.getStoreDbmsTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getStoreDbmsTypeId(),
                    root -> root.join(DsStores_.storeDbmsType, JoinType.LEFT).get(DsDbmsTypes_.id)));
            }
            if (criteria.getStoreCollationId() != null) {
                specification = specification.and(buildSpecification(criteria.getStoreCollationId(),
                    root -> root.join(DsStores_.storeCollation, JoinType.LEFT).get(DsCollations_.id)));
            }
        }
        return specification;
    }
}
