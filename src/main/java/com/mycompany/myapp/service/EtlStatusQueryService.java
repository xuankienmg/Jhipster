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

import com.mycompany.myapp.domain.EtlStatus;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.EtlStatusRepository;
import com.mycompany.myapp.service.dto.EtlStatusCriteria;
import com.mycompany.myapp.service.dto.EtlStatusDTO;
import com.mycompany.myapp.service.mapper.EtlStatusMapper;

/**
 * Service for executing complex queries for {@link EtlStatus} entities in the database.
 * The main input is a {@link EtlStatusCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EtlStatusDTO} or a {@link Page} of {@link EtlStatusDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EtlStatusQueryService extends QueryService<EtlStatus> {

    private final Logger log = LoggerFactory.getLogger(EtlStatusQueryService.class);

    private final EtlStatusRepository etlStatusRepository;

    private final EtlStatusMapper etlStatusMapper;

    public EtlStatusQueryService(EtlStatusRepository etlStatusRepository, EtlStatusMapper etlStatusMapper) {
        this.etlStatusRepository = etlStatusRepository;
        this.etlStatusMapper = etlStatusMapper;
    }

    /**
     * Return a {@link List} of {@link EtlStatusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EtlStatusDTO> findByCriteria(EtlStatusCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EtlStatus> specification = createSpecification(criteria);
        return etlStatusMapper.toDto(etlStatusRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EtlStatusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EtlStatusDTO> findByCriteria(EtlStatusCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EtlStatus> specification = createSpecification(criteria);
        return etlStatusRepository.findAll(specification, page)
            .map(etlStatusMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EtlStatusCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EtlStatus> specification = createSpecification(criteria);
        return etlStatusRepository.count(specification);
    }

    /**
     * Function to convert {@link EtlStatusCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<EtlStatus> createSpecification(EtlStatusCriteria criteria) {
        Specification<EtlStatus> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), EtlStatus_.id));
            }
            if (criteria.getEtlStatusName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEtlStatusName(), EtlStatus_.etlStatusName));
            }
        }
        return specification;
    }
}
