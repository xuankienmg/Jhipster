package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DqStandardDetailsEntityVarcharService;
import com.mycompany.myapp.domain.DqStandardDetailsEntityVarchar;
import com.mycompany.myapp.repository.DqStandardDetailsEntityVarcharRepository;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityVarcharDTO;
import com.mycompany.myapp.service.mapper.DqStandardDetailsEntityVarcharMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DqStandardDetailsEntityVarchar}.
 */
@Service
@Transactional
public class DqStandardDetailsEntityVarcharServiceImpl implements DqStandardDetailsEntityVarcharService {

    private final Logger log = LoggerFactory.getLogger(DqStandardDetailsEntityVarcharServiceImpl.class);

    private final DqStandardDetailsEntityVarcharRepository dqStandardDetailsEntityVarcharRepository;

    private final DqStandardDetailsEntityVarcharMapper dqStandardDetailsEntityVarcharMapper;

    public DqStandardDetailsEntityVarcharServiceImpl(DqStandardDetailsEntityVarcharRepository dqStandardDetailsEntityVarcharRepository, DqStandardDetailsEntityVarcharMapper dqStandardDetailsEntityVarcharMapper) {
        this.dqStandardDetailsEntityVarcharRepository = dqStandardDetailsEntityVarcharRepository;
        this.dqStandardDetailsEntityVarcharMapper = dqStandardDetailsEntityVarcharMapper;
    }

    /**
     * Save a dqStandardDetailsEntityVarchar.
     *
     * @param dqStandardDetailsEntityVarcharDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DqStandardDetailsEntityVarcharDTO save(DqStandardDetailsEntityVarcharDTO dqStandardDetailsEntityVarcharDTO) {
        log.debug("Request to save DqStandardDetailsEntityVarchar : {}", dqStandardDetailsEntityVarcharDTO);
        DqStandardDetailsEntityVarchar dqStandardDetailsEntityVarchar = dqStandardDetailsEntityVarcharMapper.toEntity(dqStandardDetailsEntityVarcharDTO);
        dqStandardDetailsEntityVarchar = dqStandardDetailsEntityVarcharRepository.save(dqStandardDetailsEntityVarchar);
        return dqStandardDetailsEntityVarcharMapper.toDto(dqStandardDetailsEntityVarchar);
    }

    /**
     * Get all the dqStandardDetailsEntityVarchars.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DqStandardDetailsEntityVarcharDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DqStandardDetailsEntityVarchars");
        return dqStandardDetailsEntityVarcharRepository.findAll(pageable)
            .map(dqStandardDetailsEntityVarcharMapper::toDto);
    }

    /**
     * Get one dqStandardDetailsEntityVarchar by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DqStandardDetailsEntityVarcharDTO> findOne(Long id) {
        log.debug("Request to get DqStandardDetailsEntityVarchar : {}", id);
        return dqStandardDetailsEntityVarcharRepository.findById(id)
            .map(dqStandardDetailsEntityVarcharMapper::toDto);
    }

    /**
     * Delete the dqStandardDetailsEntityVarchar by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DqStandardDetailsEntityVarchar : {}", id);
        dqStandardDetailsEntityVarcharRepository.deleteById(id);
    }
}
