package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.DqRuleActions;
import com.mycompany.myapp.repository.DqRuleActionsRepository;
import com.mycompany.myapp.service.DqRuleActionsService;
import com.mycompany.myapp.service.dto.DqRuleActionsDTO;
import com.mycompany.myapp.service.mapper.DqRuleActionsMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.DqRuleActionsCriteria;
import com.mycompany.myapp.service.DqRuleActionsQueryService;

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
 * Integration tests for the {@link DqRuleActionsResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class DqRuleActionsResourceIT {

    private static final String DEFAULT_ACTION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ACTION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ACTION_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_ACTION_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private DqRuleActionsRepository dqRuleActionsRepository;

    @Autowired
    private DqRuleActionsMapper dqRuleActionsMapper;

    @Autowired
    private DqRuleActionsService dqRuleActionsService;

    @Autowired
    private DqRuleActionsQueryService dqRuleActionsQueryService;

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

    private MockMvc restDqRuleActionsMockMvc;

    private DqRuleActions dqRuleActions;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DqRuleActionsResource dqRuleActionsResource = new DqRuleActionsResource(dqRuleActionsService, dqRuleActionsQueryService);
        this.restDqRuleActionsMockMvc = MockMvcBuilders.standaloneSetup(dqRuleActionsResource)
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
    public static DqRuleActions createEntity(EntityManager em) {
        DqRuleActions dqRuleActions = new DqRuleActions()
            .actionName(DEFAULT_ACTION_NAME)
            .actionDescription(DEFAULT_ACTION_DESCRIPTION);
        return dqRuleActions;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DqRuleActions createUpdatedEntity(EntityManager em) {
        DqRuleActions dqRuleActions = new DqRuleActions()
            .actionName(UPDATED_ACTION_NAME)
            .actionDescription(UPDATED_ACTION_DESCRIPTION);
        return dqRuleActions;
    }

    @BeforeEach
    public void initTest() {
        dqRuleActions = createEntity(em);
    }

    @Test
    @Transactional
    public void createDqRuleActions() throws Exception {
        int databaseSizeBeforeCreate = dqRuleActionsRepository.findAll().size();

        // Create the DqRuleActions
        DqRuleActionsDTO dqRuleActionsDTO = dqRuleActionsMapper.toDto(dqRuleActions);
        restDqRuleActionsMockMvc.perform(post("/api/dq-rule-actions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqRuleActionsDTO)))
            .andExpect(status().isCreated());

        // Validate the DqRuleActions in the database
        List<DqRuleActions> dqRuleActionsList = dqRuleActionsRepository.findAll();
        assertThat(dqRuleActionsList).hasSize(databaseSizeBeforeCreate + 1);
        DqRuleActions testDqRuleActions = dqRuleActionsList.get(dqRuleActionsList.size() - 1);
        assertThat(testDqRuleActions.getActionName()).isEqualTo(DEFAULT_ACTION_NAME);
        assertThat(testDqRuleActions.getActionDescription()).isEqualTo(DEFAULT_ACTION_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createDqRuleActionsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dqRuleActionsRepository.findAll().size();

        // Create the DqRuleActions with an existing ID
        dqRuleActions.setId(1L);
        DqRuleActionsDTO dqRuleActionsDTO = dqRuleActionsMapper.toDto(dqRuleActions);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDqRuleActionsMockMvc.perform(post("/api/dq-rule-actions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqRuleActionsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DqRuleActions in the database
        List<DqRuleActions> dqRuleActionsList = dqRuleActionsRepository.findAll();
        assertThat(dqRuleActionsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDqRuleActions() throws Exception {
        // Initialize the database
        dqRuleActionsRepository.saveAndFlush(dqRuleActions);

        // Get all the dqRuleActionsList
        restDqRuleActionsMockMvc.perform(get("/api/dq-rule-actions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dqRuleActions.getId().intValue())))
            .andExpect(jsonPath("$.[*].actionName").value(hasItem(DEFAULT_ACTION_NAME)))
            .andExpect(jsonPath("$.[*].actionDescription").value(hasItem(DEFAULT_ACTION_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getDqRuleActions() throws Exception {
        // Initialize the database
        dqRuleActionsRepository.saveAndFlush(dqRuleActions);

        // Get the dqRuleActions
        restDqRuleActionsMockMvc.perform(get("/api/dq-rule-actions/{id}", dqRuleActions.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dqRuleActions.getId().intValue()))
            .andExpect(jsonPath("$.actionName").value(DEFAULT_ACTION_NAME))
            .andExpect(jsonPath("$.actionDescription").value(DEFAULT_ACTION_DESCRIPTION));
    }


    @Test
    @Transactional
    public void getDqRuleActionsByIdFiltering() throws Exception {
        // Initialize the database
        dqRuleActionsRepository.saveAndFlush(dqRuleActions);

        Long id = dqRuleActions.getId();

        defaultDqRuleActionsShouldBeFound("id.equals=" + id);
        defaultDqRuleActionsShouldNotBeFound("id.notEquals=" + id);

        defaultDqRuleActionsShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDqRuleActionsShouldNotBeFound("id.greaterThan=" + id);

        defaultDqRuleActionsShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDqRuleActionsShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDqRuleActionsByActionNameIsEqualToSomething() throws Exception {
        // Initialize the database
        dqRuleActionsRepository.saveAndFlush(dqRuleActions);

        // Get all the dqRuleActionsList where actionName equals to DEFAULT_ACTION_NAME
        defaultDqRuleActionsShouldBeFound("actionName.equals=" + DEFAULT_ACTION_NAME);

        // Get all the dqRuleActionsList where actionName equals to UPDATED_ACTION_NAME
        defaultDqRuleActionsShouldNotBeFound("actionName.equals=" + UPDATED_ACTION_NAME);
    }

    @Test
    @Transactional
    public void getAllDqRuleActionsByActionNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dqRuleActionsRepository.saveAndFlush(dqRuleActions);

        // Get all the dqRuleActionsList where actionName not equals to DEFAULT_ACTION_NAME
        defaultDqRuleActionsShouldNotBeFound("actionName.notEquals=" + DEFAULT_ACTION_NAME);

        // Get all the dqRuleActionsList where actionName not equals to UPDATED_ACTION_NAME
        defaultDqRuleActionsShouldBeFound("actionName.notEquals=" + UPDATED_ACTION_NAME);
    }

    @Test
    @Transactional
    public void getAllDqRuleActionsByActionNameIsInShouldWork() throws Exception {
        // Initialize the database
        dqRuleActionsRepository.saveAndFlush(dqRuleActions);

        // Get all the dqRuleActionsList where actionName in DEFAULT_ACTION_NAME or UPDATED_ACTION_NAME
        defaultDqRuleActionsShouldBeFound("actionName.in=" + DEFAULT_ACTION_NAME + "," + UPDATED_ACTION_NAME);

        // Get all the dqRuleActionsList where actionName equals to UPDATED_ACTION_NAME
        defaultDqRuleActionsShouldNotBeFound("actionName.in=" + UPDATED_ACTION_NAME);
    }

    @Test
    @Transactional
    public void getAllDqRuleActionsByActionNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        dqRuleActionsRepository.saveAndFlush(dqRuleActions);

        // Get all the dqRuleActionsList where actionName is not null
        defaultDqRuleActionsShouldBeFound("actionName.specified=true");

        // Get all the dqRuleActionsList where actionName is null
        defaultDqRuleActionsShouldNotBeFound("actionName.specified=false");
    }
                @Test
    @Transactional
    public void getAllDqRuleActionsByActionNameContainsSomething() throws Exception {
        // Initialize the database
        dqRuleActionsRepository.saveAndFlush(dqRuleActions);

        // Get all the dqRuleActionsList where actionName contains DEFAULT_ACTION_NAME
        defaultDqRuleActionsShouldBeFound("actionName.contains=" + DEFAULT_ACTION_NAME);

        // Get all the dqRuleActionsList where actionName contains UPDATED_ACTION_NAME
        defaultDqRuleActionsShouldNotBeFound("actionName.contains=" + UPDATED_ACTION_NAME);
    }

    @Test
    @Transactional
    public void getAllDqRuleActionsByActionNameNotContainsSomething() throws Exception {
        // Initialize the database
        dqRuleActionsRepository.saveAndFlush(dqRuleActions);

        // Get all the dqRuleActionsList where actionName does not contain DEFAULT_ACTION_NAME
        defaultDqRuleActionsShouldNotBeFound("actionName.doesNotContain=" + DEFAULT_ACTION_NAME);

        // Get all the dqRuleActionsList where actionName does not contain UPDATED_ACTION_NAME
        defaultDqRuleActionsShouldBeFound("actionName.doesNotContain=" + UPDATED_ACTION_NAME);
    }


    @Test
    @Transactional
    public void getAllDqRuleActionsByActionDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        dqRuleActionsRepository.saveAndFlush(dqRuleActions);

        // Get all the dqRuleActionsList where actionDescription equals to DEFAULT_ACTION_DESCRIPTION
        defaultDqRuleActionsShouldBeFound("actionDescription.equals=" + DEFAULT_ACTION_DESCRIPTION);

        // Get all the dqRuleActionsList where actionDescription equals to UPDATED_ACTION_DESCRIPTION
        defaultDqRuleActionsShouldNotBeFound("actionDescription.equals=" + UPDATED_ACTION_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDqRuleActionsByActionDescriptionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dqRuleActionsRepository.saveAndFlush(dqRuleActions);

        // Get all the dqRuleActionsList where actionDescription not equals to DEFAULT_ACTION_DESCRIPTION
        defaultDqRuleActionsShouldNotBeFound("actionDescription.notEquals=" + DEFAULT_ACTION_DESCRIPTION);

        // Get all the dqRuleActionsList where actionDescription not equals to UPDATED_ACTION_DESCRIPTION
        defaultDqRuleActionsShouldBeFound("actionDescription.notEquals=" + UPDATED_ACTION_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDqRuleActionsByActionDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        dqRuleActionsRepository.saveAndFlush(dqRuleActions);

        // Get all the dqRuleActionsList where actionDescription in DEFAULT_ACTION_DESCRIPTION or UPDATED_ACTION_DESCRIPTION
        defaultDqRuleActionsShouldBeFound("actionDescription.in=" + DEFAULT_ACTION_DESCRIPTION + "," + UPDATED_ACTION_DESCRIPTION);

        // Get all the dqRuleActionsList where actionDescription equals to UPDATED_ACTION_DESCRIPTION
        defaultDqRuleActionsShouldNotBeFound("actionDescription.in=" + UPDATED_ACTION_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDqRuleActionsByActionDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        dqRuleActionsRepository.saveAndFlush(dqRuleActions);

        // Get all the dqRuleActionsList where actionDescription is not null
        defaultDqRuleActionsShouldBeFound("actionDescription.specified=true");

        // Get all the dqRuleActionsList where actionDescription is null
        defaultDqRuleActionsShouldNotBeFound("actionDescription.specified=false");
    }
                @Test
    @Transactional
    public void getAllDqRuleActionsByActionDescriptionContainsSomething() throws Exception {
        // Initialize the database
        dqRuleActionsRepository.saveAndFlush(dqRuleActions);

        // Get all the dqRuleActionsList where actionDescription contains DEFAULT_ACTION_DESCRIPTION
        defaultDqRuleActionsShouldBeFound("actionDescription.contains=" + DEFAULT_ACTION_DESCRIPTION);

        // Get all the dqRuleActionsList where actionDescription contains UPDATED_ACTION_DESCRIPTION
        defaultDqRuleActionsShouldNotBeFound("actionDescription.contains=" + UPDATED_ACTION_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDqRuleActionsByActionDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        dqRuleActionsRepository.saveAndFlush(dqRuleActions);

        // Get all the dqRuleActionsList where actionDescription does not contain DEFAULT_ACTION_DESCRIPTION
        defaultDqRuleActionsShouldNotBeFound("actionDescription.doesNotContain=" + DEFAULT_ACTION_DESCRIPTION);

        // Get all the dqRuleActionsList where actionDescription does not contain UPDATED_ACTION_DESCRIPTION
        defaultDqRuleActionsShouldBeFound("actionDescription.doesNotContain=" + UPDATED_ACTION_DESCRIPTION);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDqRuleActionsShouldBeFound(String filter) throws Exception {
        restDqRuleActionsMockMvc.perform(get("/api/dq-rule-actions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dqRuleActions.getId().intValue())))
            .andExpect(jsonPath("$.[*].actionName").value(hasItem(DEFAULT_ACTION_NAME)))
            .andExpect(jsonPath("$.[*].actionDescription").value(hasItem(DEFAULT_ACTION_DESCRIPTION)));

        // Check, that the count call also returns 1
        restDqRuleActionsMockMvc.perform(get("/api/dq-rule-actions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDqRuleActionsShouldNotBeFound(String filter) throws Exception {
        restDqRuleActionsMockMvc.perform(get("/api/dq-rule-actions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDqRuleActionsMockMvc.perform(get("/api/dq-rule-actions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDqRuleActions() throws Exception {
        // Get the dqRuleActions
        restDqRuleActionsMockMvc.perform(get("/api/dq-rule-actions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDqRuleActions() throws Exception {
        // Initialize the database
        dqRuleActionsRepository.saveAndFlush(dqRuleActions);

        int databaseSizeBeforeUpdate = dqRuleActionsRepository.findAll().size();

        // Update the dqRuleActions
        DqRuleActions updatedDqRuleActions = dqRuleActionsRepository.findById(dqRuleActions.getId()).get();
        // Disconnect from session so that the updates on updatedDqRuleActions are not directly saved in db
        em.detach(updatedDqRuleActions);
        updatedDqRuleActions
            .actionName(UPDATED_ACTION_NAME)
            .actionDescription(UPDATED_ACTION_DESCRIPTION);
        DqRuleActionsDTO dqRuleActionsDTO = dqRuleActionsMapper.toDto(updatedDqRuleActions);

        restDqRuleActionsMockMvc.perform(put("/api/dq-rule-actions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqRuleActionsDTO)))
            .andExpect(status().isOk());

        // Validate the DqRuleActions in the database
        List<DqRuleActions> dqRuleActionsList = dqRuleActionsRepository.findAll();
        assertThat(dqRuleActionsList).hasSize(databaseSizeBeforeUpdate);
        DqRuleActions testDqRuleActions = dqRuleActionsList.get(dqRuleActionsList.size() - 1);
        assertThat(testDqRuleActions.getActionName()).isEqualTo(UPDATED_ACTION_NAME);
        assertThat(testDqRuleActions.getActionDescription()).isEqualTo(UPDATED_ACTION_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingDqRuleActions() throws Exception {
        int databaseSizeBeforeUpdate = dqRuleActionsRepository.findAll().size();

        // Create the DqRuleActions
        DqRuleActionsDTO dqRuleActionsDTO = dqRuleActionsMapper.toDto(dqRuleActions);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDqRuleActionsMockMvc.perform(put("/api/dq-rule-actions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqRuleActionsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DqRuleActions in the database
        List<DqRuleActions> dqRuleActionsList = dqRuleActionsRepository.findAll();
        assertThat(dqRuleActionsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDqRuleActions() throws Exception {
        // Initialize the database
        dqRuleActionsRepository.saveAndFlush(dqRuleActions);

        int databaseSizeBeforeDelete = dqRuleActionsRepository.findAll().size();

        // Delete the dqRuleActions
        restDqRuleActionsMockMvc.perform(delete("/api/dq-rule-actions/{id}", dqRuleActions.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DqRuleActions> dqRuleActionsList = dqRuleActionsRepository.findAll();
        assertThat(dqRuleActionsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
