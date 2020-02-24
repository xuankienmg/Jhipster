package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.EtlStatus;
import com.mycompany.myapp.repository.EtlStatusRepository;
import com.mycompany.myapp.service.EtlStatusService;
import com.mycompany.myapp.service.dto.EtlStatusDTO;
import com.mycompany.myapp.service.mapper.EtlStatusMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.EtlStatusCriteria;
import com.mycompany.myapp.service.EtlStatusQueryService;

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
 * Integration tests for the {@link EtlStatusResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class EtlStatusResourceIT {

    private static final String DEFAULT_ETL_STATUS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ETL_STATUS_NAME = "BBBBBBBBBB";

    @Autowired
    private EtlStatusRepository etlStatusRepository;

    @Autowired
    private EtlStatusMapper etlStatusMapper;

    @Autowired
    private EtlStatusService etlStatusService;

    @Autowired
    private EtlStatusQueryService etlStatusQueryService;

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

    private MockMvc restEtlStatusMockMvc;

    private EtlStatus etlStatus;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EtlStatusResource etlStatusResource = new EtlStatusResource(etlStatusService, etlStatusQueryService);
        this.restEtlStatusMockMvc = MockMvcBuilders.standaloneSetup(etlStatusResource)
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
    public static EtlStatus createEntity(EntityManager em) {
        EtlStatus etlStatus = new EtlStatus()
            .etlStatusName(DEFAULT_ETL_STATUS_NAME);
        return etlStatus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EtlStatus createUpdatedEntity(EntityManager em) {
        EtlStatus etlStatus = new EtlStatus()
            .etlStatusName(UPDATED_ETL_STATUS_NAME);
        return etlStatus;
    }

    @BeforeEach
    public void initTest() {
        etlStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createEtlStatus() throws Exception {
        int databaseSizeBeforeCreate = etlStatusRepository.findAll().size();

        // Create the EtlStatus
        EtlStatusDTO etlStatusDTO = etlStatusMapper.toDto(etlStatus);
        restEtlStatusMockMvc.perform(post("/api/etl-statuses")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etlStatusDTO)))
            .andExpect(status().isCreated());

        // Validate the EtlStatus in the database
        List<EtlStatus> etlStatusList = etlStatusRepository.findAll();
        assertThat(etlStatusList).hasSize(databaseSizeBeforeCreate + 1);
        EtlStatus testEtlStatus = etlStatusList.get(etlStatusList.size() - 1);
        assertThat(testEtlStatus.getEtlStatusName()).isEqualTo(DEFAULT_ETL_STATUS_NAME);
    }

    @Test
    @Transactional
    public void createEtlStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = etlStatusRepository.findAll().size();

        // Create the EtlStatus with an existing ID
        etlStatus.setId(1L);
        EtlStatusDTO etlStatusDTO = etlStatusMapper.toDto(etlStatus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEtlStatusMockMvc.perform(post("/api/etl-statuses")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etlStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtlStatus in the database
        List<EtlStatus> etlStatusList = etlStatusRepository.findAll();
        assertThat(etlStatusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEtlStatuses() throws Exception {
        // Initialize the database
        etlStatusRepository.saveAndFlush(etlStatus);

        // Get all the etlStatusList
        restEtlStatusMockMvc.perform(get("/api/etl-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(etlStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].etlStatusName").value(hasItem(DEFAULT_ETL_STATUS_NAME)));
    }
    
    @Test
    @Transactional
    public void getEtlStatus() throws Exception {
        // Initialize the database
        etlStatusRepository.saveAndFlush(etlStatus);

        // Get the etlStatus
        restEtlStatusMockMvc.perform(get("/api/etl-statuses/{id}", etlStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(etlStatus.getId().intValue()))
            .andExpect(jsonPath("$.etlStatusName").value(DEFAULT_ETL_STATUS_NAME));
    }


    @Test
    @Transactional
    public void getEtlStatusesByIdFiltering() throws Exception {
        // Initialize the database
        etlStatusRepository.saveAndFlush(etlStatus);

        Long id = etlStatus.getId();

        defaultEtlStatusShouldBeFound("id.equals=" + id);
        defaultEtlStatusShouldNotBeFound("id.notEquals=" + id);

        defaultEtlStatusShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultEtlStatusShouldNotBeFound("id.greaterThan=" + id);

        defaultEtlStatusShouldBeFound("id.lessThanOrEqual=" + id);
        defaultEtlStatusShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllEtlStatusesByEtlStatusNameIsEqualToSomething() throws Exception {
        // Initialize the database
        etlStatusRepository.saveAndFlush(etlStatus);

        // Get all the etlStatusList where etlStatusName equals to DEFAULT_ETL_STATUS_NAME
        defaultEtlStatusShouldBeFound("etlStatusName.equals=" + DEFAULT_ETL_STATUS_NAME);

        // Get all the etlStatusList where etlStatusName equals to UPDATED_ETL_STATUS_NAME
        defaultEtlStatusShouldNotBeFound("etlStatusName.equals=" + UPDATED_ETL_STATUS_NAME);
    }

    @Test
    @Transactional
    public void getAllEtlStatusesByEtlStatusNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        etlStatusRepository.saveAndFlush(etlStatus);

        // Get all the etlStatusList where etlStatusName not equals to DEFAULT_ETL_STATUS_NAME
        defaultEtlStatusShouldNotBeFound("etlStatusName.notEquals=" + DEFAULT_ETL_STATUS_NAME);

        // Get all the etlStatusList where etlStatusName not equals to UPDATED_ETL_STATUS_NAME
        defaultEtlStatusShouldBeFound("etlStatusName.notEquals=" + UPDATED_ETL_STATUS_NAME);
    }

    @Test
    @Transactional
    public void getAllEtlStatusesByEtlStatusNameIsInShouldWork() throws Exception {
        // Initialize the database
        etlStatusRepository.saveAndFlush(etlStatus);

        // Get all the etlStatusList where etlStatusName in DEFAULT_ETL_STATUS_NAME or UPDATED_ETL_STATUS_NAME
        defaultEtlStatusShouldBeFound("etlStatusName.in=" + DEFAULT_ETL_STATUS_NAME + "," + UPDATED_ETL_STATUS_NAME);

        // Get all the etlStatusList where etlStatusName equals to UPDATED_ETL_STATUS_NAME
        defaultEtlStatusShouldNotBeFound("etlStatusName.in=" + UPDATED_ETL_STATUS_NAME);
    }

    @Test
    @Transactional
    public void getAllEtlStatusesByEtlStatusNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        etlStatusRepository.saveAndFlush(etlStatus);

        // Get all the etlStatusList where etlStatusName is not null
        defaultEtlStatusShouldBeFound("etlStatusName.specified=true");

        // Get all the etlStatusList where etlStatusName is null
        defaultEtlStatusShouldNotBeFound("etlStatusName.specified=false");
    }
                @Test
    @Transactional
    public void getAllEtlStatusesByEtlStatusNameContainsSomething() throws Exception {
        // Initialize the database
        etlStatusRepository.saveAndFlush(etlStatus);

        // Get all the etlStatusList where etlStatusName contains DEFAULT_ETL_STATUS_NAME
        defaultEtlStatusShouldBeFound("etlStatusName.contains=" + DEFAULT_ETL_STATUS_NAME);

        // Get all the etlStatusList where etlStatusName contains UPDATED_ETL_STATUS_NAME
        defaultEtlStatusShouldNotBeFound("etlStatusName.contains=" + UPDATED_ETL_STATUS_NAME);
    }

    @Test
    @Transactional
    public void getAllEtlStatusesByEtlStatusNameNotContainsSomething() throws Exception {
        // Initialize the database
        etlStatusRepository.saveAndFlush(etlStatus);

        // Get all the etlStatusList where etlStatusName does not contain DEFAULT_ETL_STATUS_NAME
        defaultEtlStatusShouldNotBeFound("etlStatusName.doesNotContain=" + DEFAULT_ETL_STATUS_NAME);

        // Get all the etlStatusList where etlStatusName does not contain UPDATED_ETL_STATUS_NAME
        defaultEtlStatusShouldBeFound("etlStatusName.doesNotContain=" + UPDATED_ETL_STATUS_NAME);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEtlStatusShouldBeFound(String filter) throws Exception {
        restEtlStatusMockMvc.perform(get("/api/etl-statuses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(etlStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].etlStatusName").value(hasItem(DEFAULT_ETL_STATUS_NAME)));

        // Check, that the count call also returns 1
        restEtlStatusMockMvc.perform(get("/api/etl-statuses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEtlStatusShouldNotBeFound(String filter) throws Exception {
        restEtlStatusMockMvc.perform(get("/api/etl-statuses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEtlStatusMockMvc.perform(get("/api/etl-statuses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingEtlStatus() throws Exception {
        // Get the etlStatus
        restEtlStatusMockMvc.perform(get("/api/etl-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEtlStatus() throws Exception {
        // Initialize the database
        etlStatusRepository.saveAndFlush(etlStatus);

        int databaseSizeBeforeUpdate = etlStatusRepository.findAll().size();

        // Update the etlStatus
        EtlStatus updatedEtlStatus = etlStatusRepository.findById(etlStatus.getId()).get();
        // Disconnect from session so that the updates on updatedEtlStatus are not directly saved in db
        em.detach(updatedEtlStatus);
        updatedEtlStatus
            .etlStatusName(UPDATED_ETL_STATUS_NAME);
        EtlStatusDTO etlStatusDTO = etlStatusMapper.toDto(updatedEtlStatus);

        restEtlStatusMockMvc.perform(put("/api/etl-statuses")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etlStatusDTO)))
            .andExpect(status().isOk());

        // Validate the EtlStatus in the database
        List<EtlStatus> etlStatusList = etlStatusRepository.findAll();
        assertThat(etlStatusList).hasSize(databaseSizeBeforeUpdate);
        EtlStatus testEtlStatus = etlStatusList.get(etlStatusList.size() - 1);
        assertThat(testEtlStatus.getEtlStatusName()).isEqualTo(UPDATED_ETL_STATUS_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingEtlStatus() throws Exception {
        int databaseSizeBeforeUpdate = etlStatusRepository.findAll().size();

        // Create the EtlStatus
        EtlStatusDTO etlStatusDTO = etlStatusMapper.toDto(etlStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEtlStatusMockMvc.perform(put("/api/etl-statuses")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(etlStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtlStatus in the database
        List<EtlStatus> etlStatusList = etlStatusRepository.findAll();
        assertThat(etlStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEtlStatus() throws Exception {
        // Initialize the database
        etlStatusRepository.saveAndFlush(etlStatus);

        int databaseSizeBeforeDelete = etlStatusRepository.findAll().size();

        // Delete the etlStatus
        restEtlStatusMockMvc.perform(delete("/api/etl-statuses/{id}", etlStatus.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EtlStatus> etlStatusList = etlStatusRepository.findAll();
        assertThat(etlStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
