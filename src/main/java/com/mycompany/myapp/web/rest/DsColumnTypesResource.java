package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.DsColumnTypesService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.DsColumnTypesDTO;
import com.mycompany.myapp.service.dto.DsColumnTypesCriteria;
import com.mycompany.myapp.service.DsColumnTypesQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.DsColumnTypes}.
 */
@RestController
@RequestMapping("/api")
public class DsColumnTypesResource {

    private final Logger log = LoggerFactory.getLogger(DsColumnTypesResource.class);

    private static final String ENTITY_NAME = "dsColumnTypes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DsColumnTypesService dsColumnTypesService;

    private final DsColumnTypesQueryService dsColumnTypesQueryService;

    public DsColumnTypesResource(DsColumnTypesService dsColumnTypesService, DsColumnTypesQueryService dsColumnTypesQueryService) {
        this.dsColumnTypesService = dsColumnTypesService;
        this.dsColumnTypesQueryService = dsColumnTypesQueryService;
    }

    /**
     * {@code POST  /ds-column-types} : Create a new dsColumnTypes.
     *
     * @param dsColumnTypesDTO the dsColumnTypesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dsColumnTypesDTO, or with status {@code 400 (Bad Request)} if the dsColumnTypes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ds-column-types")
    public ResponseEntity<DsColumnTypesDTO> createDsColumnTypes(@RequestBody DsColumnTypesDTO dsColumnTypesDTO) throws URISyntaxException {
        log.debug("REST request to save DsColumnTypes : {}", dsColumnTypesDTO);
        if (dsColumnTypesDTO.getId() != null) {
            throw new BadRequestAlertException("A new dsColumnTypes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DsColumnTypesDTO result = dsColumnTypesService.save(dsColumnTypesDTO);
        return ResponseEntity.created(new URI("/api/ds-column-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ds-column-types} : Updates an existing dsColumnTypes.
     *
     * @param dsColumnTypesDTO the dsColumnTypesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dsColumnTypesDTO,
     * or with status {@code 400 (Bad Request)} if the dsColumnTypesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dsColumnTypesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ds-column-types")
    public ResponseEntity<DsColumnTypesDTO> updateDsColumnTypes(@RequestBody DsColumnTypesDTO dsColumnTypesDTO) throws URISyntaxException {
        log.debug("REST request to update DsColumnTypes : {}", dsColumnTypesDTO);
        if (dsColumnTypesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DsColumnTypesDTO result = dsColumnTypesService.save(dsColumnTypesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dsColumnTypesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ds-column-types} : get all the dsColumnTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dsColumnTypes in body.
     */
    @GetMapping("/ds-column-types")
    public ResponseEntity<List<DsColumnTypesDTO>> getAllDsColumnTypes(DsColumnTypesCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DsColumnTypes by criteria: {}", criteria);
        Page<DsColumnTypesDTO> page = dsColumnTypesQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ds-column-types/count} : count all the dsColumnTypes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/ds-column-types/count")
    public ResponseEntity<Long> countDsColumnTypes(DsColumnTypesCriteria criteria) {
        log.debug("REST request to count DsColumnTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(dsColumnTypesQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /ds-column-types/:id} : get the "id" dsColumnTypes.
     *
     * @param id the id of the dsColumnTypesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dsColumnTypesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ds-column-types/{id}")
    public ResponseEntity<DsColumnTypesDTO> getDsColumnTypes(@PathVariable Long id) {
        log.debug("REST request to get DsColumnTypes : {}", id);
        Optional<DsColumnTypesDTO> dsColumnTypesDTO = dsColumnTypesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dsColumnTypesDTO);
    }

    /**
     * {@code DELETE  /ds-column-types/:id} : delete the "id" dsColumnTypes.
     *
     * @param id the id of the dsColumnTypesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ds-column-types/{id}")
    public ResponseEntity<Void> deleteDsColumnTypes(@PathVariable Long id) {
        log.debug("REST request to delete DsColumnTypes : {}", id);
        dsColumnTypesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
