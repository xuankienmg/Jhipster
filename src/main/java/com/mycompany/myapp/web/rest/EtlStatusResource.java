package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.EtlStatusService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.EtlStatusDTO;
import com.mycompany.myapp.service.dto.EtlStatusCriteria;
import com.mycompany.myapp.service.EtlStatusQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.EtlStatus}.
 */
@RestController
@RequestMapping("/api")
public class EtlStatusResource {

    private final Logger log = LoggerFactory.getLogger(EtlStatusResource.class);

    private static final String ENTITY_NAME = "etlStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EtlStatusService etlStatusService;

    private final EtlStatusQueryService etlStatusQueryService;

    public EtlStatusResource(EtlStatusService etlStatusService, EtlStatusQueryService etlStatusQueryService) {
        this.etlStatusService = etlStatusService;
        this.etlStatusQueryService = etlStatusQueryService;
    }

    /**
     * {@code POST  /etl-statuses} : Create a new etlStatus.
     *
     * @param etlStatusDTO the etlStatusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new etlStatusDTO, or with status {@code 400 (Bad Request)} if the etlStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/etl-statuses")
    public ResponseEntity<EtlStatusDTO> createEtlStatus(@RequestBody EtlStatusDTO etlStatusDTO) throws URISyntaxException {
        log.debug("REST request to save EtlStatus : {}", etlStatusDTO);
        if (etlStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new etlStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EtlStatusDTO result = etlStatusService.save(etlStatusDTO);
        return ResponseEntity.created(new URI("/api/etl-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /etl-statuses} : Updates an existing etlStatus.
     *
     * @param etlStatusDTO the etlStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated etlStatusDTO,
     * or with status {@code 400 (Bad Request)} if the etlStatusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the etlStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/etl-statuses")
    public ResponseEntity<EtlStatusDTO> updateEtlStatus(@RequestBody EtlStatusDTO etlStatusDTO) throws URISyntaxException {
        log.debug("REST request to update EtlStatus : {}", etlStatusDTO);
        if (etlStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EtlStatusDTO result = etlStatusService.save(etlStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, etlStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /etl-statuses} : get all the etlStatuses.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of etlStatuses in body.
     */
    @GetMapping("/etl-statuses")
    public ResponseEntity<List<EtlStatusDTO>> getAllEtlStatuses(EtlStatusCriteria criteria, Pageable pageable) {
        log.debug("REST request to get EtlStatuses by criteria: {}", criteria);
        Page<EtlStatusDTO> page = etlStatusQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /etl-statuses/count} : count all the etlStatuses.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/etl-statuses/count")
    public ResponseEntity<Long> countEtlStatuses(EtlStatusCriteria criteria) {
        log.debug("REST request to count EtlStatuses by criteria: {}", criteria);
        return ResponseEntity.ok().body(etlStatusQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /etl-statuses/:id} : get the "id" etlStatus.
     *
     * @param id the id of the etlStatusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the etlStatusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/etl-statuses/{id}")
    public ResponseEntity<EtlStatusDTO> getEtlStatus(@PathVariable Long id) {
        log.debug("REST request to get EtlStatus : {}", id);
        Optional<EtlStatusDTO> etlStatusDTO = etlStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(etlStatusDTO);
    }

    /**
     * {@code DELETE  /etl-statuses/:id} : delete the "id" etlStatus.
     *
     * @param id the id of the etlStatusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/etl-statuses/{id}")
    public ResponseEntity<Void> deleteEtlStatus(@PathVariable Long id) {
        log.debug("REST request to delete EtlStatus : {}", id);
        etlStatusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
