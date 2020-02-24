package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.DsTableTypesService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.DsTableTypesDTO;
import com.mycompany.myapp.service.dto.DsTableTypesCriteria;
import com.mycompany.myapp.service.DsTableTypesQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.DsTableTypes}.
 */
@RestController
@RequestMapping("/api")
public class DsTableTypesResource {

    private final Logger log = LoggerFactory.getLogger(DsTableTypesResource.class);

    private static final String ENTITY_NAME = "dsTableTypes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DsTableTypesService dsTableTypesService;

    private final DsTableTypesQueryService dsTableTypesQueryService;

    public DsTableTypesResource(DsTableTypesService dsTableTypesService, DsTableTypesQueryService dsTableTypesQueryService) {
        this.dsTableTypesService = dsTableTypesService;
        this.dsTableTypesQueryService = dsTableTypesQueryService;
    }

    /**
     * {@code POST  /ds-table-types} : Create a new dsTableTypes.
     *
     * @param dsTableTypesDTO the dsTableTypesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dsTableTypesDTO, or with status {@code 400 (Bad Request)} if the dsTableTypes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ds-table-types")
    public ResponseEntity<DsTableTypesDTO> createDsTableTypes(@RequestBody DsTableTypesDTO dsTableTypesDTO) throws URISyntaxException {
        log.debug("REST request to save DsTableTypes : {}", dsTableTypesDTO);
        if (dsTableTypesDTO.getId() != null) {
            throw new BadRequestAlertException("A new dsTableTypes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DsTableTypesDTO result = dsTableTypesService.save(dsTableTypesDTO);
        return ResponseEntity.created(new URI("/api/ds-table-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ds-table-types} : Updates an existing dsTableTypes.
     *
     * @param dsTableTypesDTO the dsTableTypesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dsTableTypesDTO,
     * or with status {@code 400 (Bad Request)} if the dsTableTypesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dsTableTypesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ds-table-types")
    public ResponseEntity<DsTableTypesDTO> updateDsTableTypes(@RequestBody DsTableTypesDTO dsTableTypesDTO) throws URISyntaxException {
        log.debug("REST request to update DsTableTypes : {}", dsTableTypesDTO);
        if (dsTableTypesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DsTableTypesDTO result = dsTableTypesService.save(dsTableTypesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dsTableTypesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ds-table-types} : get all the dsTableTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dsTableTypes in body.
     */
    @GetMapping("/ds-table-types")
    public ResponseEntity<List<DsTableTypesDTO>> getAllDsTableTypes(DsTableTypesCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DsTableTypes by criteria: {}", criteria);
        Page<DsTableTypesDTO> page = dsTableTypesQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ds-table-types/count} : count all the dsTableTypes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/ds-table-types/count")
    public ResponseEntity<Long> countDsTableTypes(DsTableTypesCriteria criteria) {
        log.debug("REST request to count DsTableTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(dsTableTypesQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /ds-table-types/:id} : get the "id" dsTableTypes.
     *
     * @param id the id of the dsTableTypesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dsTableTypesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ds-table-types/{id}")
    public ResponseEntity<DsTableTypesDTO> getDsTableTypes(@PathVariable Long id) {
        log.debug("REST request to get DsTableTypes : {}", id);
        Optional<DsTableTypesDTO> dsTableTypesDTO = dsTableTypesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dsTableTypesDTO);
    }

    /**
     * {@code DELETE  /ds-table-types/:id} : delete the "id" dsTableTypes.
     *
     * @param id the id of the dsTableTypesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ds-table-types/{id}")
    public ResponseEntity<Void> deleteDsTableTypes(@PathVariable Long id) {
        log.debug("REST request to delete DsTableTypes : {}", id);
        dsTableTypesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
