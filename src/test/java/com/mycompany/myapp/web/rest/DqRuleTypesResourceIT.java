package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.DqRuleTypes;
import com.mycompany.myapp.repository.DqRuleTypesRepository;
import com.mycompany.myapp.service.DqRuleTypesService;
import com.mycompany.myapp.service.dto.DqRuleTypesDTO;
import com.mycompany.myapp.service.mapper.DqRuleTypesMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.DqRuleTypesCriteria;
import com.mycompany.myapp.service.DqRuleTypesQueryService;

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
 * Integration tests for the {@link DqRuleTypesResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class DqRuleTypesResourceIT {

    private static final String DEFAULT_TYPE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private DqRuleTypesRepository dqRuleTypesRepository;

    @Autowired
    private DqRuleTypesMapper dqRuleTypesMapper;

    @Autowired
    private DqRuleTypesService dqRuleTypesService;

    @Autowired
    private DqRuleTypesQueryService dqRuleTypesQueryService;

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

    private MockMvc restDqRuleTypesMockMvc;

    private DqRuleTypes dqRuleTypes;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DqRuleTypesResource dqRuleTypesResource = new DqRuleTypesResource(dqRuleTypesService, dqRuleTypesQueryService);
        this.restDqRuleTypesMockMvc = MockMvcBuilders.standaloneSetup(dqRuleTypesResource)
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
    public static DqRuleTypes createEntity(EntityManager em) {
        DqRuleTypes dqRuleTypes = new DqRuleTypes()
            .typeName(DEFAULT_TYPE_NAME)
            .typeDescription(DEFAULT_TYPE_DESCRIPTION);
        return dqRuleTypes;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DqRuleTypes createUpdatedEntity(EntityManager em) {
        DqRuleTypes dqRuleTypes = new DqRuleTypes()
            .typeName(UPDATED_TYPE_NAME)
            .typeDescription(UPDATED_TYPE_DESCRIPTION);
        return dqRuleTypes;
    }

    @BeforeEach
    public void initTest() {
        dqRuleTypes = createEntity(em);
    }

    @Test
    @Transactional
    public void createDqRuleTypes() throws Exception {
        int databaseSizeBeforeCreate = dqRuleTypesRepository.findAll().size();

        // Create the DqRuleTypes
        DqRuleTypesDTO dqRuleTypesDTO = dqRuleTypesMapper.toDto(dqRuleTypes);
        restDqRuleTypesMockMvc.perform(post("/api/dq-rule-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqRuleTypesDTO)))
            .andExpect(status().isCreated());

        // Validate the DqRuleTypes in the database
        List<DqRuleTypes> dqRuleTypesList = dqRuleTypesRepository.findAll();
        assertThat(dqRuleTypesList).hasSize(databaseSizeBeforeCreate + 1);
        DqRuleTypes testDqRuleTypes = dqRuleTypesList.get(dqRuleTypesList.size() - 1);
        assertThat(testDqRuleTypes.getTypeName()).isEqualTo(DEFAULT_TYPE_NAME);
        assertThat(testDqRuleTypes.getTypeDescription()).isEqualTo(DEFAULT_TYPE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createDqRuleTypesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dqRuleTypesRepository.findAll().size();

        // Create the DqRuleTypes with an existing ID
        dqRuleTypes.setId(1L);
        DqRuleTypesDTO dqRuleTypesDTO = dqRuleTypesMapper.toDto(dqRuleTypes);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDqRuleTypesMockMvc.perform(post("/api/dq-rule-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqRuleTypesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DqRuleTypes in the database
        List<DqRuleTypes> dqRuleTypesList = dqRuleTypesRepository.findAll();
        assertThat(dqRuleTypesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDqRuleTypes() throws Exception {
        // Initialize the database
        dqRuleTypesRepository.saveAndFlush(dqRuleTypes);

        // Get all the dqRuleTypesList
        restDqRuleTypesMockMvc.perform(get("/api/dq-rule-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dqRuleTypes.getId().intValue())))
            .andExpect(jsonPath("$.[*].typeName").value(hasItem(DEFAULT_TYPE_NAME)))
            .andExpect(jsonPath("$.[*].typeDescription").value(hasItem(DEFAULT_TYPE_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getDqRuleTypes() throws Exception {
        // Initialize the database
        dqRuleTypesRepository.saveAndFlush(dqRuleTypes);

        // Get the dqRuleTypes
        restDqRuleTypesMockMvc.perform(get("/api/dq-rule-types/{id}", dqRuleTypes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dqRuleTypes.getId().intValue()))
            .andExpect(jsonPath("$.typeName").value(DEFAULT_TYPE_NAME))
            .andExpect(jsonPath("$.typeDescription").value(DEFAULT_TYPE_DESCRIPTION));
    }


    @Test
    @Transactional
    public void getDqRuleTypesByIdFiltering() throws Exception {
        // Initialize the database
        dqRuleTypesRepository.saveAndFlush(dqRuleTypes);

        Long id = dqRuleTypes.getId();

        defaultDqRuleTypesShouldBeFound("id.equals=" + id);
        defaultDqRuleTypesShouldNotBeFound("id.notEquals=" + id);

        defaultDqRuleTypesShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDqRuleTypesShouldNotBeFound("id.greaterThan=" + id);

        defaultDqRuleTypesShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDqRuleTypesShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDqRuleTypesByTypeNameIsEqualToSomething() throws Exception {
        // Initialize the database
        dqRuleTypesRepository.saveAndFlush(dqRuleTypes);

        // Get all the dqRuleTypesList where typeName equals to DEFAULT_TYPE_NAME
        defaultDqRuleTypesShouldBeFound("typeName.equals=" + DEFAULT_TYPE_NAME);

        // Get all the dqRuleTypesList where typeName equals to UPDATED_TYPE_NAME
        defaultDqRuleTypesShouldNotBeFound("typeName.equals=" + UPDATED_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqRuleTypesByTypeNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dqRuleTypesRepository.saveAndFlush(dqRuleTypes);

        // Get all the dqRuleTypesList where typeName not equals to DEFAULT_TYPE_NAME
        defaultDqRuleTypesShouldNotBeFound("typeName.notEquals=" + DEFAULT_TYPE_NAME);

        // Get all the dqRuleTypesList where typeName not equals to UPDATED_TYPE_NAME
        defaultDqRuleTypesShouldBeFound("typeName.notEquals=" + UPDATED_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqRuleTypesByTypeNameIsInShouldWork() throws Exception {
        // Initialize the database
        dqRuleTypesRepository.saveAndFlush(dqRuleTypes);

        // Get all the dqRuleTypesList where typeName in DEFAULT_TYPE_NAME or UPDATED_TYPE_NAME
        defaultDqRuleTypesShouldBeFound("typeName.in=" + DEFAULT_TYPE_NAME + "," + UPDATED_TYPE_NAME);

        // Get all the dqRuleTypesList where typeName equals to UPDATED_TYPE_NAME
        defaultDqRuleTypesShouldNotBeFound("typeName.in=" + UPDATED_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqRuleTypesByTypeNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        dqRuleTypesRepository.saveAndFlush(dqRuleTypes);

        // Get all the dqRuleTypesList where typeName is not null
        defaultDqRuleTypesShouldBeFound("typeName.specified=true");

        // Get all the dqRuleTypesList where typeName is null
        defaultDqRuleTypesShouldNotBeFound("typeName.specified=false");
    }
                @Test
    @Transactional
    public void getAllDqRuleTypesByTypeNameContainsSomething() throws Exception {
        // Initialize the database
        dqRuleTypesRepository.saveAndFlush(dqRuleTypes);

        // Get all the dqRuleTypesList where typeName contains DEFAULT_TYPE_NAME
        defaultDqRuleTypesShouldBeFound("typeName.contains=" + DEFAULT_TYPE_NAME);

        // Get all the dqRuleTypesList where typeName contains UPDATED_TYPE_NAME
        defaultDqRuleTypesShouldNotBeFound("typeName.contains=" + UPDATED_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqRuleTypesByTypeNameNotContainsSomething() throws Exception {
        // Initialize the database
        dqRuleTypesRepository.saveAndFlush(dqRuleTypes);

        // Get all the dqRuleTypesList where typeName does not contain DEFAULT_TYPE_NAME
        defaultDqRuleTypesShouldNotBeFound("typeName.doesNotContain=" + DEFAULT_TYPE_NAME);

        // Get all the dqRuleTypesList where typeName does not contain UPDATED_TYPE_NAME
        defaultDqRuleTypesShouldBeFound("typeName.doesNotContain=" + UPDATED_TYPE_NAME);
    }


    @Test
    @Transactional
    public void getAllDqRuleTypesByTypeDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        dqRuleTypesRepository.saveAndFlush(dqRuleTypes);

        // Get all the dqRuleTypesList where typeDescription equals to DEFAULT_TYPE_DESCRIPTION
        defaultDqRuleTypesShouldBeFound("typeDescription.equals=" + DEFAULT_TYPE_DESCRIPTION);

        // Get all the dqRuleTypesList where typeDescription equals to UPDATED_TYPE_DESCRIPTION
        defaultDqRuleTypesShouldNotBeFound("typeDescription.equals=" + UPDATED_TYPE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDqRuleTypesByTypeDescriptionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dqRuleTypesRepository.saveAndFlush(dqRuleTypes);

        // Get all the dqRuleTypesList where typeDescription not equals to DEFAULT_TYPE_DESCRIPTION
        defaultDqRuleTypesShouldNotBeFound("typeDescription.notEquals=" + DEFAULT_TYPE_DESCRIPTION);

        // Get all the dqRuleTypesList where typeDescription not equals to UPDATED_TYPE_DESCRIPTION
        defaultDqRuleTypesShouldBeFound("typeDescription.notEquals=" + UPDATED_TYPE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDqRuleTypesByTypeDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        dqRuleTypesRepository.saveAndFlush(dqRuleTypes);

        // Get all the dqRuleTypesList where typeDescription in DEFAULT_TYPE_DESCRIPTION or UPDATED_TYPE_DESCRIPTION
        defaultDqRuleTypesShouldBeFound("typeDescription.in=" + DEFAULT_TYPE_DESCRIPTION + "," + UPDATED_TYPE_DESCRIPTION);

        // Get all the dqRuleTypesList where typeDescription equals to UPDATED_TYPE_DESCRIPTION
        defaultDqRuleTypesShouldNotBeFound("typeDescription.in=" + UPDATED_TYPE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDqRuleTypesByTypeDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        dqRuleTypesRepository.saveAndFlush(dqRuleTypes);

        // Get all the dqRuleTypesList where typeDescription is not null
        defaultDqRuleTypesShouldBeFound("typeDescription.specified=true");

        // Get all the dqRuleTypesList where typeDescription is null
        defaultDqRuleTypesShouldNotBeFound("typeDescription.specified=false");
    }
                @Test
    @Transactional
    public void getAllDqRuleTypesByTypeDescriptionContainsSomething() throws Exception {
        // Initialize the database
        dqRuleTypesRepository.saveAndFlush(dqRuleTypes);

        // Get all the dqRuleTypesList where typeDescription contains DEFAULT_TYPE_DESCRIPTION
        defaultDqRuleTypesShouldBeFound("typeDescription.contains=" + DEFAULT_TYPE_DESCRIPTION);

        // Get all the dqRuleTypesList where typeDescription contains UPDATED_TYPE_DESCRIPTION
        defaultDqRuleTypesShouldNotBeFound("typeDescription.contains=" + UPDATED_TYPE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDqRuleTypesByTypeDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        dqRuleTypesRepository.saveAndFlush(dqRuleTypes);

        // Get all the dqRuleTypesList where typeDescription does not contain DEFAULT_TYPE_DESCRIPTION
        defaultDqRuleTypesShouldNotBeFound("typeDescription.doesNotContain=" + DEFAULT_TYPE_DESCRIPTION);

        // Get all the dqRuleTypesList where typeDescription does not contain UPDATED_TYPE_DESCRIPTION
        defaultDqRuleTypesShouldBeFound("typeDescription.doesNotContain=" + UPDATED_TYPE_DESCRIPTION);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDqRuleTypesShouldBeFound(String filter) throws Exception {
        restDqRuleTypesMockMvc.perform(get("/api/dq-rule-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dqRuleTypes.getId().intValue())))
            .andExpect(jsonPath("$.[*].typeName").value(hasItem(DEFAULT_TYPE_NAME)))
            .andExpect(jsonPath("$.[*].typeDescription").value(hasItem(DEFAULT_TYPE_DESCRIPTION)));

        // Check, that the count call also returns 1
        restDqRuleTypesMockMvc.perform(get("/api/dq-rule-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDqRuleTypesShouldNotBeFound(String filter) throws Exception {
        restDqRuleTypesMockMvc.perform(get("/api/dq-rule-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDqRuleTypesMockMvc.perform(get("/api/dq-rule-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDqRuleTypes() throws Exception {
        // Get the dqRuleTypes
        restDqRuleTypesMockMvc.perform(get("/api/dq-rule-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDqRuleTypes() throws Exception {
        // Initialize the database
        dqRuleTypesRepository.saveAndFlush(dqRuleTypes);

        int databaseSizeBeforeUpdate = dqRuleTypesRepository.findAll().size();

        // Update the dqRuleTypes
        DqRuleTypes updatedDqRuleTypes = dqRuleTypesRepository.findById(dqRuleTypes.getId()).get();
        // Disconnect from session so that the updates on updatedDqRuleTypes are not directly saved in db
        em.detach(updatedDqRuleTypes);
        updatedDqRuleTypes
            .typeName(UPDATED_TYPE_NAME)
            .typeDescription(UPDATED_TYPE_DESCRIPTION);
        DqRuleTypesDTO dqRuleTypesDTO = dqRuleTypesMapper.toDto(updatedDqRuleTypes);

        restDqRuleTypesMockMvc.perform(put("/api/dq-rule-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqRuleTypesDTO)))
            .andExpect(status().isOk());

        // Validate the DqRuleTypes in the database
        List<DqRuleTypes> dqRuleTypesList = dqRuleTypesRepository.findAll();
        assertThat(dqRuleTypesList).hasSize(databaseSizeBeforeUpdate);
        DqRuleTypes testDqRuleTypes = dqRuleTypesList.get(dqRuleTypesList.size() - 1);
        assertThat(testDqRuleTypes.getTypeName()).isEqualTo(UPDATED_TYPE_NAME);
        assertThat(testDqRuleTypes.getTypeDescription()).isEqualTo(UPDATED_TYPE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingDqRuleTypes() throws Exception {
        int databaseSizeBeforeUpdate = dqRuleTypesRepository.findAll().size();

        // Create the DqRuleTypes
        DqRuleTypesDTO dqRuleTypesDTO = dqRuleTypesMapper.toDto(dqRuleTypes);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDqRuleTypesMockMvc.perform(put("/api/dq-rule-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqRuleTypesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DqRuleTypes in the database
        List<DqRuleTypes> dqRuleTypesList = dqRuleTypesRepository.findAll();
        assertThat(dqRuleTypesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDqRuleTypes() throws Exception {
        // Initialize the database
        dqRuleTypesRepository.saveAndFlush(dqRuleTypes);

        int databaseSizeBeforeDelete = dqRuleTypesRepository.findAll().size();

        // Delete the dqRuleTypes
        restDqRuleTypesMockMvc.perform(delete("/api/dq-rule-types/{id}", dqRuleTypes.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DqRuleTypes> dqRuleTypesList = dqRuleTypesRepository.findAll();
        assertThat(dqRuleTypesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
