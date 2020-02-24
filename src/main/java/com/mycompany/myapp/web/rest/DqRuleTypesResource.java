package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.DqRuleTypesService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.DqRuleTypesDTO;
import com.mycompany.myapp.service.dto.DqRuleTypesCriteria;
import com.mycompany.myapp.service.DqRuleTypesQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.DqRuleTypes}.
 */
@RestController
@RequestMapping("/api")
public class DqRuleTypesResource {

    private final Logger log = LoggerFactory.getLogger(DqRuleTypesResource.class);

    private static final String ENTITY_NAME = "dqRuleTypes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DqRuleTypesService dqRuleTypesService;

    private final DqRuleTypesQueryService dqRuleTypesQueryService;

    public DqRuleTypesResource(DqRuleTypesService dqRuleTypesService, DqRuleTypesQueryService dqRuleTypesQueryService) {
        this.dqRuleTypesService = dqRuleTypesService;
        this.dqRuleTypesQueryService = dqRuleTypesQueryService;
    }

    /**
     * {@code POST  /dq-rule-types} : Create a new dqRuleTypes.
     *
     * @param dqRuleTypesDTO the dqRuleTypesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dqRuleTypesDTO, or with status {@code 400 (Bad Request)} if the dqRuleTypes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dq-rule-types")
    public ResponseEntity<DqRuleTypesDTO> createDqRuleTypes(@RequestBody DqRuleTypesDTO dqRuleTypesDTO) throws URISyntaxException {
        log.debug("REST request to save DqRuleTypes : {}", dqRuleTypesDTO);
        if (dqRuleTypesDTO.getId() != null) {
            throw new BadRequestAlertException("A new dqRuleTypes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DqRuleTypesDTO result = dqRuleTypesService.save(dqRuleTypesDTO);
        return ResponseEntity.created(new URI("/api/dq-rule-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dq-rule-types} : Updates an existing dqRuleTypes.
     *
     * @param dqRuleTypesDTO the dqRuleTypesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dqRuleTypesDTO,
     * or with status {@code 400 (Bad Request)} if the dqRuleTypesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dqRuleTypesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dq-rule-types")
    public ResponseEntity<DqRuleTypesDTO> updateDqRuleTypes(@RequestBody DqRuleTypesDTO dqRuleTypesDTO) throws URISyntaxException {
        log.debug("REST request to update DqRuleTypes : {}", dqRuleTypesDTO);
        if (dqRuleTypesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DqRuleTypesDTO result = dqRuleTypesService.save(dqRuleTypesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dqRuleTypesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dq-rule-types} : get all the dqRuleTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dqRuleTypes in body.
     */
    @GetMapping("/dq-rule-types")
    public ResponseEntity<List<DqRuleTypesDTO>> getAllDqRuleTypes(DqRuleTypesCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DqRuleTypes by criteria: {}", criteria);
        Page<DqRuleTypesDTO> page = dqRuleTypesQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dq-rule-types/count} : count all the dqRuleTypes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/dq-rule-types/count")
    public ResponseEntity<Long> countDqRuleTypes(DqRuleTypesCriteria criteria) {
        log.debug("REST request to count DqRuleTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(dqRuleTypesQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dq-rule-types/:id} : get the "id" dqRuleTypes.
     *
     * @param id the id of the dqRuleTypesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dqRuleTypesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dq-rule-types/{id}")
    public ResponseEntity<DqRuleTypesDTO> getDqRuleTypes(@PathVariable Long id) {
        log.debug("REST request to get DqRuleTypes : {}", id);
        Optional<DqRuleTypesDTO> dqRuleTypesDTO = dqRuleTypesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dqRuleTypesDTO);
    }

    /**
     * {@code DELETE  /dq-rule-types/:id} : delete the "id" dqRuleTypes.
     *
     * @param id the id of the dqRuleTypesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dq-rule-types/{id}")
    public ResponseEntity<Void> deleteDqRuleTypes(@PathVariable Long id) {
        log.debug("REST request to delete DqRuleTypes : {}", id);
        dqRuleTypesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
