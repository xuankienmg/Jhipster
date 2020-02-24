package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.DqRuleActionsService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.DqRuleActionsDTO;
import com.mycompany.myapp.service.dto.DqRuleActionsCriteria;
import com.mycompany.myapp.service.DqRuleActionsQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.DqRuleActions}.
 */
@RestController
@RequestMapping("/api")
public class DqRuleActionsResource {

    private final Logger log = LoggerFactory.getLogger(DqRuleActionsResource.class);

    private static final String ENTITY_NAME = "dqRuleActions";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DqRuleActionsService dqRuleActionsService;

    private final DqRuleActionsQueryService dqRuleActionsQueryService;

    public DqRuleActionsResource(DqRuleActionsService dqRuleActionsService, DqRuleActionsQueryService dqRuleActionsQueryService) {
        this.dqRuleActionsService = dqRuleActionsService;
        this.dqRuleActionsQueryService = dqRuleActionsQueryService;
    }

    /**
     * {@code POST  /dq-rule-actions} : Create a new dqRuleActions.
     *
     * @param dqRuleActionsDTO the dqRuleActionsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dqRuleActionsDTO, or with status {@code 400 (Bad Request)} if the dqRuleActions has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dq-rule-actions")
    public ResponseEntity<DqRuleActionsDTO> createDqRuleActions(@RequestBody DqRuleActionsDTO dqRuleActionsDTO) throws URISyntaxException {
        log.debug("REST request to save DqRuleActions : {}", dqRuleActionsDTO);
        if (dqRuleActionsDTO.getId() != null) {
            throw new BadRequestAlertException("A new dqRuleActions cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DqRuleActionsDTO result = dqRuleActionsService.save(dqRuleActionsDTO);
        return ResponseEntity.created(new URI("/api/dq-rule-actions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dq-rule-actions} : Updates an existing dqRuleActions.
     *
     * @param dqRuleActionsDTO the dqRuleActionsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dqRuleActionsDTO,
     * or with status {@code 400 (Bad Request)} if the dqRuleActionsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dqRuleActionsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dq-rule-actions")
    public ResponseEntity<DqRuleActionsDTO> updateDqRuleActions(@RequestBody DqRuleActionsDTO dqRuleActionsDTO) throws URISyntaxException {
        log.debug("REST request to update DqRuleActions : {}", dqRuleActionsDTO);
        if (dqRuleActionsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DqRuleActionsDTO result = dqRuleActionsService.save(dqRuleActionsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dqRuleActionsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dq-rule-actions} : get all the dqRuleActions.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dqRuleActions in body.
     */
    @GetMapping("/dq-rule-actions")
    public ResponseEntity<List<DqRuleActionsDTO>> getAllDqRuleActions(DqRuleActionsCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DqRuleActions by criteria: {}", criteria);
        Page<DqRuleActionsDTO> page = dqRuleActionsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dq-rule-actions/count} : count all the dqRuleActions.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/dq-rule-actions/count")
    public ResponseEntity<Long> countDqRuleActions(DqRuleActionsCriteria criteria) {
        log.debug("REST request to count DqRuleActions by criteria: {}", criteria);
        return ResponseEntity.ok().body(dqRuleActionsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dq-rule-actions/:id} : get the "id" dqRuleActions.
     *
     * @param id the id of the dqRuleActionsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dqRuleActionsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dq-rule-actions/{id}")
    public ResponseEntity<DqRuleActionsDTO> getDqRuleActions(@PathVariable Long id) {
        log.debug("REST request to get DqRuleActions : {}", id);
        Optional<DqRuleActionsDTO> dqRuleActionsDTO = dqRuleActionsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dqRuleActionsDTO);
    }

    /**
     * {@code DELETE  /dq-rule-actions/:id} : delete the "id" dqRuleActions.
     *
     * @param id the id of the dqRuleActionsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dq-rule-actions/{id}")
    public ResponseEntity<Void> deleteDqRuleActions(@PathVariable Long id) {
        log.debug("REST request to delete DqRuleActions : {}", id);
        dqRuleActionsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
