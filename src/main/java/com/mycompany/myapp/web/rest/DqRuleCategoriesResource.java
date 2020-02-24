package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.DqRuleCategoriesService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.DqRuleCategoriesDTO;
import com.mycompany.myapp.service.dto.DqRuleCategoriesCriteria;
import com.mycompany.myapp.service.DqRuleCategoriesQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.DqRuleCategories}.
 */
@RestController
@RequestMapping("/api")
public class DqRuleCategoriesResource {

    private final Logger log = LoggerFactory.getLogger(DqRuleCategoriesResource.class);

    private static final String ENTITY_NAME = "dqRuleCategories";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DqRuleCategoriesService dqRuleCategoriesService;

    private final DqRuleCategoriesQueryService dqRuleCategoriesQueryService;

    public DqRuleCategoriesResource(DqRuleCategoriesService dqRuleCategoriesService, DqRuleCategoriesQueryService dqRuleCategoriesQueryService) {
        this.dqRuleCategoriesService = dqRuleCategoriesService;
        this.dqRuleCategoriesQueryService = dqRuleCategoriesQueryService;
    }

    /**
     * {@code POST  /dq-rule-categories} : Create a new dqRuleCategories.
     *
     * @param dqRuleCategoriesDTO the dqRuleCategoriesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dqRuleCategoriesDTO, or with status {@code 400 (Bad Request)} if the dqRuleCategories has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dq-rule-categories")
    public ResponseEntity<DqRuleCategoriesDTO> createDqRuleCategories(@RequestBody DqRuleCategoriesDTO dqRuleCategoriesDTO) throws URISyntaxException {
        log.debug("REST request to save DqRuleCategories : {}", dqRuleCategoriesDTO);
        if (dqRuleCategoriesDTO.getId() != null) {
            throw new BadRequestAlertException("A new dqRuleCategories cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DqRuleCategoriesDTO result = dqRuleCategoriesService.save(dqRuleCategoriesDTO);
        return ResponseEntity.created(new URI("/api/dq-rule-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dq-rule-categories} : Updates an existing dqRuleCategories.
     *
     * @param dqRuleCategoriesDTO the dqRuleCategoriesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dqRuleCategoriesDTO,
     * or with status {@code 400 (Bad Request)} if the dqRuleCategoriesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dqRuleCategoriesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dq-rule-categories")
    public ResponseEntity<DqRuleCategoriesDTO> updateDqRuleCategories(@RequestBody DqRuleCategoriesDTO dqRuleCategoriesDTO) throws URISyntaxException {
        log.debug("REST request to update DqRuleCategories : {}", dqRuleCategoriesDTO);
        if (dqRuleCategoriesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DqRuleCategoriesDTO result = dqRuleCategoriesService.save(dqRuleCategoriesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dqRuleCategoriesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dq-rule-categories} : get all the dqRuleCategories.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dqRuleCategories in body.
     */
    @GetMapping("/dq-rule-categories")
    public ResponseEntity<List<DqRuleCategoriesDTO>> getAllDqRuleCategories(DqRuleCategoriesCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DqRuleCategories by criteria: {}", criteria);
        Page<DqRuleCategoriesDTO> page = dqRuleCategoriesQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dq-rule-categories/count} : count all the dqRuleCategories.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/dq-rule-categories/count")
    public ResponseEntity<Long> countDqRuleCategories(DqRuleCategoriesCriteria criteria) {
        log.debug("REST request to count DqRuleCategories by criteria: {}", criteria);
        return ResponseEntity.ok().body(dqRuleCategoriesQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dq-rule-categories/:id} : get the "id" dqRuleCategories.
     *
     * @param id the id of the dqRuleCategoriesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dqRuleCategoriesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dq-rule-categories/{id}")
    public ResponseEntity<DqRuleCategoriesDTO> getDqRuleCategories(@PathVariable Long id) {
        log.debug("REST request to get DqRuleCategories : {}", id);
        Optional<DqRuleCategoriesDTO> dqRuleCategoriesDTO = dqRuleCategoriesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dqRuleCategoriesDTO);
    }

    /**
     * {@code DELETE  /dq-rule-categories/:id} : delete the "id" dqRuleCategories.
     *
     * @param id the id of the dqRuleCategoriesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dq-rule-categories/{id}")
    public ResponseEntity<Void> deleteDqRuleCategories(@PathVariable Long id) {
        log.debug("REST request to delete DqRuleCategories : {}", id);
        dqRuleCategoriesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
