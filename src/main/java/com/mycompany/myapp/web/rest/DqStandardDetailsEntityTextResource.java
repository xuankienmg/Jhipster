package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.DqStandardDetailsEntityTextService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityTextDTO;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityTextCriteria;
import com.mycompany.myapp.service.DqStandardDetailsEntityTextQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.DqStandardDetailsEntityText}.
 */
@RestController
@RequestMapping("/api")
public class DqStandardDetailsEntityTextResource {

    private final Logger log = LoggerFactory.getLogger(DqStandardDetailsEntityTextResource.class);

    private static final String ENTITY_NAME = "dqStandardDetailsEntityText";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DqStandardDetailsEntityTextService dqStandardDetailsEntityTextService;

    private final DqStandardDetailsEntityTextQueryService dqStandardDetailsEntityTextQueryService;

    public DqStandardDetailsEntityTextResource(DqStandardDetailsEntityTextService dqStandardDetailsEntityTextService, DqStandardDetailsEntityTextQueryService dqStandardDetailsEntityTextQueryService) {
        this.dqStandardDetailsEntityTextService = dqStandardDetailsEntityTextService;
        this.dqStandardDetailsEntityTextQueryService = dqStandardDetailsEntityTextQueryService;
    }

    /**
     * {@code POST  /dq-standard-details-entity-texts} : Create a new dqStandardDetailsEntityText.
     *
     * @param dqStandardDetailsEntityTextDTO the dqStandardDetailsEntityTextDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dqStandardDetailsEntityTextDTO, or with status {@code 400 (Bad Request)} if the dqStandardDetailsEntityText has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dq-standard-details-entity-texts")
    public ResponseEntity<DqStandardDetailsEntityTextDTO> createDqStandardDetailsEntityText(@RequestBody DqStandardDetailsEntityTextDTO dqStandardDetailsEntityTextDTO) throws URISyntaxException {
        log.debug("REST request to save DqStandardDetailsEntityText : {}", dqStandardDetailsEntityTextDTO);
        if (dqStandardDetailsEntityTextDTO.getId() != null) {
            throw new BadRequestAlertException("A new dqStandardDetailsEntityText cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DqStandardDetailsEntityTextDTO result = dqStandardDetailsEntityTextService.save(dqStandardDetailsEntityTextDTO);
        return ResponseEntity.created(new URI("/api/dq-standard-details-entity-texts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dq-standard-details-entity-texts} : Updates an existing dqStandardDetailsEntityText.
     *
     * @param dqStandardDetailsEntityTextDTO the dqStandardDetailsEntityTextDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dqStandardDetailsEntityTextDTO,
     * or with status {@code 400 (Bad Request)} if the dqStandardDetailsEntityTextDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dqStandardDetailsEntityTextDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dq-standard-details-entity-texts")
    public ResponseEntity<DqStandardDetailsEntityTextDTO> updateDqStandardDetailsEntityText(@RequestBody DqStandardDetailsEntityTextDTO dqStandardDetailsEntityTextDTO) throws URISyntaxException {
        log.debug("REST request to update DqStandardDetailsEntityText : {}", dqStandardDetailsEntityTextDTO);
        if (dqStandardDetailsEntityTextDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DqStandardDetailsEntityTextDTO result = dqStandardDetailsEntityTextService.save(dqStandardDetailsEntityTextDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dqStandardDetailsEntityTextDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dq-standard-details-entity-texts} : get all the dqStandardDetailsEntityTexts.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dqStandardDetailsEntityTexts in body.
     */
    @GetMapping("/dq-standard-details-entity-texts")
    public ResponseEntity<List<DqStandardDetailsEntityTextDTO>> getAllDqStandardDetailsEntityTexts(DqStandardDetailsEntityTextCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DqStandardDetailsEntityTexts by criteria: {}", criteria);
        Page<DqStandardDetailsEntityTextDTO> page = dqStandardDetailsEntityTextQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dq-standard-details-entity-texts/count} : count all the dqStandardDetailsEntityTexts.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/dq-standard-details-entity-texts/count")
    public ResponseEntity<Long> countDqStandardDetailsEntityTexts(DqStandardDetailsEntityTextCriteria criteria) {
        log.debug("REST request to count DqStandardDetailsEntityTexts by criteria: {}", criteria);
        return ResponseEntity.ok().body(dqStandardDetailsEntityTextQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dq-standard-details-entity-texts/:id} : get the "id" dqStandardDetailsEntityText.
     *
     * @param id the id of the dqStandardDetailsEntityTextDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dqStandardDetailsEntityTextDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dq-standard-details-entity-texts/{id}")
    public ResponseEntity<DqStandardDetailsEntityTextDTO> getDqStandardDetailsEntityText(@PathVariable Long id) {
        log.debug("REST request to get DqStandardDetailsEntityText : {}", id);
        Optional<DqStandardDetailsEntityTextDTO> dqStandardDetailsEntityTextDTO = dqStandardDetailsEntityTextService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dqStandardDetailsEntityTextDTO);
    }

    /**
     * {@code DELETE  /dq-standard-details-entity-texts/:id} : delete the "id" dqStandardDetailsEntityText.
     *
     * @param id the id of the dqStandardDetailsEntityTextDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dq-standard-details-entity-texts/{id}")
    public ResponseEntity<Void> deleteDqStandardDetailsEntityText(@PathVariable Long id) {
        log.debug("REST request to delete DqStandardDetailsEntityText : {}", id);
        dqStandardDetailsEntityTextService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
