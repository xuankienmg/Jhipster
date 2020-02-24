package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.DqNotificationsService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.DqNotificationsDTO;
import com.mycompany.myapp.service.dto.DqNotificationsCriteria;
import com.mycompany.myapp.service.DqNotificationsQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.DqNotifications}.
 */
@RestController
@RequestMapping("/api")
public class DqNotificationsResource {

    private final Logger log = LoggerFactory.getLogger(DqNotificationsResource.class);

    private static final String ENTITY_NAME = "dqNotifications";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DqNotificationsService dqNotificationsService;

    private final DqNotificationsQueryService dqNotificationsQueryService;

    public DqNotificationsResource(DqNotificationsService dqNotificationsService, DqNotificationsQueryService dqNotificationsQueryService) {
        this.dqNotificationsService = dqNotificationsService;
        this.dqNotificationsQueryService = dqNotificationsQueryService;
    }

    /**
     * {@code POST  /dq-notifications} : Create a new dqNotifications.
     *
     * @param dqNotificationsDTO the dqNotificationsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dqNotificationsDTO, or with status {@code 400 (Bad Request)} if the dqNotifications has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dq-notifications")
    public ResponseEntity<DqNotificationsDTO> createDqNotifications(@RequestBody DqNotificationsDTO dqNotificationsDTO) throws URISyntaxException {
        log.debug("REST request to save DqNotifications : {}", dqNotificationsDTO);
        if (dqNotificationsDTO.getId() != null) {
            throw new BadRequestAlertException("A new dqNotifications cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DqNotificationsDTO result = dqNotificationsService.save(dqNotificationsDTO);
        return ResponseEntity.created(new URI("/api/dq-notifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dq-notifications} : Updates an existing dqNotifications.
     *
     * @param dqNotificationsDTO the dqNotificationsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dqNotificationsDTO,
     * or with status {@code 400 (Bad Request)} if the dqNotificationsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dqNotificationsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dq-notifications")
    public ResponseEntity<DqNotificationsDTO> updateDqNotifications(@RequestBody DqNotificationsDTO dqNotificationsDTO) throws URISyntaxException {
        log.debug("REST request to update DqNotifications : {}", dqNotificationsDTO);
        if (dqNotificationsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DqNotificationsDTO result = dqNotificationsService.save(dqNotificationsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dqNotificationsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dq-notifications} : get all the dqNotifications.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dqNotifications in body.
     */
    @GetMapping("/dq-notifications")
    public ResponseEntity<List<DqNotificationsDTO>> getAllDqNotifications(DqNotificationsCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DqNotifications by criteria: {}", criteria);
        Page<DqNotificationsDTO> page = dqNotificationsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dq-notifications/count} : count all the dqNotifications.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/dq-notifications/count")
    public ResponseEntity<Long> countDqNotifications(DqNotificationsCriteria criteria) {
        log.debug("REST request to count DqNotifications by criteria: {}", criteria);
        return ResponseEntity.ok().body(dqNotificationsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dq-notifications/:id} : get the "id" dqNotifications.
     *
     * @param id the id of the dqNotificationsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dqNotificationsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dq-notifications/{id}")
    public ResponseEntity<DqNotificationsDTO> getDqNotifications(@PathVariable Long id) {
        log.debug("REST request to get DqNotifications : {}", id);
        Optional<DqNotificationsDTO> dqNotificationsDTO = dqNotificationsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dqNotificationsDTO);
    }

    /**
     * {@code DELETE  /dq-notifications/:id} : delete the "id" dqNotifications.
     *
     * @param id the id of the dqNotificationsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dq-notifications/{id}")
    public ResponseEntity<Void> deleteDqNotifications(@PathVariable Long id) {
        log.debug("REST request to delete DqNotifications : {}", id);
        dqNotificationsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
