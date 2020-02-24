package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.DqStandardsService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.DqStandardsDTO;
import com.mycompany.myapp.service.dto.DqStandardsCriteria;
import com.mycompany.myapp.service.DqStandardsQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.DqStandards}.
 */
@RestController
@RequestMapping("/api")
public class DqStandardsResource {

    private final Logger log = LoggerFactory.getLogger(DqStandardsResource.class);

    private static final String ENTITY_NAME = "dqStandards";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DqStandardsService dqStandardsService;

    private final DqStandardsQueryService dqStandardsQueryService;

    public DqStandardsResource(DqStandardsService dqStandardsService, DqStandardsQueryService dqStandardsQueryService) {
        this.dqStandardsService = dqStandardsService;
        this.dqStandardsQueryService = dqStandardsQueryService;
    }

    /**
     * {@code POST  /dq-standards} : Create a new dqStandards.
     *
     * @param dqStandardsDTO the dqStandardsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dqStandardsDTO, or with status {@code 400 (Bad Request)} if the dqStandards has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dq-standards")
    public ResponseEntity<DqStandardsDTO> createDqStandards(@RequestBody DqStandardsDTO dqStandardsDTO) throws URISyntaxException {
        log.debug("REST request to save DqStandards : {}", dqStandardsDTO);
        if (dqStandardsDTO.getId() != null) {
            throw new BadRequestAlertException("A new dqStandards cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DqStandardsDTO result = dqStandardsService.save(dqStandardsDTO);
        return ResponseEntity.created(new URI("/api/dq-standards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dq-standards} : Updates an existing dqStandards.
     *
     * @param dqStandardsDTO the dqStandardsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dqStandardsDTO,
     * or with status {@code 400 (Bad Request)} if the dqStandardsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dqStandardsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dq-standards")
    public ResponseEntity<DqStandardsDTO> updateDqStandards(@RequestBody DqStandardsDTO dqStandardsDTO) throws URISyntaxException {
        log.debug("REST request to update DqStandards : {}", dqStandardsDTO);
        if (dqStandardsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DqStandardsDTO result = dqStandardsService.save(dqStandardsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dqStandardsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dq-standards} : get all the dqStandards.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dqStandards in body.
     */
    @GetMapping("/dq-standards")
    public ResponseEntity<List<DqStandardsDTO>> getAllDqStandards(DqStandardsCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DqStandards by criteria: {}", criteria);
        Page<DqStandardsDTO> page = dqStandardsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dq-standards/count} : count all the dqStandards.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/dq-standards/count")
    public ResponseEntity<Long> countDqStandards(DqStandardsCriteria criteria) {
        log.debug("REST request to count DqStandards by criteria: {}", criteria);
        return ResponseEntity.ok().body(dqStandardsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dq-standards/:id} : get the "id" dqStandards.
     *
     * @param id the id of the dqStandardsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dqStandardsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dq-standards/{id}")
    public ResponseEntity<DqStandardsDTO> getDqStandards(@PathVariable Long id) {
        log.debug("REST request to get DqStandards : {}", id);
        Optional<DqStandardsDTO> dqStandardsDTO = dqStandardsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dqStandardsDTO);
    }

    /**
     * {@code DELETE  /dq-standards/:id} : delete the "id" dqStandards.
     *
     * @param id the id of the dqStandardsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dq-standards/{id}")
    public ResponseEntity<Void> deleteDqStandards(@PathVariable Long id) {
        log.debug("REST request to delete DqStandards : {}", id);
        dqStandardsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
