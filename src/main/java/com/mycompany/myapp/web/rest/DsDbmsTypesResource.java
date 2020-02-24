package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.DsDbmsTypesService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.DsDbmsTypesDTO;
import com.mycompany.myapp.service.dto.DsDbmsTypesCriteria;
import com.mycompany.myapp.service.DsDbmsTypesQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.DsDbmsTypes}.
 */
@RestController
@RequestMapping("/api")
public class DsDbmsTypesResource {

    private final Logger log = LoggerFactory.getLogger(DsDbmsTypesResource.class);

    private static final String ENTITY_NAME = "dsDbmsTypes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DsDbmsTypesService dsDbmsTypesService;

    private final DsDbmsTypesQueryService dsDbmsTypesQueryService;

    public DsDbmsTypesResource(DsDbmsTypesService dsDbmsTypesService, DsDbmsTypesQueryService dsDbmsTypesQueryService) {
        this.dsDbmsTypesService = dsDbmsTypesService;
        this.dsDbmsTypesQueryService = dsDbmsTypesQueryService;
    }

    /**
     * {@code POST  /ds-dbms-types} : Create a new dsDbmsTypes.
     *
     * @param dsDbmsTypesDTO the dsDbmsTypesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dsDbmsTypesDTO, or with status {@code 400 (Bad Request)} if the dsDbmsTypes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ds-dbms-types")
    public ResponseEntity<DsDbmsTypesDTO> createDsDbmsTypes(@RequestBody DsDbmsTypesDTO dsDbmsTypesDTO) throws URISyntaxException {
        log.debug("REST request to save DsDbmsTypes : {}", dsDbmsTypesDTO);
        if (dsDbmsTypesDTO.getId() != null) {
            throw new BadRequestAlertException("A new dsDbmsTypes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DsDbmsTypesDTO result = dsDbmsTypesService.save(dsDbmsTypesDTO);
        return ResponseEntity.created(new URI("/api/ds-dbms-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ds-dbms-types} : Updates an existing dsDbmsTypes.
     *
     * @param dsDbmsTypesDTO the dsDbmsTypesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dsDbmsTypesDTO,
     * or with status {@code 400 (Bad Request)} if the dsDbmsTypesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dsDbmsTypesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ds-dbms-types")
    public ResponseEntity<DsDbmsTypesDTO> updateDsDbmsTypes(@RequestBody DsDbmsTypesDTO dsDbmsTypesDTO) throws URISyntaxException {
        log.debug("REST request to update DsDbmsTypes : {}", dsDbmsTypesDTO);
        if (dsDbmsTypesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DsDbmsTypesDTO result = dsDbmsTypesService.save(dsDbmsTypesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dsDbmsTypesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ds-dbms-types} : get all the dsDbmsTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dsDbmsTypes in body.
     */
    @GetMapping("/ds-dbms-types")
    public ResponseEntity<List<DsDbmsTypesDTO>> getAllDsDbmsTypes(DsDbmsTypesCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DsDbmsTypes by criteria: {}", criteria);
        Page<DsDbmsTypesDTO> page = dsDbmsTypesQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ds-dbms-types/count} : count all the dsDbmsTypes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/ds-dbms-types/count")
    public ResponseEntity<Long> countDsDbmsTypes(DsDbmsTypesCriteria criteria) {
        log.debug("REST request to count DsDbmsTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(dsDbmsTypesQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /ds-dbms-types/:id} : get the "id" dsDbmsTypes.
     *
     * @param id the id of the dsDbmsTypesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dsDbmsTypesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ds-dbms-types/{id}")
    public ResponseEntity<DsDbmsTypesDTO> getDsDbmsTypes(@PathVariable Long id) {
        log.debug("REST request to get DsDbmsTypes : {}", id);
        Optional<DsDbmsTypesDTO> dsDbmsTypesDTO = dsDbmsTypesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dsDbmsTypesDTO);
    }

    /**
     * {@code DELETE  /ds-dbms-types/:id} : delete the "id" dsDbmsTypes.
     *
     * @param id the id of the dsDbmsTypesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ds-dbms-types/{id}")
    public ResponseEntity<Void> deleteDsDbmsTypes(@PathVariable Long id) {
        log.debug("REST request to delete DsDbmsTypes : {}", id);
        dsDbmsTypesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
