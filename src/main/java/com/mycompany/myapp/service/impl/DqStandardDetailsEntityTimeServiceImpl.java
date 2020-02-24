package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DqStandardDetailsEntityTimeService;
import com.mycompany.myapp.domain.DqStandardDetailsEntityTime;
import com.mycompany.myapp.repository.DqStandardDetailsEntityTimeRepository;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityTimeDTO;
import com.mycompany.myapp.service.mapper.DqStandardDetailsEntityTimeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DqStandardDetailsEntityTime}.
 */
@Service
@Transactional
public class DqStandardDetailsEntityTimeServiceImpl implements DqStandardDetailsEntityTimeService {

    private final Logger log = LoggerFactory.getLogger(DqStandardDetailsEntityTimeServiceImpl.class);

    private final DqStandardDetailsEntityTimeRepository dqStandardDetailsEntityTimeRepository;

    private final DqStandardDetailsEntityTimeMapper dqStandardDetailsEntityTimeMapper;

    public DqStandardDetailsEntityTimeServiceImpl(DqStandardDetailsEntityTimeRepository dqStandardDetailsEntityTimeRepository, DqStandardDetailsEntityTimeMapper dqStandardDetailsEntityTimeMapper) {
        this.dqStandardDetailsEntityTimeRepository = dqStandardDetailsEntityTimeRepository;
        this.dqStandardDetailsEntityTimeMapper = dqStandardDetailsEntityTimeMapper;
    }

    /**
     * Save a dqStandardDetailsEntityTime.
     *
     * @param dqStandardDetailsEntityTimeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DqStandardDetailsEntityTimeDTO save(DqStandardDetailsEntityTimeDTO dqStandardDetailsEntityTimeDTO) {
        log.debug("Request to save DqStandardDetailsEntityTime : {}", dqStandardDetailsEntityTimeDTO);
        DqStandardDetailsEntityTime dqStandardDetailsEntityTime = dqStandardDetailsEntityTimeMapper.toEntity(dqStandardDetailsEntityTimeDTO);
        dqStandardDetailsEntityTime = dqStandardDetailsEntityTimeRepository.save(dqStandardDetailsEntityTime);
        return dqStandardDetailsEntityTimeMapper.toDto(dqStandardDetailsEntityTime);
    }

    /**
     * Get all the dqStandardDetailsEntityTimes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DqStandardDetailsEntityTimeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DqStandardDetailsEntityTimes");
        return dqStandardDetailsEntityTimeRepository.findAll(pageable)
            .map(dqStandardDetailsEntityTimeMapper::toDto);
    }

    /**
     * Get one dqStandardDetailsEntityTime by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DqStandardDetailsEntityTimeDTO> findOne(Long id) {
        log.debug("Request to get DqStandardDetailsEntityTime : {}", id);
        return dqStandardDetailsEntityTimeRepository.findById(id)
            .map(dqStandardDetailsEntityTimeMapper::toDto);
    }

    /**
     * Delete the dqStandardDetailsEntityTime by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DqStandardDetailsEntityTime : {}", id);
        dqStandardDetailsEntityTimeRepository.deleteById(id);
    }
}
