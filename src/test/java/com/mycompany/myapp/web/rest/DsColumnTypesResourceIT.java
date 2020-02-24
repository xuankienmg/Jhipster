package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.DsColumnTypes;
import com.mycompany.myapp.repository.DsColumnTypesRepository;
import com.mycompany.myapp.service.DsColumnTypesService;
import com.mycompany.myapp.service.dto.DsColumnTypesDTO;
import com.mycompany.myapp.service.mapper.DsColumnTypesMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.DsColumnTypesCriteria;
import com.mycompany.myapp.service.DsColumnTypesQueryService;

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
 * Integration tests for the {@link DsColumnTypesResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class DsColumnTypesResourceIT {

    private static final String DEFAULT_COL_TYPE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COL_TYPE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COL_TYPE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_COL_TYPE_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private DsColumnTypesRepository dsColumnTypesRepository;

    @Autowired
    private DsColumnTypesMapper dsColumnTypesMapper;

    @Autowired
    private DsColumnTypesService dsColumnTypesService;

    @Autowired
    private DsColumnTypesQueryService dsColumnTypesQueryService;

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

    private MockMvc restDsColumnTypesMockMvc;

    private DsColumnTypes dsColumnTypes;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DsColumnTypesResource dsColumnTypesResource = new DsColumnTypesResource(dsColumnTypesService, dsColumnTypesQueryService);
        this.restDsColumnTypesMockMvc = MockMvcBuilders.standaloneSetup(dsColumnTypesResource)
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
    public static DsColumnTypes createEntity(EntityManager em) {
        DsColumnTypes dsColumnTypes = new DsColumnTypes()
            .colTypeName(DEFAULT_COL_TYPE_NAME)
            .colTypeDescription(DEFAULT_COL_TYPE_DESCRIPTION);
        return dsColumnTypes;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DsColumnTypes createUpdatedEntity(EntityManager em) {
        DsColumnTypes dsColumnTypes = new DsColumnTypes()
            .colTypeName(UPDATED_COL_TYPE_NAME)
            .colTypeDescription(UPDATED_COL_TYPE_DESCRIPTION);
        return dsColumnTypes;
    }

    @BeforeEach
    public void initTest() {
        dsColumnTypes = createEntity(em);
    }

    @Test
    @Transactional
    public void createDsColumnTypes() throws Exception {
        int databaseSizeBeforeCreate = dsColumnTypesRepository.findAll().size();

        // Create the DsColumnTypes
        DsColumnTypesDTO dsColumnTypesDTO = dsColumnTypesMapper.toDto(dsColumnTypes);
        restDsColumnTypesMockMvc.perform(post("/api/ds-column-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dsColumnTypesDTO)))
            .andExpect(status().isCreated());

        // Validate the DsColumnTypes in the database
        List<DsColumnTypes> dsColumnTypesList = dsColumnTypesRepository.findAll();
        assertThat(dsColumnTypesList).hasSize(databaseSizeBeforeCreate + 1);
        DsColumnTypes testDsColumnTypes = dsColumnTypesList.get(dsColumnTypesList.size() - 1);
        assertThat(testDsColumnTypes.getColTypeName()).isEqualTo(DEFAULT_COL_TYPE_NAME);
        assertThat(testDsColumnTypes.getColTypeDescription()).isEqualTo(DEFAULT_COL_TYPE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createDsColumnTypesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dsColumnTypesRepository.findAll().size();

        // Create the DsColumnTypes with an existing ID
        dsColumnTypes.setId(1L);
        DsColumnTypesDTO dsColumnTypesDTO = dsColumnTypesMapper.toDto(dsColumnTypes);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDsColumnTypesMockMvc.perform(post("/api/ds-column-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dsColumnTypesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DsColumnTypes in the database
        List<DsColumnTypes> dsColumnTypesList = dsColumnTypesRepository.findAll();
        assertThat(dsColumnTypesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDsColumnTypes() throws Exception {
        // Initialize the database
        dsColumnTypesRepository.saveAndFlush(dsColumnTypes);

        // Get all the dsColumnTypesList
        restDsColumnTypesMockMvc.perform(get("/api/ds-column-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dsColumnTypes.getId().intValue())))
            .andExpect(jsonPath("$.[*].colTypeName").value(hasItem(DEFAULT_COL_TYPE_NAME)))
            .andExpect(jsonPath("$.[*].colTypeDescription").value(hasItem(DEFAULT_COL_TYPE_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getDsColumnTypes() throws Exception {
        // Initialize the database
        dsColumnTypesRepository.saveAndFlush(dsColumnTypes);

        // Get the dsColumnTypes
        restDsColumnTypesMockMvc.perform(get("/api/ds-column-types/{id}", dsColumnTypes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dsColumnTypes.getId().intValue()))
            .andExpect(jsonPath("$.colTypeName").value(DEFAULT_COL_TYPE_NAME))
            .andExpect(jsonPath("$.colTypeDescription").value(DEFAULT_COL_TYPE_DESCRIPTION));
    }


    @Test
    @Transactional
    public void getDsColumnTypesByIdFiltering() throws Exception {
        // Initialize the database
        dsColumnTypesRepository.saveAndFlush(dsColumnTypes);

        Long id = dsColumnTypes.getId();

        defaultDsColumnTypesShouldBeFound("id.equals=" + id);
        defaultDsColumnTypesShouldNotBeFound("id.notEquals=" + id);

        defaultDsColumnTypesShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDsColumnTypesShouldNotBeFound("id.greaterThan=" + id);

        defaultDsColumnTypesShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDsColumnTypesShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDsColumnTypesByColTypeNameIsEqualToSomething() throws Exception {
        // Initialize the database
        dsColumnTypesRepository.saveAndFlush(dsColumnTypes);

        // Get all the dsColumnTypesList where colTypeName equals to DEFAULT_COL_TYPE_NAME
        defaultDsColumnTypesShouldBeFound("colTypeName.equals=" + DEFAULT_COL_TYPE_NAME);

        // Get all the dsColumnTypesList where colTypeName equals to UPDATED_COL_TYPE_NAME
        defaultDsColumnTypesShouldNotBeFound("colTypeName.equals=" + UPDATED_COL_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllDsColumnTypesByColTypeNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dsColumnTypesRepository.saveAndFlush(dsColumnTypes);

        // Get all the dsColumnTypesList where colTypeName not equals to DEFAULT_COL_TYPE_NAME
        defaultDsColumnTypesShouldNotBeFound("colTypeName.notEquals=" + DEFAULT_COL_TYPE_NAME);

        // Get all the dsColumnTypesList where colTypeName not equals to UPDATED_COL_TYPE_NAME
        defaultDsColumnTypesShouldBeFound("colTypeName.notEquals=" + UPDATED_COL_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllDsColumnTypesByColTypeNameIsInShouldWork() throws Exception {
        // Initialize the database
        dsColumnTypesRepository.saveAndFlush(dsColumnTypes);

        // Get all the dsColumnTypesList where colTypeName in DEFAULT_COL_TYPE_NAME or UPDATED_COL_TYPE_NAME
        defaultDsColumnTypesShouldBeFound("colTypeName.in=" + DEFAULT_COL_TYPE_NAME + "," + UPDATED_COL_TYPE_NAME);

        // Get all the dsColumnTypesList where colTypeName equals to UPDATED_COL_TYPE_NAME
        defaultDsColumnTypesShouldNotBeFound("colTypeName.in=" + UPDATED_COL_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllDsColumnTypesByColTypeNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        dsColumnTypesRepository.saveAndFlush(dsColumnTypes);

        // Get all the dsColumnTypesList where colTypeName is not null
        defaultDsColumnTypesShouldBeFound("colTypeName.specified=true");

        // Get all the dsColumnTypesList where colTypeName is null
        defaultDsColumnTypesShouldNotBeFound("colTypeName.specified=false");
    }
                @Test
    @Transactional
    public void getAllDsColumnTypesByColTypeNameContainsSomething() throws Exception {
        // Initialize the database
        dsColumnTypesRepository.saveAndFlush(dsColumnTypes);

        // Get all the dsColumnTypesList where colTypeName contains DEFAULT_COL_TYPE_NAME
        defaultDsColumnTypesShouldBeFound("colTypeName.contains=" + DEFAULT_COL_TYPE_NAME);

        // Get all the dsColumnTypesList where colTypeName contains UPDATED_COL_TYPE_NAME
        defaultDsColumnTypesShouldNotBeFound("colTypeName.contains=" + UPDATED_COL_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllDsColumnTypesByColTypeNameNotContainsSomething() throws Exception {
        // Initialize the database
        dsColumnTypesRepository.saveAndFlush(dsColumnTypes);

        // Get all the dsColumnTypesList where colTypeName does not contain DEFAULT_COL_TYPE_NAME
        defaultDsColumnTypesShouldNotBeFound("colTypeName.doesNotContain=" + DEFAULT_COL_TYPE_NAME);

        // Get all the dsColumnTypesList where colTypeName does not contain UPDATED_COL_TYPE_NAME
        defaultDsColumnTypesShouldBeFound("colTypeName.doesNotContain=" + UPDATED_COL_TYPE_NAME);
    }


    @Test
    @Transactional
    public void getAllDsColumnTypesByColTypeDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        dsColumnTypesRepository.saveAndFlush(dsColumnTypes);

        // Get all the dsColumnTypesList where colTypeDescription equals to DEFAULT_COL_TYPE_DESCRIPTION
        defaultDsColumnTypesShouldBeFound("colTypeDescription.equals=" + DEFAULT_COL_TYPE_DESCRIPTION);

        // Get all the dsColumnTypesList where colTypeDescription equals to UPDATED_COL_TYPE_DESCRIPTION
        defaultDsColumnTypesShouldNotBeFound("colTypeDescription.equals=" + UPDATED_COL_TYPE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDsColumnTypesByColTypeDescriptionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dsColumnTypesRepository.saveAndFlush(dsColumnTypes);

        // Get all the dsColumnTypesList where colTypeDescription not equals to DEFAULT_COL_TYPE_DESCRIPTION
        defaultDsColumnTypesShouldNotBeFound("colTypeDescription.notEquals=" + DEFAULT_COL_TYPE_DESCRIPTION);

        // Get all the dsColumnTypesList where colTypeDescription not equals to UPDATED_COL_TYPE_DESCRIPTION
        defaultDsColumnTypesShouldBeFound("colTypeDescription.notEquals=" + UPDATED_COL_TYPE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDsColumnTypesByColTypeDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        dsColumnTypesRepository.saveAndFlush(dsColumnTypes);

        // Get all the dsColumnTypesList where colTypeDescription in DEFAULT_COL_TYPE_DESCRIPTION or UPDATED_COL_TYPE_DESCRIPTION
        defaultDsColumnTypesShouldBeFound("colTypeDescription.in=" + DEFAULT_COL_TYPE_DESCRIPTION + "," + UPDATED_COL_TYPE_DESCRIPTION);

        // Get all the dsColumnTypesList where colTypeDescription equals to UPDATED_COL_TYPE_DESCRIPTION
        defaultDsColumnTypesShouldNotBeFound("colTypeDescription.in=" + UPDATED_COL_TYPE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDsColumnTypesByColTypeDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        dsColumnTypesRepository.saveAndFlush(dsColumnTypes);

        // Get all the dsColumnTypesList where colTypeDescription is not null
        defaultDsColumnTypesShouldBeFound("colTypeDescription.specified=true");

        // Get all the dsColumnTypesList where colTypeDescription is null
        defaultDsColumnTypesShouldNotBeFound("colTypeDescription.specified=false");
    }
                @Test
    @Transactional
    public void getAllDsColumnTypesByColTypeDescriptionContainsSomething() throws Exception {
        // Initialize the database
        dsColumnTypesRepository.saveAndFlush(dsColumnTypes);

        // Get all the dsColumnTypesList where colTypeDescription contains DEFAULT_COL_TYPE_DESCRIPTION
        defaultDsColumnTypesShouldBeFound("colTypeDescription.contains=" + DEFAULT_COL_TYPE_DESCRIPTION);

        // Get all the dsColumnTypesList where colTypeDescription contains UPDATED_COL_TYPE_DESCRIPTION
        defaultDsColumnTypesShouldNotBeFound("colTypeDescription.contains=" + UPDATED_COL_TYPE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDsColumnTypesByColTypeDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        dsColumnTypesRepository.saveAndFlush(dsColumnTypes);

        // Get all the dsColumnTypesList where colTypeDescription does not contain DEFAULT_COL_TYPE_DESCRIPTION
        defaultDsColumnTypesShouldNotBeFound("colTypeDescription.doesNotContain=" + DEFAULT_COL_TYPE_DESCRIPTION);

        // Get all the dsColumnTypesList where colTypeDescription does not contain UPDATED_COL_TYPE_DESCRIPTION
        defaultDsColumnTypesShouldBeFound("colTypeDescription.doesNotContain=" + UPDATED_COL_TYPE_DESCRIPTION);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDsColumnTypesShouldBeFound(String filter) throws Exception {
        restDsColumnTypesMockMvc.perform(get("/api/ds-column-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dsColumnTypes.getId().intValue())))
            .andExpect(jsonPath("$.[*].colTypeName").value(hasItem(DEFAULT_COL_TYPE_NAME)))
            .andExpect(jsonPath("$.[*].colTypeDescription").value(hasItem(DEFAULT_COL_TYPE_DESCRIPTION)));

        // Check, that the count call also returns 1
        restDsColumnTypesMockMvc.perform(get("/api/ds-column-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDsColumnTypesShouldNotBeFound(String filter) throws Exception {
        restDsColumnTypesMockMvc.perform(get("/api/ds-column-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDsColumnTypesMockMvc.perform(get("/api/ds-column-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDsColumnTypes() throws Exception {
        // Get the dsColumnTypes
        restDsColumnTypesMockMvc.perform(get("/api/ds-column-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDsColumnTypes() throws Exception {
        // Initialize the database
        dsColumnTypesRepository.saveAndFlush(dsColumnTypes);

        int databaseSizeBeforeUpdate = dsColumnTypesRepository.findAll().size();

        // Update the dsColumnTypes
        DsColumnTypes updatedDsColumnTypes = dsColumnTypesRepository.findById(dsColumnTypes.getId()).get();
        // Disconnect from session so that the updates on updatedDsColumnTypes are not directly saved in db
        em.detach(updatedDsColumnTypes);
        updatedDsColumnTypes
            .colTypeName(UPDATED_COL_TYPE_NAME)
            .colTypeDescription(UPDATED_COL_TYPE_DESCRIPTION);
        DsColumnTypesDTO dsColumnTypesDTO = dsColumnTypesMapper.toDto(updatedDsColumnTypes);

        restDsColumnTypesMockMvc.perform(put("/api/ds-column-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dsColumnTypesDTO)))
            .andExpect(status().isOk());

        // Validate the DsColumnTypes in the database
        List<DsColumnTypes> dsColumnTypesList = dsColumnTypesRepository.findAll();
        assertThat(dsColumnTypesList).hasSize(databaseSizeBeforeUpdate);
        DsColumnTypes testDsColumnTypes = dsColumnTypesList.get(dsColumnTypesList.size() - 1);
        assertThat(testDsColumnTypes.getColTypeName()).isEqualTo(UPDATED_COL_TYPE_NAME);
        assertThat(testDsColumnTypes.getColTypeDescription()).isEqualTo(UPDATED_COL_TYPE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingDsColumnTypes() throws Exception {
        int databaseSizeBeforeUpdate = dsColumnTypesRepository.findAll().size();

        // Create the DsColumnTypes
        DsColumnTypesDTO dsColumnTypesDTO = dsColumnTypesMapper.toDto(dsColumnTypes);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDsColumnTypesMockMvc.perform(put("/api/ds-column-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dsColumnTypesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DsColumnTypes in the database
        List<DsColumnTypes> dsColumnTypesList = dsColumnTypesRepository.findAll();
        assertThat(dsColumnTypesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDsColumnTypes() throws Exception {
        // Initialize the database
        dsColumnTypesRepository.saveAndFlush(dsColumnTypes);

        int databaseSizeBeforeDelete = dsColumnTypesRepository.findAll().size();

        // Delete the dsColumnTypes
        restDsColumnTypesMockMvc.perform(delete("/api/ds-column-types/{id}", dsColumnTypes.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DsColumnTypes> dsColumnTypesList = dsColumnTypesRepository.findAll();
        assertThat(dsColumnTypesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
