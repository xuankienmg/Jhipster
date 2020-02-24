package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DqRuleCategoriesService;
import com.mycompany.myapp.domain.DqRuleCategories;
import com.mycompany.myapp.repository.DqRuleCategoriesRepository;
import com.mycompany.myapp.service.dto.DqRuleCategoriesDTO;
import com.mycompany.myapp.service.mapper.DqRuleCategoriesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DqRuleCategories}.
 */
@Service
@Transactional
public class DqRuleCategoriesServiceImpl implements DqRuleCategoriesService {

    private final Logger log = LoggerFactory.getLogger(DqRuleCategoriesServiceImpl.class);

    private final DqRuleCategoriesRepository dqRuleCategoriesRepository;

    private final DqRuleCategoriesMapper dqRuleCategoriesMapper;

    public DqRuleCategoriesServiceImpl(DqRuleCategoriesRepository dqRuleCategoriesRepository, DqRuleCategoriesMapper dqRuleCategoriesMapper) {
        this.dqRuleCategoriesRepository = dqRuleCategoriesRepository;
        this.dqRuleCategoriesMapper = dqRuleCategoriesMapper;
    }

    /**
     * Save a dqRuleCategories.
     *
     * @param dqRuleCategoriesDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DqRuleCategoriesDTO save(DqRuleCategoriesDTO dqRuleCategoriesDTO) {
        log.debug("Request to save DqRuleCategories : {}", dqRuleCategoriesDTO);
        DqRuleCategories dqRuleCategories = dqRuleCategoriesMapper.toEntity(dqRuleCategoriesDTO);
        dqRuleCategories = dqRuleCategoriesRepository.save(dqRuleCategories);
        return dqRuleCategoriesMapper.toDto(dqRuleCategories);
    }

    /**
     * Get all the dqRuleCategories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DqRuleCategoriesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DqRuleCategories");
        return dqRuleCategoriesRepository.findAll(pageable)
            .map(dqRuleCategoriesMapper::toDto);
    }

    /**
     * Get one dqRuleCategories by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DqRuleCategoriesDTO> findOne(Long id) {
        log.debug("Request to get DqRuleCategories : {}", id);
        return dqRuleCategoriesRepository.findById(id)
            .map(dqRuleCategoriesMapper::toDto);
    }

    /**
     * Delete the dqRuleCategories by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DqRuleCategories : {}", id);
        dqRuleCategoriesRepository.deleteById(id);
    }
}
