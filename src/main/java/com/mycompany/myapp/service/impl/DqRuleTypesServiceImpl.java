package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DqRuleTypesService;
import com.mycompany.myapp.domain.DqRuleTypes;
import com.mycompany.myapp.repository.DqRuleTypesRepository;
import com.mycompany.myapp.service.dto.DqRuleTypesDTO;
import com.mycompany.myapp.service.mapper.DqRuleTypesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DqRuleTypes}.
 */
@Service
@Transactional
public class DqRuleTypesServiceImpl implements DqRuleTypesService {

    private final Logger log = LoggerFactory.getLogger(DqRuleTypesServiceImpl.class);

    private final DqRuleTypesRepository dqRuleTypesRepository;

    private final DqRuleTypesMapper dqRuleTypesMapper;

    public DqRuleTypesServiceImpl(DqRuleTypesRepository dqRuleTypesRepository, DqRuleTypesMapper dqRuleTypesMapper) {
        this.dqRuleTypesRepository = dqRuleTypesRepository;
        this.dqRuleTypesMapper = dqRuleTypesMapper;
    }

    /**
     * Save a dqRuleTypes.
     *
     * @param dqRuleTypesDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DqRuleTypesDTO save(DqRuleTypesDTO dqRuleTypesDTO) {
        log.debug("Request to save DqRuleTypes : {}", dqRuleTypesDTO);
        DqRuleTypes dqRuleTypes = dqRuleTypesMapper.toEntity(dqRuleTypesDTO);
        dqRuleTypes = dqRuleTypesRepository.save(dqRuleTypes);
        return dqRuleTypesMapper.toDto(dqRuleTypes);
    }

    /**
     * Get all the dqRuleTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DqRuleTypesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DqRuleTypes");
        return dqRuleTypesRepository.findAll(pageable)
            .map(dqRuleTypesMapper::toDto);
    }

    /**
     * Get one dqRuleTypes by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DqRuleTypesDTO> findOne(Long id) {
        log.debug("Request to get DqRuleTypes : {}", id);
        return dqRuleTypesRepository.findById(id)
            .map(dqRuleTypesMapper::toDto);
    }

    /**
     * Delete the dqRuleTypes by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DqRuleTypes : {}", id);
        dqRuleTypesRepository.deleteById(id);
    }
}
