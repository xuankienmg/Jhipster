package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.EventCategoriesService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.EventCategoriesDTO;
import com.mycompany.myapp.service.dto.EventCategoriesCriteria;
import com.mycompany.myapp.service.EventCategoriesQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.EventCategories}.
 */
@RestController
@RequestMapping("/api")
public class EventCategoriesResource {

    private final Logger log = LoggerFactory.getLogger(EventCategoriesResource.class);

    private static final String ENTITY_NAME = "eventCategories";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EventCategoriesService eventCategoriesService;

    private final EventCategoriesQueryService eventCategoriesQueryService;

    public EventCategoriesResource(EventCategoriesService eventCategoriesService, EventCategoriesQueryService eventCategoriesQueryService) {
        this.eventCategoriesService = eventCategoriesService;
        this.eventCategoriesQueryService = eventCategoriesQueryService;
    }

    /**
     * {@code POST  /event-categories} : Create a new eventCategories.
     *
     * @param eventCategoriesDTO the eventCategoriesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new eventCategoriesDTO, or with status {@code 400 (Bad Request)} if the eventCategories has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/event-categories")
    public ResponseEntity<EventCategoriesDTO> createEventCategories(@RequestBody EventCategoriesDTO eventCategoriesDTO) throws URISyntaxException {
        log.debug("REST request to save EventCategories : {}", eventCategoriesDTO);
        if (eventCategoriesDTO.getId() != null) {
            throw new BadRequestAlertException("A new eventCategories cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EventCategoriesDTO result = eventCategoriesService.save(eventCategoriesDTO);
        return ResponseEntity.created(new URI("/api/event-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /event-categories} : Updates an existing eventCategories.
     *
     * @param eventCategoriesDTO the eventCategoriesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated eventCategoriesDTO,
     * or with status {@code 400 (Bad Request)} if the eventCategoriesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the eventCategoriesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/event-categories")
    public ResponseEntity<EventCategoriesDTO> updateEventCategories(@RequestBody EventCategoriesDTO eventCategoriesDTO) throws URISyntaxException {
        log.debug("REST request to update EventCategories : {}", eventCategoriesDTO);
        if (eventCategoriesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EventCategoriesDTO result = eventCategoriesService.save(eventCategoriesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, eventCategoriesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /event-categories} : get all the eventCategories.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of eventCategories in body.
     */
    @GetMapping("/event-categories")
    public ResponseEntity<List<EventCategoriesDTO>> getAllEventCategories(EventCategoriesCriteria criteria, Pageable pageable) {
        log.debug("REST request to get EventCategories by criteria: {}", criteria);
        Page<EventCategoriesDTO> page = eventCategoriesQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /event-categories/count} : count all the eventCategories.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/event-categories/count")
    public ResponseEntity<Long> countEventCategories(EventCategoriesCriteria criteria) {
        log.debug("REST request to count EventCategories by criteria: {}", criteria);
        return ResponseEntity.ok().body(eventCategoriesQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /event-categories/:id} : get the "id" eventCategories.
     *
     * @param id the id of the eventCategoriesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the eventCategoriesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/event-categories/{id}")
    public ResponseEntity<EventCategoriesDTO> getEventCategories(@PathVariable Long id) {
        log.debug("REST request to get EventCategories : {}", id);
        Optional<EventCategoriesDTO> eventCategoriesDTO = eventCategoriesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(eventCategoriesDTO);
    }

    /**
     * {@code DELETE  /event-categories/:id} : delete the "id" eventCategories.
     *
     * @param id the id of the eventCategoriesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/event-categories/{id}")
    public ResponseEntity<Void> deleteEventCategories(@PathVariable Long id) {
        log.debug("REST request to delete EventCategories : {}", id);
        eventCategoriesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
