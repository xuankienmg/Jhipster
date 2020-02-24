package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.DsTablesService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.DsTablesDTO;
import com.mycompany.myapp.service.dto.DsTablesCriteria;
import com.mycompany.myapp.service.DsTablesQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.DsTables}.
 */
@RestController
@RequestMapping("/api")
public class DsTablesResource {

    private final Logger log = LoggerFactory.getLogger(DsTablesResource.class);

    private static final String ENTITY_NAME = "dsTables";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DsTablesService dsTablesService;

    private final DsTablesQueryService dsTablesQueryService;

    public DsTablesResource(DsTablesService dsTablesService, DsTablesQueryService dsTablesQueryService) {
        this.dsTablesService = dsTablesService;
        this.dsTablesQueryService = dsTablesQueryService;
    }

    /**
     * {@code POST  /ds-tables} : Create a new dsTables.
     *
     * @param dsTablesDTO the dsTablesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dsTablesDTO, or with status {@code 400 (Bad Request)} if the dsTables has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ds-tables")
    public ResponseEntity<DsTablesDTO> createDsTables(@RequestBody DsTablesDTO dsTablesDTO) throws URISyntaxException {
        log.debug("REST request to save DsTables : {}", dsTablesDTO);
        if (dsTablesDTO.getId() != null) {
            throw new BadRequestAlertException("A new dsTables cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DsTablesDTO result = dsTablesService.save(dsTablesDTO);
        return ResponseEntity.created(new URI("/api/ds-tables/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ds-tables} : Updates an existing dsTables.
     *
     * @param dsTablesDTO the dsTablesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dsTablesDTO,
     * or with status {@code 400 (Bad Request)} if the dsTablesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dsTablesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ds-tables")
    public ResponseEntity<DsTablesDTO> updateDsTables(@RequestBody DsTablesDTO dsTablesDTO) throws URISyntaxException {
        log.debug("REST request to update DsTables : {}", dsTablesDTO);
        if (dsTablesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DsTablesDTO result = dsTablesService.save(dsTablesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dsTablesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ds-tables} : get all the dsTables.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dsTables in body.
     */
    @GetMapping("/ds-tables")
    public ResponseEntity<List<DsTablesDTO>> getAllDsTables(DsTablesCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DsTables by criteria: {}", criteria);
        Page<DsTablesDTO> page = dsTablesQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ds-tables/count} : count all the dsTables.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/ds-tables/count")
    public ResponseEntity<Long> countDsTables(DsTablesCriteria criteria) {
        log.debug("REST request to count DsTables by criteria: {}", criteria);
        return ResponseEntity.ok().body(dsTablesQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /ds-tables/:id} : get the "id" dsTables.
     *
     * @param id the id of the dsTablesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dsTablesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ds-tables/{id}")
    public ResponseEntity<DsTablesDTO> getDsTables(@PathVariable Long id) {
        log.debug("REST request to get DsTables : {}", id);
        Optional<DsTablesDTO> dsTablesDTO = dsTablesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dsTablesDTO);
    }

    /**
     * {@code DELETE  /ds-tables/:id} : delete the "id" dsTables.
     *
     * @param id the id of the dsTablesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ds-tables/{id}")
    public ResponseEntity<Void> deleteDsTables(@PathVariable Long id) {
        log.debug("REST request to delete DsTables : {}", id);
        dsTablesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
