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

import com.mycompany.myapp.domain.EtlPackages;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.EtlPackagesRepository;
import com.mycompany.myapp.service.dto.EtlPackagesCriteria;
import com.mycompany.myapp.service.dto.EtlPackagesDTO;
import com.mycompany.myapp.service.mapper.EtlPackagesMapper;

/**
 * Service for executing complex queries for {@link EtlPackages} entities in the database.
 * The main input is a {@link EtlPackagesCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EtlPackagesDTO} or a {@link Page} of {@link EtlPackagesDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EtlPackagesQueryService extends QueryService<EtlPackages> {

    private final Logger log = LoggerFactory.getLogger(EtlPackagesQueryService.class);

    private final EtlPackagesRepository etlPackagesRepository;

    private final EtlPackagesMapper etlPackagesMapper;

    public EtlPackagesQueryService(EtlPackagesRepository etlPackagesRepository, EtlPackagesMapper etlPackagesMapper) {
        this.etlPackagesRepository = etlPackagesRepository;
        this.etlPackagesMapper = etlPackagesMapper;
    }

    /**
     * Return a {@link List} of {@link EtlPackagesDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EtlPackagesDTO> findByCriteria(EtlPackagesCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EtlPackages> specification = createSpecification(criteria);
        return etlPackagesMapper.toDto(etlPackagesRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EtlPackagesDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EtlPackagesDTO> findByCriteria(EtlPackagesCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EtlPackages> specification = createSpecification(criteria);
        return etlPackagesRepository.findAll(specification, page)
            .map(etlPackagesMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EtlPackagesCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EtlPackages> specification = createSpecification(criteria);
        return etlPackagesRepository.count(specification);
    }

    /**
     * Function to convert {@link EtlPackagesCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<EtlPackages> createSpecification(EtlPackagesCriteria criteria) {
        Specification<EtlPackages> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), EtlPackages_.id));
            }
            if (criteria.getEtlPkgName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEtlPkgName(), EtlPackages_.etlPkgName));
            }
            if (criteria.getEtlPkgDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEtlPkgDescription(), EtlPackages_.etlPkgDescription));
            }
            if (criteria.getEtlPkgSchedule() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEtlPkgSchedule(), EtlPackages_.etlPkgSchedule));
            }
        }
        return specification;
    }
}
