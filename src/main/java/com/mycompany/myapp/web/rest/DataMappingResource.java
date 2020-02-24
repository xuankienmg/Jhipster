package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.DataMappingService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.DataMappingDTO;
import com.mycompany.myapp.service.dto.DataMappingCriteria;
import com.mycompany.myapp.service.DataMappingQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.DataMapping}.
 */
@RestController
@RequestMapping("/api")
public class DataMappingResource {

    private final Logger log = LoggerFactory.getLogger(DataMappingResource.class);

    private static final String ENTITY_NAME = "dataMapping";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DataMappingService dataMappingService;

    private final DataMappingQueryService dataMappingQueryService;

    public DataMappingResource(DataMappingService dataMappingService, DataMappingQueryService dataMappingQueryService) {
        this.dataMappingService = dataMappingService;
        this.dataMappingQueryService = dataMappingQueryService;
    }

    /**
     * {@code POST  /data-mappings} : Create a new dataMapping.
     *
     * @param dataMappingDTO the dataMappingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dataMappingDTO, or with status {@code 400 (Bad Request)} if the dataMapping has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/data-mappings")
    public ResponseEntity<DataMappingDTO> createDataMapping(@RequestBody DataMappingDTO dataMappingDTO) throws URISyntaxException {
        log.debug("REST request to save DataMapping : {}", dataMappingDTO);
        if (dataMappingDTO.getId() != null) {
            throw new BadRequestAlertException("A new dataMapping cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DataMappingDTO result = dataMappingService.save(dataMappingDTO);
        return ResponseEntity.created(new URI("/api/data-mappings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /data-mappings} : Updates an existing dataMapping.
     *
     * @param dataMappingDTO the dataMappingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dataMappingDTO,
     * or with status {@code 400 (Bad Request)} if the dataMappingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dataMappingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/data-mappings")
    public ResponseEntity<DataMappingDTO> updateDataMapping(@RequestBody DataMappingDTO dataMappingDTO) throws URISyntaxException {
        log.debug("REST request to update DataMapping : {}", dataMappingDTO);
        if (dataMappingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DataMappingDTO result = dataMappingService.save(dataMappingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dataMappingDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /data-mappings} : get all the dataMappings.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dataMappings in body.
     */
    @GetMapping("/data-mappings")
    public ResponseEntity<List<DataMappingDTO>> getAllDataMappings(DataMappingCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DataMappings by criteria: {}", criteria);
        Page<DataMappingDTO> page = dataMappingQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /data-mappings/count} : count all the dataMappings.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/data-mappings/count")
    public ResponseEntity<Long> countDataMappings(DataMappingCriteria criteria) {
        log.debug("REST request to count DataMappings by criteria: {}", criteria);
        return ResponseEntity.ok().body(dataMappingQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /data-mappings/:id} : get the "id" dataMapping.
     *
     * @param id the id of the dataMappingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dataMappingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/data-mappings/{id}")
    public ResponseEntity<DataMappingDTO> getDataMapping(@PathVariable Long id) {
        log.debug("REST request to get DataMapping : {}", id);
        Optional<DataMappingDTO> dataMappingDTO = dataMappingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dataMappingDTO);
    }

    /**
     * {@code DELETE  /data-mappings/:id} : delete the "id" dataMapping.
     *
     * @param id the id of the dataMappingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/data-mappings/{id}")
    public ResponseEntity<Void> deleteDataMapping(@PathVariable Long id) {
        log.debug("REST request to delete DataMapping : {}", id);
        dataMappingService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
