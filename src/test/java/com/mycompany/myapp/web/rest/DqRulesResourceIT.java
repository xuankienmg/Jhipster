package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.DqRules;
import com.mycompany.myapp.domain.DqRuleTypes;
import com.mycompany.myapp.domain.DqRuleRiskLevels;
import com.mycompany.myapp.domain.DqRuleStatus;
import com.mycompany.myapp.domain.DqRuleCategories;
import com.mycompany.myapp.domain.DqRuleActions;
import com.mycompany.myapp.domain.DqStandards;
import com.mycompany.myapp.domain.DsColumns;
import com.mycompany.myapp.repository.DqRulesRepository;
import com.mycompany.myapp.service.DqRulesService;
import com.mycompany.myapp.service.dto.DqRulesDTO;
import com.mycompany.myapp.service.mapper.DqRulesMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.DqRulesCriteria;
import com.mycompany.myapp.service.DqRulesQueryService;

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
 * Integration tests for the {@link DqRulesResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class DqRulesResourceIT {

    private static final String DEFAULT_RULE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RULE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_RULE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_RULE_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private DqRulesRepository dqRulesRepository;

    @Autowired
    private DqRulesMapper dqRulesMapper;

    @Autowired
    private DqRulesService dqRulesService;

    @Autowired
    private DqRulesQueryService dqRulesQueryService;

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

    private MockMvc restDqRulesMockMvc;

    private DqRules dqRules;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DqRulesResource dqRulesResource = new DqRulesResource(dqRulesService, dqRulesQueryService);
        this.restDqRulesMockMvc = MockMvcBuilders.standaloneSetup(dqRulesResource)
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
    public static DqRules createEntity(EntityManager em) {
        DqRules dqRules = new DqRules()
            .ruleName(DEFAULT_RULE_NAME)
            .ruleDescription(DEFAULT_RULE_DESCRIPTION);
        return dqRules;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DqRules createUpdatedEntity(EntityManager em) {
        DqRules dqRules = new DqRules()
            .ruleName(UPDATED_RULE_NAME)
            .ruleDescription(UPDATED_RULE_DESCRIPTION);
        return dqRules;
    }

    @BeforeEach
    public void initTest() {
        dqRules = createEntity(em);
    }

    @Test
    @Transactional
    public void createDqRules() throws Exception {
        int databaseSizeBeforeCreate = dqRulesRepository.findAll().size();

        // Create the DqRules
        DqRulesDTO dqRulesDTO = dqRulesMapper.toDto(dqRules);
        restDqRulesMockMvc.perform(post("/api/dq-rules")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqRulesDTO)))
            .andExpect(status().isCreated());

        // Validate the DqRules in the database
        List<DqRules> dqRulesList = dqRulesRepository.findAll();
        assertThat(dqRulesList).hasSize(databaseSizeBeforeCreate + 1);
        DqRules testDqRules = dqRulesList.get(dqRulesList.size() - 1);
        assertThat(testDqRules.getRuleName()).isEqualTo(DEFAULT_RULE_NAME);
        assertThat(testDqRules.getRuleDescription()).isEqualTo(DEFAULT_RULE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createDqRulesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dqRulesRepository.findAll().size();

        // Create the DqRules with an existing ID
        dqRules.setId(1L);
        DqRulesDTO dqRulesDTO = dqRulesMapper.toDto(dqRules);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDqRulesMockMvc.perform(post("/api/dq-rules")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqRulesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DqRules in the database
        List<DqRules> dqRulesList = dqRulesRepository.findAll();
        assertThat(dqRulesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDqRules() throws Exception {
        // Initialize the database
        dqRulesRepository.saveAndFlush(dqRules);

        // Get all the dqRulesList
        restDqRulesMockMvc.perform(get("/api/dq-rules?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dqRules.getId().intValue())))
            .andExpect(jsonPath("$.[*].ruleName").value(hasItem(DEFAULT_RULE_NAME)))
            .andExpect(jsonPath("$.[*].ruleDescription").value(hasItem(DEFAULT_RULE_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getDqRules() throws Exception {
        // Initialize the database
        dqRulesRepository.saveAndFlush(dqRules);

        // Get the dqRules
        restDqRulesMockMvc.perform(get("/api/dq-rules/{id}", dqRules.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dqRules.getId().intValue()))
            .andExpect(jsonPath("$.ruleName").value(DEFAULT_RULE_NAME))
            .andExpect(jsonPath("$.ruleDescription").value(DEFAULT_RULE_DESCRIPTION));
    }


    @Test
    @Transactional
    public void getDqRulesByIdFiltering() throws Exception {
        // Initialize the database
        dqRulesRepository.saveAndFlush(dqRules);

        Long id = dqRules.getId();

        defaultDqRulesShouldBeFound("id.equals=" + id);
        defaultDqRulesShouldNotBeFound("id.notEquals=" + id);

        defaultDqRulesShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDqRulesShouldNotBeFound("id.greaterThan=" + id);

        defaultDqRulesShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDqRulesShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDqRulesByRuleNameIsEqualToSomething() throws Exception {
        // Initialize the database
        dqRulesRepository.saveAndFlush(dqRules);

        // Get all the dqRulesList where ruleName equals to DEFAULT_RULE_NAME
        defaultDqRulesShouldBeFound("ruleName.equals=" + DEFAULT_RULE_NAME);

        // Get all the dqRulesList where ruleName equals to UPDATED_RULE_NAME
        defaultDqRulesShouldNotBeFound("ruleName.equals=" + UPDATED_RULE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqRulesByRuleNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dqRulesRepository.saveAndFlush(dqRules);

        // Get all the dqRulesList where ruleName not equals to DEFAULT_RULE_NAME
        defaultDqRulesShouldNotBeFound("ruleName.notEquals=" + DEFAULT_RULE_NAME);

        // Get all the dqRulesList where ruleName not equals to UPDATED_RULE_NAME
        defaultDqRulesShouldBeFound("ruleName.notEquals=" + UPDATED_RULE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqRulesByRuleNameIsInShouldWork() throws Exception {
        // Initialize the database
        dqRulesRepository.saveAndFlush(dqRules);

        // Get all the dqRulesList where ruleName in DEFAULT_RULE_NAME or UPDATED_RULE_NAME
        defaultDqRulesShouldBeFound("ruleName.in=" + DEFAULT_RULE_NAME + "," + UPDATED_RULE_NAME);

        // Get all the dqRulesList where ruleName equals to UPDATED_RULE_NAME
        defaultDqRulesShouldNotBeFound("ruleName.in=" + UPDATED_RULE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqRulesByRuleNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        dqRulesRepository.saveAndFlush(dqRules);

        // Get all the dqRulesList where ruleName is not null
        defaultDqRulesShouldBeFound("ruleName.specified=true");

        // Get all the dqRulesList where ruleName is null
        defaultDqRulesShouldNotBeFound("ruleName.specified=false");
    }
                @Test
    @Transactional
    public void getAllDqRulesByRuleNameContainsSomething() throws Exception {
        // Initialize the database
        dqRulesRepository.saveAndFlush(dqRules);

        // Get all the dqRulesList where ruleName contains DEFAULT_RULE_NAME
        defaultDqRulesShouldBeFound("ruleName.contains=" + DEFAULT_RULE_NAME);

        // Get all the dqRulesList where ruleName contains UPDATED_RULE_NAME
        defaultDqRulesShouldNotBeFound("ruleName.contains=" + UPDATED_RULE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqRulesByRuleNameNotContainsSomething() throws Exception {
        // Initialize the database
        dqRulesRepository.saveAndFlush(dqRules);

        // Get all the dqRulesList where ruleName does not contain DEFAULT_RULE_NAME
        defaultDqRulesShouldNotBeFound("ruleName.doesNotContain=" + DEFAULT_RULE_NAME);

        // Get all the dqRulesList where ruleName does not contain UPDATED_RULE_NAME
        defaultDqRulesShouldBeFound("ruleName.doesNotContain=" + UPDATED_RULE_NAME);
    }


    @Test
    @Transactional
    public void getAllDqRulesByRuleDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        dqRulesRepository.saveAndFlush(dqRules);

        // Get all the dqRulesList where ruleDescription equals to DEFAULT_RULE_DESCRIPTION
        defaultDqRulesShouldBeFound("ruleDescription.equals=" + DEFAULT_RULE_DESCRIPTION);

        // Get all the dqRulesList where ruleDescription equals to UPDATED_RULE_DESCRIPTION
        defaultDqRulesShouldNotBeFound("ruleDescription.equals=" + UPDATED_RULE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDqRulesByRuleDescriptionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dqRulesRepository.saveAndFlush(dqRules);

        // Get all the dqRulesList where ruleDescription not equals to DEFAULT_RULE_DESCRIPTION
        defaultDqRulesShouldNotBeFound("ruleDescription.notEquals=" + DEFAULT_RULE_DESCRIPTION);

        // Get all the dqRulesList where ruleDescription not equals to UPDATED_RULE_DESCRIPTION
        defaultDqRulesShouldBeFound("ruleDescription.notEquals=" + UPDATED_RULE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDqRulesByRuleDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        dqRulesRepository.saveAndFlush(dqRules);

        // Get all the dqRulesList where ruleDescription in DEFAULT_RULE_DESCRIPTION or UPDATED_RULE_DESCRIPTION
        defaultDqRulesShouldBeFound("ruleDescription.in=" + DEFAULT_RULE_DESCRIPTION + "," + UPDATED_RULE_DESCRIPTION);

        // Get all the dqRulesList where ruleDescription equals to UPDATED_RULE_DESCRIPTION
        defaultDqRulesShouldNotBeFound("ruleDescription.in=" + UPDATED_RULE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDqRulesByRuleDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        dqRulesRepository.saveAndFlush(dqRules);

        // Get all the dqRulesList where ruleDescription is not null
        defaultDqRulesShouldBeFound("ruleDescription.specified=true");

        // Get all the dqRulesList where ruleDescription is null
        defaultDqRulesShouldNotBeFound("ruleDescription.specified=false");
    }
                @Test
    @Transactional
    public void getAllDqRulesByRuleDescriptionContainsSomething() throws Exception {
        // Initialize the database
        dqRulesRepository.saveAndFlush(dqRules);

        // Get all the dqRulesList where ruleDescription contains DEFAULT_RULE_DESCRIPTION
        defaultDqRulesShouldBeFound("ruleDescription.contains=" + DEFAULT_RULE_DESCRIPTION);

        // Get all the dqRulesList where ruleDescription contains UPDATED_RULE_DESCRIPTION
        defaultDqRulesShouldNotBeFound("ruleDescription.contains=" + UPDATED_RULE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDqRulesByRuleDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        dqRulesRepository.saveAndFlush(dqRules);

        // Get all the dqRulesList where ruleDescription does not contain DEFAULT_RULE_DESCRIPTION
        defaultDqRulesShouldNotBeFound("ruleDescription.doesNotContain=" + DEFAULT_RULE_DESCRIPTION);

        // Get all the dqRulesList where ruleDescription does not contain UPDATED_RULE_DESCRIPTION
        defaultDqRulesShouldBeFound("ruleDescription.doesNotContain=" + UPDATED_RULE_DESCRIPTION);
    }


    @Test
    @Transactional
    public void getAllDqRulesByTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        dqRulesRepository.saveAndFlush(dqRules);
        DqRuleTypes type = DqRuleTypesResourceIT.createEntity(em);
        em.persist(type);
        em.flush();
        dqRules.setType(type);
        dqRulesRepository.saveAndFlush(dqRules);
        Long typeId = type.getId();

        // Get all the dqRulesList where type equals to typeId
        defaultDqRulesShouldBeFound("typeId.equals=" + typeId);

        // Get all the dqRulesList where type equals to typeId + 1
        defaultDqRulesShouldNotBeFound("typeId.equals=" + (typeId + 1));
    }


    @Test
    @Transactional
    public void getAllDqRulesByRiskIsEqualToSomething() throws Exception {
        // Initialize the database
        dqRulesRepository.saveAndFlush(dqRules);
        DqRuleRiskLevels risk = DqRuleRiskLevelsResourceIT.createEntity(em);
        em.persist(risk);
        em.flush();
        dqRules.setRisk(risk);
        dqRulesRepository.saveAndFlush(dqRules);
        Long riskId = risk.getId();

        // Get all the dqRulesList where risk equals to riskId
        defaultDqRulesShouldBeFound("riskId.equals=" + riskId);

        // Get all the dqRulesList where risk equals to riskId + 1
        defaultDqRulesShouldNotBeFound("riskId.equals=" + (riskId + 1));
    }


    @Test
    @Transactional
    public void getAllDqRulesByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        dqRulesRepository.saveAndFlush(dqRules);
        DqRuleStatus status = DqRuleStatusResourceIT.createEntity(em);
        em.persist(status);
        em.flush();
        dqRules.setStatus(status);
        dqRulesRepository.saveAndFlush(dqRules);
        Long statusId = status.getId();

        // Get all the dqRulesList where status equals to statusId
        defaultDqRulesShouldBeFound("statusId.equals=" + statusId);

        // Get all the dqRulesList where status equals to statusId + 1
        defaultDqRulesShouldNotBeFound("statusId.equals=" + (statusId + 1));
    }


    @Test
    @Transactional
    public void getAllDqRulesByCatIsEqualToSomething() throws Exception {
        // Initialize the database
        dqRulesRepository.saveAndFlush(dqRules);
        DqRuleCategories cat = DqRuleCategoriesResourceIT.createEntity(em);
        em.persist(cat);
        em.flush();
        dqRules.setCat(cat);
        dqRulesRepository.saveAndFlush(dqRules);
        Long catId = cat.getId();

        // Get all the dqRulesList where cat equals to catId
        defaultDqRulesShouldBeFound("catId.equals=" + catId);

        // Get all the dqRulesList where cat equals to catId + 1
        defaultDqRulesShouldNotBeFound("catId.equals=" + (catId + 1));
    }


    @Test
    @Transactional
    public void getAllDqRulesByActionIsEqualToSomething() throws Exception {
        // Initialize the database
        dqRulesRepository.saveAndFlush(dqRules);
        DqRuleActions action = DqRuleActionsResourceIT.createEntity(em);
        em.persist(action);
        em.flush();
        dqRules.setAction(action);
        dqRulesRepository.saveAndFlush(dqRules);
        Long actionId = action.getId();

        // Get all the dqRulesList where action equals to actionId
        defaultDqRulesShouldBeFound("actionId.equals=" + actionId);

        // Get all the dqRulesList where action equals to actionId + 1
        defaultDqRulesShouldNotBeFound("actionId.equals=" + (actionId + 1));
    }


    @Test
    @Transactional
    public void getAllDqRulesByStdIsEqualToSomething() throws Exception {
        // Initialize the database
        dqRulesRepository.saveAndFlush(dqRules);
        DqStandards std = DqStandardsResourceIT.createEntity(em);
        em.persist(std);
        em.flush();
        dqRules.addStd(std);
        dqRulesRepository.saveAndFlush(dqRules);
        Long stdId = std.getId();

        // Get all the dqRulesList where std equals to stdId
        defaultDqRulesShouldBeFound("stdId.equals=" + stdId);

        // Get all the dqRulesList where std equals to stdId + 1
        defaultDqRulesShouldNotBeFound("stdId.equals=" + (stdId + 1));
    }


    @Test
    @Transactional
    public void getAllDqRulesByColIsEqualToSomething() throws Exception {
        // Initialize the database
        dqRulesRepository.saveAndFlush(dqRules);
        DsColumns col = DsColumnsResourceIT.createEntity(em);
        em.persist(col);
        em.flush();
        dqRules.addCol(col);
        dqRulesRepository.saveAndFlush(dqRules);
        Long colId = col.getId();

        // Get all the dqRulesList where col equals to colId
        defaultDqRulesShouldBeFound("colId.equals=" + colId);

        // Get all the dqRulesList where col equals to colId + 1
        defaultDqRulesShouldNotBeFound("colId.equals=" + (colId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDqRulesShouldBeFound(String filter) throws Exception {
        restDqRulesMockMvc.perform(get("/api/dq-rules?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dqRules.getId().intValue())))
            .andExpect(jsonPath("$.[*].ruleName").value(hasItem(DEFAULT_RULE_NAME)))
            .andExpect(jsonPath("$.[*].ruleDescription").value(hasItem(DEFAULT_RULE_DESCRIPTION)));

        // Check, that the count call also returns 1
        restDqRulesMockMvc.perform(get("/api/dq-rules/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDqRulesShouldNotBeFound(String filter) throws Exception {
        restDqRulesMockMvc.perform(get("/api/dq-rules?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDqRulesMockMvc.perform(get("/api/dq-rules/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDqRules() throws Exception {
        // Get the dqRules
        restDqRulesMockMvc.perform(get("/api/dq-rules/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDqRules() throws Exception {
        // Initialize the database
        dqRulesRepository.saveAndFlush(dqRules);

        int databaseSizeBeforeUpdate = dqRulesRepository.findAll().size();

        // Update the dqRules
        DqRules updatedDqRules = dqRulesRepository.findById(dqRules.getId()).get();
        // Disconnect from session so that the updates on updatedDqRules are not directly saved in db
        em.detach(updatedDqRules);
        updatedDqRules
            .ruleName(UPDATED_RULE_NAME)
            .ruleDescription(UPDATED_RULE_DESCRIPTION);
        DqRulesDTO dqRulesDTO = dqRulesMapper.toDto(updatedDqRules);

        restDqRulesMockMvc.perform(put("/api/dq-rules")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqRulesDTO)))
            .andExpect(status().isOk());

        // Validate the DqRules in the database
        List<DqRules> dqRulesList = dqRulesRepository.findAll();
        assertThat(dqRulesList).hasSize(databaseSizeBeforeUpdate);
        DqRules testDqRules = dqRulesList.get(dqRulesList.size() - 1);
        assertThat(testDqRules.getRuleName()).isEqualTo(UPDATED_RULE_NAME);
        assertThat(testDqRules.getRuleDescription()).isEqualTo(UPDATED_RULE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingDqRules() throws Exception {
        int databaseSizeBeforeUpdate = dqRulesRepository.findAll().size();

        // Create the DqRules
        DqRulesDTO dqRulesDTO = dqRulesMapper.toDto(dqRules);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDqRulesMockMvc.perform(put("/api/dq-rules")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqRulesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DqRules in the database
        List<DqRules> dqRulesList = dqRulesRepository.findAll();
        assertThat(dqRulesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDqRules() throws Exception {
        // Initialize the database
        dqRulesRepository.saveAndFlush(dqRules);

        int databaseSizeBeforeDelete = dqRulesRepository.findAll().size();

        // Delete the dqRules
        restDqRulesMockMvc.perform(delete("/api/dq-rules/{id}", dqRules.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DqRules> dqRulesList = dqRulesRepository.findAll();
        assertThat(dqRulesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
