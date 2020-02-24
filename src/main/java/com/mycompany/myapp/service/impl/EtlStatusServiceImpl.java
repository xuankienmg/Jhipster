package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.EtlStatusService;
import com.mycompany.myapp.domain.EtlStatus;
import com.mycompany.myapp.repository.EtlStatusRepository;
import com.mycompany.myapp.service.dto.EtlStatusDTO;
import com.mycompany.myapp.service.mapper.EtlStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EtlStatus}.
 */
@Service
@Transactional
public class EtlStatusServiceImpl implements EtlStatusService {

    private final Logger log = LoggerFactory.getLogger(EtlStatusServiceImpl.class);

    private final EtlStatusRepository etlStatusRepository;

    private final EtlStatusMapper etlStatusMapper;

    public EtlStatusServiceImpl(EtlStatusRepository etlStatusRepository, EtlStatusMapper etlStatusMapper) {
        this.etlStatusRepository = etlStatusRepository;
        this.etlStatusMapper = etlStatusMapper;
    }

    /**
     * Save a etlStatus.
     *
     * @param etlStatusDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EtlStatusDTO save(EtlStatusDTO etlStatusDTO) {
        log.debug("Request to save EtlStatus : {}", etlStatusDTO);
        EtlStatus etlStatus = etlStatusMapper.toEntity(etlStatusDTO);
        etlStatus = etlStatusRepository.save(etlStatus);
        return etlStatusMapper.toDto(etlStatus);
    }

    /**
     * Get all the etlStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EtlStatusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EtlStatuses");
        return etlStatusRepository.findAll(pageable)
            .map(etlStatusMapper::toDto);
    }

    /**
     * Get one etlStatus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EtlStatusDTO> findOne(Long id) {
        log.debug("Request to get EtlStatus : {}", id);
        return etlStatusRepository.findById(id)
            .map(etlStatusMapper::toDto);
    }

    /**
     * Delete the etlStatus by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EtlStatus : {}", id);
        etlStatusRepository.deleteById(id);
    }
}
