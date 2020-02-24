package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.EventTypesService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.EventTypesDTO;
import com.mycompany.myapp.service.dto.EventTypesCriteria;
import com.mycompany.myapp.service.EventTypesQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.EventTypes}.
 */
@RestController
@RequestMapping("/api")
public class EventTypesResource {

    private final Logger log = LoggerFactory.getLogger(EventTypesResource.class);

    private static final String ENTITY_NAME = "eventTypes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EventTypesService eventTypesService;

    private final EventTypesQueryService eventTypesQueryService;

    public EventTypesResource(EventTypesService eventTypesService, EventTypesQueryService eventTypesQueryService) {
        this.eventTypesService = eventTypesService;
        this.eventTypesQueryService = eventTypesQueryService;
    }

    /**
     * {@code POST  /event-types} : Create a new eventTypes.
     *
     * @param eventTypesDTO the eventTypesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new eventTypesDTO, or with status {@code 400 (Bad Request)} if the eventTypes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/event-types")
    public ResponseEntity<EventTypesDTO> createEventTypes(@RequestBody EventTypesDTO eventTypesDTO) throws URISyntaxException {
        log.debug("REST request to save EventTypes : {}", eventTypesDTO);
        if (eventTypesDTO.getId() != null) {
            throw new BadRequestAlertException("A new eventTypes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EventTypesDTO result = eventTypesService.save(eventTypesDTO);
        return ResponseEntity.created(new URI("/api/event-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /event-types} : Updates an existing eventTypes.
     *
     * @param eventTypesDTO the eventTypesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated eventTypesDTO,
     * or with status {@code 400 (Bad Request)} if the eventTypesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the eventTypesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/event-types")
    public ResponseEntity<EventTypesDTO> updateEventTypes(@RequestBody EventTypesDTO eventTypesDTO) throws URISyntaxException {
        log.debug("REST request to update EventTypes : {}", eventTypesDTO);
        if (eventTypesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EventTypesDTO result = eventTypesService.save(eventTypesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, eventTypesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /event-types} : get all the eventTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of eventTypes in body.
     */
    @GetMapping("/event-types")
    public ResponseEntity<List<EventTypesDTO>> getAllEventTypes(EventTypesCriteria criteria, Pageable pageable) {
        log.debug("REST request to get EventTypes by criteria: {}", criteria);
        Page<EventTypesDTO> page = eventTypesQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /event-types/count} : count all the eventTypes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/event-types/count")
    public ResponseEntity<Long> countEventTypes(EventTypesCriteria criteria) {
        log.debug("REST request to count EventTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(eventTypesQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /event-types/:id} : get the "id" eventTypes.
     *
     * @param id the id of the eventTypesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the eventTypesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/event-types/{id}")
    public ResponseEntity<EventTypesDTO> getEventTypes(@PathVariable Long id) {
        log.debug("REST request to get EventTypes : {}", id);
        Optional<EventTypesDTO> eventTypesDTO = eventTypesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(eventTypesDTO);
    }

    /**
     * {@code DELETE  /event-types/:id} : delete the "id" eventTypes.
     *
     * @param id the id of the eventTypesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/event-types/{id}")
    public ResponseEntity<Void> deleteEventTypes(@PathVariable Long id) {
        log.debug("REST request to delete EventTypes : {}", id);
        eventTypesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
