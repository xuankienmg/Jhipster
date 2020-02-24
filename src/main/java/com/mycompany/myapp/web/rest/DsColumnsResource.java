package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.DsColumnsService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.DsColumnsDTO;
import com.mycompany.myapp.service.dto.DsColumnsCriteria;
import com.mycompany.myapp.service.DsColumnsQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.DsColumns}.
 */
@RestController
@RequestMapping("/api")
public class DsColumnsResource {

    private final Logger log = LoggerFactory.getLogger(DsColumnsResource.class);

    private static final String ENTITY_NAME = "dsColumns";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DsColumnsService dsColumnsService;

    private final DsColumnsQueryService dsColumnsQueryService;

    public DsColumnsResource(DsColumnsService dsColumnsService, DsColumnsQueryService dsColumnsQueryService) {
        this.dsColumnsService = dsColumnsService;
        this.dsColumnsQueryService = dsColumnsQueryService;
    }

    /**
     * {@code POST  /ds-columns} : Create a new dsColumns.
     *
     * @param dsColumnsDTO the dsColumnsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dsColumnsDTO, or with status {@code 400 (Bad Request)} if the dsColumns has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ds-columns")
    public ResponseEntity<DsColumnsDTO> createDsColumns(@RequestBody DsColumnsDTO dsColumnsDTO) throws URISyntaxException {
        log.debug("REST request to save DsColumns : {}", dsColumnsDTO);
        if (dsColumnsDTO.getId() != null) {
            throw new BadRequestAlertException("A new dsColumns cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DsColumnsDTO result = dsColumnsService.save(dsColumnsDTO);
        return ResponseEntity.created(new URI("/api/ds-columns/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ds-columns} : Updates an existing dsColumns.
     *
     * @param dsColumnsDTO the dsColumnsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dsColumnsDTO,
     * or with status {@code 400 (Bad Request)} if the dsColumnsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dsColumnsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ds-columns")
    public ResponseEntity<DsColumnsDTO> updateDsColumns(@RequestBody DsColumnsDTO dsColumnsDTO) throws URISyntaxException {
        log.debug("REST request to update DsColumns : {}", dsColumnsDTO);
        if (dsColumnsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DsColumnsDTO result = dsColumnsService.save(dsColumnsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dsColumnsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ds-columns} : get all the dsColumns.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dsColumns in body.
     */
    @GetMapping("/ds-columns")
    public ResponseEntity<List<DsColumnsDTO>> getAllDsColumns(DsColumnsCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DsColumns by criteria: {}", criteria);
        Page<DsColumnsDTO> page = dsColumnsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ds-columns/count} : count all the dsColumns.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/ds-columns/count")
    public ResponseEntity<Long> countDsColumns(DsColumnsCriteria criteria) {
        log.debug("REST request to count DsColumns by criteria: {}", criteria);
        return ResponseEntity.ok().body(dsColumnsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /ds-columns/:id} : get the "id" dsColumns.
     *
     * @param id the id of the dsColumnsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dsColumnsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ds-columns/{id}")
    public ResponseEntity<DsColumnsDTO> getDsColumns(@PathVariable Long id) {
        log.debug("REST request to get DsColumns : {}", id);
        Optional<DsColumnsDTO> dsColumnsDTO = dsColumnsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dsColumnsDTO);
    }

    /**
     * {@code DELETE  /ds-columns/:id} : delete the "id" dsColumns.
     *
     * @param id the id of the dsColumnsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ds-columns/{id}")
    public ResponseEntity<Void> deleteDsColumns(@PathVariable Long id) {
        log.debug("REST request to delete DsColumns : {}", id);
        dsColumnsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
