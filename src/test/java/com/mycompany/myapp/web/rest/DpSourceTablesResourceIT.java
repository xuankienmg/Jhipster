package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.DpSourceTables;
import com.mycompany.myapp.domain.DsTables;
import com.mycompany.myapp.repository.DpSourceTablesRepository;
import com.mycompany.myapp.service.DpSourceTablesService;
import com.mycompany.myapp.service.dto.DpSourceTablesDTO;
import com.mycompany.myapp.service.mapper.DpSourceTablesMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.DpSourceTablesCriteria;
import com.mycompany.myapp.service.DpSourceTablesQueryService;

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
 * Integration tests for the {@link DpSourceTablesResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class DpSourceTablesResourceIT {

    private static final Long DEFAULT_ROWS = 1L;
    private static final Long UPDATED_ROWS = 2L;
    private static final Long SMALLER_ROWS = 1L - 1L;

    private static final Integer DEFAULT_ROW_SIZE = 1;
    private static final Integer UPDATED_ROW_SIZE = 2;
    private static final Integer SMALLER_ROW_SIZE = 1 - 1;

    private static final Integer DEFAULT_COLUMNS = 1;
    private static final Integer UPDATED_COLUMNS = 2;
    private static final Integer SMALLER_COLUMNS = 1 - 1;

    private static final Boolean DEFAULT_HAS_TIMESTAMP = false;
    private static final Boolean UPDATED_HAS_TIMESTAMP = true;

    @Autowired
    private DpSourceTablesRepository dpSourceTablesRepository;

    @Autowired
    private DpSourceTablesMapper dpSourceTablesMapper;

    @Autowired
    private DpSourceTablesService dpSourceTablesService;

    @Autowired
    private DpSourceTablesQueryService dpSourceTablesQueryService;

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

    private MockMvc restDpSourceTablesMockMvc;

    private DpSourceTables dpSourceTables;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DpSourceTablesResource dpSourceTablesResource = new DpSourceTablesResource(dpSourceTablesService, dpSourceTablesQueryService);
        this.restDpSourceTablesMockMvc = MockMvcBuilders.standaloneSetup(dpSourceTablesResource)
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
    public static DpSourceTables createEntity(EntityManager em) {
        DpSourceTables dpSourceTables = new DpSourceTables()
            .rows(DEFAULT_ROWS)
            .rowSize(DEFAULT_ROW_SIZE)
            .columns(DEFAULT_COLUMNS)
            .hasTimestamp(DEFAULT_HAS_TIMESTAMP);
        return dpSourceTables;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DpSourceTables createUpdatedEntity(EntityManager em) {
        DpSourceTables dpSourceTables = new DpSourceTables()
            .rows(UPDATED_ROWS)
            .rowSize(UPDATED_ROW_SIZE)
            .columns(UPDATED_COLUMNS)
            .hasTimestamp(UPDATED_HAS_TIMESTAMP);
        return dpSourceTables;
    }

    @BeforeEach
    public void initTest() {
        dpSourceTables = createEntity(em);
    }

    @Test
    @Transactional
    public void createDpSourceTables() throws Exception {
        int databaseSizeBeforeCreate = dpSourceTablesRepository.findAll().size();

        // Create the DpSourceTables
        DpSourceTablesDTO dpSourceTablesDTO = dpSourceTablesMapper.toDto(dpSourceTables);
        restDpSourceTablesMockMvc.perform(post("/api/dp-source-tables")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dpSourceTablesDTO)))
            .andExpect(status().isCreated());

        // Validate the DpSourceTables in the database
        List<DpSourceTables> dpSourceTablesList = dpSourceTablesRepository.findAll();
        assertThat(dpSourceTablesList).hasSize(databaseSizeBeforeCreate + 1);
        DpSourceTables testDpSourceTables = dpSourceTablesList.get(dpSourceTablesList.size() - 1);
        assertThat(testDpSourceTables.getRows()).isEqualTo(DEFAULT_ROWS);
        assertThat(testDpSourceTables.getRowSize()).isEqualTo(DEFAULT_ROW_SIZE);
        assertThat(testDpSourceTables.getColumns()).isEqualTo(DEFAULT_COLUMNS);
        assertThat(testDpSourceTables.isHasTimestamp()).isEqualTo(DEFAULT_HAS_TIMESTAMP);
    }

    @Test
    @Transactional
    public void createDpSourceTablesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dpSourceTablesRepository.findAll().size();

        // Create the DpSourceTables with an existing ID
        dpSourceTables.setId(1L);
        DpSourceTablesDTO dpSourceTablesDTO = dpSourceTablesMapper.toDto(dpSourceTables);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDpSourceTablesMockMvc.perform(post("/api/dp-source-tables")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dpSourceTablesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DpSourceTables in the database
        List<DpSourceTables> dpSourceTablesList = dpSourceTablesRepository.findAll();
        assertThat(dpSourceTablesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDpSourceTables() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);

        // Get all the dpSourceTablesList
        restDpSourceTablesMockMvc.perform(get("/api/dp-source-tables?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dpSourceTables.getId().intValue())))
            .andExpect(jsonPath("$.[*].rows").value(hasItem(DEFAULT_ROWS.intValue())))
            .andExpect(jsonPath("$.[*].rowSize").value(hasItem(DEFAULT_ROW_SIZE)))
            .andExpect(jsonPath("$.[*].columns").value(hasItem(DEFAULT_COLUMNS)))
            .andExpect(jsonPath("$.[*].hasTimestamp").value(hasItem(DEFAULT_HAS_TIMESTAMP.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getDpSourceTables() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);

        // Get the dpSourceTables
        restDpSourceTablesMockMvc.perform(get("/api/dp-source-tables/{id}", dpSourceTables.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dpSourceTables.getId().intValue()))
            .andExpect(jsonPath("$.rows").value(DEFAULT_ROWS.intValue()))
            .andExpect(jsonPath("$.rowSize").value(DEFAULT_ROW_SIZE))
            .andExpect(jsonPath("$.columns").value(DEFAULT_COLUMNS))
            .andExpect(jsonPath("$.hasTimestamp").value(DEFAULT_HAS_TIMESTAMP.booleanValue()));
    }


    @Test
    @Transactional
    public void getDpSourceTablesByIdFiltering() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);

        Long id = dpSourceTables.getId();

        defaultDpSourceTablesShouldBeFound("id.equals=" + id);
        defaultDpSourceTablesShouldNotBeFound("id.notEquals=" + id);

        defaultDpSourceTablesShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDpSourceTablesShouldNotBeFound("id.greaterThan=" + id);

        defaultDpSourceTablesShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDpSourceTablesShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDpSourceTablesByRowsIsEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);

        // Get all the dpSourceTablesList where rows equals to DEFAULT_ROWS
        defaultDpSourceTablesShouldBeFound("rows.equals=" + DEFAULT_ROWS);

        // Get all the dpSourceTablesList where rows equals to UPDATED_ROWS
        defaultDpSourceTablesShouldNotBeFound("rows.equals=" + UPDATED_ROWS);
    }

    @Test
    @Transactional
    public void getAllDpSourceTablesByRowsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);

        // Get all the dpSourceTablesList where rows not equals to DEFAULT_ROWS
        defaultDpSourceTablesShouldNotBeFound("rows.notEquals=" + DEFAULT_ROWS);

        // Get all the dpSourceTablesList where rows not equals to UPDATED_ROWS
        defaultDpSourceTablesShouldBeFound("rows.notEquals=" + UPDATED_ROWS);
    }

    @Test
    @Transactional
    public void getAllDpSourceTablesByRowsIsInShouldWork() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);

        // Get all the dpSourceTablesList where rows in DEFAULT_ROWS or UPDATED_ROWS
        defaultDpSourceTablesShouldBeFound("rows.in=" + DEFAULT_ROWS + "," + UPDATED_ROWS);

        // Get all the dpSourceTablesList where rows equals to UPDATED_ROWS
        defaultDpSourceTablesShouldNotBeFound("rows.in=" + UPDATED_ROWS);
    }

    @Test
    @Transactional
    public void getAllDpSourceTablesByRowsIsNullOrNotNull() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);

        // Get all the dpSourceTablesList where rows is not null
        defaultDpSourceTablesShouldBeFound("rows.specified=true");

        // Get all the dpSourceTablesList where rows is null
        defaultDpSourceTablesShouldNotBeFound("rows.specified=false");
    }

    @Test
    @Transactional
    public void getAllDpSourceTablesByRowsIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);

        // Get all the dpSourceTablesList where rows is greater than or equal to DEFAULT_ROWS
        defaultDpSourceTablesShouldBeFound("rows.greaterThanOrEqual=" + DEFAULT_ROWS);

        // Get all the dpSourceTablesList where rows is greater than or equal to UPDATED_ROWS
        defaultDpSourceTablesShouldNotBeFound("rows.greaterThanOrEqual=" + UPDATED_ROWS);
    }

    @Test
    @Transactional
    public void getAllDpSourceTablesByRowsIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);

        // Get all the dpSourceTablesList where rows is less than or equal to DEFAULT_ROWS
        defaultDpSourceTablesShouldBeFound("rows.lessThanOrEqual=" + DEFAULT_ROWS);

        // Get all the dpSourceTablesList where rows is less than or equal to SMALLER_ROWS
        defaultDpSourceTablesShouldNotBeFound("rows.lessThanOrEqual=" + SMALLER_ROWS);
    }

    @Test
    @Transactional
    public void getAllDpSourceTablesByRowsIsLessThanSomething() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);

        // Get all the dpSourceTablesList where rows is less than DEFAULT_ROWS
        defaultDpSourceTablesShouldNotBeFound("rows.lessThan=" + DEFAULT_ROWS);

        // Get all the dpSourceTablesList where rows is less than UPDATED_ROWS
        defaultDpSourceTablesShouldBeFound("rows.lessThan=" + UPDATED_ROWS);
    }

    @Test
    @Transactional
    public void getAllDpSourceTablesByRowsIsGreaterThanSomething() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);

        // Get all the dpSourceTablesList where rows is greater than DEFAULT_ROWS
        defaultDpSourceTablesShouldNotBeFound("rows.greaterThan=" + DEFAULT_ROWS);

        // Get all the dpSourceTablesList where rows is greater than SMALLER_ROWS
        defaultDpSourceTablesShouldBeFound("rows.greaterThan=" + SMALLER_ROWS);
    }


    @Test
    @Transactional
    public void getAllDpSourceTablesByRowSizeIsEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);

        // Get all the dpSourceTablesList where rowSize equals to DEFAULT_ROW_SIZE
        defaultDpSourceTablesShouldBeFound("rowSize.equals=" + DEFAULT_ROW_SIZE);

        // Get all the dpSourceTablesList where rowSize equals to UPDATED_ROW_SIZE
        defaultDpSourceTablesShouldNotBeFound("rowSize.equals=" + UPDATED_ROW_SIZE);
    }

    @Test
    @Transactional
    public void getAllDpSourceTablesByRowSizeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);

        // Get all the dpSourceTablesList where rowSize not equals to DEFAULT_ROW_SIZE
        defaultDpSourceTablesShouldNotBeFound("rowSize.notEquals=" + DEFAULT_ROW_SIZE);

        // Get all the dpSourceTablesList where rowSize not equals to UPDATED_ROW_SIZE
        defaultDpSourceTablesShouldBeFound("rowSize.notEquals=" + UPDATED_ROW_SIZE);
    }

    @Test
    @Transactional
    public void getAllDpSourceTablesByRowSizeIsInShouldWork() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);

        // Get all the dpSourceTablesList where rowSize in DEFAULT_ROW_SIZE or UPDATED_ROW_SIZE
        defaultDpSourceTablesShouldBeFound("rowSize.in=" + DEFAULT_ROW_SIZE + "," + UPDATED_ROW_SIZE);

        // Get all the dpSourceTablesList where rowSize equals to UPDATED_ROW_SIZE
        defaultDpSourceTablesShouldNotBeFound("rowSize.in=" + UPDATED_ROW_SIZE);
    }

    @Test
    @Transactional
    public void getAllDpSourceTablesByRowSizeIsNullOrNotNull() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);

        // Get all the dpSourceTablesList where rowSize is not null
        defaultDpSourceTablesShouldBeFound("rowSize.specified=true");

        // Get all the dpSourceTablesList where rowSize is null
        defaultDpSourceTablesShouldNotBeFound("rowSize.specified=false");
    }

    @Test
    @Transactional
    public void getAllDpSourceTablesByRowSizeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);

        // Get all the dpSourceTablesList where rowSize is greater than or equal to DEFAULT_ROW_SIZE
        defaultDpSourceTablesShouldBeFound("rowSize.greaterThanOrEqual=" + DEFAULT_ROW_SIZE);

        // Get all the dpSourceTablesList where rowSize is greater than or equal to UPDATED_ROW_SIZE
        defaultDpSourceTablesShouldNotBeFound("rowSize.greaterThanOrEqual=" + UPDATED_ROW_SIZE);
    }

    @Test
    @Transactional
    public void getAllDpSourceTablesByRowSizeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);

        // Get all the dpSourceTablesList where rowSize is less than or equal to DEFAULT_ROW_SIZE
        defaultDpSourceTablesShouldBeFound("rowSize.lessThanOrEqual=" + DEFAULT_ROW_SIZE);

        // Get all the dpSourceTablesList where rowSize is less than or equal to SMALLER_ROW_SIZE
        defaultDpSourceTablesShouldNotBeFound("rowSize.lessThanOrEqual=" + SMALLER_ROW_SIZE);
    }

    @Test
    @Transactional
    public void getAllDpSourceTablesByRowSizeIsLessThanSomething() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);

        // Get all the dpSourceTablesList where rowSize is less than DEFAULT_ROW_SIZE
        defaultDpSourceTablesShouldNotBeFound("rowSize.lessThan=" + DEFAULT_ROW_SIZE);

        // Get all the dpSourceTablesList where rowSize is less than UPDATED_ROW_SIZE
        defaultDpSourceTablesShouldBeFound("rowSize.lessThan=" + UPDATED_ROW_SIZE);
    }

    @Test
    @Transactional
    public void getAllDpSourceTablesByRowSizeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);

        // Get all the dpSourceTablesList where rowSize is greater than DEFAULT_ROW_SIZE
        defaultDpSourceTablesShouldNotBeFound("rowSize.greaterThan=" + DEFAULT_ROW_SIZE);

        // Get all the dpSourceTablesList where rowSize is greater than SMALLER_ROW_SIZE
        defaultDpSourceTablesShouldBeFound("rowSize.greaterThan=" + SMALLER_ROW_SIZE);
    }


    @Test
    @Transactional
    public void getAllDpSourceTablesByColumnsIsEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);

        // Get all the dpSourceTablesList where columns equals to DEFAULT_COLUMNS
        defaultDpSourceTablesShouldBeFound("columns.equals=" + DEFAULT_COLUMNS);

        // Get all the dpSourceTablesList where columns equals to UPDATED_COLUMNS
        defaultDpSourceTablesShouldNotBeFound("columns.equals=" + UPDATED_COLUMNS);
    }

    @Test
    @Transactional
    public void getAllDpSourceTablesByColumnsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);

        // Get all the dpSourceTablesList where columns not equals to DEFAULT_COLUMNS
        defaultDpSourceTablesShouldNotBeFound("columns.notEquals=" + DEFAULT_COLUMNS);

        // Get all the dpSourceTablesList where columns not equals to UPDATED_COLUMNS
        defaultDpSourceTablesShouldBeFound("columns.notEquals=" + UPDATED_COLUMNS);
    }

    @Test
    @Transactional
    public void getAllDpSourceTablesByColumnsIsInShouldWork() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);

        // Get all the dpSourceTablesList where columns in DEFAULT_COLUMNS or UPDATED_COLUMNS
        defaultDpSourceTablesShouldBeFound("columns.in=" + DEFAULT_COLUMNS + "," + UPDATED_COLUMNS);

        // Get all the dpSourceTablesList where columns equals to UPDATED_COLUMNS
        defaultDpSourceTablesShouldNotBeFound("columns.in=" + UPDATED_COLUMNS);
    }

    @Test
    @Transactional
    public void getAllDpSourceTablesByColumnsIsNullOrNotNull() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);

        // Get all the dpSourceTablesList where columns is not null
        defaultDpSourceTablesShouldBeFound("columns.specified=true");

        // Get all the dpSourceTablesList where columns is null
        defaultDpSourceTablesShouldNotBeFound("columns.specified=false");
    }

    @Test
    @Transactional
    public void getAllDpSourceTablesByColumnsIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);

        // Get all the dpSourceTablesList where columns is greater than or equal to DEFAULT_COLUMNS
        defaultDpSourceTablesShouldBeFound("columns.greaterThanOrEqual=" + DEFAULT_COLUMNS);

        // Get all the dpSourceTablesList where columns is greater than or equal to UPDATED_COLUMNS
        defaultDpSourceTablesShouldNotBeFound("columns.greaterThanOrEqual=" + UPDATED_COLUMNS);
    }

    @Test
    @Transactional
    public void getAllDpSourceTablesByColumnsIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);

        // Get all the dpSourceTablesList where columns is less than or equal to DEFAULT_COLUMNS
        defaultDpSourceTablesShouldBeFound("columns.lessThanOrEqual=" + DEFAULT_COLUMNS);

        // Get all the dpSourceTablesList where columns is less than or equal to SMALLER_COLUMNS
        defaultDpSourceTablesShouldNotBeFound("columns.lessThanOrEqual=" + SMALLER_COLUMNS);
    }

    @Test
    @Transactional
    public void getAllDpSourceTablesByColumnsIsLessThanSomething() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);

        // Get all the dpSourceTablesList where columns is less than DEFAULT_COLUMNS
        defaultDpSourceTablesShouldNotBeFound("columns.lessThan=" + DEFAULT_COLUMNS);

        // Get all the dpSourceTablesList where columns is less than UPDATED_COLUMNS
        defaultDpSourceTablesShouldBeFound("columns.lessThan=" + UPDATED_COLUMNS);
    }

    @Test
    @Transactional
    public void getAllDpSourceTablesByColumnsIsGreaterThanSomething() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);

        // Get all the dpSourceTablesList where columns is greater than DEFAULT_COLUMNS
        defaultDpSourceTablesShouldNotBeFound("columns.greaterThan=" + DEFAULT_COLUMNS);

        // Get all the dpSourceTablesList where columns is greater than SMALLER_COLUMNS
        defaultDpSourceTablesShouldBeFound("columns.greaterThan=" + SMALLER_COLUMNS);
    }


    @Test
    @Transactional
    public void getAllDpSourceTablesByHasTimestampIsEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);

        // Get all the dpSourceTablesList where hasTimestamp equals to DEFAULT_HAS_TIMESTAMP
        defaultDpSourceTablesShouldBeFound("hasTimestamp.equals=" + DEFAULT_HAS_TIMESTAMP);

        // Get all the dpSourceTablesList where hasTimestamp equals to UPDATED_HAS_TIMESTAMP
        defaultDpSourceTablesShouldNotBeFound("hasTimestamp.equals=" + UPDATED_HAS_TIMESTAMP);
    }

    @Test
    @Transactional
    public void getAllDpSourceTablesByHasTimestampIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);

        // Get all the dpSourceTablesList where hasTimestamp not equals to DEFAULT_HAS_TIMESTAMP
        defaultDpSourceTablesShouldNotBeFound("hasTimestamp.notEquals=" + DEFAULT_HAS_TIMESTAMP);

        // Get all the dpSourceTablesList where hasTimestamp not equals to UPDATED_HAS_TIMESTAMP
        defaultDpSourceTablesShouldBeFound("hasTimestamp.notEquals=" + UPDATED_HAS_TIMESTAMP);
    }

    @Test
    @Transactional
    public void getAllDpSourceTablesByHasTimestampIsInShouldWork() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);

        // Get all the dpSourceTablesList where hasTimestamp in DEFAULT_HAS_TIMESTAMP or UPDATED_HAS_TIMESTAMP
        defaultDpSourceTablesShouldBeFound("hasTimestamp.in=" + DEFAULT_HAS_TIMESTAMP + "," + UPDATED_HAS_TIMESTAMP);

        // Get all the dpSourceTablesList where hasTimestamp equals to UPDATED_HAS_TIMESTAMP
        defaultDpSourceTablesShouldNotBeFound("hasTimestamp.in=" + UPDATED_HAS_TIMESTAMP);
    }

    @Test
    @Transactional
    public void getAllDpSourceTablesByHasTimestampIsNullOrNotNull() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);

        // Get all the dpSourceTablesList where hasTimestamp is not null
        defaultDpSourceTablesShouldBeFound("hasTimestamp.specified=true");

        // Get all the dpSourceTablesList where hasTimestamp is null
        defaultDpSourceTablesShouldNotBeFound("hasTimestamp.specified=false");
    }

    @Test
    @Transactional
    public void getAllDpSourceTablesByTblIsEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);
        DsTables tbl = DsTablesResourceIT.createEntity(em);
        em.persist(tbl);
        em.flush();
        dpSourceTables.setTbl(tbl);
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);
        Long tblId = tbl.getId();

        // Get all the dpSourceTablesList where tbl equals to tblId
        defaultDpSourceTablesShouldBeFound("tblId.equals=" + tblId);

        // Get all the dpSourceTablesList where tbl equals to tblId + 1
        defaultDpSourceTablesShouldNotBeFound("tblId.equals=" + (tblId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDpSourceTablesShouldBeFound(String filter) throws Exception {
        restDpSourceTablesMockMvc.perform(get("/api/dp-source-tables?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dpSourceTables.getId().intValue())))
            .andExpect(jsonPath("$.[*].rows").value(hasItem(DEFAULT_ROWS.intValue())))
            .andExpect(jsonPath("$.[*].rowSize").value(hasItem(DEFAULT_ROW_SIZE)))
            .andExpect(jsonPath("$.[*].columns").value(hasItem(DEFAULT_COLUMNS)))
            .andExpect(jsonPath("$.[*].hasTimestamp").value(hasItem(DEFAULT_HAS_TIMESTAMP.booleanValue())));

        // Check, that the count call also returns 1
        restDpSourceTablesMockMvc.perform(get("/api/dp-source-tables/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDpSourceTablesShouldNotBeFound(String filter) throws Exception {
        restDpSourceTablesMockMvc.perform(get("/api/dp-source-tables?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDpSourceTablesMockMvc.perform(get("/api/dp-source-tables/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDpSourceTables() throws Exception {
        // Get the dpSourceTables
        restDpSourceTablesMockMvc.perform(get("/api/dp-source-tables/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDpSourceTables() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);

        int databaseSizeBeforeUpdate = dpSourceTablesRepository.findAll().size();

        // Update the dpSourceTables
        DpSourceTables updatedDpSourceTables = dpSourceTablesRepository.findById(dpSourceTables.getId()).get();
        // Disconnect from session so that the updates on updatedDpSourceTables are not directly saved in db
        em.detach(updatedDpSourceTables);
        updatedDpSourceTables
            .rows(UPDATED_ROWS)
            .rowSize(UPDATED_ROW_SIZE)
            .columns(UPDATED_COLUMNS)
            .hasTimestamp(UPDATED_HAS_TIMESTAMP);
        DpSourceTablesDTO dpSourceTablesDTO = dpSourceTablesMapper.toDto(updatedDpSourceTables);

        restDpSourceTablesMockMvc.perform(put("/api/dp-source-tables")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dpSourceTablesDTO)))
            .andExpect(status().isOk());

        // Validate the DpSourceTables in the database
        List<DpSourceTables> dpSourceTablesList = dpSourceTablesRepository.findAll();
        assertThat(dpSourceTablesList).hasSize(databaseSizeBeforeUpdate);
        DpSourceTables testDpSourceTables = dpSourceTablesList.get(dpSourceTablesList.size() - 1);
        assertThat(testDpSourceTables.getRows()).isEqualTo(UPDATED_ROWS);
        assertThat(testDpSourceTables.getRowSize()).isEqualTo(UPDATED_ROW_SIZE);
        assertThat(testDpSourceTables.getColumns()).isEqualTo(UPDATED_COLUMNS);
        assertThat(testDpSourceTables.isHasTimestamp()).isEqualTo(UPDATED_HAS_TIMESTAMP);
    }

    @Test
    @Transactional
    public void updateNonExistingDpSourceTables() throws Exception {
        int databaseSizeBeforeUpdate = dpSourceTablesRepository.findAll().size();

        // Create the DpSourceTables
        DpSourceTablesDTO dpSourceTablesDTO = dpSourceTablesMapper.toDto(dpSourceTables);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDpSourceTablesMockMvc.perform(put("/api/dp-source-tables")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dpSourceTablesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DpSourceTables in the database
        List<DpSourceTables> dpSourceTablesList = dpSourceTablesRepository.findAll();
        assertThat(dpSourceTablesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDpSourceTables() throws Exception {
        // Initialize the database
        dpSourceTablesRepository.saveAndFlush(dpSourceTables);

        int databaseSizeBeforeDelete = dpSourceTablesRepository.findAll().size();

        // Delete the dpSourceTables
        restDpSourceTablesMockMvc.perform(delete("/api/dp-source-tables/{id}", dpSourceTables.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DpSourceTables> dpSourceTablesList = dpSourceTablesRepository.findAll();
        assertThat(dpSourceTablesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
