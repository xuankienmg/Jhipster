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

import com.mycompany.myapp.domain.DqRuleCategories;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.DqRuleCategoriesRepository;
import com.mycompany.myapp.service.dto.DqRuleCategoriesCriteria;
import com.mycompany.myapp.service.dto.DqRuleCategoriesDTO;
import com.mycompany.myapp.service.mapper.DqRuleCategoriesMapper;

/**
 * Service for executing complex queries for {@link DqRuleCategories} entities in the database.
 * The main input is a {@link DqRuleCategoriesCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DqRuleCategoriesDTO} or a {@link Page} of {@link DqRuleCategoriesDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DqRuleCategoriesQueryService extends QueryService<DqRuleCategories> {

    private final Logger log = LoggerFactory.getLogger(DqRuleCategoriesQueryService.class);

    private final DqRuleCategoriesRepository dqRuleCategoriesRepository;

    private final DqRuleCategoriesMapper dqRuleCategoriesMapper;

    public DqRuleCategoriesQueryService(DqRuleCategoriesRepository dqRuleCategoriesRepository, DqRuleCategoriesMapper dqRuleCategoriesMapper) {
        this.dqRuleCategoriesRepository = dqRuleCategoriesRepository;
        this.dqRuleCategoriesMapper = dqRuleCategoriesMapper;
    }

    /**
     * Return a {@link List} of {@link DqRuleCategoriesDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DqRuleCategoriesDTO> findByCriteria(DqRuleCategoriesCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DqRuleCategories> specification = createSpecification(criteria);
        return dqRuleCategoriesMapper.toDto(dqRuleCategoriesRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DqRuleCategoriesDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DqRuleCategoriesDTO> findByCriteria(DqRuleCategoriesCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DqRuleCategories> specification = createSpecification(criteria);
        return dqRuleCategoriesRepository.findAll(specification, page)
            .map(dqRuleCategoriesMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DqRuleCategoriesCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DqRuleCategories> specification = createSpecification(criteria);
        return dqRuleCategoriesRepository.count(specification);
    }

    /**
     * Function to convert {@link DqRuleCategoriesCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DqRuleCategories> createSpecification(DqRuleCategoriesCriteria criteria) {
        Specification<DqRuleCategories> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DqRuleCategories_.id));
            }
            if (criteria.getCatName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCatName(), DqRuleCategories_.catName));
            }
            if (criteria.getCatDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCatDescription(), DqRuleCategories_.catDescription));
            }
        }
        return specification;
    }
}
