package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.DataDefinition;
import com.mycompany.myapp.domain.DsColumns;
import com.mycompany.myapp.domain.DsColumnTypes;
import com.mycompany.myapp.domain.DsTables;
import com.mycompany.myapp.repository.DataDefinitionRepository;
import com.mycompany.myapp.service.DataDefinitionService;
import com.mycompany.myapp.service.dto.DataDefinitionDTO;
import com.mycompany.myapp.service.mapper.DataDefinitionMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.DataDefinitionCriteria;
import com.mycompany.myapp.service.DataDefinitionQueryService;

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
 * Integration tests for the {@link DataDefinitionResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class DataDefinitionResourceIT {

    private static final Integer DEFAULT_SRC_COL_ID = 1;
    private static final Integer UPDATED_SRC_COL_ID = 2;
    private static final Integer SMALLER_SRC_COL_ID = 1 - 1;

    private static final String DEFAULT_DEF_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DEF_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_DEF_SAMPLE_DATA = "AAAAAAAAAA";
    private static final String UPDATED_DEF_SAMPLE_DATA = "BBBBBBBBBB";

    @Autowired
    private DataDefinitionRepository dataDefinitionRepository;

    @Autowired
    private DataDefinitionMapper dataDefinitionMapper;

    @Autowired
    private DataDefinitionService dataDefinitionService;

    @Autowired
    private DataDefinitionQueryService dataDefinitionQueryService;

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

    private MockMvc restDataDefinitionMockMvc;

    private DataDefinition dataDefinition;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DataDefinitionResource dataDefinitionResource = new DataDefinitionResource(dataDefinitionService, dataDefinitionQueryService);
        this.restDataDefinitionMockMvc = MockMvcBuilders.standaloneSetup(dataDefinitionResource)
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
    public static DataDefinition createEntity(EntityManager em) {
        DataDefinition dataDefinition = new DataDefinition()
            .srcColId(DEFAULT_SRC_COL_ID)
            .defDescription(DEFAULT_DEF_DESCRIPTION)
            .defSampleData(DEFAULT_DEF_SAMPLE_DATA);
        return dataDefinition;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DataDefinition createUpdatedEntity(EntityManager em) {
        DataDefinition dataDefinition = new DataDefinition()
            .srcColId(UPDATED_SRC_COL_ID)
            .defDescription(UPDATED_DEF_DESCRIPTION)
            .defSampleData(UPDATED_DEF_SAMPLE_DATA);
        return dataDefinition;
    }

    @BeforeEach
    public void initTest() {
        dataDefinition = createEntity(em);
    }

    @Test
    @Transactional
    public void createDataDefinition() throws Exception {
        int databaseSizeBeforeCreate = dataDefinitionRepository.findAll().size();

        // Create the DataDefinition
        DataDefinitionDTO dataDefinitionDTO = dataDefinitionMapper.toDto(dataDefinition);
        restDataDefinitionMockMvc.perform(post("/api/data-definitions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataDefinitionDTO)))
            .andExpect(status().isCreated());

        // Validate the DataDefinition in the database
        List<DataDefinition> dataDefinitionList = dataDefinitionRepository.findAll();
        assertThat(dataDefinitionList).hasSize(databaseSizeBeforeCreate + 1);
        DataDefinition testDataDefinition = dataDefinitionList.get(dataDefinitionList.size() - 1);
        assertThat(testDataDefinition.getSrcColId()).isEqualTo(DEFAULT_SRC_COL_ID);
        assertThat(testDataDefinition.getDefDescription()).isEqualTo(DEFAULT_DEF_DESCRIPTION);
        assertThat(testDataDefinition.getDefSampleData()).isEqualTo(DEFAULT_DEF_SAMPLE_DATA);
    }

    @Test
    @Transactional
    public void createDataDefinitionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dataDefinitionRepository.findAll().size();

        // Create the DataDefinition with an existing ID
        dataDefinition.setId(1L);
        DataDefinitionDTO dataDefinitionDTO = dataDefinitionMapper.toDto(dataDefinition);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDataDefinitionMockMvc.perform(post("/api/data-definitions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataDefinitionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DataDefinition in the database
        List<DataDefinition> dataDefinitionList = dataDefinitionRepository.findAll();
        assertThat(dataDefinitionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDataDefinitions() throws Exception {
        // Initialize the database
        dataDefinitionRepository.saveAndFlush(dataDefinition);

        // Get all the dataDefinitionList
        restDataDefinitionMockMvc.perform(get("/api/data-definitions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dataDefinition.getId().intValue())))
            .andExpect(jsonPath("$.[*].srcColId").value(hasItem(DEFAULT_SRC_COL_ID)))
            .andExpect(jsonPath("$.[*].defDescription").value(hasItem(DEFAULT_DEF_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].defSampleData").value(hasItem(DEFAULT_DEF_SAMPLE_DATA)));
    }
    
    @Test
    @Transactional
    public void getDataDefinition() throws Exception {
        // Initialize the database
        dataDefinitionRepository.saveAndFlush(dataDefinition);

        // Get the dataDefinition
        restDataDefinitionMockMvc.perform(get("/api/data-definitions/{id}", dataDefinition.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dataDefinition.getId().intValue()))
            .andExpect(jsonPath("$.srcColId").value(DEFAULT_SRC_COL_ID))
            .andExpect(jsonPath("$.defDescription").value(DEFAULT_DEF_DESCRIPTION))
            .andExpect(jsonPath("$.defSampleData").value(DEFAULT_DEF_SAMPLE_DATA));
    }


    @Test
    @Transactional
    public void getDataDefinitionsByIdFiltering() throws Exception {
        // Initialize the database
        dataDefinitionRepository.saveAndFlush(dataDefinition);

        Long id = dataDefinition.getId();

        defaultDataDefinitionShouldBeFound("id.equals=" + id);
        defaultDataDefinitionShouldNotBeFound("id.notEquals=" + id);

        defaultDataDefinitionShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDataDefinitionShouldNotBeFound("id.greaterThan=" + id);

        defaultDataDefinitionShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDataDefinitionShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDataDefinitionsBySrcColIdIsEqualToSomething() throws Exception {
        // Initialize the database
        dataDefinitionRepository.saveAndFlush(dataDefinition);

        // Get all the dataDefinitionList where srcColId equals to DEFAULT_SRC_COL_ID
        defaultDataDefinitionShouldBeFound("srcColId.equals=" + DEFAULT_SRC_COL_ID);

        // Get all the dataDefinitionList where srcColId equals to UPDATED_SRC_COL_ID
        defaultDataDefinitionShouldNotBeFound("srcColId.equals=" + UPDATED_SRC_COL_ID);
    }

    @Test
    @Transactional
    public void getAllDataDefinitionsBySrcColIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dataDefinitionRepository.saveAndFlush(dataDefinition);

        // Get all the dataDefinitionList where srcColId not equals to DEFAULT_SRC_COL_ID
        defaultDataDefinitionShouldNotBeFound("srcColId.notEquals=" + DEFAULT_SRC_COL_ID);

        // Get all the dataDefinitionList where srcColId not equals to UPDATED_SRC_COL_ID
        defaultDataDefinitionShouldBeFound("srcColId.notEquals=" + UPDATED_SRC_COL_ID);
    }

    @Test
    @Transactional
    public void getAllDataDefinitionsBySrcColIdIsInShouldWork() throws Exception {
        // Initialize the database
        dataDefinitionRepository.saveAndFlush(dataDefinition);

        // Get all the dataDefinitionList where srcColId in DEFAULT_SRC_COL_ID or UPDATED_SRC_COL_ID
        defaultDataDefinitionShouldBeFound("srcColId.in=" + DEFAULT_SRC_COL_ID + "," + UPDATED_SRC_COL_ID);

        // Get all the dataDefinitionList where srcColId equals to UPDATED_SRC_COL_ID
        defaultDataDefinitionShouldNotBeFound("srcColId.in=" + UPDATED_SRC_COL_ID);
    }

    @Test
    @Transactional
    public void getAllDataDefinitionsBySrcColIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        dataDefinitionRepository.saveAndFlush(dataDefinition);

        // Get all the dataDefinitionList where srcColId is not null
        defaultDataDefinitionShouldBeFound("srcColId.specified=true");

        // Get all the dataDefinitionList where srcColId is null
        defaultDataDefinitionShouldNotBeFound("srcColId.specified=false");
    }

    @Test
    @Transactional
    public void getAllDataDefinitionsBySrcColIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dataDefinitionRepository.saveAndFlush(dataDefinition);

        // Get all the dataDefinitionList where srcColId is greater than or equal to DEFAULT_SRC_COL_ID
        defaultDataDefinitionShouldBeFound("srcColId.greaterThanOrEqual=" + DEFAULT_SRC_COL_ID);

        // Get all the dataDefinitionList where srcColId is greater than or equal to UPDATED_SRC_COL_ID
        defaultDataDefinitionShouldNotBeFound("srcColId.greaterThanOrEqual=" + UPDATED_SRC_COL_ID);
    }

    @Test
    @Transactional
    public void getAllDataDefinitionsBySrcColIdIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dataDefinitionRepository.saveAndFlush(dataDefinition);

        // Get all the dataDefinitionList where srcColId is less than or equal to DEFAULT_SRC_COL_ID
        defaultDataDefinitionShouldBeFound("srcColId.lessThanOrEqual=" + DEFAULT_SRC_COL_ID);

        // Get all the dataDefinitionList where srcColId is less than or equal to SMALLER_SRC_COL_ID
        defaultDataDefinitionShouldNotBeFound("srcColId.lessThanOrEqual=" + SMALLER_SRC_COL_ID);
    }

    @Test
    @Transactional
    public void getAllDataDefinitionsBySrcColIdIsLessThanSomething() throws Exception {
        // Initialize the database
        dataDefinitionRepository.saveAndFlush(dataDefinition);

        // Get all the dataDefinitionList where srcColId is less than DEFAULT_SRC_COL_ID
        defaultDataDefinitionShouldNotBeFound("srcColId.lessThan=" + DEFAULT_SRC_COL_ID);

        // Get all the dataDefinitionList where srcColId is less than UPDATED_SRC_COL_ID
        defaultDataDefinitionShouldBeFound("srcColId.lessThan=" + UPDATED_SRC_COL_ID);
    }

    @Test
    @Transactional
    public void getAllDataDefinitionsBySrcColIdIsGreaterThanSomething() throws Exception {
        // Initialize the database
        dataDefinitionRepository.saveAndFlush(dataDefinition);

        // Get all the dataDefinitionList where srcColId is greater than DEFAULT_SRC_COL_ID
        defaultDataDefinitionShouldNotBeFound("srcColId.greaterThan=" + DEFAULT_SRC_COL_ID);

        // Get all the dataDefinitionList where srcColId is greater than SMALLER_SRC_COL_ID
        defaultDataDefinitionShouldBeFound("srcColId.greaterThan=" + SMALLER_SRC_COL_ID);
    }


    @Test
    @Transactional
    public void getAllDataDefinitionsByDefDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        dataDefinitionRepository.saveAndFlush(dataDefinition);

        // Get all the dataDefinitionList where defDescription equals to DEFAULT_DEF_DESCRIPTION
        defaultDataDefinitionShouldBeFound("defDescription.equals=" + DEFAULT_DEF_DESCRIPTION);

        // Get all the dataDefinitionList where defDescription equals to UPDATED_DEF_DESCRIPTION
        defaultDataDefinitionShouldNotBeFound("defDescription.equals=" + UPDATED_DEF_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDataDefinitionsByDefDescriptionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dataDefinitionRepository.saveAndFlush(dataDefinition);

        // Get all the dataDefinitionList where defDescription not equals to DEFAULT_DEF_DESCRIPTION
        defaultDataDefinitionShouldNotBeFound("defDescription.notEquals=" + DEFAULT_DEF_DESCRIPTION);

        // Get all the dataDefinitionList where defDescription not equals to UPDATED_DEF_DESCRIPTION
        defaultDataDefinitionShouldBeFound("defDescription.notEquals=" + UPDATED_DEF_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDataDefinitionsByDefDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        dataDefinitionRepository.saveAndFlush(dataDefinition);

        // Get all the dataDefinitionList where defDescription in DEFAULT_DEF_DESCRIPTION or UPDATED_DEF_DESCRIPTION
        defaultDataDefinitionShouldBeFound("defDescription.in=" + DEFAULT_DEF_DESCRIPTION + "," + UPDATED_DEF_DESCRIPTION);

        // Get all the dataDefinitionList where defDescription equals to UPDATED_DEF_DESCRIPTION
        defaultDataDefinitionShouldNotBeFound("defDescription.in=" + UPDATED_DEF_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDataDefinitionsByDefDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        dataDefinitionRepository.saveAndFlush(dataDefinition);

        // Get all the dataDefinitionList where defDescription is not null
        defaultDataDefinitionShouldBeFound("defDescription.specified=true");

        // Get all the dataDefinitionList where defDescription is null
        defaultDataDefinitionShouldNotBeFound("defDescription.specified=false");
    }
                @Test
    @Transactional
    public void getAllDataDefinitionsByDefDescriptionContainsSomething() throws Exception {
        // Initialize the database
        dataDefinitionRepository.saveAndFlush(dataDefinition);

        // Get all the dataDefinitionList where defDescription contains DEFAULT_DEF_DESCRIPTION
        defaultDataDefinitionShouldBeFound("defDescription.contains=" + DEFAULT_DEF_DESCRIPTION);

        // Get all the dataDefinitionList where defDescription contains UPDATED_DEF_DESCRIPTION
        defaultDataDefinitionShouldNotBeFound("defDescription.contains=" + UPDATED_DEF_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDataDefinitionsByDefDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        dataDefinitionRepository.saveAndFlush(dataDefinition);

        // Get all the dataDefinitionList where defDescription does not contain DEFAULT_DEF_DESCRIPTION
        defaultDataDefinitionShouldNotBeFound("defDescription.doesNotContain=" + DEFAULT_DEF_DESCRIPTION);

        // Get all the dataDefinitionList where defDescription does not contain UPDATED_DEF_DESCRIPTION
        defaultDataDefinitionShouldBeFound("defDescription.doesNotContain=" + UPDATED_DEF_DESCRIPTION);
    }


    @Test
    @Transactional
    public void getAllDataDefinitionsByDefSampleDataIsEqualToSomething() throws Exception {
        // Initialize the database
        dataDefinitionRepository.saveAndFlush(dataDefinition);

        // Get all the dataDefinitionList where defSampleData equals to DEFAULT_DEF_SAMPLE_DATA
        defaultDataDefinitionShouldBeFound("defSampleData.equals=" + DEFAULT_DEF_SAMPLE_DATA);

        // Get all the dataDefinitionList where defSampleData equals to UPDATED_DEF_SAMPLE_DATA
        defaultDataDefinitionShouldNotBeFound("defSampleData.equals=" + UPDATED_DEF_SAMPLE_DATA);
    }

    @Test
    @Transactional
    public void getAllDataDefinitionsByDefSampleDataIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dataDefinitionRepository.saveAndFlush(dataDefinition);

        // Get all the dataDefinitionList where defSampleData not equals to DEFAULT_DEF_SAMPLE_DATA
        defaultDataDefinitionShouldNotBeFound("defSampleData.notEquals=" + DEFAULT_DEF_SAMPLE_DATA);

        // Get all the dataDefinitionList where defSampleData not equals to UPDATED_DEF_SAMPLE_DATA
        defaultDataDefinitionShouldBeFound("defSampleData.notEquals=" + UPDATED_DEF_SAMPLE_DATA);
    }

    @Test
    @Transactional
    public void getAllDataDefinitionsByDefSampleDataIsInShouldWork() throws Exception {
        // Initialize the database
        dataDefinitionRepository.saveAndFlush(dataDefinition);

        // Get all the dataDefinitionList where defSampleData in DEFAULT_DEF_SAMPLE_DATA or UPDATED_DEF_SAMPLE_DATA
        defaultDataDefinitionShouldBeFound("defSampleData.in=" + DEFAULT_DEF_SAMPLE_DATA + "," + UPDATED_DEF_SAMPLE_DATA);

        // Get all the dataDefinitionList where defSampleData equals to UPDATED_DEF_SAMPLE_DATA
        defaultDataDefinitionShouldNotBeFound("defSampleData.in=" + UPDATED_DEF_SAMPLE_DATA);
    }

    @Test
    @Transactional
    public void getAllDataDefinitionsByDefSampleDataIsNullOrNotNull() throws Exception {
        // Initialize the database
        dataDefinitionRepository.saveAndFlush(dataDefinition);

        // Get all the dataDefinitionList where defSampleData is not null
        defaultDataDefinitionShouldBeFound("defSampleData.specified=true");

        // Get all the dataDefinitionList where defSampleData is null
        defaultDataDefinitionShouldNotBeFound("defSampleData.specified=false");
    }
                @Test
    @Transactional
    public void getAllDataDefinitionsByDefSampleDataContainsSomething() throws Exception {
        // Initialize the database
        dataDefinitionRepository.saveAndFlush(dataDefinition);

        // Get all the dataDefinitionList where defSampleData contains DEFAULT_DEF_SAMPLE_DATA
        defaultDataDefinitionShouldBeFound("defSampleData.contains=" + DEFAULT_DEF_SAMPLE_DATA);

        // Get all the dataDefinitionList where defSampleData contains UPDATED_DEF_SAMPLE_DATA
        defaultDataDefinitionShouldNotBeFound("defSampleData.contains=" + UPDATED_DEF_SAMPLE_DATA);
    }

    @Test
    @Transactional
    public void getAllDataDefinitionsByDefSampleDataNotContainsSomething() throws Exception {
        // Initialize the database
        dataDefinitionRepository.saveAndFlush(dataDefinition);

        // Get all the dataDefinitionList where defSampleData does not contain DEFAULT_DEF_SAMPLE_DATA
        defaultDataDefinitionShouldNotBeFound("defSampleData.doesNotContain=" + DEFAULT_DEF_SAMPLE_DATA);

        // Get all the dataDefinitionList where defSampleData does not contain UPDATED_DEF_SAMPLE_DATA
        defaultDataDefinitionShouldBeFound("defSampleData.doesNotContain=" + UPDATED_DEF_SAMPLE_DATA);
    }


    @Test
    @Transactional
    public void getAllDataDefinitionsByColIsEqualToSomething() throws Exception {
        // Initialize the database
        dataDefinitionRepository.saveAndFlush(dataDefinition);
        DsColumns col = DsColumnsResourceIT.createEntity(em);
        em.persist(col);
        em.flush();
        dataDefinition.setCol(col);
        dataDefinitionRepository.saveAndFlush(dataDefinition);
        Long colId = col.getId();

        // Get all the dataDefinitionList where col equals to colId
        defaultDataDefinitionShouldBeFound("colId.equals=" + colId);

        // Get all the dataDefinitionList where col equals to colId + 1
        defaultDataDefinitionShouldNotBeFound("colId.equals=" + (colId + 1));
    }


    @Test
    @Transactional
    public void getAllDataDefinitionsByTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        dataDefinitionRepository.saveAndFlush(dataDefinition);
        DsColumnTypes type = DsColumnTypesResourceIT.createEntity(em);
        em.persist(type);
        em.flush();
        dataDefinition.setType(type);
        dataDefinitionRepository.saveAndFlush(dataDefinition);
        Long typeId = type.getId();

        // Get all the dataDefinitionList where type equals to typeId
        defaultDataDefinitionShouldBeFound("typeId.equals=" + typeId);

        // Get all the dataDefinitionList where type equals to typeId + 1
        defaultDataDefinitionShouldNotBeFound("typeId.equals=" + (typeId + 1));
    }


    @Test
    @Transactional
    public void getAllDataDefinitionsByTblIsEqualToSomething() throws Exception {
        // Initialize the database
        dataDefinitionRepository.saveAndFlush(dataDefinition);
        DsTables tbl = DsTablesResourceIT.createEntity(em);
        em.persist(tbl);
        em.flush();
        dataDefinition.setTbl(tbl);
        dataDefinitionRepository.saveAndFlush(dataDefinition);
        Long tblId = tbl.getId();

        // Get all the dataDefinitionList where tbl equals to tblId
        defaultDataDefinitionShouldBeFound("tblId.equals=" + tblId);

        // Get all the dataDefinitionList where tbl equals to tblId + 1
        defaultDataDefinitionShouldNotBeFound("tblId.equals=" + (tblId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDataDefinitionShouldBeFound(String filter) throws Exception {
        restDataDefinitionMockMvc.perform(get("/api/data-definitions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dataDefinition.getId().intValue())))
            .andExpect(jsonPath("$.[*].srcColId").value(hasItem(DEFAULT_SRC_COL_ID)))
            .andExpect(jsonPath("$.[*].defDescription").value(hasItem(DEFAULT_DEF_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].defSampleData").value(hasItem(DEFAULT_DEF_SAMPLE_DATA)));

        // Check, that the count call also returns 1
        restDataDefinitionMockMvc.perform(get("/api/data-definitions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDataDefinitionShouldNotBeFound(String filter) throws Exception {
        restDataDefinitionMockMvc.perform(get("/api/data-definitions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDataDefinitionMockMvc.perform(get("/api/data-definitions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDataDefinition() throws Exception {
        // Get the dataDefinition
        restDataDefinitionMockMvc.perform(get("/api/data-definitions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDataDefinition() throws Exception {
        // Initialize the database
        dataDefinitionRepository.saveAndFlush(dataDefinition);

        int databaseSizeBeforeUpdate = dataDefinitionRepository.findAll().size();

        // Update the dataDefinition
        DataDefinition updatedDataDefinition = dataDefinitionRepository.findById(dataDefinition.getId()).get();
        // Disconnect from session so that the updates on updatedDataDefinition are not directly saved in db
        em.detach(updatedDataDefinition);
        updatedDataDefinition
            .srcColId(UPDATED_SRC_COL_ID)
            .defDescription(UPDATED_DEF_DESCRIPTION)
            .defSampleData(UPDATED_DEF_SAMPLE_DATA);
        DataDefinitionDTO dataDefinitionDTO = dataDefinitionMapper.toDto(updatedDataDefinition);

        restDataDefinitionMockMvc.perform(put("/api/data-definitions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataDefinitionDTO)))
            .andExpect(status().isOk());

        // Validate the DataDefinition in the database
        List<DataDefinition> dataDefinitionList = dataDefinitionRepository.findAll();
        assertThat(dataDefinitionList).hasSize(databaseSizeBeforeUpdate);
        DataDefinition testDataDefinition = dataDefinitionList.get(dataDefinitionList.size() - 1);
        assertThat(testDataDefinition.getSrcColId()).isEqualTo(UPDATED_SRC_COL_ID);
        assertThat(testDataDefinition.getDefDescription()).isEqualTo(UPDATED_DEF_DESCRIPTION);
        assertThat(testDataDefinition.getDefSampleData()).isEqualTo(UPDATED_DEF_SAMPLE_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingDataDefinition() throws Exception {
        int databaseSizeBeforeUpdate = dataDefinitionRepository.findAll().size();

        // Create the DataDefinition
        DataDefinitionDTO dataDefinitionDTO = dataDefinitionMapper.toDto(dataDefinition);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDataDefinitionMockMvc.perform(put("/api/data-definitions")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataDefinitionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DataDefinition in the database
        List<DataDefinition> dataDefinitionList = dataDefinitionRepository.findAll();
        assertThat(dataDefinitionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDataDefinition() throws Exception {
        // Initialize the database
        dataDefinitionRepository.saveAndFlush(dataDefinition);

        int databaseSizeBeforeDelete = dataDefinitionRepository.findAll().size();

        // Delete the dataDefinition
        restDataDefinitionMockMvc.perform(delete("/api/data-definitions/{id}", dataDefinition.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DataDefinition> dataDefinitionList = dataDefinitionRepository.findAll();
        assertThat(dataDefinitionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
