package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.DsCollations;
import com.mycompany.myapp.domain.DsDbmsTypes;
import com.mycompany.myapp.repository.DsCollationsRepository;
import com.mycompany.myapp.service.DsCollationsService;
import com.mycompany.myapp.service.dto.DsCollationsDTO;
import com.mycompany.myapp.service.mapper.DsCollationsMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.DsCollationsCriteria;
import com.mycompany.myapp.service.DsCollationsQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DsCollationsResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class DsCollationsResourceIT {

    private static final String DEFAULT_COLLATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COLLATION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COLLATION_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_COLLATION_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private DsCollationsRepository dsCollationsRepository;

    @Autowired
    private DsCollationsMapper dsCollationsMapper;

    @Autowired
    private DsCollationsService dsCollationsService;

    @Autowired
    private DsCollationsQueryService dsCollationsQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restDsCollationsMockMvc;

    private DsCollations dsCollations;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DsCollationsResource dsCollationsResource = new DsCollationsResource(dsCollationsService, dsCollationsQueryService);
        this.restDsCollationsMockMvc = MockMvcBuilders.standaloneSetup(dsCollationsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DsCollations createEntity(EntityManager em) {
        DsCollations dsCollations = new DsCollations()
            .collationName(DEFAULT_COLLATION_NAME)
            .collationDescription(DEFAULT_COLLATION_DESCRIPTION);
        return dsCollations;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DsCollations createUpdatedEntity(EntityManager em) {
        DsCollations dsCollations = new DsCollations()
            .collationName(UPDATED_COLLATION_NAME)
            .collationDescription(UPDATED_COLLATION_DESCRIPTION);
        return dsCollations;
    }

    @BeforeEach
    public void initTest() {
        dsCollations = createEntity(em);
    }

    @Test
    @Transactional
    public void createDsCollations() throws Exception {
        int databaseSizeBeforeCreate = dsCollationsRepository.findAll().size();

        // Create the DsCollations
        DsCollationsDTO dsCollationsDTO = dsCollationsMapper.toDto(dsCollations);
        restDsCollationsMockMvc.perform(post("/api/ds-collations")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dsCollationsDTO)))
            .andExpect(status().isCreated());

        // Validate the DsCollations in the database
        List<DsCollations> dsCollationsList = dsCollationsRepository.findAll();
        assertThat(dsCollationsList).hasSize(databaseSizeBeforeCreate + 1);
        DsCollations testDsCollations = dsCollationsList.get(dsCollationsList.size() - 1);
        assertThat(testDsCollations.getCollationName()).isEqualTo(DEFAULT_COLLATION_NAME);
        assertThat(testDsCollations.getCollationDescription()).isEqualTo(DEFAULT_COLLATION_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createDsCollationsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dsCollationsRepository.findAll().size();

        // Create the DsCollations with an existing ID
        dsCollations.setId(1L);
        DsCollationsDTO dsCollationsDTO = dsCollationsMapper.toDto(dsCollations);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDsCollationsMockMvc.perform(post("/api/ds-collations")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dsCollationsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DsCollations in the database
        List<DsCollations> dsCollationsList = dsCollationsRepository.findAll();
        assertThat(dsCollationsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDsCollations() throws Exception {
        // Initialize the database
        dsCollationsRepository.saveAndFlush(dsCollations);

        // Get all the dsCollationsList
        restDsCollationsMockMvc.perform(get("/api/ds-collations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dsCollations.getId().intValue())))
            .andExpect(jsonPath("$.[*].collationName").value(hasItem(DEFAULT_COLLATION_NAME)))
            .andExpect(jsonPath("$.[*].collationDescription").value(hasItem(DEFAULT_COLLATION_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getDsCollations() throws Exception {
        // Initialize the database
        dsCollationsRepository.saveAndFlush(dsCollations);

        // Get the dsCollations
        restDsCollationsMockMvc.perform(get("/api/ds-collations/{id}", dsCollations.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dsCollations.getId().intValue()))
            .andExpect(jsonPath("$.collationName").value(DEFAULT_COLLATION_NAME))
            .andExpect(jsonPath("$.collationDescription").value(DEFAULT_COLLATION_DESCRIPTION));
    }


    @Test
    @Transactional
    public void getDsCollationsByIdFiltering() throws Exception {
        // Initialize the database
        dsCollationsRepository.saveAndFlush(dsCollations);

        Long id = dsCollations.getId();

        defaultDsCollationsShouldBeFound("id.equals=" + id);
        defaultDsCollationsShouldNotBeFound("id.notEquals=" + id);

        defaultDsCollationsShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDsCollationsShouldNotBeFound("id.greaterThan=" + id);

        defaultDsCollationsShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDsCollationsShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDsCollationsByCollationNameIsEqualToSomething() throws Exception {
        // Initialize the database
        dsCollationsRepository.saveAndFlush(dsCollations);

        // Get all the dsCollationsList where collationName equals to DEFAULT_COLLATION_NAME
        defaultDsCollationsShouldBeFound("collationName.equals=" + DEFAULT_COLLATION_NAME);

        // Get all the dsCollationsList where collationName equals to UPDATED_COLLATION_NAME
        defaultDsCollationsShouldNotBeFound("collationName.equals=" + UPDATED_COLLATION_NAME);
    }

    @Test
    @Transactional
    public void getAllDsCollationsByCollationNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dsCollationsRepository.saveAndFlush(dsCollations);

        // Get all the dsCollationsList where collationName not equals to DEFAULT_COLLATION_NAME
        defaultDsCollationsShouldNotBeFound("collationName.notEquals=" + DEFAULT_COLLATION_NAME);

        // Get all the dsCollationsList where collationName not equals to UPDATED_COLLATION_NAME
        defaultDsCollationsShouldBeFound("collationName.notEquals=" + UPDATED_COLLATION_NAME);
    }

    @Test
    @Transactional
    public void getAllDsCollationsByCollationNameIsInShouldWork() throws Exception {
        // Initialize the database
        dsCollationsRepository.saveAndFlush(dsCollations);

        // Get all the dsCollationsList where collationName in DEFAULT_COLLATION_NAME or UPDATED_COLLATION_NAME
        defaultDsCollationsShouldBeFound("collationName.in=" + DEFAULT_COLLATION_NAME + "," + UPDATED_COLLATION_NAME);

        // Get all the dsCollationsList where collationName equals to UPDATED_COLLATION_NAME
        defaultDsCollationsShouldNotBeFound("collationName.in=" + UPDATED_COLLATION_NAME);
    }

    @Test
    @Transactional
    public void getAllDsCollationsByCollationNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        dsCollationsRepository.saveAndFlush(dsCollations);

        // Get all the dsCollationsList where collationName is not null
        defaultDsCollationsShouldBeFound("collationName.specified=true");

        // Get all the dsCollationsList where collationName is null
        defaultDsCollationsShouldNotBeFound("collationName.specified=false");
    }
                @Test
    @Transactional
    public void getAllDsCollationsByCollationNameContainsSomething() throws Exception {
        // Initialize the database
        dsCollationsRepository.saveAndFlush(dsCollations);

        // Get all the dsCollationsList where collationName contains DEFAULT_COLLATION_NAME
        defaultDsCollationsShouldBeFound("collationName.contains=" + DEFAULT_COLLATION_NAME);

        // Get all the dsCollationsList where collationName contains UPDATED_COLLATION_NAME
        defaultDsCollationsShouldNotBeFound("collationName.contains=" + UPDATED_COLLATION_NAME);
    }

    @Test
    @Transactional
    public void getAllDsCollationsByCollationNameNotContainsSomething() throws Exception {
        // Initialize the database
        dsCollationsRepository.saveAndFlush(dsCollations);

        // Get all the dsCollationsList where collationName does not contain DEFAULT_COLLATION_NAME
        defaultDsCollationsShouldNotBeFound("collationName.doesNotContain=" + DEFAULT_COLLATION_NAME);

        // Get all the dsCollationsList where collationName does not contain UPDATED_COLLATION_NAME
        defaultDsCollationsShouldBeFound("collationName.doesNotContain=" + UPDATED_COLLATION_NAME);
    }


    @Test
    @Transactional
    public void getAllDsCollationsByCollationDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        dsCollationsRepository.saveAndFlush(dsCollations);

        // Get all the dsCollationsList where collationDescription equals to DEFAULT_COLLATION_DESCRIPTION
        defaultDsCollationsShouldBeFound("collationDescription.equals=" + DEFAULT_COLLATION_DESCRIPTION);

        // Get all the dsCollationsList where collationDescription equals to UPDATED_COLLATION_DESCRIPTION
        defaultDsCollationsShouldNotBeFound("collationDescription.equals=" + UPDATED_COLLATION_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDsCollationsByCollationDescriptionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dsCollationsRepository.saveAndFlush(dsCollations);

        // Get all the dsCollationsList where collationDescription not equals to DEFAULT_COLLATION_DESCRIPTION
        defaultDsCollationsShouldNotBeFound("collationDescription.notEquals=" + DEFAULT_COLLATION_DESCRIPTION);

        // Get all the dsCollationsList where collationDescription not equals to UPDATED_COLLATION_DESCRIPTION
        defaultDsCollationsShouldBeFound("collationDescription.notEquals=" + UPDATED_COLLATION_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDsCollationsByCollationDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        dsCollationsRepository.saveAndFlush(dsCollations);

        // Get all the dsCollationsList where collationDescription in DEFAULT_COLLATION_DESCRIPTION or UPDATED_COLLATION_DESCRIPTION
        defaultDsCollationsShouldBeFound("collationDescription.in=" + DEFAULT_COLLATION_DESCRIPTION + "," + UPDATED_COLLATION_DESCRIPTION);

        // Get all the dsCollationsList where collationDescription equals to UPDATED_COLLATION_DESCRIPTION
        defaultDsCollationsShouldNotBeFound("collationDescription.in=" + UPDATED_COLLATION_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDsCollationsByCollationDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        dsCollationsRepository.saveAndFlush(dsCollations);

        // Get all the dsCollationsList where collationDescription is not null
        defaultDsCollationsShouldBeFound("collationDescription.specified=true");

        // Get all the dsCollationsList where collationDescription is null
        defaultDsCollationsShouldNotBeFound("collationDescription.specified=false");
    }
                @Test
    @Transactional
    public void getAllDsCollationsByCollationDescriptionContainsSomething() throws Exception {
        // Initialize the database
        dsCollationsRepository.saveAndFlush(dsCollations);

        // Get all the dsCollationsList where collationDescription contains DEFAULT_COLLATION_DESCRIPTION
        defaultDsCollationsShouldBeFound("collationDescription.contains=" + DEFAULT_COLLATION_DESCRIPTION);

        // Get all the dsCollationsList where collationDescription contains UPDATED_COLLATION_DESCRIPTION
        defaultDsCollationsShouldNotBeFound("collationDescription.contains=" + UPDATED_COLLATION_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDsCollationsByCollationDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        dsCollationsRepository.saveAndFlush(dsCollations);

        // Get all the dsCollationsList where collationDescription does not contain DEFAULT_COLLATION_DESCRIPTION
        defaultDsCollationsShouldNotBeFound("collationDescription.doesNotContain=" + DEFAULT_COLLATION_DESCRIPTION);

        // Get all the dsCollationsList where collationDescription does not contain UPDATED_COLLATION_DESCRIPTION
        defaultDsCollationsShouldBeFound("collationDescription.doesNotContain=" + UPDATED_COLLATION_DESCRIPTION);
    }


    @Test
    @Transactional
    public void getAllDsCollationsByDbmsTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        dsCollationsRepository.saveAndFlush(dsCollations);
        DsDbmsTypes dbmsType = DsDbmsTypesResourceIT.createEntity(em);
        em.persist(dbmsType);
        em.flush();
        dsCollations.setDbmsType(dbmsType);
        dsCollationsRepository.saveAndFlush(dsCollations);
        Long dbmsTypeId = dbmsType.getId();

        // Get all the dsCollationsList where dbmsType equals to dbmsTypeId
        defaultDsCollationsShouldBeFound("dbmsTypeId.equals=" + dbmsTypeId);

        // Get all the dsCollationsList where dbmsType equals to dbmsTypeId + 1
        defaultDsCollationsShouldNotBeFound("dbmsTypeId.equals=" + (dbmsTypeId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDsCollationsShouldBeFound(String filter) throws Exception {
        restDsCollationsMockMvc.perform(get("/api/ds-collations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dsCollations.getId().intValue())))
            .andExpect(jsonPath("$.[*].collationName").value(hasItem(DEFAULT_COLLATION_NAME)))
            .andExpect(jsonPath("$.[*].collationDescription").value(hasItem(DEFAULT_COLLATION_DESCRIPTION)));

        // Check, that the count call also returns 1
        restDsCollationsMockMvc.perform(get("/api/ds-collations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDsCollationsShouldNotBeFound(String filter) throws Exception {
        restDsCollationsMockMvc.perform(get("/api/ds-collations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDsCollationsMockMvc.perform(get("/api/ds-collations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDsCollations() throws Exception {
        // Get the dsCollations
        restDsCollationsMockMvc.perform(get("/api/ds-collations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDsCollations() throws Exception {
        // Initialize the database
        dsCollationsRepository.saveAndFlush(dsCollations);

        int databaseSizeBeforeUpdate = dsCollationsRepository.findAll().size();

        // Update the dsCollations
        DsCollations updatedDsCollations = dsCollationsRepository.findById(dsCollations.getId()).get();
        // Disconnect from session so that the updates on updatedDsCollations are not directly saved in db
        em.detach(updatedDsCollations);
        updatedDsCollations
            .collationName(UPDATED_COLLATION_NAME)
            .collationDescription(UPDATED_COLLATION_DESCRIPTION);
        DsCollationsDTO dsCollationsDTO = dsCollationsMapper.toDto(updatedDsCollations);

        restDsCollationsMockMvc.perform(put("/api/ds-collations")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dsCollationsDTO)))
            .andExpect(status().isOk());

        // Validate the DsCollations in the database
        List<DsCollations> dsCollationsList = dsCollationsRepository.findAll();
        assertThat(dsCollationsList).hasSize(databaseSizeBeforeUpdate);
        DsCollations testDsCollations = dsCollationsList.get(dsCollationsList.size() - 1);
        assertThat(testDsCollations.getCollationName()).isEqualTo(UPDATED_COLLATION_NAME);
        assertThat(testDsCollations.getCollationDescription()).isEqualTo(UPDATED_COLLATION_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingDsCollations() throws Exception {
        int databaseSizeBeforeUpdate = dsCollationsRepository.findAll().size();

        // Create the DsCollations
        DsCollationsDTO dsCollationsDTO = dsCollationsMapper.toDto(dsCollations);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDsCollationsMockMvc.perform(put("/api/ds-collations")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dsCollationsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DsCollations in the database
        List<DsCollations> dsCollationsList = dsCollationsRepository.findAll();
        assertThat(dsCollationsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDsCollations() throws Exception {
        // Initialize the database
        dsCollationsRepository.saveAndFlush(dsCollations);

        int databaseSizeBeforeDelete = dsCollationsRepository.findAll().size();

        // Delete the dsCollations
        restDsCollationsMockMvc.perform(delete("/api/ds-collations/{id}", dsCollations.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DsCollations> dsCollationsList = dsCollationsRepository.findAll();
        assertThat(dsCollationsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
