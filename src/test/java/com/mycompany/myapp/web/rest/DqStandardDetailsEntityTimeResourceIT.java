package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.DqStandardDetailsEntityTime;
import com.mycompany.myapp.domain.DqStandards;
import com.mycompany.myapp.repository.DqStandardDetailsEntityTimeRepository;
import com.mycompany.myapp.service.DqStandardDetailsEntityTimeService;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityTimeDTO;
import com.mycompany.myapp.service.mapper.DqStandardDetailsEntityTimeMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityTimeCriteria;
import com.mycompany.myapp.service.DqStandardDetailsEntityTimeQueryService;

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
 * Integration tests for the {@link DqStandardDetailsEntityTimeResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class DqStandardDetailsEntityTimeResourceIT {

    private static final String DEFAULT_STD_ATTRIBUTE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STD_ATTRIBUTE_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_STD_ATTRIBUTE_VALUE = 1L;
    private static final Long UPDATED_STD_ATTRIBUTE_VALUE = 2L;
    private static final Long SMALLER_STD_ATTRIBUTE_VALUE = 1L - 1L;

    @Autowired
    private DqStandardDetailsEntityTimeRepository dqStandardDetailsEntityTimeRepository;

    @Autowired
    private DqStandardDetailsEntityTimeMapper dqStandardDetailsEntityTimeMapper;

    @Autowired
    private DqStandardDetailsEntityTimeService dqStandardDetailsEntityTimeService;

    @Autowired
    private DqStandardDetailsEntityTimeQueryService dqStandardDetailsEntityTimeQueryService;

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

    private MockMvc restDqStandardDetailsEntityTimeMockMvc;

    private DqStandardDetailsEntityTime dqStandardDetailsEntityTime;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DqStandardDetailsEntityTimeResource dqStandardDetailsEntityTimeResource = new DqStandardDetailsEntityTimeResource(dqStandardDetailsEntityTimeService, dqStandardDetailsEntityTimeQueryService);
        this.restDqStandardDetailsEntityTimeMockMvc = MockMvcBuilders.standaloneSetup(dqStandardDetailsEntityTimeResource)
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
    public static DqStandardDetailsEntityTime createEntity(EntityManager em) {
        DqStandardDetailsEntityTime dqStandardDetailsEntityTime = new DqStandardDetailsEntityTime()
            .stdAttributeName(DEFAULT_STD_ATTRIBUTE_NAME)
            .stdAttributeValue(DEFAULT_STD_ATTRIBUTE_VALUE);
        return dqStandardDetailsEntityTime;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DqStandardDetailsEntityTime createUpdatedEntity(EntityManager em) {
        DqStandardDetailsEntityTime dqStandardDetailsEntityTime = new DqStandardDetailsEntityTime()
            .stdAttributeName(UPDATED_STD_ATTRIBUTE_NAME)
            .stdAttributeValue(UPDATED_STD_ATTRIBUTE_VALUE);
        return dqStandardDetailsEntityTime;
    }

    @BeforeEach
    public void initTest() {
        dqStandardDetailsEntityTime = createEntity(em);
    }

    @Test
    @Transactional
    public void createDqStandardDetailsEntityTime() throws Exception {
        int databaseSizeBeforeCreate = dqStandardDetailsEntityTimeRepository.findAll().size();

        // Create the DqStandardDetailsEntityTime
        DqStandardDetailsEntityTimeDTO dqStandardDetailsEntityTimeDTO = dqStandardDetailsEntityTimeMapper.toDto(dqStandardDetailsEntityTime);
        restDqStandardDetailsEntityTimeMockMvc.perform(post("/api/dq-standard-details-entity-times")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqStandardDetailsEntityTimeDTO)))
            .andExpect(status().isCreated());

        // Validate the DqStandardDetailsEntityTime in the database
        List<DqStandardDetailsEntityTime> dqStandardDetailsEntityTimeList = dqStandardDetailsEntityTimeRepository.findAll();
        assertThat(dqStandardDetailsEntityTimeList).hasSize(databaseSizeBeforeCreate + 1);
        DqStandardDetailsEntityTime testDqStandardDetailsEntityTime = dqStandardDetailsEntityTimeList.get(dqStandardDetailsEntityTimeList.size() - 1);
        assertThat(testDqStandardDetailsEntityTime.getStdAttributeName()).isEqualTo(DEFAULT_STD_ATTRIBUTE_NAME);
        assertThat(testDqStandardDetailsEntityTime.getStdAttributeValue()).isEqualTo(DEFAULT_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void createDqStandardDetailsEntityTimeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dqStandardDetailsEntityTimeRepository.findAll().size();

        // Create the DqStandardDetailsEntityTime with an existing ID
        dqStandardDetailsEntityTime.setId(1L);
        DqStandardDetailsEntityTimeDTO dqStandardDetailsEntityTimeDTO = dqStandardDetailsEntityTimeMapper.toDto(dqStandardDetailsEntityTime);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDqStandardDetailsEntityTimeMockMvc.perform(post("/api/dq-standard-details-entity-times")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqStandardDetailsEntityTimeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DqStandardDetailsEntityTime in the database
        List<DqStandardDetailsEntityTime> dqStandardDetailsEntityTimeList = dqStandardDetailsEntityTimeRepository.findAll();
        assertThat(dqStandardDetailsEntityTimeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityTimes() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTimeRepository.saveAndFlush(dqStandardDetailsEntityTime);

        // Get all the dqStandardDetailsEntityTimeList
        restDqStandardDetailsEntityTimeMockMvc.perform(get("/api/dq-standard-details-entity-times?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dqStandardDetailsEntityTime.getId().intValue())))
            .andExpect(jsonPath("$.[*].stdAttributeName").value(hasItem(DEFAULT_STD_ATTRIBUTE_NAME)))
            .andExpect(jsonPath("$.[*].stdAttributeValue").value(hasItem(DEFAULT_STD_ATTRIBUTE_VALUE.intValue())));
    }
    
    @Test
    @Transactional
    public void getDqStandardDetailsEntityTime() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTimeRepository.saveAndFlush(dqStandardDetailsEntityTime);

        // Get the dqStandardDetailsEntityTime
        restDqStandardDetailsEntityTimeMockMvc.perform(get("/api/dq-standard-details-entity-times/{id}", dqStandardDetailsEntityTime.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dqStandardDetailsEntityTime.getId().intValue()))
            .andExpect(jsonPath("$.stdAttributeName").value(DEFAULT_STD_ATTRIBUTE_NAME))
            .andExpect(jsonPath("$.stdAttributeValue").value(DEFAULT_STD_ATTRIBUTE_VALUE.intValue()));
    }


    @Test
    @Transactional
    public void getDqStandardDetailsEntityTimesByIdFiltering() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTimeRepository.saveAndFlush(dqStandardDetailsEntityTime);

        Long id = dqStandardDetailsEntityTime.getId();

        defaultDqStandardDetailsEntityTimeShouldBeFound("id.equals=" + id);
        defaultDqStandardDetailsEntityTimeShouldNotBeFound("id.notEquals=" + id);

        defaultDqStandardDetailsEntityTimeShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDqStandardDetailsEntityTimeShouldNotBeFound("id.greaterThan=" + id);

        defaultDqStandardDetailsEntityTimeShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDqStandardDetailsEntityTimeShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityTimesByStdAttributeNameIsEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTimeRepository.saveAndFlush(dqStandardDetailsEntityTime);

        // Get all the dqStandardDetailsEntityTimeList where stdAttributeName equals to DEFAULT_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityTimeShouldBeFound("stdAttributeName.equals=" + DEFAULT_STD_ATTRIBUTE_NAME);

        // Get all the dqStandardDetailsEntityTimeList where stdAttributeName equals to UPDATED_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityTimeShouldNotBeFound("stdAttributeName.equals=" + UPDATED_STD_ATTRIBUTE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityTimesByStdAttributeNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTimeRepository.saveAndFlush(dqStandardDetailsEntityTime);

        // Get all the dqStandardDetailsEntityTimeList where stdAttributeName not equals to DEFAULT_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityTimeShouldNotBeFound("stdAttributeName.notEquals=" + DEFAULT_STD_ATTRIBUTE_NAME);

        // Get all the dqStandardDetailsEntityTimeList where stdAttributeName not equals to UPDATED_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityTimeShouldBeFound("stdAttributeName.notEquals=" + UPDATED_STD_ATTRIBUTE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityTimesByStdAttributeNameIsInShouldWork() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTimeRepository.saveAndFlush(dqStandardDetailsEntityTime);

        // Get all the dqStandardDetailsEntityTimeList where stdAttributeName in DEFAULT_STD_ATTRIBUTE_NAME or UPDATED_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityTimeShouldBeFound("stdAttributeName.in=" + DEFAULT_STD_ATTRIBUTE_NAME + "," + UPDATED_STD_ATTRIBUTE_NAME);

        // Get all the dqStandardDetailsEntityTimeList where stdAttributeName equals to UPDATED_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityTimeShouldNotBeFound("stdAttributeName.in=" + UPDATED_STD_ATTRIBUTE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityTimesByStdAttributeNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTimeRepository.saveAndFlush(dqStandardDetailsEntityTime);

        // Get all the dqStandardDetailsEntityTimeList where stdAttributeName is not null
        defaultDqStandardDetailsEntityTimeShouldBeFound("stdAttributeName.specified=true");

        // Get all the dqStandardDetailsEntityTimeList where stdAttributeName is null
        defaultDqStandardDetailsEntityTimeShouldNotBeFound("stdAttributeName.specified=false");
    }
                @Test
    @Transactional
    public void getAllDqStandardDetailsEntityTimesByStdAttributeNameContainsSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTimeRepository.saveAndFlush(dqStandardDetailsEntityTime);

        // Get all the dqStandardDetailsEntityTimeList where stdAttributeName contains DEFAULT_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityTimeShouldBeFound("stdAttributeName.contains=" + DEFAULT_STD_ATTRIBUTE_NAME);

        // Get all the dqStandardDetailsEntityTimeList where stdAttributeName contains UPDATED_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityTimeShouldNotBeFound("stdAttributeName.contains=" + UPDATED_STD_ATTRIBUTE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityTimesByStdAttributeNameNotContainsSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTimeRepository.saveAndFlush(dqStandardDetailsEntityTime);

        // Get all the dqStandardDetailsEntityTimeList where stdAttributeName does not contain DEFAULT_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityTimeShouldNotBeFound("stdAttributeName.doesNotContain=" + DEFAULT_STD_ATTRIBUTE_NAME);

        // Get all the dqStandardDetailsEntityTimeList where stdAttributeName does not contain UPDATED_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityTimeShouldBeFound("stdAttributeName.doesNotContain=" + UPDATED_STD_ATTRIBUTE_NAME);
    }


    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityTimesByStdAttributeValueIsEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTimeRepository.saveAndFlush(dqStandardDetailsEntityTime);

        // Get all the dqStandardDetailsEntityTimeList where stdAttributeValue equals to DEFAULT_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityTimeShouldBeFound("stdAttributeValue.equals=" + DEFAULT_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityTimeList where stdAttributeValue equals to UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityTimeShouldNotBeFound("stdAttributeValue.equals=" + UPDATED_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityTimesByStdAttributeValueIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTimeRepository.saveAndFlush(dqStandardDetailsEntityTime);

        // Get all the dqStandardDetailsEntityTimeList where stdAttributeValue not equals to DEFAULT_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityTimeShouldNotBeFound("stdAttributeValue.notEquals=" + DEFAULT_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityTimeList where stdAttributeValue not equals to UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityTimeShouldBeFound("stdAttributeValue.notEquals=" + UPDATED_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityTimesByStdAttributeValueIsInShouldWork() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTimeRepository.saveAndFlush(dqStandardDetailsEntityTime);

        // Get all the dqStandardDetailsEntityTimeList where stdAttributeValue in DEFAULT_STD_ATTRIBUTE_VALUE or UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityTimeShouldBeFound("stdAttributeValue.in=" + DEFAULT_STD_ATTRIBUTE_VALUE + "," + UPDATED_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityTimeList where stdAttributeValue equals to UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityTimeShouldNotBeFound("stdAttributeValue.in=" + UPDATED_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityTimesByStdAttributeValueIsNullOrNotNull() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTimeRepository.saveAndFlush(dqStandardDetailsEntityTime);

        // Get all the dqStandardDetailsEntityTimeList where stdAttributeValue is not null
        defaultDqStandardDetailsEntityTimeShouldBeFound("stdAttributeValue.specified=true");

        // Get all the dqStandardDetailsEntityTimeList where stdAttributeValue is null
        defaultDqStandardDetailsEntityTimeShouldNotBeFound("stdAttributeValue.specified=false");
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityTimesByStdAttributeValueIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTimeRepository.saveAndFlush(dqStandardDetailsEntityTime);

        // Get all the dqStandardDetailsEntityTimeList where stdAttributeValue is greater than or equal to DEFAULT_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityTimeShouldBeFound("stdAttributeValue.greaterThanOrEqual=" + DEFAULT_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityTimeList where stdAttributeValue is greater than or equal to UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityTimeShouldNotBeFound("stdAttributeValue.greaterThanOrEqual=" + UPDATED_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityTimesByStdAttributeValueIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTimeRepository.saveAndFlush(dqStandardDetailsEntityTime);

        // Get all the dqStandardDetailsEntityTimeList where stdAttributeValue is less than or equal to DEFAULT_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityTimeShouldBeFound("stdAttributeValue.lessThanOrEqual=" + DEFAULT_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityTimeList where stdAttributeValue is less than or equal to SMALLER_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityTimeShouldNotBeFound("stdAttributeValue.lessThanOrEqual=" + SMALLER_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityTimesByStdAttributeValueIsLessThanSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTimeRepository.saveAndFlush(dqStandardDetailsEntityTime);

        // Get all the dqStandardDetailsEntityTimeList where stdAttributeValue is less than DEFAULT_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityTimeShouldNotBeFound("stdAttributeValue.lessThan=" + DEFAULT_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityTimeList where stdAttributeValue is less than UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityTimeShouldBeFound("stdAttributeValue.lessThan=" + UPDATED_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityTimesByStdAttributeValueIsGreaterThanSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTimeRepository.saveAndFlush(dqStandardDetailsEntityTime);

        // Get all the dqStandardDetailsEntityTimeList where stdAttributeValue is greater than DEFAULT_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityTimeShouldNotBeFound("stdAttributeValue.greaterThan=" + DEFAULT_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityTimeList where stdAttributeValue is greater than SMALLER_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityTimeShouldBeFound("stdAttributeValue.greaterThan=" + SMALLER_STD_ATTRIBUTE_VALUE);
    }


    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityTimesByStdIsEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTimeRepository.saveAndFlush(dqStandardDetailsEntityTime);
        DqStandards std = DqStandardsResourceIT.createEntity(em);
        em.persist(std);
        em.flush();
        dqStandardDetailsEntityTime.setStd(std);
        dqStandardDetailsEntityTimeRepository.saveAndFlush(dqStandardDetailsEntityTime);
        Long stdId = std.getId();

        // Get all the dqStandardDetailsEntityTimeList where std equals to stdId
        defaultDqStandardDetailsEntityTimeShouldBeFound("stdId.equals=" + stdId);

        // Get all the dqStandardDetailsEntityTimeList where std equals to stdId + 1
        defaultDqStandardDetailsEntityTimeShouldNotBeFound("stdId.equals=" + (stdId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDqStandardDetailsEntityTimeShouldBeFound(String filter) throws Exception {
        restDqStandardDetailsEntityTimeMockMvc.perform(get("/api/dq-standard-details-entity-times?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dqStandardDetailsEntityTime.getId().intValue())))
            .andExpect(jsonPath("$.[*].stdAttributeName").value(hasItem(DEFAULT_STD_ATTRIBUTE_NAME)))
            .andExpect(jsonPath("$.[*].stdAttributeValue").value(hasItem(DEFAULT_STD_ATTRIBUTE_VALUE.intValue())));

        // Check, that the count call also returns 1
        restDqStandardDetailsEntityTimeMockMvc.perform(get("/api/dq-standard-details-entity-times/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDqStandardDetailsEntityTimeShouldNotBeFound(String filter) throws Exception {
        restDqStandardDetailsEntityTimeMockMvc.perform(get("/api/dq-standard-details-entity-times?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDqStandardDetailsEntityTimeMockMvc.perform(get("/api/dq-standard-details-entity-times/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDqStandardDetailsEntityTime() throws Exception {
        // Get the dqStandardDetailsEntityTime
        restDqStandardDetailsEntityTimeMockMvc.perform(get("/api/dq-standard-details-entity-times/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDqStandardDetailsEntityTime() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTimeRepository.saveAndFlush(dqStandardDetailsEntityTime);

        int databaseSizeBeforeUpdate = dqStandardDetailsEntityTimeRepository.findAll().size();

        // Update the dqStandardDetailsEntityTime
        DqStandardDetailsEntityTime updatedDqStandardDetailsEntityTime = dqStandardDetailsEntityTimeRepository.findById(dqStandardDetailsEntityTime.getId()).get();
        // Disconnect from session so that the updates on updatedDqStandardDetailsEntityTime are not directly saved in db
        em.detach(updatedDqStandardDetailsEntityTime);
        updatedDqStandardDetailsEntityTime
            .stdAttributeName(UPDATED_STD_ATTRIBUTE_NAME)
            .stdAttributeValue(UPDATED_STD_ATTRIBUTE_VALUE);
        DqStandardDetailsEntityTimeDTO dqStandardDetailsEntityTimeDTO = dqStandardDetailsEntityTimeMapper.toDto(updatedDqStandardDetailsEntityTime);

        restDqStandardDetailsEntityTimeMockMvc.perform(put("/api/dq-standard-details-entity-times")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqStandardDetailsEntityTimeDTO)))
            .andExpect(status().isOk());

        // Validate the DqStandardDetailsEntityTime in the database
        List<DqStandardDetailsEntityTime> dqStandardDetailsEntityTimeList = dqStandardDetailsEntityTimeRepository.findAll();
        assertThat(dqStandardDetailsEntityTimeList).hasSize(databaseSizeBeforeUpdate);
        DqStandardDetailsEntityTime testDqStandardDetailsEntityTime = dqStandardDetailsEntityTimeList.get(dqStandardDetailsEntityTimeList.size() - 1);
        assertThat(testDqStandardDetailsEntityTime.getStdAttributeName()).isEqualTo(UPDATED_STD_ATTRIBUTE_NAME);
        assertThat(testDqStandardDetailsEntityTime.getStdAttributeValue()).isEqualTo(UPDATED_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingDqStandardDetailsEntityTime() throws Exception {
        int databaseSizeBeforeUpdate = dqStandardDetailsEntityTimeRepository.findAll().size();

        // Create the DqStandardDetailsEntityTime
        DqStandardDetailsEntityTimeDTO dqStandardDetailsEntityTimeDTO = dqStandardDetailsEntityTimeMapper.toDto(dqStandardDetailsEntityTime);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDqStandardDetailsEntityTimeMockMvc.perform(put("/api/dq-standard-details-entity-times")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqStandardDetailsEntityTimeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DqStandardDetailsEntityTime in the database
        List<DqStandardDetailsEntityTime> dqStandardDetailsEntityTimeList = dqStandardDetailsEntityTimeRepository.findAll();
        assertThat(dqStandardDetailsEntityTimeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDqStandardDetailsEntityTime() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTimeRepository.saveAndFlush(dqStandardDetailsEntityTime);

        int databaseSizeBeforeDelete = dqStandardDetailsEntityTimeRepository.findAll().size();

        // Delete the dqStandardDetailsEntityTime
        restDqStandardDetailsEntityTimeMockMvc.perform(delete("/api/dq-standard-details-entity-times/{id}", dqStandardDetailsEntityTime.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DqStandardDetailsEntityTime> dqStandardDetailsEntityTimeList = dqStandardDetailsEntityTimeRepository.findAll();
        assertThat(dqStandardDetailsEntityTimeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
