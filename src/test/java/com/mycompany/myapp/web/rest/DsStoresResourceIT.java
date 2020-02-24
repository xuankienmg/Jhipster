package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.DsStores;
import com.mycompany.myapp.domain.DsDbmsTypes;
import com.mycompany.myapp.domain.DsCollations;
import com.mycompany.myapp.repository.DsStoresRepository;
import com.mycompany.myapp.service.DsStoresService;
import com.mycompany.myapp.service.dto.DsStoresDTO;
import com.mycompany.myapp.service.mapper.DsStoresMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.DsStoresCriteria;
import com.mycompany.myapp.service.DsStoresQueryService;

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
 * Integration tests for the {@link DsStoresResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class DsStoresResourceIT {

    private static final String DEFAULT_STORE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STORE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STORE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_STORE_DESCRIPTION = "BBBBBBBBBB";

    private static final Long DEFAULT_STORE_SIZE = 1L;
    private static final Long UPDATED_STORE_SIZE = 2L;
    private static final Long SMALLER_STORE_SIZE = 1L - 1L;

    private static final Long DEFAULT_GROWTH_SIZE = 1L;
    private static final Long UPDATED_GROWTH_SIZE = 2L;
    private static final Long SMALLER_GROWTH_SIZE = 1L - 1L;

    @Autowired
    private DsStoresRepository dsStoresRepository;

    @Autowired
    private DsStoresMapper dsStoresMapper;

    @Autowired
    private DsStoresService dsStoresService;

    @Autowired
    private DsStoresQueryService dsStoresQueryService;

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

    private MockMvc restDsStoresMockMvc;

    private DsStores dsStores;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DsStoresResource dsStoresResource = new DsStoresResource(dsStoresService, dsStoresQueryService);
        this.restDsStoresMockMvc = MockMvcBuilders.standaloneSetup(dsStoresResource)
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
    public static DsStores createEntity(EntityManager em) {
        DsStores dsStores = new DsStores()
            .storeName(DEFAULT_STORE_NAME)
            .storeDescription(DEFAULT_STORE_DESCRIPTION)
            .storeSize(DEFAULT_STORE_SIZE)
            .growthSize(DEFAULT_GROWTH_SIZE);
        return dsStores;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DsStores createUpdatedEntity(EntityManager em) {
        DsStores dsStores = new DsStores()
            .storeName(UPDATED_STORE_NAME)
            .storeDescription(UPDATED_STORE_DESCRIPTION)
            .storeSize(UPDATED_STORE_SIZE)
            .growthSize(UPDATED_GROWTH_SIZE);
        return dsStores;
    }

    @BeforeEach
    public void initTest() {
        dsStores = createEntity(em);
    }

    @Test
    @Transactional
    public void createDsStores() throws Exception {
        int databaseSizeBeforeCreate = dsStoresRepository.findAll().size();

        // Create the DsStores
        DsStoresDTO dsStoresDTO = dsStoresMapper.toDto(dsStores);
        restDsStoresMockMvc.perform(post("/api/ds-stores")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dsStoresDTO)))
            .andExpect(status().isCreated());

        // Validate the DsStores in the database
        List<DsStores> dsStoresList = dsStoresRepository.findAll();
        assertThat(dsStoresList).hasSize(databaseSizeBeforeCreate + 1);
        DsStores testDsStores = dsStoresList.get(dsStoresList.size() - 1);
        assertThat(testDsStores.getStoreName()).isEqualTo(DEFAULT_STORE_NAME);
        assertThat(testDsStores.getStoreDescription()).isEqualTo(DEFAULT_STORE_DESCRIPTION);
        assertThat(testDsStores.getStoreSize()).isEqualTo(DEFAULT_STORE_SIZE);
        assertThat(testDsStores.getGrowthSize()).isEqualTo(DEFAULT_GROWTH_SIZE);
    }

    @Test
    @Transactional
    public void createDsStoresWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dsStoresRepository.findAll().size();

        // Create the DsStores with an existing ID
        dsStores.setId(1L);
        DsStoresDTO dsStoresDTO = dsStoresMapper.toDto(dsStores);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDsStoresMockMvc.perform(post("/api/ds-stores")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dsStoresDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DsStores in the database
        List<DsStores> dsStoresList = dsStoresRepository.findAll();
        assertThat(dsStoresList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDsStores() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);

        // Get all the dsStoresList
        restDsStoresMockMvc.perform(get("/api/ds-stores?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dsStores.getId().intValue())))
            .andExpect(jsonPath("$.[*].storeName").value(hasItem(DEFAULT_STORE_NAME)))
            .andExpect(jsonPath("$.[*].storeDescription").value(hasItem(DEFAULT_STORE_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].storeSize").value(hasItem(DEFAULT_STORE_SIZE.intValue())))
            .andExpect(jsonPath("$.[*].growthSize").value(hasItem(DEFAULT_GROWTH_SIZE.intValue())));
    }
    
    @Test
    @Transactional
    public void getDsStores() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);

        // Get the dsStores
        restDsStoresMockMvc.perform(get("/api/ds-stores/{id}", dsStores.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dsStores.getId().intValue()))
            .andExpect(jsonPath("$.storeName").value(DEFAULT_STORE_NAME))
            .andExpect(jsonPath("$.storeDescription").value(DEFAULT_STORE_DESCRIPTION))
            .andExpect(jsonPath("$.storeSize").value(DEFAULT_STORE_SIZE.intValue()))
            .andExpect(jsonPath("$.growthSize").value(DEFAULT_GROWTH_SIZE.intValue()));
    }


    @Test
    @Transactional
    public void getDsStoresByIdFiltering() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);

        Long id = dsStores.getId();

        defaultDsStoresShouldBeFound("id.equals=" + id);
        defaultDsStoresShouldNotBeFound("id.notEquals=" + id);

        defaultDsStoresShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDsStoresShouldNotBeFound("id.greaterThan=" + id);

        defaultDsStoresShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDsStoresShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDsStoresByStoreNameIsEqualToSomething() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);

        // Get all the dsStoresList where storeName equals to DEFAULT_STORE_NAME
        defaultDsStoresShouldBeFound("storeName.equals=" + DEFAULT_STORE_NAME);

        // Get all the dsStoresList where storeName equals to UPDATED_STORE_NAME
        defaultDsStoresShouldNotBeFound("storeName.equals=" + UPDATED_STORE_NAME);
    }

    @Test
    @Transactional
    public void getAllDsStoresByStoreNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);

        // Get all the dsStoresList where storeName not equals to DEFAULT_STORE_NAME
        defaultDsStoresShouldNotBeFound("storeName.notEquals=" + DEFAULT_STORE_NAME);

        // Get all the dsStoresList where storeName not equals to UPDATED_STORE_NAME
        defaultDsStoresShouldBeFound("storeName.notEquals=" + UPDATED_STORE_NAME);
    }

    @Test
    @Transactional
    public void getAllDsStoresByStoreNameIsInShouldWork() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);

        // Get all the dsStoresList where storeName in DEFAULT_STORE_NAME or UPDATED_STORE_NAME
        defaultDsStoresShouldBeFound("storeName.in=" + DEFAULT_STORE_NAME + "," + UPDATED_STORE_NAME);

        // Get all the dsStoresList where storeName equals to UPDATED_STORE_NAME
        defaultDsStoresShouldNotBeFound("storeName.in=" + UPDATED_STORE_NAME);
    }

    @Test
    @Transactional
    public void getAllDsStoresByStoreNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);

        // Get all the dsStoresList where storeName is not null
        defaultDsStoresShouldBeFound("storeName.specified=true");

        // Get all the dsStoresList where storeName is null
        defaultDsStoresShouldNotBeFound("storeName.specified=false");
    }
                @Test
    @Transactional
    public void getAllDsStoresByStoreNameContainsSomething() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);

        // Get all the dsStoresList where storeName contains DEFAULT_STORE_NAME
        defaultDsStoresShouldBeFound("storeName.contains=" + DEFAULT_STORE_NAME);

        // Get all the dsStoresList where storeName contains UPDATED_STORE_NAME
        defaultDsStoresShouldNotBeFound("storeName.contains=" + UPDATED_STORE_NAME);
    }

    @Test
    @Transactional
    public void getAllDsStoresByStoreNameNotContainsSomething() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);

        // Get all the dsStoresList where storeName does not contain DEFAULT_STORE_NAME
        defaultDsStoresShouldNotBeFound("storeName.doesNotContain=" + DEFAULT_STORE_NAME);

        // Get all the dsStoresList where storeName does not contain UPDATED_STORE_NAME
        defaultDsStoresShouldBeFound("storeName.doesNotContain=" + UPDATED_STORE_NAME);
    }


    @Test
    @Transactional
    public void getAllDsStoresByStoreDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);

        // Get all the dsStoresList where storeDescription equals to DEFAULT_STORE_DESCRIPTION
        defaultDsStoresShouldBeFound("storeDescription.equals=" + DEFAULT_STORE_DESCRIPTION);

        // Get all the dsStoresList where storeDescription equals to UPDATED_STORE_DESCRIPTION
        defaultDsStoresShouldNotBeFound("storeDescription.equals=" + UPDATED_STORE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDsStoresByStoreDescriptionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);

        // Get all the dsStoresList where storeDescription not equals to DEFAULT_STORE_DESCRIPTION
        defaultDsStoresShouldNotBeFound("storeDescription.notEquals=" + DEFAULT_STORE_DESCRIPTION);

        // Get all the dsStoresList where storeDescription not equals to UPDATED_STORE_DESCRIPTION
        defaultDsStoresShouldBeFound("storeDescription.notEquals=" + UPDATED_STORE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDsStoresByStoreDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);

        // Get all the dsStoresList where storeDescription in DEFAULT_STORE_DESCRIPTION or UPDATED_STORE_DESCRIPTION
        defaultDsStoresShouldBeFound("storeDescription.in=" + DEFAULT_STORE_DESCRIPTION + "," + UPDATED_STORE_DESCRIPTION);

        // Get all the dsStoresList where storeDescription equals to UPDATED_STORE_DESCRIPTION
        defaultDsStoresShouldNotBeFound("storeDescription.in=" + UPDATED_STORE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDsStoresByStoreDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);

        // Get all the dsStoresList where storeDescription is not null
        defaultDsStoresShouldBeFound("storeDescription.specified=true");

        // Get all the dsStoresList where storeDescription is null
        defaultDsStoresShouldNotBeFound("storeDescription.specified=false");
    }
                @Test
    @Transactional
    public void getAllDsStoresByStoreDescriptionContainsSomething() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);

        // Get all the dsStoresList where storeDescription contains DEFAULT_STORE_DESCRIPTION
        defaultDsStoresShouldBeFound("storeDescription.contains=" + DEFAULT_STORE_DESCRIPTION);

        // Get all the dsStoresList where storeDescription contains UPDATED_STORE_DESCRIPTION
        defaultDsStoresShouldNotBeFound("storeDescription.contains=" + UPDATED_STORE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDsStoresByStoreDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);

        // Get all the dsStoresList where storeDescription does not contain DEFAULT_STORE_DESCRIPTION
        defaultDsStoresShouldNotBeFound("storeDescription.doesNotContain=" + DEFAULT_STORE_DESCRIPTION);

        // Get all the dsStoresList where storeDescription does not contain UPDATED_STORE_DESCRIPTION
        defaultDsStoresShouldBeFound("storeDescription.doesNotContain=" + UPDATED_STORE_DESCRIPTION);
    }


    @Test
    @Transactional
    public void getAllDsStoresByStoreSizeIsEqualToSomething() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);

        // Get all the dsStoresList where storeSize equals to DEFAULT_STORE_SIZE
        defaultDsStoresShouldBeFound("storeSize.equals=" + DEFAULT_STORE_SIZE);

        // Get all the dsStoresList where storeSize equals to UPDATED_STORE_SIZE
        defaultDsStoresShouldNotBeFound("storeSize.equals=" + UPDATED_STORE_SIZE);
    }

    @Test
    @Transactional
    public void getAllDsStoresByStoreSizeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);

        // Get all the dsStoresList where storeSize not equals to DEFAULT_STORE_SIZE
        defaultDsStoresShouldNotBeFound("storeSize.notEquals=" + DEFAULT_STORE_SIZE);

        // Get all the dsStoresList where storeSize not equals to UPDATED_STORE_SIZE
        defaultDsStoresShouldBeFound("storeSize.notEquals=" + UPDATED_STORE_SIZE);
    }

    @Test
    @Transactional
    public void getAllDsStoresByStoreSizeIsInShouldWork() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);

        // Get all the dsStoresList where storeSize in DEFAULT_STORE_SIZE or UPDATED_STORE_SIZE
        defaultDsStoresShouldBeFound("storeSize.in=" + DEFAULT_STORE_SIZE + "," + UPDATED_STORE_SIZE);

        // Get all the dsStoresList where storeSize equals to UPDATED_STORE_SIZE
        defaultDsStoresShouldNotBeFound("storeSize.in=" + UPDATED_STORE_SIZE);
    }

    @Test
    @Transactional
    public void getAllDsStoresByStoreSizeIsNullOrNotNull() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);

        // Get all the dsStoresList where storeSize is not null
        defaultDsStoresShouldBeFound("storeSize.specified=true");

        // Get all the dsStoresList where storeSize is null
        defaultDsStoresShouldNotBeFound("storeSize.specified=false");
    }

    @Test
    @Transactional
    public void getAllDsStoresByStoreSizeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);

        // Get all the dsStoresList where storeSize is greater than or equal to DEFAULT_STORE_SIZE
        defaultDsStoresShouldBeFound("storeSize.greaterThanOrEqual=" + DEFAULT_STORE_SIZE);

        // Get all the dsStoresList where storeSize is greater than or equal to UPDATED_STORE_SIZE
        defaultDsStoresShouldNotBeFound("storeSize.greaterThanOrEqual=" + UPDATED_STORE_SIZE);
    }

    @Test
    @Transactional
    public void getAllDsStoresByStoreSizeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);

        // Get all the dsStoresList where storeSize is less than or equal to DEFAULT_STORE_SIZE
        defaultDsStoresShouldBeFound("storeSize.lessThanOrEqual=" + DEFAULT_STORE_SIZE);

        // Get all the dsStoresList where storeSize is less than or equal to SMALLER_STORE_SIZE
        defaultDsStoresShouldNotBeFound("storeSize.lessThanOrEqual=" + SMALLER_STORE_SIZE);
    }

    @Test
    @Transactional
    public void getAllDsStoresByStoreSizeIsLessThanSomething() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);

        // Get all the dsStoresList where storeSize is less than DEFAULT_STORE_SIZE
        defaultDsStoresShouldNotBeFound("storeSize.lessThan=" + DEFAULT_STORE_SIZE);

        // Get all the dsStoresList where storeSize is less than UPDATED_STORE_SIZE
        defaultDsStoresShouldBeFound("storeSize.lessThan=" + UPDATED_STORE_SIZE);
    }

    @Test
    @Transactional
    public void getAllDsStoresByStoreSizeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);

        // Get all the dsStoresList where storeSize is greater than DEFAULT_STORE_SIZE
        defaultDsStoresShouldNotBeFound("storeSize.greaterThan=" + DEFAULT_STORE_SIZE);

        // Get all the dsStoresList where storeSize is greater than SMALLER_STORE_SIZE
        defaultDsStoresShouldBeFound("storeSize.greaterThan=" + SMALLER_STORE_SIZE);
    }


    @Test
    @Transactional
    public void getAllDsStoresByGrowthSizeIsEqualToSomething() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);

        // Get all the dsStoresList where growthSize equals to DEFAULT_GROWTH_SIZE
        defaultDsStoresShouldBeFound("growthSize.equals=" + DEFAULT_GROWTH_SIZE);

        // Get all the dsStoresList where growthSize equals to UPDATED_GROWTH_SIZE
        defaultDsStoresShouldNotBeFound("growthSize.equals=" + UPDATED_GROWTH_SIZE);
    }

    @Test
    @Transactional
    public void getAllDsStoresByGrowthSizeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);

        // Get all the dsStoresList where growthSize not equals to DEFAULT_GROWTH_SIZE
        defaultDsStoresShouldNotBeFound("growthSize.notEquals=" + DEFAULT_GROWTH_SIZE);

        // Get all the dsStoresList where growthSize not equals to UPDATED_GROWTH_SIZE
        defaultDsStoresShouldBeFound("growthSize.notEquals=" + UPDATED_GROWTH_SIZE);
    }

    @Test
    @Transactional
    public void getAllDsStoresByGrowthSizeIsInShouldWork() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);

        // Get all the dsStoresList where growthSize in DEFAULT_GROWTH_SIZE or UPDATED_GROWTH_SIZE
        defaultDsStoresShouldBeFound("growthSize.in=" + DEFAULT_GROWTH_SIZE + "," + UPDATED_GROWTH_SIZE);

        // Get all the dsStoresList where growthSize equals to UPDATED_GROWTH_SIZE
        defaultDsStoresShouldNotBeFound("growthSize.in=" + UPDATED_GROWTH_SIZE);
    }

    @Test
    @Transactional
    public void getAllDsStoresByGrowthSizeIsNullOrNotNull() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);

        // Get all the dsStoresList where growthSize is not null
        defaultDsStoresShouldBeFound("growthSize.specified=true");

        // Get all the dsStoresList where growthSize is null
        defaultDsStoresShouldNotBeFound("growthSize.specified=false");
    }

    @Test
    @Transactional
    public void getAllDsStoresByGrowthSizeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);

        // Get all the dsStoresList where growthSize is greater than or equal to DEFAULT_GROWTH_SIZE
        defaultDsStoresShouldBeFound("growthSize.greaterThanOrEqual=" + DEFAULT_GROWTH_SIZE);

        // Get all the dsStoresList where growthSize is greater than or equal to UPDATED_GROWTH_SIZE
        defaultDsStoresShouldNotBeFound("growthSize.greaterThanOrEqual=" + UPDATED_GROWTH_SIZE);
    }

    @Test
    @Transactional
    public void getAllDsStoresByGrowthSizeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);

        // Get all the dsStoresList where growthSize is less than or equal to DEFAULT_GROWTH_SIZE
        defaultDsStoresShouldBeFound("growthSize.lessThanOrEqual=" + DEFAULT_GROWTH_SIZE);

        // Get all the dsStoresList where growthSize is less than or equal to SMALLER_GROWTH_SIZE
        defaultDsStoresShouldNotBeFound("growthSize.lessThanOrEqual=" + SMALLER_GROWTH_SIZE);
    }

    @Test
    @Transactional
    public void getAllDsStoresByGrowthSizeIsLessThanSomething() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);

        // Get all the dsStoresList where growthSize is less than DEFAULT_GROWTH_SIZE
        defaultDsStoresShouldNotBeFound("growthSize.lessThan=" + DEFAULT_GROWTH_SIZE);

        // Get all the dsStoresList where growthSize is less than UPDATED_GROWTH_SIZE
        defaultDsStoresShouldBeFound("growthSize.lessThan=" + UPDATED_GROWTH_SIZE);
    }

    @Test
    @Transactional
    public void getAllDsStoresByGrowthSizeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);

        // Get all the dsStoresList where growthSize is greater than DEFAULT_GROWTH_SIZE
        defaultDsStoresShouldNotBeFound("growthSize.greaterThan=" + DEFAULT_GROWTH_SIZE);

        // Get all the dsStoresList where growthSize is greater than SMALLER_GROWTH_SIZE
        defaultDsStoresShouldBeFound("growthSize.greaterThan=" + SMALLER_GROWTH_SIZE);
    }


    @Test
    @Transactional
    public void getAllDsStoresByStoreDbmsTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);
        DsDbmsTypes storeDbmsType = DsDbmsTypesResourceIT.createEntity(em);
        em.persist(storeDbmsType);
        em.flush();
        dsStores.setStoreDbmsType(storeDbmsType);
        dsStoresRepository.saveAndFlush(dsStores);
        Long storeDbmsTypeId = storeDbmsType.getId();

        // Get all the dsStoresList where storeDbmsType equals to storeDbmsTypeId
        defaultDsStoresShouldBeFound("storeDbmsTypeId.equals=" + storeDbmsTypeId);

        // Get all the dsStoresList where storeDbmsType equals to storeDbmsTypeId + 1
        defaultDsStoresShouldNotBeFound("storeDbmsTypeId.equals=" + (storeDbmsTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllDsStoresByStoreCollationIsEqualToSomething() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);
        DsCollations storeCollation = DsCollationsResourceIT.createEntity(em);
        em.persist(storeCollation);
        em.flush();
        dsStores.setStoreCollation(storeCollation);
        dsStoresRepository.saveAndFlush(dsStores);
        Long storeCollationId = storeCollation.getId();

        // Get all the dsStoresList where storeCollation equals to storeCollationId
        defaultDsStoresShouldBeFound("storeCollationId.equals=" + storeCollationId);

        // Get all the dsStoresList where storeCollation equals to storeCollationId + 1
        defaultDsStoresShouldNotBeFound("storeCollationId.equals=" + (storeCollationId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDsStoresShouldBeFound(String filter) throws Exception {
        restDsStoresMockMvc.perform(get("/api/ds-stores?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dsStores.getId().intValue())))
            .andExpect(jsonPath("$.[*].storeName").value(hasItem(DEFAULT_STORE_NAME)))
            .andExpect(jsonPath("$.[*].storeDescription").value(hasItem(DEFAULT_STORE_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].storeSize").value(hasItem(DEFAULT_STORE_SIZE.intValue())))
            .andExpect(jsonPath("$.[*].growthSize").value(hasItem(DEFAULT_GROWTH_SIZE.intValue())));

        // Check, that the count call also returns 1
        restDsStoresMockMvc.perform(get("/api/ds-stores/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDsStoresShouldNotBeFound(String filter) throws Exception {
        restDsStoresMockMvc.perform(get("/api/ds-stores?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDsStoresMockMvc.perform(get("/api/ds-stores/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDsStores() throws Exception {
        // Get the dsStores
        restDsStoresMockMvc.perform(get("/api/ds-stores/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDsStores() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);

        int databaseSizeBeforeUpdate = dsStoresRepository.findAll().size();

        // Update the dsStores
        DsStores updatedDsStores = dsStoresRepository.findById(dsStores.getId()).get();
        // Disconnect from session so that the updates on updatedDsStores are not directly saved in db
        em.detach(updatedDsStores);
        updatedDsStores
            .storeName(UPDATED_STORE_NAME)
            .storeDescription(UPDATED_STORE_DESCRIPTION)
            .storeSize(UPDATED_STORE_SIZE)
            .growthSize(UPDATED_GROWTH_SIZE);
        DsStoresDTO dsStoresDTO = dsStoresMapper.toDto(updatedDsStores);

        restDsStoresMockMvc.perform(put("/api/ds-stores")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dsStoresDTO)))
            .andExpect(status().isOk());

        // Validate the DsStores in the database
        List<DsStores> dsStoresList = dsStoresRepository.findAll();
        assertThat(dsStoresList).hasSize(databaseSizeBeforeUpdate);
        DsStores testDsStores = dsStoresList.get(dsStoresList.size() - 1);
        assertThat(testDsStores.getStoreName()).isEqualTo(UPDATED_STORE_NAME);
        assertThat(testDsStores.getStoreDescription()).isEqualTo(UPDATED_STORE_DESCRIPTION);
        assertThat(testDsStores.getStoreSize()).isEqualTo(UPDATED_STORE_SIZE);
        assertThat(testDsStores.getGrowthSize()).isEqualTo(UPDATED_GROWTH_SIZE);
    }

    @Test
    @Transactional
    public void updateNonExistingDsStores() throws Exception {
        int databaseSizeBeforeUpdate = dsStoresRepository.findAll().size();

        // Create the DsStores
        DsStoresDTO dsStoresDTO = dsStoresMapper.toDto(dsStores);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDsStoresMockMvc.perform(put("/api/ds-stores")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dsStoresDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DsStores in the database
        List<DsStores> dsStoresList = dsStoresRepository.findAll();
        assertThat(dsStoresList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDsStores() throws Exception {
        // Initialize the database
        dsStoresRepository.saveAndFlush(dsStores);

        int databaseSizeBeforeDelete = dsStoresRepository.findAll().size();

        // Delete the dsStores
        restDsStoresMockMvc.perform(delete("/api/ds-stores/{id}", dsStores.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DsStores> dsStoresList = dsStoresRepository.findAll();
        assertThat(dsStoresList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
