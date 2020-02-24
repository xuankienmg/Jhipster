package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.DqRuleRiskLevelsService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.DqRuleRiskLevelsDTO;
import com.mycompany.myapp.service.dto.DqRuleRiskLevelsCriteria;
import com.mycompany.myapp.service.DqRuleRiskLevelsQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.DqRuleRiskLevels}.
 */
@RestController
@RequestMapping("/api")
public class DqRuleRiskLevelsResource {

    private final Logger log = LoggerFactory.getLogger(DqRuleRiskLevelsResource.class);

    private static final String ENTITY_NAME = "dqRuleRiskLevels";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DqRuleRiskLevelsService dqRuleRiskLevelsService;

    private final DqRuleRiskLevelsQueryService dqRuleRiskLevelsQueryService;

    public DqRuleRiskLevelsResource(DqRuleRiskLevelsService dqRuleRiskLevelsService, DqRuleRiskLevelsQueryService dqRuleRiskLevelsQueryService) {
        this.dqRuleRiskLevelsService = dqRuleRiskLevelsService;
        this.dqRuleRiskLevelsQueryService = dqRuleRiskLevelsQueryService;
    }

    /**
     * {@code POST  /dq-rule-risk-levels} : Create a new dqRuleRiskLevels.
     *
     * @param dqRuleRiskLevelsDTO the dqRuleRiskLevelsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dqRuleRiskLevelsDTO, or with status {@code 400 (Bad Request)} if the dqRuleRiskLevels has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dq-rule-risk-levels")
    public ResponseEntity<DqRuleRiskLevelsDTO> createDqRuleRiskLevels(@RequestBody DqRuleRiskLevelsDTO dqRuleRiskLevelsDTO) throws URISyntaxException {
        log.debug("REST request to save DqRuleRiskLevels : {}", dqRuleRiskLevelsDTO);
        if (dqRuleRiskLevelsDTO.getId() != null) {
            throw new BadRequestAlertException("A new dqRuleRiskLevels cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DqRuleRiskLevelsDTO result = dqRuleRiskLevelsService.save(dqRuleRiskLevelsDTO);
        return ResponseEntity.created(new URI("/api/dq-rule-risk-levels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dq-rule-risk-levels} : Updates an existing dqRuleRiskLevels.
     *
     * @param dqRuleRiskLevelsDTO the dqRuleRiskLevelsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dqRuleRiskLevelsDTO,
     * or with status {@code 400 (Bad Request)} if the dqRuleRiskLevelsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dqRuleRiskLevelsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dq-rule-risk-levels")
    public ResponseEntity<DqRuleRiskLevelsDTO> updateDqRuleRiskLevels(@RequestBody DqRuleRiskLevelsDTO dqRuleRiskLevelsDTO) throws URISyntaxException {
        log.debug("REST request to update DqRuleRiskLevels : {}", dqRuleRiskLevelsDTO);
        if (dqRuleRiskLevelsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DqRuleRiskLevelsDTO result = dqRuleRiskLevelsService.save(dqRuleRiskLevelsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dqRuleRiskLevelsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dq-rule-risk-levels} : get all the dqRuleRiskLevels.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dqRuleRiskLevels in body.
     */
    @GetMapping("/dq-rule-risk-levels")
    public ResponseEntity<List<DqRuleRiskLevelsDTO>> getAllDqRuleRiskLevels(DqRuleRiskLevelsCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DqRuleRiskLevels by criteria: {}", criteria);
        Page<DqRuleRiskLevelsDTO> page = dqRuleRiskLevelsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dq-rule-risk-levels/count} : count all the dqRuleRiskLevels.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/dq-rule-risk-levels/count")
    public ResponseEntity<Long> countDqRuleRiskLevels(DqRuleRiskLevelsCriteria criteria) {
        log.debug("REST request to count DqRuleRiskLevels by criteria: {}", criteria);
        return ResponseEntity.ok().body(dqRuleRiskLevelsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dq-rule-risk-levels/:id} : get the "id" dqRuleRiskLevels.
     *
     * @param id the id of the dqRuleRiskLevelsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dqRuleRiskLevelsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dq-rule-risk-levels/{id}")
    public ResponseEntity<DqRuleRiskLevelsDTO> getDqRuleRiskLevels(@PathVariable Long id) {
        log.debug("REST request to get DqRuleRiskLevels : {}", id);
        Optional<DqRuleRiskLevelsDTO> dqRuleRiskLevelsDTO = dqRuleRiskLevelsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dqRuleRiskLevelsDTO);
    }

    /**
     * {@code DELETE  /dq-rule-risk-levels/:id} : delete the "id" dqRuleRiskLevels.
     *
     * @param id the id of the dqRuleRiskLevelsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dq-rule-risk-levels/{id}")
    public ResponseEntity<Void> deleteDqRuleRiskLevels(@PathVariable Long id) {
        log.debug("REST request to delete DqRuleRiskLevels : {}", id);
        dqRuleRiskLevelsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
