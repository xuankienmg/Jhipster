package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.DqStandardTypesService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.DqStandardTypesDTO;
import com.mycompany.myapp.service.dto.DqStandardTypesCriteria;
import com.mycompany.myapp.service.DqStandardTypesQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.DqStandardTypes}.
 */
@RestController
@RequestMapping("/api")
public class DqStandardTypesResource {

    private final Logger log = LoggerFactory.getLogger(DqStandardTypesResource.class);

    private static final String ENTITY_NAME = "dqStandardTypes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DqStandardTypesService dqStandardTypesService;

    private final DqStandardTypesQueryService dqStandardTypesQueryService;

    public DqStandardTypesResource(DqStandardTypesService dqStandardTypesService, DqStandardTypesQueryService dqStandardTypesQueryService) {
        this.dqStandardTypesService = dqStandardTypesService;
        this.dqStandardTypesQueryService = dqStandardTypesQueryService;
    }

    /**
     * {@code POST  /dq-standard-types} : Create a new dqStandardTypes.
     *
     * @param dqStandardTypesDTO the dqStandardTypesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dqStandardTypesDTO, or with status {@code 400 (Bad Request)} if the dqStandardTypes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dq-standard-types")
    public ResponseEntity<DqStandardTypesDTO> createDqStandardTypes(@RequestBody DqStandardTypesDTO dqStandardTypesDTO) throws URISyntaxException {
        log.debug("REST request to save DqStandardTypes : {}", dqStandardTypesDTO);
        if (dqStandardTypesDTO.getId() != null) {
            throw new BadRequestAlertException("A new dqStandardTypes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DqStandardTypesDTO result = dqStandardTypesService.save(dqStandardTypesDTO);
        return ResponseEntity.created(new URI("/api/dq-standard-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dq-standard-types} : Updates an existing dqStandardTypes.
     *
     * @param dqStandardTypesDTO the dqStandardTypesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dqStandardTypesDTO,
     * or with status {@code 400 (Bad Request)} if the dqStandardTypesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dqStandardTypesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dq-standard-types")
    public ResponseEntity<DqStandardTypesDTO> updateDqStandardTypes(@RequestBody DqStandardTypesDTO dqStandardTypesDTO) throws URISyntaxException {
        log.debug("REST request to update DqStandardTypes : {}", dqStandardTypesDTO);
        if (dqStandardTypesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DqStandardTypesDTO result = dqStandardTypesService.save(dqStandardTypesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dqStandardTypesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dq-standard-types} : get all the dqStandardTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dqStandardTypes in body.
     */
    @GetMapping("/dq-standard-types")
    public ResponseEntity<List<DqStandardTypesDTO>> getAllDqStandardTypes(DqStandardTypesCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DqStandardTypes by criteria: {}", criteria);
        Page<DqStandardTypesDTO> page = dqStandardTypesQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dq-standard-types/count} : count all the dqStandardTypes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/dq-standard-types/count")
    public ResponseEntity<Long> countDqStandardTypes(DqStandardTypesCriteria criteria) {
        log.debug("REST request to count DqStandardTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(dqStandardTypesQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dq-standard-types/:id} : get the "id" dqStandardTypes.
     *
     * @param id the id of the dqStandardTypesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dqStandardTypesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dq-standard-types/{id}")
    public ResponseEntity<DqStandardTypesDTO> getDqStandardTypes(@PathVariable Long id) {
        log.debug("REST request to get DqStandardTypes : {}", id);
        Optional<DqStandardTypesDTO> dqStandardTypesDTO = dqStandardTypesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dqStandardTypesDTO);
    }

    /**
     * {@code DELETE  /dq-standard-types/:id} : delete the "id" dqStandardTypes.
     *
     * @param id the id of the dqStandardTypesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dq-standard-types/{id}")
    public ResponseEntity<Void> deleteDqStandardTypes(@PathVariable Long id) {
        log.debug("REST request to delete DqStandardTypes : {}", id);
        dqStandardTypesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
