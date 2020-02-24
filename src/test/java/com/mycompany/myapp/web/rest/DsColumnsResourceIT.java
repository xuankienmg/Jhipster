package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.DsColumns;
import com.mycompany.myapp.domain.DsTables;
import com.mycompany.myapp.domain.DqRules;
import com.mycompany.myapp.repository.DsColumnsRepository;
import com.mycompany.myapp.service.DsColumnsService;
import com.mycompany.myapp.service.dto.DsColumnsDTO;
import com.mycompany.myapp.service.mapper.DsColumnsMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.DsColumnsCriteria;
import com.mycompany.myapp.service.DsColumnsQueryService;

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
 * Integration tests for the {@link DsColumnsResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class DsColumnsResourceIT {

    private static final String DEFAULT_COL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COL_DATA_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_COL_DATA_TYPE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_PRIMARY_KEY = false;
    private static final Boolean UPDATED_IS_PRIMARY_KEY = true;

    private static final Boolean DEFAULT_IS_FOREIGN_KEY = false;
    private static final Boolean UPDATED_IS_FOREIGN_KEY = true;

    private static final Boolean DEFAULT_IS_IDENTITY = false;
    private static final Boolean UPDATED_IS_IDENTITY = true;

    private static final Boolean DEFAULT_IS_NULL = false;
    private static final Boolean UPDATED_IS_NULL = true;

    @Autowired
    private DsColumnsRepository dsColumnsRepository;

    @Mock
    private DsColumnsRepository dsColumnsRepositoryMock;

    @Autowired
    private DsColumnsMapper dsColumnsMapper;

    @Mock
    private DsColumnsService dsColumnsServiceMock;

    @Autowired
    private DsColumnsService dsColumnsService;

    @Autowired
    private DsColumnsQueryService dsColumnsQueryService;

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

    private MockMvc restDsColumnsMockMvc;

    private DsColumns dsColumns;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DsColumnsResource dsColumnsResource = new DsColumnsResource(dsColumnsService, dsColumnsQueryService);
        this.restDsColumnsMockMvc = MockMvcBuilders.standaloneSetup(dsColumnsResource)
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
    public static DsColumns createEntity(EntityManager em) {
        DsColumns dsColumns = new DsColumns()
            .colName(DEFAULT_COL_NAME)
            .colDataType(DEFAULT_COL_DATA_TYPE)
            .isPrimaryKey(DEFAULT_IS_PRIMARY_KEY)
            .isForeignKey(DEFAULT_IS_FOREIGN_KEY)
            .isIdentity(DEFAULT_IS_IDENTITY)
            .isNull(DEFAULT_IS_NULL);
        return dsColumns;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DsColumns createUpdatedEntity(EntityManager em) {
        DsColumns dsColumns = new DsColumns()
            .colName(UPDATED_COL_NAME)
            .colDataType(UPDATED_COL_DATA_TYPE)
            .isPrimaryKey(UPDATED_IS_PRIMARY_KEY)
            .isForeignKey(UPDATED_IS_FOREIGN_KEY)
            .isIdentity(UPDATED_IS_IDENTITY)
            .isNull(UPDATED_IS_NULL);
        return dsColumns;
    }

    @BeforeEach
    public void initTest() {
        dsColumns = createEntity(em);
    }

    @Test
    @Transactional
    public void createDsColumns() throws Exception {
        int databaseSizeBeforeCreate = dsColumnsRepository.findAll().size();

        // Create the DsColumns
        DsColumnsDTO dsColumnsDTO = dsColumnsMapper.toDto(dsColumns);
        restDsColumnsMockMvc.perform(post("/api/ds-columns")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dsColumnsDTO)))
            .andExpect(status().isCreated());

        // Validate the DsColumns in the database
        List<DsColumns> dsColumnsList = dsColumnsRepository.findAll();
        assertThat(dsColumnsList).hasSize(databaseSizeBeforeCreate + 1);
        DsColumns testDsColumns = dsColumnsList.get(dsColumnsList.size() - 1);
        assertThat(testDsColumns.getColName()).isEqualTo(DEFAULT_COL_NAME);
        assertThat(testDsColumns.getColDataType()).isEqualTo(DEFAULT_COL_DATA_TYPE);
        assertThat(testDsColumns.isIsPrimaryKey()).isEqualTo(DEFAULT_IS_PRIMARY_KEY);
        assertThat(testDsColumns.isIsForeignKey()).isEqualTo(DEFAULT_IS_FOREIGN_KEY);
        assertThat(testDsColumns.isIsIdentity()).isEqualTo(DEFAULT_IS_IDENTITY);
        assertThat(testDsColumns.isIsNull()).isEqualTo(DEFAULT_IS_NULL);
    }

    @Test
    @Transactional
    public void createDsColumnsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dsColumnsRepository.findAll().size();

        // Create the DsColumns with an existing ID
        dsColumns.setId(1L);
        DsColumnsDTO dsColumnsDTO = dsColumnsMapper.toDto(dsColumns);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDsColumnsMockMvc.perform(post("/api/ds-columns")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dsColumnsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DsColumns in the database
        List<DsColumns> dsColumnsList = dsColumnsRepository.findAll();
        assertThat(dsColumnsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDsColumns() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);

        // Get all the dsColumnsList
        restDsColumnsMockMvc.perform(get("/api/ds-columns?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dsColumns.getId().intValue())))
            .andExpect(jsonPath("$.[*].colName").value(hasItem(DEFAULT_COL_NAME)))
            .andExpect(jsonPath("$.[*].colDataType").value(hasItem(DEFAULT_COL_DATA_TYPE)))
            .andExpect(jsonPath("$.[*].isPrimaryKey").value(hasItem(DEFAULT_IS_PRIMARY_KEY.booleanValue())))
            .andExpect(jsonPath("$.[*].isForeignKey").value(hasItem(DEFAULT_IS_FOREIGN_KEY.booleanValue())))
            .andExpect(jsonPath("$.[*].isIdentity").value(hasItem(DEFAULT_IS_IDENTITY.booleanValue())))
            .andExpect(jsonPath("$.[*].isNull").value(hasItem(DEFAULT_IS_NULL.booleanValue())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllDsColumnsWithEagerRelationshipsIsEnabled() throws Exception {
        DsColumnsResource dsColumnsResource = new DsColumnsResource(dsColumnsServiceMock, dsColumnsQueryService);
        when(dsColumnsServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restDsColumnsMockMvc = MockMvcBuilders.standaloneSetup(dsColumnsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restDsColumnsMockMvc.perform(get("/api/ds-columns?eagerload=true"))
        .andExpect(status().isOk());

        verify(dsColumnsServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllDsColumnsWithEagerRelationshipsIsNotEnabled() throws Exception {
        DsColumnsResource dsColumnsResource = new DsColumnsResource(dsColumnsServiceMock, dsColumnsQueryService);
            when(dsColumnsServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restDsColumnsMockMvc = MockMvcBuilders.standaloneSetup(dsColumnsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restDsColumnsMockMvc.perform(get("/api/ds-columns?eagerload=true"))
        .andExpect(status().isOk());

            verify(dsColumnsServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getDsColumns() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);

        // Get the dsColumns
        restDsColumnsMockMvc.perform(get("/api/ds-columns/{id}", dsColumns.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dsColumns.getId().intValue()))
            .andExpect(jsonPath("$.colName").value(DEFAULT_COL_NAME))
            .andExpect(jsonPath("$.colDataType").value(DEFAULT_COL_DATA_TYPE))
            .andExpect(jsonPath("$.isPrimaryKey").value(DEFAULT_IS_PRIMARY_KEY.booleanValue()))
            .andExpect(jsonPath("$.isForeignKey").value(DEFAULT_IS_FOREIGN_KEY.booleanValue()))
            .andExpect(jsonPath("$.isIdentity").value(DEFAULT_IS_IDENTITY.booleanValue()))
            .andExpect(jsonPath("$.isNull").value(DEFAULT_IS_NULL.booleanValue()));
    }


    @Test
    @Transactional
    public void getDsColumnsByIdFiltering() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);

        Long id = dsColumns.getId();

        defaultDsColumnsShouldBeFound("id.equals=" + id);
        defaultDsColumnsShouldNotBeFound("id.notEquals=" + id);

        defaultDsColumnsShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDsColumnsShouldNotBeFound("id.greaterThan=" + id);

        defaultDsColumnsShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDsColumnsShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDsColumnsByColNameIsEqualToSomething() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);

        // Get all the dsColumnsList where colName equals to DEFAULT_COL_NAME
        defaultDsColumnsShouldBeFound("colName.equals=" + DEFAULT_COL_NAME);

        // Get all the dsColumnsList where colName equals to UPDATED_COL_NAME
        defaultDsColumnsShouldNotBeFound("colName.equals=" + UPDATED_COL_NAME);
    }

    @Test
    @Transactional
    public void getAllDsColumnsByColNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);

        // Get all the dsColumnsList where colName not equals to DEFAULT_COL_NAME
        defaultDsColumnsShouldNotBeFound("colName.notEquals=" + DEFAULT_COL_NAME);

        // Get all the dsColumnsList where colName not equals to UPDATED_COL_NAME
        defaultDsColumnsShouldBeFound("colName.notEquals=" + UPDATED_COL_NAME);
    }

    @Test
    @Transactional
    public void getAllDsColumnsByColNameIsInShouldWork() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);

        // Get all the dsColumnsList where colName in DEFAULT_COL_NAME or UPDATED_COL_NAME
        defaultDsColumnsShouldBeFound("colName.in=" + DEFAULT_COL_NAME + "," + UPDATED_COL_NAME);

        // Get all the dsColumnsList where colName equals to UPDATED_COL_NAME
        defaultDsColumnsShouldNotBeFound("colName.in=" + UPDATED_COL_NAME);
    }

    @Test
    @Transactional
    public void getAllDsColumnsByColNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);

        // Get all the dsColumnsList where colName is not null
        defaultDsColumnsShouldBeFound("colName.specified=true");

        // Get all the dsColumnsList where colName is null
        defaultDsColumnsShouldNotBeFound("colName.specified=false");
    }
                @Test
    @Transactional
    public void getAllDsColumnsByColNameContainsSomething() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);

        // Get all the dsColumnsList where colName contains DEFAULT_COL_NAME
        defaultDsColumnsShouldBeFound("colName.contains=" + DEFAULT_COL_NAME);

        // Get all the dsColumnsList where colName contains UPDATED_COL_NAME
        defaultDsColumnsShouldNotBeFound("colName.contains=" + UPDATED_COL_NAME);
    }

    @Test
    @Transactional
    public void getAllDsColumnsByColNameNotContainsSomething() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);

        // Get all the dsColumnsList where colName does not contain DEFAULT_COL_NAME
        defaultDsColumnsShouldNotBeFound("colName.doesNotContain=" + DEFAULT_COL_NAME);

        // Get all the dsColumnsList where colName does not contain UPDATED_COL_NAME
        defaultDsColumnsShouldBeFound("colName.doesNotContain=" + UPDATED_COL_NAME);
    }


    @Test
    @Transactional
    public void getAllDsColumnsByColDataTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);

        // Get all the dsColumnsList where colDataType equals to DEFAULT_COL_DATA_TYPE
        defaultDsColumnsShouldBeFound("colDataType.equals=" + DEFAULT_COL_DATA_TYPE);

        // Get all the dsColumnsList where colDataType equals to UPDATED_COL_DATA_TYPE
        defaultDsColumnsShouldNotBeFound("colDataType.equals=" + UPDATED_COL_DATA_TYPE);
    }

    @Test
    @Transactional
    public void getAllDsColumnsByColDataTypeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);

        // Get all the dsColumnsList where colDataType not equals to DEFAULT_COL_DATA_TYPE
        defaultDsColumnsShouldNotBeFound("colDataType.notEquals=" + DEFAULT_COL_DATA_TYPE);

        // Get all the dsColumnsList where colDataType not equals to UPDATED_COL_DATA_TYPE
        defaultDsColumnsShouldBeFound("colDataType.notEquals=" + UPDATED_COL_DATA_TYPE);
    }

    @Test
    @Transactional
    public void getAllDsColumnsByColDataTypeIsInShouldWork() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);

        // Get all the dsColumnsList where colDataType in DEFAULT_COL_DATA_TYPE or UPDATED_COL_DATA_TYPE
        defaultDsColumnsShouldBeFound("colDataType.in=" + DEFAULT_COL_DATA_TYPE + "," + UPDATED_COL_DATA_TYPE);

        // Get all the dsColumnsList where colDataType equals to UPDATED_COL_DATA_TYPE
        defaultDsColumnsShouldNotBeFound("colDataType.in=" + UPDATED_COL_DATA_TYPE);
    }

    @Test
    @Transactional
    public void getAllDsColumnsByColDataTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);

        // Get all the dsColumnsList where colDataType is not null
        defaultDsColumnsShouldBeFound("colDataType.specified=true");

        // Get all the dsColumnsList where colDataType is null
        defaultDsColumnsShouldNotBeFound("colDataType.specified=false");
    }
                @Test
    @Transactional
    public void getAllDsColumnsByColDataTypeContainsSomething() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);

        // Get all the dsColumnsList where colDataType contains DEFAULT_COL_DATA_TYPE
        defaultDsColumnsShouldBeFound("colDataType.contains=" + DEFAULT_COL_DATA_TYPE);

        // Get all the dsColumnsList where colDataType contains UPDATED_COL_DATA_TYPE
        defaultDsColumnsShouldNotBeFound("colDataType.contains=" + UPDATED_COL_DATA_TYPE);
    }

    @Test
    @Transactional
    public void getAllDsColumnsByColDataTypeNotContainsSomething() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);

        // Get all the dsColumnsList where colDataType does not contain DEFAULT_COL_DATA_TYPE
        defaultDsColumnsShouldNotBeFound("colDataType.doesNotContain=" + DEFAULT_COL_DATA_TYPE);

        // Get all the dsColumnsList where colDataType does not contain UPDATED_COL_DATA_TYPE
        defaultDsColumnsShouldBeFound("colDataType.doesNotContain=" + UPDATED_COL_DATA_TYPE);
    }


    @Test
    @Transactional
    public void getAllDsColumnsByIsPrimaryKeyIsEqualToSomething() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);

        // Get all the dsColumnsList where isPrimaryKey equals to DEFAULT_IS_PRIMARY_KEY
        defaultDsColumnsShouldBeFound("isPrimaryKey.equals=" + DEFAULT_IS_PRIMARY_KEY);

        // Get all the dsColumnsList where isPrimaryKey equals to UPDATED_IS_PRIMARY_KEY
        defaultDsColumnsShouldNotBeFound("isPrimaryKey.equals=" + UPDATED_IS_PRIMARY_KEY);
    }

    @Test
    @Transactional
    public void getAllDsColumnsByIsPrimaryKeyIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);

        // Get all the dsColumnsList where isPrimaryKey not equals to DEFAULT_IS_PRIMARY_KEY
        defaultDsColumnsShouldNotBeFound("isPrimaryKey.notEquals=" + DEFAULT_IS_PRIMARY_KEY);

        // Get all the dsColumnsList where isPrimaryKey not equals to UPDATED_IS_PRIMARY_KEY
        defaultDsColumnsShouldBeFound("isPrimaryKey.notEquals=" + UPDATED_IS_PRIMARY_KEY);
    }

    @Test
    @Transactional
    public void getAllDsColumnsByIsPrimaryKeyIsInShouldWork() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);

        // Get all the dsColumnsList where isPrimaryKey in DEFAULT_IS_PRIMARY_KEY or UPDATED_IS_PRIMARY_KEY
        defaultDsColumnsShouldBeFound("isPrimaryKey.in=" + DEFAULT_IS_PRIMARY_KEY + "," + UPDATED_IS_PRIMARY_KEY);

        // Get all the dsColumnsList where isPrimaryKey equals to UPDATED_IS_PRIMARY_KEY
        defaultDsColumnsShouldNotBeFound("isPrimaryKey.in=" + UPDATED_IS_PRIMARY_KEY);
    }

    @Test
    @Transactional
    public void getAllDsColumnsByIsPrimaryKeyIsNullOrNotNull() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);

        // Get all the dsColumnsList where isPrimaryKey is not null
        defaultDsColumnsShouldBeFound("isPrimaryKey.specified=true");

        // Get all the dsColumnsList where isPrimaryKey is null
        defaultDsColumnsShouldNotBeFound("isPrimaryKey.specified=false");
    }

    @Test
    @Transactional
    public void getAllDsColumnsByIsForeignKeyIsEqualToSomething() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);

        // Get all the dsColumnsList where isForeignKey equals to DEFAULT_IS_FOREIGN_KEY
        defaultDsColumnsShouldBeFound("isForeignKey.equals=" + DEFAULT_IS_FOREIGN_KEY);

        // Get all the dsColumnsList where isForeignKey equals to UPDATED_IS_FOREIGN_KEY
        defaultDsColumnsShouldNotBeFound("isForeignKey.equals=" + UPDATED_IS_FOREIGN_KEY);
    }

    @Test
    @Transactional
    public void getAllDsColumnsByIsForeignKeyIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);

        // Get all the dsColumnsList where isForeignKey not equals to DEFAULT_IS_FOREIGN_KEY
        defaultDsColumnsShouldNotBeFound("isForeignKey.notEquals=" + DEFAULT_IS_FOREIGN_KEY);

        // Get all the dsColumnsList where isForeignKey not equals to UPDATED_IS_FOREIGN_KEY
        defaultDsColumnsShouldBeFound("isForeignKey.notEquals=" + UPDATED_IS_FOREIGN_KEY);
    }

    @Test
    @Transactional
    public void getAllDsColumnsByIsForeignKeyIsInShouldWork() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);

        // Get all the dsColumnsList where isForeignKey in DEFAULT_IS_FOREIGN_KEY or UPDATED_IS_FOREIGN_KEY
        defaultDsColumnsShouldBeFound("isForeignKey.in=" + DEFAULT_IS_FOREIGN_KEY + "," + UPDATED_IS_FOREIGN_KEY);

        // Get all the dsColumnsList where isForeignKey equals to UPDATED_IS_FOREIGN_KEY
        defaultDsColumnsShouldNotBeFound("isForeignKey.in=" + UPDATED_IS_FOREIGN_KEY);
    }

    @Test
    @Transactional
    public void getAllDsColumnsByIsForeignKeyIsNullOrNotNull() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);

        // Get all the dsColumnsList where isForeignKey is not null
        defaultDsColumnsShouldBeFound("isForeignKey.specified=true");

        // Get all the dsColumnsList where isForeignKey is null
        defaultDsColumnsShouldNotBeFound("isForeignKey.specified=false");
    }

    @Test
    @Transactional
    public void getAllDsColumnsByIsIdentityIsEqualToSomething() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);

        // Get all the dsColumnsList where isIdentity equals to DEFAULT_IS_IDENTITY
        defaultDsColumnsShouldBeFound("isIdentity.equals=" + DEFAULT_IS_IDENTITY);

        // Get all the dsColumnsList where isIdentity equals to UPDATED_IS_IDENTITY
        defaultDsColumnsShouldNotBeFound("isIdentity.equals=" + UPDATED_IS_IDENTITY);
    }

    @Test
    @Transactional
    public void getAllDsColumnsByIsIdentityIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);

        // Get all the dsColumnsList where isIdentity not equals to DEFAULT_IS_IDENTITY
        defaultDsColumnsShouldNotBeFound("isIdentity.notEquals=" + DEFAULT_IS_IDENTITY);

        // Get all the dsColumnsList where isIdentity not equals to UPDATED_IS_IDENTITY
        defaultDsColumnsShouldBeFound("isIdentity.notEquals=" + UPDATED_IS_IDENTITY);
    }

    @Test
    @Transactional
    public void getAllDsColumnsByIsIdentityIsInShouldWork() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);

        // Get all the dsColumnsList where isIdentity in DEFAULT_IS_IDENTITY or UPDATED_IS_IDENTITY
        defaultDsColumnsShouldBeFound("isIdentity.in=" + DEFAULT_IS_IDENTITY + "," + UPDATED_IS_IDENTITY);

        // Get all the dsColumnsList where isIdentity equals to UPDATED_IS_IDENTITY
        defaultDsColumnsShouldNotBeFound("isIdentity.in=" + UPDATED_IS_IDENTITY);
    }

    @Test
    @Transactional
    public void getAllDsColumnsByIsIdentityIsNullOrNotNull() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);

        // Get all the dsColumnsList where isIdentity is not null
        defaultDsColumnsShouldBeFound("isIdentity.specified=true");

        // Get all the dsColumnsList where isIdentity is null
        defaultDsColumnsShouldNotBeFound("isIdentity.specified=false");
    }

    @Test
    @Transactional
    public void getAllDsColumnsByIsNullIsEqualToSomething() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);

        // Get all the dsColumnsList where isNull equals to DEFAULT_IS_NULL
        defaultDsColumnsShouldBeFound("isNull.equals=" + DEFAULT_IS_NULL);

        // Get all the dsColumnsList where isNull equals to UPDATED_IS_NULL
        defaultDsColumnsShouldNotBeFound("isNull.equals=" + UPDATED_IS_NULL);
    }

    @Test
    @Transactional
    public void getAllDsColumnsByIsNullIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);

        // Get all the dsColumnsList where isNull not equals to DEFAULT_IS_NULL
        defaultDsColumnsShouldNotBeFound("isNull.notEquals=" + DEFAULT_IS_NULL);

        // Get all the dsColumnsList where isNull not equals to UPDATED_IS_NULL
        defaultDsColumnsShouldBeFound("isNull.notEquals=" + UPDATED_IS_NULL);
    }

    @Test
    @Transactional
    public void getAllDsColumnsByIsNullIsInShouldWork() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);

        // Get all the dsColumnsList where isNull in DEFAULT_IS_NULL or UPDATED_IS_NULL
        defaultDsColumnsShouldBeFound("isNull.in=" + DEFAULT_IS_NULL + "," + UPDATED_IS_NULL);

        // Get all the dsColumnsList where isNull equals to UPDATED_IS_NULL
        defaultDsColumnsShouldNotBeFound("isNull.in=" + UPDATED_IS_NULL);
    }

    @Test
    @Transactional
    public void getAllDsColumnsByIsNullIsNullOrNotNull() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);

        // Get all the dsColumnsList where isNull is not null
        defaultDsColumnsShouldBeFound("isNull.specified=true");

        // Get all the dsColumnsList where isNull is null
        defaultDsColumnsShouldNotBeFound("isNull.specified=false");
    }

    @Test
    @Transactional
    public void getAllDsColumnsByColTblIsEqualToSomething() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);
        DsTables colTbl = DsTablesResourceIT.createEntity(em);
        em.persist(colTbl);
        em.flush();
        dsColumns.setColTbl(colTbl);
        dsColumnsRepository.saveAndFlush(dsColumns);
        Long colTblId = colTbl.getId();

        // Get all the dsColumnsList where colTbl equals to colTblId
        defaultDsColumnsShouldBeFound("colTblId.equals=" + colTblId);

        // Get all the dsColumnsList where colTbl equals to colTblId + 1
        defaultDsColumnsShouldNotBeFound("colTblId.equals=" + (colTblId + 1));
    }


    @Test
    @Transactional
    public void getAllDsColumnsByRuleIsEqualToSomething() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);
        DqRules rule = DqRulesResourceIT.createEntity(em);
        em.persist(rule);
        em.flush();
        dsColumns.addRule(rule);
        dsColumnsRepository.saveAndFlush(dsColumns);
        Long ruleId = rule.getId();

        // Get all the dsColumnsList where rule equals to ruleId
        defaultDsColumnsShouldBeFound("ruleId.equals=" + ruleId);

        // Get all the dsColumnsList where rule equals to ruleId + 1
        defaultDsColumnsShouldNotBeFound("ruleId.equals=" + (ruleId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDsColumnsShouldBeFound(String filter) throws Exception {
        restDsColumnsMockMvc.perform(get("/api/ds-columns?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dsColumns.getId().intValue())))
            .andExpect(jsonPath("$.[*].colName").value(hasItem(DEFAULT_COL_NAME)))
            .andExpect(jsonPath("$.[*].colDataType").value(hasItem(DEFAULT_COL_DATA_TYPE)))
            .andExpect(jsonPath("$.[*].isPrimaryKey").value(hasItem(DEFAULT_IS_PRIMARY_KEY.booleanValue())))
            .andExpect(jsonPath("$.[*].isForeignKey").value(hasItem(DEFAULT_IS_FOREIGN_KEY.booleanValue())))
            .andExpect(jsonPath("$.[*].isIdentity").value(hasItem(DEFAULT_IS_IDENTITY.booleanValue())))
            .andExpect(jsonPath("$.[*].isNull").value(hasItem(DEFAULT_IS_NULL.booleanValue())));

        // Check, that the count call also returns 1
        restDsColumnsMockMvc.perform(get("/api/ds-columns/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDsColumnsShouldNotBeFound(String filter) throws Exception {
        restDsColumnsMockMvc.perform(get("/api/ds-columns?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDsColumnsMockMvc.perform(get("/api/ds-columns/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDsColumns() throws Exception {
        // Get the dsColumns
        restDsColumnsMockMvc.perform(get("/api/ds-columns/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDsColumns() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);

        int databaseSizeBeforeUpdate = dsColumnsRepository.findAll().size();

        // Update the dsColumns
        DsColumns updatedDsColumns = dsColumnsRepository.findById(dsColumns.getId()).get();
        // Disconnect from session so that the updates on updatedDsColumns are not directly saved in db
        em.detach(updatedDsColumns);
        updatedDsColumns
            .colName(UPDATED_COL_NAME)
            .colDataType(UPDATED_COL_DATA_TYPE)
            .isPrimaryKey(UPDATED_IS_PRIMARY_KEY)
            .isForeignKey(UPDATED_IS_FOREIGN_KEY)
            .isIdentity(UPDATED_IS_IDENTITY)
            .isNull(UPDATED_IS_NULL);
        DsColumnsDTO dsColumnsDTO = dsColumnsMapper.toDto(updatedDsColumns);

        restDsColumnsMockMvc.perform(put("/api/ds-columns")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dsColumnsDTO)))
            .andExpect(status().isOk());

        // Validate the DsColumns in the database
        List<DsColumns> dsColumnsList = dsColumnsRepository.findAll();
        assertThat(dsColumnsList).hasSize(databaseSizeBeforeUpdate);
        DsColumns testDsColumns = dsColumnsList.get(dsColumnsList.size() - 1);
        assertThat(testDsColumns.getColName()).isEqualTo(UPDATED_COL_NAME);
        assertThat(testDsColumns.getColDataType()).isEqualTo(UPDATED_COL_DATA_TYPE);
        assertThat(testDsColumns.isIsPrimaryKey()).isEqualTo(UPDATED_IS_PRIMARY_KEY);
        assertThat(testDsColumns.isIsForeignKey()).isEqualTo(UPDATED_IS_FOREIGN_KEY);
        assertThat(testDsColumns.isIsIdentity()).isEqualTo(UPDATED_IS_IDENTITY);
        assertThat(testDsColumns.isIsNull()).isEqualTo(UPDATED_IS_NULL);
    }

    @Test
    @Transactional
    public void updateNonExistingDsColumns() throws Exception {
        int databaseSizeBeforeUpdate = dsColumnsRepository.findAll().size();

        // Create the DsColumns
        DsColumnsDTO dsColumnsDTO = dsColumnsMapper.toDto(dsColumns);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDsColumnsMockMvc.perform(put("/api/ds-columns")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dsColumnsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DsColumns in the database
        List<DsColumns> dsColumnsList = dsColumnsRepository.findAll();
        assertThat(dsColumnsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDsColumns() throws Exception {
        // Initialize the database
        dsColumnsRepository.saveAndFlush(dsColumns);

        int databaseSizeBeforeDelete = dsColumnsRepository.findAll().size();

        // Delete the dsColumns
        restDsColumnsMockMvc.perform(delete("/api/ds-columns/{id}", dsColumns.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DsColumns> dsColumnsList = dsColumnsRepository.findAll();
        assertThat(dsColumnsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
