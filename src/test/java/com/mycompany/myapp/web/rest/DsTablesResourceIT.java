package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.DsTables;
import com.mycompany.myapp.domain.DsTableTypes;
import com.mycompany.myapp.domain.DsStores;
import com.mycompany.myapp.repository.DsTablesRepository;
import com.mycompany.myapp.service.DsTablesService;
import com.mycompany.myapp.service.dto.DsTablesDTO;
import com.mycompany.myapp.service.mapper.DsTablesMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.DsTablesCriteria;
import com.mycompany.myapp.service.DsTablesQueryService;

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
 * Integration tests for the {@link DsTablesResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class DsTablesResourceIT {

    private static final String DEFAULT_TBL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TBL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TBL_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_TBL_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private DsTablesRepository dsTablesRepository;

    @Autowired
    private DsTablesMapper dsTablesMapper;

    @Autowired
    private DsTablesService dsTablesService;

    @Autowired
    private DsTablesQueryService dsTablesQueryService;

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

    private MockMvc restDsTablesMockMvc;

    private DsTables dsTables;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DsTablesResource dsTablesResource = new DsTablesResource(dsTablesService, dsTablesQueryService);
        this.restDsTablesMockMvc = MockMvcBuilders.standaloneSetup(dsTablesResource)
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
    public static DsTables createEntity(EntityManager em) {
        DsTables dsTables = new DsTables()
            .tblName(DEFAULT_TBL_NAME)
            .tblDescription(DEFAULT_TBL_DESCRIPTION);
        return dsTables;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DsTables createUpdatedEntity(EntityManager em) {
        DsTables dsTables = new DsTables()
            .tblName(UPDATED_TBL_NAME)
            .tblDescription(UPDATED_TBL_DESCRIPTION);
        return dsTables;
    }

    @BeforeEach
    public void initTest() {
        dsTables = createEntity(em);
    }

    @Test
    @Transactional
    public void createDsTables() throws Exception {
        int databaseSizeBeforeCreate = dsTablesRepository.findAll().size();

        // Create the DsTables
        DsTablesDTO dsTablesDTO = dsTablesMapper.toDto(dsTables);
        restDsTablesMockMvc.perform(post("/api/ds-tables")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dsTablesDTO)))
            .andExpect(status().isCreated());

        // Validate the DsTables in the database
        List<DsTables> dsTablesList = dsTablesRepository.findAll();
        assertThat(dsTablesList).hasSize(databaseSizeBeforeCreate + 1);
        DsTables testDsTables = dsTablesList.get(dsTablesList.size() - 1);
        assertThat(testDsTables.getTblName()).isEqualTo(DEFAULT_TBL_NAME);
        assertThat(testDsTables.getTblDescription()).isEqualTo(DEFAULT_TBL_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createDsTablesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dsTablesRepository.findAll().size();

        // Create the DsTables with an existing ID
        dsTables.setId(1L);
        DsTablesDTO dsTablesDTO = dsTablesMapper.toDto(dsTables);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDsTablesMockMvc.perform(post("/api/ds-tables")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dsTablesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DsTables in the database
        List<DsTables> dsTablesList = dsTablesRepository.findAll();
        assertThat(dsTablesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDsTables() throws Exception {
        // Initialize the database
        dsTablesRepository.saveAndFlush(dsTables);

        // Get all the dsTablesList
        restDsTablesMockMvc.perform(get("/api/ds-tables?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dsTables.getId().intValue())))
            .andExpect(jsonPath("$.[*].tblName").value(hasItem(DEFAULT_TBL_NAME)))
            .andExpect(jsonPath("$.[*].tblDescription").value(hasItem(DEFAULT_TBL_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getDsTables() throws Exception {
        // Initialize the database
        dsTablesRepository.saveAndFlush(dsTables);

        // Get the dsTables
        restDsTablesMockMvc.perform(get("/api/ds-tables/{id}", dsTables.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dsTables.getId().intValue()))
            .andExpect(jsonPath("$.tblName").value(DEFAULT_TBL_NAME))
            .andExpect(jsonPath("$.tblDescription").value(DEFAULT_TBL_DESCRIPTION));
    }


    @Test
    @Transactional
    public void getDsTablesByIdFiltering() throws Exception {
        // Initialize the database
        dsTablesRepository.saveAndFlush(dsTables);

        Long id = dsTables.getId();

        defaultDsTablesShouldBeFound("id.equals=" + id);
        defaultDsTablesShouldNotBeFound("id.notEquals=" + id);

        defaultDsTablesShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDsTablesShouldNotBeFound("id.greaterThan=" + id);

        defaultDsTablesShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDsTablesShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDsTablesByTblNameIsEqualToSomething() throws Exception {
        // Initialize the database
        dsTablesRepository.saveAndFlush(dsTables);

        // Get all the dsTablesList where tblName equals to DEFAULT_TBL_NAME
        defaultDsTablesShouldBeFound("tblName.equals=" + DEFAULT_TBL_NAME);

        // Get all the dsTablesList where tblName equals to UPDATED_TBL_NAME
        defaultDsTablesShouldNotBeFound("tblName.equals=" + UPDATED_TBL_NAME);
    }

    @Test
    @Transactional
    public void getAllDsTablesByTblNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dsTablesRepository.saveAndFlush(dsTables);

        // Get all the dsTablesList where tblName not equals to DEFAULT_TBL_NAME
        defaultDsTablesShouldNotBeFound("tblName.notEquals=" + DEFAULT_TBL_NAME);

        // Get all the dsTablesList where tblName not equals to UPDATED_TBL_NAME
        defaultDsTablesShouldBeFound("tblName.notEquals=" + UPDATED_TBL_NAME);
    }

    @Test
    @Transactional
    public void getAllDsTablesByTblNameIsInShouldWork() throws Exception {
        // Initialize the database
        dsTablesRepository.saveAndFlush(dsTables);

        // Get all the dsTablesList where tblName in DEFAULT_TBL_NAME or UPDATED_TBL_NAME
        defaultDsTablesShouldBeFound("tblName.in=" + DEFAULT_TBL_NAME + "," + UPDATED_TBL_NAME);

        // Get all the dsTablesList where tblName equals to UPDATED_TBL_NAME
        defaultDsTablesShouldNotBeFound("tblName.in=" + UPDATED_TBL_NAME);
    }

    @Test
    @Transactional
    public void getAllDsTablesByTblNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        dsTablesRepository.saveAndFlush(dsTables);

        // Get all the dsTablesList where tblName is not null
        defaultDsTablesShouldBeFound("tblName.specified=true");

        // Get all the dsTablesList where tblName is null
        defaultDsTablesShouldNotBeFound("tblName.specified=false");
    }
                @Test
    @Transactional
    public void getAllDsTablesByTblNameContainsSomething() throws Exception {
        // Initialize the database
        dsTablesRepository.saveAndFlush(dsTables);

        // Get all the dsTablesList where tblName contains DEFAULT_TBL_NAME
        defaultDsTablesShouldBeFound("tblName.contains=" + DEFAULT_TBL_NAME);

        // Get all the dsTablesList where tblName contains UPDATED_TBL_NAME
        defaultDsTablesShouldNotBeFound("tblName.contains=" + UPDATED_TBL_NAME);
    }

    @Test
    @Transactional
    public void getAllDsTablesByTblNameNotContainsSomething() throws Exception {
        // Initialize the database
        dsTablesRepository.saveAndFlush(dsTables);

        // Get all the dsTablesList where tblName does not contain DEFAULT_TBL_NAME
        defaultDsTablesShouldNotBeFound("tblName.doesNotContain=" + DEFAULT_TBL_NAME);

        // Get all the dsTablesList where tblName does not contain UPDATED_TBL_NAME
        defaultDsTablesShouldBeFound("tblName.doesNotContain=" + UPDATED_TBL_NAME);
    }


    @Test
    @Transactional
    public void getAllDsTablesByTblDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        dsTablesRepository.saveAndFlush(dsTables);

        // Get all the dsTablesList where tblDescription equals to DEFAULT_TBL_DESCRIPTION
        defaultDsTablesShouldBeFound("tblDescription.equals=" + DEFAULT_TBL_DESCRIPTION);

        // Get all the dsTablesList where tblDescription equals to UPDATED_TBL_DESCRIPTION
        defaultDsTablesShouldNotBeFound("tblDescription.equals=" + UPDATED_TBL_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDsTablesByTblDescriptionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dsTablesRepository.saveAndFlush(dsTables);

        // Get all the dsTablesList where tblDescription not equals to DEFAULT_TBL_DESCRIPTION
        defaultDsTablesShouldNotBeFound("tblDescription.notEquals=" + DEFAULT_TBL_DESCRIPTION);

        // Get all the dsTablesList where tblDescription not equals to UPDATED_TBL_DESCRIPTION
        defaultDsTablesShouldBeFound("tblDescription.notEquals=" + UPDATED_TBL_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDsTablesByTblDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        dsTablesRepository.saveAndFlush(dsTables);

        // Get all the dsTablesList where tblDescription in DEFAULT_TBL_DESCRIPTION or UPDATED_TBL_DESCRIPTION
        defaultDsTablesShouldBeFound("tblDescription.in=" + DEFAULT_TBL_DESCRIPTION + "," + UPDATED_TBL_DESCRIPTION);

        // Get all the dsTablesList where tblDescription equals to UPDATED_TBL_DESCRIPTION
        defaultDsTablesShouldNotBeFound("tblDescription.in=" + UPDATED_TBL_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDsTablesByTblDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        dsTablesRepository.saveAndFlush(dsTables);

        // Get all the dsTablesList where tblDescription is not null
        defaultDsTablesShouldBeFound("tblDescription.specified=true");

        // Get all the dsTablesList where tblDescription is null
        defaultDsTablesShouldNotBeFound("tblDescription.specified=false");
    }
                @Test
    @Transactional
    public void getAllDsTablesByTblDescriptionContainsSomething() throws Exception {
        // Initialize the database
        dsTablesRepository.saveAndFlush(dsTables);

        // Get all the dsTablesList where tblDescription contains DEFAULT_TBL_DESCRIPTION
        defaultDsTablesShouldBeFound("tblDescription.contains=" + DEFAULT_TBL_DESCRIPTION);

        // Get all the dsTablesList where tblDescription contains UPDATED_TBL_DESCRIPTION
        defaultDsTablesShouldNotBeFound("tblDescription.contains=" + UPDATED_TBL_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDsTablesByTblDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        dsTablesRepository.saveAndFlush(dsTables);

        // Get all the dsTablesList where tblDescription does not contain DEFAULT_TBL_DESCRIPTION
        defaultDsTablesShouldNotBeFound("tblDescription.doesNotContain=" + DEFAULT_TBL_DESCRIPTION);

        // Get all the dsTablesList where tblDescription does not contain UPDATED_TBL_DESCRIPTION
        defaultDsTablesShouldBeFound("tblDescription.doesNotContain=" + UPDATED_TBL_DESCRIPTION);
    }


    @Test
    @Transactional
    public void getAllDsTablesByTblTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        dsTablesRepository.saveAndFlush(dsTables);
        DsTableTypes tblType = DsTableTypesResourceIT.createEntity(em);
        em.persist(tblType);
        em.flush();
        dsTables.setTblType(tblType);
        dsTablesRepository.saveAndFlush(dsTables);
        Long tblTypeId = tblType.getId();

        // Get all the dsTablesList where tblType equals to tblTypeId
        defaultDsTablesShouldBeFound("tblTypeId.equals=" + tblTypeId);

        // Get all the dsTablesList where tblType equals to tblTypeId + 1
        defaultDsTablesShouldNotBeFound("tblTypeId.equals=" + (tblTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllDsTablesByStoreIsEqualToSomething() throws Exception {
        // Initialize the database
        dsTablesRepository.saveAndFlush(dsTables);
        DsStores store = DsStoresResourceIT.createEntity(em);
        em.persist(store);
        em.flush();
        dsTables.setStore(store);
        dsTablesRepository.saveAndFlush(dsTables);
        Long storeId = store.getId();

        // Get all the dsTablesList where store equals to storeId
        defaultDsTablesShouldBeFound("storeId.equals=" + storeId);

        // Get all the dsTablesList where store equals to storeId + 1
        defaultDsTablesShouldNotBeFound("storeId.equals=" + (storeId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDsTablesShouldBeFound(String filter) throws Exception {
        restDsTablesMockMvc.perform(get("/api/ds-tables?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dsTables.getId().intValue())))
            .andExpect(jsonPath("$.[*].tblName").value(hasItem(DEFAULT_TBL_NAME)))
            .andExpect(jsonPath("$.[*].tblDescription").value(hasItem(DEFAULT_TBL_DESCRIPTION)));

        // Check, that the count call also returns 1
        restDsTablesMockMvc.perform(get("/api/ds-tables/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDsTablesShouldNotBeFound(String filter) throws Exception {
        restDsTablesMockMvc.perform(get("/api/ds-tables?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDsTablesMockMvc.perform(get("/api/ds-tables/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDsTables() throws Exception {
        // Get the dsTables
        restDsTablesMockMvc.perform(get("/api/ds-tables/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDsTables() throws Exception {
        // Initialize the database
        dsTablesRepository.saveAndFlush(dsTables);

        int databaseSizeBeforeUpdate = dsTablesRepository.findAll().size();

        // Update the dsTables
        DsTables updatedDsTables = dsTablesRepository.findById(dsTables.getId()).get();
        // Disconnect from session so that the updates on updatedDsTables are not directly saved in db
        em.detach(updatedDsTables);
        updatedDsTables
            .tblName(UPDATED_TBL_NAME)
            .tblDescription(UPDATED_TBL_DESCRIPTION);
        DsTablesDTO dsTablesDTO = dsTablesMapper.toDto(updatedDsTables);

        restDsTablesMockMvc.perform(put("/api/ds-tables")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dsTablesDTO)))
            .andExpect(status().isOk());

        // Validate the DsTables in the database
        List<DsTables> dsTablesList = dsTablesRepository.findAll();
        assertThat(dsTablesList).hasSize(databaseSizeBeforeUpdate);
        DsTables testDsTables = dsTablesList.get(dsTablesList.size() - 1);
        assertThat(testDsTables.getTblName()).isEqualTo(UPDATED_TBL_NAME);
        assertThat(testDsTables.getTblDescription()).isEqualTo(UPDATED_TBL_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingDsTables() throws Exception {
        int databaseSizeBeforeUpdate = dsTablesRepository.findAll().size();

        // Create the DsTables
        DsTablesDTO dsTablesDTO = dsTablesMapper.toDto(dsTables);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDsTablesMockMvc.perform(put("/api/ds-tables")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dsTablesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DsTables in the database
        List<DsTables> dsTablesList = dsTablesRepository.findAll();
        assertThat(dsTablesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDsTables() throws Exception {
        // Initialize the database
        dsTablesRepository.saveAndFlush(dsTables);

        int databaseSizeBeforeDelete = dsTablesRepository.findAll().size();

        // Delete the dsTables
        restDsTablesMockMvc.perform(delete("/api/ds-tables/{id}", dsTables.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DsTables> dsTablesList = dsTablesRepository.findAll();
        assertThat(dsTablesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
