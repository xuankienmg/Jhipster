package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.DqStandardDetailsEntityVarcharService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityVarcharDTO;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityVarcharCriteria;
import com.mycompany.myapp.service.DqStandardDetailsEntityVarcharQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.DqStandardDetailsEntityVarchar}.
 */
@RestController
@RequestMapping("/api")
public class DqStandardDetailsEntityVarcharResource {

    private final Logger log = LoggerFactory.getLogger(DqStandardDetailsEntityVarcharResource.class);

    private static final String ENTITY_NAME = "dqStandardDetailsEntityVarchar";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DqStandardDetailsEntityVarcharService dqStandardDetailsEntityVarcharService;

    private final DqStandardDetailsEntityVarcharQueryService dqStandardDetailsEntityVarcharQueryService;

    public DqStandardDetailsEntityVarcharResource(DqStandardDetailsEntityVarcharService dqStandardDetailsEntityVarcharService, DqStandardDetailsEntityVarcharQueryService dqStandardDetailsEntityVarcharQueryService) {
        this.dqStandardDetailsEntityVarcharService = dqStandardDetailsEntityVarcharService;
        this.dqStandardDetailsEntityVarcharQueryService = dqStandardDetailsEntityVarcharQueryService;
    }

    /**
     * {@code POST  /dq-standard-details-entity-varchars} : Create a new dqStandardDetailsEntityVarchar.
     *
     * @param dqStandardDetailsEntityVarcharDTO the dqStandardDetailsEntityVarcharDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dqStandardDetailsEntityVarcharDTO, or with status {@code 400 (Bad Request)} if the dqStandardDetailsEntityVarchar has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dq-standard-details-entity-varchars")
    public ResponseEntity<DqStandardDetailsEntityVarcharDTO> createDqStandardDetailsEntityVarchar(@RequestBody DqStandardDetailsEntityVarcharDTO dqStandardDetailsEntityVarcharDTO) throws URISyntaxException {
        log.debug("REST request to save DqStandardDetailsEntityVarchar : {}", dqStandardDetailsEntityVarcharDTO);
        if (dqStandardDetailsEntityVarcharDTO.getId() != null) {
            throw new BadRequestAlertException("A new dqStandardDetailsEntityVarchar cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DqStandardDetailsEntityVarcharDTO result = dqStandardDetailsEntityVarcharService.save(dqStandardDetailsEntityVarcharDTO);
        return ResponseEntity.created(new URI("/api/dq-standard-details-entity-varchars/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dq-standard-details-entity-varchars} : Updates an existing dqStandardDetailsEntityVarchar.
     *
     * @param dqStandardDetailsEntityVarcharDTO the dqStandardDetailsEntityVarcharDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dqStandardDetailsEntityVarcharDTO,
     * or with status {@code 400 (Bad Request)} if the dqStandardDetailsEntityVarcharDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dqStandardDetailsEntityVarcharDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dq-standard-details-entity-varchars")
    public ResponseEntity<DqStandardDetailsEntityVarcharDTO> updateDqStandardDetailsEntityVarchar(@RequestBody DqStandardDetailsEntityVarcharDTO dqStandardDetailsEntityVarcharDTO) throws URISyntaxException {
        log.debug("REST request to update DqStandardDetailsEntityVarchar : {}", dqStandardDetailsEntityVarcharDTO);
        if (dqStandardDetailsEntityVarcharDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DqStandardDetailsEntityVarcharDTO result = dqStandardDetailsEntityVarcharService.save(dqStandardDetailsEntityVarcharDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dqStandardDetailsEntityVarcharDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dq-standard-details-entity-varchars} : get all the dqStandardDetailsEntityVarchars.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dqStandardDetailsEntityVarchars in body.
     */
    @GetMapping("/dq-standard-details-entity-varchars")
    public ResponseEntity<List<DqStandardDetailsEntityVarcharDTO>> getAllDqStandardDetailsEntityVarchars(DqStandardDetailsEntityVarcharCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DqStandardDetailsEntityVarchars by criteria: {}", criteria);
        Page<DqStandardDetailsEntityVarcharDTO> page = dqStandardDetailsEntityVarcharQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dq-standard-details-entity-varchars/count} : count all the dqStandardDetailsEntityVarchars.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/dq-standard-details-entity-varchars/count")
    public ResponseEntity<Long> countDqStandardDetailsEntityVarchars(DqStandardDetailsEntityVarcharCriteria criteria) {
        log.debug("REST request to count DqStandardDetailsEntityVarchars by criteria: {}", criteria);
        return ResponseEntity.ok().body(dqStandardDetailsEntityVarcharQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dq-standard-details-entity-varchars/:id} : get the "id" dqStandardDetailsEntityVarchar.
     *
     * @param id the id of the dqStandardDetailsEntityVarcharDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dqStandardDetailsEntityVarcharDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dq-standard-details-entity-varchars/{id}")
    public ResponseEntity<DqStandardDetailsEntityVarcharDTO> getDqStandardDetailsEntityVarchar(@PathVariable Long id) {
        log.debug("REST request to get DqStandardDetailsEntityVarchar : {}", id);
        Optional<DqStandardDetailsEntityVarcharDTO> dqStandardDetailsEntityVarcharDTO = dqStandardDetailsEntityVarcharService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dqStandardDetailsEntityVarcharDTO);
    }

    /**
     * {@code DELETE  /dq-standard-details-entity-varchars/:id} : delete the "id" dqStandardDetailsEntityVarchar.
     *
     * @param id the id of the dqStandardDetailsEntityVarcharDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dq-standard-details-entity-varchars/{id}")
    public ResponseEntity<Void> deleteDqStandardDetailsEntityVarchar(@PathVariable Long id) {
        log.debug("REST request to delete DqStandardDetailsEntityVarchar : {}", id);
        dqStandardDetailsEntityVarcharService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
