package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.EventLogsService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.EventLogsDTO;
import com.mycompany.myapp.service.dto.EventLogsCriteria;
import com.mycompany.myapp.service.EventLogsQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.EventLogs}.
 */
@RestController
@RequestMapping("/api")
public class EventLogsResource {

    private final Logger log = LoggerFactory.getLogger(EventLogsResource.class);

    private static final String ENTITY_NAME = "eventLogs";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EventLogsService eventLogsService;

    private final EventLogsQueryService eventLogsQueryService;

    public EventLogsResource(EventLogsService eventLogsService, EventLogsQueryService eventLogsQueryService) {
        this.eventLogsService = eventLogsService;
        this.eventLogsQueryService = eventLogsQueryService;
    }

    /**
     * {@code POST  /event-logs} : Create a new eventLogs.
     *
     * @param eventLogsDTO the eventLogsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new eventLogsDTO, or with status {@code 400 (Bad Request)} if the eventLogs has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/event-logs")
    public ResponseEntity<EventLogsDTO> createEventLogs(@RequestBody EventLogsDTO eventLogsDTO) throws URISyntaxException {
        log.debug("REST request to save EventLogs : {}", eventLogsDTO);
        if (eventLogsDTO.getId() != null) {
            throw new BadRequestAlertException("A new eventLogs cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EventLogsDTO result = eventLogsService.save(eventLogsDTO);
        return ResponseEntity.created(new URI("/api/event-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /event-logs} : Updates an existing eventLogs.
     *
     * @param eventLogsDTO the eventLogsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated eventLogsDTO,
     * or with status {@code 400 (Bad Request)} if the eventLogsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the eventLogsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/event-logs")
    public ResponseEntity<EventLogsDTO> updateEventLogs(@RequestBody EventLogsDTO eventLogsDTO) throws URISyntaxException {
        log.debug("REST request to update EventLogs : {}", eventLogsDTO);
        if (eventLogsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EventLogsDTO result = eventLogsService.save(eventLogsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, eventLogsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /event-logs} : get all the eventLogs.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of eventLogs in body.
     */
    @GetMapping("/event-logs")
    public ResponseEntity<List<EventLogsDTO>> getAllEventLogs(EventLogsCriteria criteria, Pageable pageable) {
        log.debug("REST request to get EventLogs by criteria: {}", criteria);
        Page<EventLogsDTO> page = eventLogsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /event-logs/count} : count all the eventLogs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/event-logs/count")
    public ResponseEntity<Long> countEventLogs(EventLogsCriteria criteria) {
        log.debug("REST request to count EventLogs by criteria: {}", criteria);
        return ResponseEntity.ok().body(eventLogsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /event-logs/:id} : get the "id" eventLogs.
     *
     * @param id the id of the eventLogsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the eventLogsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/event-logs/{id}")
    public ResponseEntity<EventLogsDTO> getEventLogs(@PathVariable Long id) {
        log.debug("REST request to get EventLogs : {}", id);
        Optional<EventLogsDTO> eventLogsDTO = eventLogsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(eventLogsDTO);
    }

    /**
     * {@code DELETE  /event-logs/:id} : delete the "id" eventLogs.
     *
     * @param id the id of the eventLogsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/event-logs/{id}")
    public ResponseEntity<Void> deleteEventLogs(@PathVariable Long id) {
        log.debug("REST request to delete EventLogs : {}", id);
        eventLogsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
