package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DqStandardDetailsEntityDatetimeService;
import com.mycompany.myapp.domain.DqStandardDetailsEntityDatetime;
import com.mycompany.myapp.repository.DqStandardDetailsEntityDatetimeRepository;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityDatetimeDTO;
import com.mycompany.myapp.service.mapper.DqStandardDetailsEntityDatetimeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DqStandardDetailsEntityDatetime}.
 */
@Service
@Transactional
public class DqStandardDetailsEntityDatetimeServiceImpl implements DqStandardDetailsEntityDatetimeService {

    private final Logger log = LoggerFactory.getLogger(DqStandardDetailsEntityDatetimeServiceImpl.class);

    private final DqStandardDetailsEntityDatetimeRepository dqStandardDetailsEntityDatetimeRepository;

    private final DqStandardDetailsEntityDatetimeMapper dqStandardDetailsEntityDatetimeMapper;

    public DqStandardDetailsEntityDatetimeServiceImpl(DqStandardDetailsEntityDatetimeRepository dqStandardDetailsEntityDatetimeRepository, DqStandardDetailsEntityDatetimeMapper dqStandardDetailsEntityDatetimeMapper) {
        this.dqStandardDetailsEntityDatetimeRepository = dqStandardDetailsEntityDatetimeRepository;
        this.dqStandardDetailsEntityDatetimeMapper = dqStandardDetailsEntityDatetimeMapper;
    }

    /**
     * Save a dqStandardDetailsEntityDatetime.
     *
     * @param dqStandardDetailsEntityDatetimeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DqStandardDetailsEntityDatetimeDTO save(DqStandardDetailsEntityDatetimeDTO dqStandardDetailsEntityDatetimeDTO) {
        log.debug("Request to save DqStandardDetailsEntityDatetime : {}", dqStandardDetailsEntityDatetimeDTO);
        DqStandardDetailsEntityDatetime dqStandardDetailsEntityDatetime = dqStandardDetailsEntityDatetimeMapper.toEntity(dqStandardDetailsEntityDatetimeDTO);
        dqStandardDetailsEntityDatetime = dqStandardDetailsEntityDatetimeRepository.save(dqStandardDetailsEntityDatetime);
        return dqStandardDetailsEntityDatetimeMapper.toDto(dqStandardDetailsEntityDatetime);
    }

    /**
     * Get all the dqStandardDetailsEntityDatetimes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DqStandardDetailsEntityDatetimeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DqStandardDetailsEntityDatetimes");
        return dqStandardDetailsEntityDatetimeRepository.findAll(pageable)
            .map(dqStandardDetailsEntityDatetimeMapper::toDto);
    }

    /**
     * Get one dqStandardDetailsEntityDatetime by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DqStandardDetailsEntityDatetimeDTO> findOne(Long id) {
        log.debug("Request to get DqStandardDetailsEntityDatetime : {}", id);
        return dqStandardDetailsEntityDatetimeRepository.findById(id)
            .map(dqStandardDetailsEntityDatetimeMapper::toDto);
    }

    /**
     * Delete the dqStandardDetailsEntityDatetime by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DqStandardDetailsEntityDatetime : {}", id);
        dqStandardDetailsEntityDatetimeRepository.deleteById(id);
    }
}
