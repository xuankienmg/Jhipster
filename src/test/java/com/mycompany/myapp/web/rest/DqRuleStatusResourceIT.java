package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.DqRuleStatus;
import com.mycompany.myapp.repository.DqRuleStatusRepository;
import com.mycompany.myapp.service.DqRuleStatusService;
import com.mycompany.myapp.service.dto.DqRuleStatusDTO;
import com.mycompany.myapp.service.mapper.DqRuleStatusMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.DqRuleStatusCriteria;
import com.mycompany.myapp.service.DqRuleStatusQueryService;

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
 * Integration tests for the {@link DqRuleStatusResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class DqRuleStatusResourceIT {

    private static final String DEFAULT_STATUS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private DqRuleStatusRepository dqRuleStatusRepository;

    @Autowired
    private DqRuleStatusMapper dqRuleStatusMapper;

    @Autowired
    private DqRuleStatusService dqRuleStatusService;

    @Autowired
    private DqRuleStatusQueryService dqRuleStatusQueryService;

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

    private MockMvc restDqRuleStatusMockMvc;

    private DqRuleStatus dqRuleStatus;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DqRuleStatusResource dqRuleStatusResource = new DqRuleStatusResource(dqRuleStatusService, dqRuleStatusQueryService);
        this.restDqRuleStatusMockMvc = MockMvcBuilders.standaloneSetup(dqRuleStatusResource)
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
    public static DqRuleStatus createEntity(EntityManager em) {
        DqRuleStatus dqRuleStatus = new DqRuleStatus()
            .statusName(DEFAULT_STATUS_NAME)
            .statusDescription(DEFAULT_STATUS_DESCRIPTION);
        return dqRuleStatus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DqRuleStatus createUpdatedEntity(EntityManager em) {
        DqRuleStatus dqRuleStatus = new DqRuleStatus()
            .statusName(UPDATED_STATUS_NAME)
            .statusDescription(UPDATED_STATUS_DESCRIPTION);
        return dqRuleStatus;
    }

    @BeforeEach
    public void initTest() {
        dqRuleStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createDqRuleStatus() throws Exception {
        int databaseSizeBeforeCreate = dqRuleStatusRepository.findAll().size();

        // Create the DqRuleStatus
        DqRuleStatusDTO dqRuleStatusDTO = dqRuleStatusMapper.toDto(dqRuleStatus);
        restDqRuleStatusMockMvc.perform(post("/api/dq-rule-statuses")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqRuleStatusDTO)))
            .andExpect(status().isCreated());

        // Validate the DqRuleStatus in the database
        List<DqRuleStatus> dqRuleStatusList = dqRuleStatusRepository.findAll();
        assertThat(dqRuleStatusList).hasSize(databaseSizeBeforeCreate + 1);
        DqRuleStatus testDqRuleStatus = dqRuleStatusList.get(dqRuleStatusList.size() - 1);
        assertThat(testDqRuleStatus.getStatusName()).isEqualTo(DEFAULT_STATUS_NAME);
        assertThat(testDqRuleStatus.getStatusDescription()).isEqualTo(DEFAULT_STATUS_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createDqRuleStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dqRuleStatusRepository.findAll().size();

        // Create the DqRuleStatus with an existing ID
        dqRuleStatus.setId(1L);
        DqRuleStatusDTO dqRuleStatusDTO = dqRuleStatusMapper.toDto(dqRuleStatus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDqRuleStatusMockMvc.perform(post("/api/dq-rule-statuses")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqRuleStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DqRuleStatus in the database
        List<DqRuleStatus> dqRuleStatusList = dqRuleStatusRepository.findAll();
        assertThat(dqRuleStatusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDqRuleStatuses() throws Exception {
        // Initialize the database
        dqRuleStatusRepository.saveAndFlush(dqRuleStatus);

        // Get all the dqRuleStatusList
        restDqRuleStatusMockMvc.perform(get("/api/dq-rule-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dqRuleStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].statusName").value(hasItem(DEFAULT_STATUS_NAME)))
            .andExpect(jsonPath("$.[*].statusDescription").value(hasItem(DEFAULT_STATUS_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getDqRuleStatus() throws Exception {
        // Initialize the database
        dqRuleStatusRepository.saveAndFlush(dqRuleStatus);

        // Get the dqRuleStatus
        restDqRuleStatusMockMvc.perform(get("/api/dq-rule-statuses/{id}", dqRuleStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dqRuleStatus.getId().intValue()))
            .andExpect(jsonPath("$.statusName").value(DEFAULT_STATUS_NAME))
            .andExpect(jsonPath("$.statusDescription").value(DEFAULT_STATUS_DESCRIPTION));
    }


    @Test
    @Transactional
    public void getDqRuleStatusesByIdFiltering() throws Exception {
        // Initialize the database
        dqRuleStatusRepository.saveAndFlush(dqRuleStatus);

        Long id = dqRuleStatus.getId();

        defaultDqRuleStatusShouldBeFound("id.equals=" + id);
        defaultDqRuleStatusShouldNotBeFound("id.notEquals=" + id);

        defaultDqRuleStatusShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDqRuleStatusShouldNotBeFound("id.greaterThan=" + id);

        defaultDqRuleStatusShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDqRuleStatusShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDqRuleStatusesByStatusNameIsEqualToSomething() throws Exception {
        // Initialize the database
        dqRuleStatusRepository.saveAndFlush(dqRuleStatus);

        // Get all the dqRuleStatusList where statusName equals to DEFAULT_STATUS_NAME
        defaultDqRuleStatusShouldBeFound("statusName.equals=" + DEFAULT_STATUS_NAME);

        // Get all the dqRuleStatusList where statusName equals to UPDATED_STATUS_NAME
        defaultDqRuleStatusShouldNotBeFound("statusName.equals=" + UPDATED_STATUS_NAME);
    }

    @Test
    @Transactional
    public void getAllDqRuleStatusesByStatusNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dqRuleStatusRepository.saveAndFlush(dqRuleStatus);

        // Get all the dqRuleStatusList where statusName not equals to DEFAULT_STATUS_NAME
        defaultDqRuleStatusShouldNotBeFound("statusName.notEquals=" + DEFAULT_STATUS_NAME);

        // Get all the dqRuleStatusList where statusName not equals to UPDATED_STATUS_NAME
        defaultDqRuleStatusShouldBeFound("statusName.notEquals=" + UPDATED_STATUS_NAME);
    }

    @Test
    @Transactional
    public void getAllDqRuleStatusesByStatusNameIsInShouldWork() throws Exception {
        // Initialize the database
        dqRuleStatusRepository.saveAndFlush(dqRuleStatus);

        // Get all the dqRuleStatusList where statusName in DEFAULT_STATUS_NAME or UPDATED_STATUS_NAME
        defaultDqRuleStatusShouldBeFound("statusName.in=" + DEFAULT_STATUS_NAME + "," + UPDATED_STATUS_NAME);

        // Get all the dqRuleStatusList where statusName equals to UPDATED_STATUS_NAME
        defaultDqRuleStatusShouldNotBeFound("statusName.in=" + UPDATED_STATUS_NAME);
    }

    @Test
    @Transactional
    public void getAllDqRuleStatusesByStatusNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        dqRuleStatusRepository.saveAndFlush(dqRuleStatus);

        // Get all the dqRuleStatusList where statusName is not null
        defaultDqRuleStatusShouldBeFound("statusName.specified=true");

        // Get all the dqRuleStatusList where statusName is null
        defaultDqRuleStatusShouldNotBeFound("statusName.specified=false");
    }
                @Test
    @Transactional
    public void getAllDqRuleStatusesByStatusNameContainsSomething() throws Exception {
        // Initialize the database
        dqRuleStatusRepository.saveAndFlush(dqRuleStatus);

        // Get all the dqRuleStatusList where statusName contains DEFAULT_STATUS_NAME
        defaultDqRuleStatusShouldBeFound("statusName.contains=" + DEFAULT_STATUS_NAME);

        // Get all the dqRuleStatusList where statusName contains UPDATED_STATUS_NAME
        defaultDqRuleStatusShouldNotBeFound("statusName.contains=" + UPDATED_STATUS_NAME);
    }

    @Test
    @Transactional
    public void getAllDqRuleStatusesByStatusNameNotContainsSomething() throws Exception {
        // Initialize the database
        dqRuleStatusRepository.saveAndFlush(dqRuleStatus);

        // Get all the dqRuleStatusList where statusName does not contain DEFAULT_STATUS_NAME
        defaultDqRuleStatusShouldNotBeFound("statusName.doesNotContain=" + DEFAULT_STATUS_NAME);

        // Get all the dqRuleStatusList where statusName does not contain UPDATED_STATUS_NAME
        defaultDqRuleStatusShouldBeFound("statusName.doesNotContain=" + UPDATED_STATUS_NAME);
    }


    @Test
    @Transactional
    public void getAllDqRuleStatusesByStatusDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        dqRuleStatusRepository.saveAndFlush(dqRuleStatus);

        // Get all the dqRuleStatusList where statusDescription equals to DEFAULT_STATUS_DESCRIPTION
        defaultDqRuleStatusShouldBeFound("statusDescription.equals=" + DEFAULT_STATUS_DESCRIPTION);

        // Get all the dqRuleStatusList where statusDescription equals to UPDATED_STATUS_DESCRIPTION
        defaultDqRuleStatusShouldNotBeFound("statusDescription.equals=" + UPDATED_STATUS_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDqRuleStatusesByStatusDescriptionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dqRuleStatusRepository.saveAndFlush(dqRuleStatus);

        // Get all the dqRuleStatusList where statusDescription not equals to DEFAULT_STATUS_DESCRIPTION
        defaultDqRuleStatusShouldNotBeFound("statusDescription.notEquals=" + DEFAULT_STATUS_DESCRIPTION);

        // Get all the dqRuleStatusList where statusDescription not equals to UPDATED_STATUS_DESCRIPTION
        defaultDqRuleStatusShouldBeFound("statusDescription.notEquals=" + UPDATED_STATUS_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDqRuleStatusesByStatusDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        dqRuleStatusRepository.saveAndFlush(dqRuleStatus);

        // Get all the dqRuleStatusList where statusDescription in DEFAULT_STATUS_DESCRIPTION or UPDATED_STATUS_DESCRIPTION
        defaultDqRuleStatusShouldBeFound("statusDescription.in=" + DEFAULT_STATUS_DESCRIPTION + "," + UPDATED_STATUS_DESCRIPTION);

        // Get all the dqRuleStatusList where statusDescription equals to UPDATED_STATUS_DESCRIPTION
        defaultDqRuleStatusShouldNotBeFound("statusDescription.in=" + UPDATED_STATUS_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDqRuleStatusesByStatusDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        dqRuleStatusRepository.saveAndFlush(dqRuleStatus);

        // Get all the dqRuleStatusList where statusDescription is not null
        defaultDqRuleStatusShouldBeFound("statusDescription.specified=true");

        // Get all the dqRuleStatusList where statusDescription is null
        defaultDqRuleStatusShouldNotBeFound("statusDescription.specified=false");
    }
                @Test
    @Transactional
    public void getAllDqRuleStatusesByStatusDescriptionContainsSomething() throws Exception {
        // Initialize the database
        dqRuleStatusRepository.saveAndFlush(dqRuleStatus);

        // Get all the dqRuleStatusList where statusDescription contains DEFAULT_STATUS_DESCRIPTION
        defaultDqRuleStatusShouldBeFound("statusDescription.contains=" + DEFAULT_STATUS_DESCRIPTION);

        // Get all the dqRuleStatusList where statusDescription contains UPDATED_STATUS_DESCRIPTION
        defaultDqRuleStatusShouldNotBeFound("statusDescription.contains=" + UPDATED_STATUS_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDqRuleStatusesByStatusDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        dqRuleStatusRepository.saveAndFlush(dqRuleStatus);

        // Get all the dqRuleStatusList where statusDescription does not contain DEFAULT_STATUS_DESCRIPTION
        defaultDqRuleStatusShouldNotBeFound("statusDescription.doesNotContain=" + DEFAULT_STATUS_DESCRIPTION);

        // Get all the dqRuleStatusList where statusDescription does not contain UPDATED_STATUS_DESCRIPTION
        defaultDqRuleStatusShouldBeFound("statusDescription.doesNotContain=" + UPDATED_STATUS_DESCRIPTION);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDqRuleStatusShouldBeFound(String filter) throws Exception {
        restDqRuleStatusMockMvc.perform(get("/api/dq-rule-statuses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dqRuleStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].statusName").value(hasItem(DEFAULT_STATUS_NAME)))
            .andExpect(jsonPath("$.[*].statusDescription").value(hasItem(DEFAULT_STATUS_DESCRIPTION)));

        // Check, that the count call also returns 1
        restDqRuleStatusMockMvc.perform(get("/api/dq-rule-statuses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDqRuleStatusShouldNotBeFound(String filter) throws Exception {
        restDqRuleStatusMockMvc.perform(get("/api/dq-rule-statuses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDqRuleStatusMockMvc.perform(get("/api/dq-rule-statuses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDqRuleStatus() throws Exception {
        // Get the dqRuleStatus
        restDqRuleStatusMockMvc.perform(get("/api/dq-rule-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDqRuleStatus() throws Exception {
        // Initialize the database
        dqRuleStatusRepository.saveAndFlush(dqRuleStatus);

        int databaseSizeBeforeUpdate = dqRuleStatusRepository.findAll().size();

        // Update the dqRuleStatus
        DqRuleStatus updatedDqRuleStatus = dqRuleStatusRepository.findById(dqRuleStatus.getId()).get();
        // Disconnect from session so that the updates on updatedDqRuleStatus are not directly saved in db
        em.detach(updatedDqRuleStatus);
        updatedDqRuleStatus
            .statusName(UPDATED_STATUS_NAME)
            .statusDescription(UPDATED_STATUS_DESCRIPTION);
        DqRuleStatusDTO dqRuleStatusDTO = dqRuleStatusMapper.toDto(updatedDqRuleStatus);

        restDqRuleStatusMockMvc.perform(put("/api/dq-rule-statuses")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqRuleStatusDTO)))
            .andExpect(status().isOk());

        // Validate the DqRuleStatus in the database
        List<DqRuleStatus> dqRuleStatusList = dqRuleStatusRepository.findAll();
        assertThat(dqRuleStatusList).hasSize(databaseSizeBeforeUpdate);
        DqRuleStatus testDqRuleStatus = dqRuleStatusList.get(dqRuleStatusList.size() - 1);
        assertThat(testDqRuleStatus.getStatusName()).isEqualTo(UPDATED_STATUS_NAME);
        assertThat(testDqRuleStatus.getStatusDescription()).isEqualTo(UPDATED_STATUS_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingDqRuleStatus() throws Exception {
        int databaseSizeBeforeUpdate = dqRuleStatusRepository.findAll().size();

        // Create the DqRuleStatus
        DqRuleStatusDTO dqRuleStatusDTO = dqRuleStatusMapper.toDto(dqRuleStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDqRuleStatusMockMvc.perform(put("/api/dq-rule-statuses")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqRuleStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DqRuleStatus in the database
        List<DqRuleStatus> dqRuleStatusList = dqRuleStatusRepository.findAll();
        assertThat(dqRuleStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDqRuleStatus() throws Exception {
        // Initialize the database
        dqRuleStatusRepository.saveAndFlush(dqRuleStatus);

        int databaseSizeBeforeDelete = dqRuleStatusRepository.findAll().size();

        // Delete the dqRuleStatus
        restDqRuleStatusMockMvc.perform(delete("/api/dq-rule-statuses/{id}", dqRuleStatus.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DqRuleStatus> dqRuleStatusList = dqRuleStatusRepository.findAll();
        assertThat(dqRuleStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
