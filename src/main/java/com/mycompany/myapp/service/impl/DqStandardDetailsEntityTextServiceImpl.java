package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DqStandardDetailsEntityTextService;
import com.mycompany.myapp.domain.DqStandardDetailsEntityText;
import com.mycompany.myapp.repository.DqStandardDetailsEntityTextRepository;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityTextDTO;
import com.mycompany.myapp.service.mapper.DqStandardDetailsEntityTextMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DqStandardDetailsEntityText}.
 */
@Service
@Transactional
public class DqStandardDetailsEntityTextServiceImpl implements DqStandardDetailsEntityTextService {

    private final Logger log = LoggerFactory.getLogger(DqStandardDetailsEntityTextServiceImpl.class);

    private final DqStandardDetailsEntityTextRepository dqStandardDetailsEntityTextRepository;

    private final DqStandardDetailsEntityTextMapper dqStandardDetailsEntityTextMapper;

    public DqStandardDetailsEntityTextServiceImpl(DqStandardDetailsEntityTextRepository dqStandardDetailsEntityTextRepository, DqStandardDetailsEntityTextMapper dqStandardDetailsEntityTextMapper) {
        this.dqStandardDetailsEntityTextRepository = dqStandardDetailsEntityTextRepository;
        this.dqStandardDetailsEntityTextMapper = dqStandardDetailsEntityTextMapper;
    }

    /**
     * Save a dqStandardDetailsEntityText.
     *
     * @param dqStandardDetailsEntityTextDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DqStandardDetailsEntityTextDTO save(DqStandardDetailsEntityTextDTO dqStandardDetailsEntityTextDTO) {
        log.debug("Request to save DqStandardDetailsEntityText : {}", dqStandardDetailsEntityTextDTO);
        DqStandardDetailsEntityText dqStandardDetailsEntityText = dqStandardDetailsEntityTextMapper.toEntity(dqStandardDetailsEntityTextDTO);
        dqStandardDetailsEntityText = dqStandardDetailsEntityTextRepository.save(dqStandardDetailsEntityText);
        return dqStandardDetailsEntityTextMapper.toDto(dqStandardDetailsEntityText);
    }

    /**
     * Get all the dqStandardDetailsEntityTexts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DqStandardDetailsEntityTextDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DqStandardDetailsEntityTexts");
        return dqStandardDetailsEntityTextRepository.findAll(pageable)
            .map(dqStandardDetailsEntityTextMapper::toDto);
    }

    /**
     * Get one dqStandardDetailsEntityText by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DqStandardDetailsEntityTextDTO> findOne(Long id) {
        log.debug("Request to get DqStandardDetailsEntityText : {}", id);
        return dqStandardDetailsEntityTextRepository.findById(id)
            .map(dqStandardDetailsEntityTextMapper::toDto);
    }

    /**
     * Delete the dqStandardDetailsEntityText by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DqStandardDetailsEntityText : {}", id);
        dqStandardDetailsEntityTextRepository.deleteById(id);
    }
}
