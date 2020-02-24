package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.DpSourceTablesService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.DpSourceTablesDTO;
import com.mycompany.myapp.service.dto.DpSourceTablesCriteria;
import com.mycompany.myapp.service.DpSourceTablesQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.DpSourceTables}.
 */
@RestController
@RequestMapping("/api")
public class DpSourceTablesResource {

    private final Logger log = LoggerFactory.getLogger(DpSourceTablesResource.class);

    private static final String ENTITY_NAME = "dpSourceTables";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DpSourceTablesService dpSourceTablesService;

    private final DpSourceTablesQueryService dpSourceTablesQueryService;

    public DpSourceTablesResource(DpSourceTablesService dpSourceTablesService, DpSourceTablesQueryService dpSourceTablesQueryService) {
        this.dpSourceTablesService = dpSourceTablesService;
        this.dpSourceTablesQueryService = dpSourceTablesQueryService;
    }

    /**
     * {@code POST  /dp-source-tables} : Create a new dpSourceTables.
     *
     * @param dpSourceTablesDTO the dpSourceTablesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dpSourceTablesDTO, or with status {@code 400 (Bad Request)} if the dpSourceTables has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dp-source-tables")
    public ResponseEntity<DpSourceTablesDTO> createDpSourceTables(@RequestBody DpSourceTablesDTO dpSourceTablesDTO) throws URISyntaxException {
        log.debug("REST request to save DpSourceTables : {}", dpSourceTablesDTO);
        if (dpSourceTablesDTO.getId() != null) {
            throw new BadRequestAlertException("A new dpSourceTables cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DpSourceTablesDTO result = dpSourceTablesService.save(dpSourceTablesDTO);
        return ResponseEntity.created(new URI("/api/dp-source-tables/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dp-source-tables} : Updates an existing dpSourceTables.
     *
     * @param dpSourceTablesDTO the dpSourceTablesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dpSourceTablesDTO,
     * or with status {@code 400 (Bad Request)} if the dpSourceTablesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dpSourceTablesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dp-source-tables")
    public ResponseEntity<DpSourceTablesDTO> updateDpSourceTables(@RequestBody DpSourceTablesDTO dpSourceTablesDTO) throws URISyntaxException {
        log.debug("REST request to update DpSourceTables : {}", dpSourceTablesDTO);
        if (dpSourceTablesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DpSourceTablesDTO result = dpSourceTablesService.save(dpSourceTablesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dpSourceTablesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dp-source-tables} : get all the dpSourceTables.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dpSourceTables in body.
     */
    @GetMapping("/dp-source-tables")
    public ResponseEntity<List<DpSourceTablesDTO>> getAllDpSourceTables(DpSourceTablesCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DpSourceTables by criteria: {}", criteria);
        Page<DpSourceTablesDTO> page = dpSourceTablesQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dp-source-tables/count} : count all the dpSourceTables.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/dp-source-tables/count")
    public ResponseEntity<Long> countDpSourceTables(DpSourceTablesCriteria criteria) {
        log.debug("REST request to count DpSourceTables by criteria: {}", criteria);
        return ResponseEntity.ok().body(dpSourceTablesQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dp-source-tables/:id} : get the "id" dpSourceTables.
     *
     * @param id the id of the dpSourceTablesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dpSourceTablesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dp-source-tables/{id}")
    public ResponseEntity<DpSourceTablesDTO> getDpSourceTables(@PathVariable Long id) {
        log.debug("REST request to get DpSourceTables : {}", id);
        Optional<DpSourceTablesDTO> dpSourceTablesDTO = dpSourceTablesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dpSourceTablesDTO);
    }

    /**
     * {@code DELETE  /dp-source-tables/:id} : delete the "id" dpSourceTables.
     *
     * @param id the id of the dpSourceTablesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dp-source-tables/{id}")
    public ResponseEntity<Void> deleteDpSourceTables(@PathVariable Long id) {
        log.debug("REST request to delete DpSourceTables : {}", id);
        dpSourceTablesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
