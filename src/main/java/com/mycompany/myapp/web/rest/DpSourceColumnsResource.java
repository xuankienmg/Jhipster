package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.DpSourceColumnsService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.DpSourceColumnsDTO;
import com.mycompany.myapp.service.dto.DpSourceColumnsCriteria;
import com.mycompany.myapp.service.DpSourceColumnsQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.DpSourceColumns}.
 */
@RestController
@RequestMapping("/api")
public class DpSourceColumnsResource {

    private final Logger log = LoggerFactory.getLogger(DpSourceColumnsResource.class);

    private static final String ENTITY_NAME = "dpSourceColumns";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DpSourceColumnsService dpSourceColumnsService;

    private final DpSourceColumnsQueryService dpSourceColumnsQueryService;

    public DpSourceColumnsResource(DpSourceColumnsService dpSourceColumnsService, DpSourceColumnsQueryService dpSourceColumnsQueryService) {
        this.dpSourceColumnsService = dpSourceColumnsService;
        this.dpSourceColumnsQueryService = dpSourceColumnsQueryService;
    }

    /**
     * {@code POST  /dp-source-columns} : Create a new dpSourceColumns.
     *
     * @param dpSourceColumnsDTO the dpSourceColumnsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dpSourceColumnsDTO, or with status {@code 400 (Bad Request)} if the dpSourceColumns has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dp-source-columns")
    public ResponseEntity<DpSourceColumnsDTO> createDpSourceColumns(@RequestBody DpSourceColumnsDTO dpSourceColumnsDTO) throws URISyntaxException {
        log.debug("REST request to save DpSourceColumns : {}", dpSourceColumnsDTO);
        if (dpSourceColumnsDTO.getId() != null) {
            throw new BadRequestAlertException("A new dpSourceColumns cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DpSourceColumnsDTO result = dpSourceColumnsService.save(dpSourceColumnsDTO);
        return ResponseEntity.created(new URI("/api/dp-source-columns/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dp-source-columns} : Updates an existing dpSourceColumns.
     *
     * @param dpSourceColumnsDTO the dpSourceColumnsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dpSourceColumnsDTO,
     * or with status {@code 400 (Bad Request)} if the dpSourceColumnsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dpSourceColumnsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dp-source-columns")
    public ResponseEntity<DpSourceColumnsDTO> updateDpSourceColumns(@RequestBody DpSourceColumnsDTO dpSourceColumnsDTO) throws URISyntaxException {
        log.debug("REST request to update DpSourceColumns : {}", dpSourceColumnsDTO);
        if (dpSourceColumnsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DpSourceColumnsDTO result = dpSourceColumnsService.save(dpSourceColumnsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dpSourceColumnsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dp-source-columns} : get all the dpSourceColumns.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dpSourceColumns in body.
     */
    @GetMapping("/dp-source-columns")
    public ResponseEntity<List<DpSourceColumnsDTO>> getAllDpSourceColumns(DpSourceColumnsCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DpSourceColumns by criteria: {}", criteria);
        Page<DpSourceColumnsDTO> page = dpSourceColumnsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dp-source-columns/count} : count all the dpSourceColumns.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/dp-source-columns/count")
    public ResponseEntity<Long> countDpSourceColumns(DpSourceColumnsCriteria criteria) {
        log.debug("REST request to count DpSourceColumns by criteria: {}", criteria);
        return ResponseEntity.ok().body(dpSourceColumnsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dp-source-columns/:id} : get the "id" dpSourceColumns.
     *
     * @param id the id of the dpSourceColumnsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dpSourceColumnsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dp-source-columns/{id}")
    public ResponseEntity<DpSourceColumnsDTO> getDpSourceColumns(@PathVariable Long id) {
        log.debug("REST request to get DpSourceColumns : {}", id);
        Optional<DpSourceColumnsDTO> dpSourceColumnsDTO = dpSourceColumnsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dpSourceColumnsDTO);
    }

    /**
     * {@code DELETE  /dp-source-columns/:id} : delete the "id" dpSourceColumns.
     *
     * @param id the id of the dpSourceColumnsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dp-source-columns/{id}")
    public ResponseEntity<Void> deleteDpSourceColumns(@PathVariable Long id) {
        log.debug("REST request to delete DpSourceColumns : {}", id);
        dpSourceColumnsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
