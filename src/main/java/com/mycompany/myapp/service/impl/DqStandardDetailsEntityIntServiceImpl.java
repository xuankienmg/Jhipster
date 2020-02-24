package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DqStandardDetailsEntityIntService;
import com.mycompany.myapp.domain.DqStandardDetailsEntityInt;
import com.mycompany.myapp.repository.DqStandardDetailsEntityIntRepository;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityIntDTO;
import com.mycompany.myapp.service.mapper.DqStandardDetailsEntityIntMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DqStandardDetailsEntityInt}.
 */
@Service
@Transactional
public class DqStandardDetailsEntityIntServiceImpl implements DqStandardDetailsEntityIntService {

    private final Logger log = LoggerFactory.getLogger(DqStandardDetailsEntityIntServiceImpl.class);

    private final DqStandardDetailsEntityIntRepository dqStandardDetailsEntityIntRepository;

    private final DqStandardDetailsEntityIntMapper dqStandardDetailsEntityIntMapper;

    public DqStandardDetailsEntityIntServiceImpl(DqStandardDetailsEntityIntRepository dqStandardDetailsEntityIntRepository, DqStandardDetailsEntityIntMapper dqStandardDetailsEntityIntMapper) {
        this.dqStandardDetailsEntityIntRepository = dqStandardDetailsEntityIntRepository;
        this.dqStandardDetailsEntityIntMapper = dqStandardDetailsEntityIntMapper;
    }

    /**
     * Save a dqStandardDetailsEntityInt.
     *
     * @param dqStandardDetailsEntityIntDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DqStandardDetailsEntityIntDTO save(DqStandardDetailsEntityIntDTO dqStandardDetailsEntityIntDTO) {
        log.debug("Request to save DqStandardDetailsEntityInt : {}", dqStandardDetailsEntityIntDTO);
        DqStandardDetailsEntityInt dqStandardDetailsEntityInt = dqStandardDetailsEntityIntMapper.toEntity(dqStandardDetailsEntityIntDTO);
        dqStandardDetailsEntityInt = dqStandardDetailsEntityIntRepository.save(dqStandardDetailsEntityInt);
        return dqStandardDetailsEntityIntMapper.toDto(dqStandardDetailsEntityInt);
    }

    /**
     * Get all the dqStandardDetailsEntityInts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DqStandardDetailsEntityIntDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DqStandardDetailsEntityInts");
        return dqStandardDetailsEntityIntRepository.findAll(pageable)
            .map(dqStandardDetailsEntityIntMapper::toDto);
    }

    /**
     * Get one dqStandardDetailsEntityInt by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DqStandardDetailsEntityIntDTO> findOne(Long id) {
        log.debug("Request to get DqStandardDetailsEntityInt : {}", id);
        return dqStandardDetailsEntityIntRepository.findById(id)
            .map(dqStandardDetailsEntityIntMapper::toDto);
    }

    /**
     * Delete the dqStandardDetailsEntityInt by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DqStandardDetailsEntityInt : {}", id);
        dqStandardDetailsEntityIntRepository.deleteById(id);
    }
}
