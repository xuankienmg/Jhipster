package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.DataFlowsService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.DataFlowsDTO;
import com.mycompany.myapp.service.dto.DataFlowsCriteria;
import com.mycompany.myapp.service.DataFlowsQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.DataFlows}.
 */
@RestController
@RequestMapping("/api")
public class DataFlowsResource {

    private final Logger log = LoggerFactory.getLogger(DataFlowsResource.class);

    private static final String ENTITY_NAME = "dataFlows";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DataFlowsService dataFlowsService;

    private final DataFlowsQueryService dataFlowsQueryService;

    public DataFlowsResource(DataFlowsService dataFlowsService, DataFlowsQueryService dataFlowsQueryService) {
        this.dataFlowsService = dataFlowsService;
        this.dataFlowsQueryService = dataFlowsQueryService;
    }

    /**
     * {@code POST  /data-flows} : Create a new dataFlows.
     *
     * @param dataFlowsDTO the dataFlowsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dataFlowsDTO, or with status {@code 400 (Bad Request)} if the dataFlows has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/data-flows")
    public ResponseEntity<DataFlowsDTO> createDataFlows(@RequestBody DataFlowsDTO dataFlowsDTO) throws URISyntaxException {
        log.debug("REST request to save DataFlows : {}", dataFlowsDTO);
        if (dataFlowsDTO.getId() != null) {
            throw new BadRequestAlertException("A new dataFlows cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DataFlowsDTO result = dataFlowsService.save(dataFlowsDTO);
        return ResponseEntity.created(new URI("/api/data-flows/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /data-flows} : Updates an existing dataFlows.
     *
     * @param dataFlowsDTO the dataFlowsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dataFlowsDTO,
     * or with status {@code 400 (Bad Request)} if the dataFlowsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dataFlowsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/data-flows")
    public ResponseEntity<DataFlowsDTO> updateDataFlows(@RequestBody DataFlowsDTO dataFlowsDTO) throws URISyntaxException {
        log.debug("REST request to update DataFlows : {}", dataFlowsDTO);
        if (dataFlowsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DataFlowsDTO result = dataFlowsService.save(dataFlowsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dataFlowsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /data-flows} : get all the dataFlows.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dataFlows in body.
     */
    @GetMapping("/data-flows")
    public ResponseEntity<List<DataFlowsDTO>> getAllDataFlows(DataFlowsCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DataFlows by criteria: {}", criteria);
        Page<DataFlowsDTO> page = dataFlowsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /data-flows/count} : count all the dataFlows.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/data-flows/count")
    public ResponseEntity<Long> countDataFlows(DataFlowsCriteria criteria) {
        log.debug("REST request to count DataFlows by criteria: {}", criteria);
        return ResponseEntity.ok().body(dataFlowsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /data-flows/:id} : get the "id" dataFlows.
     *
     * @param id the id of the dataFlowsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dataFlowsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/data-flows/{id}")
    public ResponseEntity<DataFlowsDTO> getDataFlows(@PathVariable Long id) {
        log.debug("REST request to get DataFlows : {}", id);
        Optional<DataFlowsDTO> dataFlowsDTO = dataFlowsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dataFlowsDTO);
    }

    /**
     * {@code DELETE  /data-flows/:id} : delete the "id" dataFlows.
     *
     * @param id the id of the dataFlowsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/data-flows/{id}")
    public ResponseEntity<Void> deleteDataFlows(@PathVariable Long id) {
        log.debug("REST request to delete DataFlows : {}", id);
        dataFlowsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
