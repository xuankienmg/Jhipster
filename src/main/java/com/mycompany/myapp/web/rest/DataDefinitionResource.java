package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.DataDefinitionService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.DataDefinitionDTO;
import com.mycompany.myapp.service.dto.DataDefinitionCriteria;
import com.mycompany.myapp.service.DataDefinitionQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.DataDefinition}.
 */
@RestController
@RequestMapping("/api")
public class DataDefinitionResource {

    private final Logger log = LoggerFactory.getLogger(DataDefinitionResource.class);

    private static final String ENTITY_NAME = "dataDefinition";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DataDefinitionService dataDefinitionService;

    private final DataDefinitionQueryService dataDefinitionQueryService;

    public DataDefinitionResource(DataDefinitionService dataDefinitionService, DataDefinitionQueryService dataDefinitionQueryService) {
        this.dataDefinitionService = dataDefinitionService;
        this.dataDefinitionQueryService = dataDefinitionQueryService;
    }

    /**
     * {@code POST  /data-definitions} : Create a new dataDefinition.
     *
     * @param dataDefinitionDTO the dataDefinitionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dataDefinitionDTO, or with status {@code 400 (Bad Request)} if the dataDefinition has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/data-definitions")
    public ResponseEntity<DataDefinitionDTO> createDataDefinition(@RequestBody DataDefinitionDTO dataDefinitionDTO) throws URISyntaxException {
        log.debug("REST request to save DataDefinition : {}", dataDefinitionDTO);
        if (dataDefinitionDTO.getId() != null) {
            throw new BadRequestAlertException("A new dataDefinition cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DataDefinitionDTO result = dataDefinitionService.save(dataDefinitionDTO);
        return ResponseEntity.created(new URI("/api/data-definitions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /data-definitions} : Updates an existing dataDefinition.
     *
     * @param dataDefinitionDTO the dataDefinitionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dataDefinitionDTO,
     * or with status {@code 400 (Bad Request)} if the dataDefinitionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dataDefinitionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/data-definitions")
    public ResponseEntity<DataDefinitionDTO> updateDataDefinition(@RequestBody DataDefinitionDTO dataDefinitionDTO) throws URISyntaxException {
        log.debug("REST request to update DataDefinition : {}", dataDefinitionDTO);
        if (dataDefinitionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DataDefinitionDTO result = dataDefinitionService.save(dataDefinitionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dataDefinitionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /data-definitions} : get all the dataDefinitions.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dataDefinitions in body.
     */
    @GetMapping("/data-definitions")
    public ResponseEntity<List<DataDefinitionDTO>> getAllDataDefinitions(DataDefinitionCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DataDefinitions by criteria: {}", criteria);
        Page<DataDefinitionDTO> page = dataDefinitionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /data-definitions/count} : count all the dataDefinitions.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/data-definitions/count")
    public ResponseEntity<Long> countDataDefinitions(DataDefinitionCriteria criteria) {
        log.debug("REST request to count DataDefinitions by criteria: {}", criteria);
        return ResponseEntity.ok().body(dataDefinitionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /data-definitions/:id} : get the "id" dataDefinition.
     *
     * @param id the id of the dataDefinitionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dataDefinitionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/data-definitions/{id}")
    public ResponseEntity<DataDefinitionDTO> getDataDefinition(@PathVariable Long id) {
        log.debug("REST request to get DataDefinition : {}", id);
        Optional<DataDefinitionDTO> dataDefinitionDTO = dataDefinitionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dataDefinitionDTO);
    }

    /**
     * {@code DELETE  /data-definitions/:id} : delete the "id" dataDefinition.
     *
     * @param id the id of the dataDefinitionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/data-definitions/{id}")
    public ResponseEntity<Void> deleteDataDefinition(@PathVariable Long id) {
        log.debug("REST request to delete DataDefinition : {}", id);
        dataDefinitionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
