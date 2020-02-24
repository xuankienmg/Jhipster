package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.DqStandardDetailsEntityIntService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityIntDTO;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityIntCriteria;
import com.mycompany.myapp.service.DqStandardDetailsEntityIntQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.DqStandardDetailsEntityInt}.
 */
@RestController
@RequestMapping("/api")
public class DqStandardDetailsEntityIntResource {

    private final Logger log = LoggerFactory.getLogger(DqStandardDetailsEntityIntResource.class);

    private static final String ENTITY_NAME = "dqStandardDetailsEntityInt";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DqStandardDetailsEntityIntService dqStandardDetailsEntityIntService;

    private final DqStandardDetailsEntityIntQueryService dqStandardDetailsEntityIntQueryService;

    public DqStandardDetailsEntityIntResource(DqStandardDetailsEntityIntService dqStandardDetailsEntityIntService, DqStandardDetailsEntityIntQueryService dqStandardDetailsEntityIntQueryService) {
        this.dqStandardDetailsEntityIntService = dqStandardDetailsEntityIntService;
        this.dqStandardDetailsEntityIntQueryService = dqStandardDetailsEntityIntQueryService;
    }

    /**
     * {@code POST  /dq-standard-details-entity-ints} : Create a new dqStandardDetailsEntityInt.
     *
     * @param dqStandardDetailsEntityIntDTO the dqStandardDetailsEntityIntDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dqStandardDetailsEntityIntDTO, or with status {@code 400 (Bad Request)} if the dqStandardDetailsEntityInt has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dq-standard-details-entity-ints")
    public ResponseEntity<DqStandardDetailsEntityIntDTO> createDqStandardDetailsEntityInt(@RequestBody DqStandardDetailsEntityIntDTO dqStandardDetailsEntityIntDTO) throws URISyntaxException {
        log.debug("REST request to save DqStandardDetailsEntityInt : {}", dqStandardDetailsEntityIntDTO);
        if (dqStandardDetailsEntityIntDTO.getId() != null) {
            throw new BadRequestAlertException("A new dqStandardDetailsEntityInt cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DqStandardDetailsEntityIntDTO result = dqStandardDetailsEntityIntService.save(dqStandardDetailsEntityIntDTO);
        return ResponseEntity.created(new URI("/api/dq-standard-details-entity-ints/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dq-standard-details-entity-ints} : Updates an existing dqStandardDetailsEntityInt.
     *
     * @param dqStandardDetailsEntityIntDTO the dqStandardDetailsEntityIntDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dqStandardDetailsEntityIntDTO,
     * or with status {@code 400 (Bad Request)} if the dqStandardDetailsEntityIntDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dqStandardDetailsEntityIntDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dq-standard-details-entity-ints")
    public ResponseEntity<DqStandardDetailsEntityIntDTO> updateDqStandardDetailsEntityInt(@RequestBody DqStandardDetailsEntityIntDTO dqStandardDetailsEntityIntDTO) throws URISyntaxException {
        log.debug("REST request to update DqStandardDetailsEntityInt : {}", dqStandardDetailsEntityIntDTO);
        if (dqStandardDetailsEntityIntDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DqStandardDetailsEntityIntDTO result = dqStandardDetailsEntityIntService.save(dqStandardDetailsEntityIntDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dqStandardDetailsEntityIntDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dq-standard-details-entity-ints} : get all the dqStandardDetailsEntityInts.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dqStandardDetailsEntityInts in body.
     */
    @GetMapping("/dq-standard-details-entity-ints")
    public ResponseEntity<List<DqStandardDetailsEntityIntDTO>> getAllDqStandardDetailsEntityInts(DqStandardDetailsEntityIntCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DqStandardDetailsEntityInts by criteria: {}", criteria);
        Page<DqStandardDetailsEntityIntDTO> page = dqStandardDetailsEntityIntQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dq-standard-details-entity-ints/count} : count all the dqStandardDetailsEntityInts.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/dq-standard-details-entity-ints/count")
    public ResponseEntity<Long> countDqStandardDetailsEntityInts(DqStandardDetailsEntityIntCriteria criteria) {
        log.debug("REST request to count DqStandardDetailsEntityInts by criteria: {}", criteria);
        return ResponseEntity.ok().body(dqStandardDetailsEntityIntQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dq-standard-details-entity-ints/:id} : get the "id" dqStandardDetailsEntityInt.
     *
     * @param id the id of the dqStandardDetailsEntityIntDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dqStandardDetailsEntityIntDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dq-standard-details-entity-ints/{id}")
    public ResponseEntity<DqStandardDetailsEntityIntDTO> getDqStandardDetailsEntityInt(@PathVariable Long id) {
        log.debug("REST request to get DqStandardDetailsEntityInt : {}", id);
        Optional<DqStandardDetailsEntityIntDTO> dqStandardDetailsEntityIntDTO = dqStandardDetailsEntityIntService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dqStandardDetailsEntityIntDTO);
    }

    /**
     * {@code DELETE  /dq-standard-details-entity-ints/:id} : delete the "id" dqStandardDetailsEntityInt.
     *
     * @param id the id of the dqStandardDetailsEntityIntDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dq-standard-details-entity-ints/{id}")
    public ResponseEntity<Void> deleteDqStandardDetailsEntityInt(@PathVariable Long id) {
        log.debug("REST request to delete DqStandardDetailsEntityInt : {}", id);
        dqStandardDetailsEntityIntService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
