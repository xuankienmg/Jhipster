package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.DsCollationsService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.DsCollationsDTO;
import com.mycompany.myapp.service.dto.DsCollationsCriteria;
import com.mycompany.myapp.service.DsCollationsQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.DsCollations}.
 */
@RestController
@RequestMapping("/api")
public class DsCollationsResource {

    private final Logger log = LoggerFactory.getLogger(DsCollationsResource.class);

    private static final String ENTITY_NAME = "dsCollations";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DsCollationsService dsCollationsService;

    private final DsCollationsQueryService dsCollationsQueryService;

    public DsCollationsResource(DsCollationsService dsCollationsService, DsCollationsQueryService dsCollationsQueryService) {
        this.dsCollationsService = dsCollationsService;
        this.dsCollationsQueryService = dsCollationsQueryService;
    }

    /**
     * {@code POST  /ds-collations} : Create a new dsCollations.
     *
     * @param dsCollationsDTO the dsCollationsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dsCollationsDTO, or with status {@code 400 (Bad Request)} if the dsCollations has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ds-collations")
    public ResponseEntity<DsCollationsDTO> createDsCollations(@RequestBody DsCollationsDTO dsCollationsDTO) throws URISyntaxException {
        log.debug("REST request to save DsCollations : {}", dsCollationsDTO);
        if (dsCollationsDTO.getId() != null) {
            throw new BadRequestAlertException("A new dsCollations cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DsCollationsDTO result = dsCollationsService.save(dsCollationsDTO);
        return ResponseEntity.created(new URI("/api/ds-collations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ds-collations} : Updates an existing dsCollations.
     *
     * @param dsCollationsDTO the dsCollationsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dsCollationsDTO,
     * or with status {@code 400 (Bad Request)} if the dsCollationsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dsCollationsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ds-collations")
    public ResponseEntity<DsCollationsDTO> updateDsCollations(@RequestBody DsCollationsDTO dsCollationsDTO) throws URISyntaxException {
        log.debug("REST request to update DsCollations : {}", dsCollationsDTO);
        if (dsCollationsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DsCollationsDTO result = dsCollationsService.save(dsCollationsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dsCollationsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ds-collations} : get all the dsCollations.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dsCollations in body.
     */
    @GetMapping("/ds-collations")
    public ResponseEntity<List<DsCollationsDTO>> getAllDsCollations(DsCollationsCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DsCollations by criteria: {}", criteria);
        Page<DsCollationsDTO> page = dsCollationsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ds-collations/count} : count all the dsCollations.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/ds-collations/count")
    public ResponseEntity<Long> countDsCollations(DsCollationsCriteria criteria) {
        log.debug("REST request to count DsCollations by criteria: {}", criteria);
        return ResponseEntity.ok().body(dsCollationsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /ds-collations/:id} : get the "id" dsCollations.
     *
     * @param id the id of the dsCollationsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dsCollationsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ds-collations/{id}")
    public ResponseEntity<DsCollationsDTO> getDsCollations(@PathVariable Long id) {
        log.debug("REST request to get DsCollations : {}", id);
        Optional<DsCollationsDTO> dsCollationsDTO = dsCollationsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dsCollationsDTO);
    }

    /**
     * {@code DELETE  /ds-collations/:id} : delete the "id" dsCollations.
     *
     * @param id the id of the dsCollationsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ds-collations/{id}")
    public ResponseEntity<Void> deleteDsCollations(@PathVariable Long id) {
        log.debug("REST request to delete DsCollations : {}", id);
        dsCollationsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
