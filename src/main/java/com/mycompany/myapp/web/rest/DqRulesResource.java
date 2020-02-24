package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.DqRulesService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.DqRulesDTO;
import com.mycompany.myapp.service.dto.DqRulesCriteria;
import com.mycompany.myapp.service.DqRulesQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.DqRules}.
 */
@RestController
@RequestMapping("/api")
public class DqRulesResource {

    private final Logger log = LoggerFactory.getLogger(DqRulesResource.class);

    private static final String ENTITY_NAME = "dqRules";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DqRulesService dqRulesService;

    private final DqRulesQueryService dqRulesQueryService;

    public DqRulesResource(DqRulesService dqRulesService, DqRulesQueryService dqRulesQueryService) {
        this.dqRulesService = dqRulesService;
        this.dqRulesQueryService = dqRulesQueryService;
    }

    /**
     * {@code POST  /dq-rules} : Create a new dqRules.
     *
     * @param dqRulesDTO the dqRulesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dqRulesDTO, or with status {@code 400 (Bad Request)} if the dqRules has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dq-rules")
    public ResponseEntity<DqRulesDTO> createDqRules(@RequestBody DqRulesDTO dqRulesDTO) throws URISyntaxException {
        log.debug("REST request to save DqRules : {}", dqRulesDTO);
        if (dqRulesDTO.getId() != null) {
            throw new BadRequestAlertException("A new dqRules cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DqRulesDTO result = dqRulesService.save(dqRulesDTO);
        return ResponseEntity.created(new URI("/api/dq-rules/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dq-rules} : Updates an existing dqRules.
     *
     * @param dqRulesDTO the dqRulesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dqRulesDTO,
     * or with status {@code 400 (Bad Request)} if the dqRulesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dqRulesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dq-rules")
    public ResponseEntity<DqRulesDTO> updateDqRules(@RequestBody DqRulesDTO dqRulesDTO) throws URISyntaxException {
        log.debug("REST request to update DqRules : {}", dqRulesDTO);
        if (dqRulesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DqRulesDTO result = dqRulesService.save(dqRulesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dqRulesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dq-rules} : get all the dqRules.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dqRules in body.
     */
    @GetMapping("/dq-rules")
    public ResponseEntity<List<DqRulesDTO>> getAllDqRules(DqRulesCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DqRules by criteria: {}", criteria);
        Page<DqRulesDTO> page = dqRulesQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dq-rules/count} : count all the dqRules.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/dq-rules/count")
    public ResponseEntity<Long> countDqRules(DqRulesCriteria criteria) {
        log.debug("REST request to count DqRules by criteria: {}", criteria);
        return ResponseEntity.ok().body(dqRulesQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dq-rules/:id} : get the "id" dqRules.
     *
     * @param id the id of the dqRulesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dqRulesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dq-rules/{id}")
    public ResponseEntity<DqRulesDTO> getDqRules(@PathVariable Long id) {
        log.debug("REST request to get DqRules : {}", id);
        Optional<DqRulesDTO> dqRulesDTO = dqRulesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dqRulesDTO);
    }

    /**
     * {@code DELETE  /dq-rules/:id} : delete the "id" dqRules.
     *
     * @param id the id of the dqRulesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dq-rules/{id}")
    public ResponseEntity<Void> deleteDqRules(@PathVariable Long id) {
        log.debug("REST request to delete DqRules : {}", id);
        dqRulesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
