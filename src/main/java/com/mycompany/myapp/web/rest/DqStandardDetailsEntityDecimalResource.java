package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.DqStandardDetailsEntityDecimalService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityDecimalDTO;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityDecimalCriteria;
import com.mycompany.myapp.service.DqStandardDetailsEntityDecimalQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.DqStandardDetailsEntityDecimal}.
 */
@RestController
@RequestMapping("/api")
public class DqStandardDetailsEntityDecimalResource {

    private final Logger log = LoggerFactory.getLogger(DqStandardDetailsEntityDecimalResource.class);

    private static final String ENTITY_NAME = "dqStandardDetailsEntityDecimal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DqStandardDetailsEntityDecimalService dqStandardDetailsEntityDecimalService;

    private final DqStandardDetailsEntityDecimalQueryService dqStandardDetailsEntityDecimalQueryService;

    public DqStandardDetailsEntityDecimalResource(DqStandardDetailsEntityDecimalService dqStandardDetailsEntityDecimalService, DqStandardDetailsEntityDecimalQueryService dqStandardDetailsEntityDecimalQueryService) {
        this.dqStandardDetailsEntityDecimalService = dqStandardDetailsEntityDecimalService;
        this.dqStandardDetailsEntityDecimalQueryService = dqStandardDetailsEntityDecimalQueryService;
    }

    /**
     * {@code POST  /dq-standard-details-entity-decimals} : Create a new dqStandardDetailsEntityDecimal.
     *
     * @param dqStandardDetailsEntityDecimalDTO the dqStandardDetailsEntityDecimalDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dqStandardDetailsEntityDecimalDTO, or with status {@code 400 (Bad Request)} if the dqStandardDetailsEntityDecimal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dq-standard-details-entity-decimals")
    public ResponseEntity<DqStandardDetailsEntityDecimalDTO> createDqStandardDetailsEntityDecimal(@RequestBody DqStandardDetailsEntityDecimalDTO dqStandardDetailsEntityDecimalDTO) throws URISyntaxException {
        log.debug("REST request to save DqStandardDetailsEntityDecimal : {}", dqStandardDetailsEntityDecimalDTO);
        if (dqStandardDetailsEntityDecimalDTO.getId() != null) {
            throw new BadRequestAlertException("A new dqStandardDetailsEntityDecimal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DqStandardDetailsEntityDecimalDTO result = dqStandardDetailsEntityDecimalService.save(dqStandardDetailsEntityDecimalDTO);
        return ResponseEntity.created(new URI("/api/dq-standard-details-entity-decimals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dq-standard-details-entity-decimals} : Updates an existing dqStandardDetailsEntityDecimal.
     *
     * @param dqStandardDetailsEntityDecimalDTO the dqStandardDetailsEntityDecimalDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dqStandardDetailsEntityDecimalDTO,
     * or with status {@code 400 (Bad Request)} if the dqStandardDetailsEntityDecimalDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dqStandardDetailsEntityDecimalDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dq-standard-details-entity-decimals")
    public ResponseEntity<DqStandardDetailsEntityDecimalDTO> updateDqStandardDetailsEntityDecimal(@RequestBody DqStandardDetailsEntityDecimalDTO dqStandardDetailsEntityDecimalDTO) throws URISyntaxException {
        log.debug("REST request to update DqStandardDetailsEntityDecimal : {}", dqStandardDetailsEntityDecimalDTO);
        if (dqStandardDetailsEntityDecimalDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DqStandardDetailsEntityDecimalDTO result = dqStandardDetailsEntityDecimalService.save(dqStandardDetailsEntityDecimalDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dqStandardDetailsEntityDecimalDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dq-standard-details-entity-decimals} : get all the dqStandardDetailsEntityDecimals.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dqStandardDetailsEntityDecimals in body.
     */
    @GetMapping("/dq-standard-details-entity-decimals")
    public ResponseEntity<List<DqStandardDetailsEntityDecimalDTO>> getAllDqStandardDetailsEntityDecimals(DqStandardDetailsEntityDecimalCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DqStandardDetailsEntityDecimals by criteria: {}", criteria);
        Page<DqStandardDetailsEntityDecimalDTO> page = dqStandardDetailsEntityDecimalQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dq-standard-details-entity-decimals/count} : count all the dqStandardDetailsEntityDecimals.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/dq-standard-details-entity-decimals/count")
    public ResponseEntity<Long> countDqStandardDetailsEntityDecimals(DqStandardDetailsEntityDecimalCriteria criteria) {
        log.debug("REST request to count DqStandardDetailsEntityDecimals by criteria: {}", criteria);
        return ResponseEntity.ok().body(dqStandardDetailsEntityDecimalQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dq-standard-details-entity-decimals/:id} : get the "id" dqStandardDetailsEntityDecimal.
     *
     * @param id the id of the dqStandardDetailsEntityDecimalDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dqStandardDetailsEntityDecimalDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dq-standard-details-entity-decimals/{id}")
    public ResponseEntity<DqStandardDetailsEntityDecimalDTO> getDqStandardDetailsEntityDecimal(@PathVariable Long id) {
        log.debug("REST request to get DqStandardDetailsEntityDecimal : {}", id);
        Optional<DqStandardDetailsEntityDecimalDTO> dqStandardDetailsEntityDecimalDTO = dqStandardDetailsEntityDecimalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dqStandardDetailsEntityDecimalDTO);
    }

    /**
     * {@code DELETE  /dq-standard-details-entity-decimals/:id} : delete the "id" dqStandardDetailsEntityDecimal.
     *
     * @param id the id of the dqStandardDetailsEntityDecimalDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dq-standard-details-entity-decimals/{id}")
    public ResponseEntity<Void> deleteDqStandardDetailsEntityDecimal(@PathVariable Long id) {
        log.debug("REST request to delete DqStandardDetailsEntityDecimal : {}", id);
        dqStandardDetailsEntityDecimalService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
