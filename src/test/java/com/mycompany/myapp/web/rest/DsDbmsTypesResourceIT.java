package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.DsDbmsTypes;
import com.mycompany.myapp.repository.DsDbmsTypesRepository;
import com.mycompany.myapp.service.DsDbmsTypesService;
import com.mycompany.myapp.service.dto.DsDbmsTypesDTO;
import com.mycompany.myapp.service.mapper.DsDbmsTypesMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.DsDbmsTypesCriteria;
import com.mycompany.myapp.service.DsDbmsTypesQueryService;

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
 * Integration tests for the {@link DsDbmsTypesResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class DsDbmsTypesResourceIT {

    private static final String DEFAULT_DBMS_TYPE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DBMS_TYPE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DBSM_TYPE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DBSM_TYPE_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private DsDbmsTypesRepository dsDbmsTypesRepository;

    @Autowired
    private DsDbmsTypesMapper dsDbmsTypesMapper;

    @Autowired
    private DsDbmsTypesService dsDbmsTypesService;

    @Autowired
    private DsDbmsTypesQueryService dsDbmsTypesQueryService;

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

    private MockMvc restDsDbmsTypesMockMvc;

    private DsDbmsTypes dsDbmsTypes;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DsDbmsTypesResource dsDbmsTypesResource = new DsDbmsTypesResource(dsDbmsTypesService, dsDbmsTypesQueryService);
        this.restDsDbmsTypesMockMvc = MockMvcBuilders.standaloneSetup(dsDbmsTypesResource)
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
    public static DsDbmsTypes createEntity(EntityManager em) {
        DsDbmsTypes dsDbmsTypes = new DsDbmsTypes()
            .dbmsTypeName(DEFAULT_DBMS_TYPE_NAME)
            .dbsmTypeDescription(DEFAULT_DBSM_TYPE_DESCRIPTION);
        return dsDbmsTypes;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DsDbmsTypes createUpdatedEntity(EntityManager em) {
        DsDbmsTypes dsDbmsTypes = new DsDbmsTypes()
            .dbmsTypeName(UPDATED_DBMS_TYPE_NAME)
            .dbsmTypeDescription(UPDATED_DBSM_TYPE_DESCRIPTION);
        return dsDbmsTypes;
    }

    @BeforeEach
    public void initTest() {
        dsDbmsTypes = createEntity(em);
    }

    @Test
    @Transactional
    public void createDsDbmsTypes() throws Exception {
        int databaseSizeBeforeCreate = dsDbmsTypesRepository.findAll().size();

        // Create the DsDbmsTypes
        DsDbmsTypesDTO dsDbmsTypesDTO = dsDbmsTypesMapper.toDto(dsDbmsTypes);
        restDsDbmsTypesMockMvc.perform(post("/api/ds-dbms-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dsDbmsTypesDTO)))
            .andExpect(status().isCreated());

        // Validate the DsDbmsTypes in the database
        List<DsDbmsTypes> dsDbmsTypesList = dsDbmsTypesRepository.findAll();
        assertThat(dsDbmsTypesList).hasSize(databaseSizeBeforeCreate + 1);
        DsDbmsTypes testDsDbmsTypes = dsDbmsTypesList.get(dsDbmsTypesList.size() - 1);
        assertThat(testDsDbmsTypes.getDbmsTypeName()).isEqualTo(DEFAULT_DBMS_TYPE_NAME);
        assertThat(testDsDbmsTypes.getDbsmTypeDescription()).isEqualTo(DEFAULT_DBSM_TYPE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createDsDbmsTypesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dsDbmsTypesRepository.findAll().size();

        // Create the DsDbmsTypes with an existing ID
        dsDbmsTypes.setId(1L);
        DsDbmsTypesDTO dsDbmsTypesDTO = dsDbmsTypesMapper.toDto(dsDbmsTypes);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDsDbmsTypesMockMvc.perform(post("/api/ds-dbms-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dsDbmsTypesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DsDbmsTypes in the database
        List<DsDbmsTypes> dsDbmsTypesList = dsDbmsTypesRepository.findAll();
        assertThat(dsDbmsTypesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDsDbmsTypes() throws Exception {
        // Initialize the database
        dsDbmsTypesRepository.saveAndFlush(dsDbmsTypes);

        // Get all the dsDbmsTypesList
        restDsDbmsTypesMockMvc.perform(get("/api/ds-dbms-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dsDbmsTypes.getId().intValue())))
            .andExpect(jsonPath("$.[*].dbmsTypeName").value(hasItem(DEFAULT_DBMS_TYPE_NAME)))
            .andExpect(jsonPath("$.[*].dbsmTypeDescription").value(hasItem(DEFAULT_DBSM_TYPE_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getDsDbmsTypes() throws Exception {
        // Initialize the database
        dsDbmsTypesRepository.saveAndFlush(dsDbmsTypes);

        // Get the dsDbmsTypes
        restDsDbmsTypesMockMvc.perform(get("/api/ds-dbms-types/{id}", dsDbmsTypes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dsDbmsTypes.getId().intValue()))
            .andExpect(jsonPath("$.dbmsTypeName").value(DEFAULT_DBMS_TYPE_NAME))
            .andExpect(jsonPath("$.dbsmTypeDescription").value(DEFAULT_DBSM_TYPE_DESCRIPTION));
    }


    @Test
    @Transactional
    public void getDsDbmsTypesByIdFiltering() throws Exception {
        // Initialize the database
        dsDbmsTypesRepository.saveAndFlush(dsDbmsTypes);

        Long id = dsDbmsTypes.getId();

        defaultDsDbmsTypesShouldBeFound("id.equals=" + id);
        defaultDsDbmsTypesShouldNotBeFound("id.notEquals=" + id);

        defaultDsDbmsTypesShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDsDbmsTypesShouldNotBeFound("id.greaterThan=" + id);

        defaultDsDbmsTypesShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDsDbmsTypesShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDsDbmsTypesByDbmsTypeNameIsEqualToSomething() throws Exception {
        // Initialize the database
        dsDbmsTypesRepository.saveAndFlush(dsDbmsTypes);

        // Get all the dsDbmsTypesList where dbmsTypeName equals to DEFAULT_DBMS_TYPE_NAME
        defaultDsDbmsTypesShouldBeFound("dbmsTypeName.equals=" + DEFAULT_DBMS_TYPE_NAME);

        // Get all the dsDbmsTypesList where dbmsTypeName equals to UPDATED_DBMS_TYPE_NAME
        defaultDsDbmsTypesShouldNotBeFound("dbmsTypeName.equals=" + UPDATED_DBMS_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllDsDbmsTypesByDbmsTypeNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dsDbmsTypesRepository.saveAndFlush(dsDbmsTypes);

        // Get all the dsDbmsTypesList where dbmsTypeName not equals to DEFAULT_DBMS_TYPE_NAME
        defaultDsDbmsTypesShouldNotBeFound("dbmsTypeName.notEquals=" + DEFAULT_DBMS_TYPE_NAME);

        // Get all the dsDbmsTypesList where dbmsTypeName not equals to UPDATED_DBMS_TYPE_NAME
        defaultDsDbmsTypesShouldBeFound("dbmsTypeName.notEquals=" + UPDATED_DBMS_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllDsDbmsTypesByDbmsTypeNameIsInShouldWork() throws Exception {
        // Initialize the database
        dsDbmsTypesRepository.saveAndFlush(dsDbmsTypes);

        // Get all the dsDbmsTypesList where dbmsTypeName in DEFAULT_DBMS_TYPE_NAME or UPDATED_DBMS_TYPE_NAME
        defaultDsDbmsTypesShouldBeFound("dbmsTypeName.in=" + DEFAULT_DBMS_TYPE_NAME + "," + UPDATED_DBMS_TYPE_NAME);

        // Get all the dsDbmsTypesList where dbmsTypeName equals to UPDATED_DBMS_TYPE_NAME
        defaultDsDbmsTypesShouldNotBeFound("dbmsTypeName.in=" + UPDATED_DBMS_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllDsDbmsTypesByDbmsTypeNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        dsDbmsTypesRepository.saveAndFlush(dsDbmsTypes);

        // Get all the dsDbmsTypesList where dbmsTypeName is not null
        defaultDsDbmsTypesShouldBeFound("dbmsTypeName.specified=true");

        // Get all the dsDbmsTypesList where dbmsTypeName is null
        defaultDsDbmsTypesShouldNotBeFound("dbmsTypeName.specified=false");
    }
                @Test
    @Transactional
    public void getAllDsDbmsTypesByDbmsTypeNameContainsSomething() throws Exception {
        // Initialize the database
        dsDbmsTypesRepository.saveAndFlush(dsDbmsTypes);

        // Get all the dsDbmsTypesList where dbmsTypeName contains DEFAULT_DBMS_TYPE_NAME
        defaultDsDbmsTypesShouldBeFound("dbmsTypeName.contains=" + DEFAULT_DBMS_TYPE_NAME);

        // Get all the dsDbmsTypesList where dbmsTypeName contains UPDATED_DBMS_TYPE_NAME
        defaultDsDbmsTypesShouldNotBeFound("dbmsTypeName.contains=" + UPDATED_DBMS_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllDsDbmsTypesByDbmsTypeNameNotContainsSomething() throws Exception {
        // Initialize the database
        dsDbmsTypesRepository.saveAndFlush(dsDbmsTypes);

        // Get all the dsDbmsTypesList where dbmsTypeName does not contain DEFAULT_DBMS_TYPE_NAME
        defaultDsDbmsTypesShouldNotBeFound("dbmsTypeName.doesNotContain=" + DEFAULT_DBMS_TYPE_NAME);

        // Get all the dsDbmsTypesList where dbmsTypeName does not contain UPDATED_DBMS_TYPE_NAME
        defaultDsDbmsTypesShouldBeFound("dbmsTypeName.doesNotContain=" + UPDATED_DBMS_TYPE_NAME);
    }


    @Test
    @Transactional
    public void getAllDsDbmsTypesByDbsmTypeDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        dsDbmsTypesRepository.saveAndFlush(dsDbmsTypes);

        // Get all the dsDbmsTypesList where dbsmTypeDescription equals to DEFAULT_DBSM_TYPE_DESCRIPTION
        defaultDsDbmsTypesShouldBeFound("dbsmTypeDescription.equals=" + DEFAULT_DBSM_TYPE_DESCRIPTION);

        // Get all the dsDbmsTypesList where dbsmTypeDescription equals to UPDATED_DBSM_TYPE_DESCRIPTION
        defaultDsDbmsTypesShouldNotBeFound("dbsmTypeDescription.equals=" + UPDATED_DBSM_TYPE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDsDbmsTypesByDbsmTypeDescriptionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dsDbmsTypesRepository.saveAndFlush(dsDbmsTypes);

        // Get all the dsDbmsTypesList where dbsmTypeDescription not equals to DEFAULT_DBSM_TYPE_DESCRIPTION
        defaultDsDbmsTypesShouldNotBeFound("dbsmTypeDescription.notEquals=" + DEFAULT_DBSM_TYPE_DESCRIPTION);

        // Get all the dsDbmsTypesList where dbsmTypeDescription not equals to UPDATED_DBSM_TYPE_DESCRIPTION
        defaultDsDbmsTypesShouldBeFound("dbsmTypeDescription.notEquals=" + UPDATED_DBSM_TYPE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDsDbmsTypesByDbsmTypeDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        dsDbmsTypesRepository.saveAndFlush(dsDbmsTypes);

        // Get all the dsDbmsTypesList where dbsmTypeDescription in DEFAULT_DBSM_TYPE_DESCRIPTION or UPDATED_DBSM_TYPE_DESCRIPTION
        defaultDsDbmsTypesShouldBeFound("dbsmTypeDescription.in=" + DEFAULT_DBSM_TYPE_DESCRIPTION + "," + UPDATED_DBSM_TYPE_DESCRIPTION);

        // Get all the dsDbmsTypesList where dbsmTypeDescription equals to UPDATED_DBSM_TYPE_DESCRIPTION
        defaultDsDbmsTypesShouldNotBeFound("dbsmTypeDescription.in=" + UPDATED_DBSM_TYPE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDsDbmsTypesByDbsmTypeDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        dsDbmsTypesRepository.saveAndFlush(dsDbmsTypes);

        // Get all the dsDbmsTypesList where dbsmTypeDescription is not null
        defaultDsDbmsTypesShouldBeFound("dbsmTypeDescription.specified=true");

        // Get all the dsDbmsTypesList where dbsmTypeDescription is null
        defaultDsDbmsTypesShouldNotBeFound("dbsmTypeDescription.specified=false");
    }
                @Test
    @Transactional
    public void getAllDsDbmsTypesByDbsmTypeDescriptionContainsSomething() throws Exception {
        // Initialize the database
        dsDbmsTypesRepository.saveAndFlush(dsDbmsTypes);

        // Get all the dsDbmsTypesList where dbsmTypeDescription contains DEFAULT_DBSM_TYPE_DESCRIPTION
        defaultDsDbmsTypesShouldBeFound("dbsmTypeDescription.contains=" + DEFAULT_DBSM_TYPE_DESCRIPTION);

        // Get all the dsDbmsTypesList where dbsmTypeDescription contains UPDATED_DBSM_TYPE_DESCRIPTION
        defaultDsDbmsTypesShouldNotBeFound("dbsmTypeDescription.contains=" + UPDATED_DBSM_TYPE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDsDbmsTypesByDbsmTypeDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        dsDbmsTypesRepository.saveAndFlush(dsDbmsTypes);

        // Get all the dsDbmsTypesList where dbsmTypeDescription does not contain DEFAULT_DBSM_TYPE_DESCRIPTION
        defaultDsDbmsTypesShouldNotBeFound("dbsmTypeDescription.doesNotContain=" + DEFAULT_DBSM_TYPE_DESCRIPTION);

        // Get all the dsDbmsTypesList where dbsmTypeDescription does not contain UPDATED_DBSM_TYPE_DESCRIPTION
        defaultDsDbmsTypesShouldBeFound("dbsmTypeDescription.doesNotContain=" + UPDATED_DBSM_TYPE_DESCRIPTION);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDsDbmsTypesShouldBeFound(String filter) throws Exception {
        restDsDbmsTypesMockMvc.perform(get("/api/ds-dbms-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dsDbmsTypes.getId().intValue())))
            .andExpect(jsonPath("$.[*].dbmsTypeName").value(hasItem(DEFAULT_DBMS_TYPE_NAME)))
            .andExpect(jsonPath("$.[*].dbsmTypeDescription").value(hasItem(DEFAULT_DBSM_TYPE_DESCRIPTION)));

        // Check, that the count call also returns 1
        restDsDbmsTypesMockMvc.perform(get("/api/ds-dbms-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDsDbmsTypesShouldNotBeFound(String filter) throws Exception {
        restDsDbmsTypesMockMvc.perform(get("/api/ds-dbms-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDsDbmsTypesMockMvc.perform(get("/api/ds-dbms-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDsDbmsTypes() throws Exception {
        // Get the dsDbmsTypes
        restDsDbmsTypesMockMvc.perform(get("/api/ds-dbms-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDsDbmsTypes() throws Exception {
        // Initialize the database
        dsDbmsTypesRepository.saveAndFlush(dsDbmsTypes);

        int databaseSizeBeforeUpdate = dsDbmsTypesRepository.findAll().size();

        // Update the dsDbmsTypes
        DsDbmsTypes updatedDsDbmsTypes = dsDbmsTypesRepository.findById(dsDbmsTypes.getId()).get();
        // Disconnect from session so that the updates on updatedDsDbmsTypes are not directly saved in db
        em.detach(updatedDsDbmsTypes);
        updatedDsDbmsTypes
            .dbmsTypeName(UPDATED_DBMS_TYPE_NAME)
            .dbsmTypeDescription(UPDATED_DBSM_TYPE_DESCRIPTION);
        DsDbmsTypesDTO dsDbmsTypesDTO = dsDbmsTypesMapper.toDto(updatedDsDbmsTypes);

        restDsDbmsTypesMockMvc.perform(put("/api/ds-dbms-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dsDbmsTypesDTO)))
            .andExpect(status().isOk());

        // Validate the DsDbmsTypes in the database
        List<DsDbmsTypes> dsDbmsTypesList = dsDbmsTypesRepository.findAll();
        assertThat(dsDbmsTypesList).hasSize(databaseSizeBeforeUpdate);
        DsDbmsTypes testDsDbmsTypes = dsDbmsTypesList.get(dsDbmsTypesList.size() - 1);
        assertThat(testDsDbmsTypes.getDbmsTypeName()).isEqualTo(UPDATED_DBMS_TYPE_NAME);
        assertThat(testDsDbmsTypes.getDbsmTypeDescription()).isEqualTo(UPDATED_DBSM_TYPE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingDsDbmsTypes() throws Exception {
        int databaseSizeBeforeUpdate = dsDbmsTypesRepository.findAll().size();

        // Create the DsDbmsTypes
        DsDbmsTypesDTO dsDbmsTypesDTO = dsDbmsTypesMapper.toDto(dsDbmsTypes);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDsDbmsTypesMockMvc.perform(put("/api/ds-dbms-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dsDbmsTypesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DsDbmsTypes in the database
        List<DsDbmsTypes> dsDbmsTypesList = dsDbmsTypesRepository.findAll();
        assertThat(dsDbmsTypesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDsDbmsTypes() throws Exception {
        // Initialize the database
        dsDbmsTypesRepository.saveAndFlush(dsDbmsTypes);

        int databaseSizeBeforeDelete = dsDbmsTypesRepository.findAll().size();

        // Delete the dsDbmsTypes
        restDsDbmsTypesMockMvc.perform(delete("/api/ds-dbms-types/{id}", dsDbmsTypes.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DsDbmsTypes> dsDbmsTypesList = dsDbmsTypesRepository.findAll();
        assertThat(dsDbmsTypesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
