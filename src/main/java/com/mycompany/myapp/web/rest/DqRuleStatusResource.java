package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.DqRuleStatusService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.DqRuleStatusDTO;
import com.mycompany.myapp.service.dto.DqRuleStatusCriteria;
import com.mycompany.myapp.service.DqRuleStatusQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.DqRuleStatus}.
 */
@RestController
@RequestMapping("/api")
public class DqRuleStatusResource {

    private final Logger log = LoggerFactory.getLogger(DqRuleStatusResource.class);

    private static final String ENTITY_NAME = "dqRuleStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DqRuleStatusService dqRuleStatusService;

    private final DqRuleStatusQueryService dqRuleStatusQueryService;

    public DqRuleStatusResource(DqRuleStatusService dqRuleStatusService, DqRuleStatusQueryService dqRuleStatusQueryService) {
        this.dqRuleStatusService = dqRuleStatusService;
        this.dqRuleStatusQueryService = dqRuleStatusQueryService;
    }

    /**
     * {@code POST  /dq-rule-statuses} : Create a new dqRuleStatus.
     *
     * @param dqRuleStatusDTO the dqRuleStatusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dqRuleStatusDTO, or with status {@code 400 (Bad Request)} if the dqRuleStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dq-rule-statuses")
    public ResponseEntity<DqRuleStatusDTO> createDqRuleStatus(@RequestBody DqRuleStatusDTO dqRuleStatusDTO) throws URISyntaxException {
        log.debug("REST request to save DqRuleStatus : {}", dqRuleStatusDTO);
        if (dqRuleStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new dqRuleStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DqRuleStatusDTO result = dqRuleStatusService.save(dqRuleStatusDTO);
        return ResponseEntity.created(new URI("/api/dq-rule-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dq-rule-statuses} : Updates an existing dqRuleStatus.
     *
     * @param dqRuleStatusDTO the dqRuleStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dqRuleStatusDTO,
     * or with status {@code 400 (Bad Request)} if the dqRuleStatusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dqRuleStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dq-rule-statuses")
    public ResponseEntity<DqRuleStatusDTO> updateDqRuleStatus(@RequestBody DqRuleStatusDTO dqRuleStatusDTO) throws URISyntaxException {
        log.debug("REST request to update DqRuleStatus : {}", dqRuleStatusDTO);
        if (dqRuleStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DqRuleStatusDTO result = dqRuleStatusService.save(dqRuleStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dqRuleStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dq-rule-statuses} : get all the dqRuleStatuses.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dqRuleStatuses in body.
     */
    @GetMapping("/dq-rule-statuses")
    public ResponseEntity<List<DqRuleStatusDTO>> getAllDqRuleStatuses(DqRuleStatusCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DqRuleStatuses by criteria: {}", criteria);
        Page<DqRuleStatusDTO> page = dqRuleStatusQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dq-rule-statuses/count} : count all the dqRuleStatuses.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/dq-rule-statuses/count")
    public ResponseEntity<Long> countDqRuleStatuses(DqRuleStatusCriteria criteria) {
        log.debug("REST request to count DqRuleStatuses by criteria: {}", criteria);
        return ResponseEntity.ok().body(dqRuleStatusQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dq-rule-statuses/:id} : get the "id" dqRuleStatus.
     *
     * @param id the id of the dqRuleStatusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dqRuleStatusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dq-rule-statuses/{id}")
    public ResponseEntity<DqRuleStatusDTO> getDqRuleStatus(@PathVariable Long id) {
        log.debug("REST request to get DqRuleStatus : {}", id);
        Optional<DqRuleStatusDTO> dqRuleStatusDTO = dqRuleStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dqRuleStatusDTO);
    }

    /**
     * {@code DELETE  /dq-rule-statuses/:id} : delete the "id" dqRuleStatus.
     *
     * @param id the id of the dqRuleStatusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dq-rule-statuses/{id}")
    public ResponseEntity<Void> deleteDqRuleStatus(@PathVariable Long id) {
        log.debug("REST request to delete DqRuleStatus : {}", id);
        dqRuleStatusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
