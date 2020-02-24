package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.DqStandards;
import com.mycompany.myapp.domain.DqStandardTypes;
import com.mycompany.myapp.domain.DqRules;
import com.mycompany.myapp.repository.DqStandardsRepository;
import com.mycompany.myapp.service.DqStandardsService;
import com.mycompany.myapp.service.dto.DqStandardsDTO;
import com.mycompany.myapp.service.mapper.DqStandardsMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.DqStandardsCriteria;
import com.mycompany.myapp.service.DqStandardsQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DqStandardsResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class DqStandardsResourceIT {

    private static final String DEFAULT_STD_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STD_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STD_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_STD_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private DqStandardsRepository dqStandardsRepository;

    @Mock
    private DqStandardsRepository dqStandardsRepositoryMock;

    @Autowired
    private DqStandardsMapper dqStandardsMapper;

    @Mock
    private DqStandardsService dqStandardsServiceMock;

    @Autowired
    private DqStandardsService dqStandardsService;

    @Autowired
    private DqStandardsQueryService dqStandardsQueryService;

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

    private MockMvc restDqStandardsMockMvc;

    private DqStandards dqStandards;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DqStandardsResource dqStandardsResource = new DqStandardsResource(dqStandardsService, dqStandardsQueryService);
        this.restDqStandardsMockMvc = MockMvcBuilders.standaloneSetup(dqStandardsResource)
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
    public static DqStandards createEntity(EntityManager em) {
        DqStandards dqStandards = new DqStandards()
            .stdName(DEFAULT_STD_NAME)
            .stdDescription(DEFAULT_STD_DESCRIPTION);
        return dqStandards;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DqStandards createUpdatedEntity(EntityManager em) {
        DqStandards dqStandards = new DqStandards()
            .stdName(UPDATED_STD_NAME)
            .stdDescription(UPDATED_STD_DESCRIPTION);
        return dqStandards;
    }

    @BeforeEach
    public void initTest() {
        dqStandards = createEntity(em);
    }

    @Test
    @Transactional
    public void createDqStandards() throws Exception {
        int databaseSizeBeforeCreate = dqStandardsRepository.findAll().size();

        // Create the DqStandards
        DqStandardsDTO dqStandardsDTO = dqStandardsMapper.toDto(dqStandards);
        restDqStandardsMockMvc.perform(post("/api/dq-standards")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqStandardsDTO)))
            .andExpect(status().isCreated());

        // Validate the DqStandards in the database
        List<DqStandards> dqStandardsList = dqStandardsRepository.findAll();
        assertThat(dqStandardsList).hasSize(databaseSizeBeforeCreate + 1);
        DqStandards testDqStandards = dqStandardsList.get(dqStandardsList.size() - 1);
        assertThat(testDqStandards.getStdName()).isEqualTo(DEFAULT_STD_NAME);
        assertThat(testDqStandards.getStdDescription()).isEqualTo(DEFAULT_STD_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createDqStandardsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dqStandardsRepository.findAll().size();

        // Create the DqStandards with an existing ID
        dqStandards.setId(1L);
        DqStandardsDTO dqStandardsDTO = dqStandardsMapper.toDto(dqStandards);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDqStandardsMockMvc.perform(post("/api/dq-standards")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqStandardsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DqStandards in the database
        List<DqStandards> dqStandardsList = dqStandardsRepository.findAll();
        assertThat(dqStandardsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDqStandards() throws Exception {
        // Initialize the database
        dqStandardsRepository.saveAndFlush(dqStandards);

        // Get all the dqStandardsList
        restDqStandardsMockMvc.perform(get("/api/dq-standards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dqStandards.getId().intValue())))
            .andExpect(jsonPath("$.[*].stdName").value(hasItem(DEFAULT_STD_NAME)))
            .andExpect(jsonPath("$.[*].stdDescription").value(hasItem(DEFAULT_STD_DESCRIPTION)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllDqStandardsWithEagerRelationshipsIsEnabled() throws Exception {
        DqStandardsResource dqStandardsResource = new DqStandardsResource(dqStandardsServiceMock, dqStandardsQueryService);
        when(dqStandardsServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restDqStandardsMockMvc = MockMvcBuilders.standaloneSetup(dqStandardsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restDqStandardsMockMvc.perform(get("/api/dq-standards?eagerload=true"))
        .andExpect(status().isOk());

        verify(dqStandardsServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllDqStandardsWithEagerRelationshipsIsNotEnabled() throws Exception {
        DqStandardsResource dqStandardsResource = new DqStandardsResource(dqStandardsServiceMock, dqStandardsQueryService);
            when(dqStandardsServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restDqStandardsMockMvc = MockMvcBuilders.standaloneSetup(dqStandardsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restDqStandardsMockMvc.perform(get("/api/dq-standards?eagerload=true"))
        .andExpect(status().isOk());

            verify(dqStandardsServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getDqStandards() throws Exception {
        // Initialize the database
        dqStandardsRepository.saveAndFlush(dqStandards);

        // Get the dqStandards
        restDqStandardsMockMvc.perform(get("/api/dq-standards/{id}", dqStandards.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dqStandards.getId().intValue()))
            .andExpect(jsonPath("$.stdName").value(DEFAULT_STD_NAME))
            .andExpect(jsonPath("$.stdDescription").value(DEFAULT_STD_DESCRIPTION));
    }


    @Test
    @Transactional
    public void getDqStandardsByIdFiltering() throws Exception {
        // Initialize the database
        dqStandardsRepository.saveAndFlush(dqStandards);

        Long id = dqStandards.getId();

        defaultDqStandardsShouldBeFound("id.equals=" + id);
        defaultDqStandardsShouldNotBeFound("id.notEquals=" + id);

        defaultDqStandardsShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDqStandardsShouldNotBeFound("id.greaterThan=" + id);

        defaultDqStandardsShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDqStandardsShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDqStandardsByStdNameIsEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardsRepository.saveAndFlush(dqStandards);

        // Get all the dqStandardsList where stdName equals to DEFAULT_STD_NAME
        defaultDqStandardsShouldBeFound("stdName.equals=" + DEFAULT_STD_NAME);

        // Get all the dqStandardsList where stdName equals to UPDATED_STD_NAME
        defaultDqStandardsShouldNotBeFound("stdName.equals=" + UPDATED_STD_NAME);
    }

    @Test
    @Transactional
    public void getAllDqStandardsByStdNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardsRepository.saveAndFlush(dqStandards);

        // Get all the dqStandardsList where stdName not equals to DEFAULT_STD_NAME
        defaultDqStandardsShouldNotBeFound("stdName.notEquals=" + DEFAULT_STD_NAME);

        // Get all the dqStandardsList where stdName not equals to UPDATED_STD_NAME
        defaultDqStandardsShouldBeFound("stdName.notEquals=" + UPDATED_STD_NAME);
    }

    @Test
    @Transactional
    public void getAllDqStandardsByStdNameIsInShouldWork() throws Exception {
        // Initialize the database
        dqStandardsRepository.saveAndFlush(dqStandards);

        // Get all the dqStandardsList where stdName in DEFAULT_STD_NAME or UPDATED_STD_NAME
        defaultDqStandardsShouldBeFound("stdName.in=" + DEFAULT_STD_NAME + "," + UPDATED_STD_NAME);

        // Get all the dqStandardsList where stdName equals to UPDATED_STD_NAME
        defaultDqStandardsShouldNotBeFound("stdName.in=" + UPDATED_STD_NAME);
    }

    @Test
    @Transactional
    public void getAllDqStandardsByStdNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        dqStandardsRepository.saveAndFlush(dqStandards);

        // Get all the dqStandardsList where stdName is not null
        defaultDqStandardsShouldBeFound("stdName.specified=true");

        // Get all the dqStandardsList where stdName is null
        defaultDqStandardsShouldNotBeFound("stdName.specified=false");
    }
                @Test
    @Transactional
    public void getAllDqStandardsByStdNameContainsSomething() throws Exception {
        // Initialize the database
        dqStandardsRepository.saveAndFlush(dqStandards);

        // Get all the dqStandardsList where stdName contains DEFAULT_STD_NAME
        defaultDqStandardsShouldBeFound("stdName.contains=" + DEFAULT_STD_NAME);

        // Get all the dqStandardsList where stdName contains UPDATED_STD_NAME
        defaultDqStandardsShouldNotBeFound("stdName.contains=" + UPDATED_STD_NAME);
    }

    @Test
    @Transactional
    public void getAllDqStandardsByStdNameNotContainsSomething() throws Exception {
        // Initialize the database
        dqStandardsRepository.saveAndFlush(dqStandards);

        // Get all the dqStandardsList where stdName does not contain DEFAULT_STD_NAME
        defaultDqStandardsShouldNotBeFound("stdName.doesNotContain=" + DEFAULT_STD_NAME);

        // Get all the dqStandardsList where stdName does not contain UPDATED_STD_NAME
        defaultDqStandardsShouldBeFound("stdName.doesNotContain=" + UPDATED_STD_NAME);
    }


    @Test
    @Transactional
    public void getAllDqStandardsByStdDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardsRepository.saveAndFlush(dqStandards);

        // Get all the dqStandardsList where stdDescription equals to DEFAULT_STD_DESCRIPTION
        defaultDqStandardsShouldBeFound("stdDescription.equals=" + DEFAULT_STD_DESCRIPTION);

        // Get all the dqStandardsList where stdDescription equals to UPDATED_STD_DESCRIPTION
        defaultDqStandardsShouldNotBeFound("stdDescription.equals=" + UPDATED_STD_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDqStandardsByStdDescriptionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardsRepository.saveAndFlush(dqStandards);

        // Get all the dqStandardsList where stdDescription not equals to DEFAULT_STD_DESCRIPTION
        defaultDqStandardsShouldNotBeFound("stdDescription.notEquals=" + DEFAULT_STD_DESCRIPTION);

        // Get all the dqStandardsList where stdDescription not equals to UPDATED_STD_DESCRIPTION
        defaultDqStandardsShouldBeFound("stdDescription.notEquals=" + UPDATED_STD_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDqStandardsByStdDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        dqStandardsRepository.saveAndFlush(dqStandards);

        // Get all the dqStandardsList where stdDescription in DEFAULT_STD_DESCRIPTION or UPDATED_STD_DESCRIPTION
        defaultDqStandardsShouldBeFound("stdDescription.in=" + DEFAULT_STD_DESCRIPTION + "," + UPDATED_STD_DESCRIPTION);

        // Get all the dqStandardsList where stdDescription equals to UPDATED_STD_DESCRIPTION
        defaultDqStandardsShouldNotBeFound("stdDescription.in=" + UPDATED_STD_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDqStandardsByStdDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        dqStandardsRepository.saveAndFlush(dqStandards);

        // Get all the dqStandardsList where stdDescription is not null
        defaultDqStandardsShouldBeFound("stdDescription.specified=true");

        // Get all the dqStandardsList where stdDescription is null
        defaultDqStandardsShouldNotBeFound("stdDescription.specified=false");
    }
                @Test
    @Transactional
    public void getAllDqStandardsByStdDescriptionContainsSomething() throws Exception {
        // Initialize the database
        dqStandardsRepository.saveAndFlush(dqStandards);

        // Get all the dqStandardsList where stdDescription contains DEFAULT_STD_DESCRIPTION
        defaultDqStandardsShouldBeFound("stdDescription.contains=" + DEFAULT_STD_DESCRIPTION);

        // Get all the dqStandardsList where stdDescription contains UPDATED_STD_DESCRIPTION
        defaultDqStandardsShouldNotBeFound("stdDescription.contains=" + UPDATED_STD_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDqStandardsByStdDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        dqStandardsRepository.saveAndFlush(dqStandards);

        // Get all the dqStandardsList where stdDescription does not contain DEFAULT_STD_DESCRIPTION
        defaultDqStandardsShouldNotBeFound("stdDescription.doesNotContain=" + DEFAULT_STD_DESCRIPTION);

        // Get all the dqStandardsList where stdDescription does not contain UPDATED_STD_DESCRIPTION
        defaultDqStandardsShouldBeFound("stdDescription.doesNotContain=" + UPDATED_STD_DESCRIPTION);
    }


    @Test
    @Transactional
    public void getAllDqStandardsByStdTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardsRepository.saveAndFlush(dqStandards);
        DqStandardTypes stdType = DqStandardTypesResourceIT.createEntity(em);
        em.persist(stdType);
        em.flush();
        dqStandards.setStdType(stdType);
        dqStandardsRepository.saveAndFlush(dqStandards);
        Long stdTypeId = stdType.getId();

        // Get all the dqStandardsList where stdType equals to stdTypeId
        defaultDqStandardsShouldBeFound("stdTypeId.equals=" + stdTypeId);

        // Get all the dqStandardsList where stdType equals to stdTypeId + 1
        defaultDqStandardsShouldNotBeFound("stdTypeId.equals=" + (stdTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllDqStandardsByRuleIsEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardsRepository.saveAndFlush(dqStandards);
        DqRules rule = DqRulesResourceIT.createEntity(em);
        em.persist(rule);
        em.flush();
        dqStandards.addRule(rule);
        dqStandardsRepository.saveAndFlush(dqStandards);
        Long ruleId = rule.getId();

        // Get all the dqStandardsList where rule equals to ruleId
        defaultDqStandardsShouldBeFound("ruleId.equals=" + ruleId);

        // Get all the dqStandardsList where rule equals to ruleId + 1
        defaultDqStandardsShouldNotBeFound("ruleId.equals=" + (ruleId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDqStandardsShouldBeFound(String filter) throws Exception {
        restDqStandardsMockMvc.perform(get("/api/dq-standards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dqStandards.getId().intValue())))
            .andExpect(jsonPath("$.[*].stdName").value(hasItem(DEFAULT_STD_NAME)))
            .andExpect(jsonPath("$.[*].stdDescription").value(hasItem(DEFAULT_STD_DESCRIPTION)));

        // Check, that the count call also returns 1
        restDqStandardsMockMvc.perform(get("/api/dq-standards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDqStandardsShouldNotBeFound(String filter) throws Exception {
        restDqStandardsMockMvc.perform(get("/api/dq-standards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDqStandardsMockMvc.perform(get("/api/dq-standards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDqStandards() throws Exception {
        // Get the dqStandards
        restDqStandardsMockMvc.perform(get("/api/dq-standards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDqStandards() throws Exception {
        // Initialize the database
        dqStandardsRepository.saveAndFlush(dqStandards);

        int databaseSizeBeforeUpdate = dqStandardsRepository.findAll().size();

        // Update the dqStandards
        DqStandards updatedDqStandards = dqStandardsRepository.findById(dqStandards.getId()).get();
        // Disconnect from session so that the updates on updatedDqStandards are not directly saved in db
        em.detach(updatedDqStandards);
        updatedDqStandards
            .stdName(UPDATED_STD_NAME)
            .stdDescription(UPDATED_STD_DESCRIPTION);
        DqStandardsDTO dqStandardsDTO = dqStandardsMapper.toDto(updatedDqStandards);

        restDqStandardsMockMvc.perform(put("/api/dq-standards")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqStandardsDTO)))
            .andExpect(status().isOk());

        // Validate the DqStandards in the database
        List<DqStandards> dqStandardsList = dqStandardsRepository.findAll();
        assertThat(dqStandardsList).hasSize(databaseSizeBeforeUpdate);
        DqStandards testDqStandards = dqStandardsList.get(dqStandardsList.size() - 1);
        assertThat(testDqStandards.getStdName()).isEqualTo(UPDATED_STD_NAME);
        assertThat(testDqStandards.getStdDescription()).isEqualTo(UPDATED_STD_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingDqStandards() throws Exception {
        int databaseSizeBeforeUpdate = dqStandardsRepository.findAll().size();

        // Create the DqStandards
        DqStandardsDTO dqStandardsDTO = dqStandardsMapper.toDto(dqStandards);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDqStandardsMockMvc.perform(put("/api/dq-standards")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqStandardsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DqStandards in the database
        List<DqStandards> dqStandardsList = dqStandardsRepository.findAll();
        assertThat(dqStandardsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDqStandards() throws Exception {
        // Initialize the database
        dqStandardsRepository.saveAndFlush(dqStandards);

        int databaseSizeBeforeDelete = dqStandardsRepository.findAll().size();

        // Delete the dqStandards
        restDqStandardsMockMvc.perform(delete("/api/dq-standards/{id}", dqStandards.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DqStandards> dqStandardsList = dqStandardsRepository.findAll();
        assertThat(dqStandardsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
