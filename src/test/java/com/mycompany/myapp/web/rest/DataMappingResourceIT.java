package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.DataMapping;
import com.mycompany.myapp.domain.DsColumns;
import com.mycompany.myapp.repository.DataMappingRepository;
import com.mycompany.myapp.service.DataMappingService;
import com.mycompany.myapp.service.dto.DataMappingDTO;
import com.mycompany.myapp.service.mapper.DataMappingMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.DataMappingCriteria;
import com.mycompany.myapp.service.DataMappingQueryService;

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
 * Integration tests for the {@link DataMappingResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class DataMappingResourceIT {

    private static final Integer DEFAULT_SRC_COL_ID = 1;
    private static final Integer UPDATED_SRC_COL_ID = 2;
    private static final Integer SMALLER_SRC_COL_ID = 1 - 1;

    @Autowired
    private DataMappingRepository dataMappingRepository;

    @Autowired
    private DataMappingMapper dataMappingMapper;

    @Autowired
    private DataMappingService dataMappingService;

    @Autowired
    private DataMappingQueryService dataMappingQueryService;

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

    private MockMvc restDataMappingMockMvc;

    private DataMapping dataMapping;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DataMappingResource dataMappingResource = new DataMappingResource(dataMappingService, dataMappingQueryService);
        this.restDataMappingMockMvc = MockMvcBuilders.standaloneSetup(dataMappingResource)
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
    public static DataMapping createEntity(EntityManager em) {
        DataMapping dataMapping = new DataMapping()
            .srcColId(DEFAULT_SRC_COL_ID);
        return dataMapping;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DataMapping createUpdatedEntity(EntityManager em) {
        DataMapping dataMapping = new DataMapping()
            .srcColId(UPDATED_SRC_COL_ID);
        return dataMapping;
    }

    @BeforeEach
    public void initTest() {
        dataMapping = createEntity(em);
    }

    @Test
    @Transactional
    public void createDataMapping() throws Exception {
        int databaseSizeBeforeCreate = dataMappingRepository.findAll().size();

        // Create the DataMapping
        DataMappingDTO dataMappingDTO = dataMappingMapper.toDto(dataMapping);
        restDataMappingMockMvc.perform(post("/api/data-mappings")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataMappingDTO)))
            .andExpect(status().isCreated());

        // Validate the DataMapping in the database
        List<DataMapping> dataMappingList = dataMappingRepository.findAll();
        assertThat(dataMappingList).hasSize(databaseSizeBeforeCreate + 1);
        DataMapping testDataMapping = dataMappingList.get(dataMappingList.size() - 1);
        assertThat(testDataMapping.getSrcColId()).isEqualTo(DEFAULT_SRC_COL_ID);
    }

    @Test
    @Transactional
    public void createDataMappingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dataMappingRepository.findAll().size();

        // Create the DataMapping with an existing ID
        dataMapping.setId(1L);
        DataMappingDTO dataMappingDTO = dataMappingMapper.toDto(dataMapping);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDataMappingMockMvc.perform(post("/api/data-mappings")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataMappingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DataMapping in the database
        List<DataMapping> dataMappingList = dataMappingRepository.findAll();
        assertThat(dataMappingList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDataMappings() throws Exception {
        // Initialize the database
        dataMappingRepository.saveAndFlush(dataMapping);

        // Get all the dataMappingList
        restDataMappingMockMvc.perform(get("/api/data-mappings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dataMapping.getId().intValue())))
            .andExpect(jsonPath("$.[*].srcColId").value(hasItem(DEFAULT_SRC_COL_ID)));
    }
    
    @Test
    @Transactional
    public void getDataMapping() throws Exception {
        // Initialize the database
        dataMappingRepository.saveAndFlush(dataMapping);

        // Get the dataMapping
        restDataMappingMockMvc.perform(get("/api/data-mappings/{id}", dataMapping.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dataMapping.getId().intValue()))
            .andExpect(jsonPath("$.srcColId").value(DEFAULT_SRC_COL_ID));
    }


    @Test
    @Transactional
    public void getDataMappingsByIdFiltering() throws Exception {
        // Initialize the database
        dataMappingRepository.saveAndFlush(dataMapping);

        Long id = dataMapping.getId();

        defaultDataMappingShouldBeFound("id.equals=" + id);
        defaultDataMappingShouldNotBeFound("id.notEquals=" + id);

        defaultDataMappingShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDataMappingShouldNotBeFound("id.greaterThan=" + id);

        defaultDataMappingShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDataMappingShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDataMappingsBySrcColIdIsEqualToSomething() throws Exception {
        // Initialize the database
        dataMappingRepository.saveAndFlush(dataMapping);

        // Get all the dataMappingList where srcColId equals to DEFAULT_SRC_COL_ID
        defaultDataMappingShouldBeFound("srcColId.equals=" + DEFAULT_SRC_COL_ID);

        // Get all the dataMappingList where srcColId equals to UPDATED_SRC_COL_ID
        defaultDataMappingShouldNotBeFound("srcColId.equals=" + UPDATED_SRC_COL_ID);
    }

    @Test
    @Transactional
    public void getAllDataMappingsBySrcColIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dataMappingRepository.saveAndFlush(dataMapping);

        // Get all the dataMappingList where srcColId not equals to DEFAULT_SRC_COL_ID
        defaultDataMappingShouldNotBeFound("srcColId.notEquals=" + DEFAULT_SRC_COL_ID);

        // Get all the dataMappingList where srcColId not equals to UPDATED_SRC_COL_ID
        defaultDataMappingShouldBeFound("srcColId.notEquals=" + UPDATED_SRC_COL_ID);
    }

    @Test
    @Transactional
    public void getAllDataMappingsBySrcColIdIsInShouldWork() throws Exception {
        // Initialize the database
        dataMappingRepository.saveAndFlush(dataMapping);

        // Get all the dataMappingList where srcColId in DEFAULT_SRC_COL_ID or UPDATED_SRC_COL_ID
        defaultDataMappingShouldBeFound("srcColId.in=" + DEFAULT_SRC_COL_ID + "," + UPDATED_SRC_COL_ID);

        // Get all the dataMappingList where srcColId equals to UPDATED_SRC_COL_ID
        defaultDataMappingShouldNotBeFound("srcColId.in=" + UPDATED_SRC_COL_ID);
    }

    @Test
    @Transactional
    public void getAllDataMappingsBySrcColIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        dataMappingRepository.saveAndFlush(dataMapping);

        // Get all the dataMappingList where srcColId is not null
        defaultDataMappingShouldBeFound("srcColId.specified=true");

        // Get all the dataMappingList where srcColId is null
        defaultDataMappingShouldNotBeFound("srcColId.specified=false");
    }

    @Test
    @Transactional
    public void getAllDataMappingsBySrcColIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dataMappingRepository.saveAndFlush(dataMapping);

        // Get all the dataMappingList where srcColId is greater than or equal to DEFAULT_SRC_COL_ID
        defaultDataMappingShouldBeFound("srcColId.greaterThanOrEqual=" + DEFAULT_SRC_COL_ID);

        // Get all the dataMappingList where srcColId is greater than or equal to UPDATED_SRC_COL_ID
        defaultDataMappingShouldNotBeFound("srcColId.greaterThanOrEqual=" + UPDATED_SRC_COL_ID);
    }

    @Test
    @Transactional
    public void getAllDataMappingsBySrcColIdIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dataMappingRepository.saveAndFlush(dataMapping);

        // Get all the dataMappingList where srcColId is less than or equal to DEFAULT_SRC_COL_ID
        defaultDataMappingShouldBeFound("srcColId.lessThanOrEqual=" + DEFAULT_SRC_COL_ID);

        // Get all the dataMappingList where srcColId is less than or equal to SMALLER_SRC_COL_ID
        defaultDataMappingShouldNotBeFound("srcColId.lessThanOrEqual=" + SMALLER_SRC_COL_ID);
    }

    @Test
    @Transactional
    public void getAllDataMappingsBySrcColIdIsLessThanSomething() throws Exception {
        // Initialize the database
        dataMappingRepository.saveAndFlush(dataMapping);

        // Get all the dataMappingList where srcColId is less than DEFAULT_SRC_COL_ID
        defaultDataMappingShouldNotBeFound("srcColId.lessThan=" + DEFAULT_SRC_COL_ID);

        // Get all the dataMappingList where srcColId is less than UPDATED_SRC_COL_ID
        defaultDataMappingShouldBeFound("srcColId.lessThan=" + UPDATED_SRC_COL_ID);
    }

    @Test
    @Transactional
    public void getAllDataMappingsBySrcColIdIsGreaterThanSomething() throws Exception {
        // Initialize the database
        dataMappingRepository.saveAndFlush(dataMapping);

        // Get all the dataMappingList where srcColId is greater than DEFAULT_SRC_COL_ID
        defaultDataMappingShouldNotBeFound("srcColId.greaterThan=" + DEFAULT_SRC_COL_ID);

        // Get all the dataMappingList where srcColId is greater than SMALLER_SRC_COL_ID
        defaultDataMappingShouldBeFound("srcColId.greaterThan=" + SMALLER_SRC_COL_ID);
    }


    @Test
    @Transactional
    public void getAllDataMappingsByColIsEqualToSomething() throws Exception {
        // Initialize the database
        dataMappingRepository.saveAndFlush(dataMapping);
        DsColumns col = DsColumnsResourceIT.createEntity(em);
        em.persist(col);
        em.flush();
        dataMapping.setCol(col);
        dataMappingRepository.saveAndFlush(dataMapping);
        Long colId = col.getId();

        // Get all the dataMappingList where col equals to colId
        defaultDataMappingShouldBeFound("colId.equals=" + colId);

        // Get all the dataMappingList where col equals to colId + 1
        defaultDataMappingShouldNotBeFound("colId.equals=" + (colId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDataMappingShouldBeFound(String filter) throws Exception {
        restDataMappingMockMvc.perform(get("/api/data-mappings?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dataMapping.getId().intValue())))
            .andExpect(jsonPath("$.[*].srcColId").value(hasItem(DEFAULT_SRC_COL_ID)));

        // Check, that the count call also returns 1
        restDataMappingMockMvc.perform(get("/api/data-mappings/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDataMappingShouldNotBeFound(String filter) throws Exception {
        restDataMappingMockMvc.perform(get("/api/data-mappings?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDataMappingMockMvc.perform(get("/api/data-mappings/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDataMapping() throws Exception {
        // Get the dataMapping
        restDataMappingMockMvc.perform(get("/api/data-mappings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDataMapping() throws Exception {
        // Initialize the database
        dataMappingRepository.saveAndFlush(dataMapping);

        int databaseSizeBeforeUpdate = dataMappingRepository.findAll().size();

        // Update the dataMapping
        DataMapping updatedDataMapping = dataMappingRepository.findById(dataMapping.getId()).get();
        // Disconnect from session so that the updates on updatedDataMapping are not directly saved in db
        em.detach(updatedDataMapping);
        updatedDataMapping
            .srcColId(UPDATED_SRC_COL_ID);
        DataMappingDTO dataMappingDTO = dataMappingMapper.toDto(updatedDataMapping);

        restDataMappingMockMvc.perform(put("/api/data-mappings")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataMappingDTO)))
            .andExpect(status().isOk());

        // Validate the DataMapping in the database
        List<DataMapping> dataMappingList = dataMappingRepository.findAll();
        assertThat(dataMappingList).hasSize(databaseSizeBeforeUpdate);
        DataMapping testDataMapping = dataMappingList.get(dataMappingList.size() - 1);
        assertThat(testDataMapping.getSrcColId()).isEqualTo(UPDATED_SRC_COL_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingDataMapping() throws Exception {
        int databaseSizeBeforeUpdate = dataMappingRepository.findAll().size();

        // Create the DataMapping
        DataMappingDTO dataMappingDTO = dataMappingMapper.toDto(dataMapping);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDataMappingMockMvc.perform(put("/api/data-mappings")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataMappingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DataMapping in the database
        List<DataMapping> dataMappingList = dataMappingRepository.findAll();
        assertThat(dataMappingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDataMapping() throws Exception {
        // Initialize the database
        dataMappingRepository.saveAndFlush(dataMapping);

        int databaseSizeBeforeDelete = dataMappingRepository.findAll().size();

        // Delete the dataMapping
        restDataMappingMockMvc.perform(delete("/api/data-mappings/{id}", dataMapping.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DataMapping> dataMappingList = dataMappingRepository.findAll();
        assertThat(dataMappingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
