package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.DataFlows;
import com.mycompany.myapp.domain.EtlStatus;
import com.mycompany.myapp.domain.EtlPackages;
import com.mycompany.myapp.repository.DataFlowsRepository;
import com.mycompany.myapp.service.DataFlowsService;
import com.mycompany.myapp.service.dto.DataFlowsDTO;
import com.mycompany.myapp.service.mapper.DataFlowsMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.DataFlowsCriteria;
import com.mycompany.myapp.service.DataFlowsQueryService;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DataFlowsResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class DataFlowsResourceIT {

    private static final String DEFAULT_FLOW_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FLOW_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FLOW_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_FLOW_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE = "BBBBBBBBBB";

    private static final String DEFAULT_DESTINATION = "AAAAAAAAAA";
    private static final String UPDATED_DESTINATION = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSFORMATION = "AAAAAAAAAA";
    private static final String UPDATED_TRANSFORMATION = "BBBBBBBBBB";

    private static final Instant DEFAULT_L_SET = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_L_SET = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_C_ET = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_C_ET = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private DataFlowsRepository dataFlowsRepository;

    @Autowired
    private DataFlowsMapper dataFlowsMapper;

    @Autowired
    private DataFlowsService dataFlowsService;

    @Autowired
    private DataFlowsQueryService dataFlowsQueryService;

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

    private MockMvc restDataFlowsMockMvc;

    private DataFlows dataFlows;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DataFlowsResource dataFlowsResource = new DataFlowsResource(dataFlowsService, dataFlowsQueryService);
        this.restDataFlowsMockMvc = MockMvcBuilders.standaloneSetup(dataFlowsResource)
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
    public static DataFlows createEntity(EntityManager em) {
        DataFlows dataFlows = new DataFlows()
            .flowName(DEFAULT_FLOW_NAME)
            .flowDescription(DEFAULT_FLOW_DESCRIPTION)
            .source(DEFAULT_SOURCE)
            .destination(DEFAULT_DESTINATION)
            .transformation(DEFAULT_TRANSFORMATION)
            .lSET(DEFAULT_L_SET)
            .cET(DEFAULT_C_ET);
        return dataFlows;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DataFlows createUpdatedEntity(EntityManager em) {
        DataFlows dataFlows = new DataFlows()
            .flowName(UPDATED_FLOW_NAME)
            .flowDescription(UPDATED_FLOW_DESCRIPTION)
            .source(UPDATED_SOURCE)
            .destination(UPDATED_DESTINATION)
            .transformation(UPDATED_TRANSFORMATION)
            .lSET(UPDATED_L_SET)
            .cET(UPDATED_C_ET);
        return dataFlows;
    }

    @BeforeEach
    public void initTest() {
        dataFlows = createEntity(em);
    }

    @Test
    @Transactional
    public void createDataFlows() throws Exception {
        int databaseSizeBeforeCreate = dataFlowsRepository.findAll().size();

        // Create the DataFlows
        DataFlowsDTO dataFlowsDTO = dataFlowsMapper.toDto(dataFlows);
        restDataFlowsMockMvc.perform(post("/api/data-flows")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataFlowsDTO)))
            .andExpect(status().isCreated());

        // Validate the DataFlows in the database
        List<DataFlows> dataFlowsList = dataFlowsRepository.findAll();
        assertThat(dataFlowsList).hasSize(databaseSizeBeforeCreate + 1);
        DataFlows testDataFlows = dataFlowsList.get(dataFlowsList.size() - 1);
        assertThat(testDataFlows.getFlowName()).isEqualTo(DEFAULT_FLOW_NAME);
        assertThat(testDataFlows.getFlowDescription()).isEqualTo(DEFAULT_FLOW_DESCRIPTION);
        assertThat(testDataFlows.getSource()).isEqualTo(DEFAULT_SOURCE);
        assertThat(testDataFlows.getDestination()).isEqualTo(DEFAULT_DESTINATION);
        assertThat(testDataFlows.getTransformation()).isEqualTo(DEFAULT_TRANSFORMATION);
        assertThat(testDataFlows.getlSET()).isEqualTo(DEFAULT_L_SET);
        assertThat(testDataFlows.getcET()).isEqualTo(DEFAULT_C_ET);
    }

    @Test
    @Transactional
    public void createDataFlowsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dataFlowsRepository.findAll().size();

        // Create the DataFlows with an existing ID
        dataFlows.setId(1L);
        DataFlowsDTO dataFlowsDTO = dataFlowsMapper.toDto(dataFlows);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDataFlowsMockMvc.perform(post("/api/data-flows")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataFlowsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DataFlows in the database
        List<DataFlows> dataFlowsList = dataFlowsRepository.findAll();
        assertThat(dataFlowsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDataFlows() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList
        restDataFlowsMockMvc.perform(get("/api/data-flows?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dataFlows.getId().intValue())))
            .andExpect(jsonPath("$.[*].flowName").value(hasItem(DEFAULT_FLOW_NAME)))
            .andExpect(jsonPath("$.[*].flowDescription").value(hasItem(DEFAULT_FLOW_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].source").value(hasItem(DEFAULT_SOURCE)))
            .andExpect(jsonPath("$.[*].destination").value(hasItem(DEFAULT_DESTINATION)))
            .andExpect(jsonPath("$.[*].transformation").value(hasItem(DEFAULT_TRANSFORMATION)))
            .andExpect(jsonPath("$.[*].lSET").value(hasItem(DEFAULT_L_SET.toString())))
            .andExpect(jsonPath("$.[*].cET").value(hasItem(DEFAULT_C_ET.toString())));
    }
    
    @Test
    @Transactional
    public void getDataFlows() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get the dataFlows
        restDataFlowsMockMvc.perform(get("/api/data-flows/{id}", dataFlows.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dataFlows.getId().intValue()))
            .andExpect(jsonPath("$.flowName").value(DEFAULT_FLOW_NAME))
            .andExpect(jsonPath("$.flowDescription").value(DEFAULT_FLOW_DESCRIPTION))
            .andExpect(jsonPath("$.source").value(DEFAULT_SOURCE))
            .andExpect(jsonPath("$.destination").value(DEFAULT_DESTINATION))
            .andExpect(jsonPath("$.transformation").value(DEFAULT_TRANSFORMATION))
            .andExpect(jsonPath("$.lSET").value(DEFAULT_L_SET.toString()))
            .andExpect(jsonPath("$.cET").value(DEFAULT_C_ET.toString()));
    }


    @Test
    @Transactional
    public void getDataFlowsByIdFiltering() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        Long id = dataFlows.getId();

        defaultDataFlowsShouldBeFound("id.equals=" + id);
        defaultDataFlowsShouldNotBeFound("id.notEquals=" + id);

        defaultDataFlowsShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDataFlowsShouldNotBeFound("id.greaterThan=" + id);

        defaultDataFlowsShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDataFlowsShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDataFlowsByFlowNameIsEqualToSomething() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where flowName equals to DEFAULT_FLOW_NAME
        defaultDataFlowsShouldBeFound("flowName.equals=" + DEFAULT_FLOW_NAME);

        // Get all the dataFlowsList where flowName equals to UPDATED_FLOW_NAME
        defaultDataFlowsShouldNotBeFound("flowName.equals=" + UPDATED_FLOW_NAME);
    }

    @Test
    @Transactional
    public void getAllDataFlowsByFlowNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where flowName not equals to DEFAULT_FLOW_NAME
        defaultDataFlowsShouldNotBeFound("flowName.notEquals=" + DEFAULT_FLOW_NAME);

        // Get all the dataFlowsList where flowName not equals to UPDATED_FLOW_NAME
        defaultDataFlowsShouldBeFound("flowName.notEquals=" + UPDATED_FLOW_NAME);
    }

    @Test
    @Transactional
    public void getAllDataFlowsByFlowNameIsInShouldWork() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where flowName in DEFAULT_FLOW_NAME or UPDATED_FLOW_NAME
        defaultDataFlowsShouldBeFound("flowName.in=" + DEFAULT_FLOW_NAME + "," + UPDATED_FLOW_NAME);

        // Get all the dataFlowsList where flowName equals to UPDATED_FLOW_NAME
        defaultDataFlowsShouldNotBeFound("flowName.in=" + UPDATED_FLOW_NAME);
    }

    @Test
    @Transactional
    public void getAllDataFlowsByFlowNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where flowName is not null
        defaultDataFlowsShouldBeFound("flowName.specified=true");

        // Get all the dataFlowsList where flowName is null
        defaultDataFlowsShouldNotBeFound("flowName.specified=false");
    }
                @Test
    @Transactional
    public void getAllDataFlowsByFlowNameContainsSomething() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where flowName contains DEFAULT_FLOW_NAME
        defaultDataFlowsShouldBeFound("flowName.contains=" + DEFAULT_FLOW_NAME);

        // Get all the dataFlowsList where flowName contains UPDATED_FLOW_NAME
        defaultDataFlowsShouldNotBeFound("flowName.contains=" + UPDATED_FLOW_NAME);
    }

    @Test
    @Transactional
    public void getAllDataFlowsByFlowNameNotContainsSomething() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where flowName does not contain DEFAULT_FLOW_NAME
        defaultDataFlowsShouldNotBeFound("flowName.doesNotContain=" + DEFAULT_FLOW_NAME);

        // Get all the dataFlowsList where flowName does not contain UPDATED_FLOW_NAME
        defaultDataFlowsShouldBeFound("flowName.doesNotContain=" + UPDATED_FLOW_NAME);
    }


    @Test
    @Transactional
    public void getAllDataFlowsByFlowDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where flowDescription equals to DEFAULT_FLOW_DESCRIPTION
        defaultDataFlowsShouldBeFound("flowDescription.equals=" + DEFAULT_FLOW_DESCRIPTION);

        // Get all the dataFlowsList where flowDescription equals to UPDATED_FLOW_DESCRIPTION
        defaultDataFlowsShouldNotBeFound("flowDescription.equals=" + UPDATED_FLOW_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDataFlowsByFlowDescriptionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where flowDescription not equals to DEFAULT_FLOW_DESCRIPTION
        defaultDataFlowsShouldNotBeFound("flowDescription.notEquals=" + DEFAULT_FLOW_DESCRIPTION);

        // Get all the dataFlowsList where flowDescription not equals to UPDATED_FLOW_DESCRIPTION
        defaultDataFlowsShouldBeFound("flowDescription.notEquals=" + UPDATED_FLOW_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDataFlowsByFlowDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where flowDescription in DEFAULT_FLOW_DESCRIPTION or UPDATED_FLOW_DESCRIPTION
        defaultDataFlowsShouldBeFound("flowDescription.in=" + DEFAULT_FLOW_DESCRIPTION + "," + UPDATED_FLOW_DESCRIPTION);

        // Get all the dataFlowsList where flowDescription equals to UPDATED_FLOW_DESCRIPTION
        defaultDataFlowsShouldNotBeFound("flowDescription.in=" + UPDATED_FLOW_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDataFlowsByFlowDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where flowDescription is not null
        defaultDataFlowsShouldBeFound("flowDescription.specified=true");

        // Get all the dataFlowsList where flowDescription is null
        defaultDataFlowsShouldNotBeFound("flowDescription.specified=false");
    }
                @Test
    @Transactional
    public void getAllDataFlowsByFlowDescriptionContainsSomething() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where flowDescription contains DEFAULT_FLOW_DESCRIPTION
        defaultDataFlowsShouldBeFound("flowDescription.contains=" + DEFAULT_FLOW_DESCRIPTION);

        // Get all the dataFlowsList where flowDescription contains UPDATED_FLOW_DESCRIPTION
        defaultDataFlowsShouldNotBeFound("flowDescription.contains=" + UPDATED_FLOW_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDataFlowsByFlowDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where flowDescription does not contain DEFAULT_FLOW_DESCRIPTION
        defaultDataFlowsShouldNotBeFound("flowDescription.doesNotContain=" + DEFAULT_FLOW_DESCRIPTION);

        // Get all the dataFlowsList where flowDescription does not contain UPDATED_FLOW_DESCRIPTION
        defaultDataFlowsShouldBeFound("flowDescription.doesNotContain=" + UPDATED_FLOW_DESCRIPTION);
    }


    @Test
    @Transactional
    public void getAllDataFlowsBySourceIsEqualToSomething() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where source equals to DEFAULT_SOURCE
        defaultDataFlowsShouldBeFound("source.equals=" + DEFAULT_SOURCE);

        // Get all the dataFlowsList where source equals to UPDATED_SOURCE
        defaultDataFlowsShouldNotBeFound("source.equals=" + UPDATED_SOURCE);
    }

    @Test
    @Transactional
    public void getAllDataFlowsBySourceIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where source not equals to DEFAULT_SOURCE
        defaultDataFlowsShouldNotBeFound("source.notEquals=" + DEFAULT_SOURCE);

        // Get all the dataFlowsList where source not equals to UPDATED_SOURCE
        defaultDataFlowsShouldBeFound("source.notEquals=" + UPDATED_SOURCE);
    }

    @Test
    @Transactional
    public void getAllDataFlowsBySourceIsInShouldWork() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where source in DEFAULT_SOURCE or UPDATED_SOURCE
        defaultDataFlowsShouldBeFound("source.in=" + DEFAULT_SOURCE + "," + UPDATED_SOURCE);

        // Get all the dataFlowsList where source equals to UPDATED_SOURCE
        defaultDataFlowsShouldNotBeFound("source.in=" + UPDATED_SOURCE);
    }

    @Test
    @Transactional
    public void getAllDataFlowsBySourceIsNullOrNotNull() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where source is not null
        defaultDataFlowsShouldBeFound("source.specified=true");

        // Get all the dataFlowsList where source is null
        defaultDataFlowsShouldNotBeFound("source.specified=false");
    }
                @Test
    @Transactional
    public void getAllDataFlowsBySourceContainsSomething() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where source contains DEFAULT_SOURCE
        defaultDataFlowsShouldBeFound("source.contains=" + DEFAULT_SOURCE);

        // Get all the dataFlowsList where source contains UPDATED_SOURCE
        defaultDataFlowsShouldNotBeFound("source.contains=" + UPDATED_SOURCE);
    }

    @Test
    @Transactional
    public void getAllDataFlowsBySourceNotContainsSomething() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where source does not contain DEFAULT_SOURCE
        defaultDataFlowsShouldNotBeFound("source.doesNotContain=" + DEFAULT_SOURCE);

        // Get all the dataFlowsList where source does not contain UPDATED_SOURCE
        defaultDataFlowsShouldBeFound("source.doesNotContain=" + UPDATED_SOURCE);
    }


    @Test
    @Transactional
    public void getAllDataFlowsByDestinationIsEqualToSomething() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where destination equals to DEFAULT_DESTINATION
        defaultDataFlowsShouldBeFound("destination.equals=" + DEFAULT_DESTINATION);

        // Get all the dataFlowsList where destination equals to UPDATED_DESTINATION
        defaultDataFlowsShouldNotBeFound("destination.equals=" + UPDATED_DESTINATION);
    }

    @Test
    @Transactional
    public void getAllDataFlowsByDestinationIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where destination not equals to DEFAULT_DESTINATION
        defaultDataFlowsShouldNotBeFound("destination.notEquals=" + DEFAULT_DESTINATION);

        // Get all the dataFlowsList where destination not equals to UPDATED_DESTINATION
        defaultDataFlowsShouldBeFound("destination.notEquals=" + UPDATED_DESTINATION);
    }

    @Test
    @Transactional
    public void getAllDataFlowsByDestinationIsInShouldWork() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where destination in DEFAULT_DESTINATION or UPDATED_DESTINATION
        defaultDataFlowsShouldBeFound("destination.in=" + DEFAULT_DESTINATION + "," + UPDATED_DESTINATION);

        // Get all the dataFlowsList where destination equals to UPDATED_DESTINATION
        defaultDataFlowsShouldNotBeFound("destination.in=" + UPDATED_DESTINATION);
    }

    @Test
    @Transactional
    public void getAllDataFlowsByDestinationIsNullOrNotNull() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where destination is not null
        defaultDataFlowsShouldBeFound("destination.specified=true");

        // Get all the dataFlowsList where destination is null
        defaultDataFlowsShouldNotBeFound("destination.specified=false");
    }
                @Test
    @Transactional
    public void getAllDataFlowsByDestinationContainsSomething() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where destination contains DEFAULT_DESTINATION
        defaultDataFlowsShouldBeFound("destination.contains=" + DEFAULT_DESTINATION);

        // Get all the dataFlowsList where destination contains UPDATED_DESTINATION
        defaultDataFlowsShouldNotBeFound("destination.contains=" + UPDATED_DESTINATION);
    }

    @Test
    @Transactional
    public void getAllDataFlowsByDestinationNotContainsSomething() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where destination does not contain DEFAULT_DESTINATION
        defaultDataFlowsShouldNotBeFound("destination.doesNotContain=" + DEFAULT_DESTINATION);

        // Get all the dataFlowsList where destination does not contain UPDATED_DESTINATION
        defaultDataFlowsShouldBeFound("destination.doesNotContain=" + UPDATED_DESTINATION);
    }


    @Test
    @Transactional
    public void getAllDataFlowsByTransformationIsEqualToSomething() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where transformation equals to DEFAULT_TRANSFORMATION
        defaultDataFlowsShouldBeFound("transformation.equals=" + DEFAULT_TRANSFORMATION);

        // Get all the dataFlowsList where transformation equals to UPDATED_TRANSFORMATION
        defaultDataFlowsShouldNotBeFound("transformation.equals=" + UPDATED_TRANSFORMATION);
    }

    @Test
    @Transactional
    public void getAllDataFlowsByTransformationIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where transformation not equals to DEFAULT_TRANSFORMATION
        defaultDataFlowsShouldNotBeFound("transformation.notEquals=" + DEFAULT_TRANSFORMATION);

        // Get all the dataFlowsList where transformation not equals to UPDATED_TRANSFORMATION
        defaultDataFlowsShouldBeFound("transformation.notEquals=" + UPDATED_TRANSFORMATION);
    }

    @Test
    @Transactional
    public void getAllDataFlowsByTransformationIsInShouldWork() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where transformation in DEFAULT_TRANSFORMATION or UPDATED_TRANSFORMATION
        defaultDataFlowsShouldBeFound("transformation.in=" + DEFAULT_TRANSFORMATION + "," + UPDATED_TRANSFORMATION);

        // Get all the dataFlowsList where transformation equals to UPDATED_TRANSFORMATION
        defaultDataFlowsShouldNotBeFound("transformation.in=" + UPDATED_TRANSFORMATION);
    }

    @Test
    @Transactional
    public void getAllDataFlowsByTransformationIsNullOrNotNull() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where transformation is not null
        defaultDataFlowsShouldBeFound("transformation.specified=true");

        // Get all the dataFlowsList where transformation is null
        defaultDataFlowsShouldNotBeFound("transformation.specified=false");
    }
                @Test
    @Transactional
    public void getAllDataFlowsByTransformationContainsSomething() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where transformation contains DEFAULT_TRANSFORMATION
        defaultDataFlowsShouldBeFound("transformation.contains=" + DEFAULT_TRANSFORMATION);

        // Get all the dataFlowsList where transformation contains UPDATED_TRANSFORMATION
        defaultDataFlowsShouldNotBeFound("transformation.contains=" + UPDATED_TRANSFORMATION);
    }

    @Test
    @Transactional
    public void getAllDataFlowsByTransformationNotContainsSomething() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where transformation does not contain DEFAULT_TRANSFORMATION
        defaultDataFlowsShouldNotBeFound("transformation.doesNotContain=" + DEFAULT_TRANSFORMATION);

        // Get all the dataFlowsList where transformation does not contain UPDATED_TRANSFORMATION
        defaultDataFlowsShouldBeFound("transformation.doesNotContain=" + UPDATED_TRANSFORMATION);
    }


    @Test
    @Transactional
    public void getAllDataFlowsBylSETIsEqualToSomething() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where lSET equals to DEFAULT_L_SET
        defaultDataFlowsShouldBeFound("lSET.equals=" + DEFAULT_L_SET);

        // Get all the dataFlowsList where lSET equals to UPDATED_L_SET
        defaultDataFlowsShouldNotBeFound("lSET.equals=" + UPDATED_L_SET);
    }

    @Test
    @Transactional
    public void getAllDataFlowsBylSETIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where lSET not equals to DEFAULT_L_SET
        defaultDataFlowsShouldNotBeFound("lSET.notEquals=" + DEFAULT_L_SET);

        // Get all the dataFlowsList where lSET not equals to UPDATED_L_SET
        defaultDataFlowsShouldBeFound("lSET.notEquals=" + UPDATED_L_SET);
    }

    @Test
    @Transactional
    public void getAllDataFlowsBylSETIsInShouldWork() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where lSET in DEFAULT_L_SET or UPDATED_L_SET
        defaultDataFlowsShouldBeFound("lSET.in=" + DEFAULT_L_SET + "," + UPDATED_L_SET);

        // Get all the dataFlowsList where lSET equals to UPDATED_L_SET
        defaultDataFlowsShouldNotBeFound("lSET.in=" + UPDATED_L_SET);
    }

    @Test
    @Transactional
    public void getAllDataFlowsBylSETIsNullOrNotNull() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where lSET is not null
        defaultDataFlowsShouldBeFound("lSET.specified=true");

        // Get all the dataFlowsList where lSET is null
        defaultDataFlowsShouldNotBeFound("lSET.specified=false");
    }

    @Test
    @Transactional
    public void getAllDataFlowsBycETIsEqualToSomething() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where cET equals to DEFAULT_C_ET
        defaultDataFlowsShouldBeFound("cET.equals=" + DEFAULT_C_ET);

        // Get all the dataFlowsList where cET equals to UPDATED_C_ET
        defaultDataFlowsShouldNotBeFound("cET.equals=" + UPDATED_C_ET);
    }

    @Test
    @Transactional
    public void getAllDataFlowsBycETIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where cET not equals to DEFAULT_C_ET
        defaultDataFlowsShouldNotBeFound("cET.notEquals=" + DEFAULT_C_ET);

        // Get all the dataFlowsList where cET not equals to UPDATED_C_ET
        defaultDataFlowsShouldBeFound("cET.notEquals=" + UPDATED_C_ET);
    }

    @Test
    @Transactional
    public void getAllDataFlowsBycETIsInShouldWork() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where cET in DEFAULT_C_ET or UPDATED_C_ET
        defaultDataFlowsShouldBeFound("cET.in=" + DEFAULT_C_ET + "," + UPDATED_C_ET);

        // Get all the dataFlowsList where cET equals to UPDATED_C_ET
        defaultDataFlowsShouldNotBeFound("cET.in=" + UPDATED_C_ET);
    }

    @Test
    @Transactional
    public void getAllDataFlowsBycETIsNullOrNotNull() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        // Get all the dataFlowsList where cET is not null
        defaultDataFlowsShouldBeFound("cET.specified=true");

        // Get all the dataFlowsList where cET is null
        defaultDataFlowsShouldNotBeFound("cET.specified=false");
    }

    @Test
    @Transactional
    public void getAllDataFlowsByEtlStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);
        EtlStatus etlStatus = EtlStatusResourceIT.createEntity(em);
        em.persist(etlStatus);
        em.flush();
        dataFlows.setEtlStatus(etlStatus);
        dataFlowsRepository.saveAndFlush(dataFlows);
        Long etlStatusId = etlStatus.getId();

        // Get all the dataFlowsList where etlStatus equals to etlStatusId
        defaultDataFlowsShouldBeFound("etlStatusId.equals=" + etlStatusId);

        // Get all the dataFlowsList where etlStatus equals to etlStatusId + 1
        defaultDataFlowsShouldNotBeFound("etlStatusId.equals=" + (etlStatusId + 1));
    }


    @Test
    @Transactional
    public void getAllDataFlowsByEtlPkgIsEqualToSomething() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);
        EtlPackages etlPkg = EtlPackagesResourceIT.createEntity(em);
        em.persist(etlPkg);
        em.flush();
        dataFlows.setEtlPkg(etlPkg);
        dataFlowsRepository.saveAndFlush(dataFlows);
        Long etlPkgId = etlPkg.getId();

        // Get all the dataFlowsList where etlPkg equals to etlPkgId
        defaultDataFlowsShouldBeFound("etlPkgId.equals=" + etlPkgId);

        // Get all the dataFlowsList where etlPkg equals to etlPkgId + 1
        defaultDataFlowsShouldNotBeFound("etlPkgId.equals=" + (etlPkgId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDataFlowsShouldBeFound(String filter) throws Exception {
        restDataFlowsMockMvc.perform(get("/api/data-flows?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dataFlows.getId().intValue())))
            .andExpect(jsonPath("$.[*].flowName").value(hasItem(DEFAULT_FLOW_NAME)))
            .andExpect(jsonPath("$.[*].flowDescription").value(hasItem(DEFAULT_FLOW_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].source").value(hasItem(DEFAULT_SOURCE)))
            .andExpect(jsonPath("$.[*].destination").value(hasItem(DEFAULT_DESTINATION)))
            .andExpect(jsonPath("$.[*].transformation").value(hasItem(DEFAULT_TRANSFORMATION)))
            .andExpect(jsonPath("$.[*].lSET").value(hasItem(DEFAULT_L_SET.toString())))
            .andExpect(jsonPath("$.[*].cET").value(hasItem(DEFAULT_C_ET.toString())));

        // Check, that the count call also returns 1
        restDataFlowsMockMvc.perform(get("/api/data-flows/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDataFlowsShouldNotBeFound(String filter) throws Exception {
        restDataFlowsMockMvc.perform(get("/api/data-flows?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDataFlowsMockMvc.perform(get("/api/data-flows/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDataFlows() throws Exception {
        // Get the dataFlows
        restDataFlowsMockMvc.perform(get("/api/data-flows/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDataFlows() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        int databaseSizeBeforeUpdate = dataFlowsRepository.findAll().size();

        // Update the dataFlows
        DataFlows updatedDataFlows = dataFlowsRepository.findById(dataFlows.getId()).get();
        // Disconnect from session so that the updates on updatedDataFlows are not directly saved in db
        em.detach(updatedDataFlows);
        updatedDataFlows
            .flowName(UPDATED_FLOW_NAME)
            .flowDescription(UPDATED_FLOW_DESCRIPTION)
            .source(UPDATED_SOURCE)
            .destination(UPDATED_DESTINATION)
            .transformation(UPDATED_TRANSFORMATION)
            .lSET(UPDATED_L_SET)
            .cET(UPDATED_C_ET);
        DataFlowsDTO dataFlowsDTO = dataFlowsMapper.toDto(updatedDataFlows);

        restDataFlowsMockMvc.perform(put("/api/data-flows")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataFlowsDTO)))
            .andExpect(status().isOk());

        // Validate the DataFlows in the database
        List<DataFlows> dataFlowsList = dataFlowsRepository.findAll();
        assertThat(dataFlowsList).hasSize(databaseSizeBeforeUpdate);
        DataFlows testDataFlows = dataFlowsList.get(dataFlowsList.size() - 1);
        assertThat(testDataFlows.getFlowName()).isEqualTo(UPDATED_FLOW_NAME);
        assertThat(testDataFlows.getFlowDescription()).isEqualTo(UPDATED_FLOW_DESCRIPTION);
        assertThat(testDataFlows.getSource()).isEqualTo(UPDATED_SOURCE);
        assertThat(testDataFlows.getDestination()).isEqualTo(UPDATED_DESTINATION);
        assertThat(testDataFlows.getTransformation()).isEqualTo(UPDATED_TRANSFORMATION);
        assertThat(testDataFlows.getlSET()).isEqualTo(UPDATED_L_SET);
        assertThat(testDataFlows.getcET()).isEqualTo(UPDATED_C_ET);
    }

    @Test
    @Transactional
    public void updateNonExistingDataFlows() throws Exception {
        int databaseSizeBeforeUpdate = dataFlowsRepository.findAll().size();

        // Create the DataFlows
        DataFlowsDTO dataFlowsDTO = dataFlowsMapper.toDto(dataFlows);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDataFlowsMockMvc.perform(put("/api/data-flows")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataFlowsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DataFlows in the database
        List<DataFlows> dataFlowsList = dataFlowsRepository.findAll();
        assertThat(dataFlowsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDataFlows() throws Exception {
        // Initialize the database
        dataFlowsRepository.saveAndFlush(dataFlows);

        int databaseSizeBeforeDelete = dataFlowsRepository.findAll().size();

        // Delete the dataFlows
        restDataFlowsMockMvc.perform(delete("/api/data-flows/{id}", dataFlows.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DataFlows> dataFlowsList = dataFlowsRepository.findAll();
        assertThat(dataFlowsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
