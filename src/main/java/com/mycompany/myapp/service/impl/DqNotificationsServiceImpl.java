package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DqNotificationsService;
import com.mycompany.myapp.domain.DqNotifications;
import com.mycompany.myapp.repository.DqNotificationsRepository;
import com.mycompany.myapp.service.dto.DqNotificationsDTO;
import com.mycompany.myapp.service.mapper.DqNotificationsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DqNotifications}.
 */
@Service
@Transactional
public class DqNotificationsServiceImpl implements DqNotificationsService {

    private final Logger log = LoggerFactory.getLogger(DqNotificationsServiceImpl.class);

    private final DqNotificationsRepository dqNotificationsRepository;

    private final DqNotificationsMapper dqNotificationsMapper;

    public DqNotificationsServiceImpl(DqNotificationsRepository dqNotificationsRepository, DqNotificationsMapper dqNotificationsMapper) {
        this.dqNotificationsRepository = dqNotificationsRepository;
        this.dqNotificationsMapper = dqNotificationsMapper;
    }

    /**
     * Save a dqNotifications.
     *
     * @param dqNotificationsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DqNotificationsDTO save(DqNotificationsDTO dqNotificationsDTO) {
        log.debug("Request to save DqNotifications : {}", dqNotificationsDTO);
        DqNotifications dqNotifications = dqNotificationsMapper.toEntity(dqNotificationsDTO);
        dqNotifications = dqNotificationsRepository.save(dqNotifications);
        return dqNotificationsMapper.toDto(dqNotifications);
    }

    /**
     * Get all the dqNotifications.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DqNotificationsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DqNotifications");
        return dqNotificationsRepository.findAll(pageable)
            .map(dqNotificationsMapper::toDto);
    }

    /**
     * Get one dqNotifications by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DqNotificationsDTO> findOne(Long id) {
        log.debug("Request to get DqNotifications : {}", id);
        return dqNotificationsRepository.findById(id)
            .map(dqNotificationsMapper::toDto);
    }

    /**
     * Delete the dqNotifications by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DqNotifications : {}", id);
        dqNotificationsRepository.deleteById(id);
    }
}
