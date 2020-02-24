package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.DqStandardDetailsEntityTimeService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityTimeDTO;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityTimeCriteria;
import com.mycompany.myapp.service.DqStandardDetailsEntityTimeQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.DqStandardDetailsEntityTime}.
 */
@RestController
@RequestMapping("/api")
public class DqStandardDetailsEntityTimeResource {

    private final Logger log = LoggerFactory.getLogger(DqStandardDetailsEntityTimeResource.class);

    private static final String ENTITY_NAME = "dqStandardDetailsEntityTime";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DqStandardDetailsEntityTimeService dqStandardDetailsEntityTimeService;

    private final DqStandardDetailsEntityTimeQueryService dqStandardDetailsEntityTimeQueryService;

    public DqStandardDetailsEntityTimeResource(DqStandardDetailsEntityTimeService dqStandardDetailsEntityTimeService, DqStandardDetailsEntityTimeQueryService dqStandardDetailsEntityTimeQueryService) {
        this.dqStandardDetailsEntityTimeService = dqStandardDetailsEntityTimeService;
        this.dqStandardDetailsEntityTimeQueryService = dqStandardDetailsEntityTimeQueryService;
    }

    /**
     * {@code POST  /dq-standard-details-entity-times} : Create a new dqStandardDetailsEntityTime.
     *
     * @param dqStandardDetailsEntityTimeDTO the dqStandardDetailsEntityTimeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dqStandardDetailsEntityTimeDTO, or with status {@code 400 (Bad Request)} if the dqStandardDetailsEntityTime has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dq-standard-details-entity-times")
    public ResponseEntity<DqStandardDetailsEntityTimeDTO> createDqStandardDetailsEntityTime(@RequestBody DqStandardDetailsEntityTimeDTO dqStandardDetailsEntityTimeDTO) throws URISyntaxException {
        log.debug("REST request to save DqStandardDetailsEntityTime : {}", dqStandardDetailsEntityTimeDTO);
        if (dqStandardDetailsEntityTimeDTO.getId() != null) {
            throw new BadRequestAlertException("A new dqStandardDetailsEntityTime cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DqStandardDetailsEntityTimeDTO result = dqStandardDetailsEntityTimeService.save(dqStandardDetailsEntityTimeDTO);
        return ResponseEntity.created(new URI("/api/dq-standard-details-entity-times/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dq-standard-details-entity-times} : Updates an existing dqStandardDetailsEntityTime.
     *
     * @param dqStandardDetailsEntityTimeDTO the dqStandardDetailsEntityTimeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dqStandardDetailsEntityTimeDTO,
     * or with status {@code 400 (Bad Request)} if the dqStandardDetailsEntityTimeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dqStandardDetailsEntityTimeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dq-standard-details-entity-times")
    public ResponseEntity<DqStandardDetailsEntityTimeDTO> updateDqStandardDetailsEntityTime(@RequestBody DqStandardDetailsEntityTimeDTO dqStandardDetailsEntityTimeDTO) throws URISyntaxException {
        log.debug("REST request to update DqStandardDetailsEntityTime : {}", dqStandardDetailsEntityTimeDTO);
        if (dqStandardDetailsEntityTimeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DqStandardDetailsEntityTimeDTO result = dqStandardDetailsEntityTimeService.save(dqStandardDetailsEntityTimeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dqStandardDetailsEntityTimeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dq-standard-details-entity-times} : get all the dqStandardDetailsEntityTimes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dqStandardDetailsEntityTimes in body.
     */
    @GetMapping("/dq-standard-details-entity-times")
    public ResponseEntity<List<DqStandardDetailsEntityTimeDTO>> getAllDqStandardDetailsEntityTimes(DqStandardDetailsEntityTimeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DqStandardDetailsEntityTimes by criteria: {}", criteria);
        Page<DqStandardDetailsEntityTimeDTO> page = dqStandardDetailsEntityTimeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dq-standard-details-entity-times/count} : count all the dqStandardDetailsEntityTimes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/dq-standard-details-entity-times/count")
    public ResponseEntity<Long> countDqStandardDetailsEntityTimes(DqStandardDetailsEntityTimeCriteria criteria) {
        log.debug("REST request to count DqStandardDetailsEntityTimes by criteria: {}", criteria);
        return ResponseEntity.ok().body(dqStandardDetailsEntityTimeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dq-standard-details-entity-times/:id} : get the "id" dqStandardDetailsEntityTime.
     *
     * @param id the id of the dqStandardDetailsEntityTimeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dqStandardDetailsEntityTimeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dq-standard-details-entity-times/{id}")
    public ResponseEntity<DqStandardDetailsEntityTimeDTO> getDqStandardDetailsEntityTime(@PathVariable Long id) {
        log.debug("REST request to get DqStandardDetailsEntityTime : {}", id);
        Optional<DqStandardDetailsEntityTimeDTO> dqStandardDetailsEntityTimeDTO = dqStandardDetailsEntityTimeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dqStandardDetailsEntityTimeDTO);
    }

    /**
     * {@code DELETE  /dq-standard-details-entity-times/:id} : delete the "id" dqStandardDetailsEntityTime.
     *
     * @param id the id of the dqStandardDetailsEntityTimeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dq-standard-details-entity-times/{id}")
    public ResponseEntity<Void> deleteDqStandardDetailsEntityTime(@PathVariable Long id) {
        log.debug("REST request to delete DqStandardDetailsEntityTime : {}", id);
        dqStandardDetailsEntityTimeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
