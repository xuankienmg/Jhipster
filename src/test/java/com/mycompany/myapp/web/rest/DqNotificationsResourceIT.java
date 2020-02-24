package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.DqNotifications;
import com.mycompany.myapp.domain.DqRules;
import com.mycompany.myapp.repository.DqNotificationsRepository;
import com.mycompany.myapp.service.DqNotificationsService;
import com.mycompany.myapp.service.dto.DqNotificationsDTO;
import com.mycompany.myapp.service.mapper.DqNotificationsMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.DqNotificationsCriteria;
import com.mycompany.myapp.service.DqNotificationsQueryService;

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
 * Integration tests for the {@link DqNotificationsResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class DqNotificationsResourceIT {

    private static final Integer DEFAULT_REPICIENT_ID = 1;
    private static final Integer UPDATED_REPICIENT_ID = 2;
    private static final Integer SMALLER_REPICIENT_ID = 1 - 1;

    private static final Integer DEFAULT_REPICIENT_TYPE_ID = 1;
    private static final Integer UPDATED_REPICIENT_TYPE_ID = 2;
    private static final Integer SMALLER_REPICIENT_TYPE_ID = 1 - 1;

    @Autowired
    private DqNotificationsRepository dqNotificationsRepository;

    @Autowired
    private DqNotificationsMapper dqNotificationsMapper;

    @Autowired
    private DqNotificationsService dqNotificationsService;

    @Autowired
    private DqNotificationsQueryService dqNotificationsQueryService;

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

    private MockMvc restDqNotificationsMockMvc;

    private DqNotifications dqNotifications;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DqNotificationsResource dqNotificationsResource = new DqNotificationsResource(dqNotificationsService, dqNotificationsQueryService);
        this.restDqNotificationsMockMvc = MockMvcBuilders.standaloneSetup(dqNotificationsResource)
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
    public static DqNotifications createEntity(EntityManager em) {
        DqNotifications dqNotifications = new DqNotifications()
            .repicientId(DEFAULT_REPICIENT_ID)
            .repicientTypeId(DEFAULT_REPICIENT_TYPE_ID);
        return dqNotifications;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DqNotifications createUpdatedEntity(EntityManager em) {
        DqNotifications dqNotifications = new DqNotifications()
            .repicientId(UPDATED_REPICIENT_ID)
            .repicientTypeId(UPDATED_REPICIENT_TYPE_ID);
        return dqNotifications;
    }

    @BeforeEach
    public void initTest() {
        dqNotifications = createEntity(em);
    }

    @Test
    @Transactional
    public void createDqNotifications() throws Exception {
        int databaseSizeBeforeCreate = dqNotificationsRepository.findAll().size();

        // Create the DqNotifications
        DqNotificationsDTO dqNotificationsDTO = dqNotificationsMapper.toDto(dqNotifications);
        restDqNotificationsMockMvc.perform(post("/api/dq-notifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqNotificationsDTO)))
            .andExpect(status().isCreated());

        // Validate the DqNotifications in the database
        List<DqNotifications> dqNotificationsList = dqNotificationsRepository.findAll();
        assertThat(dqNotificationsList).hasSize(databaseSizeBeforeCreate + 1);
        DqNotifications testDqNotifications = dqNotificationsList.get(dqNotificationsList.size() - 1);
        assertThat(testDqNotifications.getRepicientId()).isEqualTo(DEFAULT_REPICIENT_ID);
        assertThat(testDqNotifications.getRepicientTypeId()).isEqualTo(DEFAULT_REPICIENT_TYPE_ID);
    }

    @Test
    @Transactional
    public void createDqNotificationsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dqNotificationsRepository.findAll().size();

        // Create the DqNotifications with an existing ID
        dqNotifications.setId(1L);
        DqNotificationsDTO dqNotificationsDTO = dqNotificationsMapper.toDto(dqNotifications);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDqNotificationsMockMvc.perform(post("/api/dq-notifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqNotificationsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DqNotifications in the database
        List<DqNotifications> dqNotificationsList = dqNotificationsRepository.findAll();
        assertThat(dqNotificationsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDqNotifications() throws Exception {
        // Initialize the database
        dqNotificationsRepository.saveAndFlush(dqNotifications);

        // Get all the dqNotificationsList
        restDqNotificationsMockMvc.perform(get("/api/dq-notifications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dqNotifications.getId().intValue())))
            .andExpect(jsonPath("$.[*].repicientId").value(hasItem(DEFAULT_REPICIENT_ID)))
            .andExpect(jsonPath("$.[*].repicientTypeId").value(hasItem(DEFAULT_REPICIENT_TYPE_ID)));
    }
    
    @Test
    @Transactional
    public void getDqNotifications() throws Exception {
        // Initialize the database
        dqNotificationsRepository.saveAndFlush(dqNotifications);

        // Get the dqNotifications
        restDqNotificationsMockMvc.perform(get("/api/dq-notifications/{id}", dqNotifications.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dqNotifications.getId().intValue()))
            .andExpect(jsonPath("$.repicientId").value(DEFAULT_REPICIENT_ID))
            .andExpect(jsonPath("$.repicientTypeId").value(DEFAULT_REPICIENT_TYPE_ID));
    }


    @Test
    @Transactional
    public void getDqNotificationsByIdFiltering() throws Exception {
        // Initialize the database
        dqNotificationsRepository.saveAndFlush(dqNotifications);

        Long id = dqNotifications.getId();

        defaultDqNotificationsShouldBeFound("id.equals=" + id);
        defaultDqNotificationsShouldNotBeFound("id.notEquals=" + id);

        defaultDqNotificationsShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDqNotificationsShouldNotBeFound("id.greaterThan=" + id);

        defaultDqNotificationsShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDqNotificationsShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDqNotificationsByRepicientIdIsEqualToSomething() throws Exception {
        // Initialize the database
        dqNotificationsRepository.saveAndFlush(dqNotifications);

        // Get all the dqNotificationsList where repicientId equals to DEFAULT_REPICIENT_ID
        defaultDqNotificationsShouldBeFound("repicientId.equals=" + DEFAULT_REPICIENT_ID);

        // Get all the dqNotificationsList where repicientId equals to UPDATED_REPICIENT_ID
        defaultDqNotificationsShouldNotBeFound("repicientId.equals=" + UPDATED_REPICIENT_ID);
    }

    @Test
    @Transactional
    public void getAllDqNotificationsByRepicientIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dqNotificationsRepository.saveAndFlush(dqNotifications);

        // Get all the dqNotificationsList where repicientId not equals to DEFAULT_REPICIENT_ID
        defaultDqNotificationsShouldNotBeFound("repicientId.notEquals=" + DEFAULT_REPICIENT_ID);

        // Get all the dqNotificationsList where repicientId not equals to UPDATED_REPICIENT_ID
        defaultDqNotificationsShouldBeFound("repicientId.notEquals=" + UPDATED_REPICIENT_ID);
    }

    @Test
    @Transactional
    public void getAllDqNotificationsByRepicientIdIsInShouldWork() throws Exception {
        // Initialize the database
        dqNotificationsRepository.saveAndFlush(dqNotifications);

        // Get all the dqNotificationsList where repicientId in DEFAULT_REPICIENT_ID or UPDATED_REPICIENT_ID
        defaultDqNotificationsShouldBeFound("repicientId.in=" + DEFAULT_REPICIENT_ID + "," + UPDATED_REPICIENT_ID);

        // Get all the dqNotificationsList where repicientId equals to UPDATED_REPICIENT_ID
        defaultDqNotificationsShouldNotBeFound("repicientId.in=" + UPDATED_REPICIENT_ID);
    }

    @Test
    @Transactional
    public void getAllDqNotificationsByRepicientIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        dqNotificationsRepository.saveAndFlush(dqNotifications);

        // Get all the dqNotificationsList where repicientId is not null
        defaultDqNotificationsShouldBeFound("repicientId.specified=true");

        // Get all the dqNotificationsList where repicientId is null
        defaultDqNotificationsShouldNotBeFound("repicientId.specified=false");
    }

    @Test
    @Transactional
    public void getAllDqNotificationsByRepicientIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dqNotificationsRepository.saveAndFlush(dqNotifications);

        // Get all the dqNotificationsList where repicientId is greater than or equal to DEFAULT_REPICIENT_ID
        defaultDqNotificationsShouldBeFound("repicientId.greaterThanOrEqual=" + DEFAULT_REPICIENT_ID);

        // Get all the dqNotificationsList where repicientId is greater than or equal to UPDATED_REPICIENT_ID
        defaultDqNotificationsShouldNotBeFound("repicientId.greaterThanOrEqual=" + UPDATED_REPICIENT_ID);
    }

    @Test
    @Transactional
    public void getAllDqNotificationsByRepicientIdIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dqNotificationsRepository.saveAndFlush(dqNotifications);

        // Get all the dqNotificationsList where repicientId is less than or equal to DEFAULT_REPICIENT_ID
        defaultDqNotificationsShouldBeFound("repicientId.lessThanOrEqual=" + DEFAULT_REPICIENT_ID);

        // Get all the dqNotificationsList where repicientId is less than or equal to SMALLER_REPICIENT_ID
        defaultDqNotificationsShouldNotBeFound("repicientId.lessThanOrEqual=" + SMALLER_REPICIENT_ID);
    }

    @Test
    @Transactional
    public void getAllDqNotificationsByRepicientIdIsLessThanSomething() throws Exception {
        // Initialize the database
        dqNotificationsRepository.saveAndFlush(dqNotifications);

        // Get all the dqNotificationsList where repicientId is less than DEFAULT_REPICIENT_ID
        defaultDqNotificationsShouldNotBeFound("repicientId.lessThan=" + DEFAULT_REPICIENT_ID);

        // Get all the dqNotificationsList where repicientId is less than UPDATED_REPICIENT_ID
        defaultDqNotificationsShouldBeFound("repicientId.lessThan=" + UPDATED_REPICIENT_ID);
    }

    @Test
    @Transactional
    public void getAllDqNotificationsByRepicientIdIsGreaterThanSomething() throws Exception {
        // Initialize the database
        dqNotificationsRepository.saveAndFlush(dqNotifications);

        // Get all the dqNotificationsList where repicientId is greater than DEFAULT_REPICIENT_ID
        defaultDqNotificationsShouldNotBeFound("repicientId.greaterThan=" + DEFAULT_REPICIENT_ID);

        // Get all the dqNotificationsList where repicientId is greater than SMALLER_REPICIENT_ID
        defaultDqNotificationsShouldBeFound("repicientId.greaterThan=" + SMALLER_REPICIENT_ID);
    }


    @Test
    @Transactional
    public void getAllDqNotificationsByRepicientTypeIdIsEqualToSomething() throws Exception {
        // Initialize the database
        dqNotificationsRepository.saveAndFlush(dqNotifications);

        // Get all the dqNotificationsList where repicientTypeId equals to DEFAULT_REPICIENT_TYPE_ID
        defaultDqNotificationsShouldBeFound("repicientTypeId.equals=" + DEFAULT_REPICIENT_TYPE_ID);

        // Get all the dqNotificationsList where repicientTypeId equals to UPDATED_REPICIENT_TYPE_ID
        defaultDqNotificationsShouldNotBeFound("repicientTypeId.equals=" + UPDATED_REPICIENT_TYPE_ID);
    }

    @Test
    @Transactional
    public void getAllDqNotificationsByRepicientTypeIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dqNotificationsRepository.saveAndFlush(dqNotifications);

        // Get all the dqNotificationsList where repicientTypeId not equals to DEFAULT_REPICIENT_TYPE_ID
        defaultDqNotificationsShouldNotBeFound("repicientTypeId.notEquals=" + DEFAULT_REPICIENT_TYPE_ID);

        // Get all the dqNotificationsList where repicientTypeId not equals to UPDATED_REPICIENT_TYPE_ID
        defaultDqNotificationsShouldBeFound("repicientTypeId.notEquals=" + UPDATED_REPICIENT_TYPE_ID);
    }

    @Test
    @Transactional
    public void getAllDqNotificationsByRepicientTypeIdIsInShouldWork() throws Exception {
        // Initialize the database
        dqNotificationsRepository.saveAndFlush(dqNotifications);

        // Get all the dqNotificationsList where repicientTypeId in DEFAULT_REPICIENT_TYPE_ID or UPDATED_REPICIENT_TYPE_ID
        defaultDqNotificationsShouldBeFound("repicientTypeId.in=" + DEFAULT_REPICIENT_TYPE_ID + "," + UPDATED_REPICIENT_TYPE_ID);

        // Get all the dqNotificationsList where repicientTypeId equals to UPDATED_REPICIENT_TYPE_ID
        defaultDqNotificationsShouldNotBeFound("repicientTypeId.in=" + UPDATED_REPICIENT_TYPE_ID);
    }

    @Test
    @Transactional
    public void getAllDqNotificationsByRepicientTypeIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        dqNotificationsRepository.saveAndFlush(dqNotifications);

        // Get all the dqNotificationsList where repicientTypeId is not null
        defaultDqNotificationsShouldBeFound("repicientTypeId.specified=true");

        // Get all the dqNotificationsList where repicientTypeId is null
        defaultDqNotificationsShouldNotBeFound("repicientTypeId.specified=false");
    }

    @Test
    @Transactional
    public void getAllDqNotificationsByRepicientTypeIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dqNotificationsRepository.saveAndFlush(dqNotifications);

        // Get all the dqNotificationsList where repicientTypeId is greater than or equal to DEFAULT_REPICIENT_TYPE_ID
        defaultDqNotificationsShouldBeFound("repicientTypeId.greaterThanOrEqual=" + DEFAULT_REPICIENT_TYPE_ID);

        // Get all the dqNotificationsList where repicientTypeId is greater than or equal to UPDATED_REPICIENT_TYPE_ID
        defaultDqNotificationsShouldNotBeFound("repicientTypeId.greaterThanOrEqual=" + UPDATED_REPICIENT_TYPE_ID);
    }

    @Test
    @Transactional
    public void getAllDqNotificationsByRepicientTypeIdIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dqNotificationsRepository.saveAndFlush(dqNotifications);

        // Get all the dqNotificationsList where repicientTypeId is less than or equal to DEFAULT_REPICIENT_TYPE_ID
        defaultDqNotificationsShouldBeFound("repicientTypeId.lessThanOrEqual=" + DEFAULT_REPICIENT_TYPE_ID);

        // Get all the dqNotificationsList where repicientTypeId is less than or equal to SMALLER_REPICIENT_TYPE_ID
        defaultDqNotificationsShouldNotBeFound("repicientTypeId.lessThanOrEqual=" + SMALLER_REPICIENT_TYPE_ID);
    }

    @Test
    @Transactional
    public void getAllDqNotificationsByRepicientTypeIdIsLessThanSomething() throws Exception {
        // Initialize the database
        dqNotificationsRepository.saveAndFlush(dqNotifications);

        // Get all the dqNotificationsList where repicientTypeId is less than DEFAULT_REPICIENT_TYPE_ID
        defaultDqNotificationsShouldNotBeFound("repicientTypeId.lessThan=" + DEFAULT_REPICIENT_TYPE_ID);

        // Get all the dqNotificationsList where repicientTypeId is less than UPDATED_REPICIENT_TYPE_ID
        defaultDqNotificationsShouldBeFound("repicientTypeId.lessThan=" + UPDATED_REPICIENT_TYPE_ID);
    }

    @Test
    @Transactional
    public void getAllDqNotificationsByRepicientTypeIdIsGreaterThanSomething() throws Exception {
        // Initialize the database
        dqNotificationsRepository.saveAndFlush(dqNotifications);

        // Get all the dqNotificationsList where repicientTypeId is greater than DEFAULT_REPICIENT_TYPE_ID
        defaultDqNotificationsShouldNotBeFound("repicientTypeId.greaterThan=" + DEFAULT_REPICIENT_TYPE_ID);

        // Get all the dqNotificationsList where repicientTypeId is greater than SMALLER_REPICIENT_TYPE_ID
        defaultDqNotificationsShouldBeFound("repicientTypeId.greaterThan=" + SMALLER_REPICIENT_TYPE_ID);
    }


    @Test
    @Transactional
    public void getAllDqNotificationsByRuleIsEqualToSomething() throws Exception {
        // Initialize the database
        dqNotificationsRepository.saveAndFlush(dqNotifications);
        DqRules rule = DqRulesResourceIT.createEntity(em);
        em.persist(rule);
        em.flush();
        dqNotifications.setRule(rule);
        dqNotificationsRepository.saveAndFlush(dqNotifications);
        Long ruleId = rule.getId();

        // Get all the dqNotificationsList where rule equals to ruleId
        defaultDqNotificationsShouldBeFound("ruleId.equals=" + ruleId);

        // Get all the dqNotificationsList where rule equals to ruleId + 1
        defaultDqNotificationsShouldNotBeFound("ruleId.equals=" + (ruleId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDqNotificationsShouldBeFound(String filter) throws Exception {
        restDqNotificationsMockMvc.perform(get("/api/dq-notifications?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dqNotifications.getId().intValue())))
            .andExpect(jsonPath("$.[*].repicientId").value(hasItem(DEFAULT_REPICIENT_ID)))
            .andExpect(jsonPath("$.[*].repicientTypeId").value(hasItem(DEFAULT_REPICIENT_TYPE_ID)));

        // Check, that the count call also returns 1
        restDqNotificationsMockMvc.perform(get("/api/dq-notifications/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDqNotificationsShouldNotBeFound(String filter) throws Exception {
        restDqNotificationsMockMvc.perform(get("/api/dq-notifications?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDqNotificationsMockMvc.perform(get("/api/dq-notifications/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDqNotifications() throws Exception {
        // Get the dqNotifications
        restDqNotificationsMockMvc.perform(get("/api/dq-notifications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDqNotifications() throws Exception {
        // Initialize the database
        dqNotificationsRepository.saveAndFlush(dqNotifications);

        int databaseSizeBeforeUpdate = dqNotificationsRepository.findAll().size();

        // Update the dqNotifications
        DqNotifications updatedDqNotifications = dqNotificationsRepository.findById(dqNotifications.getId()).get();
        // Disconnect from session so that the updates on updatedDqNotifications are not directly saved in db
        em.detach(updatedDqNotifications);
        updatedDqNotifications
            .repicientId(UPDATED_REPICIENT_ID)
            .repicientTypeId(UPDATED_REPICIENT_TYPE_ID);
        DqNotificationsDTO dqNotificationsDTO = dqNotificationsMapper.toDto(updatedDqNotifications);

        restDqNotificationsMockMvc.perform(put("/api/dq-notifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqNotificationsDTO)))
            .andExpect(status().isOk());

        // Validate the DqNotifications in the database
        List<DqNotifications> dqNotificationsList = dqNotificationsRepository.findAll();
        assertThat(dqNotificationsList).hasSize(databaseSizeBeforeUpdate);
        DqNotifications testDqNotifications = dqNotificationsList.get(dqNotificationsList.size() - 1);
        assertThat(testDqNotifications.getRepicientId()).isEqualTo(UPDATED_REPICIENT_ID);
        assertThat(testDqNotifications.getRepicientTypeId()).isEqualTo(UPDATED_REPICIENT_TYPE_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingDqNotifications() throws Exception {
        int databaseSizeBeforeUpdate = dqNotificationsRepository.findAll().size();

        // Create the DqNotifications
        DqNotificationsDTO dqNotificationsDTO = dqNotificationsMapper.toDto(dqNotifications);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDqNotificationsMockMvc.perform(put("/api/dq-notifications")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqNotificationsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DqNotifications in the database
        List<DqNotifications> dqNotificationsList = dqNotificationsRepository.findAll();
        assertThat(dqNotificationsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDqNotifications() throws Exception {
        // Initialize the database
        dqNotificationsRepository.saveAndFlush(dqNotifications);

        int databaseSizeBeforeDelete = dqNotificationsRepository.findAll().size();

        // Delete the dqNotifications
        restDqNotificationsMockMvc.perform(delete("/api/dq-notifications/{id}", dqNotifications.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DqNotifications> dqNotificationsList = dqNotificationsRepository.findAll();
        assertThat(dqNotificationsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
