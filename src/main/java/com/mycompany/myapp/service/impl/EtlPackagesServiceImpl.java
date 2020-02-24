package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.EtlPackagesService;
import com.mycompany.myapp.domain.EtlPackages;
import com.mycompany.myapp.repository.EtlPackagesRepository;
import com.mycompany.myapp.service.dto.EtlPackagesDTO;
import com.mycompany.myapp.service.mapper.EtlPackagesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EtlPackages}.
 */
@Service
@Transactional
public class EtlPackagesServiceImpl implements EtlPackagesService {

    private final Logger log = LoggerFactory.getLogger(EtlPackagesServiceImpl.class);

    private final EtlPackagesRepository etlPackagesRepository;

    private final EtlPackagesMapper etlPackagesMapper;

    public EtlPackagesServiceImpl(EtlPackagesRepository etlPackagesRepository, EtlPackagesMapper etlPackagesMapper) {
        this.etlPackagesRepository = etlPackagesRepository;
        this.etlPackagesMapper = etlPackagesMapper;
    }

    /**
     * Save a etlPackages.
     *
     * @param etlPackagesDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EtlPackagesDTO save(EtlPackagesDTO etlPackagesDTO) {
        log.debug("Request to save EtlPackages : {}", etlPackagesDTO);
        EtlPackages etlPackages = etlPackagesMapper.toEntity(etlPackagesDTO);
        etlPackages = etlPackagesRepository.save(etlPackages);
        return etlPackagesMapper.toDto(etlPackages);
    }

    /**
     * Get all the etlPackages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EtlPackagesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EtlPackages");
        return etlPackagesRepository.findAll(pageable)
            .map(etlPackagesMapper::toDto);
    }

    /**
     * Get one etlPackages by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EtlPackagesDTO> findOne(Long id) {
        log.debug("Request to get EtlPackages : {}", id);
        return etlPackagesRepository.findById(id)
            .map(etlPackagesMapper::toDto);
    }

    /**
     * Delete the etlPackages by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EtlPackages : {}", id);
        etlPackagesRepository.deleteById(id);
    }
}
