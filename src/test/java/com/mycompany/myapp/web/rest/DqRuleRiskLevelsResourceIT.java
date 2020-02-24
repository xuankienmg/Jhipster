package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.DqRuleRiskLevels;
import com.mycompany.myapp.repository.DqRuleRiskLevelsRepository;
import com.mycompany.myapp.service.DqRuleRiskLevelsService;
import com.mycompany.myapp.service.dto.DqRuleRiskLevelsDTO;
import com.mycompany.myapp.service.mapper.DqRuleRiskLevelsMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.DqRuleRiskLevelsCriteria;
import com.mycompany.myapp.service.DqRuleRiskLevelsQueryService;

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
 * Integration tests for the {@link DqRuleRiskLevelsResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class DqRuleRiskLevelsResourceIT {

    private static final String DEFAULT_RISK_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RISK_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_RISK_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_RISK_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private DqRuleRiskLevelsRepository dqRuleRiskLevelsRepository;

    @Autowired
    private DqRuleRiskLevelsMapper dqRuleRiskLevelsMapper;

    @Autowired
    private DqRuleRiskLevelsService dqRuleRiskLevelsService;

    @Autowired
    private DqRuleRiskLevelsQueryService dqRuleRiskLevelsQueryService;

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

    private MockMvc restDqRuleRiskLevelsMockMvc;

    private DqRuleRiskLevels dqRuleRiskLevels;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DqRuleRiskLevelsResource dqRuleRiskLevelsResource = new DqRuleRiskLevelsResource(dqRuleRiskLevelsService, dqRuleRiskLevelsQueryService);
        this.restDqRuleRiskLevelsMockMvc = MockMvcBuilders.standaloneSetup(dqRuleRiskLevelsResource)
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
    public static DqRuleRiskLevels createEntity(EntityManager em) {
        DqRuleRiskLevels dqRuleRiskLevels = new DqRuleRiskLevels()
            .riskName(DEFAULT_RISK_NAME)
            .riskDescription(DEFAULT_RISK_DESCRIPTION);
        return dqRuleRiskLevels;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DqRuleRiskLevels createUpdatedEntity(EntityManager em) {
        DqRuleRiskLevels dqRuleRiskLevels = new DqRuleRiskLevels()
            .riskName(UPDATED_RISK_NAME)
            .riskDescription(UPDATED_RISK_DESCRIPTION);
        return dqRuleRiskLevels;
    }

    @BeforeEach
    public void initTest() {
        dqRuleRiskLevels = createEntity(em);
    }

    @Test
    @Transactional
    public void createDqRuleRiskLevels() throws Exception {
        int databaseSizeBeforeCreate = dqRuleRiskLevelsRepository.findAll().size();

        // Create the DqRuleRiskLevels
        DqRuleRiskLevelsDTO dqRuleRiskLevelsDTO = dqRuleRiskLevelsMapper.toDto(dqRuleRiskLevels);
        restDqRuleRiskLevelsMockMvc.perform(post("/api/dq-rule-risk-levels")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqRuleRiskLevelsDTO)))
            .andExpect(status().isCreated());

        // Validate the DqRuleRiskLevels in the database
        List<DqRuleRiskLevels> dqRuleRiskLevelsList = dqRuleRiskLevelsRepository.findAll();
        assertThat(dqRuleRiskLevelsList).hasSize(databaseSizeBeforeCreate + 1);
        DqRuleRiskLevels testDqRuleRiskLevels = dqRuleRiskLevelsList.get(dqRuleRiskLevelsList.size() - 1);
        assertThat(testDqRuleRiskLevels.getRiskName()).isEqualTo(DEFAULT_RISK_NAME);
        assertThat(testDqRuleRiskLevels.getRiskDescription()).isEqualTo(DEFAULT_RISK_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createDqRuleRiskLevelsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dqRuleRiskLevelsRepository.findAll().size();

        // Create the DqRuleRiskLevels with an existing ID
        dqRuleRiskLevels.setId(1L);
        DqRuleRiskLevelsDTO dqRuleRiskLevelsDTO = dqRuleRiskLevelsMapper.toDto(dqRuleRiskLevels);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDqRuleRiskLevelsMockMvc.perform(post("/api/dq-rule-risk-levels")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqRuleRiskLevelsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DqRuleRiskLevels in the database
        List<DqRuleRiskLevels> dqRuleRiskLevelsList = dqRuleRiskLevelsRepository.findAll();
        assertThat(dqRuleRiskLevelsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDqRuleRiskLevels() throws Exception {
        // Initialize the database
        dqRuleRiskLevelsRepository.saveAndFlush(dqRuleRiskLevels);

        // Get all the dqRuleRiskLevelsList
        restDqRuleRiskLevelsMockMvc.perform(get("/api/dq-rule-risk-levels?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dqRuleRiskLevels.getId().intValue())))
            .andExpect(jsonPath("$.[*].riskName").value(hasItem(DEFAULT_RISK_NAME)))
            .andExpect(jsonPath("$.[*].riskDescription").value(hasItem(DEFAULT_RISK_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getDqRuleRiskLevels() throws Exception {
        // Initialize the database
        dqRuleRiskLevelsRepository.saveAndFlush(dqRuleRiskLevels);

        // Get the dqRuleRiskLevels
        restDqRuleRiskLevelsMockMvc.perform(get("/api/dq-rule-risk-levels/{id}", dqRuleRiskLevels.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dqRuleRiskLevels.getId().intValue()))
            .andExpect(jsonPath("$.riskName").value(DEFAULT_RISK_NAME))
            .andExpect(jsonPath("$.riskDescription").value(DEFAULT_RISK_DESCRIPTION));
    }


    @Test
    @Transactional
    public void getDqRuleRiskLevelsByIdFiltering() throws Exception {
        // Initialize the database
        dqRuleRiskLevelsRepository.saveAndFlush(dqRuleRiskLevels);

        Long id = dqRuleRiskLevels.getId();

        defaultDqRuleRiskLevelsShouldBeFound("id.equals=" + id);
        defaultDqRuleRiskLevelsShouldNotBeFound("id.notEquals=" + id);

        defaultDqRuleRiskLevelsShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDqRuleRiskLevelsShouldNotBeFound("id.greaterThan=" + id);

        defaultDqRuleRiskLevelsShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDqRuleRiskLevelsShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDqRuleRiskLevelsByRiskNameIsEqualToSomething() throws Exception {
        // Initialize the database
        dqRuleRiskLevelsRepository.saveAndFlush(dqRuleRiskLevels);

        // Get all the dqRuleRiskLevelsList where riskName equals to DEFAULT_RISK_NAME
        defaultDqRuleRiskLevelsShouldBeFound("riskName.equals=" + DEFAULT_RISK_NAME);

        // Get all the dqRuleRiskLevelsList where riskName equals to UPDATED_RISK_NAME
        defaultDqRuleRiskLevelsShouldNotBeFound("riskName.equals=" + UPDATED_RISK_NAME);
    }

    @Test
    @Transactional
    public void getAllDqRuleRiskLevelsByRiskNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dqRuleRiskLevelsRepository.saveAndFlush(dqRuleRiskLevels);

        // Get all the dqRuleRiskLevelsList where riskName not equals to DEFAULT_RISK_NAME
        defaultDqRuleRiskLevelsShouldNotBeFound("riskName.notEquals=" + DEFAULT_RISK_NAME);

        // Get all the dqRuleRiskLevelsList where riskName not equals to UPDATED_RISK_NAME
        defaultDqRuleRiskLevelsShouldBeFound("riskName.notEquals=" + UPDATED_RISK_NAME);
    }

    @Test
    @Transactional
    public void getAllDqRuleRiskLevelsByRiskNameIsInShouldWork() throws Exception {
        // Initialize the database
        dqRuleRiskLevelsRepository.saveAndFlush(dqRuleRiskLevels);

        // Get all the dqRuleRiskLevelsList where riskName in DEFAULT_RISK_NAME or UPDATED_RISK_NAME
        defaultDqRuleRiskLevelsShouldBeFound("riskName.in=" + DEFAULT_RISK_NAME + "," + UPDATED_RISK_NAME);

        // Get all the dqRuleRiskLevelsList where riskName equals to UPDATED_RISK_NAME
        defaultDqRuleRiskLevelsShouldNotBeFound("riskName.in=" + UPDATED_RISK_NAME);
    }

    @Test
    @Transactional
    public void getAllDqRuleRiskLevelsByRiskNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        dqRuleRiskLevelsRepository.saveAndFlush(dqRuleRiskLevels);

        // Get all the dqRuleRiskLevelsList where riskName is not null
        defaultDqRuleRiskLevelsShouldBeFound("riskName.specified=true");

        // Get all the dqRuleRiskLevelsList where riskName is null
        defaultDqRuleRiskLevelsShouldNotBeFound("riskName.specified=false");
    }
                @Test
    @Transactional
    public void getAllDqRuleRiskLevelsByRiskNameContainsSomething() throws Exception {
        // Initialize the database
        dqRuleRiskLevelsRepository.saveAndFlush(dqRuleRiskLevels);

        // Get all the dqRuleRiskLevelsList where riskName contains DEFAULT_RISK_NAME
        defaultDqRuleRiskLevelsShouldBeFound("riskName.contains=" + DEFAULT_RISK_NAME);

        // Get all the dqRuleRiskLevelsList where riskName contains UPDATED_RISK_NAME
        defaultDqRuleRiskLevelsShouldNotBeFound("riskName.contains=" + UPDATED_RISK_NAME);
    }

    @Test
    @Transactional
    public void getAllDqRuleRiskLevelsByRiskNameNotContainsSomething() throws Exception {
        // Initialize the database
        dqRuleRiskLevelsRepository.saveAndFlush(dqRuleRiskLevels);

        // Get all the dqRuleRiskLevelsList where riskName does not contain DEFAULT_RISK_NAME
        defaultDqRuleRiskLevelsShouldNotBeFound("riskName.doesNotContain=" + DEFAULT_RISK_NAME);

        // Get all the dqRuleRiskLevelsList where riskName does not contain UPDATED_RISK_NAME
        defaultDqRuleRiskLevelsShouldBeFound("riskName.doesNotContain=" + UPDATED_RISK_NAME);
    }


    @Test
    @Transactional
    public void getAllDqRuleRiskLevelsByRiskDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        dqRuleRiskLevelsRepository.saveAndFlush(dqRuleRiskLevels);

        // Get all the dqRuleRiskLevelsList where riskDescription equals to DEFAULT_RISK_DESCRIPTION
        defaultDqRuleRiskLevelsShouldBeFound("riskDescription.equals=" + DEFAULT_RISK_DESCRIPTION);

        // Get all the dqRuleRiskLevelsList where riskDescription equals to UPDATED_RISK_DESCRIPTION
        defaultDqRuleRiskLevelsShouldNotBeFound("riskDescription.equals=" + UPDATED_RISK_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDqRuleRiskLevelsByRiskDescriptionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dqRuleRiskLevelsRepository.saveAndFlush(dqRuleRiskLevels);

        // Get all the dqRuleRiskLevelsList where riskDescription not equals to DEFAULT_RISK_DESCRIPTION
        defaultDqRuleRiskLevelsShouldNotBeFound("riskDescription.notEquals=" + DEFAULT_RISK_DESCRIPTION);

        // Get all the dqRuleRiskLevelsList where riskDescription not equals to UPDATED_RISK_DESCRIPTION
        defaultDqRuleRiskLevelsShouldBeFound("riskDescription.notEquals=" + UPDATED_RISK_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDqRuleRiskLevelsByRiskDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        dqRuleRiskLevelsRepository.saveAndFlush(dqRuleRiskLevels);

        // Get all the dqRuleRiskLevelsList where riskDescription in DEFAULT_RISK_DESCRIPTION or UPDATED_RISK_DESCRIPTION
        defaultDqRuleRiskLevelsShouldBeFound("riskDescription.in=" + DEFAULT_RISK_DESCRIPTION + "," + UPDATED_RISK_DESCRIPTION);

        // Get all the dqRuleRiskLevelsList where riskDescription equals to UPDATED_RISK_DESCRIPTION
        defaultDqRuleRiskLevelsShouldNotBeFound("riskDescription.in=" + UPDATED_RISK_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDqRuleRiskLevelsByRiskDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        dqRuleRiskLevelsRepository.saveAndFlush(dqRuleRiskLevels);

        // Get all the dqRuleRiskLevelsList where riskDescription is not null
        defaultDqRuleRiskLevelsShouldBeFound("riskDescription.specified=true");

        // Get all the dqRuleRiskLevelsList where riskDescription is null
        defaultDqRuleRiskLevelsShouldNotBeFound("riskDescription.specified=false");
    }
                @Test
    @Transactional
    public void getAllDqRuleRiskLevelsByRiskDescriptionContainsSomething() throws Exception {
        // Initialize the database
        dqRuleRiskLevelsRepository.saveAndFlush(dqRuleRiskLevels);

        // Get all the dqRuleRiskLevelsList where riskDescription contains DEFAULT_RISK_DESCRIPTION
        defaultDqRuleRiskLevelsShouldBeFound("riskDescription.contains=" + DEFAULT_RISK_DESCRIPTION);

        // Get all the dqRuleRiskLevelsList where riskDescription contains UPDATED_RISK_DESCRIPTION
        defaultDqRuleRiskLevelsShouldNotBeFound("riskDescription.contains=" + UPDATED_RISK_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDqRuleRiskLevelsByRiskDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        dqRuleRiskLevelsRepository.saveAndFlush(dqRuleRiskLevels);

        // Get all the dqRuleRiskLevelsList where riskDescription does not contain DEFAULT_RISK_DESCRIPTION
        defaultDqRuleRiskLevelsShouldNotBeFound("riskDescription.doesNotContain=" + DEFAULT_RISK_DESCRIPTION);

        // Get all the dqRuleRiskLevelsList where riskDescription does not contain UPDATED_RISK_DESCRIPTION
        defaultDqRuleRiskLevelsShouldBeFound("riskDescription.doesNotContain=" + UPDATED_RISK_DESCRIPTION);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDqRuleRiskLevelsShouldBeFound(String filter) throws Exception {
        restDqRuleRiskLevelsMockMvc.perform(get("/api/dq-rule-risk-levels?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dqRuleRiskLevels.getId().intValue())))
            .andExpect(jsonPath("$.[*].riskName").value(hasItem(DEFAULT_RISK_NAME)))
            .andExpect(jsonPath("$.[*].riskDescription").value(hasItem(DEFAULT_RISK_DESCRIPTION)));

        // Check, that the count call also returns 1
        restDqRuleRiskLevelsMockMvc.perform(get("/api/dq-rule-risk-levels/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDqRuleRiskLevelsShouldNotBeFound(String filter) throws Exception {
        restDqRuleRiskLevelsMockMvc.perform(get("/api/dq-rule-risk-levels?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDqRuleRiskLevelsMockMvc.perform(get("/api/dq-rule-risk-levels/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDqRuleRiskLevels() throws Exception {
        // Get the dqRuleRiskLevels
        restDqRuleRiskLevelsMockMvc.perform(get("/api/dq-rule-risk-levels/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDqRuleRiskLevels() throws Exception {
        // Initialize the database
        dqRuleRiskLevelsRepository.saveAndFlush(dqRuleRiskLevels);

        int databaseSizeBeforeUpdate = dqRuleRiskLevelsRepository.findAll().size();

        // Update the dqRuleRiskLevels
        DqRuleRiskLevels updatedDqRuleRiskLevels = dqRuleRiskLevelsRepository.findById(dqRuleRiskLevels.getId()).get();
        // Disconnect from session so that the updates on updatedDqRuleRiskLevels are not directly saved in db
        em.detach(updatedDqRuleRiskLevels);
        updatedDqRuleRiskLevels
            .riskName(UPDATED_RISK_NAME)
            .riskDescription(UPDATED_RISK_DESCRIPTION);
        DqRuleRiskLevelsDTO dqRuleRiskLevelsDTO = dqRuleRiskLevelsMapper.toDto(updatedDqRuleRiskLevels);

        restDqRuleRiskLevelsMockMvc.perform(put("/api/dq-rule-risk-levels")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqRuleRiskLevelsDTO)))
            .andExpect(status().isOk());

        // Validate the DqRuleRiskLevels in the database
        List<DqRuleRiskLevels> dqRuleRiskLevelsList = dqRuleRiskLevelsRepository.findAll();
        assertThat(dqRuleRiskLevelsList).hasSize(databaseSizeBeforeUpdate);
        DqRuleRiskLevels testDqRuleRiskLevels = dqRuleRiskLevelsList.get(dqRuleRiskLevelsList.size() - 1);
        assertThat(testDqRuleRiskLevels.getRiskName()).isEqualTo(UPDATED_RISK_NAME);
        assertThat(testDqRuleRiskLevels.getRiskDescription()).isEqualTo(UPDATED_RISK_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingDqRuleRiskLevels() throws Exception {
        int databaseSizeBeforeUpdate = dqRuleRiskLevelsRepository.findAll().size();

        // Create the DqRuleRiskLevels
        DqRuleRiskLevelsDTO dqRuleRiskLevelsDTO = dqRuleRiskLevelsMapper.toDto(dqRuleRiskLevels);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDqRuleRiskLevelsMockMvc.perform(put("/api/dq-rule-risk-levels")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqRuleRiskLevelsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DqRuleRiskLevels in the database
        List<DqRuleRiskLevels> dqRuleRiskLevelsList = dqRuleRiskLevelsRepository.findAll();
        assertThat(dqRuleRiskLevelsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDqRuleRiskLevels() throws Exception {
        // Initialize the database
        dqRuleRiskLevelsRepository.saveAndFlush(dqRuleRiskLevels);

        int databaseSizeBeforeDelete = dqRuleRiskLevelsRepository.findAll().size();

        // Delete the dqRuleRiskLevels
        restDqRuleRiskLevelsMockMvc.perform(delete("/api/dq-rule-risk-levels/{id}", dqRuleRiskLevels.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DqRuleRiskLevels> dqRuleRiskLevelsList = dqRuleRiskLevelsRepository.findAll();
        assertThat(dqRuleRiskLevelsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
