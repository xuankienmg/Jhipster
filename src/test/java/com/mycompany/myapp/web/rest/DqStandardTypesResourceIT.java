package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.DqStandardTypes;
import com.mycompany.myapp.repository.DqStandardTypesRepository;
import com.mycompany.myapp.service.DqStandardTypesService;
import com.mycompany.myapp.service.dto.DqStandardTypesDTO;
import com.mycompany.myapp.service.mapper.DqStandardTypesMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.DqStandardTypesCriteria;
import com.mycompany.myapp.service.DqStandardTypesQueryService;

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
 * Integration tests for the {@link DqStandardTypesResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class DqStandardTypesResourceIT {

    private static final String DEFAULT_STD_TYPE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STD_TYPE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STD_TYPE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_STD_TYPE_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private DqStandardTypesRepository dqStandardTypesRepository;

    @Autowired
    private DqStandardTypesMapper dqStandardTypesMapper;

    @Autowired
    private DqStandardTypesService dqStandardTypesService;

    @Autowired
    private DqStandardTypesQueryService dqStandardTypesQueryService;

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

    private MockMvc restDqStandardTypesMockMvc;

    private DqStandardTypes dqStandardTypes;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DqStandardTypesResource dqStandardTypesResource = new DqStandardTypesResource(dqStandardTypesService, dqStandardTypesQueryService);
        this.restDqStandardTypesMockMvc = MockMvcBuilders.standaloneSetup(dqStandardTypesResource)
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
    public static DqStandardTypes createEntity(EntityManager em) {
        DqStandardTypes dqStandardTypes = new DqStandardTypes()
            .stdTypeName(DEFAULT_STD_TYPE_NAME)
            .stdTypeDescription(DEFAULT_STD_TYPE_DESCRIPTION);
        return dqStandardTypes;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DqStandardTypes createUpdatedEntity(EntityManager em) {
        DqStandardTypes dqStandardTypes = new DqStandardTypes()
            .stdTypeName(UPDATED_STD_TYPE_NAME)
            .stdTypeDescription(UPDATED_STD_TYPE_DESCRIPTION);
        return dqStandardTypes;
    }

    @BeforeEach
    public void initTest() {
        dqStandardTypes = createEntity(em);
    }

    @Test
    @Transactional
    public void createDqStandardTypes() throws Exception {
        int databaseSizeBeforeCreate = dqStandardTypesRepository.findAll().size();

        // Create the DqStandardTypes
        DqStandardTypesDTO dqStandardTypesDTO = dqStandardTypesMapper.toDto(dqStandardTypes);
        restDqStandardTypesMockMvc.perform(post("/api/dq-standard-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqStandardTypesDTO)))
            .andExpect(status().isCreated());

        // Validate the DqStandardTypes in the database
        List<DqStandardTypes> dqStandardTypesList = dqStandardTypesRepository.findAll();
        assertThat(dqStandardTypesList).hasSize(databaseSizeBeforeCreate + 1);
        DqStandardTypes testDqStandardTypes = dqStandardTypesList.get(dqStandardTypesList.size() - 1);
        assertThat(testDqStandardTypes.getStdTypeName()).isEqualTo(DEFAULT_STD_TYPE_NAME);
        assertThat(testDqStandardTypes.getStdTypeDescription()).isEqualTo(DEFAULT_STD_TYPE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createDqStandardTypesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dqStandardTypesRepository.findAll().size();

        // Create the DqStandardTypes with an existing ID
        dqStandardTypes.setId(1L);
        DqStandardTypesDTO dqStandardTypesDTO = dqStandardTypesMapper.toDto(dqStandardTypes);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDqStandardTypesMockMvc.perform(post("/api/dq-standard-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqStandardTypesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DqStandardTypes in the database
        List<DqStandardTypes> dqStandardTypesList = dqStandardTypesRepository.findAll();
        assertThat(dqStandardTypesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDqStandardTypes() throws Exception {
        // Initialize the database
        dqStandardTypesRepository.saveAndFlush(dqStandardTypes);

        // Get all the dqStandardTypesList
        restDqStandardTypesMockMvc.perform(get("/api/dq-standard-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dqStandardTypes.getId().intValue())))
            .andExpect(jsonPath("$.[*].stdTypeName").value(hasItem(DEFAULT_STD_TYPE_NAME)))
            .andExpect(jsonPath("$.[*].stdTypeDescription").value(hasItem(DEFAULT_STD_TYPE_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getDqStandardTypes() throws Exception {
        // Initialize the database
        dqStandardTypesRepository.saveAndFlush(dqStandardTypes);

        // Get the dqStandardTypes
        restDqStandardTypesMockMvc.perform(get("/api/dq-standard-types/{id}", dqStandardTypes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dqStandardTypes.getId().intValue()))
            .andExpect(jsonPath("$.stdTypeName").value(DEFAULT_STD_TYPE_NAME))
            .andExpect(jsonPath("$.stdTypeDescription").value(DEFAULT_STD_TYPE_DESCRIPTION));
    }


    @Test
    @Transactional
    public void getDqStandardTypesByIdFiltering() throws Exception {
        // Initialize the database
        dqStandardTypesRepository.saveAndFlush(dqStandardTypes);

        Long id = dqStandardTypes.getId();

        defaultDqStandardTypesShouldBeFound("id.equals=" + id);
        defaultDqStandardTypesShouldNotBeFound("id.notEquals=" + id);

        defaultDqStandardTypesShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDqStandardTypesShouldNotBeFound("id.greaterThan=" + id);

        defaultDqStandardTypesShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDqStandardTypesShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDqStandardTypesByStdTypeNameIsEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardTypesRepository.saveAndFlush(dqStandardTypes);

        // Get all the dqStandardTypesList where stdTypeName equals to DEFAULT_STD_TYPE_NAME
        defaultDqStandardTypesShouldBeFound("stdTypeName.equals=" + DEFAULT_STD_TYPE_NAME);

        // Get all the dqStandardTypesList where stdTypeName equals to UPDATED_STD_TYPE_NAME
        defaultDqStandardTypesShouldNotBeFound("stdTypeName.equals=" + UPDATED_STD_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqStandardTypesByStdTypeNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardTypesRepository.saveAndFlush(dqStandardTypes);

        // Get all the dqStandardTypesList where stdTypeName not equals to DEFAULT_STD_TYPE_NAME
        defaultDqStandardTypesShouldNotBeFound("stdTypeName.notEquals=" + DEFAULT_STD_TYPE_NAME);

        // Get all the dqStandardTypesList where stdTypeName not equals to UPDATED_STD_TYPE_NAME
        defaultDqStandardTypesShouldBeFound("stdTypeName.notEquals=" + UPDATED_STD_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqStandardTypesByStdTypeNameIsInShouldWork() throws Exception {
        // Initialize the database
        dqStandardTypesRepository.saveAndFlush(dqStandardTypes);

        // Get all the dqStandardTypesList where stdTypeName in DEFAULT_STD_TYPE_NAME or UPDATED_STD_TYPE_NAME
        defaultDqStandardTypesShouldBeFound("stdTypeName.in=" + DEFAULT_STD_TYPE_NAME + "," + UPDATED_STD_TYPE_NAME);

        // Get all the dqStandardTypesList where stdTypeName equals to UPDATED_STD_TYPE_NAME
        defaultDqStandardTypesShouldNotBeFound("stdTypeName.in=" + UPDATED_STD_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqStandardTypesByStdTypeNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        dqStandardTypesRepository.saveAndFlush(dqStandardTypes);

        // Get all the dqStandardTypesList where stdTypeName is not null
        defaultDqStandardTypesShouldBeFound("stdTypeName.specified=true");

        // Get all the dqStandardTypesList where stdTypeName is null
        defaultDqStandardTypesShouldNotBeFound("stdTypeName.specified=false");
    }
                @Test
    @Transactional
    public void getAllDqStandardTypesByStdTypeNameContainsSomething() throws Exception {
        // Initialize the database
        dqStandardTypesRepository.saveAndFlush(dqStandardTypes);

        // Get all the dqStandardTypesList where stdTypeName contains DEFAULT_STD_TYPE_NAME
        defaultDqStandardTypesShouldBeFound("stdTypeName.contains=" + DEFAULT_STD_TYPE_NAME);

        // Get all the dqStandardTypesList where stdTypeName contains UPDATED_STD_TYPE_NAME
        defaultDqStandardTypesShouldNotBeFound("stdTypeName.contains=" + UPDATED_STD_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqStandardTypesByStdTypeNameNotContainsSomething() throws Exception {
        // Initialize the database
        dqStandardTypesRepository.saveAndFlush(dqStandardTypes);

        // Get all the dqStandardTypesList where stdTypeName does not contain DEFAULT_STD_TYPE_NAME
        defaultDqStandardTypesShouldNotBeFound("stdTypeName.doesNotContain=" + DEFAULT_STD_TYPE_NAME);

        // Get all the dqStandardTypesList where stdTypeName does not contain UPDATED_STD_TYPE_NAME
        defaultDqStandardTypesShouldBeFound("stdTypeName.doesNotContain=" + UPDATED_STD_TYPE_NAME);
    }


    @Test
    @Transactional
    public void getAllDqStandardTypesByStdTypeDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardTypesRepository.saveAndFlush(dqStandardTypes);

        // Get all the dqStandardTypesList where stdTypeDescription equals to DEFAULT_STD_TYPE_DESCRIPTION
        defaultDqStandardTypesShouldBeFound("stdTypeDescription.equals=" + DEFAULT_STD_TYPE_DESCRIPTION);

        // Get all the dqStandardTypesList where stdTypeDescription equals to UPDATED_STD_TYPE_DESCRIPTION
        defaultDqStandardTypesShouldNotBeFound("stdTypeDescription.equals=" + UPDATED_STD_TYPE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDqStandardTypesByStdTypeDescriptionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardTypesRepository.saveAndFlush(dqStandardTypes);

        // Get all the dqStandardTypesList where stdTypeDescription not equals to DEFAULT_STD_TYPE_DESCRIPTION
        defaultDqStandardTypesShouldNotBeFound("stdTypeDescription.notEquals=" + DEFAULT_STD_TYPE_DESCRIPTION);

        // Get all the dqStandardTypesList where stdTypeDescription not equals to UPDATED_STD_TYPE_DESCRIPTION
        defaultDqStandardTypesShouldBeFound("stdTypeDescription.notEquals=" + UPDATED_STD_TYPE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDqStandardTypesByStdTypeDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        dqStandardTypesRepository.saveAndFlush(dqStandardTypes);

        // Get all the dqStandardTypesList where stdTypeDescription in DEFAULT_STD_TYPE_DESCRIPTION or UPDATED_STD_TYPE_DESCRIPTION
        defaultDqStandardTypesShouldBeFound("stdTypeDescription.in=" + DEFAULT_STD_TYPE_DESCRIPTION + "," + UPDATED_STD_TYPE_DESCRIPTION);

        // Get all the dqStandardTypesList where stdTypeDescription equals to UPDATED_STD_TYPE_DESCRIPTION
        defaultDqStandardTypesShouldNotBeFound("stdTypeDescription.in=" + UPDATED_STD_TYPE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDqStandardTypesByStdTypeDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        dqStandardTypesRepository.saveAndFlush(dqStandardTypes);

        // Get all the dqStandardTypesList where stdTypeDescription is not null
        defaultDqStandardTypesShouldBeFound("stdTypeDescription.specified=true");

        // Get all the dqStandardTypesList where stdTypeDescription is null
        defaultDqStandardTypesShouldNotBeFound("stdTypeDescription.specified=false");
    }
                @Test
    @Transactional
    public void getAllDqStandardTypesByStdTypeDescriptionContainsSomething() throws Exception {
        // Initialize the database
        dqStandardTypesRepository.saveAndFlush(dqStandardTypes);

        // Get all the dqStandardTypesList where stdTypeDescription contains DEFAULT_STD_TYPE_DESCRIPTION
        defaultDqStandardTypesShouldBeFound("stdTypeDescription.contains=" + DEFAULT_STD_TYPE_DESCRIPTION);

        // Get all the dqStandardTypesList where stdTypeDescription contains UPDATED_STD_TYPE_DESCRIPTION
        defaultDqStandardTypesShouldNotBeFound("stdTypeDescription.contains=" + UPDATED_STD_TYPE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDqStandardTypesByStdTypeDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        dqStandardTypesRepository.saveAndFlush(dqStandardTypes);

        // Get all the dqStandardTypesList where stdTypeDescription does not contain DEFAULT_STD_TYPE_DESCRIPTION
        defaultDqStandardTypesShouldNotBeFound("stdTypeDescription.doesNotContain=" + DEFAULT_STD_TYPE_DESCRIPTION);

        // Get all the dqStandardTypesList where stdTypeDescription does not contain UPDATED_STD_TYPE_DESCRIPTION
        defaultDqStandardTypesShouldBeFound("stdTypeDescription.doesNotContain=" + UPDATED_STD_TYPE_DESCRIPTION);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDqStandardTypesShouldBeFound(String filter) throws Exception {
        restDqStandardTypesMockMvc.perform(get("/api/dq-standard-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dqStandardTypes.getId().intValue())))
            .andExpect(jsonPath("$.[*].stdTypeName").value(hasItem(DEFAULT_STD_TYPE_NAME)))
            .andExpect(jsonPath("$.[*].stdTypeDescription").value(hasItem(DEFAULT_STD_TYPE_DESCRIPTION)));

        // Check, that the count call also returns 1
        restDqStandardTypesMockMvc.perform(get("/api/dq-standard-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDqStandardTypesShouldNotBeFound(String filter) throws Exception {
        restDqStandardTypesMockMvc.perform(get("/api/dq-standard-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDqStandardTypesMockMvc.perform(get("/api/dq-standard-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDqStandardTypes() throws Exception {
        // Get the dqStandardTypes
        restDqStandardTypesMockMvc.perform(get("/api/dq-standard-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDqStandardTypes() throws Exception {
        // Initialize the database
        dqStandardTypesRepository.saveAndFlush(dqStandardTypes);

        int databaseSizeBeforeUpdate = dqStandardTypesRepository.findAll().size();

        // Update the dqStandardTypes
        DqStandardTypes updatedDqStandardTypes = dqStandardTypesRepository.findById(dqStandardTypes.getId()).get();
        // Disconnect from session so that the updates on updatedDqStandardTypes are not directly saved in db
        em.detach(updatedDqStandardTypes);
        updatedDqStandardTypes
            .stdTypeName(UPDATED_STD_TYPE_NAME)
            .stdTypeDescription(UPDATED_STD_TYPE_DESCRIPTION);
        DqStandardTypesDTO dqStandardTypesDTO = dqStandardTypesMapper.toDto(updatedDqStandardTypes);

        restDqStandardTypesMockMvc.perform(put("/api/dq-standard-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqStandardTypesDTO)))
            .andExpect(status().isOk());

        // Validate the DqStandardTypes in the database
        List<DqStandardTypes> dqStandardTypesList = dqStandardTypesRepository.findAll();
        assertThat(dqStandardTypesList).hasSize(databaseSizeBeforeUpdate);
        DqStandardTypes testDqStandardTypes = dqStandardTypesList.get(dqStandardTypesList.size() - 1);
        assertThat(testDqStandardTypes.getStdTypeName()).isEqualTo(UPDATED_STD_TYPE_NAME);
        assertThat(testDqStandardTypes.getStdTypeDescription()).isEqualTo(UPDATED_STD_TYPE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingDqStandardTypes() throws Exception {
        int databaseSizeBeforeUpdate = dqStandardTypesRepository.findAll().size();

        // Create the DqStandardTypes
        DqStandardTypesDTO dqStandardTypesDTO = dqStandardTypesMapper.toDto(dqStandardTypes);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDqStandardTypesMockMvc.perform(put("/api/dq-standard-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqStandardTypesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DqStandardTypes in the database
        List<DqStandardTypes> dqStandardTypesList = dqStandardTypesRepository.findAll();
        assertThat(dqStandardTypesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDqStandardTypes() throws Exception {
        // Initialize the database
        dqStandardTypesRepository.saveAndFlush(dqStandardTypes);

        int databaseSizeBeforeDelete = dqStandardTypesRepository.findAll().size();

        // Delete the dqStandardTypes
        restDqStandardTypesMockMvc.perform(delete("/api/dq-standard-types/{id}", dqStandardTypes.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DqStandardTypes> dqStandardTypesList = dqStandardTypesRepository.findAll();
        assertThat(dqStandardTypesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
