package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.DqStandardDetailsEntityDatetimeService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityDatetimeDTO;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityDatetimeCriteria;
import com.mycompany.myapp.service.DqStandardDetailsEntityDatetimeQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.DqStandardDetailsEntityDatetime}.
 */
@RestController
@RequestMapping("/api")
public class DqStandardDetailsEntityDatetimeResource {

    private final Logger log = LoggerFactory.getLogger(DqStandardDetailsEntityDatetimeResource.class);

    private static final String ENTITY_NAME = "dqStandardDetailsEntityDatetime";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DqStandardDetailsEntityDatetimeService dqStandardDetailsEntityDatetimeService;

    private final DqStandardDetailsEntityDatetimeQueryService dqStandardDetailsEntityDatetimeQueryService;

    public DqStandardDetailsEntityDatetimeResource(DqStandardDetailsEntityDatetimeService dqStandardDetailsEntityDatetimeService, DqStandardDetailsEntityDatetimeQueryService dqStandardDetailsEntityDatetimeQueryService) {
        this.dqStandardDetailsEntityDatetimeService = dqStandardDetailsEntityDatetimeService;
        this.dqStandardDetailsEntityDatetimeQueryService = dqStandardDetailsEntityDatetimeQueryService;
    }

    /**
     * {@code POST  /dq-standard-details-entity-datetimes} : Create a new dqStandardDetailsEntityDatetime.
     *
     * @param dqStandardDetailsEntityDatetimeDTO the dqStandardDetailsEntityDatetimeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dqStandardDetailsEntityDatetimeDTO, or with status {@code 400 (Bad Request)} if the dqStandardDetailsEntityDatetime has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dq-standard-details-entity-datetimes")
    public ResponseEntity<DqStandardDetailsEntityDatetimeDTO> createDqStandardDetailsEntityDatetime(@RequestBody DqStandardDetailsEntityDatetimeDTO dqStandardDetailsEntityDatetimeDTO) throws URISyntaxException {
        log.debug("REST request to save DqStandardDetailsEntityDatetime : {}", dqStandardDetailsEntityDatetimeDTO);
        if (dqStandardDetailsEntityDatetimeDTO.getId() != null) {
            throw new BadRequestAlertException("A new dqStandardDetailsEntityDatetime cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DqStandardDetailsEntityDatetimeDTO result = dqStandardDetailsEntityDatetimeService.save(dqStandardDetailsEntityDatetimeDTO);
        return ResponseEntity.created(new URI("/api/dq-standard-details-entity-datetimes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dq-standard-details-entity-datetimes} : Updates an existing dqStandardDetailsEntityDatetime.
     *
     * @param dqStandardDetailsEntityDatetimeDTO the dqStandardDetailsEntityDatetimeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dqStandardDetailsEntityDatetimeDTO,
     * or with status {@code 400 (Bad Request)} if the dqStandardDetailsEntityDatetimeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dqStandardDetailsEntityDatetimeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dq-standard-details-entity-datetimes")
    public ResponseEntity<DqStandardDetailsEntityDatetimeDTO> updateDqStandardDetailsEntityDatetime(@RequestBody DqStandardDetailsEntityDatetimeDTO dqStandardDetailsEntityDatetimeDTO) throws URISyntaxException {
        log.debug("REST request to update DqStandardDetailsEntityDatetime : {}", dqStandardDetailsEntityDatetimeDTO);
        if (dqStandardDetailsEntityDatetimeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DqStandardDetailsEntityDatetimeDTO result = dqStandardDetailsEntityDatetimeService.save(dqStandardDetailsEntityDatetimeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dqStandardDetailsEntityDatetimeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dq-standard-details-entity-datetimes} : get all the dqStandardDetailsEntityDatetimes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dqStandardDetailsEntityDatetimes in body.
     */
    @GetMapping("/dq-standard-details-entity-datetimes")
    public ResponseEntity<List<DqStandardDetailsEntityDatetimeDTO>> getAllDqStandardDetailsEntityDatetimes(DqStandardDetailsEntityDatetimeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DqStandardDetailsEntityDatetimes by criteria: {}", criteria);
        Page<DqStandardDetailsEntityDatetimeDTO> page = dqStandardDetailsEntityDatetimeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dq-standard-details-entity-datetimes/count} : count all the dqStandardDetailsEntityDatetimes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/dq-standard-details-entity-datetimes/count")
    public ResponseEntity<Long> countDqStandardDetailsEntityDatetimes(DqStandardDetailsEntityDatetimeCriteria criteria) {
        log.debug("REST request to count DqStandardDetailsEntityDatetimes by criteria: {}", criteria);
        return ResponseEntity.ok().body(dqStandardDetailsEntityDatetimeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dq-standard-details-entity-datetimes/:id} : get the "id" dqStandardDetailsEntityDatetime.
     *
     * @param id the id of the dqStandardDetailsEntityDatetimeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dqStandardDetailsEntityDatetimeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dq-standard-details-entity-datetimes/{id}")
    public ResponseEntity<DqStandardDetailsEntityDatetimeDTO> getDqStandardDetailsEntityDatetime(@PathVariable Long id) {
        log.debug("REST request to get DqStandardDetailsEntityDatetime : {}", id);
        Optional<DqStandardDetailsEntityDatetimeDTO> dqStandardDetailsEntityDatetimeDTO = dqStandardDetailsEntityDatetimeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dqStandardDetailsEntityDatetimeDTO);
    }

    /**
     * {@code DELETE  /dq-standard-details-entity-datetimes/:id} : delete the "id" dqStandardDetailsEntityDatetime.
     *
     * @param id the id of the dqStandardDetailsEntityDatetimeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dq-standard-details-entity-datetimes/{id}")
    public ResponseEntity<Void> deleteDqStandardDetailsEntityDatetime(@PathVariable Long id) {
        log.debug("REST request to delete DqStandardDetailsEntityDatetime : {}", id);
        dqStandardDetailsEntityDatetimeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
