package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.DsTableTypes;
import com.mycompany.myapp.repository.DsTableTypesRepository;
import com.mycompany.myapp.service.DsTableTypesService;
import com.mycompany.myapp.service.dto.DsTableTypesDTO;
import com.mycompany.myapp.service.mapper.DsTableTypesMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.DsTableTypesCriteria;
import com.mycompany.myapp.service.DsTableTypesQueryService;

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
 * Integration tests for the {@link DsTableTypesResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class DsTableTypesResourceIT {

    private static final String DEFAULT_TBL_TYPE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TBL_TYPE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TBL_TYPE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_TBL_TYPE_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private DsTableTypesRepository dsTableTypesRepository;

    @Autowired
    private DsTableTypesMapper dsTableTypesMapper;

    @Autowired
    private DsTableTypesService dsTableTypesService;

    @Autowired
    private DsTableTypesQueryService dsTableTypesQueryService;

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

    private MockMvc restDsTableTypesMockMvc;

    private DsTableTypes dsTableTypes;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DsTableTypesResource dsTableTypesResource = new DsTableTypesResource(dsTableTypesService, dsTableTypesQueryService);
        this.restDsTableTypesMockMvc = MockMvcBuilders.standaloneSetup(dsTableTypesResource)
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
    public static DsTableTypes createEntity(EntityManager em) {
        DsTableTypes dsTableTypes = new DsTableTypes()
            .tblTypeName(DEFAULT_TBL_TYPE_NAME)
            .tblTypeDescription(DEFAULT_TBL_TYPE_DESCRIPTION);
        return dsTableTypes;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DsTableTypes createUpdatedEntity(EntityManager em) {
        DsTableTypes dsTableTypes = new DsTableTypes()
            .tblTypeName(UPDATED_TBL_TYPE_NAME)
            .tblTypeDescription(UPDATED_TBL_TYPE_DESCRIPTION);
        return dsTableTypes;
    }

    @BeforeEach
    public void initTest() {
        dsTableTypes = createEntity(em);
    }

    @Test
    @Transactional
    public void createDsTableTypes() throws Exception {
        int databaseSizeBeforeCreate = dsTableTypesRepository.findAll().size();

        // Create the DsTableTypes
        DsTableTypesDTO dsTableTypesDTO = dsTableTypesMapper.toDto(dsTableTypes);
        restDsTableTypesMockMvc.perform(post("/api/ds-table-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dsTableTypesDTO)))
            .andExpect(status().isCreated());

        // Validate the DsTableTypes in the database
        List<DsTableTypes> dsTableTypesList = dsTableTypesRepository.findAll();
        assertThat(dsTableTypesList).hasSize(databaseSizeBeforeCreate + 1);
        DsTableTypes testDsTableTypes = dsTableTypesList.get(dsTableTypesList.size() - 1);
        assertThat(testDsTableTypes.getTblTypeName()).isEqualTo(DEFAULT_TBL_TYPE_NAME);
        assertThat(testDsTableTypes.getTblTypeDescription()).isEqualTo(DEFAULT_TBL_TYPE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createDsTableTypesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dsTableTypesRepository.findAll().size();

        // Create the DsTableTypes with an existing ID
        dsTableTypes.setId(1L);
        DsTableTypesDTO dsTableTypesDTO = dsTableTypesMapper.toDto(dsTableTypes);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDsTableTypesMockMvc.perform(post("/api/ds-table-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dsTableTypesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DsTableTypes in the database
        List<DsTableTypes> dsTableTypesList = dsTableTypesRepository.findAll();
        assertThat(dsTableTypesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDsTableTypes() throws Exception {
        // Initialize the database
        dsTableTypesRepository.saveAndFlush(dsTableTypes);

        // Get all the dsTableTypesList
        restDsTableTypesMockMvc.perform(get("/api/ds-table-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dsTableTypes.getId().intValue())))
            .andExpect(jsonPath("$.[*].tblTypeName").value(hasItem(DEFAULT_TBL_TYPE_NAME)))
            .andExpect(jsonPath("$.[*].tblTypeDescription").value(hasItem(DEFAULT_TBL_TYPE_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getDsTableTypes() throws Exception {
        // Initialize the database
        dsTableTypesRepository.saveAndFlush(dsTableTypes);

        // Get the dsTableTypes
        restDsTableTypesMockMvc.perform(get("/api/ds-table-types/{id}", dsTableTypes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dsTableTypes.getId().intValue()))
            .andExpect(jsonPath("$.tblTypeName").value(DEFAULT_TBL_TYPE_NAME))
            .andExpect(jsonPath("$.tblTypeDescription").value(DEFAULT_TBL_TYPE_DESCRIPTION));
    }


    @Test
    @Transactional
    public void getDsTableTypesByIdFiltering() throws Exception {
        // Initialize the database
        dsTableTypesRepository.saveAndFlush(dsTableTypes);

        Long id = dsTableTypes.getId();

        defaultDsTableTypesShouldBeFound("id.equals=" + id);
        defaultDsTableTypesShouldNotBeFound("id.notEquals=" + id);

        defaultDsTableTypesShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDsTableTypesShouldNotBeFound("id.greaterThan=" + id);

        defaultDsTableTypesShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDsTableTypesShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDsTableTypesByTblTypeNameIsEqualToSomething() throws Exception {
        // Initialize the database
        dsTableTypesRepository.saveAndFlush(dsTableTypes);

        // Get all the dsTableTypesList where tblTypeName equals to DEFAULT_TBL_TYPE_NAME
        defaultDsTableTypesShouldBeFound("tblTypeName.equals=" + DEFAULT_TBL_TYPE_NAME);

        // Get all the dsTableTypesList where tblTypeName equals to UPDATED_TBL_TYPE_NAME
        defaultDsTableTypesShouldNotBeFound("tblTypeName.equals=" + UPDATED_TBL_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllDsTableTypesByTblTypeNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dsTableTypesRepository.saveAndFlush(dsTableTypes);

        // Get all the dsTableTypesList where tblTypeName not equals to DEFAULT_TBL_TYPE_NAME
        defaultDsTableTypesShouldNotBeFound("tblTypeName.notEquals=" + DEFAULT_TBL_TYPE_NAME);

        // Get all the dsTableTypesList where tblTypeName not equals to UPDATED_TBL_TYPE_NAME
        defaultDsTableTypesShouldBeFound("tblTypeName.notEquals=" + UPDATED_TBL_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllDsTableTypesByTblTypeNameIsInShouldWork() throws Exception {
        // Initialize the database
        dsTableTypesRepository.saveAndFlush(dsTableTypes);

        // Get all the dsTableTypesList where tblTypeName in DEFAULT_TBL_TYPE_NAME or UPDATED_TBL_TYPE_NAME
        defaultDsTableTypesShouldBeFound("tblTypeName.in=" + DEFAULT_TBL_TYPE_NAME + "," + UPDATED_TBL_TYPE_NAME);

        // Get all the dsTableTypesList where tblTypeName equals to UPDATED_TBL_TYPE_NAME
        defaultDsTableTypesShouldNotBeFound("tblTypeName.in=" + UPDATED_TBL_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllDsTableTypesByTblTypeNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        dsTableTypesRepository.saveAndFlush(dsTableTypes);

        // Get all the dsTableTypesList where tblTypeName is not null
        defaultDsTableTypesShouldBeFound("tblTypeName.specified=true");

        // Get all the dsTableTypesList where tblTypeName is null
        defaultDsTableTypesShouldNotBeFound("tblTypeName.specified=false");
    }
                @Test
    @Transactional
    public void getAllDsTableTypesByTblTypeNameContainsSomething() throws Exception {
        // Initialize the database
        dsTableTypesRepository.saveAndFlush(dsTableTypes);

        // Get all the dsTableTypesList where tblTypeName contains DEFAULT_TBL_TYPE_NAME
        defaultDsTableTypesShouldBeFound("tblTypeName.contains=" + DEFAULT_TBL_TYPE_NAME);

        // Get all the dsTableTypesList where tblTypeName contains UPDATED_TBL_TYPE_NAME
        defaultDsTableTypesShouldNotBeFound("tblTypeName.contains=" + UPDATED_TBL_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllDsTableTypesByTblTypeNameNotContainsSomething() throws Exception {
        // Initialize the database
        dsTableTypesRepository.saveAndFlush(dsTableTypes);

        // Get all the dsTableTypesList where tblTypeName does not contain DEFAULT_TBL_TYPE_NAME
        defaultDsTableTypesShouldNotBeFound("tblTypeName.doesNotContain=" + DEFAULT_TBL_TYPE_NAME);

        // Get all the dsTableTypesList where tblTypeName does not contain UPDATED_TBL_TYPE_NAME
        defaultDsTableTypesShouldBeFound("tblTypeName.doesNotContain=" + UPDATED_TBL_TYPE_NAME);
    }


    @Test
    @Transactional
    public void getAllDsTableTypesByTblTypeDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        dsTableTypesRepository.saveAndFlush(dsTableTypes);

        // Get all the dsTableTypesList where tblTypeDescription equals to DEFAULT_TBL_TYPE_DESCRIPTION
        defaultDsTableTypesShouldBeFound("tblTypeDescription.equals=" + DEFAULT_TBL_TYPE_DESCRIPTION);

        // Get all the dsTableTypesList where tblTypeDescription equals to UPDATED_TBL_TYPE_DESCRIPTION
        defaultDsTableTypesShouldNotBeFound("tblTypeDescription.equals=" + UPDATED_TBL_TYPE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDsTableTypesByTblTypeDescriptionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dsTableTypesRepository.saveAndFlush(dsTableTypes);

        // Get all the dsTableTypesList where tblTypeDescription not equals to DEFAULT_TBL_TYPE_DESCRIPTION
        defaultDsTableTypesShouldNotBeFound("tblTypeDescription.notEquals=" + DEFAULT_TBL_TYPE_DESCRIPTION);

        // Get all the dsTableTypesList where tblTypeDescription not equals to UPDATED_TBL_TYPE_DESCRIPTION
        defaultDsTableTypesShouldBeFound("tblTypeDescription.notEquals=" + UPDATED_TBL_TYPE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDsTableTypesByTblTypeDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        dsTableTypesRepository.saveAndFlush(dsTableTypes);

        // Get all the dsTableTypesList where tblTypeDescription in DEFAULT_TBL_TYPE_DESCRIPTION or UPDATED_TBL_TYPE_DESCRIPTION
        defaultDsTableTypesShouldBeFound("tblTypeDescription.in=" + DEFAULT_TBL_TYPE_DESCRIPTION + "," + UPDATED_TBL_TYPE_DESCRIPTION);

        // Get all the dsTableTypesList where tblTypeDescription equals to UPDATED_TBL_TYPE_DESCRIPTION
        defaultDsTableTypesShouldNotBeFound("tblTypeDescription.in=" + UPDATED_TBL_TYPE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDsTableTypesByTblTypeDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        dsTableTypesRepository.saveAndFlush(dsTableTypes);

        // Get all the dsTableTypesList where tblTypeDescription is not null
        defaultDsTableTypesShouldBeFound("tblTypeDescription.specified=true");

        // Get all the dsTableTypesList where tblTypeDescription is null
        defaultDsTableTypesShouldNotBeFound("tblTypeDescription.specified=false");
    }
                @Test
    @Transactional
    public void getAllDsTableTypesByTblTypeDescriptionContainsSomething() throws Exception {
        // Initialize the database
        dsTableTypesRepository.saveAndFlush(dsTableTypes);

        // Get all the dsTableTypesList where tblTypeDescription contains DEFAULT_TBL_TYPE_DESCRIPTION
        defaultDsTableTypesShouldBeFound("tblTypeDescription.contains=" + DEFAULT_TBL_TYPE_DESCRIPTION);

        // Get all the dsTableTypesList where tblTypeDescription contains UPDATED_TBL_TYPE_DESCRIPTION
        defaultDsTableTypesShouldNotBeFound("tblTypeDescription.contains=" + UPDATED_TBL_TYPE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDsTableTypesByTblTypeDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        dsTableTypesRepository.saveAndFlush(dsTableTypes);

        // Get all the dsTableTypesList where tblTypeDescription does not contain DEFAULT_TBL_TYPE_DESCRIPTION
        defaultDsTableTypesShouldNotBeFound("tblTypeDescription.doesNotContain=" + DEFAULT_TBL_TYPE_DESCRIPTION);

        // Get all the dsTableTypesList where tblTypeDescription does not contain UPDATED_TBL_TYPE_DESCRIPTION
        defaultDsTableTypesShouldBeFound("tblTypeDescription.doesNotContain=" + UPDATED_TBL_TYPE_DESCRIPTION);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDsTableTypesShouldBeFound(String filter) throws Exception {
        restDsTableTypesMockMvc.perform(get("/api/ds-table-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dsTableTypes.getId().intValue())))
            .andExpect(jsonPath("$.[*].tblTypeName").value(hasItem(DEFAULT_TBL_TYPE_NAME)))
            .andExpect(jsonPath("$.[*].tblTypeDescription").value(hasItem(DEFAULT_TBL_TYPE_DESCRIPTION)));

        // Check, that the count call also returns 1
        restDsTableTypesMockMvc.perform(get("/api/ds-table-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDsTableTypesShouldNotBeFound(String filter) throws Exception {
        restDsTableTypesMockMvc.perform(get("/api/ds-table-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDsTableTypesMockMvc.perform(get("/api/ds-table-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDsTableTypes() throws Exception {
        // Get the dsTableTypes
        restDsTableTypesMockMvc.perform(get("/api/ds-table-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDsTableTypes() throws Exception {
        // Initialize the database
        dsTableTypesRepository.saveAndFlush(dsTableTypes);

        int databaseSizeBeforeUpdate = dsTableTypesRepository.findAll().size();

        // Update the dsTableTypes
        DsTableTypes updatedDsTableTypes = dsTableTypesRepository.findById(dsTableTypes.getId()).get();
        // Disconnect from session so that the updates on updatedDsTableTypes are not directly saved in db
        em.detach(updatedDsTableTypes);
        updatedDsTableTypes
            .tblTypeName(UPDATED_TBL_TYPE_NAME)
            .tblTypeDescription(UPDATED_TBL_TYPE_DESCRIPTION);
        DsTableTypesDTO dsTableTypesDTO = dsTableTypesMapper.toDto(updatedDsTableTypes);

        restDsTableTypesMockMvc.perform(put("/api/ds-table-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dsTableTypesDTO)))
            .andExpect(status().isOk());

        // Validate the DsTableTypes in the database
        List<DsTableTypes> dsTableTypesList = dsTableTypesRepository.findAll();
        assertThat(dsTableTypesList).hasSize(databaseSizeBeforeUpdate);
        DsTableTypes testDsTableTypes = dsTableTypesList.get(dsTableTypesList.size() - 1);
        assertThat(testDsTableTypes.getTblTypeName()).isEqualTo(UPDATED_TBL_TYPE_NAME);
        assertThat(testDsTableTypes.getTblTypeDescription()).isEqualTo(UPDATED_TBL_TYPE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingDsTableTypes() throws Exception {
        int databaseSizeBeforeUpdate = dsTableTypesRepository.findAll().size();

        // Create the DsTableTypes
        DsTableTypesDTO dsTableTypesDTO = dsTableTypesMapper.toDto(dsTableTypes);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDsTableTypesMockMvc.perform(put("/api/ds-table-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dsTableTypesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DsTableTypes in the database
        List<DsTableTypes> dsTableTypesList = dsTableTypesRepository.findAll();
        assertThat(dsTableTypesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDsTableTypes() throws Exception {
        // Initialize the database
        dsTableTypesRepository.saveAndFlush(dsTableTypes);

        int databaseSizeBeforeDelete = dsTableTypesRepository.findAll().size();

        // Delete the dsTableTypes
        restDsTableTypesMockMvc.perform(delete("/api/ds-table-types/{id}", dsTableTypes.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DsTableTypes> dsTableTypesList = dsTableTypesRepository.findAll();
        assertThat(dsTableTypesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
