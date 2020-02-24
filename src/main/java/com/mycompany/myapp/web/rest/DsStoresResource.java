package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.DsStoresService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.DsStoresDTO;
import com.mycompany.myapp.service.dto.DsStoresCriteria;
import com.mycompany.myapp.service.DsStoresQueryService;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.DsStores}.
 */
@RestController
@RequestMapping("/api")
public class DsStoresResource {

    private final Logger log = LoggerFactory.getLogger(DsStoresResource.class);

    private static final String ENTITY_NAME = "dsStores";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DsStoresService dsStoresService;

    private final DsStoresQueryService dsStoresQueryService;

    public DsStoresResource(DsStoresService dsStoresService, DsStoresQueryService dsStoresQueryService) {
        this.dsStoresService = dsStoresService;
        this.dsStoresQueryService = dsStoresQueryService;
    }

    /**
     * {@code POST  /ds-stores} : Create a new dsStores.
     *
     * @param dsStoresDTO the dsStoresDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dsStoresDTO, or with status {@code 400 (Bad Request)} if the dsStores has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ds-stores")
    public ResponseEntity<DsStoresDTO> createDsStores(@RequestBody DsStoresDTO dsStoresDTO) throws URISyntaxException {
        log.debug("REST request to save DsStores : {}", dsStoresDTO);
        if (dsStoresDTO.getId() != null) {
            throw new BadRequestAlertException("A new dsStores cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DsStoresDTO result = dsStoresService.save(dsStoresDTO);
        return ResponseEntity.created(new URI("/api/ds-stores/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ds-stores} : Updates an existing dsStores.
     *
     * @param dsStoresDTO the dsStoresDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dsStoresDTO,
     * or with status {@code 400 (Bad Request)} if the dsStoresDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dsStoresDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ds-stores")
    public ResponseEntity<DsStoresDTO> updateDsStores(@RequestBody DsStoresDTO dsStoresDTO) throws URISyntaxException {
        log.debug("REST request to update DsStores : {}", dsStoresDTO);
        if (dsStoresDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DsStoresDTO result = dsStoresService.save(dsStoresDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dsStoresDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ds-stores} : get all the dsStores.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dsStores in body.
     */
    @GetMapping("/ds-stores")
    public ResponseEntity<List<DsStoresDTO>> getAllDsStores(DsStoresCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DsStores by criteria: {}", criteria);
        Page<DsStoresDTO> page = dsStoresQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ds-stores/count} : count all the dsStores.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/ds-stores/count")
    public ResponseEntity<Long> countDsStores(DsStoresCriteria criteria) {
        log.debug("REST request to count DsStores by criteria: {}", criteria);
        return ResponseEntity.ok().body(dsStoresQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /ds-stores/:id} : get the "id" dsStores.
     *
     * @param id the id of the dsStoresDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dsStoresDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ds-stores/{id}")
    public ResponseEntity<DsStoresDTO> getDsStores(@PathVariable Long id) {
        log.debug("REST request to get DsStores : {}", id);
        Optional<DsStoresDTO> dsStoresDTO = dsStoresService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dsStoresDTO);
    }

    /**
     * {@code DELETE  /ds-stores/:id} : delete the "id" dsStores.
     *
     * @param id the id of the dsStoresDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ds-stores/{id}")
    public ResponseEntity<Void> deleteDsStores(@PathVariable Long id) {
        log.debug("REST request to delete DsStores : {}", id);
        dsStoresService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
