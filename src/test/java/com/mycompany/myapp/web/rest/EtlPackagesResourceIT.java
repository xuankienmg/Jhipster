package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.EtlPackages;
import com.mycompany.myapp.repository.EtlPackagesRepository;
import com.mycompany.myapp.service.EtlPackagesService;
import com.mycompany.myapp.service.dto.EtlPackagesDTO;
import com.mycompany.myapp.service.mapper.EtlPackagesMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.EtlPackagesCriteria;
import com.mycompany.myapp.service.EtlPackagesQueryService;

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
 * Integration tests for the {@link EtlPackagesResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class EtlPackagesResourceIT {

    private static final String DEFAULT_ETL_PKG_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ETL_PKG_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ETL_PKG_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_ETL_PKG_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_ETL_PKG_SCHEDULE = "AAAAAAAAAA";
    private static final String UPDATED_ETL_PKG_SCHEDULE = "BBBBBBBBBB";

    @Autowired
    private EtlPackagesRepository etlPackagesRepository;

    @Autowired
    private EtlPackagesMapper etlPackagesMapper;

    @Autowired
    private EtlPackagesService etlPackagesService;

    @Autowired
    private EtlPackagesQueryService etlPackagesQueryService;

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

    private MockMvc restEtlPackagesMockMvc;

    private EtlPackages etlPackages;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EtlPackagesResource etlPackagesResource = new EtlPackagesResource(etlPackagesService, etlPackagesQueryService);
        this.restEtlPackagesMockMvc = MockMvcBuilders.standaloneSetup(etlPackagesResource)
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
    public static EtlPackages createEntity(EntityManager em) {
        EtlPackages etlPackages = new EtlPackages()
            .etlPkgName(DEFAULT_ETL_PKG_NAME)
            .etlPkgDescription(DEFAULT_ETL_PKG_DESCRIPTION)
            .etlPkgSchedule(DEFAULT_ETL_PKG_SCHEDULE);
        return etlPackages;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EtlPackages createUpdatedEntity(EntityManager em) {
        EtlPackages etlPackages = new EtlPackages()
            .etlPkgName(UPDATED_ETL_PKG_NAME)
            .etlPkgDescription(UPDATED_ETL_PKG_DESCRIPTION)
            .etlPkgSchedule(UPDATED_ETL_PKG_SCHEDULE);
        return etlPackages;
    }

    @BeforeEach
    public void initTest() {
        etlPackages = createEntity(em);
    }

    @Test
    @Transactional
    public void createEtlPackages() throws Exception {
        int databaseSizeBeforeCreate = etlPackagesRepository.findAll().size();

        // Create the EtlPackages
        EtlPackagesDTO etlPackagesDTO = etlPackagesMapper.toDto(etlPackages);
        restEtlPackagesMockMvc.perform(post("/api/etl-packages")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etlPackagesDTO)))
            .andExpect(status().isCreated());

        // Validate the EtlPackages in the database
        List<EtlPackages> etlPackagesList = etlPackagesRepository.findAll();
        assertThat(etlPackagesList).hasSize(databaseSizeBeforeCreate + 1);
        EtlPackages testEtlPackages = etlPackagesList.get(etlPackagesList.size() - 1);
        assertThat(testEtlPackages.getEtlPkgName()).isEqualTo(DEFAULT_ETL_PKG_NAME);
        assertThat(testEtlPackages.getEtlPkgDescription()).isEqualTo(DEFAULT_ETL_PKG_DESCRIPTION);
        assertThat(testEtlPackages.getEtlPkgSchedule()).isEqualTo(DEFAULT_ETL_PKG_SCHEDULE);
    }

    @Test
    @Transactional
    public void createEtlPackagesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = etlPackagesRepository.findAll().size();

        // Create the EtlPackages with an existing ID
        etlPackages.setId(1L);
        EtlPackagesDTO etlPackagesDTO = etlPackagesMapper.toDto(etlPackages);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEtlPackagesMockMvc.perform(post("/api/etl-packages")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etlPackagesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtlPackages in the database
        List<EtlPackages> etlPackagesList = etlPackagesRepository.findAll();
        assertThat(etlPackagesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEtlPackages() throws Exception {
        // Initialize the database
        etlPackagesRepository.saveAndFlush(etlPackages);

        // Get all the etlPackagesList
        restEtlPackagesMockMvc.perform(get("/api/etl-packages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(etlPackages.getId().intValue())))
            .andExpect(jsonPath("$.[*].etlPkgName").value(hasItem(DEFAULT_ETL_PKG_NAME)))
            .andExpect(jsonPath("$.[*].etlPkgDescription").value(hasItem(DEFAULT_ETL_PKG_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].etlPkgSchedule").value(hasItem(DEFAULT_ETL_PKG_SCHEDULE)));
    }
    
    @Test
    @Transactional
    public void getEtlPackages() throws Exception {
        // Initialize the database
        etlPackagesRepository.saveAndFlush(etlPackages);

        // Get the etlPackages
        restEtlPackagesMockMvc.perform(get("/api/etl-packages/{id}", etlPackages.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(etlPackages.getId().intValue()))
            .andExpect(jsonPath("$.etlPkgName").value(DEFAULT_ETL_PKG_NAME))
            .andExpect(jsonPath("$.etlPkgDescription").value(DEFAULT_ETL_PKG_DESCRIPTION))
            .andExpect(jsonPath("$.etlPkgSchedule").value(DEFAULT_ETL_PKG_SCHEDULE));
    }


    @Test
    @Transactional
    public void getEtlPackagesByIdFiltering() throws Exception {
        // Initialize the database
        etlPackagesRepository.saveAndFlush(etlPackages);

        Long id = etlPackages.getId();

        defaultEtlPackagesShouldBeFound("id.equals=" + id);
        defaultEtlPackagesShouldNotBeFound("id.notEquals=" + id);

        defaultEtlPackagesShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultEtlPackagesShouldNotBeFound("id.greaterThan=" + id);

        defaultEtlPackagesShouldBeFound("id.lessThanOrEqual=" + id);
        defaultEtlPackagesShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllEtlPackagesByEtlPkgNameIsEqualToSomething() throws Exception {
        // Initialize the database
        etlPackagesRepository.saveAndFlush(etlPackages);

        // Get all the etlPackagesList where etlPkgName equals to DEFAULT_ETL_PKG_NAME
        defaultEtlPackagesShouldBeFound("etlPkgName.equals=" + DEFAULT_ETL_PKG_NAME);

        // Get all the etlPackagesList where etlPkgName equals to UPDATED_ETL_PKG_NAME
        defaultEtlPackagesShouldNotBeFound("etlPkgName.equals=" + UPDATED_ETL_PKG_NAME);
    }

    @Test
    @Transactional
    public void getAllEtlPackagesByEtlPkgNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        etlPackagesRepository.saveAndFlush(etlPackages);

        // Get all the etlPackagesList where etlPkgName not equals to DEFAULT_ETL_PKG_NAME
        defaultEtlPackagesShouldNotBeFound("etlPkgName.notEquals=" + DEFAULT_ETL_PKG_NAME);

        // Get all the etlPackagesList where etlPkgName not equals to UPDATED_ETL_PKG_NAME
        defaultEtlPackagesShouldBeFound("etlPkgName.notEquals=" + UPDATED_ETL_PKG_NAME);
    }

    @Test
    @Transactional
    public void getAllEtlPackagesByEtlPkgNameIsInShouldWork() throws Exception {
        // Initialize the database
        etlPackagesRepository.saveAndFlush(etlPackages);

        // Get all the etlPackagesList where etlPkgName in DEFAULT_ETL_PKG_NAME or UPDATED_ETL_PKG_NAME
        defaultEtlPackagesShouldBeFound("etlPkgName.in=" + DEFAULT_ETL_PKG_NAME + "," + UPDATED_ETL_PKG_NAME);

        // Get all the etlPackagesList where etlPkgName equals to UPDATED_ETL_PKG_NAME
        defaultEtlPackagesShouldNotBeFound("etlPkgName.in=" + UPDATED_ETL_PKG_NAME);
    }

    @Test
    @Transactional
    public void getAllEtlPackagesByEtlPkgNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        etlPackagesRepository.saveAndFlush(etlPackages);

        // Get all the etlPackagesList where etlPkgName is not null
        defaultEtlPackagesShouldBeFound("etlPkgName.specified=true");

        // Get all the etlPackagesList where etlPkgName is null
        defaultEtlPackagesShouldNotBeFound("etlPkgName.specified=false");
    }
                @Test
    @Transactional
    public void getAllEtlPackagesByEtlPkgNameContainsSomething() throws Exception {
        // Initialize the database
        etlPackagesRepository.saveAndFlush(etlPackages);

        // Get all the etlPackagesList where etlPkgName contains DEFAULT_ETL_PKG_NAME
        defaultEtlPackagesShouldBeFound("etlPkgName.contains=" + DEFAULT_ETL_PKG_NAME);

        // Get all the etlPackagesList where etlPkgName contains UPDATED_ETL_PKG_NAME
        defaultEtlPackagesShouldNotBeFound("etlPkgName.contains=" + UPDATED_ETL_PKG_NAME);
    }

    @Test
    @Transactional
    public void getAllEtlPackagesByEtlPkgNameNotContainsSomething() throws Exception {
        // Initialize the database
        etlPackagesRepository.saveAndFlush(etlPackages);

        // Get all the etlPackagesList where etlPkgName does not contain DEFAULT_ETL_PKG_NAME
        defaultEtlPackagesShouldNotBeFound("etlPkgName.doesNotContain=" + DEFAULT_ETL_PKG_NAME);

        // Get all the etlPackagesList where etlPkgName does not contain UPDATED_ETL_PKG_NAME
        defaultEtlPackagesShouldBeFound("etlPkgName.doesNotContain=" + UPDATED_ETL_PKG_NAME);
    }


    @Test
    @Transactional
    public void getAllEtlPackagesByEtlPkgDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        etlPackagesRepository.saveAndFlush(etlPackages);

        // Get all the etlPackagesList where etlPkgDescription equals to DEFAULT_ETL_PKG_DESCRIPTION
        defaultEtlPackagesShouldBeFound("etlPkgDescription.equals=" + DEFAULT_ETL_PKG_DESCRIPTION);

        // Get all the etlPackagesList where etlPkgDescription equals to UPDATED_ETL_PKG_DESCRIPTION
        defaultEtlPackagesShouldNotBeFound("etlPkgDescription.equals=" + UPDATED_ETL_PKG_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllEtlPackagesByEtlPkgDescriptionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        etlPackagesRepository.saveAndFlush(etlPackages);

        // Get all the etlPackagesList where etlPkgDescription not equals to DEFAULT_ETL_PKG_DESCRIPTION
        defaultEtlPackagesShouldNotBeFound("etlPkgDescription.notEquals=" + DEFAULT_ETL_PKG_DESCRIPTION);

        // Get all the etlPackagesList where etlPkgDescription not equals to UPDATED_ETL_PKG_DESCRIPTION
        defaultEtlPackagesShouldBeFound("etlPkgDescription.notEquals=" + UPDATED_ETL_PKG_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllEtlPackagesByEtlPkgDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        etlPackagesRepository.saveAndFlush(etlPackages);

        // Get all the etlPackagesList where etlPkgDescription in DEFAULT_ETL_PKG_DESCRIPTION or UPDATED_ETL_PKG_DESCRIPTION
        defaultEtlPackagesShouldBeFound("etlPkgDescription.in=" + DEFAULT_ETL_PKG_DESCRIPTION + "," + UPDATED_ETL_PKG_DESCRIPTION);

        // Get all the etlPackagesList where etlPkgDescription equals to UPDATED_ETL_PKG_DESCRIPTION
        defaultEtlPackagesShouldNotBeFound("etlPkgDescription.in=" + UPDATED_ETL_PKG_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllEtlPackagesByEtlPkgDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        etlPackagesRepository.saveAndFlush(etlPackages);

        // Get all the etlPackagesList where etlPkgDescription is not null
        defaultEtlPackagesShouldBeFound("etlPkgDescription.specified=true");

        // Get all the etlPackagesList where etlPkgDescription is null
        defaultEtlPackagesShouldNotBeFound("etlPkgDescription.specified=false");
    }
                @Test
    @Transactional
    public void getAllEtlPackagesByEtlPkgDescriptionContainsSomething() throws Exception {
        // Initialize the database
        etlPackagesRepository.saveAndFlush(etlPackages);

        // Get all the etlPackagesList where etlPkgDescription contains DEFAULT_ETL_PKG_DESCRIPTION
        defaultEtlPackagesShouldBeFound("etlPkgDescription.contains=" + DEFAULT_ETL_PKG_DESCRIPTION);

        // Get all the etlPackagesList where etlPkgDescription contains UPDATED_ETL_PKG_DESCRIPTION
        defaultEtlPackagesShouldNotBeFound("etlPkgDescription.contains=" + UPDATED_ETL_PKG_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllEtlPackagesByEtlPkgDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        etlPackagesRepository.saveAndFlush(etlPackages);

        // Get all the etlPackagesList where etlPkgDescription does not contain DEFAULT_ETL_PKG_DESCRIPTION
        defaultEtlPackagesShouldNotBeFound("etlPkgDescription.doesNotContain=" + DEFAULT_ETL_PKG_DESCRIPTION);

        // Get all the etlPackagesList where etlPkgDescription does not contain UPDATED_ETL_PKG_DESCRIPTION
        defaultEtlPackagesShouldBeFound("etlPkgDescription.doesNotContain=" + UPDATED_ETL_PKG_DESCRIPTION);
    }


    @Test
    @Transactional
    public void getAllEtlPackagesByEtlPkgScheduleIsEqualToSomething() throws Exception {
        // Initialize the database
        etlPackagesRepository.saveAndFlush(etlPackages);

        // Get all the etlPackagesList where etlPkgSchedule equals to DEFAULT_ETL_PKG_SCHEDULE
        defaultEtlPackagesShouldBeFound("etlPkgSchedule.equals=" + DEFAULT_ETL_PKG_SCHEDULE);

        // Get all the etlPackagesList where etlPkgSchedule equals to UPDATED_ETL_PKG_SCHEDULE
        defaultEtlPackagesShouldNotBeFound("etlPkgSchedule.equals=" + UPDATED_ETL_PKG_SCHEDULE);
    }

    @Test
    @Transactional
    public void getAllEtlPackagesByEtlPkgScheduleIsNotEqualToSomething() throws Exception {
        // Initialize the database
        etlPackagesRepository.saveAndFlush(etlPackages);

        // Get all the etlPackagesList where etlPkgSchedule not equals to DEFAULT_ETL_PKG_SCHEDULE
        defaultEtlPackagesShouldNotBeFound("etlPkgSchedule.notEquals=" + DEFAULT_ETL_PKG_SCHEDULE);

        // Get all the etlPackagesList where etlPkgSchedule not equals to UPDATED_ETL_PKG_SCHEDULE
        defaultEtlPackagesShouldBeFound("etlPkgSchedule.notEquals=" + UPDATED_ETL_PKG_SCHEDULE);
    }

    @Test
    @Transactional
    public void getAllEtlPackagesByEtlPkgScheduleIsInShouldWork() throws Exception {
        // Initialize the database
        etlPackagesRepository.saveAndFlush(etlPackages);

        // Get all the etlPackagesList where etlPkgSchedule in DEFAULT_ETL_PKG_SCHEDULE or UPDATED_ETL_PKG_SCHEDULE
        defaultEtlPackagesShouldBeFound("etlPkgSchedule.in=" + DEFAULT_ETL_PKG_SCHEDULE + "," + UPDATED_ETL_PKG_SCHEDULE);

        // Get all the etlPackagesList where etlPkgSchedule equals to UPDATED_ETL_PKG_SCHEDULE
        defaultEtlPackagesShouldNotBeFound("etlPkgSchedule.in=" + UPDATED_ETL_PKG_SCHEDULE);
    }

    @Test
    @Transactional
    public void getAllEtlPackagesByEtlPkgScheduleIsNullOrNotNull() throws Exception {
        // Initialize the database
        etlPackagesRepository.saveAndFlush(etlPackages);

        // Get all the etlPackagesList where etlPkgSchedule is not null
        defaultEtlPackagesShouldBeFound("etlPkgSchedule.specified=true");

        // Get all the etlPackagesList where etlPkgSchedule is null
        defaultEtlPackagesShouldNotBeFound("etlPkgSchedule.specified=false");
    }
                @Test
    @Transactional
    public void getAllEtlPackagesByEtlPkgScheduleContainsSomething() throws Exception {
        // Initialize the database
        etlPackagesRepository.saveAndFlush(etlPackages);

        // Get all the etlPackagesList where etlPkgSchedule contains DEFAULT_ETL_PKG_SCHEDULE
        defaultEtlPackagesShouldBeFound("etlPkgSchedule.contains=" + DEFAULT_ETL_PKG_SCHEDULE);

        // Get all the etlPackagesList where etlPkgSchedule contains UPDATED_ETL_PKG_SCHEDULE
        defaultEtlPackagesShouldNotBeFound("etlPkgSchedule.contains=" + UPDATED_ETL_PKG_SCHEDULE);
    }

    @Test
    @Transactional
    public void getAllEtlPackagesByEtlPkgScheduleNotContainsSomething() throws Exception {
        // Initialize the database
        etlPackagesRepository.saveAndFlush(etlPackages);

        // Get all the etlPackagesList where etlPkgSchedule does not contain DEFAULT_ETL_PKG_SCHEDULE
        defaultEtlPackagesShouldNotBeFound("etlPkgSchedule.doesNotContain=" + DEFAULT_ETL_PKG_SCHEDULE);

        // Get all the etlPackagesList where etlPkgSchedule does not contain UPDATED_ETL_PKG_SCHEDULE
        defaultEtlPackagesShouldBeFound("etlPkgSchedule.doesNotContain=" + UPDATED_ETL_PKG_SCHEDULE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEtlPackagesShouldBeFound(String filter) throws Exception {
        restEtlPackagesMockMvc.perform(get("/api/etl-packages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(etlPackages.getId().intValue())))
            .andExpect(jsonPath("$.[*].etlPkgName").value(hasItem(DEFAULT_ETL_PKG_NAME)))
            .andExpect(jsonPath("$.[*].etlPkgDescription").value(hasItem(DEFAULT_ETL_PKG_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].etlPkgSchedule").value(hasItem(DEFAULT_ETL_PKG_SCHEDULE)));

        // Check, that the count call also returns 1
        restEtlPackagesMockMvc.perform(get("/api/etl-packages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEtlPackagesShouldNotBeFound(String filter) throws Exception {
        restEtlPackagesMockMvc.perform(get("/api/etl-packages?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEtlPackagesMockMvc.perform(get("/api/etl-packages/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingEtlPackages() throws Exception {
        // Get the etlPackages
        restEtlPackagesMockMvc.perform(get("/api/etl-packages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEtlPackages() throws Exception {
        // Initialize the database
        etlPackagesRepository.saveAndFlush(etlPackages);

        int databaseSizeBeforeUpdate = etlPackagesRepository.findAll().size();

        // Update the etlPackages
        EtlPackages updatedEtlPackages = etlPackagesRepository.findById(etlPackages.getId()).get();
        // Disconnect from session so that the updates on updatedEtlPackages are not directly saved in db
        em.detach(updatedEtlPackages);
        updatedEtlPackages
            .etlPkgName(UPDATED_ETL_PKG_NAME)
            .etlPkgDescription(UPDATED_ETL_PKG_DESCRIPTION)
            .etlPkgSchedule(UPDATED_ETL_PKG_SCHEDULE);
        EtlPackagesDTO etlPackagesDTO = etlPackagesMapper.toDto(updatedEtlPackages);

        restEtlPackagesMockMvc.perform(put("/api/etl-packages")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etlPackagesDTO)))
            .andExpect(status().isOk());

        // Validate the EtlPackages in the database
        List<EtlPackages> etlPackagesList = etlPackagesRepository.findAll();
        assertThat(etlPackagesList).hasSize(databaseSizeBeforeUpdate);
        EtlPackages testEtlPackages = etlPackagesList.get(etlPackagesList.size() - 1);
        assertThat(testEtlPackages.getEtlPkgName()).isEqualTo(UPDATED_ETL_PKG_NAME);
        assertThat(testEtlPackages.getEtlPkgDescription()).isEqualTo(UPDATED_ETL_PKG_DESCRIPTION);
        assertThat(testEtlPackages.getEtlPkgSchedule()).isEqualTo(UPDATED_ETL_PKG_SCHEDULE);
    }

    @Test
    @Transactional
    public void updateNonExistingEtlPackages() throws Exception {
        int databaseSizeBeforeUpdate = etlPackagesRepository.findAll().size();

        // Create the EtlPackages
        EtlPackagesDTO etlPackagesDTO = etlPackagesMapper.toDto(etlPackages);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEtlPackagesMockMvc.perform(put("/api/etl-packages")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etlPackagesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtlPackages in the database
        List<EtlPackages> etlPackagesList = etlPackagesRepository.findAll();
        assertThat(etlPackagesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEtlPackages() throws Exception {
        // Initialize the database
        etlPackagesRepository.saveAndFlush(etlPackages);

        int databaseSizeBeforeDelete = etlPackagesRepository.findAll().size();

        // Delete the etlPackages
        restEtlPackagesMockMvc.perform(delete("/api/etl-packages/{id}", etlPackages.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EtlPackages> etlPackagesList = etlPackagesRepository.findAll();
        assertThat(etlPackagesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
