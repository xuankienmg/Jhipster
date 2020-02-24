package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.DpSourceColumns;
import com.mycompany.myapp.domain.DsTables;
import com.mycompany.myapp.domain.DsColumns;
import com.mycompany.myapp.repository.DpSourceColumnsRepository;
import com.mycompany.myapp.service.DpSourceColumnsService;
import com.mycompany.myapp.service.dto.DpSourceColumnsDTO;
import com.mycompany.myapp.service.mapper.DpSourceColumnsMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.DpSourceColumnsCriteria;
import com.mycompany.myapp.service.DpSourceColumnsQueryService;

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
 * Integration tests for the {@link DpSourceColumnsResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class DpSourceColumnsResourceIT {

    private static final Long DEFAULT_UNIQUE_VALUES = 1L;
    private static final Long UPDATED_UNIQUE_VALUES = 2L;
    private static final Long SMALLER_UNIQUE_VALUES = 1L - 1L;

    private static final String DEFAULT_MIN_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_MIN_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_MAX_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_MAX_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_AVERAGE_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_AVERAGE_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_DP_SOURCE_COLUMNSCOL = "AAAAAAAAAA";
    private static final String UPDATED_DP_SOURCE_COLUMNSCOL = "BBBBBBBBBB";

    private static final Long DEFAULT_MAX_LENGTH = 1L;
    private static final Long UPDATED_MAX_LENGTH = 2L;
    private static final Long SMALLER_MAX_LENGTH = 1L - 1L;

    private static final Long DEFAULT_NULLS = 1L;
    private static final Long UPDATED_NULLS = 2L;
    private static final Long SMALLER_NULLS = 1L - 1L;

    @Autowired
    private DpSourceColumnsRepository dpSourceColumnsRepository;

    @Autowired
    private DpSourceColumnsMapper dpSourceColumnsMapper;

    @Autowired
    private DpSourceColumnsService dpSourceColumnsService;

    @Autowired
    private DpSourceColumnsQueryService dpSourceColumnsQueryService;

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

    private MockMvc restDpSourceColumnsMockMvc;

    private DpSourceColumns dpSourceColumns;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DpSourceColumnsResource dpSourceColumnsResource = new DpSourceColumnsResource(dpSourceColumnsService, dpSourceColumnsQueryService);
        this.restDpSourceColumnsMockMvc = MockMvcBuilders.standaloneSetup(dpSourceColumnsResource)
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
    public static DpSourceColumns createEntity(EntityManager em) {
        DpSourceColumns dpSourceColumns = new DpSourceColumns()
            .uniqueValues(DEFAULT_UNIQUE_VALUES)
            .minValue(DEFAULT_MIN_VALUE)
            .maxValue(DEFAULT_MAX_VALUE)
            .averageValue(DEFAULT_AVERAGE_VALUE)
            .dpSourceColumnscol(DEFAULT_DP_SOURCE_COLUMNSCOL)
            .maxLength(DEFAULT_MAX_LENGTH)
            .nulls(DEFAULT_NULLS);
        return dpSourceColumns;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DpSourceColumns createUpdatedEntity(EntityManager em) {
        DpSourceColumns dpSourceColumns = new DpSourceColumns()
            .uniqueValues(UPDATED_UNIQUE_VALUES)
            .minValue(UPDATED_MIN_VALUE)
            .maxValue(UPDATED_MAX_VALUE)
            .averageValue(UPDATED_AVERAGE_VALUE)
            .dpSourceColumnscol(UPDATED_DP_SOURCE_COLUMNSCOL)
            .maxLength(UPDATED_MAX_LENGTH)
            .nulls(UPDATED_NULLS);
        return dpSourceColumns;
    }

    @BeforeEach
    public void initTest() {
        dpSourceColumns = createEntity(em);
    }

    @Test
    @Transactional
    public void createDpSourceColumns() throws Exception {
        int databaseSizeBeforeCreate = dpSourceColumnsRepository.findAll().size();

        // Create the DpSourceColumns
        DpSourceColumnsDTO dpSourceColumnsDTO = dpSourceColumnsMapper.toDto(dpSourceColumns);
        restDpSourceColumnsMockMvc.perform(post("/api/dp-source-columns")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dpSourceColumnsDTO)))
            .andExpect(status().isCreated());

        // Validate the DpSourceColumns in the database
        List<DpSourceColumns> dpSourceColumnsList = dpSourceColumnsRepository.findAll();
        assertThat(dpSourceColumnsList).hasSize(databaseSizeBeforeCreate + 1);
        DpSourceColumns testDpSourceColumns = dpSourceColumnsList.get(dpSourceColumnsList.size() - 1);
        assertThat(testDpSourceColumns.getUniqueValues()).isEqualTo(DEFAULT_UNIQUE_VALUES);
        assertThat(testDpSourceColumns.getMinValue()).isEqualTo(DEFAULT_MIN_VALUE);
        assertThat(testDpSourceColumns.getMaxValue()).isEqualTo(DEFAULT_MAX_VALUE);
        assertThat(testDpSourceColumns.getAverageValue()).isEqualTo(DEFAULT_AVERAGE_VALUE);
        assertThat(testDpSourceColumns.getDpSourceColumnscol()).isEqualTo(DEFAULT_DP_SOURCE_COLUMNSCOL);
        assertThat(testDpSourceColumns.getMaxLength()).isEqualTo(DEFAULT_MAX_LENGTH);
        assertThat(testDpSourceColumns.getNulls()).isEqualTo(DEFAULT_NULLS);
    }

    @Test
    @Transactional
    public void createDpSourceColumnsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dpSourceColumnsRepository.findAll().size();

        // Create the DpSourceColumns with an existing ID
        dpSourceColumns.setId(1L);
        DpSourceColumnsDTO dpSourceColumnsDTO = dpSourceColumnsMapper.toDto(dpSourceColumns);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDpSourceColumnsMockMvc.perform(post("/api/dp-source-columns")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dpSourceColumnsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DpSourceColumns in the database
        List<DpSourceColumns> dpSourceColumnsList = dpSourceColumnsRepository.findAll();
        assertThat(dpSourceColumnsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDpSourceColumns() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList
        restDpSourceColumnsMockMvc.perform(get("/api/dp-source-columns?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dpSourceColumns.getId().intValue())))
            .andExpect(jsonPath("$.[*].uniqueValues").value(hasItem(DEFAULT_UNIQUE_VALUES.intValue())))
            .andExpect(jsonPath("$.[*].minValue").value(hasItem(DEFAULT_MIN_VALUE)))
            .andExpect(jsonPath("$.[*].maxValue").value(hasItem(DEFAULT_MAX_VALUE)))
            .andExpect(jsonPath("$.[*].averageValue").value(hasItem(DEFAULT_AVERAGE_VALUE)))
            .andExpect(jsonPath("$.[*].dpSourceColumnscol").value(hasItem(DEFAULT_DP_SOURCE_COLUMNSCOL)))
            .andExpect(jsonPath("$.[*].maxLength").value(hasItem(DEFAULT_MAX_LENGTH.intValue())))
            .andExpect(jsonPath("$.[*].nulls").value(hasItem(DEFAULT_NULLS.intValue())));
    }
    
    @Test
    @Transactional
    public void getDpSourceColumns() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get the dpSourceColumns
        restDpSourceColumnsMockMvc.perform(get("/api/dp-source-columns/{id}", dpSourceColumns.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dpSourceColumns.getId().intValue()))
            .andExpect(jsonPath("$.uniqueValues").value(DEFAULT_UNIQUE_VALUES.intValue()))
            .andExpect(jsonPath("$.minValue").value(DEFAULT_MIN_VALUE))
            .andExpect(jsonPath("$.maxValue").value(DEFAULT_MAX_VALUE))
            .andExpect(jsonPath("$.averageValue").value(DEFAULT_AVERAGE_VALUE))
            .andExpect(jsonPath("$.dpSourceColumnscol").value(DEFAULT_DP_SOURCE_COLUMNSCOL))
            .andExpect(jsonPath("$.maxLength").value(DEFAULT_MAX_LENGTH.intValue()))
            .andExpect(jsonPath("$.nulls").value(DEFAULT_NULLS.intValue()));
    }


    @Test
    @Transactional
    public void getDpSourceColumnsByIdFiltering() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        Long id = dpSourceColumns.getId();

        defaultDpSourceColumnsShouldBeFound("id.equals=" + id);
        defaultDpSourceColumnsShouldNotBeFound("id.notEquals=" + id);

        defaultDpSourceColumnsShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDpSourceColumnsShouldNotBeFound("id.greaterThan=" + id);

        defaultDpSourceColumnsShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDpSourceColumnsShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDpSourceColumnsByUniqueValuesIsEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where uniqueValues equals to DEFAULT_UNIQUE_VALUES
        defaultDpSourceColumnsShouldBeFound("uniqueValues.equals=" + DEFAULT_UNIQUE_VALUES);

        // Get all the dpSourceColumnsList where uniqueValues equals to UPDATED_UNIQUE_VALUES
        defaultDpSourceColumnsShouldNotBeFound("uniqueValues.equals=" + UPDATED_UNIQUE_VALUES);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByUniqueValuesIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where uniqueValues not equals to DEFAULT_UNIQUE_VALUES
        defaultDpSourceColumnsShouldNotBeFound("uniqueValues.notEquals=" + DEFAULT_UNIQUE_VALUES);

        // Get all the dpSourceColumnsList where uniqueValues not equals to UPDATED_UNIQUE_VALUES
        defaultDpSourceColumnsShouldBeFound("uniqueValues.notEquals=" + UPDATED_UNIQUE_VALUES);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByUniqueValuesIsInShouldWork() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where uniqueValues in DEFAULT_UNIQUE_VALUES or UPDATED_UNIQUE_VALUES
        defaultDpSourceColumnsShouldBeFound("uniqueValues.in=" + DEFAULT_UNIQUE_VALUES + "," + UPDATED_UNIQUE_VALUES);

        // Get all the dpSourceColumnsList where uniqueValues equals to UPDATED_UNIQUE_VALUES
        defaultDpSourceColumnsShouldNotBeFound("uniqueValues.in=" + UPDATED_UNIQUE_VALUES);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByUniqueValuesIsNullOrNotNull() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where uniqueValues is not null
        defaultDpSourceColumnsShouldBeFound("uniqueValues.specified=true");

        // Get all the dpSourceColumnsList where uniqueValues is null
        defaultDpSourceColumnsShouldNotBeFound("uniqueValues.specified=false");
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByUniqueValuesIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where uniqueValues is greater than or equal to DEFAULT_UNIQUE_VALUES
        defaultDpSourceColumnsShouldBeFound("uniqueValues.greaterThanOrEqual=" + DEFAULT_UNIQUE_VALUES);

        // Get all the dpSourceColumnsList where uniqueValues is greater than or equal to UPDATED_UNIQUE_VALUES
        defaultDpSourceColumnsShouldNotBeFound("uniqueValues.greaterThanOrEqual=" + UPDATED_UNIQUE_VALUES);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByUniqueValuesIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where uniqueValues is less than or equal to DEFAULT_UNIQUE_VALUES
        defaultDpSourceColumnsShouldBeFound("uniqueValues.lessThanOrEqual=" + DEFAULT_UNIQUE_VALUES);

        // Get all the dpSourceColumnsList where uniqueValues is less than or equal to SMALLER_UNIQUE_VALUES
        defaultDpSourceColumnsShouldNotBeFound("uniqueValues.lessThanOrEqual=" + SMALLER_UNIQUE_VALUES);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByUniqueValuesIsLessThanSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where uniqueValues is less than DEFAULT_UNIQUE_VALUES
        defaultDpSourceColumnsShouldNotBeFound("uniqueValues.lessThan=" + DEFAULT_UNIQUE_VALUES);

        // Get all the dpSourceColumnsList where uniqueValues is less than UPDATED_UNIQUE_VALUES
        defaultDpSourceColumnsShouldBeFound("uniqueValues.lessThan=" + UPDATED_UNIQUE_VALUES);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByUniqueValuesIsGreaterThanSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where uniqueValues is greater than DEFAULT_UNIQUE_VALUES
        defaultDpSourceColumnsShouldNotBeFound("uniqueValues.greaterThan=" + DEFAULT_UNIQUE_VALUES);

        // Get all the dpSourceColumnsList where uniqueValues is greater than SMALLER_UNIQUE_VALUES
        defaultDpSourceColumnsShouldBeFound("uniqueValues.greaterThan=" + SMALLER_UNIQUE_VALUES);
    }


    @Test
    @Transactional
    public void getAllDpSourceColumnsByMinValueIsEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where minValue equals to DEFAULT_MIN_VALUE
        defaultDpSourceColumnsShouldBeFound("minValue.equals=" + DEFAULT_MIN_VALUE);

        // Get all the dpSourceColumnsList where minValue equals to UPDATED_MIN_VALUE
        defaultDpSourceColumnsShouldNotBeFound("minValue.equals=" + UPDATED_MIN_VALUE);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByMinValueIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where minValue not equals to DEFAULT_MIN_VALUE
        defaultDpSourceColumnsShouldNotBeFound("minValue.notEquals=" + DEFAULT_MIN_VALUE);

        // Get all the dpSourceColumnsList where minValue not equals to UPDATED_MIN_VALUE
        defaultDpSourceColumnsShouldBeFound("minValue.notEquals=" + UPDATED_MIN_VALUE);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByMinValueIsInShouldWork() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where minValue in DEFAULT_MIN_VALUE or UPDATED_MIN_VALUE
        defaultDpSourceColumnsShouldBeFound("minValue.in=" + DEFAULT_MIN_VALUE + "," + UPDATED_MIN_VALUE);

        // Get all the dpSourceColumnsList where minValue equals to UPDATED_MIN_VALUE
        defaultDpSourceColumnsShouldNotBeFound("minValue.in=" + UPDATED_MIN_VALUE);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByMinValueIsNullOrNotNull() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where minValue is not null
        defaultDpSourceColumnsShouldBeFound("minValue.specified=true");

        // Get all the dpSourceColumnsList where minValue is null
        defaultDpSourceColumnsShouldNotBeFound("minValue.specified=false");
    }
                @Test
    @Transactional
    public void getAllDpSourceColumnsByMinValueContainsSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where minValue contains DEFAULT_MIN_VALUE
        defaultDpSourceColumnsShouldBeFound("minValue.contains=" + DEFAULT_MIN_VALUE);

        // Get all the dpSourceColumnsList where minValue contains UPDATED_MIN_VALUE
        defaultDpSourceColumnsShouldNotBeFound("minValue.contains=" + UPDATED_MIN_VALUE);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByMinValueNotContainsSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where minValue does not contain DEFAULT_MIN_VALUE
        defaultDpSourceColumnsShouldNotBeFound("minValue.doesNotContain=" + DEFAULT_MIN_VALUE);

        // Get all the dpSourceColumnsList where minValue does not contain UPDATED_MIN_VALUE
        defaultDpSourceColumnsShouldBeFound("minValue.doesNotContain=" + UPDATED_MIN_VALUE);
    }


    @Test
    @Transactional
    public void getAllDpSourceColumnsByMaxValueIsEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where maxValue equals to DEFAULT_MAX_VALUE
        defaultDpSourceColumnsShouldBeFound("maxValue.equals=" + DEFAULT_MAX_VALUE);

        // Get all the dpSourceColumnsList where maxValue equals to UPDATED_MAX_VALUE
        defaultDpSourceColumnsShouldNotBeFound("maxValue.equals=" + UPDATED_MAX_VALUE);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByMaxValueIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where maxValue not equals to DEFAULT_MAX_VALUE
        defaultDpSourceColumnsShouldNotBeFound("maxValue.notEquals=" + DEFAULT_MAX_VALUE);

        // Get all the dpSourceColumnsList where maxValue not equals to UPDATED_MAX_VALUE
        defaultDpSourceColumnsShouldBeFound("maxValue.notEquals=" + UPDATED_MAX_VALUE);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByMaxValueIsInShouldWork() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where maxValue in DEFAULT_MAX_VALUE or UPDATED_MAX_VALUE
        defaultDpSourceColumnsShouldBeFound("maxValue.in=" + DEFAULT_MAX_VALUE + "," + UPDATED_MAX_VALUE);

        // Get all the dpSourceColumnsList where maxValue equals to UPDATED_MAX_VALUE
        defaultDpSourceColumnsShouldNotBeFound("maxValue.in=" + UPDATED_MAX_VALUE);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByMaxValueIsNullOrNotNull() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where maxValue is not null
        defaultDpSourceColumnsShouldBeFound("maxValue.specified=true");

        // Get all the dpSourceColumnsList where maxValue is null
        defaultDpSourceColumnsShouldNotBeFound("maxValue.specified=false");
    }
                @Test
    @Transactional
    public void getAllDpSourceColumnsByMaxValueContainsSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where maxValue contains DEFAULT_MAX_VALUE
        defaultDpSourceColumnsShouldBeFound("maxValue.contains=" + DEFAULT_MAX_VALUE);

        // Get all the dpSourceColumnsList where maxValue contains UPDATED_MAX_VALUE
        defaultDpSourceColumnsShouldNotBeFound("maxValue.contains=" + UPDATED_MAX_VALUE);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByMaxValueNotContainsSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where maxValue does not contain DEFAULT_MAX_VALUE
        defaultDpSourceColumnsShouldNotBeFound("maxValue.doesNotContain=" + DEFAULT_MAX_VALUE);

        // Get all the dpSourceColumnsList where maxValue does not contain UPDATED_MAX_VALUE
        defaultDpSourceColumnsShouldBeFound("maxValue.doesNotContain=" + UPDATED_MAX_VALUE);
    }


    @Test
    @Transactional
    public void getAllDpSourceColumnsByAverageValueIsEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where averageValue equals to DEFAULT_AVERAGE_VALUE
        defaultDpSourceColumnsShouldBeFound("averageValue.equals=" + DEFAULT_AVERAGE_VALUE);

        // Get all the dpSourceColumnsList where averageValue equals to UPDATED_AVERAGE_VALUE
        defaultDpSourceColumnsShouldNotBeFound("averageValue.equals=" + UPDATED_AVERAGE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByAverageValueIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where averageValue not equals to DEFAULT_AVERAGE_VALUE
        defaultDpSourceColumnsShouldNotBeFound("averageValue.notEquals=" + DEFAULT_AVERAGE_VALUE);

        // Get all the dpSourceColumnsList where averageValue not equals to UPDATED_AVERAGE_VALUE
        defaultDpSourceColumnsShouldBeFound("averageValue.notEquals=" + UPDATED_AVERAGE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByAverageValueIsInShouldWork() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where averageValue in DEFAULT_AVERAGE_VALUE or UPDATED_AVERAGE_VALUE
        defaultDpSourceColumnsShouldBeFound("averageValue.in=" + DEFAULT_AVERAGE_VALUE + "," + UPDATED_AVERAGE_VALUE);

        // Get all the dpSourceColumnsList where averageValue equals to UPDATED_AVERAGE_VALUE
        defaultDpSourceColumnsShouldNotBeFound("averageValue.in=" + UPDATED_AVERAGE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByAverageValueIsNullOrNotNull() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where averageValue is not null
        defaultDpSourceColumnsShouldBeFound("averageValue.specified=true");

        // Get all the dpSourceColumnsList where averageValue is null
        defaultDpSourceColumnsShouldNotBeFound("averageValue.specified=false");
    }
                @Test
    @Transactional
    public void getAllDpSourceColumnsByAverageValueContainsSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where averageValue contains DEFAULT_AVERAGE_VALUE
        defaultDpSourceColumnsShouldBeFound("averageValue.contains=" + DEFAULT_AVERAGE_VALUE);

        // Get all the dpSourceColumnsList where averageValue contains UPDATED_AVERAGE_VALUE
        defaultDpSourceColumnsShouldNotBeFound("averageValue.contains=" + UPDATED_AVERAGE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByAverageValueNotContainsSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where averageValue does not contain DEFAULT_AVERAGE_VALUE
        defaultDpSourceColumnsShouldNotBeFound("averageValue.doesNotContain=" + DEFAULT_AVERAGE_VALUE);

        // Get all the dpSourceColumnsList where averageValue does not contain UPDATED_AVERAGE_VALUE
        defaultDpSourceColumnsShouldBeFound("averageValue.doesNotContain=" + UPDATED_AVERAGE_VALUE);
    }


    @Test
    @Transactional
    public void getAllDpSourceColumnsByDpSourceColumnscolIsEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where dpSourceColumnscol equals to DEFAULT_DP_SOURCE_COLUMNSCOL
        defaultDpSourceColumnsShouldBeFound("dpSourceColumnscol.equals=" + DEFAULT_DP_SOURCE_COLUMNSCOL);

        // Get all the dpSourceColumnsList where dpSourceColumnscol equals to UPDATED_DP_SOURCE_COLUMNSCOL
        defaultDpSourceColumnsShouldNotBeFound("dpSourceColumnscol.equals=" + UPDATED_DP_SOURCE_COLUMNSCOL);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByDpSourceColumnscolIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where dpSourceColumnscol not equals to DEFAULT_DP_SOURCE_COLUMNSCOL
        defaultDpSourceColumnsShouldNotBeFound("dpSourceColumnscol.notEquals=" + DEFAULT_DP_SOURCE_COLUMNSCOL);

        // Get all the dpSourceColumnsList where dpSourceColumnscol not equals to UPDATED_DP_SOURCE_COLUMNSCOL
        defaultDpSourceColumnsShouldBeFound("dpSourceColumnscol.notEquals=" + UPDATED_DP_SOURCE_COLUMNSCOL);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByDpSourceColumnscolIsInShouldWork() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where dpSourceColumnscol in DEFAULT_DP_SOURCE_COLUMNSCOL or UPDATED_DP_SOURCE_COLUMNSCOL
        defaultDpSourceColumnsShouldBeFound("dpSourceColumnscol.in=" + DEFAULT_DP_SOURCE_COLUMNSCOL + "," + UPDATED_DP_SOURCE_COLUMNSCOL);

        // Get all the dpSourceColumnsList where dpSourceColumnscol equals to UPDATED_DP_SOURCE_COLUMNSCOL
        defaultDpSourceColumnsShouldNotBeFound("dpSourceColumnscol.in=" + UPDATED_DP_SOURCE_COLUMNSCOL);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByDpSourceColumnscolIsNullOrNotNull() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where dpSourceColumnscol is not null
        defaultDpSourceColumnsShouldBeFound("dpSourceColumnscol.specified=true");

        // Get all the dpSourceColumnsList where dpSourceColumnscol is null
        defaultDpSourceColumnsShouldNotBeFound("dpSourceColumnscol.specified=false");
    }
                @Test
    @Transactional
    public void getAllDpSourceColumnsByDpSourceColumnscolContainsSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where dpSourceColumnscol contains DEFAULT_DP_SOURCE_COLUMNSCOL
        defaultDpSourceColumnsShouldBeFound("dpSourceColumnscol.contains=" + DEFAULT_DP_SOURCE_COLUMNSCOL);

        // Get all the dpSourceColumnsList where dpSourceColumnscol contains UPDATED_DP_SOURCE_COLUMNSCOL
        defaultDpSourceColumnsShouldNotBeFound("dpSourceColumnscol.contains=" + UPDATED_DP_SOURCE_COLUMNSCOL);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByDpSourceColumnscolNotContainsSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where dpSourceColumnscol does not contain DEFAULT_DP_SOURCE_COLUMNSCOL
        defaultDpSourceColumnsShouldNotBeFound("dpSourceColumnscol.doesNotContain=" + DEFAULT_DP_SOURCE_COLUMNSCOL);

        // Get all the dpSourceColumnsList where dpSourceColumnscol does not contain UPDATED_DP_SOURCE_COLUMNSCOL
        defaultDpSourceColumnsShouldBeFound("dpSourceColumnscol.doesNotContain=" + UPDATED_DP_SOURCE_COLUMNSCOL);
    }


    @Test
    @Transactional
    public void getAllDpSourceColumnsByMaxLengthIsEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where maxLength equals to DEFAULT_MAX_LENGTH
        defaultDpSourceColumnsShouldBeFound("maxLength.equals=" + DEFAULT_MAX_LENGTH);

        // Get all the dpSourceColumnsList where maxLength equals to UPDATED_MAX_LENGTH
        defaultDpSourceColumnsShouldNotBeFound("maxLength.equals=" + UPDATED_MAX_LENGTH);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByMaxLengthIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where maxLength not equals to DEFAULT_MAX_LENGTH
        defaultDpSourceColumnsShouldNotBeFound("maxLength.notEquals=" + DEFAULT_MAX_LENGTH);

        // Get all the dpSourceColumnsList where maxLength not equals to UPDATED_MAX_LENGTH
        defaultDpSourceColumnsShouldBeFound("maxLength.notEquals=" + UPDATED_MAX_LENGTH);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByMaxLengthIsInShouldWork() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where maxLength in DEFAULT_MAX_LENGTH or UPDATED_MAX_LENGTH
        defaultDpSourceColumnsShouldBeFound("maxLength.in=" + DEFAULT_MAX_LENGTH + "," + UPDATED_MAX_LENGTH);

        // Get all the dpSourceColumnsList where maxLength equals to UPDATED_MAX_LENGTH
        defaultDpSourceColumnsShouldNotBeFound("maxLength.in=" + UPDATED_MAX_LENGTH);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByMaxLengthIsNullOrNotNull() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where maxLength is not null
        defaultDpSourceColumnsShouldBeFound("maxLength.specified=true");

        // Get all the dpSourceColumnsList where maxLength is null
        defaultDpSourceColumnsShouldNotBeFound("maxLength.specified=false");
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByMaxLengthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where maxLength is greater than or equal to DEFAULT_MAX_LENGTH
        defaultDpSourceColumnsShouldBeFound("maxLength.greaterThanOrEqual=" + DEFAULT_MAX_LENGTH);

        // Get all the dpSourceColumnsList where maxLength is greater than or equal to UPDATED_MAX_LENGTH
        defaultDpSourceColumnsShouldNotBeFound("maxLength.greaterThanOrEqual=" + UPDATED_MAX_LENGTH);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByMaxLengthIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where maxLength is less than or equal to DEFAULT_MAX_LENGTH
        defaultDpSourceColumnsShouldBeFound("maxLength.lessThanOrEqual=" + DEFAULT_MAX_LENGTH);

        // Get all the dpSourceColumnsList where maxLength is less than or equal to SMALLER_MAX_LENGTH
        defaultDpSourceColumnsShouldNotBeFound("maxLength.lessThanOrEqual=" + SMALLER_MAX_LENGTH);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByMaxLengthIsLessThanSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where maxLength is less than DEFAULT_MAX_LENGTH
        defaultDpSourceColumnsShouldNotBeFound("maxLength.lessThan=" + DEFAULT_MAX_LENGTH);

        // Get all the dpSourceColumnsList where maxLength is less than UPDATED_MAX_LENGTH
        defaultDpSourceColumnsShouldBeFound("maxLength.lessThan=" + UPDATED_MAX_LENGTH);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByMaxLengthIsGreaterThanSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where maxLength is greater than DEFAULT_MAX_LENGTH
        defaultDpSourceColumnsShouldNotBeFound("maxLength.greaterThan=" + DEFAULT_MAX_LENGTH);

        // Get all the dpSourceColumnsList where maxLength is greater than SMALLER_MAX_LENGTH
        defaultDpSourceColumnsShouldBeFound("maxLength.greaterThan=" + SMALLER_MAX_LENGTH);
    }


    @Test
    @Transactional
    public void getAllDpSourceColumnsByNullsIsEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where nulls equals to DEFAULT_NULLS
        defaultDpSourceColumnsShouldBeFound("nulls.equals=" + DEFAULT_NULLS);

        // Get all the dpSourceColumnsList where nulls equals to UPDATED_NULLS
        defaultDpSourceColumnsShouldNotBeFound("nulls.equals=" + UPDATED_NULLS);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByNullsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where nulls not equals to DEFAULT_NULLS
        defaultDpSourceColumnsShouldNotBeFound("nulls.notEquals=" + DEFAULT_NULLS);

        // Get all the dpSourceColumnsList where nulls not equals to UPDATED_NULLS
        defaultDpSourceColumnsShouldBeFound("nulls.notEquals=" + UPDATED_NULLS);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByNullsIsInShouldWork() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where nulls in DEFAULT_NULLS or UPDATED_NULLS
        defaultDpSourceColumnsShouldBeFound("nulls.in=" + DEFAULT_NULLS + "," + UPDATED_NULLS);

        // Get all the dpSourceColumnsList where nulls equals to UPDATED_NULLS
        defaultDpSourceColumnsShouldNotBeFound("nulls.in=" + UPDATED_NULLS);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByNullsIsNullOrNotNull() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where nulls is not null
        defaultDpSourceColumnsShouldBeFound("nulls.specified=true");

        // Get all the dpSourceColumnsList where nulls is null
        defaultDpSourceColumnsShouldNotBeFound("nulls.specified=false");
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByNullsIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where nulls is greater than or equal to DEFAULT_NULLS
        defaultDpSourceColumnsShouldBeFound("nulls.greaterThanOrEqual=" + DEFAULT_NULLS);

        // Get all the dpSourceColumnsList where nulls is greater than or equal to UPDATED_NULLS
        defaultDpSourceColumnsShouldNotBeFound("nulls.greaterThanOrEqual=" + UPDATED_NULLS);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByNullsIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where nulls is less than or equal to DEFAULT_NULLS
        defaultDpSourceColumnsShouldBeFound("nulls.lessThanOrEqual=" + DEFAULT_NULLS);

        // Get all the dpSourceColumnsList where nulls is less than or equal to SMALLER_NULLS
        defaultDpSourceColumnsShouldNotBeFound("nulls.lessThanOrEqual=" + SMALLER_NULLS);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByNullsIsLessThanSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where nulls is less than DEFAULT_NULLS
        defaultDpSourceColumnsShouldNotBeFound("nulls.lessThan=" + DEFAULT_NULLS);

        // Get all the dpSourceColumnsList where nulls is less than UPDATED_NULLS
        defaultDpSourceColumnsShouldBeFound("nulls.lessThan=" + UPDATED_NULLS);
    }

    @Test
    @Transactional
    public void getAllDpSourceColumnsByNullsIsGreaterThanSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        // Get all the dpSourceColumnsList where nulls is greater than DEFAULT_NULLS
        defaultDpSourceColumnsShouldNotBeFound("nulls.greaterThan=" + DEFAULT_NULLS);

        // Get all the dpSourceColumnsList where nulls is greater than SMALLER_NULLS
        defaultDpSourceColumnsShouldBeFound("nulls.greaterThan=" + SMALLER_NULLS);
    }


    @Test
    @Transactional
    public void getAllDpSourceColumnsByTblIsEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);
        DsTables tbl = DsTablesResourceIT.createEntity(em);
        em.persist(tbl);
        em.flush();
        dpSourceColumns.setTbl(tbl);
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);
        Long tblId = tbl.getId();

        // Get all the dpSourceColumnsList where tbl equals to tblId
        defaultDpSourceColumnsShouldBeFound("tblId.equals=" + tblId);

        // Get all the dpSourceColumnsList where tbl equals to tblId + 1
        defaultDpSourceColumnsShouldNotBeFound("tblId.equals=" + (tblId + 1));
    }


    @Test
    @Transactional
    public void getAllDpSourceColumnsByColIsEqualToSomething() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);
        DsColumns col = DsColumnsResourceIT.createEntity(em);
        em.persist(col);
        em.flush();
        dpSourceColumns.setCol(col);
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);
        Long colId = col.getId();

        // Get all the dpSourceColumnsList where col equals to colId
        defaultDpSourceColumnsShouldBeFound("colId.equals=" + colId);

        // Get all the dpSourceColumnsList where col equals to colId + 1
        defaultDpSourceColumnsShouldNotBeFound("colId.equals=" + (colId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDpSourceColumnsShouldBeFound(String filter) throws Exception {
        restDpSourceColumnsMockMvc.perform(get("/api/dp-source-columns?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dpSourceColumns.getId().intValue())))
            .andExpect(jsonPath("$.[*].uniqueValues").value(hasItem(DEFAULT_UNIQUE_VALUES.intValue())))
            .andExpect(jsonPath("$.[*].minValue").value(hasItem(DEFAULT_MIN_VALUE)))
            .andExpect(jsonPath("$.[*].maxValue").value(hasItem(DEFAULT_MAX_VALUE)))
            .andExpect(jsonPath("$.[*].averageValue").value(hasItem(DEFAULT_AVERAGE_VALUE)))
            .andExpect(jsonPath("$.[*].dpSourceColumnscol").value(hasItem(DEFAULT_DP_SOURCE_COLUMNSCOL)))
            .andExpect(jsonPath("$.[*].maxLength").value(hasItem(DEFAULT_MAX_LENGTH.intValue())))
            .andExpect(jsonPath("$.[*].nulls").value(hasItem(DEFAULT_NULLS.intValue())));

        // Check, that the count call also returns 1
        restDpSourceColumnsMockMvc.perform(get("/api/dp-source-columns/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDpSourceColumnsShouldNotBeFound(String filter) throws Exception {
        restDpSourceColumnsMockMvc.perform(get("/api/dp-source-columns?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDpSourceColumnsMockMvc.perform(get("/api/dp-source-columns/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDpSourceColumns() throws Exception {
        // Get the dpSourceColumns
        restDpSourceColumnsMockMvc.perform(get("/api/dp-source-columns/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDpSourceColumns() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        int databaseSizeBeforeUpdate = dpSourceColumnsRepository.findAll().size();

        // Update the dpSourceColumns
        DpSourceColumns updatedDpSourceColumns = dpSourceColumnsRepository.findById(dpSourceColumns.getId()).get();
        // Disconnect from session so that the updates on updatedDpSourceColumns are not directly saved in db
        em.detach(updatedDpSourceColumns);
        updatedDpSourceColumns
            .uniqueValues(UPDATED_UNIQUE_VALUES)
            .minValue(UPDATED_MIN_VALUE)
            .maxValue(UPDATED_MAX_VALUE)
            .averageValue(UPDATED_AVERAGE_VALUE)
            .dpSourceColumnscol(UPDATED_DP_SOURCE_COLUMNSCOL)
            .maxLength(UPDATED_MAX_LENGTH)
            .nulls(UPDATED_NULLS);
        DpSourceColumnsDTO dpSourceColumnsDTO = dpSourceColumnsMapper.toDto(updatedDpSourceColumns);

        restDpSourceColumnsMockMvc.perform(put("/api/dp-source-columns")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dpSourceColumnsDTO)))
            .andExpect(status().isOk());

        // Validate the DpSourceColumns in the database
        List<DpSourceColumns> dpSourceColumnsList = dpSourceColumnsRepository.findAll();
        assertThat(dpSourceColumnsList).hasSize(databaseSizeBeforeUpdate);
        DpSourceColumns testDpSourceColumns = dpSourceColumnsList.get(dpSourceColumnsList.size() - 1);
        assertThat(testDpSourceColumns.getUniqueValues()).isEqualTo(UPDATED_UNIQUE_VALUES);
        assertThat(testDpSourceColumns.getMinValue()).isEqualTo(UPDATED_MIN_VALUE);
        assertThat(testDpSourceColumns.getMaxValue()).isEqualTo(UPDATED_MAX_VALUE);
        assertThat(testDpSourceColumns.getAverageValue()).isEqualTo(UPDATED_AVERAGE_VALUE);
        assertThat(testDpSourceColumns.getDpSourceColumnscol()).isEqualTo(UPDATED_DP_SOURCE_COLUMNSCOL);
        assertThat(testDpSourceColumns.getMaxLength()).isEqualTo(UPDATED_MAX_LENGTH);
        assertThat(testDpSourceColumns.getNulls()).isEqualTo(UPDATED_NULLS);
    }

    @Test
    @Transactional
    public void updateNonExistingDpSourceColumns() throws Exception {
        int databaseSizeBeforeUpdate = dpSourceColumnsRepository.findAll().size();

        // Create the DpSourceColumns
        DpSourceColumnsDTO dpSourceColumnsDTO = dpSourceColumnsMapper.toDto(dpSourceColumns);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDpSourceColumnsMockMvc.perform(put("/api/dp-source-columns")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dpSourceColumnsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DpSourceColumns in the database
        List<DpSourceColumns> dpSourceColumnsList = dpSourceColumnsRepository.findAll();
        assertThat(dpSourceColumnsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDpSourceColumns() throws Exception {
        // Initialize the database
        dpSourceColumnsRepository.saveAndFlush(dpSourceColumns);

        int databaseSizeBeforeDelete = dpSourceColumnsRepository.findAll().size();

        // Delete the dpSourceColumns
        restDpSourceColumnsMockMvc.perform(delete("/api/dp-source-columns/{id}", dpSourceColumns.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DpSourceColumns> dpSourceColumnsList = dpSourceColumnsRepository.findAll();
        assertThat(dpSourceColumnsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
